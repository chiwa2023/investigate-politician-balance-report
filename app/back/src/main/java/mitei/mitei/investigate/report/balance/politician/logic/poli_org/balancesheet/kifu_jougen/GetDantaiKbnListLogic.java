package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.PoliticalOrganizationKbnConstants;

/**
 * 団体区分リストを取得する
 */
@Component
public class GetDantaiKbnListLogic {

    /** 検索区分:政党 */
    public static final int SEARCH_PARTY = 1;

    /** 検索区分:その他団体 */
    public static final int SEARCH_OTHER = 2;

    /** 検索区分:団体区分指定なし */
    public static final int SEARCH_NO_SET = 3;

    
    
    /**
     * 指定された検索区分に対応するリストを返却する
     *
     * @param searchKbn 検索区分
     * @return 姓j機団体団体区分リスト
     */
    public List<String> practice(final int searchKbn) {

        switch (searchKbn) {

            // 政党リスト
            case SEARCH_PARTY:
                return this.practiceParty();

            // その他団体リスト
            case SEARCH_OTHER:
                return this.practiceOtherOrg();

            // 未指定リスト
            case SEARCH_NO_SET:
                return this.practiceNoSet();

            default:
                throw new IllegalArgumentException("Unexpected value: " + searchKbn);
        }

    }

    /**
     * 政党の団体区分リストを取得する
     *
     * @return 政党の団体区分リスト
     */
    public List<String> practiceParty() {

        List<String> list = new ArrayList<>();
        list.add(PoliticalOrganizationKbnConstants.PARTY);
        list.add(PoliticalOrganizationKbnConstants.PARTY_BRANCH);
        list.add(PoliticalOrganizationKbnConstants.POLI_FUND_ORG);

        return list;

    }

    /**
     * その他団体の団体区分リストを取得する
     *
     * @return その他団体の団体区分リスト
     */
    public List<String> practiceOtherOrg() {

        List<String> list = new ArrayList<>();
        list.add(PoliticalOrganizationKbnConstants.DANTAI_18JOU_2KOU);
        list.add(PoliticalOrganizationKbnConstants.ORG_OTHER);
        list.add(PoliticalOrganizationKbnConstants.ORG_OTHER_BRANCH);

        return list;
    }

    /**
     * 団体区分未設定リストを取得する
     *
     * @return 団体区分未設定リスト
     */
    public List<String> practiceNoSet() {

        List<String> list = new ArrayList<>();
        list.add("0");

        return list;

    }

}
