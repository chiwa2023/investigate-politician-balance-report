package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
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
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2023.OfferingBalancesheet0718Estate2023Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2023.OfferingBalancesheet0719RealEstate2023Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023.OfferingBalancesheet0718Estate2023Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023.OfferingBalancesheet0719RealEstate2023Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * UpdatePoliticalOrganizationEstateAllY2023Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePoliticalOrganizationEstateAllY2023LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePoliticalOrganizationEstateAllY2023Logic updatePoliticalOrganizationEstateAllY2023Logic;

    /** 様式7その18資産詳細Repository */
    @Autowired
    private OfferingBalancesheet0718Estate2023Repository offeringBalancesheet0718Estate2023Repository;

    /** 様式7その19不動産の利用状況詳細Repository */
    @Autowired
    private OfferingBalancesheet0719RealEstate2023Repository offeringBalancesheet0719RealEstate2023Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("offering_balancesheet_0718_estate_2023.sql")
    void testPractice0718() {

        Long oldDcoumentCode = 3232L;

        List<OfferingBalancesheet0718Estate2023Entity> listPre = offeringBalancesheet0718Estate2023Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0718EstateId(oldDcoumentCode);

        OfferingBalancesheet0718Estate2023Entity preEntity = listPre.get(0);

        // 変更前の値ではUpdate項目に値は入っていません
        assertThat(preEntity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(preEntity.getUpdateUserId()).isEqualTo(0L);
        assertThat(preEntity.getUpdateUserCode()).isEqualTo(0);
        assertThat(preEntity.getUpdateUserName()).isEqualTo("");
        assertThat(preEntity.getUpdateTimestamp()).isEqualTo(LocalDateTime.of(1948, 7, 29, 0, 0, 0));

        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        int size = updatePoliticalOrganizationEstateAllY2023Logic.practice(oldDcoumentCode, checkPrivilegeDto);

        List<OfferingBalancesheet0718Estate2023Entity> listPro = offeringBalancesheet0718Estate2023Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0718EstateId(oldDcoumentCode);

        // 前後で件数は替わっていません
        assertThat(size).isEqualTo(listPre.size());
        assertThat(listPro.size()).isEqualTo(listPre.size());

        OfferingBalancesheet0718Estate2023Entity proEntity;
        for (int index = 0; index < size; index++) {
            preEntity = listPre.get(index);
            proEntity = listPro.get(index);

            // id順に取得しているのでidは一緒です
            assertThat(proEntity.getOfferingBalancesheet0718EstateId())
                    .isEqualTo(preEntity.getOfferingBalancesheet0718EstateId());

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

    @Test
    @Transactional
    @Sql("offering_balancesheet_0719_real_estate_2023.sql")
    void testPractice0719() {

        Long oldDcoumentCode = 3232L;

        List<OfferingBalancesheet0719RealEstate2023Entity> listPre = offeringBalancesheet0719RealEstate2023Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0719RealEstateId(oldDcoumentCode);

        OfferingBalancesheet0719RealEstate2023Entity preEntity = listPre.get(0);

        // 変更前の値ではUpdate項目に値は入っていません
        assertThat(preEntity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(preEntity.getUpdateUserId()).isEqualTo(0L);
        assertThat(preEntity.getUpdateUserCode()).isEqualTo(0);
        assertThat(preEntity.getUpdateUserName()).isEqualTo("");
        assertThat(preEntity.getUpdateTimestamp()).isEqualTo(LocalDateTime.of(1948, 7, 29, 0, 0, 0));

        CheckPrivilegeDto checkPrivilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        int size = updatePoliticalOrganizationEstateAllY2023Logic.practice(oldDcoumentCode, checkPrivilegeDto);

        List<OfferingBalancesheet0719RealEstate2023Entity> listPro = offeringBalancesheet0719RealEstate2023Repository
                .findByDocumentCodeOrderByOfferingBalancesheet0719RealEstateId(oldDcoumentCode);

        // 前後で件数は替わっていません
        assertThat(size).isEqualTo(listPre.size());
        assertThat(listPro.size()).isEqualTo(listPre.size());

        OfferingBalancesheet0719RealEstate2023Entity proEntity;
        for (int index = 0; index < size; index++) {
            preEntity = listPre.get(index);
            proEntity = listPro.get(index);

            // id順に取得しているのでidは一緒です
            assertThat(proEntity.getOfferingBalancesheet0719RealEstateId())
                    .isEqualTo(preEntity.getOfferingBalancesheet0719RealEstateId());

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
