<script setup lang="ts">
import { ref, Ref, WritableComputedRef, computed, watch } from "vue";
import YoushikiKbnIncomeConstants from "../../../dto/balancesheet/youshikiKbnIncomeConstants";
import YoushikiEdaKbnIncomeConstants from "../../../dto/balancesheet/youshikiEdaKbnIncomeConstants";
import ReportKbnConstants from "../../../dto/balancesheet/reportKbnConstants";
import AuditOpinionIncomeInterface from "../../../dto/audit_opinion/auditOpinionIncomeDto";
import AuditOpinionIncomeDto from "../../../dto/audit_opinion/auditOpinionIncomeDto";
import mockGetAuditOpinionIncome from "../search_audit_opinion_income/mock/mockGetAuditOpinionIncome";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import SelectOptionDto from "../../../dto/selectOptionDto";

//props,emits
const props = defineProps<{ incomeCode: number }>();
const emits = defineEmits(["sendNewestIncome", "sendSelectIncome"]);

//収入項目同一識別コード
const code: WritableComputedRef<number> = computed(
    () => props.incomeCode,
);

//表示用リスト
const listAllIncome: Ref<AuditOpinionIncomeInterface[]> = ref([]);
const listOldIncome: Ref<AuditOpinionIncomeInterface[]> = ref([]);

//最新Dto
const newestIncomeDto: Ref<AuditOpinionIncomeInterface> = ref(new AuditOpinionIncomeDto());

//データセットフラグ
const isDataSet: Ref<boolean> = ref(false);

//同一識別コードの更新を監視
watch(code, () => {
    if (code.value !== 0) {

        // 同一識別コードを持つデータを処理履歴として取得
        listAllIncome.value = mockGetAuditOpinionIncome(code.value);

        //渡された収入リストが空でなければ最新データを取得する    
        const listLength = listAllIncome.value.length;
        if (listLength > 0) {
            newestIncomeDto.value = listAllIncome.value[0];
        }

        //履歴が複数の場合は選択用リストにコピーする
        if (listLength > 1) {
            listOldIncome.value.splice(0);
            const max = listLength;
            for (let index = 1; index < max; index++) {
                listOldIncome.value.push(listAllIncome.value[index]);
            }
        }

        //最新データを比較表示用に投げる

        const listAgreement:SelectOptionInterface[] = [];
        for(const auditDto of listAllIncome.value){

            const option:SelectOptionInterface = new SelectOptionDto();
            option.value = String(auditDto.auditOpinionId);
            option.text = auditDto.updateTime.toLocaleDateString("ja-Jp") + " " +auditDto.updateTime.toLocaleTimeString("ja-Jp")+ "("+auditDto.loginUserRole+")";

            listAgreement.push(option);
        }

        emits("sendNewestIncome", newestIncomeDto.value,listAgreement);

        isDataSet.value = true;
    }
    else {
        //コードが初期値なら初期化
        listAllIncome.value.splice(0);
        listOldIncome.value.splice(0);
        newestIncomeDto.value = new AuditOpinionIncomeDto();

        //最新データも非表示にする
        isDataSet.value = false;
    }
});

/**  
 * 選択データを送信する
 * @param rowId その行のDtoのId
 */
function selectRow(rowId: number) {
    //Primary Keyをもとに1件に絞込み
    const selectedDto: AuditOpinionIncomeInterface = listOldIncome.value.filter((dto) => dto.balancesheetIncomeId == rowId)[0];

    //最新データを比較表示用に投げる
    emits("sendSelectIncome", selectedDto);
}

/**
 * タイムスタンプを表示テキスト変換
 * @param date タイムスタンプ
 * @returns 表示テキスト
 */
function viewTimestamp(date: Date): string {
    return date.toLocaleDateString("ja-JP") + " " + date.toLocaleTimeString("ja-JP");
}

/**
 * 収支報告区分を表示テキスト変換
 * @param kbn 区分コード
 * @returns 表示テキスト
 */
function viewReportKbn(kbn: number): string {
    return ReportKbnConstants.convertText(kbn);
}

/**
 * 様式区分を表示テキスト変換
 * @param kbn 区分コード
 * @returns 表示テキスト
 */
function viewYoushiKbn(kbn: number): string {
    return YoushikiKbnIncomeConstants.convertText(kbn);
}

/**
 * 様式枝区分を表示テキスト変換
 * @param kbn 区分コード
 * @returns 表示テキスト
 */
function viewYoushiEdaKbn(kbn: number): string {
    return YoushikiEdaKbnIncomeConstants.convertText(kbn);
}

/**
 * 長い意見付記を前方200文字にカット
 * @param note 意見付記
 * @returns 省略した意見付記
 */
function digestNote(note: string): string {

    let content: string = "";

    if (note.length > 200) {
        content = note.substring(0, 200);
    } else {
        content = note;
    }

    return content;
}

</script>
<template>
    <h2>処理履歴</h2>
    <table>
        <tr>
            <th>更新時間</th>
            <th>作業者</th>
            <th>報告区分</th>
            <th>摘要</th>
            <th>発生日</th>
            <th>取引金額</th>
            <th>様式区分</th>
            <th>意見付記</th>
            <th>参照</th>
        </tr>

        <tr v-show="isDataSet">
            <td>{{ viewTimestamp(newestIncomeDto.updateTime) }}</td>
            <td>{{ newestIncomeDto.loginUserRole }}</td>
            <td>{{ viewReportKbn(newestIncomeDto.reportKbn) }}</td>
            <td>{{ newestIncomeDto.itemName }}</td>
            <td>{{ newestIncomeDto.accrualDate }}</td>
            <td>{{ newestIncomeDto.amount }}</td>
            <td>{{ viewYoushiKbn(newestIncomeDto.youshikiKbn) }}-{{ viewYoushiEdaKbn(newestIncomeDto.youshikiEdaKbn) }}
            </td>
            <td>{{ digestNote(newestIncomeDto.note) }}</td>
            <td>最新(左)</td>
        </tr>
        <!-- 区分け用の空行 -->
        <tr v-show="isDataSet">
            <td colspan="9">&nbsp;</td>
        </tr>
        <tbody>
            <tr v-for="itemLoop in listOldIncome" :key="itemLoop.balancesheetIncomeId">
                <td>{{ viewTimestamp(itemLoop.updateTime) }}</td>
                <td>{{ itemLoop.loginUserRole }}</td>
                <td>{{ viewReportKbn(itemLoop.reportKbn) }}</td>
                <td>{{ itemLoop.itemName }}</td>
                <td>{{ itemLoop.accrualDate }}</td>
                <td>{{ itemLoop.amount }}</td>
                <td>{{ viewYoushiKbn(itemLoop.youshikiKbn) }}-{{ viewYoushiEdaKbn(itemLoop.youshikiEdaKbn) }}</td>
                <td>{{ digestNote(itemLoop.note) }}</td>
                <td><button @click="selectRow(itemLoop.balancesheetIncomeId)">比較対象(右)</button></td>
            </tr>
        </tbody>
    </table>
</template>
<style scoped>
table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
