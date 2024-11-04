package mitei.mitei.investigate.report.balance.politician.service.zip_upload;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.storage.SaveStorageFileLogic;
import mitei.mitei.investigate.report.balance.politician.logic.zip_upload.CompareDirectoryTreeZipFileLogic;
import mitei.mitei.investigate.report.balance.politician.logic.zip_upload.UnCompressZipFileLogic;

/**
 * zipファイルのアップロードと解凍を行う
 */
@Service
public class ZipUploadService {

    /** アップロードファイル保存処理 */
    @Autowired
    private SaveStorageFileLogic saveStorageFileLogic;

    /** テスト対象 */
    @Autowired
    private UnCompressZipFileLogic unCompressZipFileLogic;

    /** テスト対象 */
    @Autowired
    private CompareDirectoryTreeZipFileLogic compareDirectoryTreeZipFileLogic;

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

        // zipの展開
        if (unCompressZipFileLogic.practice(saveStorageResultDto)) {
            // 展開の確認
            compareDirectoryTreeZipFileLogic.practice(saveStorageResultDto);
        }

        return saveStorageResultDto;
    }

}
