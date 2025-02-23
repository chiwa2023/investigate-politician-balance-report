package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;


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
 * PoliOrgUkaiKenkinDetailLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PoliOrgUkaiKenkinDetailLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PoliOrgUkaiKenkinDetailLogic poliOrgUkaiKenkinDetailLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "../y2022/wk_tbl_ukai_kenkin_times00.sql", "../y2022/tasklet_stage0_income_2022.sql" })
    void test() throws Exception {

        final int chunkSize = 2;
        Pageable pageable = Pageable.ofSize(chunkSize).withPage(0);
        Integer userCode = 987;

        List<OfferingBalancesheetIncomeEntity> list = poliOrgUkaiKenkinDetailLogic.practice(userCode, 2022, 1, false,
                pageable);

        assertEquals(chunkSize, list.size(), "2件取得");

        OfferingBalancesheetIncomeEntity entity00 = list.get(0);
        assertEquals(201L, entity00.getOfferingBalancesheetIncomeId(), "コード取得1(2022年)");
        assertEquals(1, entity00.getPickupStage(), "階層固定値が取得できている1");

        OfferingBalancesheetIncomeEntity entity01 = list.get(1);
        assertEquals(202L, entity01.getOfferingBalancesheetIncomeId(), "コード取得2(2022年)");
        assertEquals(1, entity01.getPickupStage(), "階層固定値が取得できている2");
    }

    @Test
    @Tag("TableTruncate")
    //@Transactional
    @Sql({ "../y2022/wk_tbl_ukai_kenkin_times00.sql", "../y2022/tasklet_stage0_income_2022.sql" })
    void testCount() throws Exception {

        Integer userCode = 987;

        assertEquals(7, poliOrgUkaiKenkinDetailLogic.practiceCount(userCode, 2022, 1, false),
                "0階層、交付金データを含まない場合は取得件数は7件");
    }

}
