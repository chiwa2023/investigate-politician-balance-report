package mitei.mitei.investigate.report.balance.politician.dto.storage;

import java.io.Serializable;

/**
 * ファイル内容伝送Dto
 */
public class OneFileBlobDto implements Serializable {

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 文字列初期値 */
    private final String INIT_STRING = "";

    /** ファイル名 */
    private String fileName = INIT_STRING;

    /** ファイル内容バイナリBase64変換文字列 */
    private String fileContentBase64 = INIT_STRING;

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
     * ファイル内容バイナリBase64変換文字列を取得する
     *
     * @return ファイル内容バイナリBase64変換文字列
     */
    public String getFileContentBase64() {
        return fileContentBase64;
    }

    /**
     * ファイル内容バイナリBase64変換文字列を設定する
     *
     * @param fileContentBase64 ファイル内容バイナリBase64変換文字列
     */
    public void setFileContentBase64(final String fileContentBase64) {
        this.fileContentBase64 = fileContentBase64;
    }

}
