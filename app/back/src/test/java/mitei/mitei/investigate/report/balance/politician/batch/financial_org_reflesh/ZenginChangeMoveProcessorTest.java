package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * ZenginChangeMoveProcessor単体テスト
 */
class ZenginChangeMoveProcessorTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ZenginChangeMoveProcessor moveProcessor = new ZenginChangeMoveProcessor();

        ZenginOrgChangeBranchEntity entity = new ZenginOrgChangeBranchEntity();
        entity.setChangeKbn(100);

        ZenginOrgChangeBranchEntity entityAnswer = moveProcessor.process(entity);

        assertEquals(3, entityAnswer.getChangeKbn(), "区分は3:移転");
        assertEquals("移転", entityAnswer.getChangeKbnText(), "区分3テキスト:移転");

    }

}
