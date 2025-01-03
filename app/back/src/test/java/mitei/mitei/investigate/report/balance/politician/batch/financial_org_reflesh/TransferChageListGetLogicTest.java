package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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

/**
 * TransferChageListGetLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class TransferChageListGetLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private TransferChageListGetLogic transferChageListGetLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample/zengin_org_change_branch.sql")
    void test() throws Exception {

        List<ZenginOrgChangeBranchEntity> listChange = transferChageListGetLogic.practice();
        assertEquals(16, listChange.size(), "取得件数が一致");
        // id=81 相模大野支店は廃止だが異動先が示されていないので唯一伝送に含まれない

        List<ZenginOrgChangeBranchEntity> listDelete = listChange.stream()
                .filter(e -> ZenginOrgChangeKbnConstants.DELETE == e.getChangeKbn()).toList();

        assertEquals(80, listDelete.get(0).getZenginOrgChangeBranchId(), "idが一致"); // NOPMD
        assertEquals(85, listDelete.get(1).getZenginOrgChangeBranchId(), "idが一致");
        assertEquals(89, listDelete.get(2).getZenginOrgChangeBranchId(), "idが一致");
        assertEquals(93, listDelete.get(3).getZenginOrgChangeBranchId(), "idが一致");

    }

}
