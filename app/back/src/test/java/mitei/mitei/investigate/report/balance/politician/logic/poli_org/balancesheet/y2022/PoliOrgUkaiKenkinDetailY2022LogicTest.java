package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.OfferingBalancesheetIncomeEntity;

/**
 * PoliOrgUkaiKenkinDetailY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PoliOrgUkaiKenkinDetailY2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PoliOrgUkaiKenkinDetailY2022Logic poliOrgUkaiKenkinDetailY2022Logic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "wk_tbl_ukai_kenkin_times00.sql", "tasklet_stage0_income_2022.sql" })
    void test() {

        final int chunkSize = 2;
        Pageable pageable = Pageable.ofSize(chunkSize).withPage(0);
        Integer userCode = 987;

        List<OfferingBalancesheetIncomeEntity> list = poliOrgUkaiKenkinDetailY2022Logic.practice(userCode, 1, 100,
                pageable);

        assertEquals(chunkSize, list.size(), "2件取得");

        OfferingBalancesheetIncomeEntity entity00 = list.get(0);
        assertEquals(201L, entity00.getOfferingBalancesheetIncomeId(), "コード取得1");
        assertEquals(1, entity00.getPickupStage(), "階層固定値が取得できている1");

        OfferingBalancesheetIncomeEntity entity01 = list.get(1);
        assertEquals(202L, entity01.getOfferingBalancesheetIncomeId(), "コード取得2");
        assertEquals(1, entity01.getPickupStage(), "階層固定値が取得できている2");

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "wk_tbl_ukai_kenkin_times00.sql", "tasklet_stage0_income_2022.sql" })
    void testCount() {
        Integer userCode = 987;

        assertEquals(7, poliOrgUkaiKenkinDetailY2022Logic.practiceCount(userCode, 1, 100), "0階層、交付金データを含まない場合は取得件数は7件");

    }

}
