package mitei.mitei.investigate.report.balance.politician.controller.offering.natural_search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.tomcat.websocket.AuthenticationException;

import mitei.mitei.investigate.report.balance.politician.controller.AbstractTemplateCheckController;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.natural_search.IncomeAndOutcomeNaturalSearchResultDto;
import mitei.mitei.investigate.report.balance.politician.service.offering.natural_search.SearchIncomeAndOutcomeBySearchWordsService;

/**
 * 提出分のデータの支出・収入データを検索する
 */
@Controller
@RequestMapping("/offering-data")
public class SearchIncomeAndOutcomeBySearchWordsController extends AbstractTemplateCheckController {

    /** セキュリティチェック不可定数 */
    private static final int SECURITY_CHECK_FALSE = AbstractTemplateCheckController.SECURITY_CHECK_FALSE;
    /** 権限チェック不可定数 */
    private static final int PRIVIKEGE_CHECK_FALSE = AbstractTemplateCheckController.PRIVIKEGE_CHECK_FALSE;
    /** 排他制御チェック不可定数 */
    private static final int TRANSACION_CHECK_FALSE = AbstractTemplateCheckController.TRANSACION_CHECK_FALSE;
    /** ビジネス処理続行定数 */
    private static final int CHECK_TRUE = AbstractTemplateCheckController.CHECK_TRUE;

    /** 提出分検索Service */
    @Autowired
    private SearchIncomeAndOutcomeBySearchWordsService searchIncomeAndOutcomeBySearchWordsService;
    
    /**
     * 検索処理を行う
     *
     * @param searchConditionCapsuleDto 検索条件Dto
     * @return 検索結果Dto
     * @throws SecurityException                  セキュリティ例外
     * @throws AuthenticationException            権限例外
     * @throws PessimisticLockingFailureException トランザクション例外
     */
    @Transactional // SUPPRESS CHECKSTYLE ReturnCount
    @PostMapping("/search-income-outocome")
    public ResponseEntity<IncomeAndOutcomeNaturalSearchResultDto> searchWords(
            final @RequestBody IncomeAndOutcomeNaturalSearchConditionCapsuleDto searchConditionCapsuleDto)
            throws SecurityException, AuthenticationException, PessimisticLockingFailureException { // NOPMD
        
        // NOTE:共通処理を行ったのちビジネス処理を行うフレームワークのため、ビジネス処理以外は丸コピすること
        try {
            switch (super.allCheck(searchConditionCapsuleDto.getCheckSecurityDto(),
                    searchConditionCapsuleDto.getCheckPrivilegeDto(),
                    searchConditionCapsuleDto.getCheckTransactionDto())) {
                // セキュリティチェック不可
                case SECURITY_CHECK_FALSE:
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
                // 権限チェック不可
                case PRIVIKEGE_CHECK_FALSE:
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                // 排他制御チェック不可
                case TRANSACION_CHECK_FALSE:
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();

                // ビジネス処理続行
                case CHECK_TRUE:
                    break;

                // 想定外の値(実装ミス?)
                default:
                    throw new IllegalArgumentException(OTHER_EXCEPTION_MESSAGE);
            }

            /*
             * ここに固有のビジネス処理を記載する
             */

            return ResponseEntity.ok(searchIncomeAndOutcomeBySearchWordsService.practice(searchConditionCapsuleDto));

            /* ここまで */

        } catch (AuthenticationException authenticationException) { // NOPMD
            // 権限不足
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (SecurityException securityException) {
            // セキュリティ事故
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
        } catch (PessimisticLockingFailureException pessimisticLockingFailureException) {
            // 排他の対象
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception exception) { // NOPMD
            // その他のビジネスロジック処理例外はInternalServerError
            super.showError(exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
