package mitei.mitei.investigate.report.balance.politician.logic.storage.y2024;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.storage.SaveFileStorage2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.storage.SaveFileStorage2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 書証を保存する(2024)年
 */
@Component
public class SaveStorageFileY2024Logic {

    /** ファイル保存Repository(2024年) */
    @Autowired
    private SaveFileStorage2024Repository saveFileStorage2024Repository;

    /**
     * 保存処理を行う
     *
     * @param privilegeDto 権限確認Dto
     * @param unixTime     保存Unix時間
     * @param childDir     子ディレクトリ
     * @param fileName     ファイル名
     * @return 書証保存Dto
     * @throws IOException ファイル例外
     */
    public SaveStorageResultDto practice(final CheckPrivilegeDto privilegeDto, final String unixTime,
            final String childDir, final String fileName) throws IOException {

        SaveFileStorage2024Entity entity2024 = new SaveFileStorage2024Entity();
        SetTableDataHistoryUtil.practice(privilegeDto, entity2024, DataHistoryStatusConstants.INSERT);
        entity2024.setChildDir(childDir);
        entity2024.setFileName(fileName);
        entity2024.setRegistTimeText(unixTime);
        entity2024.setSaveFileStorageId(0L); // auto increment 明記
        entity2024.setSaveFileStorageCode(0L); // 基本的に変更は控えてほしい。どうしてもの場合は更新時に同一識別コードを持ってくる

        SaveFileStorage2024Entity entityNew2024 = saveFileStorage2024Repository.save(entity2024);
        Long entityId = entityNew2024.getSaveFileStorageId();
        Long entityCode = entityNew2024.getSaveFileStorageCode();

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

}
