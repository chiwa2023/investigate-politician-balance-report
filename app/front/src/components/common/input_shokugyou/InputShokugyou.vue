<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import SearchCorpNo from '../search_corp_no/SearchCorpNo.vue';
import type InputShokugyouInterface from '../../../dto/input_shokugyou/inputShokugyouDto';
import InputShokugyouDto from '../../../dto/input_shokugyou/inputShokugyouDto';

// props,emit
const props = defineProps<{ editDto: InputShokugyouDto, isfooter: boolean }>();
const emits = defineEmits(["sendCancelInputShokugyou", "sendInputShokugyouInterface"]);

const BLANK: string = "";
const inputShokugyouDto: Ref<InputShokugyouInterface> = ref(structuredClone(toRaw(props.editDto)));

/** 生成食用文字列を送信する */
function sendAllShokugyou() {
    switch (inputShokugyouDto.value.yakushoku) {
        // フリーランス
        case yakushokuFree:
            isShokugyoEdit.value = false;
            if (BLANK === inputShokugyouDto.value.shokugyouUserWrite) {
                // return inputPersonNoDto.value.gyoushu + "業従事者";
                inputShokugyouDto.value.allShokugyou = inputShokugyouDto.value.gyoushu + "業従事者";
                emits("sendInputShokugyouInterface", inputShokugyouDto.value);
            } else {
                //return inputPersonNoDto.value.shokugyouUserWrite;
                inputShokugyouDto.value.allShokugyou = inputShokugyouDto.value.shokugyouUserWrite;
                emits("sendInputShokugyouInterface", inputShokugyouDto.value);
            }
            break;
        // 団体所属者
        case yakushokuGeneral:
            isShokugyoEdit.value = false;
            if (BLANK === inputShokugyouDto.value.shokugyouUserWrite) {
                //return inputPersonNoDto.value.gyoushu + "業社員・職員";
                inputShokugyouDto.value.allShokugyou = inputShokugyouDto.value.gyoushu + "業社員・職員";
                emits("sendInputShokugyouInterface", inputShokugyouDto.value);
            } else {
                //return inputPersonNoDto.value.shokugyouUserWrite;
                inputShokugyouDto.value.allShokugyou = inputShokugyouDto.value.shokugyouUserWrite;
                emits("sendInputShokugyouInterface", inputShokugyouDto.value);
            }
            break;
        // 役職者
        case yakushokuDirector:
            isShokugyoEdit.value = true;
            if (BLANK === inputShokugyouDto.value.corpNo) {
                //return inputPersonNoDto.value.gyoushu + "業役職者(社名記載拒否)";
                inputShokugyouDto.value.allShokugyou = inputShokugyouDto.value.gyoushu + "業役職者(社名記載拒否)";
                emits("sendInputShokugyouInterface", inputShokugyouDto.value);
            } else {
                //return corpName.value + "(" + corpPrefecture.value + ")" + "役員";
                //emits("sendInputShokugyouInterface", inputShokugyouDto.value.gyoushu + corpName.value + "(" + corpPrefecture.value + ")" + "役員");
                inputShokugyouDto.value.allShokugyou = inputShokugyouDto.value.gyoushu + inputShokugyouDto.value.corpName + "(" + inputShokugyouDto.value.corpAddress + ")" + "役員";
                emits("sendInputShokugyouInterface", inputShokugyouDto.value);
            }
            break;
        // 定職なし
        case yakushokuNoJob:
            isShokugyoEdit.value = false;
            inputShokugyouDto.value.allShokugyou = inputShokugyouDto.value.shokugyouUserWrite;
            emits("sendInputShokugyouInterface", inputShokugyouDto.value);
            break;
        default:
            isShokugyoEdit.value = true;
            break;
    }
}

// 職業入力定義
const yakushokuFree: string = "所属なし";
const yakushokuGeneral: string = "一般職員";
const yakushokuDirector: string = "役職者";
const yakushokuNoJob: string = "定職なし";

const isShokugyoEdit: Ref<boolean> = ref(true);

const isCorpSearch: Ref<boolean> = ref(false);
// 検索リスト
const listCorp: Ref<CorpNoInterface[]> = ref([]);

/**
 * 法人検索表示
 */
function onSearchCorpNo() {
    isCorpSearch.value = true;
}


