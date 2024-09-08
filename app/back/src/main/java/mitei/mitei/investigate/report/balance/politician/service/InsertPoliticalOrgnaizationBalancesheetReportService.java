package mitei.mitei.investigate.report.balance.politician.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.RegistPoliticalOrgBalancesheetReportRresultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganization08000Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganization0802Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganizationEstateAllLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganizationIncomeAllLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganizationOutcomeAllLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganizationSheet0701And0720Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganizationSummaryLogic;

/**
 * 政治資金収支報告書提出分登録サービス
 */
@Service
public class InsertPoliticalOrgnaizationBalancesheetReportService {

    /** 政治資金収支報告書様式8領収書を徴しがたかったもの登録Logic */
    @Autowired
    private InsertPoliticalOrganization08000Logic insertPoliticalOrganization08000Logic;

    /** 政治資金収支報告書様式8その2支出項目別内訳登録Logic */
    @Autowired
    private InsertPoliticalOrganization0802Logic insertPoliticalOrganization0802Logic;

    /** 政治資金収支報告書資産登録Logic */
    @Autowired
    private InsertPoliticalOrganizationEstateAllLogic insertPoliticalOrganizationEstateAllLogic;

    /** 政治資金収支報告書収入登録Logic */
    @Autowired
    private InsertPoliticalOrganizationIncomeAllLogic insertPoliticalOrganizationIncomeAllLogic;

    /** 政治資金収支報告書支出登録Logic */
    @Autowired
    private InsertPoliticalOrganizationOutcomeAllLogic insertPoliticalOrganizationOutcomeAllLogic;

    /** 政治資金収支報告書表紙と誓約書登録Logic */
    @Autowired
    private InsertPoliticalOrganizationSheet0701And0720Logic insertPoliticalOrganizationSheet0701And0720Logic;

    /** 政治資金収支報告書集計表登録Logic */
    @Autowired
    private InsertPoliticalOrganizationSummaryLogic insertPoliticalOrganizationSummaryLogic;

    /**
     * 政治資金収支報告書を独自形式で保存する
     *
     * @param documentPropertyDto 文書属性Dto
     * @param allBookDto          政治資金収支報告書Dto
     * @param checkPrivilegeDto   権限確認Dto
     * @return 登録結果Dto
     */
    public RegistPoliticalOrgBalancesheetReportRresultDto practice(
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto, final AllBookDto allBookDto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        // 政治資金収支報告書表紙と宣誓書(その1と20)
        Long documentCode = insertPoliticalOrganizationSheet0701And0720Logic.practice(documentPropertyDto, allBookDto,
                checkPrivilegeDto);

        // 政治資金収支報告集計表
        int sizeSummary = insertPoliticalOrganizationSummaryLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0702SummaryTableIncomeDto(),
                allBookDto.getAllSheet0713ListOfExpenditureItemsDto(),
                allBookDto.getAllSheet0717SummaryTableOfAssetsDto(), checkPrivilegeDto);

        // 政治資金収支報告収入
        int sizeIncome = insertPoliticalOrganizationIncomeAllLogic.practice(documentCode, documentPropertyDto,
                allBookDto, checkPrivilegeDto);

        // 政治資金収支報告支出
        int sizeOutcome = insertPoliticalOrganizationOutcomeAllLogic.practice(documentCode, documentPropertyDto,
                allBookDto, checkPrivilegeDto);

        // 政治資金収支報告資産
        int sizeEstate = insertPoliticalOrganizationEstateAllLogic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0718AssetsDto(), allBookDto.getAllSheet0719RealEstateDto(), checkPrivilegeDto);

        // 政治資金収支報告
        int size0800 = insertPoliticalOrganization08000Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0800DifficultCollectReceiptDto(), checkPrivilegeDto);

        // 政治資金収支報告
        int size0802 = insertPoliticalOrganization0802Logic.practice(documentCode, documentPropertyDto,
                allBookDto.getAllSheet0802WithdrawalItemsByTransferDto(), checkPrivilegeDto);

        // 登録失敗はTransactionをロールバックしないといけないのですべて例外
        RegistPoliticalOrgBalancesheetReportRresultDto resultDto = new RegistPoliticalOrgBalancesheetReportRresultDto();

        resultDto.setDocumentCode(documentCode);
        resultDto.setSuccessCount(1 + sizeSummary + sizeIncome + sizeOutcome + sizeEstate + size0800 + size0802);
        resultDto.setIsOk(true);
        resultDto.setMessage(resultDto.getSuccessCount() + "件登録しました");

        return resultDto;
    }

}
