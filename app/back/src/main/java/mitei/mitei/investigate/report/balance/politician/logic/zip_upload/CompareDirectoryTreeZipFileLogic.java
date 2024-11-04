package mitei.mitei.investigate.report.balance.politician.logic.zip_upload;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;

/**
 * 解凍前のファイル構造と解凍後のファイル構造を比較して解凍漏れがないかどうか確認する
 */
@Component
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
public class CompareDirectoryTreeZipFileLogic { // NOPMD

    /** 展開フォルダ */
    private String uncompress;

    /**
     * 展開フォルダを取得する
     *
     * @return 展開フォルダ
     */
    public String getUncompress() {
        return uncompress;
    }

    /**
     * 展開フォルダを設定する
     *
     * @param uncompress 展開フォルダ
     */
    public void setUncompress(final String uncompress) {
        this.uncompress = uncompress;
    }

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
     * 比較処理を行う
     *
     * @param saveStorageResultDto 書証保存Dto
     * @return 処理結果
     * @throws IOException ファイル例外
     */
    public boolean practice(final SaveStorageResultDto saveStorageResultDto) throws IOException {

        Path zipPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), saveStorageResultDto.getFileName());

        // 実ファイルリスト
        Path copyPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), uncompress);

        Predicate<? super Path> isRegularFile = p -> {
            return Files.isRegularFile(p);
        };
        List<Path> copiedList = new ArrayList<>();
        Files.walk(copyPath).filter(isRegularFile).forEach(p1 -> copiedList.add(p1));
        
        // 解凍前に想定した解凍後のファイルパス
        List<Path> compressList = new ArrayList<>();
        Path compressPath;
        try (ZipFile zipFile = new ZipFile(zipPath.toFile(), StandardCharsets.UTF_8)) {
            Iterator<? extends ZipEntry> ite = zipFile.entries().asIterator();
            while (ite.hasNext()) {
                ZipEntry zipEntry = ite.next();
                if (!zipEntry.isDirectory()) {
                    compressPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), uncompress,
                            zipEntry.getName());
                    compressList.add(compressPath);
                }
            }
        }


        // 二つのリストを比較して相違があればfalse
        for (Path p : copiedList) {
            if(!compressList.contains(p)) {
                return false;
            }
        }
        
        return true;
    }
}
