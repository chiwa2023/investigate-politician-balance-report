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
 * UkaiKenkinRouteByStage04BatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UkaiKenkinRouteByStage04BatchConfigurationTest {
    // CHECKSTYLE:OFF

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(UkaiKenkinRouteByStage04BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage04;

    /** 作成データ抽出選択肢作成Logic */
    @Autowired
    private CreateUkaiKenkinRouteSelectOptionLogic createUkaiKenkinRouteSelectOptionLogic;

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(UkaiKenkinRouteByStage04BatchConfiguration.JOB_NAME, ukaiKenkinRouteByStage04.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    // @Transactional
    @Sql({ "configuration_income_2022.sql", "configuration_poli_org_property.sql",
            "configuration_wktbl_meisai_clean.sql", "configuration_wktbl_route.sql" })
    void test() throws Exception {

        jobLauncherTestUtils.setJob(ukaiKenkinRouteByStage04);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        Integer userCode = privilegeDto.getLoginUserCode();

        JobParameters jobParameters = new JobParametersBuilder(
                ukaiKenkinRouteByStage04.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
                .addLocalDateTime("executeTime", LocalDateTime.now()).addLong("userId", privilegeDto.getLoginUserId())
                .addLong("userCode", Long.valueOf(String.valueOf(userCode))).addString("userName", "ユーザA")
                .addLong("houkokuNen", 2022L).addString("isSearchKoufukin", "false").addLong("poliOrgCode", 100L)
                .toJobParameters();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode(), "作業完了Statusが戻ってくる");

        // 全体を選択肢リスト形式で取得
        List<SelectOptionDto> listOption = createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
        assertEquals(12, listOption.size(), "12経路取得できた");

        /* 個人・企業・政治団体(階層0) */

        // 経路1
        SelectOptionDto dto00 = listOption.get(0);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute0 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto00.getValue()));
        assertEquals(1, listRoute0.size(), "経路1のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity00 = listRoute0.get(0);
        assertEquals(11L, entity00.getTablleId(), "経路1詳細1");

        // 経路2
        SelectOptionDto dto01 = listOption.get(1);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute1 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto01.getValue()));
        assertEquals(1, listRoute1.size(), "経路2のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity10 = listRoute1.get(0);
        assertEquals(12L, entity10.getTablleId(), "経路2詳細1");

        // 経路3
        SelectOptionDto dto02 = listOption.get(2);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute2 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto02.getValue()));
        assertEquals(1, listRoute2.size(), "経路3のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity20 = listRoute2.get(0);
        assertEquals(13L, entity20.getTablleId(), "経路3詳細1");

        // 経路4
        SelectOptionDto dto03 = listOption.get(3);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute3 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto03.getValue()));
        assertEquals(1, listRoute3.size(), "経路4のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity30 = listRoute3.get(0);
        assertEquals(14L, entity30.getTablleId(), "経路4詳細1");

        // 経路5
        SelectOptionDto dto04 = listOption.get(4);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute4 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto04.getValue()));
        assertEquals(1, listRoute4.size(), "経路5のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity40 = listRoute4.get(0);
        assertEquals(15L, entity40.getTablleId(), "経路5詳細1");

        // 経路6
        SelectOptionDto dto05 = listOption.get(5);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute5 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto05.getValue()));
        assertEquals(1, listRoute5.size(), "経路6のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity50 = listRoute5.get(0);
        assertEquals(16L, entity50.getTablleId(), "経路6詳細1");

        // 経路7
        SelectOptionDto dto06 = listOption.get(6);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute6 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto06.getValue()));
        assertEquals(1, listRoute6.size(), "経路7のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity60 = listRoute6.get(0);
        assertEquals(17L, entity60.getTablleId(), "経路7詳細1");

        // 経路8
        SelectOptionDto dto07 = listOption.get(7);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute7 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto07.getValue()));
        assertEquals(1, listRoute7.size(), "経路8のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity70 = listRoute7.get(0);
        assertEquals(18L, entity70.getTablleId(), "経路8詳細1");

        /* ここから政治団体(政治団体階層1以上) */

        // 経路9(政治団体1)
        SelectOptionDto dto08 = listOption.get(8);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute8 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto08.getValue()));
        assertEquals(2, listRoute8.size(), "経路9のデータ数は2");
        WkTblUkaiKenkinPickupRouteEntity entity80 = listRoute8.get(0);
        assertEquals(30L, entity80.getTablleId(), "経路9詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity81 = listRoute8.get(1);
        assertEquals(120L, entity81.getTablleId(), "経路9詳細2");

        // 経路10(政治団体2)
        SelectOptionDto dto09 = listOption.get(9);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute9 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto09.getValue()));
        assertEquals(3, listRoute9.size(), "経路10のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity90 = listRoute9.get(0);
        assertEquals(31L, entity90.getTablleId(), "経路10詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity91 = listRoute9.get(1);
        assertEquals(121L, entity91.getTablleId(), "経路10詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity92 = listRoute9.get(2);
        assertEquals(220L, entity92.getTablleId(), "経路10詳細3");

        // 経路11(政治団体3)
        SelectOptionDto dto10 = listOption.get(10);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute10 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto10.getValue()));
        assertEquals(4, listRoute10.size(), "経路11のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity100 = listRoute10.get(0);
        assertEquals(32L, entity100.getTablleId(), "経路11詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity101 = listRoute10.get(1);
        assertEquals(122L, entity101.getTablleId(), "経路11詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity102 = listRoute10.get(2);
        assertEquals(221L, entity102.getTablleId(), "経路11詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity103 = listRoute10.get(3);
        assertEquals(320L, entity103.getTablleId(), "経路11詳細4");

        // 経路12(政治団体4)
        SelectOptionDto dto11 = listOption.get(11);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute11 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto11.getValue()));
        assertEquals(5, listRoute11.size(), "経路12のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity110 = listRoute11.get(0);
        assertEquals(33L, entity110.getTablleId(), "経路12詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity111 = listRoute11.get(1);
        assertEquals(123L, entity111.getTablleId(), "経路12詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity112 = listRoute11.get(2);
        assertEquals(222L, entity112.getTablleId(), "経路12詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity113 = listRoute11.get(3);
        assertEquals(321L, entity113.getTablleId(), "経路12詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity114 = listRoute11.get(4);
        assertEquals(420L, entity114.getTablleId(), "経路12詳細5");

    }

}
