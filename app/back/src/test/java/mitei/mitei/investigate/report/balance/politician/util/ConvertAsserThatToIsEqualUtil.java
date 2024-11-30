package mitei.mitei.investigate.report.balance.politician.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * assertThatからassertEqualsに変換するUtility
 */
public class ConvertAsserThatToIsEqualUtil {

    /** assertThatの開始Key */
    private static final String KEY_OPEN = "assertThat(";

    /** assertThatの終了Key(equals) */
    private static final String KEY_CLOSE_EQUALS = "isEqualTo(";

    /** assertThatの終了Key(true) */
    private static final String KEY_CLOSE_TRUE = "isTrue(";

    /** assertThatの終了Key(false) */
    private static final String KEY_CLOSE_FALSE = "isFalse(";

    /** 新しいファイルの保存場所 TODO 完全に安定して動くようになったら元ファイルを上書きする */
    private static final String SAVE_FOLDER = "c:/temp";

    /**
     * 元記述の直下にassertEqualsテストメソッドを追加する
     *
     * @param pathDirectory 解析するディレクトリ
     * @throws IOException ファイル読み取り例外
     */
    public void practice(final Path pathDirectory) throws IOException {

        // 指定されたファイルが存在しないときは処理中断
        if (!Files.exists(pathDirectory)) {
            throw new IllegalArgumentException("指定されたファイルが存在しません");
        }

        List<Path> listFiles = Files.list(pathDirectory).toList();

        Path pathCopy;
        for (Path path : listFiles) {
            if (!Files.isDirectory(path)) {
                pathCopy = Paths.get(SAVE_FOLDER, path.getFileName().toString());
                Files.write(pathCopy, this.analysisFile(path), StandardCharsets.UTF_8);
            }
        }
    }

    private List<String> analysisFile(final Path pathFile) throws IOException {
        // JavaはUTF-8
        List<String> listBody = Files.readAllLines(pathFile, StandardCharsets.UTF_8);

        List<String> list = new ArrayList<>();
        boolean isAssert = false;
        StringBuilder stringBuilder = new StringBuilder();
        for (String line : listBody) {
            // TODO しばらくは元記述も残して動作が安定したら切り替える
            list.add(line);

            if (line.indexOf(KEY_OPEN) != -1) {
                isAssert = true;
                stringBuilder = new StringBuilder(); // NOPMD
            }
            if (isAssert) {
                stringBuilder.append(line.trim());
            }

            String key = this.useKey(line);

            if (!Objects.isNull(key)) {

                isAssert = false;

                String allTest = stringBuilder.toString();

                // actualを設定
                int posActualStart = allTest.indexOf(KEY_OPEN) + KEY_OPEN.length();
                int posActualEnd = allTest.indexOf(").", posActualStart);
                String actual = allTest.substring(posActualStart, posActualEnd);

                // expectを設定
                String expect = this.getExpect(allTest, key);

                StringBuilder builderNew = new StringBuilder("assertEquals("); // NOPMD
                builderNew.append(expect).append(',').append(actual).append(",\"\");");

                String newString = builderNew.toString();
                list.add(newString);
            }
        }

        return list;
    }

    // actual節が終了しているかどうか確認
    private String useKey(final String line) {

        if (line.indexOf(KEY_CLOSE_EQUALS) != -1) {
            return KEY_CLOSE_EQUALS;
        }

        if (line.indexOf(KEY_CLOSE_TRUE) != -1) {
            return KEY_CLOSE_TRUE;
        }

        if (line.indexOf(KEY_CLOSE_FALSE) != -1) {
            return KEY_CLOSE_FALSE;
        }

        return null;
    }

    // expectを取得
    private String getExpect(final String allTest, final String key) {
        String expect = "";
        switch (key) {
            case KEY_CLOSE_EQUALS:
                int posExpectStart = allTest.indexOf(KEY_CLOSE_EQUALS) + KEY_CLOSE_EQUALS.length();
                int posExpectEnd = allTest.indexOf(");", posExpectStart);
                expect = allTest.substring(posExpectStart, posExpectEnd);
                break;

            case KEY_CLOSE_TRUE:
                expect = "true";
                break;

            case KEY_CLOSE_FALSE:
                expect = "false";
                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + key);
        }
        return expect;
    }

}
