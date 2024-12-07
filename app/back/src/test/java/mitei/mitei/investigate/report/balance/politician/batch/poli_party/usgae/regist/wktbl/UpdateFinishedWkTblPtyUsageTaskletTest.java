package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.wktbl;


import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDocumentRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliPartyUsageReportRepository;

/**
 * UpdateFinishedWkTblPtyUsageTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdateFinishedWkTblPtyUsageTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdateFinishedWkTblPtyUsageTasklet updateFinishedWkTblPtyUsageTasklet;

    /** 政治資金収支報告書一括処理ワークテーブル */
    @Autowired
    private WkTblPoliPartyUsageReportRepository wkTblPoliPartyUsageReportRepository;

    /** 政治資金収支報告書一括処理一時テーブル */
    @Autowired
    private TaskPlanPartyUsageDocumentRepository taskPlanPartyUsageDocumentRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "wk_tbl_poli_party_usage_report_read.sql", "task_plan_party_usage_document.sql" })
    void test() throws Exception {

        // とりあえず正常終了する
        assertEquals(RepeatStatus.FINISHED, updateFinishedWkTblPtyUsageTasklet.execute(null, null), "とりあえず正常終了");

        // すべてのデータを最新から移してきたので0になっている(はず)
        assertEquals(0, taskPlanPartyUsageDocumentRepository.findBySaishinKbnCount(), "全処理で該当データなし");

        WkTblPoliPartyUsageReportEntity entity01 = wkTblPoliPartyUsageReportRepository.findById(101L).get();
        assertEquals(0, entity01.getSaishinKbn(), "最初から履歴");

        WkTblPoliPartyUsageReportEntity entity02 = wkTblPoliPartyUsageReportRepository.findById(204L).get();
        assertEquals(0, entity02.getSaishinKbn(), "履歴に変更");

        WkTblPoliPartyUsageReportEntity entity03 = wkTblPoliPartyUsageReportRepository.findById(409L).get();
        assertEquals(0, entity03.getSaishinKbn(), "履歴に変更");

    }

}
