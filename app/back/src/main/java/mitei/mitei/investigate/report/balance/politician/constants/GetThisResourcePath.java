package mitei.mitei.investigate.report.balance.politician.constants;

import java.nio.file.FileSystems;

/**
 * 各種設定は、application.propertiesあるいはデータベース、
 * 定数は関連するDtoないしLogicに記載する予定
 * Mock用としてリソースへのアクセスを用意する
 * TODO Mockロジック削除とともにこのクラスも削除する
 */
public final class GetThisResourcePath {

    //インスタンス生成除け
    private GetThisResourcePath() {
        
    }
    
    /**
     * テストリソースを取得する
     *
     * @return テストリソースフォルダ
     */
    public static String getBackMainResourcePath() {
    
        return FileSystems.getDefault().getPath("src/main/resources/").toAbsolutePath().toString();
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
