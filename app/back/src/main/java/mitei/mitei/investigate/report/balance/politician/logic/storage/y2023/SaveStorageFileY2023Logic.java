package mitei.mitei.investigate.report.balance.politician.logic.storage.y2023;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.storage.SaveFileStorage2023Entity;
import mitei.mitei.investigate.report.balance.politician.repository.storage.SaveFileStorage2023Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 書証を保存する(2023)年
 */
@Component
public class SaveStorageFileY2023Logic {

    /** ファイル保存Repository(2023年) */
    @Autowired
    private SaveFileStorage2023Repository saveFileStorage2023Repository;

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

        SaveFileStorage2023Entity entity2023 = new SaveFileStorage2023Entity();
        SetTableDataHistoryUtil.practice(privilegeDto, entity2023, DataHistoryStatusConstants.INSERT);
        entity2023.setChildDir(childDir);
        entity2023.setFileName(fileName);
        entity2023.setRegistTimeText(unixTime);
        entity2023.setSaveFileStorageId(0L); // auto increment 明記
        entity2023.setSaveFileStorageCode(0L); // 基本的に変更は控えてほしい。どうしてもの場合は更新時に同一識別コードを持ってくる

        SaveFileStorage2023Entity entityNew2023 = saveFileStorage2023Repository.save(entity2023);
        Long entityId = entityNew2023.getSaveFileStorageId();
        Long entityCode = entityNew2023.getSaveFileStorageCode();

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
