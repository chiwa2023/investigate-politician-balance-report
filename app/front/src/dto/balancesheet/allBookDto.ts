import AllSheet0701CoverAndOrganizationDetailsDto  from "./sheet01/allSheet0701CoverAndOrganizationDetailsDto";

export default interface AllBookInterface {

    /** ブックヘッダ */
    //private AllBookHeaderDto allBookHeaderDto;

    /** 入力有無フラグ */
    //private AllBookUmuInputDataDto allBookUmuInputDataDto;

    /** シート1 */
    allSheet0701CoverAndOrganizationDetailsDto: AllSheet0701CoverAndOrganizationDetailsDto;

    /** シート2 */
    //private AllSheet0702SummaryTableIncomeDto allSheet0702SummaryTableIncomeDto;

    /** シート3 */
    //private AllSheet0703JournalAndOtherDto allSheet0703JournalAndOtherDto;

    /** シート4 */
    //private AllSheet0704BorrowedMoneyDto allSheet0704BorrowedMoneyDto;

    /** シート5 */
    //private AllSheet0705IncomeRelatedToGrantsDto allSheet0705IncomeRelatedToGrantsDto;

    /** シート6 */
    //private AllSheet0706OtherIncomeDto allSheet0706OtherIncomeDto;

    /** シート7 */
    //private AllSheet0707DonateDto allSheet0707DonateDto;

    /** シート8 */
    //private AllSheet0708MediationDto allSheet0708MediationDto;

    /** シート9 */
    //private AllSheet0709AnonymousInPoliticalPartyDto allSheet0709AnonymousInPoliticalPartyDto;

    /** シート10 */
    //private AllSheet0710SpecificPartyDto allSheet0710SpecificPartyDto;

    /** シート11 */
    //private AllSheet0711ConsiderationPartyDto allSheet0711ConsiderationPartyDto;

    /** シート12 */
    //private AllSheet0712PartyMediationDto allSheet0712PartyMediationDto;

    /** シート13 */
    //private AllSheet0713ListOfExpenditureItemsDto allSheet0713ListOfExpenditureItemsDto;

    /** シート14 */
    //private AllSheet0714ConstsDto allSheet0714ConstsDto;

    /** シート15 */
    //private AllSheet0715ExpenseDto allSheet0715ExpenseDto;

    /** シート16 */
    //private AllSheet0716RelatedToGrantsDtoDto allSheet0716RelatedToGrantsDtoDto;

    /** シート17 */
    //private AllSheet0717SummaryTableOfAssetsDto allSheet0717SummaryTableOfAssetsDto;

    /** シート18 */
    //private AllSheet0718AssetsDto allSheet0718AssetsDto;

    /** シート19 */
    //private AllSheet0719RealEstateDto allSheet0719RealEstateDto;

    /** シート20 */
    //private AllSheet0720OathDto allSheet0720OathDto;

    /** シート8 */
    //private AllSheet0800DifficultCollectReceiptDto allSheet0800DifficultCollectReceiptDto;

    /** シート8の2 */
    //private AllSheet0802WithdrawalItemsByTransferDto allSheet0802WithdrawalItemsByTransferDto;

    /** 租税特別措置 */
    //private AllSheet411710DonationClassifyDto allSheet411710DonationClassifyDto;

}


export default class AllBookDto implements AllBookInterface {

    /** シート1 */
    allSheet0701CoverAndOrganizationDetailsDto: AllSheet0701CoverAndOrganizationDetailsDto;

    /**
     * Creates an instance of AllBookDto.
     * @param {AllBookInterface} [allBookInterface]
     * @memberof AllBookDto
     */
    constructor(allBookInterface?: AllBookInterface) {

        if (allBookInterface?.allSheet0701CoverAndOrganizationDetailsDto !== undefined) {
            this.allSheet0701CoverAndOrganizationDetailsDto = allBookInterface?.allSheet0701CoverAndOrganizationDetailsDto;
        }
        else {
            this.allSheet0701CoverAndOrganizationDetailsDto = new AllSheet0701CoverAndOrganizationDetailsDto();
        }

    }
}