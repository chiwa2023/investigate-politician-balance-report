package mitei.mitei.investigate.report.balance.politician.service.zengin_org;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgChangeBranchRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * InsertDeleteByEntityListService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertDeleteByEntityListServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertDeleteByEntityListService insertDeleteByEntityListService;

    /** 全銀変更内容保存Repository */
    @Autowired
    private ZenginOrgChangeBranchRepository zenginOrgChangeBranRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql("zengin_org_change_branch.sql")
    void test() throws Exception {

        // 廃止で終了しておらず新規を抽出し移動先がセットされた想定
        Pageable pageable = Pageable.unpaged();
        List<ZenginOrgChangeBranchEntity> list = zenginOrgChangeBranRepository
                .findByChangeKbnAndIsFinishedAndSaishinKbn(2, false, 1, pageable).toList();

        assertEquals(2, list.size(), "廃止店舗2件");

        // 廃止である前橋支店のみを取得して異動先を設定する
        ZenginOrgChangeBranchEntity entity = list.stream().filter((e) -> "3000".equals(e.getOrgCode())).toList().get(0);
        Integer preId = entity.getZenginOrgChangeBranchId();
        entity.setZenginOrgMoveId(600);
        entity.setZenginOrgMoveCode(601);
        entity.setZenginOrgMoveName("602");

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        int size = insertDeleteByEntityListService.practice(list, privilegeDto);
        assertEquals(2, size, "更新は元データ履歴と異動を反映した新データ");

        List<ZenginOrgChangeBranchEntity> listPro = zenginOrgChangeBranRepository
                .findByChangeKbnAndIsFinishedAndSaishinKbn(2, false, 1, pageable).toList();

        assertEquals(2, listPro.size(), "前橋支店の新規と未設定データを取得");

        ZenginOrgChangeBranchEntity entityNew = listPro.stream().filter((e) -> "3000".equals(e.getOrgCode())).toList()
                .get(0); // 店舗コードでフィルタすると1件のみ
        assertEquals(600, entityNew.getZenginOrgMoveId(), "設定した異動先Id");
        assertEquals(601, entityNew.getZenginOrgMoveCode(), "設定した異動先code");
        assertEquals("602", entityNew.getZenginOrgMoveName(), "設定した異動先Name");

        ZenginOrgChangeBranchEntity entityPre = zenginOrgChangeBranRepository.findById(preId).get();
        assertEquals(0, entityPre.getSaishinKbn(), "履歴");
        assertEquals(0, entityPre.getZenginOrgMoveId(), "履歴は設定前の値であることid");
        assertEquals(0, entityPre.getZenginOrgMoveCode(), "履歴は設定前の値であることcode");
        assertEquals("", entityPre.getZenginOrgMoveName(), "履歴は設定前の値であることName");

    }

}
