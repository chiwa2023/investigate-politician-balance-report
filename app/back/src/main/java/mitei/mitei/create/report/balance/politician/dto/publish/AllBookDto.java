package mitei.mitei.create.report.balance.politician.dto.publish; // NOPMD

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 全「Book」XMLを表現するDto 互換性のためには…Excelじゃないけど「Book」root必要(笑)
 */
@JacksonXmlRootElement(localName = "BOOK")
public class AllBookDto implements Serializable {

    //TODO 最後は上書き、または文書発行部分のみを切り出したjarからimportするのでしばらくこだわらない
    
    /** serialId */
    private static final long serialVersionUID = 1L;

    ///** ブックヘッダ */
    //@JacksonXmlProperty(localName = "HEAD")
    //private AllBookHeaderDto allBookHeaderDto;

    ///** 入力有無フラグ */
    //@JacksonXmlProperty(localName = "SYUUSHI_UMU_FLG")
    //private AllBookUmuInputDataDto allBookUmuInputDataDto;

    /** シート1 */
    @JacksonXmlProperty(localName = "SYUUSHI07_01")
    private AllSheet0701CoverAndOrganizationDetailsDto allSheet0701CoverAndOrganizationDetailsDto;

    ///** シート2 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_02")
    //private AllSheet0702SummaryTableIncomeDto allSheet0702SummaryTableIncomeDto;

    ///** シート3 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_03")
    //private AllSheet0703JournalAndOtherDto allSheet0703JournalAndOtherDto;

    ///** シート4 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_04")
    //private AllSheet0704BorrowedMoneyDto allSheet0704BorrowedMoneyDto;

    ///** シート5 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_05")
    //private AllSheet0705IncomeRelatedToGrantsDto allSheet0705IncomeRelatedToGrantsDto;

    ///** シート6 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_06")
    //private AllSheet0706OtherIncomeDto allSheet0706OtherIncomeDto;

    ///** シート7 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_07")
    //private AllSheet0707DonateDto allSheet0707DonateDto;

    ///** シート8 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_08")
    //private AllSheet0708MediationDto allSheet0708MediationDto;

    ///** シート9 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_09")
    //private AllSheet0709AnonymousInPoliticalPartyDto allSheet0709AnonymousInPoliticalPartyDto;

    ///** シート10 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_10")
    //private AllSheet0710SpecificPartyDto allSheet0710SpecificPartyDto;

    ///** シート11 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_11")
    //private AllSheet0711ConsiderationPartyDto allSheet0711ConsiderationPartyDto;

    ///** シート12 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_12")
    //private AllSheet0712PartyMediationDto allSheet0712PartyMediationDto;

    ///** シート13 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_13")
    //private AllSheet0713ListOfExpenditureItemsDto allSheet0713ListOfExpenditureItemsDto;

    ///** シート14 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_14")
    //private AllSheet0714ConstsDto allSheet0714ConstsDto;

    ///** シート15 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_15")
    //private AllSheet0715ExpenseDto allSheet0715ExpenseDto;

    ///** シート16 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_16")
    //private AllSheet0716RelatedToGrantsDtoDto allSheet0716RelatedToGrantsDtoDto;

    ///** シート17 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_17")
    //private AllSheet0717SummaryTableOfAssetsDto allSheet0717SummaryTableOfAssetsDto;

    ///** シート18 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_18")
    //private AllSheet0718AssetsDto allSheet0718AssetsDto;

    ///** シート19 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_19")
    //private AllSheet0719RealEstateDto allSheet0719RealEstateDto;

    ///** シート20 */
    //@JacksonXmlProperty(localName = "SYUUSHI07_20")
    //private AllSheet0720OathDto allSheet0720OathDto;

    ///** シート8 */
    //@JacksonXmlProperty(localName = "SYUUSHI08")
    //private AllSheet0800DifficultCollectReceiptDto allSheet0800DifficultCollectReceiptDto;

    ///** シート8の2 */
    //@JacksonXmlProperty(localName = "SYUUSHI08_02")
    //private AllSheet0802WithdrawalItemsByTransferDto allSheet0802WithdrawalItemsByTransferDto;

    ///** 租税特別措置 */
    //@JacksonXmlProperty(localName = "SYUUSHI_KIFUKOUJYO")
    //private AllSheet411710DonationClassifyDto allSheet411710DonationClassifyDto;

    /**
     * シート1全体を取得する
     *
     * @return シート1全体
     */
    public AllSheet0701CoverAndOrganizationDetailsDto getAllSheet0701CoverAndOrganizationDetailsDto() {
        return allSheet0701CoverAndOrganizationDetailsDto;
    }

    /**
     * シート1全体を設定する
     *
     * @param allSheet0701CoverAndOrganizationDetailsDto シート1全体
     */
    public void setAllSheet0701CoverAndOrganizationDetailsDto(
            final AllSheet0701CoverAndOrganizationDetailsDto allSheet0701CoverAndOrganizationDetailsDto) {
        this.allSheet0701CoverAndOrganizationDetailsDto = allSheet0701CoverAndOrganizationDetailsDto;
    }


}
