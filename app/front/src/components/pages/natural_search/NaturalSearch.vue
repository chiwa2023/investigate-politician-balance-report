<script setup lang="ts">
import { computed, Ref, ref } from "vue";
import IncomeAndOutcomeNaturalSearchConditionCapsuleDto from "../../../dto/natural_search/incomeAndOutcomeNaturalSearchConditionCapsuleDto";
import IncomeAndOutcomeNaturalSearchResultInterface from "../../../dto/natural_search/incomeAndOutcomeNaturalSearchResultDto";
import IncomeAndOutcomeNaturalSearchResultDto from "../../../dto/natural_search/incomeAndOutcomeNaturalSearchResultDto";
import IncomeAndOutcomeSearchLineDto from "../../../dto/natural_search/incomeAndOutcomeSearchLineDto";
import IncomeAndOutcomeSearchLineInterface from "../../../dto/natural_search/incomeAndOutcomeSearchLineDto";
import SelectOptionDto from "../../../dto/selectOptionDto";
import createPagingSelectBoxOption from "../../../dto/natural_search/createPagingSelectBoxOption";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";

// 検索条件
const conditionDto: Ref<IncomeAndOutcomeNaturalSearchConditionCapsuleDto> = ref(new IncomeAndOutcomeNaturalSearchConditionCapsuleDto());

// 検索結果
const resultDto: Ref<IncomeAndOutcomeNaturalSearchResultInterface> = ref(new IncomeAndOutcomeNaturalSearchResultDto());

// ページング(offset)
const listPagingOutcome: Ref<SelectOptionDto[]> = ref([]);
const listPagingIncome: Ref<SelectOptionDto[]> = ref([]);

// すでにレポートに追加していることを表すキーワード
const isAddText: string = "added";

// 表示レポートキーワード
// 政治団体視点
const reportKeyDantai: string = "dantai";
// 取引相手視点
const reportKeyPartner: string = "partner";
// 資金フロー視点
const reportKeyFlow: string = "flow";
// 資金フロー視点
const reportKeyFlowLeft: string = "flow-left";

// 選択中表示レポート
const selectedReport: Ref<string> = ref(reportKeyDantai);

// レポートリスト(団体・相手観点)
const listReport: Ref<IncomeAndOutcomeSearchLineInterface[]> = ref([]);

// レポートリスト(フロー観点)
const listFlowIncome: Ref<IncomeAndOutcomeSearchLineInterface[]> = ref([]);
const listFlowOutcome: Ref<IncomeAndOutcomeSearchLineInterface[]> = ref([]);

// レポートの集計(政治団体・取引相手視点)
const sumDantai = computed(() => {
    let sum: number = 0
    for (const dto of listReport.value) {
        sum = sum + dto.kingakuShuukei;
    }
    return sum.toLocaleString();
});

// レポートの集計(フロー視点)
const sumIncome = computed(() => {
    let sum: number = 0
    for (const dto of listFlowIncome.value) {
        sum = sum + dto.kingakuShuukei;
    }
    return sum.toLocaleString();
});

// レポートの集計(フロー視点)
const sumOutcome = computed(() => {
    let sum: number = 0
    for (const dto of listFlowOutcome.value) {
        sum = sum + dto.kingakuShuukei;
    }
    return sum.toLocaleString();
});


// 検索表示件数
const viewCount: number = 100;

