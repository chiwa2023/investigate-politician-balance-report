package mitei.mitei.investigate.report.balance.politician.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * DecideXmlFileCharsetUtil単体テスト
 */
class DecideXmlFileCharsetUtilTest {

    @Test
    void testPractice() throws Exception {

        DecideXmlFileCharsetUtil decideXmlFileCharsetUtil = new DecideXmlFileCharsetUtil();
        final String keyword = "収支報告書作成ソフト";
        Path path00 = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/judge_sjis.xml");
        assertThat(decideXmlFileCharsetUtil.practice(path00, keyword)).isEqualTo(Charset.forName("Shift_JIS"));

        Path path01 = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/judge_utf-8.xml");
        assertThat(decideXmlFileCharsetUtil.practice(path01, keyword)).isEqualTo(StandardCharsets.UTF_8);

        Path path02 = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/judge_utf-16.xml");
        assertThat(decideXmlFileCharsetUtil.practice(path02, keyword)).isEqualTo(StandardCharsets.UTF_16);

        // ファイルが存在しないときはIoException
        assertThrows(IOException.class, () -> decideXmlFileCharsetUtil.practice(Paths.get("存在しないパス"), keyword));

        // 該当文字セットが見当たらないときはnullとする。不用意に読み取りを試みるととすると落ちるのが正解
        // このプロジェクトでは文字コード不明ファイルを一覧化し再アップロードを促す。
        Path path03 = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file/judge_euc-jp.xml");
        assertThat(decideXmlFileCharsetUtil.practice(path03, keyword)).isEqualTo(null);

    }

}
