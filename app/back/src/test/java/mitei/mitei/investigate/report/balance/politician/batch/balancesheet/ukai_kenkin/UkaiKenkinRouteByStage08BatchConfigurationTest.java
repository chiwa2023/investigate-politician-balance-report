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
 * UkaiKenkinRouteByStage08BatchConfiguration単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UkaiKenkinRouteByStage08BatchConfigurationTest {
    // CHECKSTYLE:OFF

    /** テストユーティリティ */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    /** 起動をするJob */
    @Qualifier(UkaiKenkinRouteByStage08BatchConfiguration.JOB_NAME)
    @Autowired
    private Job ukaiKenkinRouteByStage08;

    /** 作成データ抽出選択肢作成Logic */
    @Autowired
    private CreateUkaiKenkinRouteSelectOptionLogic createUkaiKenkinRouteSelectOptionLogic;

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    @Test
    @Tag("TableTruncate")
    void testJob() {
        assertEquals(UkaiKenkinRouteByStage08BatchConfiguration.JOB_NAME, ukaiKenkinRouteByStage08.getName(),
                "Job名が一致");
    }

    @Test
    @Tag("TableTruncate")
    // @Transactional
    @Sql({ "configuration_income_2022.sql", "configuration_poli_org_property.sql",
            "configuration_wktbl_meisai_clean.sql", "configuration_wktbl_route.sql" })
    void test() throws Exception { // NOPMD

        jobLauncherTestUtils.setJob(ukaiKenkinRouteByStage08);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        Integer userCode = privilegeDto.getLoginUserCode();

        JobParameters jobParameters = new JobParametersBuilder(
                ukaiKenkinRouteByStage08.getJobParametersIncrementer().getNext(new JobParameters())) // NOPMD
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
        assertEquals(18, listOption.size(), "18経路取得できた");

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

        // 経路9
        SelectOptionDto dto08 = listOption.get(8);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute8 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto08.getValue()));
        assertEquals(1, listRoute8.size(), "経路9のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity80 = listRoute8.get(0);
        assertEquals(21L, entity80.getTablleId(), "経路9詳細1");

        // 経路10
        SelectOptionDto dto09 = listOption.get(9);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute9 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto09.getValue()));
        assertEquals(1, listRoute9.size(), "経路10のデータ数は1");
        WkTblUkaiKenkinPickupRouteEntity entity90 = listRoute9.get(0);
        assertEquals(22L, entity90.getTablleId(), "経路10詳細1");

        /* ここから政治団体(政治団体階層1以上) */

        // 経路11(政治団体1)
        SelectOptionDto dto10 = listOption.get(10);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute10 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto10.getValue()));
        assertEquals(3, listRoute10.size(), "経路11のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity100 = listRoute10.get(0);
        assertEquals(30L, entity100.getTablleId(), "経路11詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity101 = listRoute10.get(1);
        assertEquals(51L, entity101.getTablleId(), "経路11詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity102 = listRoute10.get(2);
        assertEquals(120L, entity102.getTablleId(), "経路11詳細3");

        // 経路12(政治団体2)
        SelectOptionDto dto11 = listOption.get(11);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute11 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto11.getValue()));
        assertEquals(4, listRoute11.size(), "経路12のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity110 = listRoute11.get(0);
        assertEquals(31L, entity110.getTablleId(), "経路12詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity111 = listRoute11.get(1);
        assertEquals(52L, entity111.getTablleId(), "経路12詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity112 = listRoute11.get(2);
        assertEquals(121L, entity112.getTablleId(), "経路12詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity113 = listRoute11.get(3);
        assertEquals(220L, entity113.getTablleId(), "経路12詳細4");

        // 経路13(政治団体3)
        SelectOptionDto dto12 = listOption.get(12);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute12 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto12.getValue()));
        assertEquals(5, listRoute12.size(), "経路13のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity120 = listRoute12.get(0);
        assertEquals(32L, entity120.getTablleId(), "経路13詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity121 = listRoute12.get(1);
        assertEquals(53L, entity121.getTablleId(), "経路13詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity122 = listRoute12.get(2);
        assertEquals(122L, entity122.getTablleId(), "経路13詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity123 = listRoute12.get(3);
        assertEquals(221L, entity123.getTablleId(), "経路13詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity124 = listRoute12.get(4);
        assertEquals(320L, entity124.getTablleId(), "経路13詳細4");

        // 経路14(政治団体4)
        SelectOptionDto dto13 = listOption.get(13);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute13 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto13.getValue()));
        assertEquals(6, listRoute13.size(), "経路14のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity130 = listRoute13.get(0);
        assertEquals(33L, entity130.getTablleId(), "経路14詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity131 = listRoute13.get(1);
        assertEquals(54L, entity131.getTablleId(), "経路14詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity132 = listRoute13.get(2);
        assertEquals(123L, entity132.getTablleId(), "経路14詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity133 = listRoute13.get(3);
        assertEquals(222L, entity133.getTablleId(), "経路14詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity134 = listRoute13.get(4);
        assertEquals(321L, entity134.getTablleId(), "経路14詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity135 = listRoute13.get(5);
        assertEquals(420L, entity135.getTablleId(), "経路14詳細6");

        // 経路15(政治団体5)
        SelectOptionDto dto14 = listOption.get(14);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute14 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto14.getValue()));
        assertEquals(7, listRoute14.size(), "経路15のデータ数は6");
        WkTblUkaiKenkinPickupRouteEntity entity140 = listRoute14.get(0);
        assertEquals(34L, entity140.getTablleId(), "経路15詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity141 = listRoute14.get(1);
        assertEquals(55L, entity141.getTablleId(), "経路15詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity142 = listRoute14.get(2);
        assertEquals(124L, entity142.getTablleId(), "経路15詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity143 = listRoute14.get(3);
        assertEquals(224L, entity143.getTablleId(), "経路15詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity144 = listRoute14.get(4);
        assertEquals(324L, entity144.getTablleId(), "経路15詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity145 = listRoute14.get(5);
        assertEquals(424L, entity145.getTablleId(), "経路15詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity146 = listRoute14.get(6);
        assertEquals(520L, entity146.getTablleId(), "経路15詳細5");

        // 経路16(政治団体6)
        SelectOptionDto dto15 = listOption.get(15);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute15 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto15.getValue()));
        assertEquals(8, listRoute15.size(), "経路16のデータ数は8");
        WkTblUkaiKenkinPickupRouteEntity entity150 = listRoute15.get(0);
        assertEquals(35L, entity150.getTablleId(), "経路16詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity151 = listRoute15.get(1);
        assertEquals(56L, entity151.getTablleId(), "経路16詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity152 = listRoute15.get(2);
        assertEquals(125L, entity152.getTablleId(), "経路16詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity153 = listRoute15.get(3);
        assertEquals(225L, entity153.getTablleId(), "経路16詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity154 = listRoute15.get(4);
        assertEquals(325L, entity154.getTablleId(), "経路16詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity155 = listRoute15.get(5);
        assertEquals(425L, entity155.getTablleId(), "経路16詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity156 = listRoute15.get(6);
        assertEquals(525L, entity156.getTablleId(), "経路16詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity157 = listRoute15.get(7);
        assertEquals(620L, entity157.getTablleId(), "経路16詳細7");

        // 経路17(政治団体7)
        SelectOptionDto dto16 = listOption.get(16);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute16 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto16.getValue()));
        assertEquals(9, listRoute16.size(), "経路17のデータ数は9");
        WkTblUkaiKenkinPickupRouteEntity entity160 = listRoute16.get(0);
        assertEquals(36L, entity160.getTablleId(), "経路17詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity161 = listRoute16.get(1);
        assertEquals(57L, entity161.getTablleId(), "経路17詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity162 = listRoute16.get(2);
        assertEquals(126L, entity162.getTablleId(), "経路17詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity163 = listRoute16.get(3);
        assertEquals(226L, entity163.getTablleId(), "経路17詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity164 = listRoute16.get(4);
        assertEquals(326L, entity164.getTablleId(), "経路17詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity165 = listRoute16.get(5);
        assertEquals(426L, entity165.getTablleId(), "経路17詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity166 = listRoute16.get(6);
        assertEquals(526L, entity166.getTablleId(), "経路17詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity167 = listRoute16.get(7);
        assertEquals(626L, entity167.getTablleId(), "経路17詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity168 = listRoute16.get(8);
        assertEquals(720L, entity168.getTablleId(), "経路17詳細8");

        // 経路18(政治団体8)
        SelectOptionDto dto17 = listOption.get(17);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute17 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto17.getValue()));
        assertEquals(10, listRoute17.size(), "経路18のデータ数は10");
        WkTblUkaiKenkinPickupRouteEntity entity170 = listRoute17.get(0);
        assertEquals(37L, entity170.getTablleId(), "経路18詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity171 = listRoute17.get(1);
        assertEquals(58L, entity171.getTablleId(), "経路18詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity172 = listRoute17.get(2);
        assertEquals(127L, entity172.getTablleId(), "経路18詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity173 = listRoute17.get(3);
        assertEquals(227L, entity173.getTablleId(), "経路18詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity174 = listRoute17.get(4);
        assertEquals(327L, entity174.getTablleId(), "経路18詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity175 = listRoute17.get(5);
        assertEquals(427L, entity175.getTablleId(), "経路18詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity176 = listRoute17.get(6);
        assertEquals(527L, entity176.getTablleId(), "経路18詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity177 = listRoute17.get(7);
        assertEquals(627L, entity177.getTablleId(), "経路18詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity178 = listRoute17.get(8);
        assertEquals(727L, entity178.getTablleId(), "経路18詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity179 = listRoute17.get(9);
        assertEquals(820L, entity179.getTablleId(), "経路18詳細9");

    }

}
