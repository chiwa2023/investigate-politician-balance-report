package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.CreateUkaiKenkinRouteSelectOptionLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UkaiKenkinRouteByStage01BatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UkaiKenkinRouteByStage01BatchConfigurationTest {

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(UkaiKenkinRouteByStage01BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage01;

    /** 作成データ抽出選択肢作成Logic */
    @Autowired
    private CreateUkaiKenkinRouteSelectOptionLogic createUkaiKenkinRouteSelectOptionLogic;

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(UkaiKenkinRouteByStage01BatchConfiguration.JOB_NAME, ukaiKenkinRouteByStage01.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    @Sql({ "configuration_income_2022.sql", "configuration_poli_org_property.sql",
            "configuration_wktbl_meisai_clean.sql", "configuration_wktbl_route.sql" })
    void test() throws Exception { // NOPMD
        // CHECKSTYLE:OFF

        jobLauncherTestUtils.setJob(ukaiKenkinRouteByStage01);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        Integer userCode = privilegeDto.getLoginUserCode();

        JobParameters jobParameters = new JobParametersBuilder(
                ukaiKenkinRouteByStage01.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addLong("userId", privilegeDto.getLoginUserId())
                .addLong("userCode", Long.valueOf(String.valueOf(userCode))).addString("userName", "ユーザA")
                .addLong("houkokuNen", 2022L).addString("isSearchKoufukin", "false").addLong("poliOrgCode", 100L)
                .addLong("taskPlanCode", Long.valueOf(260)).addString("taskName", "迂回献金キャッチャー")
                .addLong("year", Long.valueOf(2025)).toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

        // 全体を選択肢リスト形式で取得
        List<SelectOptionDto> listOption = createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
        listOption.remove(0); // 最初の1行は0階層(全)
        assertEquals(17, listOption.size(), "17経路取得できた");

        /* 個人・企業・政治団体(階層0) */

        // 経路0(0階層個人)
        SelectOptionDto dto00 = listOption.get(0);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute00 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto00.getValue()));
        assertEquals(2, listRoute00.size(), "経路0のデータ数は2");
        WkTblUkaiKenkinPickupRouteEntity entity00 = listRoute00.get(0);
        assertEquals(11L, entity00.getTablleId(), "経路0詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity01 = listRoute00.get(1);
        assertEquals(21L, entity01.getTablleId(), "経路0詳細1");

        // 経路1(0階層個人が団体代表者、政治団体関連者と一致)
        SelectOptionDto dto01 = listOption.get(1);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute01 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto01.getValue()));
        assertEquals(10, listRoute01.size(), "経路1のデータ数は10");
        WkTblUkaiKenkinPickupRouteEntity entity10 = listRoute01.get(0);
        assertEquals(11L, entity10.getTablleId(), "経路1詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity11 = listRoute01.get(1);
        assertEquals(21L, entity11.getTablleId(), "経路1詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity12 = listRoute01.get(2);
        assertEquals(12L, entity12.getTablleId(), "経路1詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity13 = listRoute01.get(3);
        assertEquals(13L, entity13.getTablleId(), "経路1詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity14 = listRoute01.get(4);
        assertEquals(14L, entity14.getTablleId(), "経路1詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity15 = listRoute01.get(5);
        assertEquals(15L, entity15.getTablleId(), "経路1詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity16 = listRoute01.get(6);
        assertEquals(16L, entity16.getTablleId(), "経路1詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity17 = listRoute01.get(7);
        assertEquals(17L, entity17.getTablleId(), "経路1詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity18 = listRoute01.get(8);
        assertEquals(18L, entity18.getTablleId(), "経路1詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity19 = listRoute01.get(9);
        assertEquals(22L, entity19.getTablleId(), "経路1詳細9");

        // 経路2(0階層企業)
        SelectOptionDto dto02 = listOption.get(2);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute02 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto02.getValue()));
        assertEquals(2, listRoute02.size(), "経路2のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity020 = listRoute02.get(0);
        assertEquals(12L, entity020.getTablleId(), "経路2詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity021 = listRoute02.get(1);
        assertEquals(22L, entity021.getTablleId(), "経路2詳細1");

        // 経路3(0階層政治団体)
        SelectOptionDto dto03 = listOption.get(3);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute03 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto03.getValue()));
        assertEquals(2, listRoute03.size(), "経路3のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity030 = listRoute03.get(0);
        assertEquals(23L, entity030.getTablleId(), "経路3詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity031 = listRoute03.get(1);
        assertEquals(24L, entity031.getTablleId(), "経路3詳細1");

        // 経路4(1階層個人から企業代表者)
        SelectOptionDto dto04 = listOption.get(4);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute04 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto04.getValue()));
        assertEquals(3, listRoute04.size(), "経路4のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity040 = listRoute04.get(0);
        assertEquals(30L, entity040.getTablleId(), "経路4詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity041 = listRoute04.get(1);
        assertEquals(11L, entity041.getTablleId(), "経路4詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity042 = listRoute04.get(2);
        assertEquals(33_101L, entity042.getTablleId(), "経路4詳細2");

        // 経路5(1階層個人から政治団体関連者)
        SelectOptionDto dto05 = listOption.get(5);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute05 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto05.getValue()));
        assertEquals(3, listRoute05.size(), "経路5のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity050 = listRoute05.get(0);
        assertEquals(30L, entity050.getTablleId(), "経路5詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity051 = listRoute05.get(1);
        assertEquals(11L, entity051.getTablleId(), "経路5詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity052 = listRoute05.get(2);
        assertEquals(34_101L, entity052.getTablleId(), "経路5詳細2");

        /* 個人迂回階層 */
        // 経路22(個人迂回1階層)
        SelectOptionDto dto22 = listOption.get(6);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute22 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto22.getValue()));
        assertEquals(4, listRoute22.size(), "経路22のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity220 = listRoute22.get(0);
        assertEquals(11_001L, entity220.getTablleId(), "経路22詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity221 = listRoute22.get(1);
        assertEquals(11_000L, entity221.getTablleId(), "経路22詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity222 = listRoute22.get(2);
        assertEquals(11_000L, entity222.getTablleId(), "経路22詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity223 = listRoute22.get(3);
        assertEquals(11_002L, entity223.getTablleId(), "経路22詳細3");

        /* 企業迂回階層 */
        // 経路31(企業迂回1階層)
        SelectOptionDto dto31 = listOption.get(7);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute31 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto31.getValue()));
        assertEquals(3, listRoute31.size(), "経路31のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity310 = listRoute31.get(0);
        assertEquals(12_001L, entity310.getTablleId(), "経路31詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity311 = listRoute31.get(1);
        assertEquals(12_000L, entity311.getTablleId(), "経路31詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity312 = listRoute31.get(2);
        assertEquals(12_002L, entity312.getTablleId(), "経路31詳細2");

        /* 政治団体迂回階層 */
        // 経路40(政治団体迂回1階層)
        SelectOptionDto dto40 = listOption.get(8);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute40 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto40.getValue()));
        assertEquals(3, listRoute40.size(), "経路40のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity400 = listRoute40.get(0);
        assertEquals(30L, entity400.getTablleId(), "経路40詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity401 = listRoute40.get(1);
        assertEquals(51L, entity401.getTablleId(), "経路40詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity402 = listRoute40.get(2);
        assertEquals(120L, entity402.getTablleId(), "経路40詳細2");

        // 経路49(0階層企業代表者が政治団体関連者と一致)
        SelectOptionDto dto49 = listOption.get(9);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute49 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto49.getValue()));
        assertEquals(8, listRoute49.size(), "経路49のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity490 = listRoute49.get(0);
        assertEquals(12L, entity490.getTablleId(), "経路49詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity491 = listRoute49.get(1);
        assertEquals(22L, entity491.getTablleId(), "経路49詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity492 = listRoute49.get(2);
        assertEquals(13L, entity492.getTablleId(), "経路49詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity493 = listRoute49.get(3);
        assertEquals(14L, entity493.getTablleId(), "経路49詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity494 = listRoute49.get(4);
        assertEquals(15L, entity494.getTablleId(), "経路49詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity495 = listRoute49.get(5);
        assertEquals(16L, entity495.getTablleId(), "経路49詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity496 = listRoute49.get(6);
        assertEquals(17L, entity496.getTablleId(), "経路49詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity497 = listRoute49.get(7);
        assertEquals(18L, entity497.getTablleId(), "経路49詳細7");

        /* 個人迂回階層 */
        // 経路50(個人迂回1階層)
        SelectOptionDto dto50 = listOption.get(10);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute50 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto50.getValue()));
        assertEquals(3, listRoute50.size(), "経路50のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity500 = listRoute50.get(0);
        assertEquals(30L, entity500.getTablleId(), "経路50詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity501 = listRoute50.get(1);
        assertEquals(12L, entity501.getTablleId(), "経路50詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity502 = listRoute50.get(2);
        assertEquals(33_101L, entity502.getTablleId(), "経路50詳細2");

        // 経路51(個人迂回1階層)
        SelectOptionDto dto51 = listOption.get(11);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute51 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto51.getValue()));
        assertEquals(3, listRoute51.size(), "経路51のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity510 = listRoute51.get(0);
        assertEquals(30L, entity510.getTablleId(), "経路51詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity511 = listRoute51.get(1);
        assertEquals(12L, entity511.getTablleId(), "経路51詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity512 = listRoute51.get(2);
        assertEquals(34_101L, entity512.getTablleId(), "経路51詳細2");

        /* 企業代表者迂回階層 */
        // 経路68(個人迂回1階層)
        SelectOptionDto dto68 = listOption.get(12);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute68 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto68.getValue()));
        assertEquals(3, listRoute68.size(), "経路68のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity680 = listRoute68.get(0);
        assertEquals(61L, entity680.getTablleId(), "経路68詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity681 = listRoute68.get(1);
        assertEquals(62L, entity681.getTablleId(), "経路68詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity682 = listRoute68.get(2);
        assertEquals(63L, entity682.getTablleId(), "経路68詳細2");

        // 経路69(個人迂回1階層)
        SelectOptionDto dto69 = listOption.get(13);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute69 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto69.getValue()));
        assertEquals(3, listRoute69.size(), "経路69のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity690 = listRoute69.get(0);
        assertEquals(30L, entity690.getTablleId(), "経路69詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity691 = listRoute69.get(1);
        assertEquals(61L, entity691.getTablleId(), "経路69詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity692 = listRoute69.get(2);
        assertEquals(31_101L, entity692.getTablleId(), "経路69詳細2");

        // 経路70(個人迂回1階層)
        SelectOptionDto dto70 = listOption.get(14);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute70 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto70.getValue()));
        assertEquals(3, listRoute70.size(), "経路70のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity700 = listRoute70.get(0);
        assertEquals(30L, entity700.getTablleId(), "経路70詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity701 = listRoute70.get(1);
        assertEquals(61L, entity701.getTablleId(), "経路70詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity702 = listRoute70.get(2);
        assertEquals(32_101L, entity702.getTablleId(), "経路70詳細2");

        // 経路87(政治団体迂回1階層)
        SelectOptionDto dto87 = listOption.get(15);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute87 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto87.getValue()));
        assertEquals(2, listRoute87.size(), "経路87のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity870 = listRoute87.get(0);
        assertEquals(72L, entity870.getTablleId(), "経路87詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity871 = listRoute87.get(1);
        assertEquals(71L, entity871.getTablleId(), "経路87詳細1");

        // 経路88(政治団体迂回1階層)
        SelectOptionDto dto88 = listOption.get(16);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute88 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto88.getValue()));
        assertEquals(3, listRoute88.size(), "経路88のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity880 = listRoute88.get(0);
        assertEquals(30L, entity880.getTablleId(), "経路88詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity881 = listRoute88.get(1);
        assertEquals(30_001L, entity881.getTablleId(), "経路88詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity882 = listRoute88.get(2);
        assertEquals(30_002L, entity882.getTablleId(), "経路88詳細2");
    }

}
