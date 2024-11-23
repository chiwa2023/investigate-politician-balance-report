package mitei.mitei.investigate.report.balance.politician.logic.storage.y2025;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.storage.SaveFileStorage2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.storage.SaveFileStorage2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 書証を保存する(2025)年
 */
@Component
public class SaveStorageFileY2025Logic {

    /** ファイル保存Repository(2025年) */
    @Autowired
    private SaveFileStorage2025Repository saveFileStorage2025Repository;

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

        SaveFileStorage2025Entity entity2025 = new SaveFileStorage2025Entity();
        SetTableDataHistoryUtil.practice(privilegeDto, entity2025, DataHistoryStatusConstants.INSERT);
        entity2025.setChildDir(childDir);
        entity2025.setFileName(fileName);
        entity2025.setRegistTimeText(unixTime);
        entity2025.setSaveFileStorageId(0L); // auto increment 明記
        entity2025.setSaveFileStorageCode(0L); // 基本的に変更は控えてほしい。どうしてもの場合は更新時に同一識別コードを持ってくる

        SaveFileStorage2025Entity entityNew2025 = saveFileStorage2025Repository.save(entity2025);
        Long entityId = entityNew2025.getSaveFileStorageId();
        Long entityCode = entityNew2025.getSaveFileStorageCode();

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
