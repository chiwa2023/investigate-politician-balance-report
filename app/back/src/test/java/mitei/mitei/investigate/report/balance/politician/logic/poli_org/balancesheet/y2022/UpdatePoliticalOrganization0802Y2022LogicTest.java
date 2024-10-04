package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetWithdrawal0802Transfer2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetWithdrawal0802Transfer2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdatePoliticalOrganization0802Y2022Logic単体テスト
 * 
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePoliticalOrganization0802Y2022LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private UpdatePoliticalOrganization0802Y2022Logic updatePoliticalOrganization0802Y2022Logic;

    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetWithdrawal0802Transfer2022Repository offeringBalancesheetWithdrawal0802Transfer2022Repository;

    @Test
    @Transactional
    @Sql("offering_balancesheet_withdrawal_0802_transfer_2022.sql")
    void testPractice() {
        Long oldDcoumentCode = 3232L;

        List<OfferingBalancesheetWithdrawal0802Transfer2022Entity> listPre = offeringBalancesheetWithdrawal0802Transfer2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheetWithdrawal0802TransferId(oldDcoumentCode);

        OfferingBalancesheetWithdrawal0802Transfer2022Entity preEntity = listPre.get(0);

        // 変更前の値ではUpdate項目に値は入っていません
        assertThat(preEntity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(preEntity.getUpdateUserId()).isEqualTo(0L);
        assertThat(preEntity.getUpdateUserCode()).isEqualTo(0);
        assertThat(preEntity.getUpdateUserName()).isEqualTo("");
        assertThat(preEntity.getUpdateTimestamp())
                .isEqualTo(LocalDateTime.of(1948, 7, 29, 0, 0, 0));
        
        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        int size = updatePoliticalOrganization0802Y2022Logic.practice(oldDcoumentCode, checkPrivilegeDto);

        List<OfferingBalancesheetWithdrawal0802Transfer2022Entity> listPro = offeringBalancesheetWithdrawal0802Transfer2022Repository
                .findByDocumentCodeOrderByOfferingBalancesheetWithdrawal0802TransferId(oldDcoumentCode);

        // 前後で件数は替わっていません
        assertThat(size).isEqualTo(listPre.size());
        assertThat(listPro.size()).isEqualTo(listPre.size());

        OfferingBalancesheetWithdrawal0802Transfer2022Entity proEntity;
        for (int index = 0; index < size; index++) {
            preEntity = listPre.get(index);
            proEntity = listPro.get(index);

            // id順に取得しているのでidは一緒です
            assertThat(proEntity.getOfferingBalancesheetWithdrawal0802TransferId())
                    .isEqualTo(preEntity.getOfferingBalancesheetWithdrawal0802TransferId());
            
            // 最新区分は履歴に変更
            assertThat(proEntity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());

            // 変更後にはUpdate項目に値が入ります
            assertThat(proEntity.getUpdateUserId()).isEqualTo(checkPrivilegeDto.getLoginUserId());
            assertThat(proEntity.getUpdateUserCode()).isEqualTo(checkPrivilegeDto.getLoginUserCode());
            assertThat(proEntity.getUpdateUserName()).isEqualTo(checkPrivilegeDto.getLoginUserName());
            assertTrue(proEntity.getUpdateTimestamp().isAfter(LocalDateTime.now().minusMinutes(1L)),
                    "1分前より後に生成");
        }

    }

}
