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
 * PickupUkaiKenkinCorpTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupUkaiKenkinCorpTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PickupUkaiKenkinCorpTasklet pickupUkaiKenkinCorpTasklet;

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
        pickupUkaiKenkinCorpTasklet.beforeStep(execution);
        assertEquals(RepeatStatus.FINISHED, pickupUkaiKenkinCorpTasklet.execute(null, null), "実行");

        Integer userCode = privilegeDto.getLoginUserCode();

        // 全体を選択肢リスト形式で取得
        List<SelectOptionDto> listOption = createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
        listOption.remove(0); // 最初の1行は0階層(全)
        assertEquals(9, listOption.size(), "9経路取得できた");

        /* 企業迂回階層 */
        // 経路31(企業迂回1階層)
        SelectOptionDto dto31 = listOption.get(0);
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
        SelectOptionDto dto32 = listOption.get(1);
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
        SelectOptionDto dto33 = listOption.get(2);
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
        SelectOptionDto dto34 = listOption.get(3);
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
        SelectOptionDto dto35 = listOption.get(4);
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
        SelectOptionDto dto36 = listOption.get(5);
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
        SelectOptionDto dto37 = listOption.get(6);
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

        // 経路38(企業迂回8階層)
        SelectOptionDto dto38 = listOption.get(7);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute38 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto38.getValue()));
        assertEquals(10, listRoute38.size(), "経路38のデータ数は10");
        WkTblUkaiKenkinPickupRouteEntity entity380 = listRoute38.get(0);
        assertEquals(37L, entity380.getTablleId(), "経路38詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity381 = listRoute38.get(1);
        assertEquals(12_015L, entity381.getTablleId(), "経路38詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity382 = listRoute38.get(2);
        assertEquals(127L, entity382.getTablleId(), "経路38詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity383 = listRoute38.get(3);
        assertEquals(227L, entity383.getTablleId(), "経路38詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity384 = listRoute38.get(4);
        assertEquals(327L, entity384.getTablleId(), "経路38詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity385 = listRoute38.get(5);
        assertEquals(427L, entity385.getTablleId(), "経路38詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity386 = listRoute38.get(6);
        assertEquals(527L, entity386.getTablleId(), "経路38詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity387 = listRoute38.get(7);
        assertEquals(627L, entity387.getTablleId(), "経路38詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity388 = listRoute38.get(8);
        assertEquals(727L, entity388.getTablleId(), "経路38詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity389 = listRoute38.get(9);
        assertEquals(12_016L, entity389.getTablleId(), "経路38詳細9");

        // 経路39(企業迂回9階層)
        SelectOptionDto dto39 = listOption.get(8);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute39 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto39.getValue()));
        assertEquals(11, listRoute39.size(), "経路39のデータ数は11");
        WkTblUkaiKenkinPickupRouteEntity entity390 = listRoute39.get(0);
        assertEquals(38L, entity390.getTablleId(), "経路39細0");
        WkTblUkaiKenkinPickupRouteEntity entity391 = listRoute39.get(1);
        assertEquals(12_017L, entity391.getTablleId(), "経路39詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity392 = listRoute39.get(2);
        assertEquals(128L, entity392.getTablleId(), "経路39詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity393 = listRoute39.get(3);
        assertEquals(228L, entity393.getTablleId(), "経路39詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity394 = listRoute39.get(4);
        assertEquals(328L, entity394.getTablleId(), "経路11詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity395 = listRoute39.get(5);
        assertEquals(428L, entity395.getTablleId(), "経路39詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity396 = listRoute39.get(6);
        assertEquals(528L, entity396.getTablleId(), "経路39詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity397 = listRoute39.get(7);
        assertEquals(628L, entity397.getTablleId(), "経路39詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity398 = listRoute39.get(8);
        assertEquals(728L, entity398.getTablleId(), "経路39詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity399 = listRoute39.get(9);
        assertEquals(828L, entity399.getTablleId(), "経路39詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity39A = listRoute39.get(10);
        assertEquals(12_018L, entity39A.getTablleId(), "経路39詳細10");

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
}
