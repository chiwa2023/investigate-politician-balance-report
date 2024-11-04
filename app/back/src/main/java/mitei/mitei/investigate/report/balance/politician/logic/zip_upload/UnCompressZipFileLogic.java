package mitei.mitei.investigate.report.balance.politician.logic.zip_upload;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;

/**
 * zipファイルを解凍展開する
 */
@Component
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
public class UnCompressZipFileLogic { // NOPMD

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
     * zipファイル展開処理を行う
     *
     * @param saveStorageResultDto 書証保存Dto
     * @return 処理結果
     * @throws IOException ファイル関係例外
     */
    public boolean practice(final SaveStorageResultDto saveStorageResultDto) throws IOException {
        
        Path zipPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), saveStorageResultDto.getFileName());

        try (ZipFile zipFile = new ZipFile(zipPath.toFile(), StandardCharsets.UTF_8)) {
            Iterator<? extends ZipEntry> iterator = zipFile.entries().asIterator();
            Path pathUnCompress;
            while (iterator.hasNext()) {
                ZipEntry zipEntry = iterator.next();

                pathUnCompress = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), uncompress,
                        zipEntry.getName());

                if (zipEntry.isDirectory()) {
                    // ディレクトリ書き込み
                    Files.createDirectories(pathUnCompress);
                } else {

                    // 親ディレクトリが存在しない場合は作成
                    if (!Files.exists(pathUnCompress.getParent())) {
                        Files.createDirectories(pathUnCompress.getParent());
                    }
                    
                    Files.write(pathUnCompress, zipFile.getInputStream(zipEntry).readAllBytes(),
                            StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                }
            }
        }

        return true;
    }
}
