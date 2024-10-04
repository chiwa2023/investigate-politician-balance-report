package mitei.mitei.investigate.report.balance.politician.service;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;

/**
 * 権限チェックを行うService
 * TODO 仕様が決定次第修正する
 */
@Service
public class CheckPrivilegeService {

    /**
     * 権限チェックを行う
     *
     * @param checkPrivilegeDto 権限チェック情報Dto
     * @return チェック結果
     * @throws AuthenticationException 権限例外
     */
    public boolean practice(final CheckPrivilegeDto checkPrivilegeDto)throws AuthenticationException {

        //TODO 仕様が決定次第修正する
        //Mockで外部から強制例外発生と判定結果falseを送信るようにする
        if(checkPrivilegeDto.getIsRaiseExcception()) {
            throw new AuthenticationException("Mock外部からの強制例外スロー");
        }
        
        return checkPrivilegeDto.getIsResult();
    }
}
