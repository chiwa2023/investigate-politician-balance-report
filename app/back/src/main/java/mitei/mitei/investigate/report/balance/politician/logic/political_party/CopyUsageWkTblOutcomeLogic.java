package mitei.mitei.investigate.report.balance.politician.logic.political_party;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUsageOutcomeEntity;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022.CopyUsageWkTbOutncomeY2022Logic;

/**
 * 使途報告書ワークテーブル複写用データ抽出Logic
 */
@Component
public class CopyUsageWkTblOutcomeLogic {

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 50;

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private CopyUsageWkTbOutncomeY2022Logic copyUsageWkTbOutncomeY2022Logic;

    /**
     * 処理を行う
     *
     * @param houkokuNen   報告年
     * @param documentCode 文書同一識別コード
     * @param pageNumber   ページ番号
     * @param chunkSize    チャンクサイズ
     * @param privilegeDto 権限Dto
     * @return 検索結果
     */
    public List<WkTblUsageOutcomeEntity> practice(final int houkokuNen, final Long documentCode,
            final Integer pageNumber, final Integer chunkSize, final CheckPrivilegeDto privilegeDto) {

        switch (houkokuNen) {
            case YEAR_2022:
                return copyUsageWkTbOutncomeY2022Logic.practice(documentCode, pageNumber, chunkSize, privilegeDto);
            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }
    }

    /**
     * チャンクサイズを取得する
     *
     * @return チャンクサイズ
     */
    public int getChunkSize() {
        return CHUNK_SIZE;
    }

    /**
     * 処理件数を取得する
     *
     * @param houkokuNen   報告年
     * @param documentCode 文書同一識別コード
     * @return 処理件数
     */
    public Integer practiceSize(final int houkokuNen, final Long documentCode) {
        switch (houkokuNen) {
            case YEAR_2022:
                return copyUsageWkTbOutncomeY2022Logic.practiceSize(documentCode);
            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }
    }

}
