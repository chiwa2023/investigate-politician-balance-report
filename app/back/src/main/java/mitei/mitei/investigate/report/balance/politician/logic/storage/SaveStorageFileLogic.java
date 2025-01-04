package mitei.mitei.investigate.report.balance.politician.logic.storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.util.SaveFileOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.logic.storage.y2022.SaveStorageFileY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.storage.y2024.SaveStorageFileY2024Logic;
import mitei.mitei.investigate.report.balance.politician.logic.storage.y2025.SaveStorageFileY2025Logic;
import mitei.mitei.investigate.report.balance.politician.logic.storage.y2023.SaveStorageFileY2023Logic;
// importを追加

/**
 * アップロードされたファイルをストレージに格納する
 */
@Component
public class SaveStorageFileLogic {

    /** ファイル保存Utilty */
    @Autowired
    private SaveFileOnlyUtil saveFileOnlyUtil;

    /** Base64ヘッダ */
    private static final String BASE64_HEADER = ";base64,";

    // フィールドテンプレート始まり
    /** 処理年(2022) */
    private static final int YEAR_2022 = 2022;
    /** ファイル保存Logic(2022年) */
    @Autowired // 2022
    private SaveStorageFileY2022Logic saveStorageFileY2022Logic;

    /** 処理年(2024) */
    private static final int YEAR_2024 = 2024;
    /** ファイル保存Logic(2024年) */
    @Autowired
    private SaveStorageFileY2024Logic saveStorageFileY2024Logic;

    /** 処理年(2025) */
    private static final int YEAR_2025 = 2025;
    /** ファイル保存Logic(2025年) */
    @Autowired
    private SaveStorageFileY2025Logic saveStorageFileY2025Logic;

    /** 処理年(2023) */
    private static final int YEAR_2023 = 2023;
    /** ファイル保存Logic(2023年) */
    private SaveStorageFileY2023Logic saveStorageFileY2023Logic;

    // フィールドの追加位置

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

        return this.saveTable(privilegeDto, unixTime, childDir, fileName);
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
            final CheckPrivilegeDto privilegeDto, final LocalDateTime datetimeShori) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String unixTime = formatter.format(datetimeShori);

        String content = fileContent;
        // Webから取得したデータの場合はMymeTypeのヘッダがついているので発見したら除去
        int pos = fileContent.indexOf(BASE64_HEADER);
        if (-1 != pos) {
            content = fileContent.substring(pos + BASE64_HEADER.length(), fileContent.length());
        }

        String childDir = saveFileOnlyUtil.practice(unixTime, privilegeDto, fileName,
                Base64.getDecoder().decode(content));

        return this.saveTable(privilegeDto, unixTime, childDir, fileName);
    }

    // テーブルに保存して書証保存Dtoを返却する
    private SaveStorageResultDto saveTable(// SUPPRESS CHECKSTYLE ReturnCountCheck
            final CheckPrivilegeDto privilegeDto, final String unixTime, final String childDir, final String fileName)
            throws IOException {

        // 年4桁を取り出し
        int year = Integer.parseInt(unixTime.substring(0, 4)); // SUPPRESS CHECKSTYLE MagicNumber

        switch (year) {

            // 2022年
            case YEAR_2022:
                return saveStorageFileY2022Logic.practice(privilegeDto, unixTime, childDir, fileName);

            // caseテンプレート始まり
            // 2024年
            case YEAR_2024:
                return saveStorageFileY2024Logic.practice(privilegeDto, unixTime, childDir, fileName);

            // 2025年
            case YEAR_2025:
                return saveStorageFileY2025Logic.practice(privilegeDto, unixTime, childDir, fileName);

            // 2023年
            case YEAR_2023:
                return saveStorageFileY2023Logic.practice(privilegeDto, unixTime, childDir, fileName);

            // caseの追加位置

            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }

}
