package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliPartyUsageReportRepository;

/**
 * UpdateFinishedTaskPlanPartyUsageDetailItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdateFinishedTaskPlanPartyUsageDetailItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdateFinishedTaskPlanPartyUsageDetailItemWriter updateFinishedTaskPlanPartyUsageDetailItemWriter;

    /** ワークテーブルRepository */
    @Autowired
    private WkTblPoliPartyUsageReportRepository wkTblPoliPartyUsageReportRepository;

    /** 一括処理計画Rpository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "wk_tbl_poli_party_usage_report_read.sql", "task_plan_party_usage_detail_read.sql" })
    void test() throws Exception {

        Page<WkTblPoliPartyUsageReportEntity> page = wkTblPoliPartyUsageReportRepository.findBySaishinKbn(1,
                Pageable.ofSize(100));
        Chunk<? extends WkTblPoliPartyUsageReportEntity> items = new Chunk<>(page.toList());

        updateFinishedTaskPlanPartyUsageDetailItemWriter.write(items);

        TaskPlanPartyUsageDetailEntity detailEntity01 = taskPlanPartyUsageDetailRepository.findById(1245L).get();
        assertEquals(0, detailEntity01.getSaishinKbn(), "履歴に変換"); // NOPMD
        assertEquals(true, detailEntity01.getIsFinished(), "終了フラグを立てる"); // NOPMD

        TaskPlanPartyUsageDetailEntity detailEntity02 = taskPlanPartyUsageDetailRepository.findById(1246L).get();
        assertEquals(0, detailEntity02.getSaishinKbn(), "履歴に変換");
        assertEquals(true, detailEntity02.getIsFinished(), "終了フラグを立てる");

        TaskPlanPartyUsageDetailEntity detailEntity03 = taskPlanPartyUsageDetailRepository.findById(1434L).get();
        assertEquals(0, detailEntity03.getSaishinKbn(), "履歴に変換");
        assertEquals(true, detailEntity03.getIsFinished(), "終了フラグを立てる");

        TaskPlanPartyUsageDetailEntity detailEntity04 = taskPlanPartyUsageDetailRepository.findById(1435L).get();
        assertEquals(0, detailEntity04.getSaishinKbn(), "履歴に変換");
        assertEquals(true, detailEntity04.getIsFinished(), "終了フラグを立てる");

    }

}
