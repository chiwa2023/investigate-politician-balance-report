<script setup lang="ts">
import { WritableComputedRef, computed } from "vue";
import AuditOpinionIncomeInterface from "../../../dto/audit_opinion/auditOpinionIncomeDto";
import viewPrepareIncome from "../../../dto/balancesheet/viewPrepareIncome";


//props
const props = defineProps<{ newsetDto: AuditOpinionIncomeInterface, selectedDto: AuditOpinionIncomeInterface }>();

//最新データ
const newestIncomeItem: WritableComputedRef<AuditOpinionIncomeInterface> = computed(
    () => {
        return viewPrepareIncome(props.newsetDto);
    }
);

//選択比較データ
const selectedIncomeItem: WritableComputedRef<AuditOpinionIncomeInterface> = computed(
    () => {
        return viewPrepareIncome(props.selectedDto);
    }
);

/**
 * タイムスタンプを表示テキスト変換
 * @param date タイムスタンプ
 * @returns 表示テキスト
 */
function viewTimestamp(date: Date): string {
    return date.toLocaleDateString("ja-JP") + " " + date.toLocaleTimeString("ja-JP");
}

</script>
<template>
    <h2>最新データと選択データの比較</h2>
    <table style="width:95%">
        <tr>
            <th>項目</th>
            <th style="width:40%">最新</th>
            <th style="width:40%">比較</th>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td style="width:40%">{{ viewTimestamp(newestIncomeItem.updateTime) }} ({{ newestIncomeItem.loginUserRole
                }})</td>
            <td style="width:40%">{{ viewTimestamp(newestIncomeItem.updateTime) }} ({{ newestIncomeItem.loginUserRole
                }})</td>
        </tr>
        <tr>
            <th>自動入力編集</th>
            <td><input type="checkbox" v-model="newestIncomeItem.isEditAutoInput" :disabled="true">入力できない部分を入力可能にする</td>
            <td><input type="checkbox" v-model="selectedIncomeItem.isEditAutoInput" :disabled="true">入力できない部分を入力可能にする
            </td>
        </tr>
        <tr>
            <th>収支報告区分</th>
            <td>
                <input type="radio" v-model="newestIncomeItem.reportKbn" :value="1" :disabled="true">報告対象<br><input
                    type="radio" v-model="newestIncomeItem.reportKbn" :value="20" :disabled="true">報告対象外(生活費)<br><input
                    type="radio" v-model="newestIncomeItem.reportKbn" :value="11"
                    :disabled="true">報告対象外政治関連(廃止予定)<br><input type="radio" v-model="newestIncomeItem.reportKbn"
                    :value="50" :disabled="true">タスク計上(後で確定)

            </td>
            <td>
                <input type="radio" v-model="selectedIncomeItem.reportKbn" :value="1" :disabled="true">報告対象<br><input
                    type="radio" v-model="selectedIncomeItem.reportKbn" :value="20"
                    :disabled="true">報告対象外(生活費)<br><input type="radio" v-model="selectedIncomeItem.reportKbn"
                    :value="11" :disabled="true">報告対象外政治関連(廃止予定)<br><input type="radio"
                    v-model="selectedIncomeItem.reportKbn" :value="50" :disabled="true">タスク計上(後で確定)

            </td>
        </tr>

        <tr>
            <th>様式区分</th>
            <td>
                <div v-show="newestIncomeItem.isUseYoushikiKbn">
                <select v-model="newestIncomeItem.youshikiKbn" :disabled="true">
                    <option value="3">3.機関誌発行その他事業収入</option>
                    <option value="4">4.借入金</option>
                    <option value="5">5.本部／支部から交付金収入</option>
                    <option value="6">6.その他の収入</option>
                    <option value="7">7.寄付の内訳</option>
                    <option value="8">8.寄付の内訳(あっせん)</option>
                    <option value="9">9.政党匿名寄付</option>
                    <option value="10" :disabled="false">10.特定パーティの対価に係る収入</option>
                    <option value="11">11.政治資金パーティー収入</option>
                    <option value="12">12.政治資金パーティ収入(あっせん)</option>
                </select>
                </div>
            </td>
            <td>
                <div v-show="selectedIncomeItem.isUseYoushikiKbn">
                <select v-model="selectedIncomeItem.youshikiKbn" :disabled="true">
                    <option value="3">3.機関誌発行その他事業収入</option>
                    <option value="4">4.借入金</option>
                    <option value="5">5.本部／支部から交付金収入</option>
                    <option value="6">6.その他の収入</option>
                    <option value="7">7.寄付の内訳</option>
                    <option value="8">8.寄付の内訳(あっせん)</option>
                    <option value="9">9.政党匿名寄付</option>
                    <option value="10" :disabled="false">10.特定パーティの対価に係る収入</option>
                    <option value="11">11.政治資金パーティー収入</option>
                  div>  <option value="12">12.政治資金パーティ収入(あっせん)</option>
                </select>
            </div>
            </td>
        </tr>

        <tr>
            <th>様式枝区分項目</th>
            <td>
                <div v-show="newestIncomeItem.isUseYoushikiEdaKbn">
                <select v-model="newestIncomeItem.youshikiEdaKbn" :disabled="true">
                    <option value="1">1.個人</option>
                    <option value="2">2.法人その他の団体</option>
                    <option value="3">3.政治団体</option>
                </select>
            </div>
            </td>
            <td>
                <div v-show="selectedIncomeItem.isUseYoushikiEdaKbn">

                <select v-model="selectedIncomeItem.youshikiEdaKbn" :disabled="true">
                    <option value="1">1.個人</option>
                    <option value="2">2.法人その他の団体</option>
                    <option value="3">3.政治団体</option>
                </select>
            </div>
            </td>
        </tr>

        <tr>
            <th>発生日</th>
            <td>
                <input type="date" v-model="newestIncomeItem.accrualDate" :disabled="true">
            </td>
            <td>
                <input type="date" v-model="selectedIncomeItem.accrualDate" :disabled="true">
            </td>
        </tr>

        <tr>
            <th>取引金額</th>
            <td>
                <input type="number" v-model="newestIncomeItem.amount" :disabled="true">
            </td>
            <td>
                <input type="number" v-model="selectedIncomeItem.amount" :disabled="true">
            </td>
        </tr>

        <tr>
            <th>あっせん期間</th>
            <td>
                <div v-show="newestIncomeItem.isUseMediation">
                <input type="date" v-model="newestIncomeItem.mediationStartDate" :disabled="true">から<input type="date"
                    v-model="newestIncomeItem.mediationEndDate" :disabled="true">
                    </div>
            </td>
            <td>
                <div v-show="selectedIncomeItem.isUseMediation">
                <input type="date" v-model="selectedIncomeItem.mediationStartDate" :disabled="true">から<input type="date"
                    v-model="selectedIncomeItem.mediationEndDate" :disabled="true">
                    </div>
            </td>
        </tr>

        <tr>
            <th>項目名称</th>
            <td>
                <div v-show="newestIncomeItem.isUseItemName">
                {{ newestIncomeItem.attentionItemName }}<br>
                <input type="text" v-model="newestIncomeItem.itemName" :disabled="true">
                    </div>
            </td>
            <td>
                <div v-show="selectedIncomeItem.isUseItemName">
                {{ selectedIncomeItem.attentionItemName }}<br>
                <input type="text" v-model="selectedIncomeItem.itemName" :disabled="true">
                </div>
            </td>
        </tr>

        <tr>
            <th>個人・団体住所</th>
            <td>
                <div v-show="newestIncomeItem.isUseAddress">
                {{ newestIncomeItem.attentionAddress }}<br>
                <textarea v-model="newestIncomeItem.orgnizationAddress" :disabled="true"></textarea>
                </div>
            </td>
            <td>
                <div v-show="selectedIncomeItem.isUseAddress">
                {{ selectedIncomeItem.attentionAddress }}<br>
                <textarea v-model="selectedIncomeItem.orgnizationAddress" :disabled="true"></textarea>
                </div>
            </td>
        </tr>

        <tr>
            <th>個人職業・団体代表者</th>
            <td>
                <div v-show="newestIncomeItem.isUseOrgName">
                {{ newestIncomeItem.attentionOrgName }}<br>
                <input type="text" v-model="newestIncomeItem.professionOrgnizationName" :disabled="true">
                </div>
            </td>
            <td>
                <div v-show="selectedIncomeItem.isUseOrgName">
                {{ selectedIncomeItem.attentionOrgName }}<br>
                <input type="text" v-model="selectedIncomeItem.professionOrgnizationName" :disabled="true">
                </div>
            </td>
        </tr>

        <tr>
            <th>パーティ名称</th>
            <td>
                <div v-show="newestIncomeItem.isUsePartyName">
                <input type="text" v-model="newestIncomeItem.partyName" :disabled="true">
                </div>
            </td>
            <td>
                <div v-show="selectedIncomeItem.isUsePartyName">
                <input type="text" v-model="selectedIncomeItem.partyName" :disabled="true">
                </div>
            </td>
        </tr>

        <tr>
            <th>パーティ開催日</th>
            <td>
                <div v-show="newestIncomeItem.isUsePartyDate">
                <input type="date" v-model="newestIncomeItem.partyDate" :disabled="true">
                </div>
            </td>
            <td>
                <div v-show="selectedIncomeItem.isUsePartyDate">
                <input type="date" v-model="selectedIncomeItem.partyDate" :disabled="true">
                </div>
            </td>
        </tr>

        <tr>
            <th>備考</th>
            <td>
                <div v-show="newestIncomeItem.isUseBiko">
                <input type="text" v-model="newestIncomeItem.biko" :disabled="true">
                <div v-if="newestIncomeItem.isUseBequest">
                    <input type="checkbox" v-model="newestIncomeItem.isBequest" :disabled="true">遺贈
                </div>
                <div v-if="newestIncomeItem.isUsePrimeListedOrForeign">
                    <input type="checkbox" v-model="newestIncomeItem.isPrimeListedOrForeign" :disabled="true">上場・外資50%超
                </div>
                </div>
            </td>
            <td>
                <div v-show="selectedIncomeItem.isUseBiko">
                <input type="text" v-model="selectedIncomeItem.biko" :disabled="true">
                <div v-if="newestIncomeItem.isUseBequest">
                    <input type="checkbox" v-model="selectedIncomeItem.isBequest" :disabled="true">遺贈
                </div>
                <div v-if="selectedIncomeItem.isUsePrimeListedOrForeign">
                    <input type="checkbox" v-model="selectedIncomeItem.isPrimeListedOrForeign"
                        :disabled="true">上場・外資50%超
                </div>
                </div>
            </td>
        </tr>

        <tr>
            <th>寄付金控除</th>
            <td>
                <div v-show="newestIncomeItem.isUseCreditTax">
                <input type="checkbox" v-model="newestIncomeItem.isCreditTax" :disabled="true">寄付金(税額)控除
                </div>
            </td>
            <td>
                <div v-show="selectedIncomeItem.isUseCreditTax">
                <input type="checkbox" v-model="selectedIncomeItem.isCreditTax" :disabled="true">寄付金(税額)控除
                </div>
            </td>
        </tr>

        <tr>
            <th>意見付記</th>
            <td>
                <textarea v-model="newestIncomeItem.note" :disabled="true"></textarea>
            </td>
            <td>
                <textarea v-model="selectedIncomeItem.note" :disabled="true"></textarea>
            </td>
        </tr>

        <tr>
            <th>前例と異なる処理</th>
            <td>
                <input type="checkbox" v-model="newestIncomeItem.isDifferPrecedent" :disabled="true">前例と異なる処理
            </td>
            <td>
                <input type="checkbox" v-model="selectedIncomeItem.isDifferPrecedent" :disabled="true">前例と異なる処理
            </td>
        </tr>

        <tr>
            <th>保全した書証</th>
            <td>
                <button disabled="true">書証を参照する</button>
            </td>
            <td>
                <button disabled="true">書証を参照する</button>
            </td>
        </tr>

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
