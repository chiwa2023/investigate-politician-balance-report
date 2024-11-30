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
     * 配下ディレクトリdirで指定された配備ディレクトリを返却する
     *
     * @param dir 配下ディレクトリ名
     * @return 絶対パス
     */
    public static String getBackSrcPath(final String dir) {
        
        return FileSystems.getDefault().getPath("src/" + dir).toAbsolutePath().toString();
    }

    
    
    /**
     * テストリソースを取得する
     *
     * @return テストリソースフォルダ
     */
    public static String getBackTestResourcePath() {
    
        return FileSystems.getDefault().getPath("src/test/resources/").toAbsolutePath().toString();
    }

    /**
     * テストリソースを取得する
     *
     * @return テストリソースフォルダ
     */
    public static String getBackTestFilePath() {
    
        return FileSystems.getDefault().getPath("src/test/java/").toAbsolutePath().toString();
    }

}
