package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

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
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheet0702And0713And0717Summary2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheet0702And0713And0717Summary2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdatePoliticalOrganizationSummaryY2025Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePoliticalOrganizationSummaryY2025LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePoliticalOrganizationSummaryY2025Logic updatePoliticalOrganizationSummaryY2025Logic;

    /** 政治資金収支報告書集計表登録Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2025Repository offeringBalancesheet0702And0713And0717Summary2025Repository;

    @Test
    @Transactional
    @Sql("offering_balancesheet_0702_and_0713_and_0717_summary_2025.sql")
    void testPractice() throws Exception {

        Long oldDcoumentCode = 3232L;

        List<OfferingBalancesheet0702And0713And0717Summary2025Entity> listPre = offeringBalancesheet0702And0713And0717Summary2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(oldDcoumentCode);

        OfferingBalancesheet0702And0713And0717Summary2025Entity preEntity = listPre.get(0);

        // 変更前の値ではUpdate項目に値は入っていません
        assertThat(preEntity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(preEntity.getUpdateUserId()).isEqualTo(0L);
        assertThat(preEntity.getUpdateUserCode()).isEqualTo(0);
        assertThat(preEntity.getUpdateUserName()).isEqualTo("");
        assertThat(preEntity.getUpdateTimestamp()).isEqualTo(LocalDateTime.of(1948, 7, 29, 0, 0, 0));

        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        int size = updatePoliticalOrganizationSummaryY2025Logic.practice(oldDcoumentCode, checkPrivilegeDto);

        List<OfferingBalancesheet0702And0713And0717Summary2025Entity> listPro = offeringBalancesheet0702And0713And0717Summary2025Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0702And0713And0717SummaryId(oldDcoumentCode);

        // 前後で件数は替わっていません
        assertThat(size).isEqualTo(listPre.size());
        assertThat(listPro.size()).isEqualTo(listPre.size());

        OfferingBalancesheet0702And0713And0717Summary2025Entity proEntity;
        for (int index = 0; index < size; index++) {
            preEntity = listPre.get(index);
            proEntity = listPro.get(index);

            // id順に取得しているのでidは一緒です
            assertThat(proEntity.getOfferingBalancesheet0702And0713And0717SummaryId())
                    .isEqualTo(preEntity.getOfferingBalancesheet0702And0713And0717SummaryId());

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
