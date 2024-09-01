package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet; // NOPMD

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.constants.blancesheet_report.RealEstateKbnConstants;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0718AssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0719RealEstateDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071801Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071802Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071803Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071804Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071805Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071806Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071807Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071808Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071809Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071810Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071811Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheetKbn071812Dto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071800ClassificationOfAssetsByItemDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Row071900CurrentStatusOfRealEstateUseDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071801LandAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071802BuildingsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071803SurfaceRightsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071804MovablesAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071805SavingsAmmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071806TrustAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071807SecuritiesAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071808InvestmentAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071809LoanAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071810DepositAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071811FacilityUsageRightsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071812BorrowingsAmongAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071900RealEstateDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet0718Estate2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet0719RealEstate2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingBalancesheet0718Estate2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingBalancesheet0719RealEstate2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * InsertPoliticalOrganizationEstateAllLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrganizationEstateAllLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrganizationEstateAllLogic insertPoliticalOrganizationEstateAllLogic;
    
    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /**  様式7その18資産詳細Repository */ 
    @Autowired
    private OfferingBalancesheet0718Estate2025Repository offeringBalancesheet0718Estate2025Repository;
    
    /**  様式7その19不動産の利用状況詳細Repository */ 
    @Autowired
    private OfferingBalancesheet0719RealEstate2025Repository offeringBalancesheet0719RealEstate2025Repository;

    
    @Test
    @Transactional
    void testPractice() { // NOPMD
        
        // 文書同一識別コード
        Long documentCode = 3434L;
        
        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(2024); // 実際には表紙からコピー
        documentPropertyDto
                .setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate("R4/12/1")); // 実際には宣誓書からコピー
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        
        /* 資産の詳細様式7その18 */
        AllSheetKbn071801Dto allSheetKbn071801Dto = new AllSheetKbn071801Dto();

        Sheet071801LandAmongAssetsDto sheet1 = new Sheet071801LandAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row1 = new Row071800ClassificationOfAssetsByItemDto();
        row1.setIchirenNo(1);
        row1.setTekiyou("摘要");
        row1.setKingaku(30001L); // NOPMD
        row1.setAccrualDate("R4/12/7"); // NOPMD
        row1.setBiko("備考");

        sheet1.getList().add(row1);

        allSheetKbn071801Dto.setSheet071801LandAmongAssetsDto(sheet1);

        /**
         * その2
         */
        AllSheetKbn071802Dto allSheetKbn071802Dto = new AllSheetKbn071802Dto();

        Sheet071802BuildingsAmongAssetsDto sheet2 = new Sheet071802BuildingsAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row2 = new Row071800ClassificationOfAssetsByItemDto();
        row2.setIchirenNo(1);
        row2.setTekiyou("摘要");
        row2.setKingaku(30002L); // NOPMD
        row2.setAccrualDate("R4/12/8");
        row2.setBiko("備考");

        sheet2.getList().add(row2);

        allSheetKbn071802Dto.setSheet071802BuildingsAmongAssetsDto(sheet2);

        /**
         * その3
         */
        AllSheetKbn071803Dto allSheetKbn071803Dto = new AllSheetKbn071803Dto();

        Sheet071803SurfaceRightsAmongAssetsDto sheet3 = new Sheet071803SurfaceRightsAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row3 = new Row071800ClassificationOfAssetsByItemDto();
        // 
        row3.setIchirenNo(1);
        // 
        row3.setTekiyou("摘要");
        // 
        row3.setKingaku(30003L); // NOPMD
        // 
        row3.setAccrualDate("R4/12/9");
        // 
        row3.setBiko("備考");

        sheet3.getList().add(row3);

        allSheetKbn071803Dto.setSheet071803SurfaceRightsAmongAssetsDto(sheet3);

        /**
         * その4
         */
        AllSheetKbn071804Dto allSheetKbn071804Dto = new AllSheetKbn071804Dto();

        Sheet071804MovablesAmongAssetsDto sheet4 = new Sheet071804MovablesAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row4 = new Row071800ClassificationOfAssetsByItemDto();
        // 
        row4.setIchirenNo(1);
        // 
        row4.setTekiyou("摘要");
        // 
        row4.setKingaku(30004L); // NOPMD
        // 
        row4.setAccrualDate("R4/12/10");
        // 
        row4.setBiko("備考");

        sheet4.getList().add(row4);

        allSheetKbn071804Dto.setSheet071804MovablesAmongAssetsDto(sheet4);

        /**
         * その5
         */
        AllSheetKbn071805Dto allSheetKbn071805Dto = new AllSheetKbn071805Dto();

        Sheet071805SavingsAmmongAssetsDto sheet5 = new Sheet071805SavingsAmmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row5 = new Row071800ClassificationOfAssetsByItemDto();
        // 
        row5.setIchirenNo(1);
        // 
        row5.setTekiyou("摘要");
        // 
        row5.setKingaku(30005L); // NOPMD
        // 
        row5.setAccrualDate("R4/12/11");
        // 
        row5.setBiko("備考");

        sheet5.getList().add(row5);

        allSheetKbn071805Dto.setSheet071805SavingsAmmongAssetsDto(sheet5);

        /**
         * その6
         */
        AllSheetKbn071806Dto allSheetKbn071806Dto = new AllSheetKbn071806Dto();

        Sheet071806TrustAmongAssetsDto sheet6 = new Sheet071806TrustAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row6 = new Row071800ClassificationOfAssetsByItemDto();
        // 
        row6.setIchirenNo(1);
        // 
        row6.setTekiyou("摘要");
        // 
        row6.setKingaku(30006L); // NOPMD
        // 
        row6.setAccrualDate("R4/12/12");
        // 
        row6.setBiko("備考");

        sheet6.getList().add(row6);

        allSheetKbn071806Dto.setSheet071806TrustAmongAssetsDto(sheet6);

        /**
         * その7
         */
        AllSheetKbn071807Dto allSheetKbn071807Dto = new AllSheetKbn071807Dto();

        Sheet071807SecuritiesAmongAssetsDto sheet7 = new Sheet071807SecuritiesAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row7 = new Row071800ClassificationOfAssetsByItemDto();
        // 
        row7.setIchirenNo(1);
        // 
        row7.setTekiyou("摘要");
        // 
        row7.setKingaku(30007L); // NOPMD
        // 
        row7.setAccrualDate("R4/12/13");
        // 
        row7.setBiko("備考");

        sheet7.getList().add(row7);

        allSheetKbn071807Dto.setSheet071807SecuritiesAmongAssetsDto(sheet7);

        /**
         * その8
         */
        AllSheetKbn071808Dto allSheetKbn071808Dto = new AllSheetKbn071808Dto();

        Sheet071808InvestmentAmongAssetsDto sheet8 = new Sheet071808InvestmentAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row8 = new Row071800ClassificationOfAssetsByItemDto();
        // 
        row8.setIchirenNo(1);
        // 
        row8.setTekiyou("摘要");
        // 
        row8.setKingaku(30008L); // NOPMD
        // 
        row8.setAccrualDate("R4/12/14");
        // 
        row8.setBiko("備考");

        sheet8.getList().add(row8);

        allSheetKbn071808Dto.setSheet071808InvestmentAmongAssetsDto(sheet8);

        /**
         * その9
         */
        AllSheetKbn071809Dto allSheetKbn071809Dto = new AllSheetKbn071809Dto();

        Sheet071809LoanAmongAssetsDto sheet9 = new Sheet071809LoanAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row9 = new Row071800ClassificationOfAssetsByItemDto();
        // 
        row9.setIchirenNo(1);
        // 
        row9.setTekiyou("摘要");
        // 
        row9.setKingaku(30009L); // NOPMD
        // 
        row9.setAccrualDate("R4/12/15");
        // 
        row9.setBiko("備考");

        sheet9.getList().add(row9);

        allSheetKbn071809Dto.setSheet071809LoanAmongAssetsDto(sheet9);

        /**
         * その10
         */
        AllSheetKbn071810Dto allSheetKbn071810Dto = new AllSheetKbn071810Dto();

        Sheet071810DepositAmongAssetsDto sheet10 = new Sheet071810DepositAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row10 = new Row071800ClassificationOfAssetsByItemDto();
        // 
        row10.setIchirenNo(1);
        // 
        row10.setTekiyou("摘要");
        // 
        row10.setKingaku(30010L); // NOPMD
        // 
        row10.setAccrualDate("R4/12/16");
        // 
        row10.setBiko("備考");

        sheet10.getList().add(row10);

        allSheetKbn071810Dto.setSheet071810DepositAmongAssetsDto(sheet10);

        /**
         * その11
         */
        AllSheetKbn071811Dto allSheetKbn071811Dto = new AllSheetKbn071811Dto();

        Sheet071811FacilityUsageRightsAmongAssetsDto sheet11 = new Sheet071811FacilityUsageRightsAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row11 = new Row071800ClassificationOfAssetsByItemDto();
        // 
        row11.setIchirenNo(1);
        // 
        row11.setTekiyou("摘要");
        // 
        row11.setKingaku(30011L); // NOPMD
        // 
        row11.setAccrualDate("R4/12/17");
        // 
        row11.setBiko("備考");

        sheet11.getList().add(row11);

        allSheetKbn071811Dto.setSheet071811FacilityUsageRightsAmongAssetsDto(sheet11);

        /**
         * その12
         */
        AllSheetKbn071812Dto allSheetKbn071812Dto = new AllSheetKbn071812Dto();

        Sheet071812BorrowingsAmongAssetsDto sheet12 = new Sheet071812BorrowingsAmongAssetsDto();

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071800ClassificationOfAssetsByItemDto row12 = new Row071800ClassificationOfAssetsByItemDto();
        // 
        row12.setIchirenNo(1);
        // 
        row12.setTekiyou("摘要");
        // 
        row12.setKingaku(30012L); // NOPMD
        // 
        row12.setAccrualDate("R4/12/18");
        // 
        row12.setBiko("備考");

        sheet12.getList().add(row12);

        allSheetKbn071812Dto.setSheet071812BorrowingsAmongAssetsDto(sheet12);

        AllSheet0718AssetsDto allSheet0718AssetsDto = new AllSheet0718AssetsDto();
        allSheet0718AssetsDto.setAllSheetKbn071801Dto(allSheetKbn071801Dto);
        allSheet0718AssetsDto.setAllSheetKbn071802Dto(allSheetKbn071802Dto);
        allSheet0718AssetsDto.setAllSheetKbn071803Dto(allSheetKbn071803Dto);
        allSheet0718AssetsDto.setAllSheetKbn071804Dto(allSheetKbn071804Dto);
        allSheet0718AssetsDto.setAllSheetKbn071805Dto(allSheetKbn071805Dto);
        allSheet0718AssetsDto.setAllSheetKbn071806Dto(allSheetKbn071806Dto);
        allSheet0718AssetsDto.setAllSheetKbn071807Dto(allSheetKbn071807Dto);
        allSheet0718AssetsDto.setAllSheetKbn071808Dto(allSheetKbn071808Dto);
        allSheet0718AssetsDto.setAllSheetKbn071809Dto(allSheetKbn071809Dto);
        allSheet0718AssetsDto.setAllSheetKbn071810Dto(allSheetKbn071810Dto);
        allSheet0718AssetsDto.setAllSheetKbn071811Dto(allSheetKbn071811Dto);
        allSheet0718AssetsDto.setAllSheetKbn071812Dto(allSheetKbn071812Dto);

        /* 不動産の詳細様式7その19 */
        
        AllSheet0719RealEstateDto allSheet0719RealEstateDto = new AllSheet0719RealEstateDto();

        // その1
        Sheet071900RealEstateDto sheet13 = new Sheet071900RealEstateDto();
        sheet13.setKbnRealEstitate(1);

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071900CurrentStatusOfRealEstateUseDto row13 = new Row071900CurrentStatusOfRealEstateUseDto();
        // 
        row13.setIchirenNo(1);
        // 
        row13.setTekiyou("摘要");
        // 
        row13.setYouto("用途");
        // 
        row13.setKankeiShiyousha("使用者との関係");
        // 
        row13.setShiyouYouto("使用の用途");
        // 
        row13.setShiyouMenseki("123.45");
        // 
        row13.setShiyouKakaku(44441L); // NOPMD

        sheet13.getList().add(row13);

        // その3
        Sheet071900RealEstateDto sheet14 = new Sheet071900RealEstateDto();
        sheet14.setKbnRealEstitate(2);

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071900CurrentStatusOfRealEstateUseDto row14 = new Row071900CurrentStatusOfRealEstateUseDto();
        // 
        row14.setIchirenNo(1);
        // 
        row14.setTekiyou("摘要");
        // 
        row14.setYouto("用途");
        // 
        row14.setKankeiShiyousha("使用者との関係");
        // 
        row14.setShiyouYouto("使用の用途");
        // 
        row14.setShiyouMenseki("123.45");
        // 
        row14.setShiyouKakaku(44442L); // NOPMD

        sheet14.getList().add(row14);

        // その3
        Sheet071900RealEstateDto sheet15 = new Sheet071900RealEstateDto();
        sheet15.setKbnRealEstitate(3);

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071900CurrentStatusOfRealEstateUseDto row15 = new Row071900CurrentStatusOfRealEstateUseDto();
        // 
        row15.setIchirenNo(1);
        // 
        row15.setTekiyou("摘要");
        // 
        row15.setYouto("用途");
        // 
        row15.setKankeiShiyousha("使用者との関係");
        // 
        row15.setShiyouYouto("使用の用途");
        // 
        row15.setShiyouMenseki("123.45");
        // 
        row15.setShiyouKakaku(44443L); // NOPMD

        sheet15.getList().add(row15);

        allSheet0719RealEstateDto.getListSheet0719().add(sheet13);
        allSheet0719RealEstateDto.getListSheet0719().add(sheet14);
        allSheet0719RealEstateDto.getListSheet0719().add(sheet15);
        
        
        final int RESULT_ASSET = 12;//区分12*1行
        final int RESULT_REAL_ESTATE = 3;//3区分*1行
        
        int size =insertPoliticalOrganizationEstateAllLogic.practice(documentCode, documentPropertyDto, allSheet0718AssetsDto, allSheet0719RealEstateDto, CreateTestPrivilegeDtoUtil.pracitce());
        
        List<OfferingBalancesheet0718Estate2025Entity> listAsset = offeringBalancesheet0718Estate2025Repository.findByDocumentCodeOrderByOfferingBalancesheet0718EstateId(documentCode);
        List<OfferingBalancesheet0719RealEstate2025Entity> listRealEstate = offeringBalancesheet0719RealEstate2025Repository.findByDocumentCodeOrderByOfferingBalancesheet0719RealEstateId(documentCode);

        //取得数の検証
        assertThat(size).isEqualTo(RESULT_ASSET+RESULT_REAL_ESTATE);
        assertThat(listAsset.size()).isEqualTo(RESULT_ASSET);
        assertThat(listRealEstate.size()).isEqualTo(RESULT_REAL_ESTATE);
        
        
        OfferingBalancesheet0718Estate2025Entity entityAsset01 = listAsset.get(0);
        OfferingBalancesheet0719RealEstate2025Entity entityRealEstate1 = listRealEstate.get(0);
        
        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entityAsset01.getSaishinKbn()).isEqualTo(SetTableDataHistoryUtil.IS_SAISHIN);
        assertThat(entityAsset01.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entityAsset01.getOfferingDate())
                .isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entityAsset01.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entityAsset01.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entityAsset01.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entityAsset01.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entityAsset01.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entityAsset01.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entityAsset01.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entityAsset01.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entityAsset01.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());
        
        /* 区分1 */
        // 
        //row1.setIchirenNo(1);
        assertThat(entityAsset01.getIchirenNo()).isEqualTo(row1.getIchirenNo());
        
        // 
        //row1.setTekiyou("摘要");
        assertThat(entityAsset01.getTekiyou()).isEqualTo(row1.getTekiyou());
        
        // 
        //row1.setKingaku(30001L);
        assertThat(entityAsset01.getKingaku()).isEqualTo(row1.getKingaku());
        
        // 
        //row1.setAccrualDate("R4/12/1"); // NOPMD
        assertThat(entityAsset01.getAccrualDate()).isEqualTo(row1.getAccrualDate());
        
        // 
        //row1.setBiko("備考");
        assertThat(entityAsset01.getBiko()).isEqualTo(row1.getBiko());

        
        /* 区分2 */
        OfferingBalancesheet0718Estate2025Entity entityAsset02 = listAsset.get(1);
        assertThat(entityAsset02.getKingaku()).isEqualTo(row2.getKingaku());
        
        /* 区分3 */
        OfferingBalancesheet0718Estate2025Entity entityAsset03 = listAsset.get(2);
        assertThat(entityAsset03.getKingaku()).isEqualTo(row3.getKingaku());

        /* 区分4 */
        OfferingBalancesheet0718Estate2025Entity entityAsset04 = listAsset.get(3);
        assertThat(entityAsset04.getKingaku()).isEqualTo(row4.getKingaku());
        
        /* 区分5 */
        OfferingBalancesheet0718Estate2025Entity entityAsset05 = listAsset.get(4);
        assertThat(entityAsset05.getKingaku()).isEqualTo(row5.getKingaku());
        
        /* 区分6 */
        OfferingBalancesheet0718Estate2025Entity entityAsset06 = listAsset.get(5);
        assertThat(entityAsset06.getKingaku()).isEqualTo(row6.getKingaku());
        
        /* 区分7 */
        OfferingBalancesheet0718Estate2025Entity entityAsset07 = listAsset.get(6);
        assertThat(entityAsset07.getKingaku()).isEqualTo(row7.getKingaku());
        
        /* 区分8 */
        OfferingBalancesheet0718Estate2025Entity entityAsset08 = listAsset.get(7);
        assertThat(entityAsset08.getKingaku()).isEqualTo(row8.getKingaku());

        /* 区分9 */
        OfferingBalancesheet0718Estate2025Entity entityAsset09 = listAsset.get(8);
        assertThat(entityAsset09.getKingaku()).isEqualTo(row9.getKingaku());
        
        /* 区分10 */
        OfferingBalancesheet0718Estate2025Entity entityAsset10 = listAsset.get(9);
        assertThat(entityAsset10.getKingaku()).isEqualTo(row10.getKingaku());

        /* 区分11 */
        OfferingBalancesheet0718Estate2025Entity entityAsset11 = listAsset.get(10);
        assertThat(entityAsset11.getKingaku()).isEqualTo(row11.getKingaku());
        
        /* 区分12 */
        OfferingBalancesheet0718Estate2025Entity entityAsset12 = listAsset.get(11);
        assertThat(entityAsset12.getKingaku()).isEqualTo(row12.getKingaku());
       
        
        
        
        
        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entityRealEstate1.getSaishinKbn()).isEqualTo(SetTableDataHistoryUtil.IS_SAISHIN);
        assertThat(entityRealEstate1.getHoukokuNen()).isEqualTo(documentPropertyDto.getHoukokuNen());
        assertThat(entityRealEstate1.getOfferingDate())
                .isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entityRealEstate1.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entityRealEstate1.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entityRealEstate1.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entityRealEstate1.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entityRealEstate1.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entityRealEstate1.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entityRealEstate1.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entityRealEstate1.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entityRealEstate1.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());
        

        //区分
        assertThat(entityRealEstate1.getRealEstateKbn()).isEqualTo(RealEstateKbnConstants.KBN_LAND);
        assertThat(entityRealEstate1.getRealEstateKbnName()).isEqualTo(RealEstateKbnConstants.KBN_LAND_TEXT);
        assertThat(entityRealEstate1.getIchirenNo()).isEqualTo(row13.getIchirenNo());
        assertThat(entityRealEstate1.getTekiyou()).isEqualTo(row13.getTekiyou());
        assertThat(entityRealEstate1.getYouto()).isEqualTo(row13.getYouto());
        assertThat(entityRealEstate1.getKankeiShiyousha()).isEqualTo(row13.getKankeiShiyousha());
        assertThat(entityRealEstate1.getShiyouYouto()).isEqualTo(row13.getShiyouYouto());
        assertThat(entityRealEstate1.getShiyouMenseki()).isEqualTo(row13.getShiyouMenseki());
        assertThat(entityRealEstate1.getShiyouKakaku()).isEqualTo(row13.getShiyouKakaku());

        /* 建物 */
        OfferingBalancesheet0719RealEstate2025Entity entityRealEstate2 = listRealEstate.get(1);
        assertThat(entityRealEstate2.getRealEstateKbn()).isEqualTo(RealEstateKbnConstants.KBN_BUILDING);
        assertThat(entityRealEstate2.getRealEstateKbnName()).isEqualTo(RealEstateKbnConstants.KBN_BUILDING_TEXT);
        assertThat(entityRealEstate2.getShiyouKakaku()).isEqualTo(row14.getShiyouKakaku());


        /* 地上権 */
        OfferingBalancesheet0719RealEstate2025Entity entityRealEstate3 = listRealEstate.get(2);
        assertThat(entityRealEstate3.getRealEstateKbn()).isEqualTo(RealEstateKbnConstants.KBN_SURFACE_RIGHTS);
        assertThat(entityRealEstate3.getRealEstateKbnName()).isEqualTo(RealEstateKbnConstants.KBN_SURFACE_RIGHTS_TEXT);
        assertThat(entityRealEstate3.getShiyouKakaku()).isEqualTo(row15.getShiyouKakaku());

    }

}