/* 検索 */
async function onSearch() {
    // 検索前には日付を整頓する
    conditionDto.value.startDate = convertInputDateText(conditionDto.value.startDateText);
    conditionDto.value.endDate = convertInputDateText(conditionDto.value.endDateText);

    if (conditionDto.value.offsetIncome === -1) {
        conditionDto.value.offsetIncome = 0;
    }
    if (conditionDto.value.offsetOutcome === -1) {
        conditionDto.value.offsetOutcome = 0;
    }

    //実接続
    //セッションストレージ取得
    conditionDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    conditionDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    conditionDto.value.checkTransactionDto = createCheckTransactionDto(false);

    // 全文検索を行う
    const url = "http://localhost:9080/offering-data/search-income-outocome";
    const method = "POST";
    const body = JSON.stringify(conditionDto.value);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    fetch(url, { method, headers, body })
        .then(async (response) => {
            resultDto.value = await response.json();
        })
        .catch((error) => { alert(error); });

    listPagingIncome.value = createPagingSelectBoxOption(resultDto.value.countIncome, viewCount);
    listPagingOutcome.value = createPagingSelectBoxOption(resultDto.value.countOutcome, viewCount);

    // offsetが0…未検索かつ検索結果が返ったときはoffsetを1にする
    if ((conditionDto.value.offsetIncome === -1) && resultDto.value.listIncome.length > 0) {
        conditionDto.value.offsetIncome = 0;
    }
    if ((conditionDto.value.offsetOutcome === -1) && resultDto.value.listOutcome.length > 0) {
        conditionDto.value.offsetOutcome = 0;
    }
}

/* 検索 */
function convertInputDateText(inputDate: string): Date {
    const cell: string[] = inputDate.split("-");
    return new Date(parseInt(cell[0]), parseInt(cell[1]) - 1, parseInt(cell[2]));
}

/* レポート追加(収入) */
function onAddIncome(lineDto: IncomeAndOutcomeSearchLineDto) {
    lineDto.availableAddText = isAddText;
    listReport.value.push(lineDto);
    listFlowIncome.value.push(lineDto);
}

/* レポート追加(支出) */
function onAddOutcome(lineDto: IncomeAndOutcomeSearchLineDto) {
    lineDto.availableAddText = isAddText;
    listReport.value.push(lineDto);
    listFlowOutcome.value.push(lineDto);
}

function onMoveOffset() {

    // 両方のリストが空の場合はoffsetの変更による再検索を許さない
    if (resultDto.value.listIncome.length > 0 || resultDto.value.listOutcome.length > 0) {
        onSearch();
    }
}

/* レポート削除 */
function onDelete(lineDto: IncomeAndOutcomeSearchLineDto) {

    // 削除指定のDtoが収入だったらlistFlowOutcomeに挿入されていることはないが、
    // そういった事前の挿入可能性に関係なくすべてのチェックする
    listReport.value = listReport.value.filter((dto) => dto.itemId !== lineDto.itemId);
    listFlowIncome.value = listFlowIncome.value.filter((dto) => dto.itemId !== lineDto.itemId);
    listFlowOutcome.value = listFlowOutcome.value.filter((dto) => dto.itemId !== lineDto.itemId);

    // レポートから削除されたら挿入可能に戻す
    for (const dto of resultDto.value.listIncome) {
        if (dto.itemId === lineDto.itemId) {
            dto.availableAddText = "";
        }
    }
    for (const dto of resultDto.value.listOutcome) {
        if (dto.itemId === lineDto.itemId) {
            dto.availableAddText = "";
        }
    }
}

/* キャンセル */
function onCancel() {
    alert("キャンセル");
}

/* レポート保存 */
function onSave() {
    alert("保存");
}

