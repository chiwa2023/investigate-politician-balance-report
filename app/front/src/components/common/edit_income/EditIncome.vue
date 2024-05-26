<script setup lang="ts">
import { computed, WritableComputedRef, watch } from "vue";
import viewPrepareIncome from "../../../dto/balancesheet/viewPrepareIncome";
import changeIncomeYoushikiKbnState from "../../../dto/balancesheet/changeIncomeYoushikiKbnState";
import changeStateIncomeEdaKbn from "../../../dto/balancesheet/changeStateIncomeEdaKbn";
import AuditOpinionIncomeInterface from "../../../dto/audit_opinion/auditOpinionIncomeDto";

//表示・編集対象
const props = defineProps<{ incomeDto: AuditOpinionIncomeInterface }>();
const incomeItem: WritableComputedRef<AuditOpinionIncomeInterface> = computed(
    () => {
        return props.incomeDto;
    }
);

/** 初期データ */
watch(() => incomeItem.value, () => {
    viewPrepareIncome(incomeItem.value);
});

/** 報告区分を監視 */
watch(() => incomeItem.value.reportKbn, () => {
    viewPrepareIncome(incomeItem.value);
});

//様式区分を監視
watch(() => incomeItem.value.youshikiKbn, () => {
    changeIncomeYoushikiKbnState(incomeItem.value);
});

//様式枝区分を監視
watch(() => incomeItem.value.youshikiEdaKbn, () => {
    changeStateIncomeEdaKbn(incomeItem.value);
});

</script>
<template>
    <h3>収入項目の編集</h3>
    <div class="left-area">自動入力編集</div>
    <div class="right-area">
        <input type="checkbox" v-model="incomeItem.isEditAutoInput">入力できない部分を入力可能にする
    </div>
    <!-- TODO 書証参照は仕様が決定次第修正する
    <div class="left-area">書証タイプ</div>
    <div class="right-area">
        <input type="checkbox" v-model="incomeItem.isEditAutoInput">入力できない部分を入力可能にする
    </div>
    -->
    <div class="left-area">収支報告区分</div>
    <div class="right-area">
        <input type="radio" v-model="incomeItem.reportKbn" :value="1">報告対象<br><input type="radio"
            v-model="incomeItem.reportKbn" :value="20">報告対象外(生活費)<br><input type="radio" v-model="incomeItem.reportKbn"
            :value="11">報告対象外政治関連(廃止予定)<br><input type="radio" v-model="incomeItem.reportKbn" :value="50">タスク計上(後で確定)
    </div>
    <div class="clear-both" />

    <div v-show="incomeItem.isUseYoushikiKbn">
        <div class="left-area">様式区分</div>
        <div class="right-area">
            <select v-model="incomeItem.youshikiKbn">
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
        <div class="clear-both" />
    </div>

    <div v-show="incomeItem.isUseYoushikiEdaKbn">
        <div class="left-area">様式枝区分項目</div>
        <div class="right-area">
            <select v-model="incomeItem.youshikiEdaKbn">
                <option value="1">1.個人</option>
                <option value="2">2.法人その他の団体</option>
                <option value="3">3.政治団体</option>
            </select>
        </div>
        <div class="clear-both" />
    </div>

    <div class="left-area">発生日</div>
    <div class="right-area">
        <input type="date" v-model="incomeItem.accrualDate" :disabled="!incomeItem.isEditAutoInput">
    </div>
    <div class="clear-both" />

    <div class="left-area">取引金額</div>
    <div class="right-area">
        <input type="number" v-model="incomeItem.amount" :disabled="!incomeItem.isEditAutoInput">
    </div>
    <div class="clear-both" />

    <div v-show="incomeItem.isUseMediation">
        <div class="left-area">あっせん期間</div>
        <div class="right-area">
            <input type="date" v-model="incomeItem.mediationStartDate">から<input type="date"
                v-model="incomeItem.mediationEndDate">
        </div>
        <div class="clear-both" />
    </div>

    <!-- 項目名称 -->
    <div v-show="incomeItem.isUseItemName">
        <div class="left-area">{{ incomeItem.attentionItemName }}</div>
        <div class="right-area">
            <input type="text" v-model="incomeItem.itemName">
        </div>
        <div class="clear-both" />
    </div>

    <!-- 個人・団体住所 -->
    <div v-show="incomeItem.isUseAddress">
        <div class="left-area">{{ incomeItem.attentionAddress }}</div>
        <div class="right-area">
            <textarea v-model="incomeItem.orgnizationAddress"></textarea>
        </div>
        <div class="clear-both" />
    </div>

    <!-- 個人職業または団体代表者氏名 -->
    <div v-show="incomeItem.isUseOrgName">
        <div class="left-area">{{ incomeItem.attentionOrgName }}</div>
        <div class="right-area">
            <input type="text" v-model="incomeItem.professionOrgnizationName">
        </div>
        <div class="clear-both" />
    </div>

    <div v-show="incomeItem.isUsePartyName">
        <div class="left-area">パーティ名称</div>
        <div class="right-area">
            <input type="text" v-model="incomeItem.partyName">
        </div>
        <div class="clear-both" />
    </div>

    <div v-show="incomeItem.isUsePartyDate">
        <div class="left-area">パーティ開催日</div>
        <div class="right-area">
            <input type="date" v-model="incomeItem.partyDate">
        </div>
        <div class="clear-both" />
    </div>

    <div v-show="incomeItem.isUseBiko">
        <div class="left-area">備考</div>
        <div class="right-area">
            <input type="text" v-model="incomeItem.biko">
            <div v-if="incomeItem.isUseBequest">
                <input type="checkbox" v-model="incomeItem.isBequest">遺贈
            </div>
            <div v-if="incomeItem.isUsePrimeListedOrForeign">
                <input type="checkbox" v-model="incomeItem.isPrimeListedOrForeign">上場・外資50%超
            </div>

        </div>
        <div class="clear-both" />
    </div>

    <div v-show="incomeItem.isUseCreditTax">
        <div class="left-area">寄付金控除</div>
        <div class="right-area">
            <input type="checkbox" v-model="incomeItem.isCreditTax">寄付金(税額)控除
        </div>
        <div class="clear-both" />
    </div>

    <div class="left-area">意見付記</div>
    <div class="right-area">
        <textarea v-model="incomeItem.note"></textarea>
    </div>
    <div class="clear-both" />

    <div class="left-area">前例と異なる処理</div>
    <div class="right-area">
        <input type="checkbox" v-model="incomeItem.isDifferPrecedent">前例と異なる処理
    </div>
    <div class="clear-both" />

    <!-- TODO 保全書証参照は仕様が決定次第修正する -->
    <div class="left-area">保全した書証</div>
    <div class="right-area">
        <button disabled="true">書証を参照する</button>
    </div>
    <div class="clear-both" />

</template>
<style scoped></style>
