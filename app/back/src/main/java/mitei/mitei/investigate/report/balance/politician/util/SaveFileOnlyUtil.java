package mitei.mitei.investigate.report.balance.politician.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;

/**
 * 指定された内容でファイルの書き込みを行う
 */
@Component
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
public class SaveFileOnlyUtil {

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    /**
     * 書き込み処理を行う(ファイル内容文字列)
     *
     * @param unixTime 処理日時(UnixTime形式)
     * @param privilegeDto  権限確認Dto
     * @param fileName      ファイル名
     * @param fileContent   ファイル内容
     * @return 生成した子パス
     * @throws IOException ディレクトリ・ファイル書き込みができなかった例外
     */
    public String practice(final String unixTime, final CheckPrivilegeDto privilegeDto, // NOPMD
            final String fileName, final String fileContent, final String charset) throws IOException {

        // 冗長だが毎回ディレクトリの有無を確認する
        Path parentPath = Paths.get(storageFolder);
        if (!Files.exists(parentPath) || !Files.isDirectory(parentPath)) {
            Files.createDirectories(parentPath);
        }


        // 子ディレクトリも作成
        Path childPath = Paths.get(String.valueOf(privilegeDto.getLoginUserCode()), unixTime,
                UUID.randomUUID().toString());
        String childDir = childPath.toString().replaceAll(File.pathSeparator, "/");
        Files.createDirectories(Paths.get(storageFolder, childDir));

        // 実際のファイル書き込み
        Path allPath = Paths.get(parentPath.toString(), childPath.toString(), fileName);
        Files.writeString(allPath, fileContent,Charset.forName(charset));

        return childDir;
    }
}
