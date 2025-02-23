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
class PickupUkaiKenkinPersonAndCorpTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PickupUkaiKenkinPersonAndCorpTasklet pickupUkaiKenkinPersonAndCorpTasklet;
    
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
    @Sql({ "truncate_wk_tbl_ukai_kenkin_route.sql", "configuration_income_2022.sql", "configuration_wktbl_meisai.sql" })
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        pickupUkaiKenkinPersonAndCorpTasklet.beforeStep(execution);
        assertEquals(RepeatStatus.FINISHED, pickupUkaiKenkinPersonAndCorpTasklet.execute(null, null), "実行");
        
        Integer userCode = privilegeDto.getLoginUserCode();

        // 全体を選択肢リスト形式で取得
        List<SelectOptionDto> listOption = createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
        assertEquals(8, listOption.size(), "8経路取得できた");

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
