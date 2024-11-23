package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_org;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import mitei.mitei.investigate.report.balance.politician.controller.AbstractTemplateCheckController;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplateFrameworkResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.UpdateBalancesheetWkTblCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.service.offering.poli_org.UpdateBalancesheetWkTbService;

/**
 * 政治資金収支報告書一括処理ワークテーブル変更
 */
@RestController
@RequestMapping("/update-balancesheet-prepare")
public class UpdateBalancesheetWkTbController extends AbstractTemplateCheckController {

    /** セキュリティチェック不可定数 */
    private static final int SECURITY_CHECK_FALSE = AbstractTemplateCheckController.SECURITY_CHECK_FALSE;
    /** 権限チェック不可定数 */
    private static final int PRIVIKEGE_CHECK_FALSE = AbstractTemplateCheckController.PRIVIKEGE_CHECK_FALSE;
    /** 排他制御チェック不可定数 */
    private static final int TRANSACION_CHECK_FALSE = AbstractTemplateCheckController.TRANSACION_CHECK_FALSE;
    /** ビジネス処理続行定数 */
    private static final int CHECK_TRUE = AbstractTemplateCheckController.CHECK_TRUE;

    /** 政治資金収支報告書更新Service */
    @Autowired
    private UpdateBalancesheetWkTbService updateBalancesheetWkTbService;

    /**
     * 更新処理を行う
     *
     * @param capsuleDto XMl読み取り内容
     * @return XML解析結果Dto
     * @throws SecurityException                  セキュリティ例外
     * @throws AuthenticationException            権限例外
     * @throws PessimisticLockingFailureException トランザクション例外
     */
    @Transactional // SUPPRESS CHECKSTYLE ReturnCountCheck
    @PostMapping("/worktable")
    public ResponseEntity<TemplateFrameworkResultDto> practice(
            final @RequestBody UpdateBalancesheetWkTblCapsuleDto capsuleDto)
            throws SecurityException, AuthenticationException, PessimisticLockingFailureException { // NOPMD

        // NOTE:共通処理を行ったのちビジネス処理を行うフレームワークのため、ビジネス処理以外は丸コピすること
        try {
            switch (super.allCheck(capsuleDto.getCheckSecurityDto(), capsuleDto.getCheckPrivilegeDto(),
                    capsuleDto.getCheckTransactionDto())) {
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

            Long newId = updateBalancesheetWkTbService.practice(capsuleDto.getWkTblPoliOrgBalancesheetReportEntity(),
                    capsuleDto.getCheckPrivilegeDto());
            TemplateFrameworkResultDto resultDto = new TemplateFrameworkResultDto();
            final long noUpdate = 0L;
            if (noUpdate == newId) {
                resultDto.setIsOk(false);
                resultDto.setMessage("更新できませんでした");
            } else {
                resultDto.setIsOk(true);
                resultDto.setMessage("更新しました");
            }
            return ResponseEntity.ok(resultDto);

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
            // TODO 例外をデータベースに記録するようになったら削除する
            super.showError(exception);
            // その他のビジネスロジック処理例外はInternalServerError
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
