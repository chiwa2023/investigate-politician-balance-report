package mitei.mitei.investigate.report.balance.politician.logic.storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageSimulateEntityDto;
import mitei.mitei.investigate.report.balance.politician.entity.storage.SaveFileStorage2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.storage.SaveFileStorage2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SaveFileOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * アップロードされたファイルをストレージに格納する
 */
@Component
public class SaveStorageFileLogic {

    /** ファイル保存Utilty */
    @Autowired
    private SaveFileOnlyUtil saveFileOnlyUtil;

    /** 処理年(2024) */
    private static final int YEAR_2024 = 2024;
    /** ファイル保存Repository(2024年) */
    @Autowired
    private SaveFileStorage2024Repository saveFileStorage2024Repository;

    /**
     * ファイル保存とDB保存記録処理を行う
     *
     * @param fileName      ファイル名
     * @param fileContent   ファイル内容
     * @param privilegeDto  権限確認Dto
     * @param datetimeShori 処理日時
     * @return 書証保存Dto
     * @throws IOException ファイルが保存できなかった場合の例外
     */
    public SaveStorageResultDto practiceText(final String fileName, final String fileContent, final String charset,
            final CheckPrivilegeDto privilegeDto, final LocalDateTime datetimeShori) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String unixTime = formatter.format(datetimeShori);

        String childDir = saveFileOnlyUtil.practice(unixTime, privilegeDto, fileName, fileContent, charset);
        Long entityId = 0L;
        Long entityCode = 0L;

        switch (datetimeShori.getYear()) {
            // 2024年
            case YEAR_2024:
                SaveFileStorage2024Entity entity2024 = new SaveFileStorage2024Entity();
                BeanUtils.copyProperties(this.getStorageDto(privilegeDto, fileName, childDir), entity2024);
                SaveFileStorage2024Entity entityNew2024 = saveFileStorage2024Repository.save(entity2024);
                entityId = entityNew2024.getSaveFileStorageId();
                entityCode = entityNew2024.getSaveFileStorageCode();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + datetimeShori.getYear());
        }

        SaveStorageResultDto resultDto = new SaveStorageResultDto();

        resultDto.setShoshouId(entityId);
        resultDto.setShoshouCode(entityCode);
        resultDto.setShoshouKbn(0); // 仮値 TODO 決定次第修正を行う
        resultDto.setFullPath(childDir + "/" + fileName);
        resultDto.setChildDir(childDir);
        resultDto.setFileName(fileName);
        resultDto.setRegistTimeText(unixTime);

        return resultDto;
    }

    /**
     * ファイル保存とDB保存記録処理を行う(バイナリデータ専用)
     *
     * @param fileName      ファイル名
     * @param fileContent   ファイル内容
     * @param privilegeDto  権限確認Dto
     * @param datetimeShori 処理日時
     * @return 書証保存Dto
     * @throws IOException ファイルが保存できなかった場合の例外
     */
    public SaveStorageResultDto practiceWithDecode(final String fileName, final String fileContent,
            final String charset, final CheckPrivilegeDto privilegeDto, final LocalDateTime datetimeShori)
            throws IOException {

        // TODO UrlDecode(バイナリを文字列にしたもの)

        return this.practiceText(fileName, fileContent, charset, privilegeDto, datetimeShori);
    }

    /**
     * save_storage共通で使用できるDtoに値をセットする
     *
     * @param privilegeDto 権限確認Dto
     * @param fileName     ファイル名
     * @param childDir     子ディレクト
     * @return 共通利用用途Dto
     */
    private SaveStorageSimulateEntityDto getStorageDto(final CheckPrivilegeDto privilegeDto, final String fileName,
            final String childDir) {
        SaveStorageSimulateEntityDto dto = new SaveStorageSimulateEntityDto();

        SetTableDataHistoryUtil.practice(privilegeDto, dto, DataHistoryStatusConstants.INSERT);
        dto.setDirChild(childDir);
        dto.setFileName(fileName);
        dto.setSaveFileStorageId(0L); // auto increment 明記
        dto.setSaveFileStorageCode(0L); // 基本的に変更は控えてほしい。どうしてもの場合は更新時に同一識別コードを持ってくる

        return dto;
    }
}
