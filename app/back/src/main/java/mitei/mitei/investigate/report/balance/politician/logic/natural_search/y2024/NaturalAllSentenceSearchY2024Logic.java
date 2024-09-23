package mitei.mitei.investigate.report.balance.politician.logic.natural_search.y2024;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeSearchLineDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetOutcome2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetOutcome2024Repository;

/**
 * 政治資金収支報告書収入・支出検索(2024年)
 */
@Component
public class NaturalAllSentenceSearchY2024Logic {

    /** 政治資金収支報告書収入2024年収入 */
    @Autowired
    private OfferingBalancesheetIncome2024Repository offeringBalancesheetIncome2024Repository;

    /** 政治資金収支報告書収入2024年支出 */
    @Autowired
    private OfferingBalancesheetOutcome2024Repository offeringBalancesheetOutcome2024Repository;

    /**
     * 検索を行う
     *
     * @param searchConditionDto 検索条件Dto
     */
    public IncomeAndOutcomeNaturalSearchResultDto practice(
            final IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto) {

        String searchWords = searchConditionDto.getSearchWords();

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = new IncomeAndOutcomeNaturalSearchResultDto();

        List<IncomeAndOutcomeSearchLineDto> listIncome = searchResultDto.getListIncome();
        List<IncomeAndOutcomeSearchLineDto> listOutcome = searchResultDto.getListOutcome();

        // 政治資金収支報告書収入
        if (searchConditionDto.getIsSearchIncome()) {
            // 件数
            searchResultDto.setCountIncome(offeringBalancesheetIncome2024Repository.findFullTextCount(searchWords,
                    searchConditionDto.getStartDate(), searchConditionDto.getEndDate()));

            // 取得する実データ
            List<OfferingBalancesheetIncome2024Entity> listTemp = offeringBalancesheetIncome2024Repository.findFullText(
                    searchWords, searchConditionDto.getOffsetIncome(), searchConditionDto.getStartDate(),
                    searchConditionDto.getEndDate());

            for (OfferingBalancesheetIncome2024Entity entity : listTemp) {
                listIncome.add(this.creatDto(entity));
            }

        }

        // 政治資金収支報告書支出
        if (searchConditionDto.getIsSearchIncome()) {
            searchResultDto.setCountOutcome(offeringBalancesheetOutcome2024Repository.findFullTextCount(searchWords,
                    searchConditionDto.getStartDate(), searchConditionDto.getEndDate()));

            // 取得する実データ
            List<OfferingBalancesheetOutcome2024Entity> listTemp = offeringBalancesheetOutcome2024Repository
                    .findFullText(searchWords, searchConditionDto.getOffsetIncome(), searchConditionDto.getStartDate(),
                            searchConditionDto.getEndDate());
            for (OfferingBalancesheetOutcome2024Entity entity : listTemp) {
                listOutcome.add(this.creatDto(entity));
            }
        }

        return searchResultDto;
    }

    /**
     * 検索結果行Dtoを生成する
     *
     * @param entity 支出Entity
     * @return 検索結果行Dto
     */
    private IncomeAndOutcomeSearchLineDto creatDto(final OfferingBalancesheetOutcome2024Entity entity) {
        IncomeAndOutcomeSearchLineDto lineDto = new IncomeAndOutcomeSearchLineDto();
        BeanUtils.copyProperties(entity, lineDto);
        // front側でitemName使用で統一する
        lineDto.setItemName("(" + entity.getHimoku() + ")" + entity.getMokuteki());
        return lineDto;
    }

    /**
     * 検索結果行Dtoを生成する
     *
     * @param entity 収入Entity
     * @return 検索結果行Dto
     */
    private IncomeAndOutcomeSearchLineDto creatDto(final OfferingBalancesheetIncome2024Entity entity) {
        IncomeAndOutcomeSearchLineDto lineDto = new IncomeAndOutcomeSearchLineDto();
        BeanUtils.copyProperties(entity, lineDto);

        return lineDto;
    }

}
