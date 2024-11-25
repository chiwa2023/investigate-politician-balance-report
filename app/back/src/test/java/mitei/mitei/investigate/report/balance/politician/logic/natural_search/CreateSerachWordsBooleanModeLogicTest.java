package mitei.mitei.investigate.report.balance.politician.logic.natural_search;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * CreateSerachWordsBooleanModeLogic単体テスト
 */
class CreateSerachWordsBooleanModeLogicTest {

    @Test
    @Tag("NaturalTextSearch")
    void testPractice() {

        CreateSerachWordsBooleanModeLogic createSerachWordsBooleanModeLogic = new CreateSerachWordsBooleanModeLogic();

        // 検索条件を指定しない場合は空文字を検索条件とします(検索結果が返りません)
        assertThat(createSerachWordsBooleanModeLogic.practice(null)).isEqualTo("");
        assertThat(createSerachWordsBooleanModeLogic.practice("")).isEqualTo("");

        // 一単語だけの場合はその単語の制限を行う
        assertThat(createSerachWordsBooleanModeLogic.practice("パーティ")).isEqualTo("+パーティ");

        // 全角空白と半角スペースが混在している、あるある状態。単語語での区切りを作成
        assertThat(createSerachWordsBooleanModeLogic.practice("漢字1　漢字2 漢字3")).isEqualTo("+漢字1 +漢字2 +漢字3");

        // ()による構造化内の空白は+を付加すべき単語区切りとみなしません
        assertThat(createSerachWordsBooleanModeLogic.practice("漢字1 (<漢字2 漢字3)")).isEqualTo("+漢字1 +(<漢字2 漢字3)");

        // ()が入れ子構造でも機能します
        assertThat(createSerachWordsBooleanModeLogic.practice("漢字1 (<漢字2 (漢字3 <カタカナ4))"))
                .isEqualTo("+漢字1 +(<漢字2 (漢字3 <カタカナ4))");

        // ()の数があっていない場合は仮でかっこを抹消する
        assertThat(createSerachWordsBooleanModeLogic.practice("漢字1 (<漢字2 漢字3 <カタカナ4))"))
                .isEqualTo("+漢字1 <漢字2 +漢字3 <カタカナ4");

        // "演算子は除去する。検索対象カラムに空白を入れていないため 1)'"abcd"' は '+abcd'である、2)'"ab cd"'は絶対ヒットしない、ため
        assertThat(createSerachWordsBooleanModeLogic.practice("\"パーティ 東京\"")).isEqualTo("+パーティ +東京");

        // すでに+演算子が付加されている場合には変更しない
        assertThat(createSerachWordsBooleanModeLogic.practice("+パーティ")).isEqualTo("+パーティ");

        // すでに-演算子が付加されている場合には変更しない
        assertThat(createSerachWordsBooleanModeLogic.practice("-パーティ")).isEqualTo("-パーティ");

        // 単語の中間に演算子が存在しても影響は受けません(記号は検索に？との噂も)
        assertThat(createSerachWordsBooleanModeLogic.practice("漢字1　漢字+2 漢字3")).isEqualTo("+漢字1 +漢字+2 +漢字3");

        // @distance演算子はInnoDBしか機能しないので削除する
        assertThat(createSerachWordsBooleanModeLogic.practice("漢字1　@distance漢字2 漢字3")).isEqualTo("+漢字1 +漢字2 +漢字3");

        // @でスタートしても通常通り扱う(実装の都合によるケース分け)
        assertThat(createSerachWordsBooleanModeLogic.practice("漢字1　@mail漢字2 漢字3")).isEqualTo("+漢字1 +@mail漢字2 +漢字3");

        // すでに~演算子が付加されている場合には変更しない
        assertThat(createSerachWordsBooleanModeLogic.practice("漢字1　~漢字2 漢字3")).isEqualTo("+漢字1 ~漢字2 +漢字3");

        // *演算子は+演算子を付加する
        assertThat(createSerachWordsBooleanModeLogic.practice("漢字1　*漢字2 漢字3")).isEqualTo("+漢字1 +*漢字2 +漢字3");

        // すでに+演算子と-演算子は末尾でも機能するので付加しない
        assertThat(createSerachWordsBooleanModeLogic.practice("パーティ+")).isEqualTo("パーティ+");
        assertThat(createSerachWordsBooleanModeLogic.practice("パーティ-")).isEqualTo("パーティ-");

    }

}
