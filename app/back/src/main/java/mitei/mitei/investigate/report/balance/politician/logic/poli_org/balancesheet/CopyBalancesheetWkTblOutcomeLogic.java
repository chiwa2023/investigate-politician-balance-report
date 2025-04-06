package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblBalancesheetOutcomeEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.CopyBalancesheetWkTblOutcomeY2022Logic;

/**
 * 収支報告書支出ワークテーブル複写用データ抽出Logic
 */
@Component
public class CopyBalancesheetWkTblOutcomeLogic {

    /** チャンクサイズ */
    private static final int CHUNK_SIZE = 50;

    // フィールドテンプレート始まり
    /** 登録対応年(2022) */
    private static final int YEAR_2022 = 2022;
    /** 登録対応年(2022)Logic */
    @Autowired // 2022
    private CopyBalancesheetWkTblOutcomeY2022Logic copyBalancesheetWkTblOutcomeY2022Logic;

    /**
     * 処理を行う
     *
     * @param houkokuNen   報告年
     * @param documentCode 文書同一識別コード
     * @param pageNumber   ページ番号
     * @param chunkSize    チャンクサイズ
     * @param privilegeDto 権限確認Dto
     * @return 検索結果
     */
    public List<WkTblBalancesheetOutcomeEntity> practice(final int houkokuNen, final Long documentCode,
            final Integer pageNumber, final Integer chunkSize, final CheckPrivilegeDto privilegeDto) {

        switch (houkokuNen) {
            case YEAR_2022:
                return copyBalancesheetWkTblOutcomeY2022Logic.practice(documentCode, pageNumber, chunkSize,
                        privilegeDto);
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
     * @return 件数
     */
    public Integer practiceSize(final int houkokuNen, final Long documentCode) {
        switch (houkokuNen) {
            case YEAR_2022:
                return copyBalancesheetWkTblOutcomeY2022Logic.practiceSize(documentCode);
            default:
                throw new IllegalArgumentException("Unexpected value: " + houkokuNen);
        }
    }

}