</script>
<template>
    <h1>登録済文書支出／収入項目検索する</h1>

    <div class="left-area">
        検索期間
    </div>
    <div class="right-area">
        <input type="date" v-model="conditionDto.startDateText">から
        <input type="date" v-model="conditionDto.endDateText">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        検索語(入力)
    </div>
    <div class="right-area">
        ※『単語A 単語B』は単語Aと単語Bが含まれるAND検索とみなします。OR検索の場合は『&lt;単語A &lt;単語B』と不等号(&gt;でも可)を前につけて検索してください
        <textarea v-model="conditionDto.userKeyWords"></textarea>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        検索語(実際に検索)
    </div>
    <div class="right-area">
        <textarea disabled="true" v-model="resultDto.searchWords"></textarea>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        検索対象
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="conditionDto.isSearchIncome">収入を検索する <input type="checkbox"
            v-model="conditionDto.isSearchOutcome" class="left-space">支出を検索する
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        &nbsp;
    </div>
    <div class="right-area">
        <button @click="onSearch">検索する</button>
    </div>
    <div class="clear-both"></div>

    <h3>検索結果</h3>

    <div class="one-line">
        収入:
        {{ resultDto.countIncome }}件がヒットしました。
        <select v-model="conditionDto.offsetIncome" @change="onMoveOffset">
            <option v-for="(pageDto, index) in listPagingIncome" :key="index" :value="pageDto.value">{{ pageDto.text }}
            </option>
        </select>
        を表示しています
        <table>
            <tr>
                <th>発生日</th>
                <th>政治団体名</th>
                <th>代表者</th>
                <th>項目</th>
                <th>金額</th>
                <th>収入を得た相手</th>
                <th>相手先住所</th>
                <th>&nbsp;</th>
            </tr>
            <tr v-for="(lineDto, index) in resultDto.listIncome" :key="index" :class="lineDto.availableAddText">
                <td>{{ lineDto.accrualDate }}</td>
                <td>{{ lineDto.dantaiName }}</td>
                <td>{{ lineDto.daihyouName }}</td>
                <td>{{ lineDto.itemName }}</td>
                <td>{{ lineDto.kingakuIncomeText }}</td>
                <td>{{ lineDto.partnerName }}</td>
                <td>{{ lineDto.partnerJuusho }}</td>
                <td><button @click="onAddIncome(lineDto)" :disabled="lineDto.availableAddText === isAddText">追加</button>
                </td>
            </tr>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        支出:
        {{ resultDto.countOutcome }}件がヒットしました。
        <select v-model="conditionDto.offsetOutcome" @change="onMoveOffset">
            <option v-for="(pageDto, index) in listPagingOutcome" :key="index" :value="pageDto.value">{{ pageDto.text }}
            </option>
        </select>
        を表示しています
        <table>
            <tr>
                <th>発生日</th>
                <th>政治団体名</th>
                <th>代表者</th>
                <th>項目</th>
                <th>金額</th>
                <th>支出をした相手</th>
                <th>相手先住所</th>
                <th>&nbsp;</th>
            </tr>
            <tr v-for="(lineDto, index) in resultDto.listOutcome" :key="index" :class="lineDto.availableAddText">
                <td>{{ lineDto.accrualDate }}</td>
                <td>{{ lineDto.dantaiName }}</td>
                <td>{{ lineDto.daihyouName }}</td>
                <td>{{ lineDto.itemName }}</td>
                <td>{{ lineDto.kingakuOutcomeText }}</td>
                <td>{{ lineDto.partnerName }}</td>
                <td>{{ lineDto.partnerJuusho }}</td>
                <td><button @click="onAddOutcome(lineDto)"
                        :disabled="lineDto.availableAddText === isAddText">追加</button></td>
            </tr>

        </table>
    </div>
    <div class="clear-both"><br></div>

    <h3>レポート用集計</h3>
    <select v-model="selectedReport">
        <option :value="reportKeyDantai"> 政治団体視点</option>
        <option :value="reportKeyPartner"> 取引相手視点</option>
        <option :value="reportKeyFlow"> 資金フロー視点(支出)</option>
        <option :value="reportKeyFlowLeft"> 資金フロー視点(収入)</option>
    </select>
    <!-- 政治団体視点：合計算出と出入りが見やすい -->
    <div v-if="reportKeyDantai === selectedReport">
        政治団体視点
        <div class="one-line">
            <table>
                <tr>
                    <th>発生日</th>
                    <th>政治団体名</th>
                    <th>代表者</th>
                    <th>項目</th>
                    <th>収入金額</th>
                    <th>支出金額</th>
                    <th>取引相手</th>
                    <th>相手先住所</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="(lineDto, index) in listReport" :key="index">
                    <td>{{ lineDto.accrualDate }}</td>
                    <td>{{ lineDto.dantaiName }}</td>
                    <td>{{ lineDto.daihyouName }}</td>
                    <td>{{ lineDto.itemName }}</td>
                    <td>{{ lineDto.kingakuIncomeText }}</td>
                    <td>{{ lineDto.kingakuOutcomeText }}</td>
                    <td>{{ lineDto.partnerName }}</td>
                    <td>{{ lineDto.partnerJuusho }}</td>
                    <td><button @click="onDelete(lineDto)">削除</button></td>
                </tr>
                <tr>
                    <th colspan="4">&nbsp;</th>
                    <th>集計</th>
                    <th>{{ sumDantai }}</th>
                    <th colspan="2">&nbsp;</th>
                </tr>
            </table>
        </div>
        <div class="clear-both"><br></div>
    </div>

    <!-- 取引相手視点：合計算出と出入りが見やすい -->
    <div v-if="reportKeyPartner === selectedReport">
        取引相手視点：合計算出と団体間活動内容が見やすい
        <div class="one-line">
            <table>
                <tr>
                    <th>発生日</th>
                    <th>取引相手</th>
                    <th>相手先住所</th>
                    <th>項目</th>
                    <th>収入金額</th>
                    <th>支出金額</th>
                    <th>政治団体名</th>
                    <th>代表者</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="(lineDto, index) in listReport" :key="index">
                    <td>{{ lineDto.accrualDate }}</td>
                    <td>{{ lineDto.partnerName }}</td>
                    <td>{{ lineDto.partnerJuusho }}</td>
                    <td>{{ lineDto.itemName }}</td>
                    <td>{{ lineDto.kingakuIncomeText }}</td>
                    <td>{{ lineDto.kingakuOutcomeText }}</td>
                    <td>{{ lineDto.dantaiName }}</td>
                    <td>{{ lineDto.daihyouName }}</td>
                    <td><button @click="onDelete(lineDto)">削除</button></td>
                </tr>
                <tr>
                    <th colspan="4">&nbsp;</th>
                    <th>集計</th>
                    <th>{{ sumDantai }}</th>
                    <th colspan="2">&nbsp;</th>
                </tr>
            </table>
        </div>
        <div class="clear-both"><br></div>
    </div>


    <!-- フロー視点：合計算出と出入りが見やすい -->
    <div v-if="reportKeyFlow === selectedReport">
        フロー視点：資金の流れを可視化する<br>
        <div class="one-line" style="overflow: scroll;">
            <div style="float: left;overflow: scroll;width: 47%;">
                支出
                <table style="white-space: nowrap;">
                    <tr>
                        <th>&nbsp;</th>
                        <th>政治団体名</th>
                        <th>代表者</th>
                        <th>相手先住所</th>
                        <th>項目</th>
                        <th>支出をした相手</th>
                        <th>発生日</th>
                        <th>支出金額</th>
                    </tr>
                    <tr v-for="(lineDto, index) in listFlowOutcome" :key="index">
                        <td><button @click="onDelete(lineDto)">削除</button></td>
                        <td>{{ lineDto.dantaiName }}</td>
                        <td>{{ lineDto.daihyouName }}</td>
                        <td>{{ lineDto.partnerJuusho }}</td>
                        <td>{{ lineDto.itemName }}</td>
                        <td>{{ lineDto.partnerName }}</td>
                        <td>{{ lineDto.accrualDate }}</td>
                        <td>{{ lineDto.kingakuOutcomeText }}</td>
                    </tr>
                    <tr>
                        <th colspan="4">&nbsp;</th>
                        <th>集計</th>
                        <th>{{ sumOutcome }} </th>
                        <th colspan="2">&nbsp;</th>
                    </tr>
                </table>
            </div>

            <div style="float: left;vertical-align: middle;text-align: center;width: 5%;">
                <br>
                資金移動<br>
                ⇒⇒⇒<br>
            </div>

            <div style="float: right;overflow: scroll;;width: 47%;">
                収入
                <table style="white-space: nowrap;">
                    <tr>
                        <th>発生日</th>
                        <th>収入金額</th>
                        <th>政治団体名</th>
                        <th>項目</th>
                        <th>代表者</th>
                        <th>支出をした相手</th>
                        <th>相手先住所</th>
                        <th>&nbsp;</th>
                    </tr>
                    <tr v-for="(lineDto, index) in listFlowIncome" :key="index">
                        <td>{{ lineDto.accrualDate }}</td>
                        <td>{{ lineDto.kingakuIncomeText }}</td>
                        <td>{{ lineDto.dantaiName }}</td>
                        <td>{{ lineDto.itemName }}</td>
                        <td>{{ lineDto.daihyouName }}</td>
                        <td>{{ lineDto.partnerName }}</td>
                        <td>{{ lineDto.partnerJuusho }}</td>
                        <td><button @click="onDelete(lineDto)">削除</button></td>
                    </tr>
                    <tr>
                        <th colspan="4">&nbsp;</th>
                        <th>集計</th>
                        <th>{{ sumIncome }}</th>
                        <th colspan="2">&nbsp;</th>
                    </tr>
                </table>
            </div>
        </div>
        <div class="clear-both"><br></div>
    </div>

    <!-- フロー視点：合計算出と出入りが見やすい -->
    <div v-if="reportKeyFlowLeft === selectedReport">
        フロー視点：資金の流れを可視化する<br>
        <div class="one-line" style="overflow: scroll;">
            <div style="float: left;overflow: scroll;width: 47%;">

                収入
                <table style="white-space: nowrap;">
                    <tr>
                        <th>&nbsp;</th>
                        <th>代表者</th>
                        <th>相手先住所</th>
                        <th>政治団体名</th>
                        <th>項目</th>
                        <th>支出をした相手</th>
                        <th>発生日</th>
                        <th>収入金額</th>
                    </tr>
                    <tr v-for="(lineDto, index) in listFlowIncome" :key="index">
                        <td><button @click="onDelete(lineDto)">削除</button></td>
                        <td>{{ lineDto.daihyouName }}</td>
                        <td>{{ lineDto.partnerJuusho }}</td>
                        <td>{{ lineDto.dantaiName }}</td>
                        <td>{{ lineDto.itemName }}</td>
                        <td>{{ lineDto.partnerName }}</td>
                        <td>{{ lineDto.accrualDate }}</td>
                        <td>{{ lineDto.kingakuIncomeText }}</td>
                    </tr>
                    <tr>
                        <th colspan="4">&nbsp;</th>
                        <th>集計</th>
                        <th>{{ sumIncome }}</th>
                        <th colspan="2">&nbsp;</th>
                    </tr>
                </table>
            </div>

            <div style="float: left;vertical-align: middle;text-align: center;width: 5%;">
                <br>
                資金移動<br>
                ⇒⇒⇒<br>
            </div>

            <div style="float: right;overflow: scroll;;width: 47%;">
                支出
                <table style="white-space: nowrap;">
                    <tr>
                        <th>政治団体名</th>
                        <th>代表者</th>
                        <th>相手先住所</th>
                        <th>項目</th>
                        <th>支出をした相手</th>
                        <th>発生日</th>
                        <th>支出金額</th>
                        <th>&nbsp;</th>
                    </tr>
                    <tr v-for="(lineDto, index) in listFlowOutcome" :key="index">
                        <td>{{ lineDto.accrualDate }}</td>
                        <td>{{ lineDto.kingakuOutcomeText }}</td>
                        <td>{{ lineDto.partnerName }}</td>
                        <td>{{ lineDto.itemName }}</td>
                        <td>{{ lineDto.dantaiName }}</td>
                        <td>{{ lineDto.partnerJuusho }}</td>
                        <td>{{ lineDto.daihyouName }}</td>
                        <td><button @click="onDelete(lineDto)">削除</button></td>
                    </tr>
                    <tr>
                        <th colspan="4">&nbsp;</th>
                        <th>集計</th>
                        <th>{{ sumOutcome }} </th>
                        <th colspan="2">&nbsp;</th>
                    </tr>
                </table>
            </div>
        </div>
        <div class="clear-both"><br></div>
    </div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button">保存</button>
    </div>
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

tr.added {
    background-color: gainsboro;
}

textarea {
    width: 85%;
}
</style>