/**
 * 法人情報受信
 */
function recieveCorpNoInterface(sendDto: CorpNoInterface) {

    inputShokugyouDto.value.corpNo = sendDto.corpNo;
    inputShokugyouDto.value.corpAddress = sendDto.inputAddress.addressPostal;
    inputShokugyouDto.value.corpName = sendDto.corpName;

    sendAllShokugyou();

    //非表示
    isCorpSearch.value = false;
}

/**
 * 法人検索キャンセル
 */
function recieveCancelCorpNo() {
    //非表示
    isCorpSearch.value = false;
}


function onCancel() {
    emits("sendCancelInputShokugyou");
}

</script>
<template>
    <div v-if="isfooter">
        <div class="left-area">
            職業
        </div>
        <div class="right-area">
            <input type="text" v-model="inputShokugyouDto.allShokugyou">
        </div>
        <div class="clear-both"><br></div>
    </div>
    
    <div class="left-area">
        職業(1)
    </div>
    <div class="right-area">
        <span>業種(日本標準産業分類)&nbsp;<select v-model="inputShokugyouDto.gyoushu" @change="sendAllShokugyou">
                <option value=""></option>
                <option value="農林"> A.農業，林業</option>
                <option value="水産">B.漁業</option>
                <option value="鉱業">C.鉱業，採石業，砂利採取業</option>
                <option value="建設">D.建設業</option>
                <option value="製造">E.製造業</option>
                <option value="インフラ">F.電気・ガス・熱供給・水道業</option>
                <option value="情報通信">G.情報通信業</option>
                <option value="運輸">H.運輸業，郵便業</option>
                <option value="流通小売">I.卸売業，小売業</option>
                <option value="金融">J.金融業，保険業</option>
                <option value="不動産">K.不動産業，物品賃貸業</option>
                <option value="学術研究">L.学術研究，専門・技術サービス業</option>
                <option value="宿泊飲食">M.宿泊業，飲食サービス業</option>
                <option value="娯楽">N.生活関連サービス業，娯楽業</option>
                <option value="教育学習">O.教育，学習支援業</option>
                <option value="医療・福祉">P.医療，福祉</option>
                <option value="複合">Q.複合サービス事業</option>
                <option value="その他サービス">R.サービス業（他に分類されないもの）</option>
                <option value="公務">S.公務（他に分類されるものを除く）</option>
                <option value="分類不能">T.分類不能の産業</option>
            </select></span>
        <span class="left-space">役職&nbsp;<select v-model="inputShokugyouDto.yakushoku" @change="sendAllShokugyou">
                <option :value="yakushokuFree">フリーランス(所属団体なし・法人登記なし)</option>
                <option :value="yakushokuGeneral">一般職員(パート含む)</option>
                <option :value="yakushokuDirector">法人役員(一人企業の社長)</option>
                <option :value="yakushokuNoJob">定職なし</option>
            </select></span>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        職業(2)
    </div>
    <div class="right-area">
        <div v-if="yakushokuDirector !== inputShokugyouDto.yakushoku"><input type="text"
                v-model="inputShokugyouDto.shokugyouUserWrite" :disabled="isShokugyoEdit" class="max-input"
                @change="sendAllShokugyou"></div>
        <div v-if="yakushokuDirector === inputShokugyouDto.yakushoku">
            <input type="text" v-model="inputShokugyouDto.corpNo" class="code-input" disabled="true">
            <input type="text" v-model="inputShokugyouDto.corpAddress" class="code-input left-space" disabled="true">
            <input type="text" v-model="inputShokugyouDto.corpName" class="left-space text-input" disabled="true">
            <button class="left-space" @click="onSearchCorpNo">企業／団体検索</button>
        </div>
    </div>
    <div class="clear-both"></div>

    <div class="footer" v-if="isfooter">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

    <div v-if="isCorpSearch" class="overBackgroundLayer2"></div>
    <div v-if="isCorpSearch">
        <div class="overComponentLayer2">
            <SearchCorpNo :list="listCorp" :is-footer="true" @send-corp-no-interface="recieveCorpNoInterface"
                @send-canceel-corp-no="recieveCancelCorpNo"></SearchCorpNo>
        </div>
    </div>

</template>
<style scoped></style>
