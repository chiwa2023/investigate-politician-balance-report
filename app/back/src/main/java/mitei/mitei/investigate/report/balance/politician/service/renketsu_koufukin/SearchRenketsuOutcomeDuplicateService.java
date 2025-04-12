package mitei.mitei.investigate.report.balance.politician.service.renketsu_koufukin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.constants.RenketsuKoufukinConstants;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplatePagingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.RenketsuKoufukinWkTblDto;
import mitei.mitei.investigate.report.balance.politician.logic.renketsu_koufukin.SearchRenketsuKoufukinPagingLogic;

/**
 * 交付金連結支出紐づけ重複データ検索Service
 */
@Service
public class SearchRenketsuOutcomeDuplicateService {

    /** 交付金連結結果ページング取得Logic */
    @Autowired
    private SearchRenketsuKoufukinPagingLogic searchRenketsuKoufukinPagingLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto ページング検索条件Dto
     * @return 検索結果
     */
    public RenketsuKoufukinWkTblDto practice(final TemplatePagingCapsuleDto capsuleDto) {

        return searchRenketsuKoufukinPagingLogic.practice(capsuleDto, RenketsuKoufukinConstants.OUTCOME_DUPLICATE);
    }

}
