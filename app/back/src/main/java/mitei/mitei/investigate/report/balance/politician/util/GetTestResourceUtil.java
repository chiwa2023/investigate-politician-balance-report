package mitei.mitei.investigate.report.balance.politician.util;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * テストリソースの絶対パスを取得するUtility
 */
public final class GetTestResourceUtil {

    private GetTestResourceUtil() {

    }

    /**
     * テストリソース内の絶対パスを取得する
     *
     * @param pathName 取得したいテストリーソース内のパス
     * @return 取得したいパスの絶対パス
     * @throws URISyntaxException 存在しない場合などURI変換できなかった場合
     */
    public static Path practice(final String pathName) throws URISyntaxException {

        URL url = Thread.currentThread().getContextClassLoader().getResource(""); // NOPMD LowDemeter
        Path pathApp = Paths.get(url.toURI()).getParent().getParent();

        return Paths.get(pathApp.toString(), "src/test/resources/", pathName);
    }
}
