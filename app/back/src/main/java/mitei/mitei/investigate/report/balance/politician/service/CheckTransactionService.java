package mitei.mitei.investigate.report.balance.politician.service;

import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckTransactionDto;

/**
 * 排他制御がされているかをチェックするService TODO 仕様が決定次第修正する
 */
@Service
public class CheckTransactionService {

    /**
     * 排他制御の対象かどうかをチェックする
     *
     * @param checkTransactionDto 排他制御確認情報
     * @return チェック結果
     * @throws PessimisticLockingFailureException   悲観的排他制御例外
     */
    public boolean practice(final CheckTransactionDto checkTransactionDto) throws PessimisticLockingFailureException { // NOPMD

        // 照会専用機能の場合はチェックしない
        if (checkTransactionDto.getIsSelectOnly()) {
            return checkTransactionDto.getIsSelectOnly();
        }

        // TODO 仕様が決定次第修正する
        // Mockで外部から強制例外発生と判定結果falseを送信するようにする
        if (checkTransactionDto.getIsRaiseExcception()) {
            throw new PessimisticLockingFailureException("Mock強制例外");
        }

        return checkTransactionDto.getIsResult();
    }

}
