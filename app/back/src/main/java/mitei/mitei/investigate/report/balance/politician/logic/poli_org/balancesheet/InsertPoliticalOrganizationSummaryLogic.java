package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0702SummaryTableIncomeDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0713ListOfExpenditureItemsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllSheet0717SummaryTableOfAssetsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070200SummaryTableIncomeExpenditureDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071300ListOfExpenditureItemsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet071700SummaryTableOfAssetsDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingBalancesheet0702And0713And0717Summary2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingBalancesheet0702And0713And0717Summary2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書の集計表(収入・支出・資産)を登録する
 */
@Component
public class InsertPoliticalOrganizationSummaryLogic {

    /** 政治資金収支報告書集計表登録Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2025Repository offeringBalancesheet0702And0713And0717Summary2025Repository;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param allSheet0702dto     様式7その2収入の集計
     * @param allSheet0713dto     様式7その13支出の集計
     * @param allSheet0717dto     様式7その17資産の集計
     * @param checkPrivilegeDto   権限Dto
     * @return 登録件数
     */
    public int practice(final Long documentCode,
            final BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto,
            final AllSheet0702SummaryTableIncomeDto allSheet0702dto,
            final AllSheet0713ListOfExpenditureItemsDto allSheet0713dto,
            final AllSheet0717SummaryTableOfAssetsDto allSheet0717dto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingBalancesheet0702And0713And0717Summary2025Entity summaryEntity = new OfferingBalancesheet0702And0713And0717Summary2025Entity();

        summaryEntity.setDocumentCode(documentCode);
        BeanUtils.copyProperties(documentPropertyDto, summaryEntity);

        // シート2を登録する
        Sheet070200SummaryTableIncomeExpenditureDto sheet0702Dto = allSheet0702dto
                .getSheet070200SummaryTableIncomeExpenditureDto();

        BeanUtils.copyProperties(sheet0702Dto, summaryEntity);

        // シート13を登録する
        Sheet071300ListOfExpenditureItemsDto sheet0713Dto = allSheet0713dto.getSheet071300ListOfExpenditureItemsDto();

        BeanUtils.copyProperties(sheet0713Dto, summaryEntity);

        // シート17を登録する
        Sheet071700SummaryTableOfAssetsDto sheet0717Dto = allSheet0717dto.getSheet071700SummaryTableOfAssetsDto();

        BeanUtils.copyProperties(sheet0717Dto, summaryEntity);


        SetTableDataHistoryUtil.practice(checkPrivilegeDto, summaryEntity, DataHistoryStatusConstants.INSERT);

        // 同一識別コードの取得
        Long code = 1L;
        Optional<OfferingBalancesheet0702And0713And0717Summary2025Entity> optional = offeringBalancesheet0702And0713And0717Summary2025Repository
                .findFirstByOrderByOfferingBalancesheet0702And0713And0717SummaryCodeDesc();
        if (!optional.isEmpty()) {
            code = code + optional.get().getOfferingBalancesheet0702And0713And0717SummaryCode();
        }

        summaryEntity.setOfferingBalancesheet0702And0713And0717SummaryCode(code);
        
        Long idRecord = offeringBalancesheet0702And0713And0717Summary2025Repository.save(summaryEntity)
                .getOfferingBalancesheet0702And0713And0717SummaryId();

        if (idRecord.equals(0L)) {
            return 0;// 異常登録(idがauto incrementされていない)
        } else {
            return 1; // 正常登録(1行しか登録しない)
        }
    }
}
