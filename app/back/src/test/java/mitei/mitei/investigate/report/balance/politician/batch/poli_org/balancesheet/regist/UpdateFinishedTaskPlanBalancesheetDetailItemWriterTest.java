package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

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

import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDetailRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;

/**
 * UpdateFinishedTaskPlanBalancesheetDetailItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdateFinishedTaskPlanBalancesheetDetailItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdateFinishedTaskPlanBalancesheetDetailItemWriter updateFinishedTaskPlanBalancesheetDetailItemWriter;

    /** ワークテーブルRpository */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

    /** 一括処理計画Rpository */
    @Autowired
    private TaskPlanBalancesheetDetailRepository taskPlanBalancesheetDetailRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "wk_tbl_poli_org_balancesheet_report_read.sql", "task_plan_balancesheet_detail_read.sql" })
    void test() throws Exception {

        Page<WkTblPoliOrgBalancesheetReportEntity> page = wkTblPoliOrgBalancesheetReportRepository.findBySaishinKbn(1,
                Pageable.ofSize(100));
        Chunk<? extends WkTblPoliOrgBalancesheetReportEntity> items = new Chunk<>(page.toList());

        updateFinishedTaskPlanBalancesheetDetailItemWriter.write(items);

        TaskPlanBalancesheetDetailEntity detailEntity01 = taskPlanBalancesheetDetailRepository.findById(201L).get();
        assertEquals(0, detailEntity01.getSaishinKbn(), "履歴に変換"); // NOPMD
        assertEquals(true, detailEntity01.getIsFinished(), "終了フラグを立てる"); // NOPMD

        TaskPlanBalancesheetDetailEntity detailEntity02 = taskPlanBalancesheetDetailRepository.findById(401L).get();
        assertEquals(0, detailEntity02.getSaishinKbn(), "履歴に変換");
        assertEquals(true, detailEntity02.getIsFinished(), "終了フラグを立てる");

        TaskPlanBalancesheetDetailEntity detailEntity03 = taskPlanBalancesheetDetailRepository.findById(501L).get();
        assertEquals(0, detailEntity03.getSaishinKbn(), "履歴に変換");
        assertEquals(true, detailEntity03.getIsFinished(), "終了フラグを立てる");

        TaskPlanBalancesheetDetailEntity detailEntity04 = taskPlanBalancesheetDetailRepository.findById(701L).get();
        assertEquals(0, detailEntity04.getSaishinKbn(), "履歴に変換");
        assertEquals(true, detailEntity04.getIsFinished(), "終了フラグを立てる");

        TaskPlanBalancesheetDetailEntity detailEntity05 = taskPlanBalancesheetDetailRepository.findById(801L).get();
        assertEquals(0, detailEntity05.getSaishinKbn(), "履歴に変換");
        assertEquals(true, detailEntity05.getIsFinished(), "終了フラグを立てる");

    }

}
