package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.kanrensha.KanrenshaBalancesheetConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeSearchLineDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2023.OfferingBalancesheetIncome2023Entity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2023.OfferingBalancesheetOutcome2023Entity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kanrensha.CreateKanrenshaSearchYoushikiKbnIncomeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kanrensha.CreateKanrenshaSearchYoushikiKbnOutcomeLogic;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023.OfferingBalancesheetIncome2023Repository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023.OfferingBalancesheetOutcome2023Repository;
import reactor.util.function.Tuple4;
import reactor.util.function.Tuple6;

/**
 * 関連者から収入・支出データを取得する
 */
@Component
public class ShowBakancesheetReportByKanrenshaY2023Logic {

    /** 収支報告書収入テーブルRepository(2023) */
    @Autowired
    private OfferingBalancesheetIncome2023Repository offeringBalancesheetIncome2023Repository;

    /** 収支報告書支出テーブルRepository(2023) */
    @Autowired
    private OfferingBalancesheetOutcome2023Repository offeringBalancesheetOutcome2023Repository;

    /** 収入様式番号作成Logic */
    @Autowired
    private CreateKanrenshaSearchYoushikiKbnIncomeLogic createKanrenshaSearchYoushikiKbnIncomeLogic;

    /** 支出様式番号作成Logic */
    @Autowired
    private CreateKanrenshaSearchYoushikiKbnOutcomeLogic createKanrenshaSearchYoushikiKbnOutcomeLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    public IncomeAndOutcomeNaturalSearchResultDto practice(final KanrenshaBalancesheetConditionCapsuleDto capsuleDto) {

        final Integer zero = 0;
        Integer partnerCode = capsuleDto.getRelationCode();
        Integer partnerKbn = capsuleDto.getRelationKbn();
        Integer offset = capsuleDto.getOffset();

        Tuple6<List<Integer>, List<Integer>, List<Integer>, List<Integer>, List<Integer>, List<Integer>> tuple6 = createKanrenshaSearchYoushikiKbnIncomeLogic
                .practice(capsuleDto);
        Tuple4<List<Integer>, List<Integer>, List<Integer>, List<Integer>> tuple4 = createKanrenshaSearchYoushikiKbnOutcomeLogic
                .practice(capsuleDto);

        // 件数が0の時は件数を(再)取得する
        IncomeAndOutcomeNaturalSearchResultDto resultDto = new IncomeAndOutcomeNaturalSearchResultDto();
        if (capsuleDto.getAllCountIncome().equals(zero) && capsuleDto.getAllCountOutcome().equals(zero)) {
            // 件数取得処理
            resultDto.setCountIncome(offeringBalancesheetIncome2023Repository.findCountDataByPartnerCode(partnerKbn,
                    partnerCode, tuple6.getT1(), tuple6.getT2(), tuple6.getT3(), tuple6.getT4(), tuple6.getT5(),
                    tuple6.getT6()));
            resultDto.setCountOutcome(offeringBalancesheetOutcome2023Repository.findCountDataByPartnerCode(partnerKbn,
                    partnerCode, tuple4.getT1(), tuple4.getT2(), tuple4.getT3(), tuple4.getT4()));
        }

        // 0件でない場合はページングに合わせて検索
        if (resultDto.getCountIncome() > 0) {

            Pageable pageable = Pageable.ofSize(offset).withPage(capsuleDto.getPageNumIncome());

            List<OfferingBalancesheetIncome2023Entity> listEntity = offeringBalancesheetIncome2023Repository
                    .findDataByPartnerCode(partnerKbn, partnerCode, tuple6.getT1(), tuple6.getT2(), tuple6.getT3(),
                            tuple6.getT4(), tuple6.getT5(), tuple6.getT6(), pageable);

            resultDto.setListIncome(this.createIncomeLine(listEntity));
        }

        // 0件でない場合はページングに合わせて検索
        if (resultDto.getCountIncome() > 0) {

            Pageable pageable = Pageable.ofSize(offset).withPage(capsuleDto.getPageNumIncome());

            List<OfferingBalancesheetOutcome2023Entity> listEntity = offeringBalancesheetOutcome2023Repository
                    .findDataByPartnerCode(partnerKbn, partnerCode, tuple4.getT1(), tuple4.getT2(), tuple4.getT3(),
                            tuple4.getT4(), pageable);

            resultDto.setListOutcome(this.createOutcomeLine(listEntity));
        }

        resultDto.setIsOk(true);

        return resultDto;
    }

    private List<IncomeAndOutcomeSearchLineDto> createIncomeLine(
            final List<OfferingBalancesheetIncome2023Entity> listEntity) {

        List<IncomeAndOutcomeSearchLineDto> list = new ArrayList<>();

        for (OfferingBalancesheetIncome2023Entity entity : listEntity) {

            list.add(this.createIncomeDto(entity));
        }
        return list;
    }

    private List<IncomeAndOutcomeSearchLineDto> createOutcomeLine(
            final List<OfferingBalancesheetOutcome2023Entity> listEntity) {

        List<IncomeAndOutcomeSearchLineDto> list = new ArrayList<>();

        for (OfferingBalancesheetOutcome2023Entity entity : listEntity) {

            list.add(this.createOutcomeDto(entity));
        }
        return list;
    }

    private IncomeAndOutcomeSearchLineDto createIncomeDto(final OfferingBalancesheetIncome2023Entity entity) {
        IncomeAndOutcomeSearchLineDto dto = new IncomeAndOutcomeSearchLineDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private IncomeAndOutcomeSearchLineDto createOutcomeDto(final OfferingBalancesheetOutcome2023Entity entity) {
        IncomeAndOutcomeSearchLineDto dto = new IncomeAndOutcomeSearchLineDto();
        BeanUtils.copyProperties(entity, dto);
        dto.setItemName(entity.getHimoku());
        return dto;
    }

}
