package mitei.mitei.investigate.report.balance.politician.controller.offering.document.regist;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;
import mitei.mitei.investigate.report.balance.politician.logic.task_info.CallTaskInfoLogic;
import mitei.mitei.investigate.report.balance.politician.service.task_plan.RegistTaskPlanService;
import mitei.mitei.investigate.report.balance.politician.service.zip_upload.ZipUploadDocumentService;

/**
 * ビジネスロジックをまとめるWorksBandController(Transaction用)
 */
@Component
public class ReadXmlDocumentByCompressedZipControllerWorksBand {

    /** zipファイル保存展開Service */
    @Autowired
    private ZipUploadDocumentService zipUploadDocumentService;

    /** タスク計画と通知予定登録Service */
    @Autowired
    private RegistTaskPlanService registTaskPlanService;

    /** タスク情報呼び出しLogic */
    @Autowired
    private CallTaskInfoLogic callTaskInfoLogic;

    /**
     * 処理を行う
     *
     * @param readXmlByFileCapsuleDto XML読み取りDto
     * @param datetimeShori 処理日時
     * @return 書証保存Dto
     * @throws IOException ファイル読取り例外
     */
    @Transactional
    public SaveStorageResultDto wakeBusiness(final ReadXmlByFileCapsuleDto readXmlByFileCapsuleDto ,final LocalDateTime datetimeShori)throws IOException {
        
        // ファイルを保存して展開する
        final SaveStorageResultDto saveStorageResultDto = zipUploadDocumentService.practcie(datetimeShori, readXmlByFileCapsuleDto);
        
        // 該当のタスク情報を呼び出す
        List<String> listName = new ArrayList<>();
        listName.add("報告書一括登録準備");
        listName.add("報告書一括登録処理結果");
        List<TaskInfoEntity> listTaskInfo = callTaskInfoLogic.practice(listName);
        
        // タスク登録(メール送信通知予定含む)する
        // 計画登録、計画通知の記録が必要な場合はDtoを起こすが、
        // ユーザさんが欲しいのは登録したファイルに対する処理結果なので、この結果は返却不要
        registTaskPlanService.practice(readXmlByFileCapsuleDto.getCheckPrivilegeDto(), datetimeShori, listTaskInfo);

        return saveStorageResultDto;
    }

}
