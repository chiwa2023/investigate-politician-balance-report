package mitei.mitei.investigate.report.balance.politician.contoroller.poli_party;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.controller.AbstractTemplateCheckController;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.RegistPoliticalPartyUsageReportCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.RegistPoliticalPartyUsageReportResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.ReadAllShitoBookByXmlFileLogic;
import mitei.mitei.investigate.report.balance.politician.service.offering.poli_party.InsertPartyUsageReportService;

/**
 * 政党交付金使途報告書の公式XMLを登録する
 */
@Controller
public class InsertPoliticalPartyUsageReportController extends AbstractTemplateCheckController {

    /** セキュリティチェック不可定数 */
    private static final int SECURITY_CHECK_FALSE = AbstractTemplateCheckController.SECURITY_CHECK_FALSE;
    /** 権限チェック不可定数 */
    private static final int PRIVIKEGE_CHECK_FALSE = AbstractTemplateCheckController.PRIVIKEGE_CHECK_FALSE;
    /** 排他制御チェック不可定数 */
    private static final int TRANSACION_CHECK_FALSE = AbstractTemplateCheckController.TRANSACION_CHECK_FALSE;
    /** ビジネス処理続行定数 */
    private static final int CHECK_TRUE = AbstractTemplateCheckController.CHECK_TRUE;

    /** 政治資金収支報告書の公式XMLを登録Service */
    @Autowired
    private InsertPartyUsageReportService insertPartyUsageReportService;

    /** 政党交付金使途報告書読み取りLogic */
    @Autowired
    private ReadAllShitoBookByXmlFileLogic readAllShitoBookByXmlFileLogic;

    /**
     * 登録処理を行う
     *
     * @param capsuleDto 政党交付金使途報告書処理条件Dto
     * @return 政党交付金使途報告書処理結果Dto
     */
    @Transactional // SUPPRESS CHECKSTYLE ReturnCountCheck
    @PostMapping("/insert-political-party-usage-report")
    public ResponseEntity<RegistPoliticalPartyUsageReportResultDto> practice(
            @RequestBody final RegistPoliticalPartyUsageReportCapsuleDto capsuleDto) {

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

            // 保存したXMLからの全データ
            AllShitoBook allBookDto = readAllShitoBookByXmlFileLogic.practice(
                    capsuleDto.getSaveStorageResultDto().getFullPath(),
                    capsuleDto.getSaveStorageResultDto().getCharset());

            // 登録作業
            RegistPoliticalPartyUsageReportResultDto resultDto = insertPartyUsageReportService.practice(
                    capsuleDto.getDocumentPropertyDto(), capsuleDto.getCheckPrivilegeDto(), allBookDto, false);

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
