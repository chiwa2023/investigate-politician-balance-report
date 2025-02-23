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
    @Sql({ "truncate_wk_tbl_ukai_kenkin_route.sql", "configuration_income_2022.sql", "configuration_wktbl_meisai.sql" })
    void test() throws Exception { // NOPMD

        StepExecution execution = this.getStepExecution();
        pickupUkaiKenkinPoliOrgTasklet.beforeStep(execution);
        assertEquals(RepeatStatus.FINISHED, pickupUkaiKenkinPoliOrgTasklet.execute(null, null), "実行");

        Integer userCode = privilegeDto.getLoginUserCode();

        // 全体を選択肢リスト形式で取得
        List<SelectOptionDto> listOption = createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
        assertEquals(5, listOption.size(), "5経路取得できた");

        // 経路1
        SelectOptionDto dto00 = listOption.get(0);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute0 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto00.getValue()));
        assertEquals(2, listRoute0.size(), "経路1のデータ数は2");
        WkTblUkaiKenkinPickupRouteEntity entity00 = listRoute0.get(0);
        assertEquals(30L, entity00.getTablleId(), "経路1詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity01 = listRoute0.get(1);
        assertEquals(120L, entity01.getTablleId(), "経路1詳細2");

        // 経路2
        SelectOptionDto dto01 = listOption.get(1);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute1 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto01.getValue()));
        assertEquals(3, listRoute1.size(), "経路2のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity10 = listRoute1.get(0);
        assertEquals(31L, entity10.getTablleId(), "経路2詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity11 = listRoute1.get(1);
        assertEquals(121L, entity11.getTablleId(), "経路2詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity12 = listRoute1.get(2);
        assertEquals(220L, entity12.getTablleId(), "経路2詳細3");

        // 経路3
        SelectOptionDto dto02 = listOption.get(2);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute2 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto02.getValue()));
        assertEquals(4, listRoute2.size(), "経路3のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity20 = listRoute2.get(0);
        assertEquals(32L, entity20.getTablleId(), "経路3詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity21 = listRoute2.get(1);
        assertEquals(122L, entity21.getTablleId(), "経路3詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity22 = listRoute2.get(2);
        assertEquals(221L, entity22.getTablleId(), "経路3詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity23 = listRoute2.get(3);
        assertEquals(320L, entity23.getTablleId(), "経路3詳細4");

        // 経路4
        SelectOptionDto dto03 = listOption.get(3);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute3 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto03.getValue()));
        assertEquals(5, listRoute3.size(), "経路4のデータ数は4");
        WkTblUkaiKenkinPickupRouteEntity entity30 = listRoute3.get(0);
        assertEquals(33L, entity30.getTablleId(), "経路4詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity31 = listRoute3.get(1);
        assertEquals(123L, entity31.getTablleId(), "経路4詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity32 = listRoute3.get(2);
        assertEquals(222L, entity32.getTablleId(), "経路4詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity33 = listRoute3.get(3);
        assertEquals(321L, entity33.getTablleId(), "経路4詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity34 = listRoute3.get(4);
        assertEquals(420L, entity34.getTablleId(), "経路4詳細5");

        // 経路5
        SelectOptionDto dto04 = listOption.get(4);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute4 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto04.getValue()));
        assertEquals(6, listRoute4.size(), "経路5のデータ数は5");
        WkTblUkaiKenkinPickupRouteEntity entity40 = listRoute4.get(0);
        assertEquals(34L, entity40.getTablleId(), "経路5詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity41 = listRoute4.get(1);
        assertEquals(124L, entity41.getTablleId(), "経路5詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity42 = listRoute4.get(2);
        assertEquals(224L, entity42.getTablleId(), "経路5詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity43 = listRoute4.get(3);
        assertEquals(324L, entity43.getTablleId(), "経路5詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity44 = listRoute4.get(4);
        assertEquals(424L, entity44.getTablleId(), "経路5詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity45 = listRoute4.get(5);
        assertEquals(520L, entity45.getTablleId(), "経路5詳細5");
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
