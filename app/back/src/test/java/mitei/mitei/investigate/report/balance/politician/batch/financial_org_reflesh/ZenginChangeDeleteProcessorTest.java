package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * ZenginChangeDeleteProcessor単体テスト
 */
class ZenginChangeDeleteProcessorTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ZenginChangeDeleteProcessor deleteProcessor = new ZenginChangeDeleteProcessor();

        ZenginOrgChangeBranchEntity entity = new ZenginOrgChangeBranchEntity();
        entity.setChangeKbn(100);

        ZenginOrgChangeBranchEntity entityAnswer = deleteProcessor.process(entity);

        assertEquals(2, entityAnswer.getChangeKbn(), "区分は2:廃止");
        assertEquals("廃止", entityAnswer.getChangeKbnText(), "区分2テキスト:廃止");

    }

}
