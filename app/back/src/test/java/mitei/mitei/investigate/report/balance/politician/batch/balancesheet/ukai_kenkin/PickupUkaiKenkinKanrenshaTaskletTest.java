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
 * PickupUkaiKenkinKanrenshaTasklet単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PickupUkaiKenkinKanrenshaTaskletTest {

    /** テスト対象 */
    @Autowired
    private PickupUkaiKenkinKanrenshaTasklet pickupUkaiKenkinKanrenshaTasklet;

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
        // CHECKSTYLE:OFF

        StepExecution execution = this.getStepExecution();
        pickupUkaiKenkinKanrenshaTasklet.beforeStep(execution);
        assertEquals(RepeatStatus.FINISHED, pickupUkaiKenkinKanrenshaTasklet.execute(null, null), "実行");

        Integer userCode = privilegeDto.getLoginUserCode();

        // 全体を選択肢リスト形式で取得
        List<SelectOptionDto> listOption = createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
        listOption.remove(0); // 最初の1行は0階層(全)
        assertEquals(48, listOption.size(), "48経路取得できた");

        // 経路49(0階層企業代表者が政治団体関連者と一致)
        SelectOptionDto dto49 = listOption.get(0);
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
        SelectOptionDto dto50 = listOption.get(1);
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
        SelectOptionDto dto51 = listOption.get(2);
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
        SelectOptionDto dto52 = listOption.get(3);
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
        SelectOptionDto dto53 = listOption.get(4);
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
        SelectOptionDto dto54 = listOption.get(5);
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
        SelectOptionDto dto55 = listOption.get(6);
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
        SelectOptionDto dto56 = listOption.get(7);
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
        SelectOptionDto dto57 = listOption.get(8);
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
        SelectOptionDto dto58 = listOption.get(9);
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

        SelectOptionDto dto59 = listOption.get(10);
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
        SelectOptionDto dto60 = listOption.get(11);
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
        SelectOptionDto dto61 = listOption.get(12);
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
        SelectOptionDto dto62 = listOption.get(13);
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
        SelectOptionDto dto63 = listOption.get(14);
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

        // 経路64(8階層個人から企業代表者)
        SelectOptionDto dto64 = listOption.get(15);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute64 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto64.getValue()));
        assertEquals(10, listRoute64.size(), "経路64のデータ数は64");
        WkTblUkaiKenkinPickupRouteEntity entity640 = listRoute64.get(0);
        assertEquals(37L, entity640.getTablleId(), "経路64詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity641 = listRoute64.get(1);
        assertEquals(12L, entity641.getTablleId(), "経路64詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity642 = listRoute64.get(2);
        assertEquals(127L, entity642.getTablleId(), "経路64詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity643 = listRoute64.get(3);
        assertEquals(227L, entity643.getTablleId(), "経路64詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity644 = listRoute64.get(4);
        assertEquals(327L, entity644.getTablleId(), "経路64詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity645 = listRoute64.get(5);
        assertEquals(427L, entity645.getTablleId(), "経路64詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity694 = listRoute64.get(6);
        assertEquals(527L, entity694.getTablleId(), "経路64詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity647 = listRoute64.get(7);
        assertEquals(627L, entity647.getTablleId(), "経路64詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity648 = listRoute64.get(8);
        assertEquals(727L, entity648.getTablleId(), "経路64詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity649 = listRoute64.get(9);
        assertEquals(33_108L, entity649.getTablleId(), "経路64詳細9");

        // 経路65(8階層個人から政治団体関連者)
        SelectOptionDto dto65 = listOption.get(16);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute65 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto65.getValue()));
        assertEquals(10, listRoute65.size(), "経路65のデータ数は10");
        WkTblUkaiKenkinPickupRouteEntity entity650 = listRoute65.get(0);
        assertEquals(37L, entity650.getTablleId(), "経路65詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity651 = listRoute65.get(1);
        assertEquals(12L, entity651.getTablleId(), "経路65詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity652 = listRoute65.get(2);
        assertEquals(127L, entity652.getTablleId(), "経路65詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity653 = listRoute65.get(3);
        assertEquals(227L, entity653.getTablleId(), "経路65詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity654 = listRoute65.get(4);
        assertEquals(327L, entity654.getTablleId(), "経路65詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity655 = listRoute65.get(5);
        assertEquals(427L, entity655.getTablleId(), "経路65詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity656 = listRoute65.get(6);
        assertEquals(527L, entity656.getTablleId(), "経路65詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity657 = listRoute65.get(7);
        assertEquals(627L, entity657.getTablleId(), "経路65詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity658 = listRoute65.get(8);
        assertEquals(727L, entity658.getTablleId(), "経路65詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity659 = listRoute65.get(9);
        assertEquals(34_108L, entity659.getTablleId(), "経路65詳細9");

        // 経路66(9階層個人から企業代表者)
        SelectOptionDto dto66 = listOption.get(17);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute66 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto66.getValue()));
        assertEquals(11, listRoute66.size(), "経路66のデータ数は11");
        WkTblUkaiKenkinPickupRouteEntity entity660 = listRoute66.get(0);
        assertEquals(38L, entity660.getTablleId(), "経路66細0");
        WkTblUkaiKenkinPickupRouteEntity entity661 = listRoute66.get(1);
        assertEquals(12L, entity661.getTablleId(), "経路66詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity662 = listRoute66.get(2);
        assertEquals(128L, entity662.getTablleId(), "経路66詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity663 = listRoute66.get(3);
        assertEquals(228L, entity663.getTablleId(), "経路66詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity664 = listRoute66.get(4);
        assertEquals(328L, entity664.getTablleId(), "経路66詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity665 = listRoute66.get(5);
        assertEquals(428L, entity665.getTablleId(), "経路66詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity666 = listRoute66.get(6);
        assertEquals(528L, entity666.getTablleId(), "経路66詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity667 = listRoute66.get(7);
        assertEquals(628L, entity667.getTablleId(), "経路66詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity668 = listRoute66.get(8);
        assertEquals(728L, entity668.getTablleId(), "経路66詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity669 = listRoute66.get(9);
        assertEquals(828L, entity669.getTablleId(), "経路66詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity66A = listRoute66.get(10);
        assertEquals(33_109L, entity66A.getTablleId(), "経路66詳細10");

        // 経路67(9階層個人から政治団体関連者)
        SelectOptionDto dto67 = listOption.get(18);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute67 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto67.getValue()));
        assertEquals(11, listRoute67.size(), "経路67のデータ数は11");
        WkTblUkaiKenkinPickupRouteEntity entity670 = listRoute67.get(0);
        assertEquals(38L, entity670.getTablleId(), "経路67細0");
        WkTblUkaiKenkinPickupRouteEntity entity671 = listRoute67.get(1);
        assertEquals(12L, entity671.getTablleId(), "経路67詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity672 = listRoute67.get(2);
        assertEquals(128L, entity672.getTablleId(), "経路67詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity673 = listRoute67.get(3);
        assertEquals(228L, entity673.getTablleId(), "経路67詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity674 = listRoute67.get(4);
        assertEquals(328L, entity674.getTablleId(), "経路67詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity675 = listRoute67.get(5);
        assertEquals(428L, entity675.getTablleId(), "経路67詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity676 = listRoute67.get(6);
        assertEquals(528L, entity676.getTablleId(), "経路67詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity677 = listRoute67.get(7);
        assertEquals(628L, entity677.getTablleId(), "経路67詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity678 = listRoute67.get(8);
        assertEquals(728L, entity678.getTablleId(), "経路67詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity679 = listRoute67.get(9);
        assertEquals(828L, entity679.getTablleId(), "経路67詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity67A = listRoute67.get(10);
        assertEquals(34_109L, entity67A.getTablleId(), "経路67詳細10");

        /* 企業代表者迂回階層 */
        // 経路68(個人迂回1階層)
        SelectOptionDto dto68 = listOption.get(19);
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
        SelectOptionDto dto69 = listOption.get(20);
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
        SelectOptionDto dto70 = listOption.get(21);
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
        SelectOptionDto dto71 = listOption.get(22);
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
        SelectOptionDto dto72 = listOption.get(23);
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
        SelectOptionDto dto73 = listOption.get(24);
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
        SelectOptionDto dto74 = listOption.get(25);
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
        SelectOptionDto dto75 = listOption.get(26);
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
        SelectOptionDto dto76 = listOption.get(27);
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
        SelectOptionDto dto77 = listOption.get(28);
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

        SelectOptionDto dto78 = listOption.get(29);
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
        SelectOptionDto dto79 = listOption.get(30);
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
        SelectOptionDto dto80 = listOption.get(31);
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
        SelectOptionDto dto81 = listOption.get(32);
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
        SelectOptionDto dto82 = listOption.get(33);
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

        // 経路83(8階層個人から企業代表者)
        SelectOptionDto dto83 = listOption.get(34);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute83 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto83.getValue()));
        assertEquals(10, listRoute83.size(), "経路83のデータ数は83");
        WkTblUkaiKenkinPickupRouteEntity entity830 = listRoute83.get(0);
        assertEquals(37L, entity830.getTablleId(), "経路83詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity831 = listRoute83.get(1);
        assertEquals(61L, entity831.getTablleId(), "経路83詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity832 = listRoute83.get(2);
        assertEquals(127L, entity832.getTablleId(), "経路83詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity833 = listRoute83.get(3);
        assertEquals(227L, entity833.getTablleId(), "経路83詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity834 = listRoute83.get(4);
        assertEquals(327L, entity834.getTablleId(), "経路83詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity835 = listRoute83.get(5);
        assertEquals(427L, entity835.getTablleId(), "経路83詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity836 = listRoute83.get(6);
        assertEquals(527L, entity836.getTablleId(), "経路83詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity837 = listRoute83.get(7);
        assertEquals(627L, entity837.getTablleId(), "経路83詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity838 = listRoute83.get(8);
        assertEquals(727L, entity838.getTablleId(), "経路83詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity839 = listRoute83.get(9);
        assertEquals(31_108L, entity839.getTablleId(), "経路83詳細9");

        // 経路84(8階層個人から政治団体関連者)
        SelectOptionDto dto84 = listOption.get(35);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute84 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto84.getValue()));
        assertEquals(10, listRoute84.size(), "経路84のデータ数は10");
        WkTblUkaiKenkinPickupRouteEntity entity840 = listRoute84.get(0);
        assertEquals(37L, entity840.getTablleId(), "経路84詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity841 = listRoute84.get(1);
        assertEquals(61L, entity841.getTablleId(), "経路84詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity842 = listRoute84.get(2);
        assertEquals(127L, entity842.getTablleId(), "経路84詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity843 = listRoute84.get(3);
        assertEquals(227L, entity843.getTablleId(), "経路84詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity844 = listRoute84.get(4);
        assertEquals(327L, entity844.getTablleId(), "経路84詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity845 = listRoute84.get(5);
        assertEquals(427L, entity845.getTablleId(), "経路84詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity894 = listRoute84.get(6);
        assertEquals(527L, entity894.getTablleId(), "経路84詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity847 = listRoute84.get(7);
        assertEquals(627L, entity847.getTablleId(), "経路84詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity848 = listRoute84.get(8);
        assertEquals(727L, entity848.getTablleId(), "経路84詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity849 = listRoute84.get(9);
        assertEquals(32_108L, entity849.getTablleId(), "経路84詳細9");

        // 経路85(9階層個人から企業代表者)
        SelectOptionDto dto85 = listOption.get(36);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute85 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto85.getValue()));
        assertEquals(11, listRoute85.size(), "経路85のデータ数は11");
        WkTblUkaiKenkinPickupRouteEntity entity850 = listRoute85.get(0);
        assertEquals(38L, entity850.getTablleId(), "経路85細0");
        WkTblUkaiKenkinPickupRouteEntity entity851 = listRoute85.get(1);
        assertEquals(61L, entity851.getTablleId(), "経路85詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity852 = listRoute85.get(2);
        assertEquals(128L, entity852.getTablleId(), "経路85詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity853 = listRoute85.get(3);
        assertEquals(228L, entity853.getTablleId(), "経路85詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity854 = listRoute85.get(4);
        assertEquals(328L, entity854.getTablleId(), "経路85詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity855 = listRoute85.get(5);
        assertEquals(428L, entity855.getTablleId(), "経路85詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity856 = listRoute85.get(6);
        assertEquals(528L, entity856.getTablleId(), "経路85詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity857 = listRoute85.get(7);
        assertEquals(628L, entity857.getTablleId(), "経路85詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity858 = listRoute85.get(8);
        assertEquals(728L, entity858.getTablleId(), "経路85詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity859 = listRoute85.get(9);
        assertEquals(828L, entity859.getTablleId(), "経路85詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity85A = listRoute85.get(10);
        assertEquals(31_109L, entity85A.getTablleId(), "経路85詳細10");

        // 経路86(9階層個人から政治団体関連者)
        SelectOptionDto dto86 = listOption.get(37);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute86 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto86.getValue()));
        assertEquals(11, listRoute86.size(), "経路86のデータ数は11");
        WkTblUkaiKenkinPickupRouteEntity entity860 = listRoute86.get(0);
        assertEquals(38L, entity860.getTablleId(), "経路86細0");
        WkTblUkaiKenkinPickupRouteEntity entity861 = listRoute86.get(1);
        assertEquals(61L, entity861.getTablleId(), "経路86詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity862 = listRoute86.get(2);
        assertEquals(128L, entity862.getTablleId(), "経路86詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity863 = listRoute86.get(3);
        assertEquals(228L, entity863.getTablleId(), "経路86詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity864 = listRoute86.get(4);
        assertEquals(328L, entity864.getTablleId(), "経路86詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity865 = listRoute86.get(5);
        assertEquals(428L, entity865.getTablleId(), "経路86詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity866 = listRoute86.get(6);
        assertEquals(528L, entity866.getTablleId(), "経路86詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity867 = listRoute86.get(7);
        assertEquals(628L, entity867.getTablleId(), "経路86詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity868 = listRoute86.get(8);
        assertEquals(728L, entity868.getTablleId(), "経路86詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity869 = listRoute86.get(9);
        assertEquals(828L, entity869.getTablleId(), "経路86詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity86A = listRoute86.get(10);
        assertEquals(32_109L, entity86A.getTablleId(), "経路86詳細10");

        // 経路87(政治団体迂回1階層)
        SelectOptionDto dto87 = listOption.get(38);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute87 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto87.getValue()));
        assertEquals(2, listRoute87.size(), "経路87のデータ数は3");
        WkTblUkaiKenkinPickupRouteEntity entity870 = listRoute87.get(0);
        assertEquals(72L, entity870.getTablleId(), "経路87詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity871 = listRoute87.get(1);
        assertEquals(71L, entity871.getTablleId(), "経路87詳細1");

        // 経路88(政治団体迂回1階層)
        SelectOptionDto dto88 = listOption.get(39);
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
        SelectOptionDto dto89 = listOption.get(40);
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
        SelectOptionDto dto90 = listOption.get(41);
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
        SelectOptionDto dto91 = listOption.get(42);
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
        SelectOptionDto dto92 = listOption.get(43);
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
        SelectOptionDto dto93 = listOption.get(44);
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
        SelectOptionDto dto94 = listOption.get(45);
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

        // 経路95(政治団体迂回8階層)
        SelectOptionDto dto95 = listOption.get(46);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute95 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto95.getValue()));
        assertEquals(10, listRoute95.size(), "経路95のデータ数は10");
        WkTblUkaiKenkinPickupRouteEntity entity950 = listRoute95.get(0);
        assertEquals(37L, entity950.getTablleId(), "経路95詳細0");
        WkTblUkaiKenkinPickupRouteEntity entity951 = listRoute95.get(1);
        assertEquals(30_015L, entity951.getTablleId(), "経路95詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity952 = listRoute95.get(2);
        assertEquals(127L, entity952.getTablleId(), "経路95詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity953 = listRoute95.get(3);
        assertEquals(227L, entity953.getTablleId(), "経路95詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity954 = listRoute95.get(4);
        assertEquals(327L, entity954.getTablleId(), "経路95詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity955 = listRoute95.get(5);
        assertEquals(427L, entity955.getTablleId(), "経路95詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity956 = listRoute95.get(6);
        assertEquals(527L, entity956.getTablleId(), "経路95詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity957 = listRoute95.get(7);
        assertEquals(627L, entity957.getTablleId(), "経路95詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity958 = listRoute95.get(8);
        assertEquals(727L, entity958.getTablleId(), "経路95詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity959 = listRoute95.get(9);
        assertEquals(30_016L, entity959.getTablleId(), "経路95詳細9");

        // 経路96(政治団体迂回9階層)
        SelectOptionDto dto96 = listOption.get(47);
        List<WkTblUkaiKenkinPickupRouteEntity> listRoute96 = wkTblUkaiKenkinPickupRouteRepository
                .findByInsertUserCodeAndWkTblUkaiKenkinPickupRouteCodeOrderByPickupStageAsc(userCode,
                        Integer.parseInt(dto96.getValue()));
        assertEquals(11, listRoute96.size(), "経路96のデータ数は11");
        WkTblUkaiKenkinPickupRouteEntity entity960 = listRoute96.get(0);
        assertEquals(38L, entity960.getTablleId(), "経路96細0");
        WkTblUkaiKenkinPickupRouteEntity entity961 = listRoute96.get(1);
        assertEquals(30_017L, entity961.getTablleId(), "経路96詳細1");
        WkTblUkaiKenkinPickupRouteEntity entity962 = listRoute96.get(2);
        assertEquals(128L, entity962.getTablleId(), "経路96詳細2");
        WkTblUkaiKenkinPickupRouteEntity entity963 = listRoute96.get(3);
        assertEquals(228L, entity963.getTablleId(), "経路96詳細3");
        WkTblUkaiKenkinPickupRouteEntity entity964 = listRoute96.get(4);
        assertEquals(328L, entity964.getTablleId(), "経路96詳細4");
        WkTblUkaiKenkinPickupRouteEntity entity965 = listRoute96.get(5);
        assertEquals(428L, entity965.getTablleId(), "経路96詳細5");
        WkTblUkaiKenkinPickupRouteEntity entity966 = listRoute96.get(6);
        assertEquals(528L, entity966.getTablleId(), "経路96詳細6");
        WkTblUkaiKenkinPickupRouteEntity entity967 = listRoute96.get(7);
        assertEquals(628L, entity967.getTablleId(), "経路96詳細7");
        WkTblUkaiKenkinPickupRouteEntity entity968 = listRoute96.get(8);
        assertEquals(728L, entity968.getTablleId(), "経路96詳細8");
        WkTblUkaiKenkinPickupRouteEntity entity969 = listRoute96.get(9);
        assertEquals(828L, entity969.getTablleId(), "経路96詳細9");
        WkTblUkaiKenkinPickupRouteEntity entity96A = listRoute96.get(10);
        assertEquals(30_018L, entity96A.getTablleId(), "経路96詳細10");

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
