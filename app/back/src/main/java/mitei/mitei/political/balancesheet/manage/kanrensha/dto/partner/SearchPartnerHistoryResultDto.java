package mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 履歴検索結果Dto
 */
public class SearchPartnerHistoryResultDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 履歴共通リスト */
    private List<PartnerCommonInfoDto> listHistoryInfo = new ArrayList<>();

    /**
     * 履歴共通リストを取得する
     *
     * @return 履歴共通リスト
     */
    public List<PartnerCommonInfoDto> getListHistoryInfo() {
        return listHistoryInfo;
    }

    /**
     * 履歴共通リストを設定留守
     *
     * @param listHistoryInfo 履歴共通リスト
     */
    public void setListHistoryInfo(final List<PartnerCommonInfoDto> listHistoryInfo) {
        this.listHistoryInfo = listHistoryInfo;
    }

}
