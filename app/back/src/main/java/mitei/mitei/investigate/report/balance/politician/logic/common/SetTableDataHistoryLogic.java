package mitei.mitei.investigate.report.balance.politician.logic.common;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.AllTabeDataHistoryInterface;

/**
 * テーブルの更新履歴に必要なログインユーザ、更新時間情報をセットする
 */
@Component
public class SetTableDataHistoryLogic {

    /**
     * データ履歴カラムにデータを入力する
     *
     * @param checkPrivilegeDto 権限チェックDto
     * @param interfaceImple    データ履歴カラムInterface
     * @param status            データ履歴ステータス
     */
    public void practice(final CheckPrivilegeDto checkPrivilegeDto,
            final AllTabeDataHistoryInterface interfaceImple, final DataHistoryStatusConstants status) {

        LocalDateTime timestampNow = LocalDateTime.now();

        // Insert(初回)データセット
        if (DataHistoryStatusConstants.INSERT.equals(status)) {
            interfaceImple.setInsertUserId(checkPrivilegeDto.getLoginUserId());
            interfaceImple.setInsertUserCode(checkPrivilegeDto.getLoginUserCode());
            interfaceImple.setInsertUserName(checkPrivilegeDto.getLoginUserName());
            interfaceImple.setInsertTimestamp(timestampNow);
        }

        // Update(更新)データセット
        if (DataHistoryStatusConstants.UPDATE.equals(status)) {
            interfaceImple.setUpdateUserId(checkPrivilegeDto.getLoginUserId());
            interfaceImple.setUpdateUserCode(checkPrivilegeDto.getLoginUserCode());
            interfaceImple.setUpdateUserName(checkPrivilegeDto.getLoginUserName());
            interfaceImple.setUpdateTimestamp(timestampNow);
        }

    }

}
