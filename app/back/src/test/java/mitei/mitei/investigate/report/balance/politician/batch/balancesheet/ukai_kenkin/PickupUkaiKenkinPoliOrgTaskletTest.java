package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.CreateUkaiKenkinRouteSelectOptionLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * PickupUkaiKenkinPersonAndCorpTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupUkaiKenkinPoliOrgTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PickupUkaiKenkinPoliOrgTasklet pickupUkaiKenkinPoliOrgTasklet;

    /** 作成データ抽出選択肢作成Logic */
    @Autowired
    private CreateUkaiKenkinRouteSelectOptionLogic createUkaiKenkinRouteSelectOptionLogic;

    /** 権限確認Dto */
    private final CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "truncate_wk_tbl_ukai_kenkin_route.sql", "configuration_wktbl_meisai.sql" })
    void test() throws Exception { // NOPMD

        StepExecution execution = this.getStepExecution();
        pickupUkaiKenkinPoliOrgTasklet.beforeStep(execution);
        assertEquals(RepeatStatus.FINISHED, pickupUkaiKenkinPoliOrgTasklet.execute(null, null), "実行");

        Integer userCode = privilegeDto.getLoginUserCode();

        // 全体を選択肢リスト形式で取得
        List<SelectOptionDto> listOption = createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
        listOption.remove(0); // 最初の1行は0階層(全)
        assertEquals(9, listOption.size(), "9経路取得できた");

        /* 政治団体迂回階層 */
        // 経路40(政治団体迂回1階層)
        SelectOptionDto dto40 = listOption.get(0);
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
        SelectOptionDto dto41 = listOption.get(1);
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
        SelectOptionDto dto42 = listOption.get(2);
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
        SelectOptionDto dto43 = listOption.get(3);
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
        SelectOptionDto dto44 = listOption.get(4);
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
        SelectOptionDto dto45 = listOption.get(5);
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
        SelectOptionDto dto46 = listOption.get(6);
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

        // 経路47(政治団体迂回8階層)
        SelectOptionDto dto47 = listOption.get(7);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute47 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto47.getValue()));
        assertEquals(10, listRoute47.size(), "経路47のデータ数は10");
        WkTblUkaiKenkinPickupRouteEntity entity470 = listRoute47.get(0);
        assertEquals(37L, entity470.getTablleId(), "経路47詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity471 = listRoute47.get(1);
        assertEquals(58L, entity471.getTablleId(), "経路47詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity472 = listRoute47.get(2);
        assertEquals(127L, entity472.getTablleId(), "経路47詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity473 = listRoute47.get(3);
        assertEquals(227L, entity473.getTablleId(), "経路47詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity474 = listRoute47.get(4);
        assertEquals(327L, entity474.getTablleId(), "経路47詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity475 = listRoute47.get(5);
        assertEquals(427L, entity475.getTablleId(), "経路47詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity476 = listRoute47.get(6);
        assertEquals(527L, entity476.getTablleId(), "経路47詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity477 = listRoute47.get(7);
        assertEquals(627L, entity477.getTablleId(), "経路47詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity478 = listRoute47.get(8);
        assertEquals(727L, entity478.getTablleId(), "経路47詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity479 = listRoute47.get(9);
        assertEquals(820L, entity479.getTablleId(), "経路47詳細9");

        // 経路48(政治団体迂回9階層)
        SelectOptionDto dto48 = listOption.get(8);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute48 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto48.getValue()));
        assertEquals(11, listRoute48.size(), "経路48のデータ数は11");
        WkTblUkaiKenkinPickupRouteEntity entity480 = listRoute48.get(0);
        assertEquals(38L, entity480.getTablleId(), "経路48細0");
        WkTblUkaiKenkinPickupRouteEntity entity481 = listRoute48.get(1);
        assertEquals(59L, entity481.getTablleId(), "経路48詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity482 = listRoute48.get(2);
        assertEquals(128L, entity482.getTablleId(), "経路48詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity483 = listRoute48.get(3);
        assertEquals(228L, entity483.getTablleId(), "経路48詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity484 = listRoute48.get(4);
        assertEquals(328L, entity484.getTablleId(), "経路48詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity485 = listRoute48.get(5);
        assertEquals(428L, entity485.getTablleId(), "経路48詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity486 = listRoute48.get(6);
        assertEquals(528L, entity486.getTablleId(), "経路48詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity487 = listRoute48.get(7);
        assertEquals(628L, entity487.getTablleId(), "経路48詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity488 = listRoute48.get(8);
        assertEquals(728L, entity488.getTablleId(), "経路48詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity489 = listRoute48.get(9);
        assertEquals(828L, entity489.getTablleId(), "経路48詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity48A = listRoute48.get(10);
        assertEquals(920L, entity48A.getTablleId(), "経路48詳細10");
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "truncate_wk_tbl_ukai_kenkin_route.sql", "configuration_wktbl_meisai_koufukin.sql" })
    void testKoufukin() throws Exception { // NOPMD

        StepExecution execution = this.getStepExecutionKoufukin();
        pickupUkaiKenkinPoliOrgTasklet.beforeStep(execution);
        assertEquals(RepeatStatus.FINISHED, pickupUkaiKenkinPoliOrgTasklet.execute(null, null), "実行");

        Integer userCode = privilegeDto.getLoginUserCode();

        // 全体を選択肢リスト形式で取得
        // テーブルId 30が交付金データに変更しているが、取得できるデータに変化はない
        List<SelectOptionDto> listOption = createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
        listOption.remove(0); // 最初の1行は0階層(全)
        assertEquals(9, listOption.size(), "9経路取得できた");

    }

    private StepExecution getStepExecution() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", privilegeDto.getLoginUserId())
                .addLong("userCode", Long.valueOf(privilegeDto.getLoginUserCode()))
                .addString("userName", privilegeDto.getLoginUserName()).addLong("poliOrgCode", Long.valueOf(100))
                .addString("isSearchKoufukin", Boolean.FALSE.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private StepExecution getStepExecutionKoufukin() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", privilegeDto.getLoginUserId())
                .addLong("userCode", Long.valueOf(privilegeDto.getLoginUserCode()))
                .addString("userName", privilegeDto.getLoginUserName()).addLong("poliOrgCode", Long.valueOf(100))
                .addString("isSearchKoufukin", Boolean.TRUE.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
