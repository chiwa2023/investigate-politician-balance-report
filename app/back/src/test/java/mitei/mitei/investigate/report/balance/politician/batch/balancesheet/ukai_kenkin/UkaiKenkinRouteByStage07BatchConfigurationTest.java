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
 * UkaiKenkinRouteByStage07BatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UkaiKenkinRouteByStage07BatchConfigurationTest {
    // CHECKSTYLE:OFF

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(UkaiKenkinRouteByStage07BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage07;

    /** 作成データ抽出選択肢作成Logic */
    @Autowired
    private CreateUkaiKenkinRouteSelectOptionLogic createUkaiKenkinRouteSelectOptionLogic;

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(UkaiKenkinRouteByStage07BatchConfiguration.JOB_NAME, ukaiKenkinRouteByStage07.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    // @Transactional
    @Sql({ "configuration_income_2022.sql", "configuration_poli_org_property.sql",
            "configuration_wktbl_meisai_clean.sql", "configuration_wktbl_route.sql" })
    void test() throws Exception { // NOPMD

        jobLauncherTestUtils.setJob(ukaiKenkinRouteByStage07);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        Integer userCode = privilegeDto.getLoginUserCode();

        JobParameters jobParameters = new JobParametersBuilder(
                ukaiKenkinRouteByStage07.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
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
        assertEquals(77, listOption.size(), "77経路取得できた");

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

        // 経路6(2階層個人から企業代表者)
        SelectOptionDto dto06 = listOption.get(6);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute06 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto06.getValue()));
        assertEquals(4, listRoute06.size(), "経路6のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity060 = listRoute06.get(0);
        assertEquals(31L, entity060.getTablleId(), "経路6詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity061 = listRoute06.get(1);
        assertEquals(11L, entity061.getTablleId(), "経路6詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity062 = listRoute06.get(2);
        assertEquals(121L, entity062.getTablleId(), "経路6詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity063 = listRoute06.get(3);
        assertEquals(33_102L, entity063.getTablleId(), "経路6詳細3");

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
        assertEquals(34_102L, entity073.getTablleId(), "経路7詳細3");

        // 経路8(3階層個人から企業代表者)
        SelectOptionDto dto08 = listOption.get(8);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute08 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto08.getValue()));
        assertEquals(5, listRoute08.size(), "経路8のデータ数は5");
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
        assertEquals(8, listRoute14.size(), "経路14のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity140 = listRoute14.get(0);
        assertEquals(35L, entity140.getTablleId(), "経路14詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity141 = listRoute14.get(1);
        assertEquals(11L, entity141.getTablleId(), "経路14詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity142 = listRoute14.get(2);
        assertEquals(125L, entity142.getTablleId(), "経路8詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity143 = listRoute14.get(3);
        assertEquals(225L, entity143.getTablleId(), "経路14詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity144 = listRoute14.get(4);
        assertEquals(325L, entity144.getTablleId(), "経路14詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity145 = listRoute14.get(5);
        assertEquals(425L, entity145.getTablleId(), "経路14詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity146 = listRoute14.get(6);
        assertEquals(525L, entity146.getTablleId(), "経路14詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity147 = listRoute14.get(7);
        assertEquals(33_106L, entity147.getTablleId(), "経路14詳細7");

        // 経路15(6階層個人から政治団体関連者)
        SelectOptionDto dto15 = listOption.get(15);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute15 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto15.getValue()));
        assertEquals(8, listRoute15.size(), "経路15のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity150 = listRoute15.get(0);
        assertEquals(35L, entity150.getTablleId(), "経路15詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity151 = listRoute15.get(1);
        assertEquals(11L, entity151.getTablleId(), "経路15詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity152 = listRoute15.get(2);
        assertEquals(125L, entity152.getTablleId(), "経路15詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity153 = listRoute15.get(3);
        assertEquals(225L, entity153.getTablleId(), "経15詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity154 = listRoute15.get(4);
        assertEquals(325L, entity154.getTablleId(), "経路15詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity155 = listRoute15.get(5);
        assertEquals(425L, entity155.getTablleId(), "経路15詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity156 = listRoute15.get(6);
        assertEquals(525L, entity156.getTablleId(), "経路15詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity157 = listRoute15.get(7);
        assertEquals(34_106L, entity157.getTablleId(), "経路15詳細7");

        // 経路16(7階層個人から企業代表者)
        SelectOptionDto dto16 = listOption.get(16);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute16 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto16.getValue()));
        assertEquals(9, listRoute16.size(), "経路16のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity160 = listRoute16.get(0);
        assertEquals(36L, entity160.getTablleId(), "経路16詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity161 = listRoute16.get(1);
        assertEquals(11L, entity161.getTablleId(), "経路16詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity162 = listRoute16.get(2);
        assertEquals(126L, entity162.getTablleId(), "経路16詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity163 = listRoute16.get(3);
        assertEquals(226L, entity163.getTablleId(), "経路16詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity164 = listRoute16.get(4);
        assertEquals(326L, entity164.getTablleId(), "経路16詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity165 = listRoute16.get(5);
        assertEquals(426L, entity165.getTablleId(), "経路16詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity166 = listRoute16.get(6);
        assertEquals(526L, entity166.getTablleId(), "経路16詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity167 = listRoute16.get(7);
        assertEquals(626L, entity167.getTablleId(), "経路16詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity168 = listRoute16.get(8);
        assertEquals(33_107L, entity168.getTablleId(), "経路16詳細8");

        // 経路17(7階層個人から政治団体関連者)
        SelectOptionDto dto17 = listOption.get(17);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute17 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto17.getValue()));
        assertEquals(9, listRoute17.size(), "経路17のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity170 = listRoute17.get(0);
        assertEquals(36L, entity170.getTablleId(), "経路17詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity171 = listRoute17.get(1);
        assertEquals(11L, entity171.getTablleId(), "経路17詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity172 = listRoute17.get(2);
        assertEquals(126L, entity172.getTablleId(), "経路17詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity173 = listRoute17.get(3);
        assertEquals(226L, entity173.getTablleId(), "経路17詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity174 = listRoute17.get(4);
        assertEquals(326L, entity174.getTablleId(), "経路17詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity175 = listRoute17.get(5);
        assertEquals(426L, entity175.getTablleId(), "経路17詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity176 = listRoute17.get(6);
        assertEquals(526L, entity176.getTablleId(), "経路17詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity177 = listRoute17.get(7);
        assertEquals(626L, entity177.getTablleId(), "経路17詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity178 = listRoute17.get(8);
        assertEquals(34_107L, entity178.getTablleId(), "経路17詳細8");

        /* 個人迂回階層 */
        // 経路22(個人迂回1階層)
        SelectOptionDto dto22 = listOption.get(18);
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

        // 経路23(個人迂回2階層)
        SelectOptionDto dto23 = listOption.get(19);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute23 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto23.getValue()));
        assertEquals(5, listRoute23.size(), "経路23のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity230 = listRoute23.get(0);
        assertEquals(31L, entity230.getTablleId(), "経路23詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity231 = listRoute23.get(1);
        assertEquals(11_003L, entity231.getTablleId(), "経路23詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity232 = listRoute23.get(2);
        assertEquals(11_003L, entity232.getTablleId(), "経路23詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity233 = listRoute23.get(3);
        assertEquals(121L, entity233.getTablleId(), "経路23詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity234 = listRoute23.get(4);
        assertEquals(11_004L, entity234.getTablleId(), "経路23詳細3");

        // 経路24(個人迂回3階層)
        SelectOptionDto dto24 = listOption.get(20);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute24 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto24.getValue()));
        assertEquals(6, listRoute24.size(), "経路24のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity240 = listRoute24.get(0);
        assertEquals(32L, entity240.getTablleId(), "経路24詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity241 = listRoute24.get(1);
        assertEquals(11_005L, entity241.getTablleId(), "経路24詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity242 = listRoute24.get(2);
        assertEquals(11_005L, entity242.getTablleId(), "経路2424詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity243 = listRoute24.get(3);
        assertEquals(122L, entity243.getTablleId(), "経路24詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity244 = listRoute24.get(4);
        assertEquals(221L, entity244.getTablleId(), "経路24詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity245 = listRoute24.get(5);
        assertEquals(11_006L, entity245.getTablleId(), "経路24詳細4");

        // 経路25(個人迂回4階層)
        SelectOptionDto dto25 = listOption.get(21);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute25 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto25.getValue()));
        assertEquals(7, listRoute25.size(), "経路25のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity250 = listRoute25.get(0);
        assertEquals(33L, entity250.getTablleId(), "経路25詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity251 = listRoute25.get(1);
        assertEquals(11_007L, entity251.getTablleId(), "経路25詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity252 = listRoute25.get(2);
        assertEquals(11_007L, entity252.getTablleId(), "経路25詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity253 = listRoute25.get(3);
        assertEquals(123L, entity253.getTablleId(), "経路25詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity254 = listRoute25.get(4);
        assertEquals(222L, entity254.getTablleId(), "経路25詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity255 = listRoute25.get(5);
        assertEquals(321L, entity255.getTablleId(), "経路25詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity256 = listRoute25.get(6);
        assertEquals(11_008L, entity256.getTablleId(), "経路25詳細5");

        // 経路26(個人迂回5階層)
        SelectOptionDto dto26 = listOption.get(22);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute26 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto26.getValue()));
        assertEquals(8, listRoute26.size(), "経路26のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity260 = listRoute26.get(0);
        assertEquals(34L, entity260.getTablleId(), "経路26詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity261 = listRoute26.get(1);
        assertEquals(11_009L, entity261.getTablleId(), "経路26詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity262 = listRoute26.get(2);
        assertEquals(11_009L, entity262.getTablleId(), "経路26詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity263 = listRoute26.get(3);
        assertEquals(124L, entity263.getTablleId(), "経路26詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity264 = listRoute26.get(4);
        assertEquals(224L, entity264.getTablleId(), "経路26詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity265 = listRoute26.get(5);
        assertEquals(324L, entity265.getTablleId(), "経路26詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity266 = listRoute26.get(6);
        assertEquals(424L, entity266.getTablleId(), "経路26詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity267 = listRoute26.get(7);
        assertEquals(11_010L, entity267.getTablleId(), "経路26詳細6");

        // 経路27(個人迂回6階層)
        SelectOptionDto dto27 = listOption.get(23);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute27 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto27.getValue()));
        assertEquals(9, listRoute27.size(), "経路27のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity270 = listRoute27.get(0);
        assertEquals(35L, entity270.getTablleId(), "経路27詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity271 = listRoute27.get(1);
        assertEquals(11_011L, entity271.getTablleId(), "経路27詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity272 = listRoute27.get(2);
        assertEquals(11_011L, entity272.getTablleId(), "経路27詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity273 = listRoute27.get(3);
        assertEquals(125L, entity273.getTablleId(), "経路27詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity274 = listRoute27.get(4);
        assertEquals(225L, entity274.getTablleId(), "経27詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity275 = listRoute27.get(5);
        assertEquals(325L, entity275.getTablleId(), "経路27詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity276 = listRoute27.get(6);
        assertEquals(425L, entity276.getTablleId(), "経路27詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity277 = listRoute27.get(7);
        assertEquals(525L, entity277.getTablleId(), "経路27詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity278 = listRoute27.get(8);
        assertEquals(11_012L, entity278.getTablleId(), "経路27詳細7");

        // 経路28(個人迂回7階層)
        SelectOptionDto dto28 = listOption.get(24);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute28 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto28.getValue()));
        assertEquals(10, listRoute28.size(), "経路28のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity280 = listRoute28.get(0);
        assertEquals(36L, entity280.getTablleId(), "経路28詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity281 = listRoute28.get(1);
        assertEquals(11_013L, entity281.getTablleId(), "経路28詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity282 = listRoute28.get(2);
        assertEquals(11_013L, entity282.getTablleId(), "経路9詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity283 = listRoute28.get(3);
        assertEquals(126L, entity283.getTablleId(), "経路28詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity284 = listRoute28.get(4);
        assertEquals(226L, entity284.getTablleId(), "経路28詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity285 = listRoute28.get(5);
        assertEquals(326L, entity285.getTablleId(), "経路28詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity286 = listRoute28.get(6);
        assertEquals(426L, entity286.getTablleId(), "経路28詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity287 = listRoute28.get(7);
        assertEquals(526L, entity287.getTablleId(), "経路28詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity288 = listRoute28.get(8);
        assertEquals(626L, entity288.getTablleId(), "経路28詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity289 = listRoute28.get(9);
        assertEquals(11_014L, entity289.getTablleId(), "経路28詳細8");

        /* 企業迂回階層 */
        // 経路31(企業迂回1階層)
        SelectOptionDto dto31 = listOption.get(25);
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

        // 経路32(企業迂回2階層)
        SelectOptionDto dto32 = listOption.get(26);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute32 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto32.getValue()));
        assertEquals(4, listRoute32.size(), "経路32のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity320 = listRoute32.get(0);
        assertEquals(31L, entity320.getTablleId(), "経路32詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity321 = listRoute32.get(1);
        assertEquals(12_003L, entity321.getTablleId(), "経路32詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity322 = listRoute32.get(2);
        assertEquals(121L, entity322.getTablleId(), "経路32詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity323 = listRoute32.get(3);
        assertEquals(12_004L, entity323.getTablleId(), "経路32詳細3");

        // 経路33(企業迂回3階層)
        SelectOptionDto dto33 = listOption.get(27);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute33 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto33.getValue()));
        assertEquals(5, listRoute33.size(), "経路33のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity330 = listRoute33.get(0);
        assertEquals(32L, entity330.getTablleId(), "経路33詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity331 = listRoute33.get(1);
        assertEquals(12_005L, entity331.getTablleId(), "経路33詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity332 = listRoute33.get(2);
        assertEquals(122L, entity332.getTablleId(), "経路33詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity333 = listRoute33.get(3);
        assertEquals(221L, entity333.getTablleId(), "経路33詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity334 = listRoute33.get(4);
        assertEquals(12_006L, entity334.getTablleId(), "経路33詳細4");

        // 経路34(企業迂回4階層)
        SelectOptionDto dto34 = listOption.get(28);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute34 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto34.getValue()));
        assertEquals(6, listRoute34.size(), "経路34のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity340 = listRoute34.get(0);
        assertEquals(33L, entity340.getTablleId(), "経路34詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity341 = listRoute34.get(1);
        assertEquals(12_007L, entity341.getTablleId(), "経路34詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity342 = listRoute34.get(2);
        assertEquals(123L, entity342.getTablleId(), "経路34詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity343 = listRoute34.get(3);
        assertEquals(222L, entity343.getTablleId(), "経路34詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity344 = listRoute34.get(4);
        assertEquals(321L, entity344.getTablleId(), "経路34詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity345 = listRoute34.get(5);
        assertEquals(12_008L, entity345.getTablleId(), "経路34詳細5");

        // 経路35(企業迂回5階層)
        SelectOptionDto dto35 = listOption.get(29);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute35 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto35.getValue()));
        assertEquals(7, listRoute35.size(), "経路35のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity350 = listRoute35.get(0);
        assertEquals(34L, entity350.getTablleId(), "経路35詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity351 = listRoute35.get(1);
        assertEquals(12_009L, entity351.getTablleId(), "経路35詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity352 = listRoute35.get(2);
        assertEquals(124L, entity352.getTablleId(), "経路35詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity353 = listRoute35.get(3);
        assertEquals(224L, entity353.getTablleId(), "経路35詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity354 = listRoute35.get(4);
        assertEquals(324L, entity354.getTablleId(), "経路35詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity355 = listRoute35.get(5);
        assertEquals(424L, entity355.getTablleId(), "経路35詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity356 = listRoute35.get(6);
        assertEquals(12_010L, entity356.getTablleId(), "経路35詳細6");

        // 経路36(企業迂回6階層)
        SelectOptionDto dto36 = listOption.get(30);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute36 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto36.getValue()));
        assertEquals(8, listRoute36.size(), "経路36のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity360 = listRoute36.get(0);
        assertEquals(35L, entity360.getTablleId(), "経路36詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity361 = listRoute36.get(1);
        assertEquals(12_011L, entity361.getTablleId(), "経路36詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity362 = listRoute36.get(2);
        assertEquals(125L, entity362.getTablleId(), "経路36詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity363 = listRoute36.get(3);
        assertEquals(225L, entity363.getTablleId(), "経36詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity364 = listRoute36.get(4);
        assertEquals(325L, entity364.getTablleId(), "経路36詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity365 = listRoute36.get(5);
        assertEquals(425L, entity365.getTablleId(), "経路36詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity366 = listRoute36.get(6);
        assertEquals(525L, entity366.getTablleId(), "経路36詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity367 = listRoute36.get(7);
        assertEquals(12_012L, entity367.getTablleId(), "経路36詳細7");

        // 経路37(企業迂回7階層)
        SelectOptionDto dto37 = listOption.get(31);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute37 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto37.getValue()));
        assertEquals(9, listRoute37.size(), "経路37のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity370 = listRoute37.get(0);
        assertEquals(36L, entity370.getTablleId(), "経路37詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity371 = listRoute37.get(1);
        assertEquals(12_013L, entity371.getTablleId(), "経路37詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity372 = listRoute37.get(2);
        assertEquals(126L, entity372.getTablleId(), "経路37詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity373 = listRoute37.get(3);
        assertEquals(226L, entity373.getTablleId(), "経路37詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity374 = listRoute37.get(4);
        assertEquals(326L, entity374.getTablleId(), "経路37詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity375 = listRoute37.get(5);
        assertEquals(426L, entity375.getTablleId(), "経路37詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity376 = listRoute37.get(6);
        assertEquals(526L, entity376.getTablleId(), "経路37詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity377 = listRoute37.get(7);
        assertEquals(626L, entity377.getTablleId(), "経路37詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity378 = listRoute37.get(8);
        assertEquals(12_014L, entity378.getTablleId(), "経路37詳細8");

        /* 政治団体迂回階層 */
        // 経路40(政治団体迂回1階層)
        SelectOptionDto dto40 = listOption.get(32);
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

        // 経路41(政治団体迂回2階層)
        SelectOptionDto dto41 = listOption.get(33);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute41 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto41.getValue()));
        assertEquals(4, listRoute41.size(), "経路41のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity410 = listRoute41.get(0);
        assertEquals(31L, entity410.getTablleId(), "経路41詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity411 = listRoute41.get(1);
        assertEquals(52L, entity411.getTablleId(), "経路41詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity412 = listRoute41.get(2);
        assertEquals(121L, entity412.getTablleId(), "経路41詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity413 = listRoute41.get(3);
        assertEquals(220L, entity413.getTablleId(), "経路41詳細3");

        // 経路42(政治団体迂回3階層)
        SelectOptionDto dto42 = listOption.get(34);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute42 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto42.getValue()));
        assertEquals(5, listRoute42.size(), "経路42のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity420 = listRoute42.get(0);
        assertEquals(32L, entity420.getTablleId(), "経路42詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity421 = listRoute42.get(1);
        assertEquals(53L, entity421.getTablleId(), "経路42詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity422 = listRoute42.get(2);
        assertEquals(122L, entity422.getTablleId(), "経路14詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity423 = listRoute42.get(3);
        assertEquals(221L, entity423.getTablleId(), "経路42詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity424 = listRoute42.get(4);
        assertEquals(320L, entity424.getTablleId(), "経路42詳細4");

        // 経路43(政治団体迂回4階層)
        SelectOptionDto dto43 = listOption.get(35);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute43 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto43.getValue()));
        assertEquals(6, listRoute43.size(), "経路43のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity430 = listRoute43.get(0);
        assertEquals(33L, entity430.getTablleId(), "経路43詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity431 = listRoute43.get(1);
        assertEquals(54L, entity431.getTablleId(), "経路43詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity432 = listRoute43.get(2);
        assertEquals(123L, entity432.getTablleId(), "経路43詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity433 = listRoute43.get(3);
        assertEquals(222L, entity433.getTablleId(), "経路43詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity434 = listRoute43.get(4);
        assertEquals(321L, entity434.getTablleId(), "経路43詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity435 = listRoute43.get(5);
        assertEquals(420L, entity435.getTablleId(), "経路43詳細5");

        // 経路44(政治団体迂回5階層)
        SelectOptionDto dto44 = listOption.get(36);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute44 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto44.getValue()));
        assertEquals(7, listRoute44.size(), "経路44のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity440 = listRoute44.get(0);
        assertEquals(34L, entity440.getTablleId(), "経路16詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity441 = listRoute44.get(1);
        assertEquals(55L, entity441.getTablleId(), "経路44詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity442 = listRoute44.get(2);
        assertEquals(124L, entity442.getTablleId(), "経路44詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity443 = listRoute44.get(3);
        assertEquals(224L, entity443.getTablleId(), "経路44詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity444 = listRoute44.get(4);
        assertEquals(324L, entity444.getTablleId(), "経路44詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity445 = listRoute44.get(5);
        assertEquals(424L, entity445.getTablleId(), "経路44詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity446 = listRoute44.get(6);
        assertEquals(520L, entity446.getTablleId(), "経路44詳細6");

        // 経路45(政治団体迂回6階層)
        SelectOptionDto dto45 = listOption.get(37);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute45 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto45.getValue()));
        assertEquals(8, listRoute45.size(), "経路45のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity450 = listRoute45.get(0);
        assertEquals(35L, entity450.getTablleId(), "経路45詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity451 = listRoute45.get(1);
        assertEquals(56L, entity451.getTablleId(), "経路45詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity452 = listRoute45.get(2);
        assertEquals(125L, entity452.getTablleId(), "経路45詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity453 = listRoute45.get(3);
        assertEquals(225L, entity453.getTablleId(), "経45詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity454 = listRoute45.get(4);
        assertEquals(325L, entity454.getTablleId(), "経路45詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity455 = listRoute45.get(5);
        assertEquals(425L, entity455.getTablleId(), "経路45詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity456 = listRoute45.get(6);
        assertEquals(525L, entity456.getTablleId(), "経路45詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity457 = listRoute45.get(7);
        assertEquals(620L, entity457.getTablleId(), "経路45詳細7");

        // 経路46(政治団体迂回7階層)
        SelectOptionDto dto46 = listOption.get(38);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute46 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto46.getValue()));
        assertEquals(9, listRoute46.size(), "経路46のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity460 = listRoute46.get(0);
        assertEquals(36L, entity460.getTablleId(), "経路46詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity461 = listRoute46.get(1);
        assertEquals(57L, entity461.getTablleId(), "経路46詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity462 = listRoute46.get(2);
        assertEquals(126L, entity462.getTablleId(), "経路46詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity463 = listRoute46.get(3);
        assertEquals(226L, entity463.getTablleId(), "経路46詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity464 = listRoute46.get(4);
        assertEquals(326L, entity464.getTablleId(), "経路46詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity465 = listRoute46.get(5);
        assertEquals(426L, entity465.getTablleId(), "経路46詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity466 = listRoute46.get(6);
        assertEquals(526L, entity466.getTablleId(), "経路46詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity467 = listRoute46.get(7);
        assertEquals(626L, entity467.getTablleId(), "経路46詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity468 = listRoute46.get(8);
        assertEquals(720L, entity468.getTablleId(), "経路46詳細8");

        // 経路49(0階層企業代表者が政治団体関連者と一致)
        SelectOptionDto dto49 = listOption.get(39);
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
        SelectOptionDto dto50 = listOption.get(40);
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
        SelectOptionDto dto51 = listOption.get(41);
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

        // 経路52(2階層個人から企業代表者)
        SelectOptionDto dto52 = listOption.get(42);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute52 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto52.getValue()));
        assertEquals(4, listRoute52.size(), "経路52のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity520 = listRoute52.get(0);
        assertEquals(31L, entity520.getTablleId(), "経路52詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity521 = listRoute52.get(1);
        assertEquals(12L, entity521.getTablleId(), "経路52詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity522 = listRoute52.get(2);
        assertEquals(121L, entity522.getTablleId(), "経路52詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity523 = listRoute52.get(3);
        assertEquals(33_102L, entity523.getTablleId(), "経路52詳細3");

        // 経路7(2階層個人から政治団体関連者)
        SelectOptionDto dto53 = listOption.get(43);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute53 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto53.getValue()));
        assertEquals(4, listRoute53.size(), "経路53のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity530 = listRoute53.get(0);
        assertEquals(31L, entity530.getTablleId(), "経路53詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity531 = listRoute53.get(1);
        assertEquals(12L, entity531.getTablleId(), "経路53詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity532 = listRoute53.get(2);
        assertEquals(121L, entity532.getTablleId(), "経路53詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity533 = listRoute53.get(3);
        assertEquals(34_102L, entity533.getTablleId(), "経路53詳細3");

        // 経路8(3階層個人から企業代表者)
        SelectOptionDto dto54 = listOption.get(44);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute54 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto54.getValue()));
        assertEquals(5, listRoute54.size(), "経路54のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity540 = listRoute54.get(0);
        assertEquals(32L, entity540.getTablleId(), "経路54詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity541 = listRoute54.get(1);
        assertEquals(12L, entity541.getTablleId(), "経路54詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity542 = listRoute54.get(2);
        assertEquals(122L, entity542.getTablleId(), "経路54詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity543 = listRoute54.get(3);
        assertEquals(221L, entity543.getTablleId(), "経路54詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity544 = listRoute54.get(4);
        assertEquals(33_103L, entity544.getTablleId(), "経路54詳細4");

        // 経路55(3階層個人から政治団体関連者)
        SelectOptionDto dto55 = listOption.get(45);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute55 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto55.getValue()));
        assertEquals(5, listRoute55.size(), "経路55のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity550 = listRoute55.get(0);
        assertEquals(32L, entity550.getTablleId(), "経路55詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity551 = listRoute55.get(1);
        assertEquals(12L, entity551.getTablleId(), "経路55詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity552 = listRoute55.get(2);
        assertEquals(122L, entity552.getTablleId(), "経路55詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity553 = listRoute55.get(3);
        assertEquals(221L, entity553.getTablleId(), "経路55詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity554 = listRoute55.get(4);
        assertEquals(34_103L, entity554.getTablleId(), "経路55詳細4");

        // 経路56(3階層個人から企業代表者)
        SelectOptionDto dto56 = listOption.get(46);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute56 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto56.getValue()));
        assertEquals(6, listRoute56.size(), "経路56のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity560 = listRoute56.get(0);
        assertEquals(33L, entity560.getTablleId(), "経路56詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity561 = listRoute56.get(1);
        assertEquals(12L, entity561.getTablleId(), "経路56詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity562 = listRoute56.get(2);
        assertEquals(123L, entity562.getTablleId(), "経路56詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity563 = listRoute56.get(3);
        assertEquals(222L, entity563.getTablleId(), "経路56詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity564 = listRoute56.get(4);
        assertEquals(321L, entity564.getTablleId(), "経路56詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity565 = listRoute56.get(5);
        assertEquals(33_104L, entity565.getTablleId(), "経路56詳細5");

        // 経路57(4階層個人から政治団体関連者)
        SelectOptionDto dto57 = listOption.get(47);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute57 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto57.getValue()));
        assertEquals(6, listRoute57.size(), "経路57のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity570 = listRoute57.get(0);
        assertEquals(33L, entity570.getTablleId(), "経路57詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity571 = listRoute57.get(1);
        assertEquals(12L, entity571.getTablleId(), "経路57詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity572 = listRoute57.get(2);
        assertEquals(123L, entity572.getTablleId(), "経路57詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity573 = listRoute57.get(3);
        assertEquals(222L, entity573.getTablleId(), "経路57詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity574 = listRoute57.get(4);
        assertEquals(321L, entity574.getTablleId(), "経路57詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity575 = listRoute57.get(5);
        assertEquals(34_104L, entity575.getTablleId(), "経路57詳細5");

        // 経路58(3階層個人から企業代表者)
        SelectOptionDto dto58 = listOption.get(48);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute58 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto58.getValue()));
        assertEquals(7, listRoute58.size(), "経路58のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity580 = listRoute58.get(0);
        assertEquals(34L, entity580.getTablleId(), "経路58詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity581 = listRoute58.get(1);
        assertEquals(12L, entity581.getTablleId(), "経路58詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity582 = listRoute58.get(2);
        assertEquals(124L, entity582.getTablleId(), "経路58詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity583 = listRoute58.get(3);
        assertEquals(224L, entity583.getTablleId(), "経路58詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity584 = listRoute58.get(4);
        assertEquals(324L, entity584.getTablleId(), "経路58詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity585 = listRoute58.get(5);
        assertEquals(424L, entity585.getTablleId(), "経路58詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity586 = listRoute58.get(6);
        assertEquals(33_105L, entity586.getTablleId(), "経路58詳細6");

        SelectOptionDto dto59 = listOption.get(49);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute59 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto59.getValue()));
        assertEquals(7, listRoute59.size(), "経路59のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity590 = listRoute59.get(0);
        assertEquals(34L, entity590.getTablleId(), "経路59詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity591 = listRoute59.get(1);
        assertEquals(12L, entity591.getTablleId(), "経路59詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity592 = listRoute59.get(2);
        assertEquals(124L, entity592.getTablleId(), "経路59詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity593 = listRoute59.get(3);
        assertEquals(224L, entity593.getTablleId(), "経路59詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity594 = listRoute59.get(4);
        assertEquals(324L, entity594.getTablleId(), "経路59詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity595 = listRoute59.get(5);
        assertEquals(424L, entity595.getTablleId(), "経路59詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity596 = listRoute59.get(6);
        assertEquals(34_105L, entity596.getTablleId(), "経路59詳細6");

        // 経路60(6階層個人から企業代表者)
        SelectOptionDto dto60 = listOption.get(50);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute60 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto60.getValue()));
        assertEquals(8, listRoute60.size(), "経路60のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity600 = listRoute60.get(0);
        assertEquals(35L, entity600.getTablleId(), "経路60詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity601 = listRoute60.get(1);
        assertEquals(12L, entity601.getTablleId(), "経路60詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity602 = listRoute60.get(2);
        assertEquals(125L, entity602.getTablleId(), "経路60詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity603 = listRoute60.get(3);
        assertEquals(225L, entity603.getTablleId(), "経60詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity604 = listRoute60.get(4);
        assertEquals(325L, entity604.getTablleId(), "経路60詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity605 = listRoute60.get(5);
        assertEquals(425L, entity605.getTablleId(), "経路60詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity606 = listRoute60.get(6);
        assertEquals(525L, entity606.getTablleId(), "経路60詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity607 = listRoute60.get(7);
        assertEquals(33_106L, entity607.getTablleId(), "経路60詳細7");

        // 経路61(6階層個人から政治団体関連者)
        SelectOptionDto dto61 = listOption.get(51);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute61 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto61.getValue()));
        assertEquals(8, listRoute61.size(), "経路61のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity610 = listRoute61.get(0);
        assertEquals(35L, entity610.getTablleId(), "経路61詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity611 = listRoute61.get(1);
        assertEquals(12L, entity611.getTablleId(), "経路61詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity612 = listRoute61.get(2);
        assertEquals(125L, entity612.getTablleId(), "経路61詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity613 = listRoute61.get(3);
        assertEquals(225L, entity613.getTablleId(), "経61詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity614 = listRoute61.get(4);
        assertEquals(325L, entity614.getTablleId(), "経路61詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity615 = listRoute61.get(5);
        assertEquals(425L, entity615.getTablleId(), "経路61詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity616 = listRoute61.get(6);
        assertEquals(525L, entity616.getTablleId(), "経路61詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity617 = listRoute61.get(7);
        assertEquals(34_106L, entity617.getTablleId(), "経路8詳細7");

        // 経路62(7階層個人から企業代表者)
        SelectOptionDto dto62 = listOption.get(52);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute62 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto62.getValue()));
        assertEquals(9, listRoute62.size(), "経路62のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity620 = listRoute62.get(0);
        assertEquals(36L, entity620.getTablleId(), "経路62詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity621 = listRoute62.get(1);
        assertEquals(12L, entity621.getTablleId(), "経路62詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity622 = listRoute62.get(2);
        assertEquals(126L, entity622.getTablleId(), "経路62詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity623 = listRoute62.get(3);
        assertEquals(226L, entity623.getTablleId(), "経路62詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity624 = listRoute62.get(4);
        assertEquals(326L, entity624.getTablleId(), "経路62詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity625 = listRoute62.get(5);
        assertEquals(426L, entity625.getTablleId(), "経路62詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity626 = listRoute62.get(6);
        assertEquals(526L, entity626.getTablleId(), "経路62詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity627 = listRoute62.get(7);
        assertEquals(626L, entity627.getTablleId(), "経路62詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity628 = listRoute62.get(8);
        assertEquals(33_107L, entity628.getTablleId(), "経路62詳細8");

        // 経路63(7階層個人から政治団体関連者)
        SelectOptionDto dto63 = listOption.get(53);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute63 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto63.getValue()));
        assertEquals(9, listRoute63.size(), "経路63のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity630 = listRoute63.get(0);
        assertEquals(36L, entity630.getTablleId(), "経路63詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity631 = listRoute63.get(1);
        assertEquals(12L, entity631.getTablleId(), "経路63詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity632 = listRoute63.get(2);
        assertEquals(126L, entity632.getTablleId(), "経路63詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity633 = listRoute63.get(3);
        assertEquals(226L, entity633.getTablleId(), "経路63詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity634 = listRoute63.get(4);
        assertEquals(326L, entity634.getTablleId(), "経路63詳詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity635 = listRoute63.get(5);
        assertEquals(426L, entity635.getTablleId(), "経路63詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity636 = listRoute63.get(6);
        assertEquals(526L, entity636.getTablleId(), "経路9詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity637 = listRoute63.get(7);
        assertEquals(626L, entity637.getTablleId(), "経路63詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity638 = listRoute63.get(8);
        assertEquals(34_107L, entity638.getTablleId(), "経路63詳細8");

        /* 企業代表者迂回階層 */
        // 経路68(個人迂回1階層)
        SelectOptionDto dto68 = listOption.get(54);
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
        SelectOptionDto dto69 = listOption.get(55);
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
        SelectOptionDto dto70 = listOption.get(56);
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

        // 経路71(2階層個人から企業代表者)
        SelectOptionDto dto71 = listOption.get(57);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute71 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto71.getValue()));
        assertEquals(4, listRoute71.size(), "経路71のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity710 = listRoute71.get(0);
        assertEquals(31L, entity710.getTablleId(), "経路71詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity711 = listRoute71.get(1);
        assertEquals(61L, entity711.getTablleId(), "経路71詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity712 = listRoute71.get(2);
        assertEquals(121L, entity712.getTablleId(), "経路71詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity713 = listRoute71.get(3);
        assertEquals(31_102L, entity713.getTablleId(), "経路71詳細3");

        // 経路7(2階層個人から政治団体関連者)
        SelectOptionDto dto72 = listOption.get(58);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute72 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto72.getValue()));
        assertEquals(4, listRoute72.size(), "経路72のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity720 = listRoute72.get(0);
        assertEquals(31L, entity720.getTablleId(), "経路72詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity721 = listRoute72.get(1);
        assertEquals(61L, entity721.getTablleId(), "経路72詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity722 = listRoute72.get(2);
        assertEquals(121L, entity722.getTablleId(), "経路72詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity723 = listRoute72.get(3);
        assertEquals(32_102L, entity723.getTablleId(), "経路72詳細3");

        // 経路8(3階層個人から企業代表者)
        SelectOptionDto dto73 = listOption.get(59);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute73 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto73.getValue()));
        assertEquals(5, listRoute73.size(), "経路73のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity730 = listRoute73.get(0);
        assertEquals(32L, entity730.getTablleId(), "経路73詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity731 = listRoute73.get(1);
        assertEquals(61L, entity731.getTablleId(), "経路73詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity732 = listRoute73.get(2);
        assertEquals(122L, entity732.getTablleId(), "経路73詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity733 = listRoute73.get(3);
        assertEquals(221L, entity733.getTablleId(), "経路73詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity734 = listRoute73.get(4);
        assertEquals(31_103L, entity734.getTablleId(), "経路73詳細4");

        // 経路74(3階層個人から政治団体関連者)
        SelectOptionDto dto74 = listOption.get(60);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute74 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto74.getValue()));
        assertEquals(5, listRoute74.size(), "経路74のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity740 = listRoute74.get(0);
        assertEquals(32L, entity740.getTablleId(), "経路74詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity741 = listRoute74.get(1);
        assertEquals(61L, entity741.getTablleId(), "経路74詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity742 = listRoute74.get(2);
        assertEquals(122L, entity742.getTablleId(), "経路74詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity743 = listRoute74.get(3);
        assertEquals(221L, entity743.getTablleId(), "経路74詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity744 = listRoute74.get(4);
        assertEquals(32_103L, entity744.getTablleId(), "経路74詳細4");

        // 経路75(3階層個人から企業代表者)
        SelectOptionDto dto75 = listOption.get(61);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute75 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto75.getValue()));
        assertEquals(6, listRoute75.size(), "経路75のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity750 = listRoute75.get(0);
        assertEquals(33L, entity750.getTablleId(), "経路75詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity751 = listRoute75.get(1);
        assertEquals(61L, entity751.getTablleId(), "経路75詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity752 = listRoute75.get(2);
        assertEquals(123L, entity752.getTablleId(), "経路75詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity753 = listRoute75.get(3);
        assertEquals(222L, entity753.getTablleId(), "経路75詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity754 = listRoute75.get(4);
        assertEquals(321L, entity754.getTablleId(), "経路75詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity755 = listRoute75.get(5);
        assertEquals(31_104L, entity755.getTablleId(), "経路75詳細5");

        // 経路76(4階層個人から政治団体関連者)
        SelectOptionDto dto76 = listOption.get(62);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute76 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto76.getValue()));
        assertEquals(6, listRoute76.size(), "経路76のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity760 = listRoute76.get(0);
        assertEquals(33L, entity760.getTablleId(), "経路76詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity761 = listRoute76.get(1);
        assertEquals(61L, entity761.getTablleId(), "経路76詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity762 = listRoute76.get(2);
        assertEquals(123L, entity762.getTablleId(), "経路76詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity763 = listRoute76.get(3);
        assertEquals(222L, entity763.getTablleId(), "経路76詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity764 = listRoute76.get(4);
        assertEquals(321L, entity764.getTablleId(), "経路76詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity765 = listRoute76.get(5);
        assertEquals(32_104L, entity765.getTablleId(), "経路76詳細5");

        // 経路77(3階層個人から企業代表者)
        SelectOptionDto dto77 = listOption.get(63);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute77 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto77.getValue()));
        assertEquals(7, listRoute77.size(), "経路77のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity770 = listRoute77.get(0);
        assertEquals(34L, entity770.getTablleId(), "経路77詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity771 = listRoute77.get(1);
        assertEquals(61L, entity771.getTablleId(), "経路77詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity772 = listRoute77.get(2);
        assertEquals(124L, entity772.getTablleId(), "経路77詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity773 = listRoute77.get(3);
        assertEquals(224L, entity773.getTablleId(), "経路77詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity774 = listRoute77.get(4);
        assertEquals(324L, entity774.getTablleId(), "経路77詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity775 = listRoute77.get(5);
        assertEquals(424L, entity775.getTablleId(), "経路77詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity776 = listRoute77.get(6);
        assertEquals(31_105L, entity776.getTablleId(), "経路77詳細6");

        SelectOptionDto dto78 = listOption.get(64);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute78 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto78.getValue()));
        assertEquals(7, listRoute78.size(), "経路78のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity780 = listRoute78.get(0);
        assertEquals(34L, entity780.getTablleId(), "経路78詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity781 = listRoute78.get(1);
        assertEquals(61L, entity781.getTablleId(), "経路78詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity782 = listRoute78.get(2);
        assertEquals(124L, entity782.getTablleId(), "経路78詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity783 = listRoute78.get(3);
        assertEquals(224L, entity783.getTablleId(), "経路78詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity784 = listRoute78.get(4);
        assertEquals(324L, entity784.getTablleId(), "経路78詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity785 = listRoute78.get(5);
        assertEquals(424L, entity785.getTablleId(), "経路78詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity786 = listRoute78.get(6);
        assertEquals(32_105L, entity786.getTablleId(), "経路78詳細6");

        // 経路79(6階層個人から企業代表者)
        SelectOptionDto dto79 = listOption.get(65);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute79 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto79.getValue()));
        assertEquals(8, listRoute79.size(), "経路79のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity790 = listRoute79.get(0);
        assertEquals(35L, entity790.getTablleId(), "経路79詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity791 = listRoute79.get(1);
        assertEquals(61L, entity791.getTablleId(), "経路79詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity792 = listRoute79.get(2);
        assertEquals(125L, entity792.getTablleId(), "経路8詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity793 = listRoute79.get(3);
        assertEquals(225L, entity793.getTablleId(), "経79詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity794 = listRoute79.get(4);
        assertEquals(325L, entity794.getTablleId(), "経路79詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity795 = listRoute79.get(5);
        assertEquals(425L, entity795.getTablleId(), "経路79詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity796 = listRoute79.get(6);
        assertEquals(525L, entity796.getTablleId(), "経路79詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity797 = listRoute79.get(7);
        assertEquals(31_106L, entity797.getTablleId(), "経路79詳細7");

        // 経路80(6階層個人から政治団体関連者)
        SelectOptionDto dto80 = listOption.get(66);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute80 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto80.getValue()));
        assertEquals(8, listRoute80.size(), "経路80のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity800 = listRoute80.get(0);
        assertEquals(35L, entity800.getTablleId(), "経路80詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity801 = listRoute80.get(1);
        assertEquals(61L, entity801.getTablleId(), "経路80詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity802 = listRoute80.get(2);
        assertEquals(125L, entity802.getTablleId(), "経路80詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity803 = listRoute80.get(3);
        assertEquals(225L, entity803.getTablleId(), "経80詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity804 = listRoute80.get(4);
        assertEquals(325L, entity804.getTablleId(), "経路80詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity805 = listRoute80.get(5);
        assertEquals(425L, entity805.getTablleId(), "経路80詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity806 = listRoute80.get(6);
        assertEquals(525L, entity806.getTablleId(), "経路80詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity807 = listRoute80.get(7);
        assertEquals(32_106L, entity807.getTablleId(), "経路80詳細7");

        // 経路81(7階層個人から企業代表者)
        SelectOptionDto dto81 = listOption.get(67);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute81 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto81.getValue()));
        assertEquals(9, listRoute81.size(), "経路81のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity810 = listRoute81.get(0);
        assertEquals(36L, entity810.getTablleId(), "経路81詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity811 = listRoute81.get(1);
        assertEquals(61L, entity811.getTablleId(), "経路81詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity812 = listRoute81.get(2);
        assertEquals(126L, entity812.getTablleId(), "経路81詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity813 = listRoute81.get(3);
        assertEquals(226L, entity813.getTablleId(), "経路81詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity814 = listRoute81.get(4);
        assertEquals(326L, entity814.getTablleId(), "経路81詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity815 = listRoute81.get(5);
        assertEquals(426L, entity815.getTablleId(), "経路81詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity816 = listRoute81.get(6);
        assertEquals(526L, entity816.getTablleId(), "経路81詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity817 = listRoute81.get(7);
        assertEquals(626L, entity817.getTablleId(), "経路81詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity818 = listRoute81.get(8);
        assertEquals(31_107L, entity818.getTablleId(), "経路81詳細8");

        // 経路82(7階層個人から政治団体関連者)
        SelectOptionDto dto82 = listOption.get(68);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute82 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto82.getValue()));
        assertEquals(9, listRoute82.size(), "経路82のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity820 = listRoute82.get(0);
        assertEquals(36L, entity820.getTablleId(), "経路82詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity821 = listRoute82.get(1);
        assertEquals(61L, entity821.getTablleId(), "経路82詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity822 = listRoute82.get(2);
        assertEquals(126L, entity822.getTablleId(), "経路82詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity823 = listRoute82.get(3);
        assertEquals(226L, entity823.getTablleId(), "経路82詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity824 = listRoute82.get(4);
        assertEquals(326L, entity824.getTablleId(), "経路82詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity825 = listRoute82.get(5);
        assertEquals(426L, entity825.getTablleId(), "経路82詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity826 = listRoute82.get(6);
        assertEquals(526L, entity826.getTablleId(), "経路82詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity827 = listRoute82.get(7);
        assertEquals(626L, entity827.getTablleId(), "経路82詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity828 = listRoute82.get(8);
        assertEquals(32_107L, entity828.getTablleId(), "経路82詳細8");

        // 経路87(政治団体迂回1階層)
        SelectOptionDto dto87 = listOption.get(69);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute87 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto87.getValue()));
        assertEquals(2, listRoute87.size(), "経路87のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity870 = listRoute87.get(0);
        assertEquals(72L, entity870.getTablleId(), "経路87詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity871 = listRoute87.get(1);
        assertEquals(71L, entity871.getTablleId(), "経路87詳細1");

        // 経路88(政治団体迂回1階層)
        SelectOptionDto dto88 = listOption.get(70);
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

        // 経路89(政治団体迂回2階層)
        SelectOptionDto dto89 = listOption.get(71);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute89 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto89.getValue()));
        assertEquals(4, listRoute89.size(), "経路89のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity890 = listRoute89.get(0);
        assertEquals(31L, entity890.getTablleId(), "経路89詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity891 = listRoute89.get(1);
        assertEquals(30_003L, entity891.getTablleId(), "経路89詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity892 = listRoute89.get(2);
        assertEquals(121L, entity892.getTablleId(), "経路89詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity893 = listRoute89.get(3);
        assertEquals(30_004L, entity893.getTablleId(), "経路89詳細3");

        // 経路90(政治団体迂回3階層)
        SelectOptionDto dto90 = listOption.get(72);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute90 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto90.getValue()));
        assertEquals(5, listRoute90.size(), "経路90のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity900 = listRoute90.get(0);
        assertEquals(32L, entity900.getTablleId(), "経路90詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity901 = listRoute90.get(1);
        assertEquals(30_005L, entity901.getTablleId(), "経路90詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity902 = listRoute90.get(2);
        assertEquals(122L, entity902.getTablleId(), "経路14詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity903 = listRoute90.get(3);
        assertEquals(221L, entity903.getTablleId(), "経路90詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity904 = listRoute90.get(4);
        assertEquals(30_006L, entity904.getTablleId(), "経路90詳細4");

        // 経路91(政治団体迂回4階層)
        SelectOptionDto dto91 = listOption.get(73);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute91 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto91.getValue()));
        assertEquals(6, listRoute91.size(), "経路91のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity910 = listRoute91.get(0);
        assertEquals(33L, entity910.getTablleId(), "経路91詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity911 = listRoute91.get(1);
        assertEquals(30_007L, entity911.getTablleId(), "経路91詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity912 = listRoute91.get(2);
        assertEquals(123L, entity912.getTablleId(), "経路91詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity913 = listRoute91.get(3);
        assertEquals(222L, entity913.getTablleId(), "経路91詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity914 = listRoute91.get(4);
        assertEquals(321L, entity914.getTablleId(), "経路91詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity915 = listRoute91.get(5);
        assertEquals(30_008L, entity915.getTablleId(), "経路91詳細5");

        // 経路92(政治団体迂回5階層)
        SelectOptionDto dto92 = listOption.get(74);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute92 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto92.getValue()));
        assertEquals(7, listRoute92.size(), "経路92のデータ数は7");
        WkTblUkaiKenkinPickupRouteEntity entity920 = listRoute92.get(0);
        assertEquals(34L, entity920.getTablleId(), "経路92詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity921 = listRoute92.get(1);
        assertEquals(30_009L, entity921.getTablleId(), "経路92詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity922 = listRoute92.get(2);
        assertEquals(124L, entity922.getTablleId(), "経路92詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity923 = listRoute92.get(3);
        assertEquals(224L, entity923.getTablleId(), "経路92詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity924 = listRoute92.get(4);
        assertEquals(324L, entity924.getTablleId(), "経路92詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity925 = listRoute92.get(5);
        assertEquals(424L, entity925.getTablleId(), "経路92詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity926 = listRoute92.get(6);
        assertEquals(30_010L, entity926.getTablleId(), "経路92詳細6");

        // 経路93(政治団体迂回6階層)
        SelectOptionDto dto93 = listOption.get(75);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute93 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto93.getValue()));
        assertEquals(8, listRoute93.size(), "経路93のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity930 = listRoute93.get(0);
        assertEquals(35L, entity930.getTablleId(), "経路93詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity931 = listRoute93.get(1);
        assertEquals(30_011L, entity931.getTablleId(), "経路93詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity932 = listRoute93.get(2);
        assertEquals(125L, entity932.getTablleId(), "経路93詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity933 = listRoute93.get(3);
        assertEquals(225L, entity933.getTablleId(), "経93詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity934 = listRoute93.get(4);
        assertEquals(325L, entity934.getTablleId(), "経路93詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity935 = listRoute93.get(5);
        assertEquals(425L, entity935.getTablleId(), "経路93詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity936 = listRoute93.get(6);
        assertEquals(525L, entity936.getTablleId(), "経路17詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity937 = listRoute93.get(7);
        assertEquals(30_012L, entity937.getTablleId(), "経路17詳細7");

        // 経路94(政治団体迂回7階層)
        SelectOptionDto dto94 = listOption.get(76);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute94 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto94.getValue()));
        assertEquals(9, listRoute94.size(), "経路94のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity940 = listRoute94.get(0);
        assertEquals(36L, entity940.getTablleId(), "経路94詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity941 = listRoute94.get(1);
        assertEquals(30_013L, entity941.getTablleId(), "経路94詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity942 = listRoute94.get(2);
        assertEquals(126L, entity942.getTablleId(), "経路94詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity943 = listRoute94.get(3);
        assertEquals(226L, entity943.getTablleId(), "経路94詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity944 = listRoute94.get(4);
        assertEquals(326L, entity944.getTablleId(), "経路94詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity945 = listRoute94.get(5);
        assertEquals(426L, entity945.getTablleId(), "経路94詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity946 = listRoute94.get(6);
        assertEquals(526L, entity946.getTablleId(), "経路94詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity947 = listRoute94.get(7);
        assertEquals(626L, entity947.getTablleId(), "経路94詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity948 = listRoute94.get(8);
        assertEquals(30_014L, entity948.getTablleId(), "経路94詳細8");

    }

}
