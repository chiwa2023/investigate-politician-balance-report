package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.SouryouKiseiRelationCodeDto;
import mitei.mitei.investigate.report.balance.politician.entity.RelationPersonEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.SouryouKiseiByCodeEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen.GetDonationLimitAmountLogic;
import mitei.mitei.investigate.report.balance.politician.repository.RelationPersonRepository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Repository;

/**
 * 政治団体に寄付をした個人の総額を取得する(関連者個人同一識別コード基準)
 */
@Component
public class CheckKifuSouryouKiseiByPersonCodeY2024Logic {

    /** 政治資金収支報告書収入Repository(2024) */
    @Autowired
    private OfferingBalancesheetIncome2024Repository offeringBalancesheetIncome2024Repository;

    /** 関連者個人マスタRepository */
    @Autowired
    private RelationPersonRepository relationPersonRepository;

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

        List<SouryouKiseiByCodeEntity> listSum = offeringBalancesheetIncome2024Repository.calcSumByRelationPerson(
                offeringBalancesheetIncome2024Repository.findRelationPersonByPoliOrg(poliOrgCode), listDantaiKbn);

        Long limitAmount = getDonationLimitAmountLogic.practice(YoushikiEdaKbn.KOJIN, listDantaiKbn);

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

        Optional<RelationPersonEntity> optional = relationPersonRepository.findById(entity.getRelationId());
        if (!optional.isEmpty()) {
            RelationPersonEntity personEntity = optional.get();
            dto.setPartnerName(personEntity.getRelationPersonName());
            dto.setPartnerJusho(personEntity.getAddressName());

            // 上限額と判定を設定する
            dto.setLimitAmount(limitAmount);
            dto.setIsLimitOver(limitAmount < entity.getSum());
        }

        return dto;
    }

}
