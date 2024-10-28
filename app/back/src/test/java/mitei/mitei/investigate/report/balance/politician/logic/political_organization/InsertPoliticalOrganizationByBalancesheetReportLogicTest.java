package mitei.mitei.investigate.report.balance.politician.logic.political_organization;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * InsertPoliticalOrganizationByBalancesheetReportLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrganizationByBalancesheetReportLogicTest {

    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrganizationByBalancesheetReportLogic insertPoliticalOrganizationByBalancesheetReportLogic;
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPractice() {
        
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        
        Sheet070100CoverAndOrganizationDetailsDto detailsDto = new Sheet070100CoverAndOrganizationDetailsDto();
        detailsDto.setDantaiName01("ちゃらんぽらん政治団体");
        detailsDto.setDantaiNameKana("ちゃらんぽらんせいじだんたい");
        detailsDto.setDantaiKbn("6");
        detailsDto.setJimushoJusho("東京都千代田区霞が関");
        detailsDto.setJimushoJushoTatemono("三角ビル903");
        detailsDto.setDaihyoushaNameLast("代表者");
        detailsDto.setDaihyoushaNameFirst("花子");
        detailsDto.setKaikeiSekinnshaNameLast("会計責任者");
        detailsDto.setKaikeiSekinnshaNameFirst("次郎");
        detailsDto.setJimuTantousha1NameLast("事務担当者");
        detailsDto.setJimuTantousha1NameFirst("三郎");
        
        PoliticalOrganizationEntity entity = insertPoliticalOrganizationByBalancesheetReportLogic.practice(detailsDto,privilegeDto);
        
        assertThat(entity.getPoliticalOrganizationName()).isEqualTo(detailsDto.getDantaiName01());
        assertThat(entity.getPoliticalOrganizationNameKana()).isEqualTo(detailsDto.getDantaiNameKana());
        assertThat(entity.getDantaiKbn()).isEqualTo(detailsDto.getDantaiKbn());

        // 住所
        assertThat(entity.getAddressAll()).isEqualTo("東京都千代田区霞が関　三角ビル903");

        // 代表者
        assertThat(entity.getDaihyoshaLastName()).isEqualTo(detailsDto.getDaihyoushaNameLast());
        assertThat(entity.getDaihyoshaFirstName()).isEqualTo(detailsDto.getDaihyoushaNameFirst());
        assertThat(entity.getDaihyoshaName()).isEqualTo("代表者　花子");

        // 会計責任者
        assertThat(entity.getKaikeiSekininshaLastName()).isEqualTo(detailsDto.getKaikeiSekinnshaNameLast());
        assertThat(entity.getKaikeiSekininshaFirstName()).isEqualTo(detailsDto.getKaikeiSekinnshaNameFirst());
        assertThat(entity.getKaikeiSekininshaName()).isEqualTo("会計責任者　次郎");

        // 会計責任者の職務代行者
        assertThat(entity.getKaikeiDaikoLastName()).isEqualTo(detailsDto.getJimuTantousha1NameLast());
        assertThat(entity.getKaikeiDaikoFirstName()).isEqualTo(detailsDto.getJimuTantousha1NameFirst());
        assertThat(entity.getKaikeiDaikoName()).isEqualTo("事務担当者　三郎");

        assertThat(entity.getSearchText()).isEqualTo("ちゃらんぽらん政治団体代表者花子東京都千代田区霞が関三角ビル903");
        
        assertThat(entity.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity.getInsertUserId()).isEqualTo(privilegeDto.getLoginUserId());
        assertThat(entity.getInsertUserCode()).isEqualTo(privilegeDto.getLoginUserCode());
        assertThat(entity.getInsertUserName()).isEqualTo(privilegeDto.getLoginUserName());
        
    }

}
