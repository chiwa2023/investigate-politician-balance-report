package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.constants.ZenginOrgChangeKbnConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgChangeBranchRepository;

/**
 * EraseChangeIdoDeleteRowTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EraseChangeIdoDeleteRowTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EraseChangeIdoDeleteRowTasklet eraseChangeIdoDeleteRowTasklet;

    /** 金融機関支店異動Repository */
    @Autowired
    private ZenginOrgChangeBranchRepository zenginOrgChangeBranchRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "zengin_org_change_branch.sql", "zengin_org_branch_master.sql" })
    void testNoData() throws Exception {

        assertEquals(5L, zenginOrgChangeBranchRepository.count(), "初期5件");

        // とにかく最後まで実行(該当データがないのでなにもしない)
        assertEquals(RepeatStatus.FINISHED, eraseChangeIdoDeleteRowTasklet.execute(null, null), "正常に実行できる");

        assertEquals(5L, zenginOrgChangeBranchRepository.count(), "履歴が積みあがらず5件のまま");

    }

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "zengin_org_change_branch.sql", "zengin_org_branch_master_erase.sql" })
    void test() throws Exception {

        assertEquals(5L, zenginOrgChangeBranchRepository.count(), "初期5件");

        // 1件ある削除データを消化する
        assertEquals(RepeatStatus.FINISHED, eraseChangeIdoDeleteRowTasklet.execute(null, null), "正常に実行できる");

        assertEquals(6L, zenginOrgChangeBranchRepository.count(), "履歴が積みあがり6件");

        List<Integer> listId = new ArrayList<>();
        listId.add(0);
        List<ZenginOrgChangeBranchEntity> listYet = zenginOrgChangeBranchRepository
                .findByYetFinished(ZenginOrgChangeKbnConstants.DELETE, listId);
        assertTrue(listYet.isEmpty(), "追加で未処理を取得してもリストが空");

    }

}
