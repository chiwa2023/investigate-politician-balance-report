package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CheckKifuKobetsuJogenByRelationCodeY2022Logic;

/**
 * 寄付明細同一識別コード取得ファクトリメソッドLogic
 */
@Component
public class GetKifuKobetsuJogenByRelationCodeMeisaiLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private CheckKifuKobetsuJogenByRelationCodeY2022Logic checkKifuKobetsuJogenByRelationCodeY2022Logic;

    /**
     * 処理を行う
     *
     * @param conditionDto 明細取得条件Dto
     * @return 検索結果
     */
    public SearchKifuJougenMeisaiBalancesheetResultDto practice(final KifuJougenConditionCapsuleDto conditionDto) {

        int key = conditionDto.getHoukokuNen();
        Pageable pageable = Pageable.ofSize(conditionDto.getOffset()).withPage(conditionDto.getPageNum());

        switch (key) {

            // caseテンプレート始まり
            // 2022年
            case YEAR_2022:
                return checkKifuKobetsuJogenByRelationCodeY2022Logic.practice(conditionDto.getPoliOrgCode(),
                        conditionDto.getSeachKifuKbn(), conditionDto.getYoushikiEdaKbn(), pageable);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + key);
        }
    }

}
