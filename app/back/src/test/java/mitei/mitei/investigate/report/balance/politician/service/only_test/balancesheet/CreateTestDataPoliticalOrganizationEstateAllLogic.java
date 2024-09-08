package mitei.mitei.investigate.report.balance.politician.service.only_test.balancesheet; // NOPMD

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
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

/**
 * 政治資金収支報告書資産(その18と19)
 */
public class CreateTestDataPoliticalOrganizationEstateAllLogic {
    // CHECKSTYLE:OFF

    /**
     * テストデータ生成
     * @param allBookDto 政治資金収支報告書
     */
    public void practice(final AllBookDto allBookDto) { // NOPMD

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
        row3.setIchirenNo(1);
        row3.setTekiyou("摘要");
        row3.setKingaku(30003L); // NOPMD
        row3.setAccrualDate("R4/12/9");
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
        row4.setIchirenNo(1);
        row4.setTekiyou("摘要");
        row4.setKingaku(30004L); // NOPMD
        row4.setAccrualDate("R4/12/10");
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
        row5.setIchirenNo(1);
        row5.setTekiyou("摘要");
        row5.setKingaku(30005L); // NOPMD
        row5.setAccrualDate("R4/12/11");
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
        row6.setIchirenNo(1);
        row6.setTekiyou("摘要");
        row6.setKingaku(30006L); // NOPMD
        row6.setAccrualDate("R4/12/12");
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
        row7.setIchirenNo(1);
        row7.setTekiyou("摘要");
        row7.setKingaku(30007L); // NOPMD
        row7.setAccrualDate("R4/12/13");
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
        row8.setIchirenNo(1);
        row8.setTekiyou("摘要");
        row8.setKingaku(30008L); // NOPMD
        row8.setAccrualDate("R4/12/14");
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
        row9.setIchirenNo(1);
        row9.setTekiyou("摘要");
        row9.setKingaku(30009L); // NOPMD
        row9.setAccrualDate("R4/12/15");
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
        row10.setIchirenNo(1);
        row10.setTekiyou("摘要");
        row10.setKingaku(30010L); // NOPMD
        row10.setAccrualDate("R4/12/16");
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
        row11.setIchirenNo(1);
        row11.setTekiyou("摘要");
        row11.setKingaku(30011L); // NOPMD
        row11.setAccrualDate("R4/12/17");
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
        row12.setIchirenNo(1);
        row12.setTekiyou("摘要");
        row12.setKingaku(30012L); // NOPMD
        row12.setAccrualDate("R4/12/18");
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

        Row071900CurrentStatusOfRealEstateUseDto row13 = new Row071900CurrentStatusOfRealEstateUseDto();
        row13.setIchirenNo(1);
        row13.setTekiyou("摘要");
        row13.setYouto("用途");
        row13.setKankeiShiyousha("使用者との関係");
        row13.setShiyouYouto("使用の用途");
        row13.setShiyouMenseki("123.45");
        row13.setShiyouKakaku(44441L); // NOPMD

        sheet13.getList().add(row13);

        // その3
        Sheet071900RealEstateDto sheet14 = new Sheet071900RealEstateDto();
        sheet14.setKbnRealEstitate(2);

        Row071900CurrentStatusOfRealEstateUseDto row14 = new Row071900CurrentStatusOfRealEstateUseDto();
        row14.setIchirenNo(1);
        row14.setTekiyou("摘要");
        row14.setYouto("用途");
        row14.setKankeiShiyousha("使用者との関係");
        row14.setShiyouYouto("使用の用途");
        row14.setShiyouMenseki("123.45");
        row14.setShiyouKakaku(44442L); // NOPMD

        sheet14.getList().add(row14);

        // その3
        Sheet071900RealEstateDto sheet15 = new Sheet071900RealEstateDto();
        sheet15.setKbnRealEstitate(3);

        // すべてにデータが入っている場合。データ欠損などは作成Logicでの対応が必要
        Row071900CurrentStatusOfRealEstateUseDto row15 = new Row071900CurrentStatusOfRealEstateUseDto();
        row15.setIchirenNo(1);
        row15.setTekiyou("摘要");
        row15.setYouto("用途");
        row15.setKankeiShiyousha("使用者との関係");
        row15.setShiyouYouto("使用の用途");
        row15.setShiyouMenseki("123.45");
        row15.setShiyouKakaku(44443L); // NOPMD

        sheet15.getList().add(row15);

        allSheet0719RealEstateDto.getListSheet0719().add(sheet13);
        allSheet0719RealEstateDto.getListSheet0719().add(sheet14);
        allSheet0719RealEstateDto.getListSheet0719().add(sheet15);

        allBookDto.setAllSheet0718AssetsDto(allSheet0718AssetsDto);
        allBookDto.setAllSheet0719RealEstateDto(allSheet0719RealEstateDto);

    }
}
