package mitei.mitei.investigate.report.balance.politician.util;

import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.AllTabeDataHistoryInterface;

/**
 * データテーブルから権限Dtoを生成する
 */
@Component
public class ConvertCheckPrivilegeDtoUtil {

    /**
     * 生成する
     *
     * @param imple データ履歴Interface実装
     * @return 権限確認Dto
     */
    public CheckPrivilegeDto practice(final AllTabeDataHistoryInterface imple) {
        CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();

        privilegeDto.setLoginUserId(imple.getInsertUserId());
        privilegeDto.setLoginUserCode(imple.getInsertUserCode());
        privilegeDto.setLoginUserName(imple.getInsertUserName());

        return privilegeDto;

    }
}
