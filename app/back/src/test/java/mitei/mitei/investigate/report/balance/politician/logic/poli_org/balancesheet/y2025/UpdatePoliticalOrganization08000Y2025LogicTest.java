package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
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
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetDifficalt0800Recipt2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetDifficalt0800Recipt2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdatePoliticalOrganization08000Y2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePoliticalOrganization08000Y2025LogicTest {
    // CHECKSTYLE:OFF
    
    /** テスト対象 */
    @Autowired
    private UpdatePoliticalOrganization08000Y2025Logic updatePoliticalOrganization08000Y2025Logic;

    /** 様式08その2 支出項目別金額の内訳Repository */
    @Autowired
    private OfferingBalancesheetDifficalt0800Recipt2025Repository offeringBalancesheetDifficalt0800Recipt2025Repository;

    @Test
    @Transactional
    @Sql("offering_balancesheet_difficalt_0800_recipt_2025.sql")
    void testPractice() {

        Long oldDcoumentCode = 3232L;

        List<OfferingBalancesheetDifficalt0800Recipt2025Entity> listPre = offeringBalancesheetDifficalt0800Recipt2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheetDifficalt0800ReciptId(oldDcoumentCode);

        OfferingBalancesheetDifficalt0800Recipt2025Entity preEntity = listPre.get(0);
        
        assertThat(preEntity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        // 変更前の値ではUpdate項目に値は入っていません
        assertThat(preEntity.getUpdateUserId()).isEqualTo(0L);
        assertThat(preEntity.getUpdateUserCode()).isEqualTo(0);
        assertThat(preEntity.getUpdateUserName()).isEqualTo("");
        assertThat(preEntity.getUpdateTimestamp())
                .isEqualTo(Timestamp.valueOf(LocalDateTime.of(1980, 1, 1, 0, 0, 0)));
        
        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        int size = updatePoliticalOrganization08000Y2025Logic.practice(oldDcoumentCode, checkPrivilegeDto);
        
        List<OfferingBalancesheetDifficalt0800Recipt2025Entity> listPro = offeringBalancesheetDifficalt0800Recipt2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheetDifficalt0800ReciptId(oldDcoumentCode);

        // 前後で件数は替わっていません
        assertThat(size).isEqualTo(listPre.size());
        assertThat(listPro.size()).isEqualTo(listPre.size());

        OfferingBalancesheetDifficalt0800Recipt2025Entity proEntity;
        for (int index = 0; index < size; index++) {
            proEntity = listPro.get(index);

            // id順に取得しているのでidは一緒です
            assertThat(proEntity.getOfferingBalancesheetDifficalt0800ReciptId())
                    .isEqualTo(preEntity.getOfferingBalancesheetDifficalt0800ReciptId());

            // 最新区分は履歴に変更
            assertThat(proEntity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());


            // 変更後にはUpdate項目に値が入ります
            assertThat(proEntity.getUpdateUserId()).isEqualTo(checkPrivilegeDto.getLoginUserId());
            assertThat(proEntity.getUpdateUserCode()).isEqualTo(checkPrivilegeDto.getLoginUserCode());
            assertThat(proEntity.getUpdateUserName()).isEqualTo(checkPrivilegeDto.getLoginUserName());
            assertTrue(proEntity.getUpdateTimestamp().after(Timestamp.valueOf(LocalDateTime.now().minusMinutes(1L))),
                    "1分前より後に生成");
        }
    }

}
