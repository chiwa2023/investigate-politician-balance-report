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
 * UkaiKenkinRouteByStage09BatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UkaiKenkinRouteByStage09BatchConfigurationTest {
    // CHECKSTYLE:OFF

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(UkaiKenkinRouteByStage09BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage09;

    /** 作成データ抽出選択肢作成Logic */
    @Autowired
    private CreateUkaiKenkinRouteSelectOptionLogic createUkaiKenkinRouteSelectOptionLogic;

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(UkaiKenkinRouteByStage09BatchConfiguration.JOB_NAME, ukaiKenkinRouteByStage09.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    @Sql({ "configuration_income_2022.sql", "configuration_poli_org_property.sql",
            "configuration_wktbl_meisai_clean.sql", "configuration_wktbl_route.sql" })
    void test() throws Exception { // NOPMD

        jobLauncherTestUtils.setJob(ukaiKenkinRouteByStage09);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        Integer userCode = privilegeDto.getLoginUserCode();

        JobParameters jobParameters = new JobParametersBuilder(
                ukaiKenkinRouteByStage09.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
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
        // assertEquals(19, listOption.size(), "19経路取得できた");

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
        assertEquals(2, listRoute02.size(), "経路1のデータ数は1");
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
        assertEquals(11L, entity041.getTablleId(), "経路3詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity042 = listRoute04.get(2);
        assertEquals(33_101L, entity042.getTablleId(), "経路4詳細2");

        // 経路5(1階層個人から政治団体関連者)
        SelectOptionDto dto05 = listOption.get(5);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute05 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto05.getValue()));
        assertEquals(3, listRoute05.size(), "経路3のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity050 = listRoute05.get(0);
        assertEquals(30L, entity050.getTablleId(), "経路5詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity051 = listRoute05.get(1);
        assertEquals(11L, entity051.getTablleId(), "経路5詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity052 = listRoute05.get(2);
        assertEquals(34101L, entity052.getTablleId(), "経路5詳細2");

        // 経路6(2階層個人から企業代表者)
        SelectOptionDto dto06 = listOption.get(6);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute06 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto06.getValue()));
        assertEquals(4, listRoute06.size(), "経路4のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity060 = listRoute06.get(0);
        assertEquals(31L, entity060.getTablleId(), "経路6詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity061 = listRoute06.get(1);
        assertEquals(11L, entity061.getTablleId(), "経路6詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity062 = listRoute06.get(2);
        assertEquals(121L, entity062.getTablleId(), "経路4詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity063 = listRoute06.get(3);
        assertEquals(33102L, entity063.getTablleId(), "経路6詳細3");

        // 経路7(2階層個人から政治団体関連者)
        SelectOptionDto dto07 = listOption.get(7);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute07 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto07.getValue()));
        assertEquals(4, listRoute07.size(), "経路7のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity070 = listRoute07.get(0);
        assertEquals(31L, entity070.getTablleId(), "経路7詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity071 = listRoute07.get(1);
        assertEquals(11L, entity071.getTablleId(), "経路7詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity072 = listRoute07.get(2);
        assertEquals(121L, entity072.getTablleId(), "経路7詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity073 = listRoute07.get(3);
        assertEquals(34102L, entity073.getTablleId(), "経路7詳細3");

        // 経路8(3階層個人から企業代表者)
        SelectOptionDto dto08 = listOption.get(8);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute08 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto08.getValue()));
        assertEquals(5, listRoute08.size(), "経路5のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity080 = listRoute08.get(0);
        assertEquals(32L, entity080.getTablleId(), "経路8詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity081 = listRoute08.get(1);
        assertEquals(11L, entity081.getTablleId(), "経路8詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity082 = listRoute08.get(2);
        assertEquals(122L, entity082.getTablleId(), "経路8詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity083 = listRoute08.get(3);
        assertEquals(221L, entity083.getTablleId(), "経路8詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity084 = listRoute08.get(4);
        assertEquals(33_103L, entity084.getTablleId(), "経路8詳細4");

        // 経路9(3階層個人から政治団体関連者)
        SelectOptionDto dto09 = listOption.get(9);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute09 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto09.getValue()));
        assertEquals(5, listRoute09.size(), "経路9のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity090 = listRoute09.get(0);
        assertEquals(32L, entity090.getTablleId(), "経路9詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity091 = listRoute05.get(1);
        assertEquals(11L, entity091.getTablleId(), "経路9詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity092 = listRoute09.get(2);
        assertEquals(122L, entity092.getTablleId(), "経路9詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity093 = listRoute09.get(3);
        assertEquals(221L, entity093.getTablleId(), "経路9詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity094 = listRoute09.get(4);
        assertEquals(34_103L, entity094.getTablleId(), "経路9詳細4");

        // 経路10(3階層個人から企業代表者)
        SelectOptionDto dto10 = listOption.get(10);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute10 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto10.getValue()));
        assertEquals(6, listRoute10.size(), "経路10のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity100 = listRoute10.get(0);
        assertEquals(33L, entity100.getTablleId(), "経路10詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity101 = listRoute10.get(1);
        assertEquals(11L, entity101.getTablleId(), "経路10詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity102 = listRoute10.get(2);
        assertEquals(123L, entity102.getTablleId(), "経路10詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity103 = listRoute10.get(3);
        assertEquals(222L, entity103.getTablleId(), "経路10詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity104 = listRoute10.get(4);
        assertEquals(321L, entity104.getTablleId(), "経路10詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity105 = listRoute10.get(5);
        assertEquals(33_104L, entity105.getTablleId(), "経路10詳細5");

        // 経路11(4階層個人から政治団体関連者)
        SelectOptionDto dto11 = listOption.get(11);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute11 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto11.getValue()));
        assertEquals(6, listRoute11.size(), "経路11のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity110 = listRoute11.get(0);
        assertEquals(33L, entity110.getTablleId(), "経路11詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity111 = listRoute11.get(1);
        assertEquals(11L, entity111.getTablleId(), "経路11詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity112 = listRoute11.get(2);
        assertEquals(123L, entity112.getTablleId(), "経路11詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity113 = listRoute11.get(3);
        assertEquals(222L, entity113.getTablleId(), "経路11詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity114 = listRoute11.get(4);
        assertEquals(321L, entity114.getTablleId(), "経路11詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity115 = listRoute11.get(5);
        assertEquals(34_104L, entity115.getTablleId(), "経路11詳細5");

        // 経路12(3階層個人から企業代表者)
        SelectOptionDto dto12 = listOption.get(12);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute12 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto12.getValue()));
        assertEquals(7, listRoute12.size(), "経路12のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity120 = listRoute12.get(0);
        assertEquals(34L, entity120.getTablleId(), "経路12詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity121 = listRoute12.get(1);
        assertEquals(11L, entity121.getTablleId(), "経路12詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity122 = listRoute12.get(2);
        assertEquals(124L, entity122.getTablleId(), "経路12詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity123 = listRoute12.get(3);
        assertEquals(224L, entity123.getTablleId(), "経路12詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity124 = listRoute12.get(4);
        assertEquals(324L, entity124.getTablleId(), "経路12詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity125 = listRoute12.get(5);
        assertEquals(424L, entity125.getTablleId(), "経路12詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity126 = listRoute12.get(6);
        assertEquals(33_105L, entity126.getTablleId(), "経路12詳細6");

        SelectOptionDto dto13 = listOption.get(13);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute13 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto13.getValue()));
        assertEquals(7, listRoute13.size(), "経路13のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity130 = listRoute13.get(0);
        assertEquals(34L, entity130.getTablleId(), "経路13詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity131 = listRoute13.get(1);
        assertEquals(11L, entity131.getTablleId(), "経路13詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity132 = listRoute13.get(2);
        assertEquals(124L, entity132.getTablleId(), "経路13詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity133 = listRoute13.get(3);
        assertEquals(224L, entity133.getTablleId(), "経路13詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity134 = listRoute13.get(4);
        assertEquals(324L, entity134.getTablleId(), "経路13詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity135 = listRoute13.get(5);
        assertEquals(424L, entity135.getTablleId(), "経路13詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity136 = listRoute13.get(6);
        assertEquals(34_105L, entity136.getTablleId(), "経路13詳細6");

        // 経路14(6階層個人から企業代表者)
        SelectOptionDto dto14 = listOption.get(14);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute14 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto14.getValue()));
        assertEquals(8, listRoute14.size(), "経路6のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity140 = listRoute14.get(0);
        assertEquals(35L, entity140.getTablleId(), "経路8詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity141 = listRoute14.get(1);
        assertEquals(11L, entity141.getTablleId(), "経路8詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity142 = listRoute14.get(2);
        assertEquals(125L, entity142.getTablleId(), "経路8詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity143 = listRoute14.get(3);
        assertEquals(225L, entity143.getTablleId(), "経86詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity144 = listRoute14.get(4);
        assertEquals(325L, entity144.getTablleId(), "経路8詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity145 = listRoute14.get(5);
        assertEquals(425L, entity145.getTablleId(), "経路8詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity146 = listRoute14.get(6);
        assertEquals(525L, entity146.getTablleId(), "経路8詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity147 = listRoute14.get(7);
        assertEquals(33_106L, entity147.getTablleId(), "経路8詳細7");

        // 経路15(6階層個人から政治団体関連者)
        SelectOptionDto dto15 = listOption.get(15);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute15 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto15.getValue()));
        assertEquals(8, listRoute15.size(), "経路6のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity150 = listRoute15.get(0);
        assertEquals(35L, entity150.getTablleId(), "経路8詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity151 = listRoute15.get(1);
        assertEquals(11L, entity151.getTablleId(), "経路8詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity152 = listRoute15.get(2);
        assertEquals(125L, entity152.getTablleId(), "経路8詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity153 = listRoute15.get(3);
        assertEquals(225L, entity153.getTablleId(), "経86詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity154 = listRoute15.get(4);
        assertEquals(325L, entity154.getTablleId(), "経路8詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity155 = listRoute15.get(5);
        assertEquals(425L, entity155.getTablleId(), "経路8詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity156 = listRoute15.get(6);
        assertEquals(525L, entity156.getTablleId(), "経路8詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity157 = listRoute15.get(7);
        assertEquals(34_106L, entity157.getTablleId(), "経路8詳細7");

        // 経路16(7階層個人から企業代表者)
        SelectOptionDto dto16 = listOption.get(16);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute16 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto16.getValue()));
        assertEquals(9, listRoute16.size(), "経路9のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity160 = listRoute16.get(0);
        assertEquals(36L, entity160.getTablleId(), "経路9詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity161 = listRoute16.get(1);
        assertEquals(11L, entity161.getTablleId(), "経路9詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity162 = listRoute16.get(2);
        assertEquals(126L, entity162.getTablleId(), "経路9詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity163 = listRoute16.get(3);
        assertEquals(226L, entity163.getTablleId(), "経路9詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity164 = listRoute16.get(4);
        assertEquals(326L, entity164.getTablleId(), "経路9詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity165 = listRoute16.get(5);
        assertEquals(426L, entity165.getTablleId(), "経路9詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity166 = listRoute16.get(6);
        assertEquals(526L, entity166.getTablleId(), "経路9詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity167 = listRoute16.get(7);
        assertEquals(626L, entity167.getTablleId(), "経路9詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity168 = listRoute16.get(8);
        assertEquals(33_107L, entity168.getTablleId(), "経路9詳細8");

        // 経路17(7階層個人から政治団体関連者)
        SelectOptionDto dto17 = listOption.get(17);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute17 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto17.getValue()));
        assertEquals(9, listRoute17.size(), "経路9のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity170 = listRoute17.get(0);
        assertEquals(36L, entity170.getTablleId(), "経路9詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity171 = listRoute17.get(1);
        assertEquals(11L, entity171.getTablleId(), "経路9詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity172 = listRoute17.get(2);
        assertEquals(126L, entity172.getTablleId(), "経路9詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity173 = listRoute17.get(3);
        assertEquals(226L, entity173.getTablleId(), "経路9詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity174 = listRoute17.get(4);
        assertEquals(326L, entity174.getTablleId(), "経路9詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity175 = listRoute17.get(5);
        assertEquals(426L, entity175.getTablleId(), "経路9詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity176 = listRoute17.get(6);
        assertEquals(526L, entity176.getTablleId(), "経路9詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity177 = listRoute17.get(7);
        assertEquals(626L, entity177.getTablleId(), "経路9詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity178 = listRoute17.get(8);
        assertEquals(34_107L, entity178.getTablleId(), "経路9詳細8");

        // 経路18(8階層個人から企業代表者)
        SelectOptionDto dto18 = listOption.get(18);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute18 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto18.getValue()));
        assertEquals(10, listRoute18.size(), "経路18のデータ数は18");
        WkTblUkaiKenkinPickupRouteEntity entity180 = listRoute18.get(0);
        assertEquals(37L, entity180.getTablleId(), "経路18詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity181 = listRoute18.get(1);
        assertEquals(11L, entity181.getTablleId(), "経路18詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity182 = listRoute18.get(2);
        assertEquals(127L, entity182.getTablleId(), "経路18詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity183 = listRoute18.get(3);
        assertEquals(227L, entity183.getTablleId(), "経路18詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity184 = listRoute18.get(4);
        assertEquals(327L, entity184.getTablleId(), "経路18詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity185 = listRoute18.get(5);
        assertEquals(427L, entity185.getTablleId(), "経路18詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity186 = listRoute18.get(6);
        assertEquals(527L, entity186.getTablleId(), "経路18詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity187 = listRoute18.get(7);
        assertEquals(627L, entity187.getTablleId(), "経路18詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity188 = listRoute18.get(8);
        assertEquals(727L, entity188.getTablleId(), "経路18詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity189 = listRoute18.get(9);
        assertEquals(33_108L, entity189.getTablleId(), "経路18詳細9");

        // 経路19(8階層個人から政治団体関連者)
        SelectOptionDto dto19 = listOption.get(19);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute19 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto19.getValue()));
        assertEquals(10, listRoute19.size(), "経路19のデータ数は10");
        WkTblUkaiKenkinPickupRouteEntity entity190 = listRoute19.get(0);
        assertEquals(37L, entity190.getTablleId(), "経路19詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity191 = listRoute19.get(1);
        assertEquals(11L, entity191.getTablleId(), "経路19詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity192 = listRoute19.get(2);
        assertEquals(127L, entity192.getTablleId(), "経路19詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity193 = listRoute19.get(3);
        assertEquals(227L, entity193.getTablleId(), "経路19詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity194 = listRoute19.get(4);
        assertEquals(327L, entity194.getTablleId(), "経路19詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity195 = listRoute19.get(5);
        assertEquals(427L, entity195.getTablleId(), "経路19詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity196 = listRoute19.get(6);
        assertEquals(527L, entity196.getTablleId(), "経路19詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity197 = listRoute19.get(7);
        assertEquals(627L, entity197.getTablleId(), "経路19詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity198 = listRoute19.get(8);
        assertEquals(727L, entity198.getTablleId(), "経路19詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity199 = listRoute19.get(9);
        assertEquals(34_108L, entity199.getTablleId(), "経路19詳細9");

        // 経路20(9階層個人から企業代表者)
        SelectOptionDto dto20 = listOption.get(20);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute20 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto20.getValue()));
        assertEquals(11, listRoute20.size(), "経路6のデータ数は11");
        WkTblUkaiKenkinPickupRouteEntity entity200 = listRoute20.get(0);
        assertEquals(38L, entity200.getTablleId(), "経路20細0");
        WkTblUkaiKenkinPickupRouteEntity entity201 = listRoute20.get(1);
        assertEquals(11L, entity201.getTablleId(), "経路20詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity202 = listRoute20.get(2);
        assertEquals(128L, entity202.getTablleId(), "経路20詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity203 = listRoute20.get(3);
        assertEquals(228L, entity203.getTablleId(), "経路20詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity204 = listRoute20.get(4);
        assertEquals(328L, entity204.getTablleId(), "経路20詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity205 = listRoute20.get(5);
        assertEquals(428L, entity205.getTablleId(), "経路20詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity206 = listRoute20.get(6);
        assertEquals(528L, entity206.getTablleId(), "経路20詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity207 = listRoute20.get(7);
        assertEquals(628L, entity207.getTablleId(), "経路20詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity208 = listRoute20.get(8);
        assertEquals(728L, entity208.getTablleId(), "経路20詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity209 = listRoute20.get(9);
        assertEquals(828L, entity209.getTablleId(), "経路20詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity20A = listRoute20.get(10);
        assertEquals(33_109L, entity20A.getTablleId(), "経路20詳細10");

        // 経路21(9階層個人から政治団体関連者)
        SelectOptionDto dto21 = listOption.get(21);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute21 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto21.getValue()));
        assertEquals(11, listRoute21.size(), "経路6のデータ数は11");
        WkTblUkaiKenkinPickupRouteEntity entity210 = listRoute21.get(0);
        assertEquals(38L, entity210.getTablleId(), "経路21細0");
        WkTblUkaiKenkinPickupRouteEntity entity211 = listRoute21.get(1);
        assertEquals(11L, entity211.getTablleId(), "経路21詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity212 = listRoute21.get(2);
        assertEquals(128L, entity212.getTablleId(), "経路21詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity213 = listRoute21.get(3);
        assertEquals(228L, entity213.getTablleId(), "経路21詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity214 = listRoute21.get(4);
        assertEquals(328L, entity214.getTablleId(), "経路21詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity215 = listRoute21.get(5);
        assertEquals(428L, entity215.getTablleId(), "経路21詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity216 = listRoute21.get(6);
        assertEquals(528L, entity216.getTablleId(), "経路21詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity217 = listRoute21.get(7);
        assertEquals(628L, entity217.getTablleId(), "経路21詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity218 = listRoute21.get(8);
        assertEquals(728L, entity218.getTablleId(), "経路21詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity219 = listRoute21.get(9);
        assertEquals(828L, entity219.getTablleId(), "経路21詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity21A = listRoute21.get(10);
        assertEquals(34_109L, entity21A.getTablleId(), "経路21詳細10");

        /* 個人迂回階層 */
        // 経路22(個人迂回1階層)
        SelectOptionDto dto22 = listOption.get(22);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute22 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto22.getValue()));
        assertEquals(4, listRoute22.size(), "経路3のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity220 = listRoute22.get(0);
        assertEquals(11_001L, entity220.getTablleId(), "経路3詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity221 = listRoute22.get(1);
        assertEquals(11_000L, entity221.getTablleId(), "経路3詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity222 = listRoute22.get(2);
        assertEquals(11_000L, entity222.getTablleId(), "経路3詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity223 = listRoute22.get(3);
        assertEquals(11_002L, entity223.getTablleId(), "経路3詳細3");

        // 経路23(個人迂回2階層)
        SelectOptionDto dto23 = listOption.get(23);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute23 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto23.getValue()));
        assertEquals(5, listRoute23.size(), "経路4のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity230 = listRoute23.get(0);
        assertEquals(31L, entity230.getTablleId(), "経路4詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity231 = listRoute23.get(1);
        assertEquals(11_003L, entity231.getTablleId(), "経路4詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity232 = listRoute23.get(2);
        assertEquals(11_003L, entity232.getTablleId(), "経路4詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity233 = listRoute23.get(3);
        assertEquals(121L, entity233.getTablleId(), "経路4詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity234 = listRoute23.get(4);
        assertEquals(11_004L, entity234.getTablleId(), "経路4詳細3");

        // 経路24(個人迂回3階層)
        SelectOptionDto dto24 = listOption.get(24);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute24 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto24.getValue()));
        assertEquals(6, listRoute24.size(), "経路5のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity240 = listRoute24.get(0);
        assertEquals(32L, entity240.getTablleId(), "経路5詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity241 = listRoute24.get(1);
        assertEquals(11_005L, entity241.getTablleId(), "経路5詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity242 = listRoute24.get(2);
        assertEquals(11_005L, entity242.getTablleId(), "経路5詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity243 = listRoute24.get(3);
        assertEquals(122L, entity243.getTablleId(), "経路5詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity244 = listRoute24.get(4);
        assertEquals(221L, entity244.getTablleId(), "経路5詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity245 = listRoute24.get(5);
        assertEquals(11_006L, entity245.getTablleId(), "経路5詳細4");

        // 経路25(個人迂回4階層)
        SelectOptionDto dto25 = listOption.get(25);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute25 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto25.getValue()));
        assertEquals(7, listRoute25.size(), "経路6のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity250 = listRoute25.get(0);
        assertEquals(33L, entity250.getTablleId(), "経路6詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity251 = listRoute25.get(1);
        assertEquals(11_007L, entity251.getTablleId(), "経路6詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity252 = listRoute25.get(2);
        assertEquals(11_007L, entity252.getTablleId(), "経路6詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity253 = listRoute25.get(3);
        assertEquals(123L, entity253.getTablleId(), "経路6詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity254 = listRoute25.get(4);
        assertEquals(222L, entity254.getTablleId(), "経路6詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity255 = listRoute25.get(5);
        assertEquals(321L, entity255.getTablleId(), "経路6詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity256 = listRoute25.get(6);
        assertEquals(11_008L, entity256.getTablleId(), "経路6詳細5");

        // 経路26(個人迂回5階層)
        SelectOptionDto dto26 = listOption.get(26);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute26 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto26.getValue()));
        assertEquals(8, listRoute26.size(), "経路7のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity260 = listRoute26.get(0);
        assertEquals(34L, entity260.getTablleId(), "経路7詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity261 = listRoute26.get(1);
        assertEquals(11_009L, entity261.getTablleId(), "経路7詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity262 = listRoute26.get(2);
        assertEquals(11_009L, entity262.getTablleId(), "経路7詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity263 = listRoute26.get(3);
        assertEquals(124L, entity263.getTablleId(), "経路7詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity264 = listRoute26.get(4);
        assertEquals(224L, entity264.getTablleId(), "経路7詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity265 = listRoute26.get(5);
        assertEquals(324L, entity265.getTablleId(), "経路7詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity266 = listRoute26.get(6);
        assertEquals(424L, entity266.getTablleId(), "経路7詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity267 = listRoute26.get(7);
        assertEquals(11_010L, entity267.getTablleId(), "経路7詳細6");

        // 経路27(個人迂回6階層)
        SelectOptionDto dto27 = listOption.get(27);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute27 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto27.getValue()));
        assertEquals(9, listRoute27.size(), "経路6のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity270 = listRoute27.get(0);
        assertEquals(35L, entity270.getTablleId(), "経路8詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity271 = listRoute27.get(1);
        assertEquals(11_011L, entity271.getTablleId(), "経路8詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity272 = listRoute27.get(2);
        assertEquals(11_011L, entity272.getTablleId(), "経路8詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity273 = listRoute27.get(3);
        assertEquals(125L, entity273.getTablleId(), "経路8詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity274 = listRoute27.get(4);
        assertEquals(225L, entity274.getTablleId(), "経86詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity275 = listRoute27.get(5);
        assertEquals(325L, entity275.getTablleId(), "経路8詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity276 = listRoute27.get(6);
        assertEquals(425L, entity276.getTablleId(), "経路8詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity277 = listRoute27.get(7);
        assertEquals(525L, entity277.getTablleId(), "経路8詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity278 = listRoute27.get(8);
        assertEquals(11_012L, entity278.getTablleId(), "経路8詳細7");

        // 経路28(個人迂回7階層)
        SelectOptionDto dto28 = listOption.get(28);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute28 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto28.getValue()));
        assertEquals(10, listRoute28.size(), "経路9のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity280 = listRoute28.get(0);
        assertEquals(36L, entity280.getTablleId(), "経路9詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity281 = listRoute28.get(1);
        assertEquals(11_013L, entity281.getTablleId(), "経路9詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity282 = listRoute28.get(2);
        assertEquals(11_013L, entity282.getTablleId(), "経路9詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity283 = listRoute28.get(3);
        assertEquals(126L, entity283.getTablleId(), "経路9詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity284 = listRoute28.get(4);
        assertEquals(226L, entity284.getTablleId(), "経路9詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity285 = listRoute28.get(5);
        assertEquals(326L, entity285.getTablleId(), "経路9詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity286 = listRoute28.get(6);
        assertEquals(426L, entity286.getTablleId(), "経路9詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity287 = listRoute28.get(7);
        assertEquals(526L, entity287.getTablleId(), "経路9詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity288 = listRoute28.get(8);
        assertEquals(626L, entity288.getTablleId(), "経路9詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity289 = listRoute28.get(9);
        assertEquals(11_014L, entity289.getTablleId(), "経路9詳細8");

        // 経路29(個人迂回8階層)
        SelectOptionDto dto29 = listOption.get(29);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute29 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto29.getValue()));
        assertEquals(11, listRoute29.size(), "経路29のデータ数は29");
        WkTblUkaiKenkinPickupRouteEntity entity290 = listRoute29.get(0);
        assertEquals(37L, entity290.getTablleId(), "経路29詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity291 = listRoute29.get(1);
        assertEquals(11_015L, entity291.getTablleId(), "経路29詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity292 = listRoute29.get(2);
        assertEquals(11_015L, entity292.getTablleId(), "経路29詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity293 = listRoute29.get(3);
        assertEquals(127L, entity293.getTablleId(), "経路29詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity294 = listRoute29.get(4);
        assertEquals(227L, entity294.getTablleId(), "経路29詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity295 = listRoute29.get(5);
        assertEquals(327L, entity295.getTablleId(), "経路29詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity296 = listRoute29.get(6);
        assertEquals(427L, entity296.getTablleId(), "経路29詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity297 = listRoute29.get(7);
        assertEquals(527L, entity297.getTablleId(), "経路29詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity298 = listRoute29.get(8);
        assertEquals(627L, entity298.getTablleId(), "経路29詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity299 = listRoute29.get(9);
        assertEquals(727L, entity299.getTablleId(), "経路29詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity29A = listRoute29.get(10);
        assertEquals(11_016L, entity29A.getTablleId(), "経路29詳細9");

        // 経路30(個人迂回9階層)
        SelectOptionDto dto30 = listOption.get(30);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute30 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto30.getValue()));
        assertEquals(12, listRoute30.size(), "経路6のデータ数は30");
        WkTblUkaiKenkinPickupRouteEntity entity300 = listRoute30.get(0);
        assertEquals(38L, entity300.getTablleId(), "経路30細0");
        WkTblUkaiKenkinPickupRouteEntity entity301 = listRoute30.get(1);
        assertEquals(11_017L, entity301.getTablleId(), "経路30詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity302 = listRoute30.get(2);
        assertEquals(11_017L, entity302.getTablleId(), "経路30詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity303 = listRoute30.get(3);
        assertEquals(128L, entity303.getTablleId(), "経路30詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity304 = listRoute30.get(4);
        assertEquals(228L, entity304.getTablleId(), "経路30詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity305 = listRoute30.get(5);
        assertEquals(328L, entity305.getTablleId(), "経路30詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity306 = listRoute30.get(6);
        assertEquals(428L, entity306.getTablleId(), "経路30詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity307 = listRoute30.get(7);
        assertEquals(528L, entity307.getTablleId(), "経路30詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity308 = listRoute30.get(8);
        assertEquals(628L, entity308.getTablleId(), "経路30詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity309 = listRoute30.get(9);
        assertEquals(728L, entity309.getTablleId(), "経路30詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity30A = listRoute30.get(10);
        assertEquals(828L, entity30A.getTablleId(), "経路30詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity30B = listRoute30.get(11);
        assertEquals(11_018L, entity30B.getTablleId(), "経路30詳細10");

//        /* 企業迂回階層 */
//        // 経路12(企業迂回1階層)
//        SelectOptionDto dto12 = listOption.get(12);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute12 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto12.getValue()));
//        assertEquals(3, listRoute12.size(), "経路12のデータ数は3");
//        WkTblUkaiKenkinPickupRouteEntity entity120 = listRoute12.get(0);
//        assertEquals(12_001L, entity120.getTablleId(), "経路12詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity121 = listRoute12.get(1);
//        assertEquals(12_000L, entity121.getTablleId(), "経路12詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity122 = listRoute12.get(2);
//        assertEquals(12_002L, entity122.getTablleId(), "経路12詳細2");
//
//        // 経路13(企業迂回2階層)
//        SelectOptionDto dto13 = listOption.get(13);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute13 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto13.getValue()));
//        assertEquals(4, listRoute13.size(), "経路4のデータ数は4");
//        WkTblUkaiKenkinPickupRouteEntity entity130 = listRoute13.get(0);
//        assertEquals(31L, entity130.getTablleId(), "経路13詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity131 = listRoute13.get(1);
//        assertEquals(12_003L, entity131.getTablleId(), "経路13詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity132 = listRoute13.get(2);
//        assertEquals(121L, entity132.getTablleId(), "経路13詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity133 = listRoute13.get(3);
//        assertEquals(12_004L, entity133.getTablleId(), "経路13詳細3");
//
//        // 経路14(企業迂回3階層)
//        SelectOptionDto dto14 = listOption.get(14);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute14 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto14.getValue()));
//        assertEquals(5, listRoute14.size(), "経路14のデータ数は5");
//        WkTblUkaiKenkinPickupRouteEntity entity140 = listRoute14.get(0);
//        assertEquals(32L, entity140.getTablleId(), "経路14詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity141 = listRoute14.get(1);
//        assertEquals(12_005L, entity141.getTablleId(), "経路14詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity142 = listRoute14.get(2);
//        assertEquals(122L, entity142.getTablleId(), "経路14詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity143 = listRoute14.get(3);
//        assertEquals(221L, entity143.getTablleId(), "経路14詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity144 = listRoute14.get(4);
//        assertEquals(12_006L, entity144.getTablleId(), "経路14詳細4");
//
//        // 経路15(企業迂回4階層)
//        SelectOptionDto dto15 = listOption.get(15);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute15 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto15.getValue()));
//        assertEquals(6, listRoute15.size(), "経路15のデータ数は6");
//        WkTblUkaiKenkinPickupRouteEntity entity150 = listRoute15.get(0);
//        assertEquals(33L, entity150.getTablleId(), "経路15詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity151 = listRoute15.get(1);
//        assertEquals(12_007L, entity151.getTablleId(), "経路15詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity152 = listRoute15.get(2);
//        assertEquals(123L, entity152.getTablleId(), "経路15詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity153 = listRoute15.get(3);
//        assertEquals(222L, entity153.getTablleId(), "経路15詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity154 = listRoute15.get(4);
//        assertEquals(321L, entity154.getTablleId(), "経路15詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity155 = listRoute15.get(5);
//        assertEquals(12_008L, entity155.getTablleId(), "経路15詳細5");
//
//        // 経路16(企業迂回5階層)
//        SelectOptionDto dto16 = listOption.get(16);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute16 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto16.getValue()));
//        assertEquals(7, listRoute16.size(), "経路16のデータ数は7");
//        WkTblUkaiKenkinPickupRouteEntity entity160 = listRoute16.get(0);
//        assertEquals(34L, entity160.getTablleId(), "経路16詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity161 = listRoute16.get(1);
//        assertEquals(12_009L, entity161.getTablleId(), "経路16詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity162 = listRoute16.get(2);
//        assertEquals(124L, entity162.getTablleId(), "経路16詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity163 = listRoute16.get(3);
//        assertEquals(224L, entity163.getTablleId(), "経路16詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity164 = listRoute16.get(4);
//        assertEquals(324L, entity164.getTablleId(), "経路16詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity165 = listRoute16.get(5);
//        assertEquals(424L, entity165.getTablleId(), "経路16詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity166 = listRoute16.get(6);
//        assertEquals(12_010L, entity166.getTablleId(), "経路16詳細6");
//
//        // 経路17(企業迂回6階層)
//        SelectOptionDto dto17 = listOption.get(17);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute17 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto17.getValue()));
//        assertEquals(8, listRoute17.size(), "経路17のデータ数は8");
//        WkTblUkaiKenkinPickupRouteEntity entity170 = listRoute17.get(0);
//        assertEquals(35L, entity170.getTablleId(), "経路8詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity171 = listRoute17.get(1);
//        assertEquals(12_011L, entity171.getTablleId(), "経路17詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity172 = listRoute17.get(2);
//        assertEquals(125L, entity172.getTablleId(), "経路17詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity173 = listRoute17.get(3);
//        assertEquals(225L, entity173.getTablleId(), "経17詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity174 = listRoute17.get(4);
//        assertEquals(325L, entity174.getTablleId(), "経路17詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity175 = listRoute17.get(5);
//        assertEquals(425L, entity175.getTablleId(), "経路17詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity176 = listRoute17.get(6);
//        assertEquals(525L, entity176.getTablleId(), "経路17詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity177 = listRoute17.get(7);
//        assertEquals(12_012L, entity177.getTablleId(), "経路17詳細7");
//
//        // 経路18(企業迂回7階層)
//        SelectOptionDto dto18 = listOption.get(18);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute18 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto18.getValue()));
//        assertEquals(9, listRoute18.size(), "経路9のデータ数は9");
//        WkTblUkaiKenkinPickupRouteEntity entity180 = listRoute18.get(0);
//        assertEquals(36L, entity180.getTablleId(), "経路18詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity181 = listRoute18.get(1);
//        assertEquals(12_013L, entity181.getTablleId(), "経路18詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity182 = listRoute18.get(2);
//        assertEquals(126L, entity182.getTablleId(), "経路18詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity183 = listRoute18.get(3);
//        assertEquals(226L, entity183.getTablleId(), "経路18詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity184 = listRoute18.get(4);
//        assertEquals(326L, entity184.getTablleId(), "経路18詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity185 = listRoute18.get(5);
//        assertEquals(426L, entity185.getTablleId(), "経路18詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity186 = listRoute18.get(6);
//        assertEquals(526L, entity186.getTablleId(), "経路18詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity187 = listRoute18.get(7);
//        assertEquals(626L, entity187.getTablleId(), "経路18詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity188 = listRoute18.get(8);
//        assertEquals(12_014L, entity188.getTablleId(), "経路18詳細8");
//
//        // 経路19(企業迂回8階層)
//        SelectOptionDto dto19 = listOption.get(19);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute19 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto19.getValue()));
//        assertEquals(10, listRoute19.size(), "経路19のデータ数は10");
//        WkTblUkaiKenkinPickupRouteEntity entity190 = listRoute19.get(0);
//        assertEquals(37L, entity190.getTablleId(), "経路19詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity191 = listRoute19.get(1);
//        assertEquals(12_015L, entity191.getTablleId(), "経路19詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity192 = listRoute19.get(2);
//        assertEquals(127L, entity192.getTablleId(), "経路19詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity193 = listRoute19.get(3);
//        assertEquals(227L, entity193.getTablleId(), "経路19詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity194 = listRoute19.get(4);
//        assertEquals(327L, entity194.getTablleId(), "経路19詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity195 = listRoute19.get(5);
//        assertEquals(427L, entity195.getTablleId(), "経路19詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity196 = listRoute19.get(6);
//        assertEquals(527L, entity196.getTablleId(), "経路19詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity197 = listRoute19.get(7);
//        assertEquals(627L, entity197.getTablleId(), "経路19詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity198 = listRoute19.get(8);
//        assertEquals(727L, entity198.getTablleId(), "経路19詳細8");
//        WkTblUkaiKenkinPickupRouteEntity entity199 = listRoute19.get(9);
//        assertEquals(12_016L, entity199.getTablleId(), "経路19詳細9");
//
//        // 経路20(企業迂回9階層)
//        SelectOptionDto dto20 = listOption.get(20);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute20 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto20.getValue()));
//        assertEquals(11, listRoute20.size(), "経路20のデータ数は11");
//        WkTblUkaiKenkinPickupRouteEntity entity200 = listRoute20.get(0);
//        assertEquals(38L, entity200.getTablleId(), "経路20細0");
//        WkTblUkaiKenkinPickupRouteEntity entity201 = listRoute20.get(1);
//        assertEquals(12_017L, entity201.getTablleId(), "経路20詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity202 = listRoute20.get(2);
//        assertEquals(128L, entity202.getTablleId(), "経路20詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity203 = listRoute20.get(3);
//        assertEquals(228L, entity203.getTablleId(), "経路20詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity204 = listRoute20.get(4);
//        assertEquals(328L, entity204.getTablleId(), "経路11詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity205 = listRoute20.get(5);
//        assertEquals(428L, entity205.getTablleId(), "経路20詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity206 = listRoute20.get(6);
//        assertEquals(528L, entity206.getTablleId(), "経路20詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity207 = listRoute20.get(7);
//        assertEquals(628L, entity207.getTablleId(), "経路20詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity208 = listRoute20.get(8);
//        assertEquals(728L, entity208.getTablleId(), "経路20詳細8");
//        WkTblUkaiKenkinPickupRouteEntity entity209 = listRoute20.get(9);
//        assertEquals(828L, entity209.getTablleId(), "経路20詳細9");
//        WkTblUkaiKenkinPickupRouteEntity entity20A = listRoute20.get(10);
//        assertEquals(12_018L, entity20A.getTablleId(), "経路20詳細10");
//        
//        
//        /* 政治団体迂回階層 */
//        // 経路21(政治団体迂回1階層)
//        SelectOptionDto dto21 = listOption.get(21);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute21 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto21.getValue()));
//        assertEquals(3, listRoute21.size(), "経路21のデータ数は3");
//        WkTblUkaiKenkinPickupRouteEntity entity210 = listRoute21.get(0);
//        assertEquals(30L, entity210.getTablleId(), "経路21詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity211 = listRoute21.get(1);
//        assertEquals(51L, entity211.getTablleId(), "経路21詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity212 = listRoute21.get(2);
//        assertEquals(120L, entity212.getTablleId(), "経路21詳細2");
//
//        // 経路22(政治団体迂回2階層)
//        SelectOptionDto dto22 = listOption.get(22);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute22 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto22.getValue()));
//        assertEquals(4, listRoute22.size(), "経路4のデータ数は4");
//        WkTblUkaiKenkinPickupRouteEntity entity220 = listRoute22.get(0);
//        assertEquals(31L, entity220.getTablleId(), "経路22詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity221 = listRoute22.get(1);
//        assertEquals(52L, entity221.getTablleId(), "経路22詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity222 = listRoute22.get(2);
//        assertEquals(121L, entity222.getTablleId(), "経路22詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity223 = listRoute22.get(3);
//        assertEquals(220L, entity223.getTablleId(), "経路22詳細3");
//
//        // 経路23(政治団体迂回3階層)
//        SelectOptionDto dto23 = listOption.get(23);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute23 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto23.getValue()));
//        assertEquals(5, listRoute23.size(), "経路23のデータ数は5");
//        WkTblUkaiKenkinPickupRouteEntity entity230 = listRoute23.get(0);
//        assertEquals(32L, entity230.getTablleId(), "経路23詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity231 = listRoute23.get(1);
//        assertEquals(53L, entity231.getTablleId(), "経路23詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity232 = listRoute23.get(2);
//        assertEquals(122L, entity232.getTablleId(), "経路14詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity233 = listRoute23.get(3);
//        assertEquals(221L, entity233.getTablleId(), "経路23詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity234 = listRoute23.get(4);
//        assertEquals(320L, entity234.getTablleId(), "経路23詳細4");
//
//        // 経路24(政治団体迂回4階層)
//        SelectOptionDto dto24 = listOption.get(24);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute24 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto24.getValue()));
//        assertEquals(6, listRoute24.size(), "経路24のデータ数は6");
//        WkTblUkaiKenkinPickupRouteEntity entity240 = listRoute24.get(0);
//        assertEquals(33L, entity240.getTablleId(), "経路24詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity241 = listRoute24.get(1);
//        assertEquals(54L, entity241.getTablleId(), "経路24詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity242 = listRoute24.get(2);
//        assertEquals(123L, entity242.getTablleId(), "経路24詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity243 = listRoute24.get(3);
//        assertEquals(222L, entity243.getTablleId(), "経路24詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity244 = listRoute24.get(4);
//        assertEquals(321L, entity244.getTablleId(), "経路24詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity245 = listRoute24.get(5);
//        assertEquals(420L, entity245.getTablleId(), "経路24詳細5");
//
//        // 経路25(政治団体迂回5階層)
//        SelectOptionDto dto25 = listOption.get(25);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute25 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto25.getValue()));
//        assertEquals(7, listRoute25.size(), "経路25のデータ数は7");
//        WkTblUkaiKenkinPickupRouteEntity entity250 = listRoute25.get(0);
//        assertEquals(34L, entity250.getTablleId(), "経路16詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity251 = listRoute25.get(1);
//        assertEquals(55L, entity251.getTablleId(), "経路25詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity252 = listRoute25.get(2);
//        assertEquals(124L, entity252.getTablleId(), "経路25詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity253 = listRoute25.get(3);
//        assertEquals(224L, entity253.getTablleId(), "経路25詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity254 = listRoute25.get(4);
//        assertEquals(324L, entity254.getTablleId(), "経路25詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity255 = listRoute25.get(5);
//        assertEquals(424L, entity255.getTablleId(), "経路25詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity256 = listRoute25.get(6);
//        assertEquals(520L, entity256.getTablleId(), "経路25詳細6");
//
//        // 経路26(政治団体迂回6階層)
//        SelectOptionDto dto26 = listOption.get(26);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute26 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto26.getValue()));
//        assertEquals(8, listRoute26.size(), "経路17のデータ数は8");
//        WkTblUkaiKenkinPickupRouteEntity entity260 = listRoute26.get(0);
//        assertEquals(35L, entity260.getTablleId(), "経路26詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity261 = listRoute26.get(1);
//        assertEquals(56L, entity261.getTablleId(), "経路26詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity262 = listRoute26.get(2);
//        assertEquals(125L, entity262.getTablleId(), "経路26詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity263 = listRoute26.get(3);
//        assertEquals(225L, entity263.getTablleId(), "経26詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity264 = listRoute26.get(4);
//        assertEquals(325L, entity264.getTablleId(), "経路26詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity265 = listRoute26.get(5);
//        assertEquals(425L, entity265.getTablleId(), "経路26詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity266 = listRoute26.get(6);
//        assertEquals(525L, entity266.getTablleId(), "経路17詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity267 = listRoute26.get(7);
//        assertEquals(620L, entity267.getTablleId(), "経路17詳細7");
//
//        // 経路27(政治団体迂回7階層)
//        SelectOptionDto dto27 = listOption.get(27);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute27 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto27.getValue()));
//        assertEquals(9, listRoute27.size(), "経路9のデータ数は9");
//        WkTblUkaiKenkinPickupRouteEntity entity270 = listRoute27.get(0);
//        assertEquals(36L, entity270.getTablleId(), "経路27詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity271 = listRoute27.get(1);
//        assertEquals(57L, entity271.getTablleId(), "経路27詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity272 = listRoute27.get(2);
//        assertEquals(126L, entity272.getTablleId(), "経路27詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity273 = listRoute27.get(3);
//        assertEquals(226L, entity273.getTablleId(), "経路27詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity274 = listRoute27.get(4);
//        assertEquals(326L, entity274.getTablleId(), "経路27詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity275 = listRoute27.get(5);
//        assertEquals(426L, entity275.getTablleId(), "経路27詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity276 = listRoute27.get(6);
//        assertEquals(526L, entity276.getTablleId(), "経路27詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity277 = listRoute27.get(7);
//        assertEquals(626L, entity277.getTablleId(), "経路27詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity278 = listRoute27.get(8);
//        assertEquals(720L, entity278.getTablleId(), "経路27詳細8");
//
//        // 経路28(政治団体迂回8階層)
//        SelectOptionDto dto28 = listOption.get(28);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute28 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto28.getValue()));
//        assertEquals(10, listRoute28.size(), "経路28のデータ数は10");
//        WkTblUkaiKenkinPickupRouteEntity entity280 = listRoute28.get(0);
//        assertEquals(37L, entity280.getTablleId(), "経路28詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity281 = listRoute28.get(1);
//        assertEquals(58L, entity281.getTablleId(), "経路28詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity282 = listRoute28.get(2);
//        assertEquals(127L, entity282.getTablleId(), "経路28詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity283 = listRoute28.get(3);
//        assertEquals(227L, entity283.getTablleId(), "経路28詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity284 = listRoute28.get(4);
//        assertEquals(327L, entity284.getTablleId(), "経路28詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity285 = listRoute28.get(5);
//        assertEquals(427L, entity285.getTablleId(), "経路28詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity286 = listRoute28.get(6);
//        assertEquals(527L, entity286.getTablleId(), "経路28詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity287 = listRoute28.get(7);
//        assertEquals(627L, entity287.getTablleId(), "経路28詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity288 = listRoute28.get(8);
//        assertEquals(727L, entity288.getTablleId(), "経路28詳細8");
//        WkTblUkaiKenkinPickupRouteEntity entity289 = listRoute28.get(9);
//        assertEquals(820L, entity289.getTablleId(), "経路28詳細9");
//
//        // 経路29(政治団体迂回9階層)
//        SelectOptionDto dto29 = listOption.get(29);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute29 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto29.getValue()));
//        assertEquals(11, listRoute29.size(), "経路29のデータ数は11");
//        WkTblUkaiKenkinPickupRouteEntity entity290 = listRoute29.get(0);
//        assertEquals(38L, entity290.getTablleId(), "経路29細0");
//        WkTblUkaiKenkinPickupRouteEntity entity291 = listRoute29.get(1);
//        assertEquals(59L, entity291.getTablleId(), "経路29詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity292 = listRoute29.get(2);
//        assertEquals(128L, entity292.getTablleId(), "経路29詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity293 = listRoute29.get(3);
//        assertEquals(228L, entity293.getTablleId(), "経路29詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity294 = listRoute29.get(4);
//        assertEquals(328L, entity294.getTablleId(), "経路29詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity295 = listRoute29.get(5);
//        assertEquals(428L, entity295.getTablleId(), "経路29詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity296 = listRoute29.get(6);
//        assertEquals(528L, entity296.getTablleId(), "経路29詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity297 = listRoute29.get(7);
//        assertEquals(628L, entity297.getTablleId(), "経路29詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity298 = listRoute29.get(8);
//        assertEquals(728L, entity298.getTablleId(), "経路29詳細8");
//        WkTblUkaiKenkinPickupRouteEntity entity299 = listRoute29.get(9);
//        assertEquals(828L, entity299.getTablleId(), "経路29詳細9");
//        WkTblUkaiKenkinPickupRouteEntity entity29A = listRoute29.get(10);
//        assertEquals(920L, entity29A.getTablleId(), "経路29詳細10");

        /* 個人(迂回献金太郎)が企業または政治団体に含まれる */
        // 経路30(個人0階層)

        /* 企業代表者(兼職良子)が政治団体に含まれる */
        // 経路31(企業0階層)

        /* 企業代表者(兼職良子)が企業・政治団体に含まれる */
        // 経路33(企業0階層)

        /* 政治団体関係者(不明隆志)が政治団体関係者に含まれる */
        // 経路53(政治団体0階層)

//        /* 政治団体関連者迂回階層 */
//        // 経路51(政治団体迂回1階層)
//        SelectOptionDto dto35 = listOption.get(51);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute35 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto35.getValue()));
//        assertEquals(3, listRoute35.size(), "経路35のデータ数は3");
//        WkTblUkaiKenkinPickupRouteEntity entity350 = listRoute35.get(0);
//        assertEquals(30L, entity350.getTablleId(), "経路35詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity351 = listRoute35.get(1);
//        assertEquals(30001L, entity351.getTablleId(), "経路35詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity352 = listRoute35.get(2);
//        assertEquals(30002L, entity352.getTablleId(), "経路35詳細2");
//
//        // 経路36(政治団体迂回2階層)
//        SelectOptionDto dto36 = listOption.get(52);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute36 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto36.getValue()));
//        assertEquals(4, listRoute36.size(), "経路36のデータ数は4");
//        WkTblUkaiKenkinPickupRouteEntity entity360 = listRoute36.get(0);
//        assertEquals(31L, entity360.getTablleId(), "経路36詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity361 = listRoute36.get(1);
//        assertEquals(30003L, entity361.getTablleId(), "経路36詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity362 = listRoute36.get(2);
//        assertEquals(121L, entity362.getTablleId(), "経路36詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity363 = listRoute36.get(3);
//        assertEquals(30004L, entity363.getTablleId(), "経路36詳細3");
//
//        // 経路37(政治団体迂回3階層)
//        SelectOptionDto dto37 = listOption.get(37);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute37 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto37.getValue()));
//        assertEquals(5, listRoute37.size(), "経路23のデータ数は5");
//        WkTblUkaiKenkinPickupRouteEntity entity370 = listRoute37.get(0);
//        assertEquals(32L, entity370.getTablleId(), "経路37詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity371 = listRoute37.get(1);
//        assertEquals(30005L, entity371.getTablleId(), "経路37詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity372 = listRoute37.get(2);
//        assertEquals(122L, entity372.getTablleId(), "経路37詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity373 = listRoute37.get(3);
//        assertEquals(221L, entity373.getTablleId(), "経路37詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity374 = listRoute37.get(4);
//        assertEquals(30006L, entity374.getTablleId(), "経路37詳細4");
//
//        // 経路38(政治団体迂回4階層)
//        SelectOptionDto dto38 = listOption.get(38);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute38 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto38.getValue()));
//        assertEquals(6, listRoute38.size(), "経路38のデータ数は6");
//        WkTblUkaiKenkinPickupRouteEntity entity380 = listRoute38.get(0);
//        assertEquals(33L, entity380.getTablleId(), "経路38詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity381 = listRoute38.get(1);
//        assertEquals(30007L, entity381.getTablleId(), "経路38詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity382 = listRoute38.get(2);
//        assertEquals(123L, entity382.getTablleId(), "経路24詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity383 = listRoute38.get(3);
//        assertEquals(222L, entity383.getTablleId(), "経路24詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity384 = listRoute38.get(4);
//        assertEquals(321L, entity384.getTablleId(), "経路38詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity385 = listRoute38.get(5);
//        assertEquals(30008L, entity385.getTablleId(), "経路38詳細5");
//
//        // 経路39(政治団体迂回5階層)
//        SelectOptionDto dto39 = listOption.get(39);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute39 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto39.getValue()));
//        assertEquals(7, listRoute39.size(), "経路39のデータ数は7");
//        WkTblUkaiKenkinPickupRouteEntity entity390 = listRoute39.get(0);
//        assertEquals(34L, entity390.getTablleId(), "経路39詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity391 = listRoute39.get(1);
//        assertEquals(30009L, entity391.getTablleId(), "経路39詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity392 = listRoute39.get(2);
//        assertEquals(124L, entity392.getTablleId(), "経路39詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity393 = listRoute39.get(3);
//        assertEquals(224L, entity393.getTablleId(), "経路39詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity394 = listRoute39.get(4);
//        assertEquals(324L, entity394.getTablleId(), "経路39詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity395 = listRoute39.get(5);
//        assertEquals(424L, entity395.getTablleId(), "経路39詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity396 = listRoute39.get(6);
//        assertEquals(30010L, entity396.getTablleId(), "経路39詳細6");
//
//        // 経路40(政治団体迂回6階層)
//        SelectOptionDto dto40 = listOption.get(40);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute40 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto40.getValue()));
//        assertEquals(8, listRoute40.size(), "経路40のデータ数は8");
//        WkTblUkaiKenkinPickupRouteEntity entity400 = listRoute40.get(0);
//        assertEquals(35L, entity400.getTablleId(), "経路40詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity401 = listRoute40.get(1);
//        assertEquals(30011L, entity401.getTablleId(), "経路40詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity402 = listRoute40.get(2);
//        assertEquals(125L, entity402.getTablleId(), "経路40詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity403 = listRoute40.get(3);
//        assertEquals(225L, entity403.getTablleId(), "経路40詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity404 = listRoute40.get(4);
//        assertEquals(325L, entity404.getTablleId(), "経路40詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity405 = listRoute40.get(5);
//        assertEquals(425L, entity405.getTablleId(), "経路40詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity406 = listRoute40.get(6);
//        assertEquals(525L, entity406.getTablleId(), "経路40詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity407 = listRoute40.get(7);
//        assertEquals(30012L, entity407.getTablleId(), "経路40詳細7");
//
//        // 経路41(政治団体迂回7階層)
//        SelectOptionDto dto41 = listOption.get(41);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute41 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto41.getValue()));
//        assertEquals(9, listRoute41.size(), "経路41のデータ数は9");
//        WkTblUkaiKenkinPickupRouteEntity entity410 = listRoute41.get(0);
//        assertEquals(36L, entity410.getTablleId(), "経路41詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity411 = listRoute41.get(1);
//        assertEquals(30013L, entity411.getTablleId(), "経路27詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity412 = listRoute41.get(2);
//        assertEquals(126L, entity412.getTablleId(), "経路41詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity413 = listRoute41.get(3);
//        assertEquals(226L, entity413.getTablleId(), "経路41詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity414 = listRoute41.get(4);
//        assertEquals(326L, entity414.getTablleId(), "経路41詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity415 = listRoute41.get(5);
//        assertEquals(426L, entity415.getTablleId(), "経路41詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity416 = listRoute41.get(6);
//        assertEquals(526L, entity416.getTablleId(), "経路41詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity417 = listRoute41.get(7);
//        assertEquals(626L, entity417.getTablleId(), "経路41詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity418 = listRoute41.get(8);
//        assertEquals(30014L, entity418.getTablleId(), "経路41詳細8");
//
//        // 経路42(政治団体迂回8階層)
//        SelectOptionDto dto42 = listOption.get(42);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute42 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto42.getValue()));
//        assertEquals(10, listRoute42.size(), "経路42のデータ数は10");
//        WkTblUkaiKenkinPickupRouteEntity entity420 = listRoute42.get(0);
//        assertEquals(37L, entity420.getTablleId(), "経路42詳細0");
//        WkTblUkaiKenkinPickupRouteEntity entity421 = listRoute42.get(1);
//        assertEquals(30015L, entity421.getTablleId(), "経路42詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity422 = listRoute42.get(2);
//        assertEquals(127L, entity422.getTablleId(), "経路42詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity423 = listRoute42.get(3);
//        assertEquals(227L, entity423.getTablleId(), "経路42詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity424 = listRoute42.get(4);
//        assertEquals(327L, entity424.getTablleId(), "経路42詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity425 = listRoute42.get(5);
//        assertEquals(427L, entity425.getTablleId(), "経路42詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity426 = listRoute42.get(6);
//        assertEquals(527L, entity426.getTablleId(), "経路42詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity427 = listRoute42.get(7);
//        assertEquals(627L, entity427.getTablleId(), "経路42詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity428 = listRoute42.get(8);
//        assertEquals(727L, entity428.getTablleId(), "経路42詳細8");
//        WkTblUkaiKenkinPickupRouteEntity entity429 = listRoute42.get(9);
//        assertEquals(30016L, entity429.getTablleId(), "経路42詳細9");
//        
//        
//        // 経路43(政治団体関連者迂回9階層)
//        SelectOptionDto dto43 = listOption.get(43);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute43 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto43.getValue()));
//        assertEquals(11, listRoute43.size(), "経路43のデータ数は11");
//        WkTblUkaiKenkinPickupRouteEntity entity430 = listRoute43.get(0);
//        assertEquals(38L, entity430.getTablleId(), "経路43細0");
//        WkTblUkaiKenkinPickupRouteEntity entity431 = listRoute43.get(1);
//        assertEquals(30017L, entity431.getTablleId(), "経路43詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity432 = listRoute43.get(2);
//        assertEquals(128L, entity432.getTablleId(), "経路43詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity433 = listRoute43.get(3);
//        assertEquals(228L, entity433.getTablleId(), "経路43詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity434 = listRoute43.get(4);
//        assertEquals(328L, entity434.getTablleId(), "経路43詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity435 = listRoute43.get(5);
//        assertEquals(428L, entity435.getTablleId(), "経路43詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity436 = listRoute43.get(6);
//        assertEquals(528L, entity436.getTablleId(), "経路43詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity437 = listRoute43.get(7);
//        assertEquals(628L, entity437.getTablleId(), "経路43詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity438 = listRoute43.get(8);
//        assertEquals(728L, entity438.getTablleId(), "経路43詳細8");
//        WkTblUkaiKenkinPickupRouteEntity entity439 = listRoute43.get(9);
//        assertEquals(828L, entity439.getTablleId(), "経路43詳細9");
//        WkTblUkaiKenkinPickupRouteEntity entity43A = listRoute43.get(10);
//        assertEquals(30018L, entity43A.getTablleId(), "経路43詳細10");

//        // 経路6
//        SelectOptionDto dto05 = listOption.get(5);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute5 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto05.getValue()));
//        assertEquals(1, listRoute5.size(), "経路6のデータ数は1");
//        WkTblUkaiKenkinPickupRouteEntity entity50 = listRoute5.get(0);
//        assertEquals(16L, entity50.getTablleId(), "経路6詳細1");
//
//        // 経路7
//        SelectOptionDto dto06 = listOption.get(6);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute6 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto06.getValue()));
//        assertEquals(1, listRoute6.size(), "経路7のデータ数は1");
//        WkTblUkaiKenkinPickupRouteEntity entity60 = listRoute6.get(0);
//        assertEquals(17L, entity60.getTablleId(), "経路7詳細1");
//
//        // 経路8
//        SelectOptionDto dto07 = listOption.get(7);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute7 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto07.getValue()));
//        assertEquals(1, listRoute7.size(), "経路8のデータ数は1");
//        WkTblUkaiKenkinPickupRouteEntity entity70 = listRoute7.get(0);
//        assertEquals(18L, entity70.getTablleId(), "経路8詳細1");
//
//        // 経路9
//        SelectOptionDto dto08 = listOption.get(8);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute8 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto08.getValue()));
//        assertEquals(1, listRoute8.size(), "経路9のデータ数は1");
//        WkTblUkaiKenkinPickupRouteEntity entity80 = listRoute8.get(0);
//        assertEquals(21L, entity80.getTablleId(), "経路9詳細1");
//
//        // 経路10
//        SelectOptionDto dto09 = listOption.get(9);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute9 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto09.getValue()));
//        assertEquals(1, listRoute9.size(), "経路10のデータ数は1");
//        WkTblUkaiKenkinPickupRouteEntity entity90 = listRoute9.get(0);
//        assertEquals(22L, entity90.getTablleId(), "経路10詳細1");
//
//        /* ここから政治団体(政治団体階層1以上) */
//
//        // 経路11(政治団体1)
//        SelectOptionDto dto10 = listOption.get(10);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute10 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto10.getValue()));
//        assertEquals(3, listRoute10.size(), "経路11のデータ数は3");
//        WkTblUkaiKenkinPickupRouteEntity entity100 = listRoute10.get(0);
//        assertEquals(30L, entity100.getTablleId(), "経路11詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity101 = listRoute10.get(1);
//        assertEquals(51L, entity101.getTablleId(), "経路11詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity102 = listRoute10.get(2);
//        assertEquals(120L, entity102.getTablleId(), "経路11詳細3");
//
//        // 経路12(政治団体2)
//        SelectOptionDto dto11 = listOption.get(11);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute11 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto11.getValue()));
//        assertEquals(4, listRoute11.size(), "経路12のデータ数は4");
//        WkTblUkaiKenkinPickupRouteEntity entity110 = listRoute11.get(0);
//        assertEquals(31L, entity110.getTablleId(), "経路12詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity111 = listRoute11.get(1);
//        assertEquals(52L, entity111.getTablleId(), "経路12詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity112 = listRoute11.get(2);
//        assertEquals(121L, entity112.getTablleId(), "経路12詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity113 = listRoute11.get(3);
//        assertEquals(220L, entity113.getTablleId(), "経路12詳細4");
//
//        // 経路13(政治団体3)
//        SelectOptionDto dto12 = listOption.get(12);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute12 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto12.getValue()));
//        assertEquals(5, listRoute12.size(), "経路13のデータ数は5");
//        WkTblUkaiKenkinPickupRouteEntity entity120 = listRoute12.get(0);
//        assertEquals(32L, entity120.getTablleId(), "経路13詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity121 = listRoute12.get(1);
//        assertEquals(53L, entity121.getTablleId(), "経路13詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity122 = listRoute12.get(2);
//        assertEquals(122L, entity122.getTablleId(), "経路13詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity123 = listRoute12.get(3);
//        assertEquals(221L, entity123.getTablleId(), "経路13詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity124 = listRoute12.get(4);
//        assertEquals(320L, entity124.getTablleId(), "経路13詳細4");
//
//        // 経路14(政治団体4)
//        SelectOptionDto dto13 = listOption.get(13);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute13 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto13.getValue()));
//        assertEquals(6, listRoute13.size(), "経路14のデータ数は6");
//        WkTblUkaiKenkinPickupRouteEntity entity130 = listRoute13.get(0);
//        assertEquals(33L, entity130.getTablleId(), "経路14詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity131 = listRoute13.get(1);
//        assertEquals(54L, entity131.getTablleId(), "経路14詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity132 = listRoute13.get(2);
//        assertEquals(123L, entity132.getTablleId(), "経路14詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity133 = listRoute13.get(3);
//        assertEquals(222L, entity133.getTablleId(), "経路14詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity134 = listRoute13.get(4);
//        assertEquals(321L, entity134.getTablleId(), "経路14詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity135 = listRoute13.get(5);
//        assertEquals(420L, entity135.getTablleId(), "経路14詳細6");
//
//        // 経路15(政治団体5)
//        SelectOptionDto dto14 = listOption.get(14);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute14 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto14.getValue()));
//        assertEquals(7, listRoute14.size(), "経路15のデータ数は6");
//        WkTblUkaiKenkinPickupRouteEntity entity140 = listRoute14.get(0);
//        assertEquals(34L, entity140.getTablleId(), "経路15詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity141 = listRoute14.get(1);
//        assertEquals(55L, entity141.getTablleId(), "経路15詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity142 = listRoute14.get(2);
//        assertEquals(124L, entity142.getTablleId(), "経路15詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity143 = listRoute14.get(3);
//        assertEquals(224L, entity143.getTablleId(), "経路15詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity144 = listRoute14.get(4);
//        assertEquals(324L, entity144.getTablleId(), "経路15詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity145 = listRoute14.get(5);
//        assertEquals(424L, entity145.getTablleId(), "経路15詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity146 = listRoute14.get(6);
//        assertEquals(520L, entity146.getTablleId(), "経路15詳細5");
//
//        // 経路16(政治団体6)
//        SelectOptionDto dto15 = listOption.get(15);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute15 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto15.getValue()));
//        assertEquals(8, listRoute15.size(), "経路16のデータ数は8");
//        WkTblUkaiKenkinPickupRouteEntity entity150 = listRoute15.get(0);
//        assertEquals(35L, entity150.getTablleId(), "経路16詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity151 = listRoute15.get(1);
//        assertEquals(56L, entity151.getTablleId(), "経路16詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity152 = listRoute15.get(2);
//        assertEquals(125L, entity152.getTablleId(), "経路16詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity153 = listRoute15.get(3);
//        assertEquals(225L, entity153.getTablleId(), "経路16詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity154 = listRoute15.get(4);
//        assertEquals(325L, entity154.getTablleId(), "経路16詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity155 = listRoute15.get(5);
//        assertEquals(425L, entity155.getTablleId(), "経路16詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity156 = listRoute15.get(6);
//        assertEquals(525L, entity156.getTablleId(), "経路16詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity157 = listRoute15.get(7);
//        assertEquals(620L, entity157.getTablleId(), "経路16詳細7");
//
//        // 経路17(政治団体7)
//        SelectOptionDto dto16 = listOption.get(16);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute16 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto16.getValue()));
//        assertEquals(9, listRoute16.size(), "経路17のデータ数は9");
//        WkTblUkaiKenkinPickupRouteEntity entity160 = listRoute16.get(0);
//        assertEquals(36L, entity160.getTablleId(), "経路17詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity161 = listRoute16.get(1);
//        assertEquals(57L, entity161.getTablleId(), "経路17詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity162 = listRoute16.get(2);
//        assertEquals(126L, entity162.getTablleId(), "経路17詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity163 = listRoute16.get(3);
//        assertEquals(226L, entity163.getTablleId(), "経路17詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity164 = listRoute16.get(4);
//        assertEquals(326L, entity164.getTablleId(), "経路17詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity165 = listRoute16.get(5);
//        assertEquals(426L, entity165.getTablleId(), "経路17詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity166 = listRoute16.get(6);
//        assertEquals(526L, entity166.getTablleId(), "経路17詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity167 = listRoute16.get(7);
//        assertEquals(626L, entity167.getTablleId(), "経路17詳細8");
//        WkTblUkaiKenkinPickupRouteEntity entity168 = listRoute16.get(8);
//        assertEquals(720L, entity168.getTablleId(), "経路17詳細8");

//        // 経路18(政治団体8)
//        SelectOptionDto dto17 = listOption.get(17);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute17 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto17.getValue()));
//        assertEquals(10, listRoute17.size(), "経路18のデータ数は10");
//        WkTblUkaiKenkinPickupRouteEntity entity170 = listRoute17.get(0);
//        assertEquals(37L, entity170.getTablleId(), "経路18詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity171 = listRoute17.get(1);
//        assertEquals(58L, entity171.getTablleId(), "経路18詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity172 = listRoute17.get(2);
//        assertEquals(127L, entity172.getTablleId(), "経路18詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity173 = listRoute17.get(3);
//        assertEquals(227L, entity173.getTablleId(), "経路18詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity174 = listRoute17.get(4);
//        assertEquals(327L, entity174.getTablleId(), "経路18詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity175 = listRoute17.get(5);
//        assertEquals(427L, entity175.getTablleId(), "経路18詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity176 = listRoute17.get(6);
//        assertEquals(527L, entity176.getTablleId(), "経路18詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity177 = listRoute17.get(7);
//        assertEquals(627L, entity177.getTablleId(), "経路18詳細8");
//        WkTblUkaiKenkinPickupRouteEntity entity178 = listRoute17.get(8);
//        assertEquals(727L, entity178.getTablleId(), "経路18詳細9");
//        WkTblUkaiKenkinPickupRouteEntity entity179 = listRoute17.get(9);
//        assertEquals(820L, entity179.getTablleId(), "経路18詳細9");

//        // 経路19(政治団体9)
//        SelectOptionDto dto18 = listOption.get(18);
//        List<WkTblUkaiKenkinPickupRouteEntity> listRoute18 = wkTblUkaiKenkinPickupRouteRepository
//                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
//                        Integer.parseInt(dto18.getValue()));
//        assertEquals(11, listRoute18.size(), "経路19のデータ数は11");
//        WkTblUkaiKenkinPickupRouteEntity entity180 = listRoute18.get(0);
//        assertEquals(38L, entity180.getTablleId(), "経路19詳細1");
//        WkTblUkaiKenkinPickupRouteEntity entity181 = listRoute18.get(1);
//        assertEquals(59L, entity181.getTablleId(), "経路19詳細2");
//        WkTblUkaiKenkinPickupRouteEntity entity182 = listRoute18.get(2);
//        assertEquals(128L, entity182.getTablleId(), "経路19詳細3");
//        WkTblUkaiKenkinPickupRouteEntity entity183 = listRoute18.get(3);
//        assertEquals(228L, entity183.getTablleId(), "経路19詳細4");
//        WkTblUkaiKenkinPickupRouteEntity entity184 = listRoute18.get(4);
//        assertEquals(328L, entity184.getTablleId(), "経路19詳細5");
//        WkTblUkaiKenkinPickupRouteEntity entity185 = listRoute18.get(5);
//        assertEquals(428L, entity185.getTablleId(), "経路19詳細6");
//        WkTblUkaiKenkinPickupRouteEntity entity186 = listRoute18.get(6);
//        assertEquals(528L, entity186.getTablleId(), "経路19詳細7");
//        WkTblUkaiKenkinPickupRouteEntity entity187 = listRoute18.get(7);
//        assertEquals(628L, entity187.getTablleId(), "経路19詳細8");
//        WkTblUkaiKenkinPickupRouteEntity entity188 = listRoute18.get(8);
//        assertEquals(728L, entity188.getTablleId(), "経路19詳細9");
//        WkTblUkaiKenkinPickupRouteEntity entity189 = listRoute18.get(9);
//        assertEquals(828L, entity189.getTablleId(), "経路19詳細10");
//        WkTblUkaiKenkinPickupRouteEntity entity18A = listRoute18.get(10);
//        assertEquals(920L, entity18A.getTablleId(), "経路19詳細10");

    }

}
