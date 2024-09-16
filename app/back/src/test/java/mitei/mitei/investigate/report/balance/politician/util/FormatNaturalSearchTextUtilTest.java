package mitei.mitei.investigate.report.balance.politician.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * FormatNaturalSearchTextUtil単体テスト
 */
class FormatNaturalSearchTextUtilTest {

    @Test
    void testPractice() {
        
        FormatNaturalSearchTextUtil formatNaturalSearchTextUtil = new FormatNaturalSearchTextUtil();
        
        // nullでも空文字を返して落とさない
        assertThat(formatNaturalSearchTextUtil.practice(null)).isEqualTo("");

        //　半角スペース
        assertThat(formatNaturalSearchTextUtil.practice("あい うえお")).isEqualTo("あいうえお");

        //　全角スペース
        assertThat(formatNaturalSearchTextUtil.practice("か　きくけ　こ")).isEqualTo("かきくけこ");

        //　半角大文字英語
        assertThat(formatNaturalSearchTextUtil.practice("abcABC")).isEqualTo("abcabc");

        //　全角大文字英語数字
        assertThat(formatNaturalSearchTextUtil.practice("ＡＢＣａｂｃ１２３")).isEqualTo("abcabc123");
        
        //　カタカナ全角
        assertThat(formatNaturalSearchTextUtil.practice("パーティ")).isEqualTo("パーティ");

        //　カタカナ半角
        assertThat(formatNaturalSearchTextUtil.practice("ﾊﾟｰﾃｨ")).isEqualTo("パーティ");

    }

}
