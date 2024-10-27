package mitei.mitei.investigate.report.balance.politician;

import org.junit.jupiter.api.Test;

/**
 * 更新系(テーブルTruncateするテストのTestSuites)
 * ※タグをIDEで有効化するにはtest実行プロパティで設定することになる(何もプラグインいらない)
 * 1.Single　TestからAll testへ
 * 2.categoriesのボタンをおしてTableTruncateを追加
 * ./mvnw test -Dgroups="NaturalTextSearch"(pom.xmlのmaven-surefire-pluginのconfiguration-groupsと一致させる)
 */
class DatabaseUpdateTestSuites {
    
    /**
     *  タグ付けテスト起動用
     */
    @Test
    void testPractice() { // NOPMD
        // タグ付けテスト起動用
    }

}
