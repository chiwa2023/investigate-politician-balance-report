package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import static org.assertj.core.api.Assertions.assertThat;

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

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;

/**
 * ConvertWorkTableToReadXmlBalancesheetResultLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertWorkTableToReadXmlBalancesheetResultLogicTest {

    /** 政治資金収支報告書一括処理ワークテーブル */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_poli_org_balancesheet_report.sql")
    void testPractice() { // NOPMD

        // サンプルデータで削除して1件だけ登録するので1件であることが保証されている
        WkTblPoliOrgBalancesheetReportEntity entity = wkTblPoliOrgBalancesheetReportRepository.findAll().get(0);

        ConvertWorkTableToReadXmlBalancesheetResultLogic convertWorkTableToReadXmlBalancesheetResultLogic = new ConvertWorkTableToReadXmlBalancesheetResultLogic();

        ReadXmlBalancesheetResultDto resultDto = convertWorkTableToReadXmlBalancesheetResultLogic.practice(entity);

        /* 書証保存Dto */
        SaveStorageResultDto saveStorageResultDto = resultDto.getSaveStorageResultDto();

        assertThat(saveStorageResultDto.getCharset()).isEqualTo(entity.getCharset());
        assertThat(saveStorageResultDto.getChildDir()).isEqualTo(entity.getChildDir());
        assertThat(saveStorageResultDto.getFileName()).isEqualTo(entity.getFileName());
        assertThat(saveStorageResultDto.getRegistTimeText()).isEqualTo(entity.getRegistTimeText());
        assertThat(saveStorageResultDto.getShoshouCode()).isEqualTo(entity.getShoshouCode());
        assertThat(saveStorageResultDto.getShoshouId()).isEqualTo(entity.getShoshouId());
        assertThat(saveStorageResultDto.getShoshouKbn()).isEqualTo(entity.getShoshouKbn());

        /* 政治資金収支報告書政治団体Dto */
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = resultDto.getDocumentPropertyDto();
        assertThat(documentPropertyDto.getDaihyouName()).isEqualTo(entity.getDaihyouName());
        assertThat(documentPropertyDto.getDantaiName()).isEqualTo(entity.getDantaiName());
        assertThat(documentPropertyDto.getHoukokuNen()).isEqualTo(entity.getHoukokuNen());
        assertThat(documentPropertyDto.getIsAddOrganization()).isEqualTo(entity.getIsAddOrganization());
        assertThat(documentPropertyDto.getOfferingDate()).isEqualTo(entity.getOfferingDate());
        assertThat(documentPropertyDto.getPoliticalOrganizationCode()).isEqualTo(entity.getPoliticalOrganizationCode());
        assertThat(documentPropertyDto.getPoliticalOrganizationId()).isEqualTo(entity.getPoliticalOrganizationId());
        assertThat(documentPropertyDto.getPoliticalOrganizationName()).isEqualTo(entity.getPoliticalOrganizationName());
        assertThat(documentPropertyDto.getRelationKbn()).isEqualTo(entity.getRelationKbn());
        assertThat(documentPropertyDto.getRelationPersonCodeDelegate())
                .isEqualTo(entity.getRelationPersonCodeDelegate());
        assertThat(documentPropertyDto.getRelationPersonIdDelegate()).isEqualTo(entity.getRelationPersonIdDelegate());
        assertThat(documentPropertyDto.getRelationPersonNameDelegate())
                .isEqualTo(entity.getRelationPersonNameDelegate());

        /* 政治資金収支報告書表紙(様式7その1)Dto */
        Sheet070100CoverAndOrganizationDetailsDto coverDto = resultDto.getCoverDto();
        assertThat(coverDto.getDaihyoushaNameFirst()).isEqualTo(entity.getDaihyoushaNameFirst());
        assertThat(coverDto.getDaihyoushaNameLast()).isEqualTo(entity.getDaihyoushaNameLast());
        assertThat(coverDto.getDantaiKbn()).isEqualTo(entity.getDantaiKbn());
        assertThat(coverDto.getDantaiName01()).isEqualTo(entity.getDantaiName01());
        assertThat(coverDto.getDantaiNameKana()).isEqualTo(entity.getDantaiNameKana());
        assertThat(coverDto.getDateKaisai()).isEqualTo(entity.getDateKaisai());
        assertThat(coverDto.getGiinDantantaiTokureiPeriodEnd()).isEqualTo(entity.getGiinDantantaiTokureiPeriodEnd());
        assertThat(coverDto.getGiinDantantaiTokureiPeriodRest()).isEqualTo(entity.getGiinDantantaiTokureiPeriodRest());
        assertThat(coverDto.getGiinDantantaiTokureiPeriodStart())
                .isEqualTo(entity.getGiinDantantaiTokureiPeriodStart());
        assertThat(coverDto.getHoukokuNen()).isEqualTo(entity.getHoukokuNen());
        assertThat(coverDto.getJimushoJusho()).isEqualTo(entity.getJimushoJusho());
        assertThat(coverDto.getJimushoJushoTatemono()).isEqualTo(entity.getJimushoJushoTatemono());
        assertThat(coverDto.getJimuTantousha1NameFirst()).isEqualTo(entity.getJimuTantousha1NameFirst());
        assertThat(coverDto.getJimuTantousha1NameLast()).isEqualTo(entity.getJimuTantousha1NameLast());
        assertThat(coverDto.getJimuTantousha1Tel()).isEqualTo(entity.getJimuTantousha1Tel());
        assertThat(coverDto.getJimuTantousha2NameFirst()).isEqualTo(entity.getJimuTantousha2NameFirst());
        assertThat(coverDto.getJimuTantousha2NameLast()).isEqualTo(entity.getJimuTantousha2NameLast());
        assertThat(coverDto.getJimuTantousha2Tel()).isEqualTo(entity.getJimuTantousha2Tel());
        assertThat(coverDto.getJimuTantousha3NameFirst()).isEqualTo(entity.getJimuTantousha3NameFirst());
        assertThat(coverDto.getJimuTantousha3NameLast()).isEqualTo(entity.getJimuTantousha3NameLast());
        assertThat(coverDto.getJimuTantousha3Tel()).isEqualTo(entity.getJimuTantousha3Tel());
        assertThat(coverDto.getKaikeiSekinnshaNameFirst()).isEqualTo(entity.getKaikeiSekinnshaNameFirst());
        assertThat(coverDto.getKaikeiSekinnshaNameLast()).isEqualTo(entity.getKaikeiSekinnshaNameLast());
        assertThat(coverDto.getKanriDantaiPeriodEnd()).isEqualTo(entity.getKanriDantaiPeriodEnd());
        assertThat(coverDto.getKanriDantaiPeriodRest()).isEqualTo(entity.getKanriDantaiPeriodRest());
        assertThat(coverDto.getKanriDantaiPeriodStart()).isEqualTo(entity.getKanriDantaiPeriodStart());
        assertThat(coverDto.getKatsudouKuikiKbn()).isEqualTo(entity.getKatsudouKuikiKbn());
        assertThat(coverDto.getKokkaiGiin1GenKouho()).isEqualTo(entity.getKokkaiGiin1GenKouho());
        assertThat(coverDto.getKokkaiGiin1NameFirst()).isEqualTo(entity.getKokkaiGiin1NameFirst());
        assertThat(coverDto.getKokkaiGiin1NameLast()).isEqualTo(entity.getKokkaiGiin1NameLast());
        assertThat(coverDto.getKokkaiGiin1ShuuSan()).isEqualTo(entity.getKokkaiGiin1ShuuSan());
        assertThat(coverDto.getKokkaiGiin2GenKouho()).isEqualTo(entity.getKokkaiGiin2GenKouho());
        assertThat(coverDto.getKokkaiGiin2NameFirst()).isEqualTo(entity.getKokkaiGiin2NameFirst());
        assertThat(coverDto.getKokkaiGiin2NameLast()).isEqualTo(entity.getKokkaiGiin2NameLast());
        assertThat(coverDto.getKokkaiGiin2ShuuSan()).isEqualTo(entity.getKokkaiGiin2ShuuSan());
        assertThat(coverDto.getKokkaiGiin3GenKouho()).isEqualTo(entity.getKokkaiGiin3GenKouho());
        assertThat(coverDto.getKokkaiGiin3NameFirst()).isEqualTo(entity.getKokkaiGiin3NameFirst());
        assertThat(coverDto.getKokkaiGiin3NameLast()).isEqualTo(entity.getKokkaiGiin3NameLast());
        assertThat(coverDto.getKokkaiGiin3ShuuSan()).isEqualTo(entity.getKokkaiGiin3ShuuSan());
        assertThat(coverDto.getKokkaiGiinDantaiKbn()).isEqualTo(entity.getKokkaiGiinDantaiKbn());
        assertThat(coverDto.getKoushokuName()).isEqualTo(entity.getKoushokuName());
        assertThat(coverDto.getShikinDaihyouName1()).isEqualTo(entity.getShikinDaihyouName1());
        assertThat(coverDto.getShikinDaihyouName2()).isEqualTo(entity.getShikinDaihyouName2());
        assertThat(coverDto.getUmuShikinKanrenDantai()).isEqualTo(entity.getUmuShikinKanrenDantai());

    }

}
