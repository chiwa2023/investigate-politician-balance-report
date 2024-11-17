package mitei.mitei.investigate.report.balance.politician.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

/**
 * XMLファイルの文字コードを推定するUtility (1)公式ソフトウェアMcrosoft Excelマクロで排出されるのがSJIS
 * (2)XML公式文字コードUTF-8 (3)XML公式文字コードUTF-16
 */
@Component
public class DecideXmlFileCharsetUtil {

    /** 文字コードUTF-8 */
    private static final String CHARSET_UTF8 = "UTF-8";
    /** 文字コードUTF-16 */
    private static final String CHARSET_UTF16 = "UTF-16";
    /** 文字コードUTF-sjis */
    private static final String CHARSET_SJIS = "Shift_JIS";

    /**
     * 文字コード特定を行う
     *
     * @param path    ファイル絶対パス
     * @param keyWord 必ず読みとられるキーワード
     * @return 文字コード
     * @throws IOException ファイル(主に不存在など)例外
     */
    public Charset practice(final Path path,final String keyWord) throws IOException { // SUPPRESS CHECKSTYLE ReturnCountCheck

        // ファイル全文を読み取りを試みてMalformedInputExceptionが発生しなかったらその文字コードが仮に正解とする

        // まず公式ソフトウェアでWindows環境で吐き出すsjisを確かめる
        if (this.judge(path, CHARSET_SJIS, keyWord)) {
            return Charset.forName(CHARSET_SJIS);
        }

        // XML標準文字コードUTF-8
        if (this.judge(path, CHARSET_UTF8, keyWord)) {
            return Charset.forName(CHARSET_UTF8);
        }

        // XML標準文字コードUTF-6
        if (this.judge(path, CHARSET_UTF16, keyWord)) {
            return Charset.forName(CHARSET_UTF16);
        }

        return null;
    }

    // ファイルを開けてみてうまくいったらかつ必ず含まれる文字が含まれていたらその文字コードとする
    private boolean judge(final Path path, final String charsetName, final String keyWord) throws IOException { // SUPPRESS CHECKSTYLE ReturnCountCheck

        try {
            String data = Files.readString(path, Charset.forName(charsetName));

            return data.indexOf(keyWord) != -1;
        } catch (MalformedInputException exception) {
            return false;
        }

    }

}
