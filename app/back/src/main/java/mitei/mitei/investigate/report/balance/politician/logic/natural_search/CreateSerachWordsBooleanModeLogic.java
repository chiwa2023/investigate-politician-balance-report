package mitei.mitei.investigate.report.balance.politician.logic.natural_search;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.stereotype.Component;

/**
 * ユーザさんの指定した検索語からMySQL自然検索Boolean Mode用の検索語を生成する 『単語1
 * 単語2』の場合、OR検索の場合が多いようだがAND検索を強制する OR検索は『>単語1 >単語2』としてもらう
 */
@Component
public class CreateSerachWordsBooleanModeLogic {

    /** 半角空白(何回も使用するので) */
    private static final String SPACE_HALF = " ";

    /** 空文字 */
    private static final String BLANK = "";

    /** 左カッコ */
    private static final String PARENTHES_LEFT = "(";

    /** 右カッコ */
    private static final String PARENTHES_RIGHT = ")";

    /**
     * 実検索語を生成する
     *
     * @param keyWords ユーザ指定検索語
     * @return 実検索語
     */
    public String practice(final String keyWords) {

        // 変換処理は下記公式ドキュメントを参考にする
        // https://dev.mysql.com/doc/refman/8.0/ja/fulltext-boolean.html

        // わざわざ例外出すほどではないですが、検索結果は出しません
        if (Objects.isNull(keyWords) | BLANK.equals(keyWords)) {
            return BLANK;
        }

        // 全角を半角スペースに変換してsplit対応
        String tempWords = keyWords.replaceAll("　", SPACE_HALF);

        // DBに保存した検索用カラムのNormalizeと同内容処理を施す
        tempWords = Normalizer.normalize(tempWords, Normalizer.Form.NFKC).toLowerCase(Locale.JAPANESE);

        // 全角を半角スペースに変換してsplit
        String[] cell = tempWords.split(SPACE_HALF);

        // かっこの構造化構造は維持します
        List<String> listRepair = this.repairParentheses(cell);

        // かっこ構造化を正しく処理したらに組み立て
        StringBuilder stringBuilder = new StringBuilder();
        for (String data : listRepair) {
            stringBuilder.append(this.formatWords(data)).append(SPACE_HALF);
        }

        String answer = stringBuilder.toString();

        // 最後の半角スペースを除去
        return answer.substring(0, answer.length() - 1);
    }

    private String formatWords(final String data) { // SUPPRESS CHECKSTYLE ReturnCount

        String firstChar = data.substring(0, 1);

        // ブール全文検索機能では、次の演算子がサポートされています。
        switch (firstChar) {

            // [+] 先頭または末尾のプラス記号は、この単語が返される各行に存在しなければならないことを示します。
            case "+":
                return data;

            // [-] 先頭または末尾のマイナス記号は、この単語が返される行のいずれにも存在してはならないことを示します。
            case "-":
                return data;

            // [@distance] この演算子は、InnoDB テーブルでのみ機能します。
            case "@":
                if (data.startsWith("@distance")) {
                    // InnoDBでしか機能しない演算子は抹殺します
                    return "+" + data.substring("@distance".length(), data.length());
                } else {
                    return "+" + data;
                }

            // [> <]これらの 2 つの演算子は、行に割り当てられた関連性の値への単語の貢献度を変更する際に使用されます。
            case ">":
                return data;
            case "<":
                return data;

            // [( )]丸括弧は、単語を部分式にグループ化します。
            case "(":
                return "+" + data;

            // [~]先頭のチルダは否定演算子として機能するため、行の関連性への単語の貢献度がマイナスになります。 これは、「ノイズ」単語にマークを付ける際に便利です。
            case "~":
                return data;

            // [*]アスタリスクは、切り捨て (またはワイルドカード) 演算子として機能します。
            // 語の中間でも前にも置けます。むしろ+をつける=Defaultのほうが、ユーザさんが考える検索結果に近いと想定します
            // case "*":
            // return data;

            // ["]二重引用符 (") 文字内で囲まれたフレーズは、入力されたそのままのフレーズを含む行にのみ一致します。
            // 検索対象にスペースが含まれていないため"abc def"は絶対にヒットしません。スペースのない"abc"は+abcと同じです

            // (演算子なし)
            default:
                // +演算子は末尾でも機能します
                if (data.endsWith("+")) {
                    return data;
                }
                // -演算子は末尾でも機能します
                if (data.endsWith("-")) {
                    return data;
                }

                return "+" + data;
        }
    }

    /**
     * かっこ内の空白を+付加対象としない補正を行う
     *
     * @param cell 半角文字列で分割した配列
     * @return 補正後のリスト
     */
    private List<String> repairParentheses(final String[] cell) { // NOPMD

        int countStart = 0;
        int countEnd = 0;
        boolean isModeParentheses = false;

        String data;
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < cell.length; index++) { // NOPMD
            data = cell[index].replaceAll("\"", BLANK); // "演算子は無効化する

            // どこかしらに(が含まれている場合。すでに+(などの演算子が前置されている可能性がある
            if (data.indexOf(PARENTHES_LEFT) != -1) {
                isModeParentheses = true;
                countStart = countStart + this.countKey(PARENTHES_LEFT, data);
                builder.append(data).append(SPACE_HALF);
            }

            // (>abc <def)*と末尾に他演算子がついている可能性も一応考慮する
            if (data.indexOf(PARENTHES_RIGHT) != -1) {
                countEnd = countEnd + this.countKey(PARENTHES_RIGHT, data);
                builder.append(data);
                if (countEnd == countStart) {
                    isModeParentheses = false;
                    countEnd = 0;
                    countStart = 0;
                }
            }

            if (!isModeParentheses) {
                if (BLANK.equals(builder.toString())) {
                    builder.append(data);
                }
                list.add(builder.toString());
                builder = new StringBuilder(); // NOPMD リスト保存したら初期化
            }

        }

        // ユーザ指定でかっこの数があわない場合は全かっこ外し
        if (countEnd != countStart) {
            list = new ArrayList<>();
            for (String ans2 : cell) {
                list.add(ans2.replaceAll("\\" + PARENTHES_LEFT, BLANK).replaceAll("\\" + PARENTHES_RIGHT, BLANK));
            }
        }

        return list;
    }

    /**
     * 文字の出現回数を数える
     *
     * @param key  回数を数える文字
     * @param word 出現回数を確認する文字列
     * @return 回数
     */
    private int countKey(final String key, final String word) {

        int count = 0;
        int pos = 0;
        pos = word.indexOf(key, pos);
        // 出現位置をカウント
        while (pos != -1) {
            count++;
            pos = word.indexOf(key, pos + 1);
        }

        return count;
    }

}
