package mitei.mitei.investigate.report.balance.politician.logic.natural_search.y2025;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeSearchLineDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetOutcome2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetOutcome2025Repository;

/**
 * 政治資金収支報告書収入・支出検索(2025年)
 */
@Component
public class NaturalAllSentenceSearchY2025Logic {

    /** 政治資金収支報告書収入2025年収入 */
    @Autowired
    private OfferingBalancesheetIncome2025Repository offeringBalancesheetIncome2025Repository;

    /** 政治資金収支報告書収入2025年支出 */
    @Autowired
    private OfferingBalancesheetOutcome2025Repository offeringBalancesheetOutcome2025Repository;

    /** 空白文字 */
    private static final String BLANK = "";

    /** id区切り文字 */
    private static final String ID_SPLITTER = "-";

    /** カンマ区切りフォーマッタ */
    private NumberFormat numberFormat;

    /**
     * 検索を行う
     *
     * @param searchConditionDto 検索条件Dto
     */
    public IncomeAndOutcomeNaturalSearchResultDto practice(
            final IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionDto) {

        // fieldでfinal staticインスタンス生成すると望ましくないといわれる
        numberFormat = NumberFormat.getNumberInstance();

        String searchWords = searchConditionDto.getSearchWords();

        IncomeAndOutcomeNaturalSearchResultDto searchResultDto = new IncomeAndOutcomeNaturalSearchResultDto();
        searchResultDto.setSearchWords(searchWords);
        
        List<IncomeAndOutcomeSearchLineDto> listIncome = searchResultDto.getListIncome();
        List<IncomeAndOutcomeSearchLineDto> listOutcome = searchResultDto.getListOutcome();
        
        // 政治資金収支報告書収入
        if (searchConditionDto.getIsSearchIncome()) {
            // 件数
            searchResultDto.setCountIncome(offeringBalancesheetIncome2025Repository.findFullTextCount(searchWords,
                    searchConditionDto.getStartDate(), searchConditionDto.getEndDate()));

            // 取得する実データ
            List<OfferingBalancesheetIncome2025Entity> listTemp = offeringBalancesheetIncome2025Repository.findFullText(
                    searchWords, searchConditionDto.getOffsetIncome(), searchConditionDto.getStartDate(),
                    searchConditionDto.getEndDate());

            for (OfferingBalancesheetIncome2025Entity entity : listTemp) {
                listIncome.add(this.creatDto(entity));
            }

        }

        // 政治資金収支報告書支出
        if (searchConditionDto.getIsSearchOutcome()) {
            searchResultDto.setCountOutcome(offeringBalancesheetOutcome2025Repository.findFullTextCount(searchWords,
                    searchConditionDto.getStartDate(), searchConditionDto.getEndDate()));
            
            // 取得する実データ
            List<OfferingBalancesheetOutcome2025Entity> listTemp = offeringBalancesheetOutcome2025Repository
                    .findFullText(searchWords, searchConditionDto.getOffsetOutcome(), searchConditionDto.getStartDate(),
                            searchConditionDto.getEndDate());
            for (OfferingBalancesheetOutcome2025Entity entity : listTemp) {
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
    private IncomeAndOutcomeSearchLineDto creatDto(final OfferingBalancesheetOutcome2025Entity entity) {
        IncomeAndOutcomeSearchLineDto lineDto = new IncomeAndOutcomeSearchLineDto();
        BeanUtils.copyProperties(entity, lineDto);
        // front側でitemName使用で統一する
        lineDto.setItemName("(" + entity.getHimoku() + ")" + entity.getMokuteki());

        // id
        StringBuilder builderId = new StringBuilder();
        builderId.append(entity.getHoukokuNen()).append(ID_SPLITTER).append(entity.getDocumentCode())
                .append(ID_SPLITTER).append(entity.getYoushikiKbn()).append(ID_SPLITTER)
                .append(entity.getYoushikiEdaKbn()).append(ID_SPLITTER).append(entity.getIchirenNo());
        lineDto.setItemId(builderId.toString());

        // 金額関係編集
        lineDto.setKingakuIncomeText(BLANK);
        lineDto.setKingakuOutcomeText(numberFormat.format(lineDto.getKingaku()));
        lineDto.setKingakuShuukei(-1 * lineDto.getKingaku());

        return lineDto;
    }

    /**
     * 検索結果行Dtoを生成する
     *
     * @param entity 収入Entity
     * @return 検索結果行Dto
     */
    private IncomeAndOutcomeSearchLineDto creatDto(final OfferingBalancesheetIncome2025Entity entity) {
        IncomeAndOutcomeSearchLineDto lineDto = new IncomeAndOutcomeSearchLineDto();
        BeanUtils.copyProperties(entity, lineDto);

        // id
        StringBuilder builderId = new StringBuilder();
        builderId.append(entity.getHoukokuNen()).append(ID_SPLITTER).append(entity.getDocumentCode())
                .append(ID_SPLITTER).append(entity.getYoushikiKbn()).append(ID_SPLITTER)
                .append(entity.getYoushikiEdaKbn()).append(ID_SPLITTER).append(entity.getIchirenNo());
        lineDto.setItemId(builderId.toString());

        // 金額関係編集
        lineDto.setKingakuIncomeText(numberFormat.format(lineDto.getKingaku()));
        lineDto.setKingakuOutcomeText(BLANK);
        lineDto.setKingakuShuukei(lineDto.getKingaku());

        return lineDto;
    }

}
