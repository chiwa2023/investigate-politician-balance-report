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
 * PickupUkaiKenkinStageZeroTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupUkaiKenkinStageZeroTaskletTest {
    // CHECKSTYLE:OFF

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
    @Transactional
    @Sql({ "truncate_wk_tbl_ukai_kenkin_route.sql", "configuration_wktbl_meisai.sql" })
    void test() throws Exception {

        StepExecution execution = this.getStepExecution();
        pickupUkaiKenkinStageZeroTasklet.beforeStep(execution);
        assertEquals(RepeatStatus.FINISHED, pickupUkaiKenkinStageZeroTasklet.execute(null, null), "実行");

        Integer userCode = privilegeDto.getLoginUserCode();

        // 全体を選択肢リスト形式で取得
        List<SelectOptionDto> listOption = createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
        listOption.remove(0); // 最初の1行は0階層(全)
        assertEquals(4, listOption.size(), "4経路取得できた");

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
