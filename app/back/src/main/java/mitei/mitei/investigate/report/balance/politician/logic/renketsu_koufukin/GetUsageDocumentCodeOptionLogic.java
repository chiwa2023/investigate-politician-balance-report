package mitei.mitei.investigate.report.balance.politician.logic.renketsu_koufukin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.SearchDocumentCodeByOfferingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.GetUsageDocumentCodeOptionY2022Logic;

/**
 * 政治団体から使途報告書文書コード選択肢リストを取得するLogic
 */
@Component
public class GetUsageDocumentCodeOptionLogic {

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private GetUsageDocumentCodeOptionY2022Logic getUsageDocumentCodeOptionY2022Logic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 文書コードリスト取得検索条件Dto
     * @return 選択肢リスト
     */
    public List<SelectOptionDto> practice(final SearchDocumentCodeByOfferingCapsuleDto capsuleDto) {

        Integer houkokuNen = capsuleDto.getHoukokuNen();
        switch (houkokuNen) {
            case YEAR_2022:
                return getUsageDocumentCodeOptionY2022Logic.practice(capsuleDto.getPoliOrgCode());
            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }

    }
}
