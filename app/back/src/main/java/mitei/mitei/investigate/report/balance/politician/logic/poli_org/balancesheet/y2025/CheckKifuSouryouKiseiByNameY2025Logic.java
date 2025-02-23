package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.souryou_kisei.SouryouKiseiRelationCodeDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.SouryouKiseiGenBunshoEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen.GetDonationLimitAmountLogic;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Repository;

/**
 * 政治団体に寄付をした個人の総額を取得する
 */
@Component
public class CheckKifuSouryouKiseiByNameY2025Logic {

    /** 政治資金収支報告書収入Repository(2025) */
    @Autowired
    private OfferingBalancesheetIncome2025Repository offeringBalancesheetIncome2025Repository;

    /** 寄付上限額取得Logic */
    @Autowired
    private GetDonationLimitAmountLogic getDonationLimitAmountLogic;

    /**
     * 処理を行う
     *
     * @param poliOrgCode 政治団体同一識別コード
     * @return 総計リスト
     */
    public List<SouryouKiseiRelationCodeDto> practice(final Integer poliOrgCode, final Integer youshikiEdaKbn,
            final List<String> listDanaiKbn) {
        
        // 政治団体同一識別コードから取引相手名称リストを取得し、
        // 取引相手名称トごとの合計金額を算出する
        List<SouryouKiseiGenBunshoEntity> listEntity = offeringBalancesheetIncome2025Repository.calcSumByPartnerName(
                offeringBalancesheetIncome2025Repository.findPartnerNameByPoliOrg(poliOrgCode, youshikiEdaKbn),
                youshikiEdaKbn, listDanaiKbn);

        Long limitAmount = getDonationLimitAmountLogic.practice(youshikiEdaKbn, listDanaiKbn);

        List<SouryouKiseiRelationCodeDto> list = new ArrayList<>();
        for (SouryouKiseiGenBunshoEntity entity : listEntity) {
            list.add(this.createDto(entity, limitAmount));
        }

        return list;
    }

    // EntiyからDtoに変換する制限判定処理
    private SouryouKiseiRelationCodeDto createDto(final SouryouKiseiGenBunshoEntity entity, final Long limitAmount) {
        SouryouKiseiRelationCodeDto dto = new SouryouKiseiRelationCodeDto();
        BeanUtils.copyProperties(entity, dto);
        
        dto.setLimitAmount(limitAmount);

        // 企業・団体A枠以外の場合は-1以外の判定可能な数値が返ってくるので判定する
        if (limitAmount != -1) {
            // 上限額とフラグを設定する
            dto.setIsLimitOver(limitAmount < entity.getSum());
        }
        // TODO 企業団体の資本金・人員リストが整備されたら実装を行う

        return dto;
    }

}
