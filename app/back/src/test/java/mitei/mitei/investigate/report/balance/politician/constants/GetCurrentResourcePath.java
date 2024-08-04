package mitei.mitei.investigate.report.balance.politician.constants;

import java.nio.file.FileSystems;

/**
 * このサービスを配備している各種設定ディレクトリを取得する
 * Windowsマシンでgithubからc:\abcdeに展開しているならc:\abcdが配備ディレクトリ
 */
public final class GetCurrentResourcePath {

    private GetCurrentResourcePath() {

    }

    /**
     * テストリソースを取得する
     *
     * @return テストリソースフォルダ
     */
    public static String getBackTestResourcePath() {
    
        return FileSystems.getDefault().getPath("src/test/resources/").toAbsolutePath().toString();
    }

}
