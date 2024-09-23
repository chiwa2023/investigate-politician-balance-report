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
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheet0701And0720Surface2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0701And0720Surface2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdatetPoliticalOrganizationSheet0701And0720Y2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePoliticalOrganizationSheet0701And0720Y2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatetPoliticalOrganizationSheet0701And0720Y2022Logic updatetPoliticalOrganizationSheet0701And0720Y2022Logic;

    /** 政治資金収支報告書の表紙、宣誓書、文書属性Repository */
    @Autowired
    private OfferingBalancesheet0701And0720Surface2022Repository offeringBalancesheet0701And0720Surface2022Repository;

    @Test
    @Transactional
    @Sql("offering_balancesheet_0701_and_0720_surface_2022.sql")
    void testPractice() {

        Long oldDcoumentCode = 3232L;

        List<OfferingBalancesheet0701And0720Surface2022Entity> listPre = offeringBalancesheet0701And0720Surface2022Repository
                .findByOfferingBalancesheet0701And0720SurfaceCodeOrderByOfferingBalancesheet0701And0720SurfaceId(
                        oldDcoumentCode);

        OfferingBalancesheet0701And0720Surface2022Entity preEntity = listPre.get(0);

        // 変更前の値ではUpdate項目に値は入っていません
        assertThat(preEntity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(preEntity.getUpdateUserId()).isEqualTo(0L);
        assertThat(preEntity.getUpdateUserCode()).isEqualTo(0);
        assertThat(preEntity.getUpdateUserName()).isEqualTo("");
        assertThat(preEntity.getUpdateTimestamp()).isEqualTo(LocalDateTime.of(1948, 7, 29, 0, 0, 0));

        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        int size = updatetPoliticalOrganizationSheet0701And0720Y2022Logic.practice(oldDcoumentCode, checkPrivilegeDto);

        List<OfferingBalancesheet0701And0720Surface2022Entity> listPro = offeringBalancesheet0701And0720Surface2022Repository
                .findByOfferingBalancesheet0701And0720SurfaceCodeOrderByOfferingBalancesheet0701And0720SurfaceId(
                        oldDcoumentCode);

        // 前後で件数は替わっていません
        assertThat(size).isEqualTo(listPre.size());
        assertThat(listPro.size()).isEqualTo(listPre.size());

        OfferingBalancesheet0701And0720Surface2022Entity proEntity;
        for (int index = 0; index < size; index++) {
            preEntity = listPre.get(index);
            proEntity = listPro.get(index);

            // id順に取得しているのでidは一緒です
            assertThat(proEntity.getOfferingBalancesheet0701And0720SurfaceId())
                    .isEqualTo(preEntity.getOfferingBalancesheet0701And0720SurfaceId());

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
