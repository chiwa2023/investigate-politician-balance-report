package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * アップロードされたcsv内容格納Dto
 */
public class ReadXmlByFileCapsuleDto extends AbstractCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serial Id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** ファイル名 */
    private String fileName = INIT_String;

    /** csvファイル内容 */
    private String fileContent = INIT_String;

    /**
     * ファイル名を取得する
     *
     * @return ファイル名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * ファイル名を設定する
     *
     * @param fileName ファイル名
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * csvファイル内容ファイル名を取得する
     *
     * @return csvファイル内容
     */
    public String getFileContent() {
        return fileContent;
    }

    /**
     * csvファイル内容を設定する
     *
     * @param fileContent csvファイル内容
     */
    public void setFileContent(final String fileContent) {
        this.fileContent = fileContent;
    }

    /** 読み取り文字コード */
    private String charset = INIT_String;

    /**
     * 読み取り文字コードを取得する
     *
     * @return 読み取り文字コード
     */
    public String getCharset() {
        return charset;
    }

    /**
     * 読み取り文字コードを設定する
     *
     * @param charset 読み取り文字コード
     */
    public void setCharset(final String charset) {
        this.charset = charset;
    }

}
