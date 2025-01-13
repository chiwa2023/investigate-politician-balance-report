package mitei.mitei.investigate.report.balance.politician.service.kifu_jogen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KobetsuKifuJougenResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.CheckKifuKobetsuJogenByRelationCodeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.CheckKifuKobetsuJougenLogic;

/**
 * 個人B枠、政治団体5000万円枠の初回のみ個別規制明細を取得する(初回のみ)
 */
@Service
public class CheckDonationKobetsuKiseiUpperLimitService {

    
    /** 指定したコード条件で上限規制用取引明細を取得する */
    @Autowired
    private CheckKifuKobetsuJogenByRelationCodeLogic checkKifuKobetsuJogenByRelationCodeLogic;
    
    /** 指定した原文書条件で上限規制用取引明細を取得する */
    @Autowired
    private CheckKifuKobetsuJougenLogic checkKifuKobetsuJougenLogic;
    
    
    /**
     * 処理を行う
     *
     * @param houkokuNen 報告年
     * @param poliOrgCode 政治団体同一識別コード
     * @param isSearchByName 検索を原文書名称で行うかどうか
     * @return 政治団体と個人B枠の明細Dto
     */
    public KobetsuKifuJougenResultDto practice(final Integer houkokuNen, final Integer poliOrgCode,
            final boolean isSearchByName) {
        
        if (isSearchByName) {
            // 名前による検索
            return checkKifuKobetsuJougenLogic.practice(houkokuNen, poliOrgCode);
            
        }else {
            // 同一識別コードによる検索
            return checkKifuKobetsuJogenByRelationCodeLogic.practice(houkokuNen, poliOrgCode);
        }
    }    
}
