package mitei.mitei.investigate.report.balance.politician.service.zip_upload;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.constants.DocumentRecognizeKeyConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.storage.SaveStorageFileLogic;
import mitei.mitei.investigate.report.balance.politician.logic.task_plan.balancesheet.detail.InsertTaskPlanBalancesheetDetailLogic;
import mitei.mitei.investigate.report.balance.politician.logic.zip_upload.CompareDirectoryTreeZipFileLogic;
import mitei.mitei.investigate.report.balance.politician.logic.zip_upload.UnCompressZipFileLogic;

/**
 * zipファイルのアップロードと解凍を行う
 */
@Service
public class ZipUploadDocumentService {

    /** アップロードファイル保存処理 */
    @Autowired
    private SaveStorageFileLogic saveStorageFileLogic;

    /** zip解凍Logic */
    @Autowired
    private UnCompressZipFileLogic unCompressZipFileLogic;

    /** 解凍後の確認及び解析用の諸所保存Dto生成Logic */
    @Autowired
    private CompareDirectoryTreeZipFileLogic compareDirectoryTreeZipFileLogic;

    /** 政治資金収支報告書解析予定登録Logic */
    @Autowired
    private InsertTaskPlanBalancesheetDetailLogic balancesheetDetailLogic;

    /** 政治資金収支報告識別Key */
    private static final String SOFT_BALANCESHEET = DocumentRecognizeKeyConstants.SOFT_BALANCESHEET;

    /**
     * ファイル保存と展開、その確認処理をする
     *
     * @param datetimeShori 処理日時
     * @param capsuleDto    処理条件Dto
     * @return 書証保存Dto
     * @throws IOException ファイル例外
     */
    public SaveStorageResultDto practcie(final LocalDateTime datetimeShori, final ReadXmlByFileCapsuleDto capsuleDto)
            throws IOException {

        // 保存
        SaveStorageResultDto saveStorageResultDto = saveStorageFileLogic.practiceWithDecode(capsuleDto.getFileName(),
                capsuleDto.getFileContent(), capsuleDto.getCheckPrivilegeDto(), datetimeShori);

        final String keyWord = capsuleDto.getDocumentKey();

        // zipの展開
        List<SaveStorageResultDto> listFile = new ArrayList<>();
        if (unCompressZipFileLogic.practice(saveStorageResultDto)) {
            // 展開の確認
            listFile.addAll(compareDirectoryTreeZipFileLogic.practice(saveStorageResultDto, keyWord));
        }

        // 保存作業
        int compressCount = 0;
        if (!listFile.isEmpty()) {
            switch (keyWord) {
                // 政治資金収支報告書
                case SOFT_BALANCESHEET:
                    compressCount = balancesheetDetailLogic.practice(listFile, capsuleDto.getCheckPrivilegeDto());
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected value: " + keyWord);
            }

        }

        // 登録できた圧縮されていたファイル数を保存する
        saveStorageResultDto.setCompressCount(compressCount);
        saveStorageResultDto.setMessage("zipファイル1件を保存しました。(格納ファイル数" + compressCount + "件)");

        return saveStorageResultDto;
    }

}
