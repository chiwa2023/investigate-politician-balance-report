package mitei.mitei.investigate.report.balance.politician.controller.poli_org.fukisai;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import mitei.mitei.investigate.report.balance.politician.controller.AbstractTemplateCheckController;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.SearchFukisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.SearchWkTblFukisaiPagingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.fukisai.SearchWkTblFukisaiLogic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.CallTaskPlanByCodeUserLogic;

/**
 * 作成した不記載検出ワークテーブルデータを取得する
 */
@Controller
@RequestMapping("/check-fukisai")
public class SerachWkTbkFukisaiController extends AbstractTemplateCheckController {

    /** セキュリティチェック不可定数 */
    private static final int SECURITY_CHECK_FALSE = AbstractTemplateCheckController.SECURITY_CHECK_FALSE;
    /** 権限チェック不可定数 */
    private static final int PRIVIKEGE_CHECK_FALSE = AbstractTemplateCheckController.PRIVIKEGE_CHECK_FALSE;
    /** 排他制御チェック不可定数 */
    private static final int TRANSACION_CHECK_FALSE = AbstractTemplateCheckController.TRANSACION_CHECK_FALSE;
    /** ビジネス処理続行定数 */
    private static final int CHECK_TRUE = AbstractTemplateCheckController.CHECK_TRUE;

    /** タスク計画から該当の情報を取得する */
    @Autowired
    private CallTaskPlanByCodeUserLogic callTaskPlanByCodeUserLogic;

    /** タスク計画から該当の情報を取得する */
    @Autowired
    private SearchWkTblFukisaiLogic searchWkTblFukisaiLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索ページングDto
     * @return 不記載検出ワークテーブル検索結果
     */
    @PostMapping("/search")
    public ResponseEntity<SearchFukisaiBalancesheetResultDto> practice(
            @RequestBody final SearchWkTblFukisaiPagingCapsuleDto capsuleDto){

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

            final String taskName = "不記載チェッカー";

            if (callTaskPlanByCodeUserLogic.practice(capsuleDto.getTaskPlanCode(),
                    capsuleDto.getCheckPrivilegeDto().getLoginUserCode(), taskName, capsuleDto.getYear())) {

                // isFinished=trueすなわちデータ作成完了時にはデータを取得する
                return ResponseEntity.ok(searchWkTblFukisaiLogic.practice(capsuleDto.getPoliOrgCode(),
                        capsuleDto.getCountAll(), capsuleDto.getPageNumber()));

            } else {
                SearchFukisaiBalancesheetResultDto resultDto = new SearchFukisaiBalancesheetResultDto();
                resultDto.setIsOk(false);
                resultDto.setMessage("作成作業が完了していません。今しばらくお待ちください。");

                return ResponseEntity.ok(resultDto);
            }

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
