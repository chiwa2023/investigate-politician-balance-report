package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;

/**
 * パスで指定されたXMLから政治資金収支報告書Dtoを読みだす
 */
@Component
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
public class ReadAllShitoBookByXmlFileLogic {

    /** 保存親フォルダ */
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
     * 読みだし処理をする
     *
     * @param fullPath ファイル所在パス(完全パス・ストレージの部分バスどちらでも)
     * @param charset  ファイル読みだしの文字コード
     * @return 政治資金収支報告書Dto
     * @throws IOException ファイル読み取り例外
     */
    public AllShitoBook practice(final String fullPath, final String charset) throws IOException {

        Path path = Paths.get(fullPath);
        // 渡されたパスディレクトリまでが記載されたパスであればそのまま、違う場合は、ストレージとみなして補正する
        if ( !Files.exists(path) | !Files.isReadable(path)) {
            path = Paths.get(storageFolder, fullPath);
        }

        // 公式XML読み取り
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(Include.ALWAYS);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String readText = Files.readString(path, Charset.forName(charset));

        return xmlMapper.readValue(readText, new TypeReference<>() {
        });

    }
}
