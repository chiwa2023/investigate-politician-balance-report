package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;


import static org.assertj.core.api.Assertions.fail;
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

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.CreateUkaiKenkinRouteSelectOptionLogic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * PickupUkaiKenkinStageZeroTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupUkaiKenkinStageZeroTaskletTest {

    /** テスト対象 */
    @Autowired
    private PickupUkaiKenkinStageZeroTasklet pickupUkaiKenkinStageZeroTasklet;

    /** 権限確認Dto */
    private final CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

    /** 迂回献金経路Repository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /** 作成データ抽出選択肢作成Logic */
    @Autowired
    private CreateUkaiKenkinRouteSelectOptionLogic createUkaiKenkinRouteSelectOptionLogic;

    @Test
    @Tag("TableTruncate")
    @Sql({ "configuration_income_2022.sql", "configuration_poli_org_property.sql",
            "configuration_wktbl_meisai_clean.sql", "configuration_wktbl_route.sql" })
    void test() throws Exception{
        
        StepExecution execution = this.getStepExecution();
        pickupUkaiKenkinStageZeroTasklet.beforeStep(execution);
        assertEquals(RepeatStatus.FINISHED, pickupUkaiKenkinStageZeroTasklet.execute(null, null), "実行");

        Integer userCode = privilegeDto.getLoginUserCode();

        // 全体を選択肢リスト形式で取得
        List<SelectOptionDto> listOption = createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
        listOption.remove(0); // 最初の1行は0階層(全)
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

        
        fail("Not yet implemented");
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
