package mitei.mitei.investigate.report.balance.politician.controller;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Controller;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckSecurityDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckTransactionDto;
import mitei.mitei.investigate.report.balance.politician.service.CheckPrivilegeService;
import mitei.mitei.investigate.report.balance.politician.service.CheckSecurityService;
import mitei.mitei.investigate.report.balance.politician.service.CheckTransactionService;

/**
 * 共通チェック用共通処理Controller
 */
@Controller
public class AbstractTemplateCheckController {

    /** セキュリティチェック不可 */
    public static final int SECURITY_CHECK_FALSE = 1;

    /** 権限チェック不可 */
    public static final int PRIVIKEGE_CHECK_FALSE = 2;

    /** 排他制御チェック不可 */
    public static final int TRANSACION_CHECK_FALSE = 3;

    /** 共通チェック不可なし */
    public static final int CHECK_TRUE = 4;
    
    /** その他の例外が発生し場合のメッセージ */
    public static final String OTHER_EXCEPTION_MESSAGE = "共通チェック処理で発生しえない値が挿入されています";

    
    
    /** セキュリティチェックService */
    @Autowired
    private CheckSecurityService checkSecurityService;

    /** 権限チェックService */
    @Autowired
    private CheckPrivilegeService checkPrivilegeService;

    /** 排他制御チェックService */
    @Autowired
    private CheckTransactionService checkTransactionService;

    /**
     * セキュリティ、権限、排他制御の共通チェック処理を行う
     *
     * @param checkSecurityDto    セキュリティチェック情報
     * @param checkPrivilegeDto   権限チェック情報
     * @param checkTransactionDto 排他制御情報
     * @return チェック結果(不可になったプロセス)
     * @throws SecurityException       セキュリティ例外
     * @throws PessimisticLockingFailureException   悲観的排他制御例外
     * @throws AuthenticationException 権限例外
     */
    protected int allCheck( // CHECKSTYLE:OFF
            final CheckSecurityDto checkSecurityDto,
            final CheckPrivilegeDto checkPrivilegeDto,
            final CheckTransactionDto checkTransactionDto)
            throws SecurityException, PessimisticLockingFailureException, AuthenticationException { // NOPMD

        // セキュリティチェック
        if (!checkSecurityService.practice(checkSecurityDto)) {
            // セキュリティ事故
            return SECURITY_CHECK_FALSE;
        }

        // 権限チェック
        if (!checkPrivilegeService.practice(checkPrivilegeDto)) {
            // 権限不足
            return PRIVIKEGE_CHECK_FALSE;
        }

        // 排他制御チェック
        if (!checkTransactionService.practice(checkTransactionDto)) {
            return TRANSACION_CHECK_FALSE;
        }

        return CHECK_TRUE;
    }

    /**
     * 開発中の例外記録(StackTrace)
     * TODO データベースへのログ記録に変更する
     * @param exception
     */
    protected void showError(final Exception exception) {
        exception.printStackTrace(); //NOPMD
    }
}
