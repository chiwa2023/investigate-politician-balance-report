package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * ZenginChangeAddProcessor単体テスト
 */
class ZenginChangeAddProcessorTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        ZenginChangeAddProcessor addProcessor = new ZenginChangeAddProcessor();
        
        ZenginOrgChangeBranchEntity entity = new ZenginOrgChangeBranchEntity();
        entity.setChangeKbn(100);

        ZenginOrgChangeBranchEntity entityAnswer = addProcessor.process(entity);

        assertEquals(1, entityAnswer.getChangeKbn(),"区分は1:追加");
        assertEquals("追加", entityAnswer.getChangeKbnText(),"区分1テキスト:追加");
        
    }

}
