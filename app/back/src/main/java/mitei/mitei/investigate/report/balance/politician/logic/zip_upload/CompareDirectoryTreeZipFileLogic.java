package mitei.mitei.investigate.report.balance.politician.logic.zip_upload;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.util.DecideXmlFileCharsetUtil;

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

    /** XML文字コードセット */
    @Autowired
    private DecideXmlFileCharsetUtil decideXmlFileCharsetUtil;

    /**
     * 比較処理を行う
     *
     * @param saveStorageResultDto 書証保存Dto
     * @return 処理結果
     * @throws IOException ファイル例外
     */
    public List<SaveStorageResultDto> practice(final SaveStorageResultDto saveStorageResultDto, final String keyword)
            throws IOException {

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

        List<SaveStorageResultDto> listStorage = new ArrayList<>();
        // 二つのリストを比較して相違があれば空リスト
        for (Path p : copiedList) {
            if (!compressList.contains(p)) {
                return new ArrayList<>();
            }
            listStorage.add(this.createStorageDto(p, saveStorageResultDto, keyword));
        }

        return listStorage;
    }

    // 元書証保存Dtoから展開後のファイルの個別書証保存Dtoを生成する
    private SaveStorageResultDto createStorageDto(final Path pathFile, final SaveStorageResultDto srcDto,
            final String keyword) throws IOException {

        SaveStorageResultDto storageDto = new SaveStorageResultDto();

        BeanUtils.copyProperties(srcDto, storageDto);
        
        // 文字コード
        Charset charset = decideXmlFileCharsetUtil.practice(pathFile, keyword);
        if (Objects.isNull(charset)) {
            storageDto.setCharset(null);
        } else {
            storageDto.setCharset(charset.toString());
        }

        storageDto.setChildDir(this.getChildDir(pathFile));
        storageDto.setFileName(pathFile.getFileName().toString());
        storageDto.setFullPath(storageDto.getChildDir() + "/" + storageDto.getFileName());

        return storageDto;
    }

    // 子ディレクトリ生成
    private String getChildDir(final Path filePath) {

        StringBuffer buffer = new StringBuffer("/");

        Path pathStorage = Paths.get(storageFolder);
        int start = pathStorage.getNameCount();
        int end = filePath.getNameCount() - 1;
        for (int index = start; index < end; index++) {
            buffer.append(filePath.getName(index)).append("/");// NOPMD
        }

        return buffer.toString();
    }

}
