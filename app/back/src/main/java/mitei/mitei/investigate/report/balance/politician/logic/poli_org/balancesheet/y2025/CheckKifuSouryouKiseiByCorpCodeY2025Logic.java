package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.SouryouKiseiRelationCodeDto;
import mitei.mitei.investigate.report.balance.politician.entity.RelationCorpEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.SouryouKiseiByCodeEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen.GetDonationLimitAmountLogic;
import mitei.mitei.investigate.report.balance.politician.repository.RelationCorpRepository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Repository;

/**
 * 総量規制用総額取得企業団体同一識別コード基準
 */
@Component
public class CheckKifuSouryouKiseiByCorpCodeY2025Logic {

    /** 政治資金収支報告書収入Repository(2025) */
    @Autowired
    private OfferingBalancesheetIncome2025Repository offeringBalancesheetIncome2025Repository;

    /** 関連者個人マスタRepository */
    @Autowired
    private RelationCorpRepository relationCorpRepository;

    /** 寄付上限額取得Logic */
    @Autowired
    private GetDonationLimitAmountLogic getDonationLimitAmountLogic;

    /**
     * 処理を行う
     *
     * @param poliOrgCode 政治団体同一識別コード
     * @return 総計リスト
     */
    public List<SouryouKiseiRelationCodeDto> practice(final Integer poliOrgCode, final List<String> listDantaiKbn) {

        // 政治団体同一識別コードから取引相手関連者個人同一識別コードリストを取得し、
        // 関連者個人同一識別コードリストごとの合計金額を算出する

        List<SouryouKiseiByCodeEntity> listSum = offeringBalancesheetIncome2025Repository.calcSumByRelationCorp(
                offeringBalancesheetIncome2025Repository.findRelationCorpByPoliOrg(poliOrgCode), listDantaiKbn);
        Long limitAmount = getDonationLimitAmountLogic.practice(YoushikiEdaKbn.KIGYOU_DANTAI, listDantaiKbn);
        List<SouryouKiseiRelationCodeDto> list = new ArrayList<>();
        for (SouryouKiseiByCodeEntity entity : listSum) {
            list.add(this.addPersonInfo(entity, limitAmount));
        }

        return list;
    }

    // 氏名・住所などを補足する
    private SouryouKiseiRelationCodeDto addPersonInfo(final SouryouKiseiByCodeEntity entity, final Long limitAmount) {
        SouryouKiseiRelationCodeDto dto = new SouryouKiseiRelationCodeDto();
        BeanUtils.copyProperties(entity, dto);

        Optional<RelationCorpEntity> optional = relationCorpRepository.findById(entity.getRelationId());
        if (!optional.isEmpty()) {
            RelationCorpEntity corpEntity = optional.get();
            dto.setPartnerName(corpEntity.getRelationCorpName());
            dto.setPartnerJusho(corpEntity.getAddressName());
            dto.setLimitAmount(limitAmount);
            if (limitAmount != -1L) {
                // 上限額とフラグを設定する(といっても資本金・人員に関係ないB枠の場合は基本0円)
                dto.setIsLimitOver(limitAmount < entity.getSum());
            }
            // TODO 関連者企業・団体から資本金・人員条件が取得できるようになった段階で修正する
        }

        return dto;
    }

}
