package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.party.usage.ConstantsKbn080201Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0802Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0802Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0803Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2024.OfferingPartyUsage0802And0803Report2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0802And0803Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 使途報告書様式8その2(区分1)、区分2を保存する
 */
@Component
public class InsertPartyUsageShito0802And0803Y2024Logic {

    /** 様式8その2区分1と3Repository */
    @Autowired
    private OfferingPartyUsage0802And0803Report2024Repository offeringPartyUsage0802And0803Report2024Repository;

    /**
     * 登録作業を行う
     *
     * @param shito0802Dto 様式8その2(区分1だけ使用)
     * @param shito0803Dto 様式8その3
     */
    public int practice(final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final Shito0802Dto shito0802Dto, final Shito0803Dto shito0803Dto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingPartyUsage0802And0803Report2024Entity reportEntity = new OfferingPartyUsage0802And0803Report2024Entity();

        BeanUtils.copyProperties(shito0803Dto.getSheet0803Dto(), reportEntity);
        BeanUtils.copyProperties(partyUsageDocumentPoliticalPropertyDto, reportEntity);
        reportEntity.setDocumentCode(documentCode);

        List<RowShito0802Dto> list = shito0802Dto.getSheet0802Dto().getKbn080201Dto().getList();

        // 政党交付金（支部政党交付金）の総額 --------------------------------------------①
        // list.get(0).getAmount(); party_all_koufukin
        reportEntity.setPartyAllKoufukin(list.get(ConstantsKbn080201Dto.POS01-1).getAmount());
        
        // 前年末政党基金（支部基金）残高 ------------------------------------------------②
        // list.get(1).getAmount(); last_year_rest
        reportEntity.setLastYearRest   (list.get(ConstantsKbn080201Dto.POS02-1).getAmount());

        // 政党交付金（支部政党交付金）による支出総額（④＋⑤） --------------------------③
        // list.get(2).getAmount(); outcome_koufukin_all
        reportEntity.setOutcomeKoufukinAll   (list.get(ConstantsKbn080201Dto.POS03-1).getAmount());

        // 政党交付金（支部政党交付金）支出充当額総額 ----------------------------------④
        // list.get(3).getAmount(); outcome_koufukin
        reportEntity.setOutcomeKoufukin   (list.get(ConstantsKbn080201Dto.POS04-1).getAmount());

        // 政党基金（支部基金）支出充当額総額 ------------------------------------------⑤
        // list.get(4).getAmount(); outcome_shibu_kikin
        reportEntity.setOutcomeShibuKikin   (list.get(ConstantsKbn080201Dto.POS05-1).getAmount());

        // 政党基金（支部基金）の積立に充てるために取り崩した政党基金（支部基金）の額 ----⑥
        // list.get(5).getAmount(); //withdrawal_funds
        reportEntity.setWithdrawalFunds   (list.get(ConstantsKbn080201Dto.POS06-1).getAmount());

        // 政党基金（支部基金）積立総額（果実を含む。） ----------------------------------⑦
        // list.get(6).getAmount(); accumulate_funds
        reportEntity.setAccumulateFunds   (list.get(ConstantsKbn080201Dto.POS07-1).getAmount());

        // 政党基金（支部基金）の運用により収受した果実の総額
        // list.get(7).getAmount(); funds_sum_gain
        reportEntity.setFundsSumGain   (list.get(ConstantsKbn080201Dto.POS08-1).getAmount());

        // 本年末等政党基金（支部基金）残高（②－⑤－⑥＋⑦） ----------------------------⑧
        // list.get(8).getAmount(); this_year_rest
        reportEntity.setThisYearRest   (list.get(ConstantsKbn080201Dto.POS09-1).getAmount());

        // （備 考） ①－③＋②－⑧
        // list.get(9).getAmount(); bikou_differ
        reportEntity.setBikouDiffer(   list.get(ConstantsKbn080201Dto.POS10-1).getAmount());

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, reportEntity, DataHistoryStatusConstants.INSERT);

        // 同一識別コード取得
        Long code = 1L;
        Optional<OfferingPartyUsage0802And0803Report2024Entity> optional = offeringPartyUsage0802And0803Report2024Repository
                .findFirstByOrderByPartyUsage0802And0803ReportCodeDesc();
        if (!optional.isEmpty()) {
            code = code + optional.get().getPartyUsage0802And0803ReportCode();
        }

        // repositoryで保存
        reportEntity.setPartyUsage0802And0803ReportCode(code);
        offeringPartyUsage0802And0803Report2024Repository.save(reportEntity);

        return 1;// 集計データの登録なので1行しか登録しない
    }

}
