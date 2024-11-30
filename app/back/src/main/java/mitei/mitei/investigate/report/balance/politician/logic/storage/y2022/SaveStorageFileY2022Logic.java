package mitei.mitei.investigate.report.balance.politician.logic.storage.y2022;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.storage.SaveFileStorage2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.storage.SaveFileStorage2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 書証を保存する(2022)年
 */
@Component
public class SaveStorageFileY2022Logic {

    /** ファイル保存Repository(2022年) */
    @Autowired
    private SaveFileStorage2022Repository saveFileStorage2022Repository;

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

        SaveFileStorage2022Entity entity2022 = new SaveFileStorage2022Entity();
        SetTableDataHistoryUtil.practice(privilegeDto, entity2022, DataHistoryStatusConstants.INSERT);
        entity2022.setChildDir(childDir);
        entity2022.setFileName(fileName);
        entity2022.setRegistTimeText(unixTime);
        entity2022.setSaveFileStorageId(0L); // auto increment 明記
        entity2022.setSaveFileStorageCode(0L); // 基本的に変更は控えてほしい。どうしてもの場合は更新時に同一識別コードを持ってくる

        SaveFileStorage2022Entity entityNew2022 = saveFileStorage2022Repository.save(entity2022);
        Long entityId = entityNew2022.getSaveFileStorageId();
        Long entityCode = entityNew2022.getSaveFileStorageCode();

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
