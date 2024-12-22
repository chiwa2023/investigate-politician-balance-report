package mitei.mitei.investigate.report.balance.politician.util;

import java.time.LocalDateTime;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.AllTabeDataHistoryInterface;

/**
 * テーブルの更新履歴に必要なログインユーザ、更新時間情報をセットする
 */
public final class SetTableDataHistoryUtil {

    // インスタンス生成よけ
    private SetTableDataHistoryUtil() {

    }

    /**
     * データ履歴カラムにデータを入力する
     *
     * @param checkPrivilegeDto 権限チェックDto
     * @param interfaceImple    データ履歴カラムInterface
     * @param status            データ履歴ステータス
     */
    public static void practice(final CheckPrivilegeDto checkPrivilegeDto,
            final AllTabeDataHistoryInterface interfaceImple, final DataHistoryStatusConstants status) {

        final LocalDateTime timestampNow = LocalDateTime.now();
        final LocalDateTime timestampInit = LocalDateTime.of(1948, 7, 29, 0, 0, 0); // SUPPRESS CHECKSTYLE MagicNumber

        // Insert(初回)データセット
        if (DataHistoryStatusConstants.INSERT.equals(status)) {

            interfaceImple.setSaishinKbn(DataHistoryStatusConstants.INSERT.value());
            interfaceImple.setInsertUserId(checkPrivilegeDto.getLoginUserId());
            interfaceImple.setInsertUserCode(checkPrivilegeDto.getLoginUserCode());
            interfaceImple.setInsertUserName(checkPrivilegeDto.getLoginUserName());
            interfaceImple.setInsertTimestamp(timestampNow);
            interfaceImple.setUpdateUserId(0L);
            interfaceImple.setUpdateUserCode(0);
            interfaceImple.setUpdateUserName("");
            interfaceImple.setUpdateTimestamp(timestampInit);
        }

        // Update(更新)データセット
        if (DataHistoryStatusConstants.UPDATE.equals(status)) {
            // NOTE どうしても更新してもInsertを維持したい場合は別メソッドとする
            interfaceImple.setSaishinKbn(DataHistoryStatusConstants.UPDATE.value());
            interfaceImple.setUpdateUserId(checkPrivilegeDto.getLoginUserId());
            interfaceImple.setUpdateUserCode(checkPrivilegeDto.getLoginUserCode());
            interfaceImple.setUpdateUserName(checkPrivilegeDto.getLoginUserName());
            interfaceImple.setUpdateTimestamp(timestampNow);
        }

    }
}
