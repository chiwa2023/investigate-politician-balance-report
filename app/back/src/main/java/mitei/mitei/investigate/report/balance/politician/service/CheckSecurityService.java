package mitei.mitei.investigate.report.balance.politician.service;

import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckSecurityDto;

/**
 * セキュリティチェックを行うService
 * TODO 仕様が決定次第修正する
 */
@Service
public class CheckSecurityService {

    /**
     * セキュリティチェックを行う
     *
     * @param checkSecurityDto セキュリティチェック情報
     * @return チェック結果
     * @throws SecurityException 汎用セキュリティ例外
     */
    public boolean practice(final CheckSecurityDto checkSecurityDto)throws SecurityException { // NOPMD

        //TODO 仕様が決定次第修正する
        //Mockで外部から強制例外発生と判定結果falseを送信るようにする
        if(checkSecurityDto.getIsRaiseExcception()) {
            throw new SecurityException("Mock外部からの強制例外");
        }
        
        return checkSecurityDto.getIsResult();
    }

}
