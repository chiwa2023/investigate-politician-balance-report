<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import SearchCorpNo from '../search_corp_no/SearchCorpNo.vue';
import type CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import ViewInputPersonName from '../input_person_name/ViewInputPersonName.vue';
import InputPersonNameInterface from '../../../dto/input_person_name/inputPersonNameDto';
import InputPersonNameDto from '../../../dto/input_person_name/inputPersonNameDto';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';

const props = defineProps<{ editDto: PersonNoInterface }>();
const inputPersonNoDto: ComputedRef<PersonNoInterface> = computed(() => { return props.editDto });

const BLANK: string = "";

// 職業入力定義
const yakushokuFree: string = "所属なし";
const yakushokuGeneral: string = "一般職員";
const yakushokuDirector: string = "役職者";
const yakushokuNoJob: string = "定職なし";

// 職業入力用
const allShokugyou: ComputedRef<string> = computed(() => {
    // return gyoushu.value + yakushoku.value; 

    switch (inputPersonNoDto.value.yakushoku) {
        // フリーランス
        case yakushokuFree:
            isShokugyoEdit.value = false;
            if (BLANK === inputPersonNoDto.value.shokugyouUserWrite) {
                return inputPersonNoDto.value.gyoushu + "業従事者";
            } else {
                return inputPersonNoDto.value.shokugyouUserWrite;
            }
        // 団体所属者
        case yakushokuGeneral:
            isShokugyoEdit.value = false;
            if (BLANK === inputPersonNoDto.value.shokugyouUserWrite) {
                return inputPersonNoDto.value.gyoushu + "業社員・職員";
            } else {
                return inputPersonNoDto.value.shokugyouUserWrite;
            }

        // 役職者
        case yakushokuDirector:
            isShokugyoEdit.value = true;
            if (BLANK === corpNo.value) {
                return inputPersonNoDto.value.gyoushu + "業役職者(社名記載拒否)";
            } else {
                return corpName.value + "(" + corpPrefecture.value + ")" + "役員";
            }
        // 定職なし
        case yakushokuNoJob:
            isShokugyoEdit.value = false;
            return inputPersonNoDto.value.shokugyouUserWrite;
        default:
            isShokugyoEdit.value = true;
            return "";
    }
});
const isShokugyoEdit: Ref<boolean> = ref(true);

/**
 * 法人情報受信
 */
function recieveCorpNoInterface(sendDto: CorpNoInterface) {

    corpNo.value = sendDto.corpNo;
    corpPrefecture.value = sendDto.inputAddress.addressPostal;
    corpName.value = sendDto.corpName;

    //非表示
    isCorpSearch.value = false;
}

/**
 * 関連者検索キャンセル
 */
function recieveCancelCorpNo() {
    //非表示
    isCorpSearch.value = false;
}

// 検索リスト
const listCorp: Ref<CorpNoInterface[]> = ref([]);


function onCancel() {
    alert("キャンセル");
}
function onSave() {
    alert("保存");
}

// 企業団体検索
const corpNo: Ref<string> = ref("");
const corpPrefecture: Ref<string> = ref("");
const corpName: Ref<string> = ref("");
const isCorpSearch: Ref<boolean> = ref(false);

/**
 * 法人検索表示
 */
function onSearchCorpNo() {
    isCorpSearch.value = true;
}

/**
 *住所編集受信
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {
    inputPersonNoDto.value.inputAddress = sendDto;
}


function resetData() {
    // コードのリセット
    inputPersonNoDto.value.personNo = BLANK;
    // 名前情報のリセット
    inputPersonNoDto.value.inputName = new InputPersonNameDto();
    // 住所情報のリセット   
    inputPersonNoDto.value.inputAddress = new InputAddressDto();
    // 職業情報のリセット   
    inputPersonNoDto.value.gyoushu = BLANK;
    inputPersonNoDto.value.yakushoku = BLANK;
    inputPersonNoDto.value.shokugyouUserWrite = BLANK;

}

function recieveInputPersonNameInterface(sendDto: InputPersonNameInterface) {

    inputPersonNoDto.value.inputName = sendDto;
}

/**
 * すでに同じ法人番号で登録されているかチェック
 */
function onCheckAlreadyRegist() {
    if (inputPersonNoDto.value.personNo !== BLANK) {
        alert("現在既存または新規と確定したデータを編集中です");
    } else {
        // 仮で時効の秒数基準で既存だったり新規だったり動作を変更する
        // TOD Back側で同一判定処理ができたら連結する
        const date: Date = new Date();
        if (date.getSeconds() % 2 == 0) {
            alert("新規データでした");
            inputPersonNoDto.value.personNo = "新規";
        } else {
            alert("既存データが存在します。変更が必要な場合はデータ検索からやり直してください");
            inputPersonNoDto.value.personNo = "12-tye12er";
        }
    }
}

/**国籍を確認する */
function nationarityConfirm() {

    // チェックされた対象だけに絞る
    // const list: PersonNoInterface[] = ref([]);
    // list.push(inputPersonNoDto);

    // // API接続時には不要な回答リスト初期処理
    // listInquireAnswer.value.splice(0);

    // // 外部APIに国籍情報問い合わせ
    // // TODO (現在はRelationPersonNoを送付しているが、PersonNoDtoを送付する形に変更)
    // // 国籍問い合わせInquireNationality.vueも編集
    // const url = "http://localhost:7080/inquire-nationarity";
    // const method = "POST";
    // const body = JSON.stringify(list.value);
    // const headers = {
    //     'Accept': 'application/json',
    //     'Content-Type': 'application/json'
    // };
    // fetch(url, { method, headers, body })
    //     .then(async (response) => {
    //         listInquireAnswer.value = await response.json();
    //         listInquireAnswer.value[0];
    //     })
    //     .catch((error) => { alert(error); });

    // 国籍確認mock実装
    switch (parseInt(inputPersonNoDto.value.inputAddress.tel3) % 3) {
        case 0:
            alert("日本国籍保持");
            break;

        case 1:
            alert("外国人籍");
            break;

        case 2:
            alert("国籍不明");
            break;

        default:
            break;
    }



}
</script>
<template>

    <h3>収支報告書公開情報</h3>

    <div class="left-area">
        姓名
    </div>
    <div class="right-area">
        <input type="text" v-model="inputPersonNoDto.inputName.allName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所
    </div>
    <div class="right-area">
        <input type="text" v-model="inputPersonNoDto.inputAddress.addressPostal" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        職業
    </div>
    <div class="right-area">
        <input type="text" disabled="true" v-model="allShokugyou" class="max-input">
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>収支報告書公開入力</h3>

    <div class="left-area">
        (編集→)新規作成
    </div>
    <div class="right-area">
        <button @click="resetData">入力情報のリセット</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治資金関連者コード(個人)
    </div>
    <div class="right-area">
        <input type="text" v-model="inputPersonNoDto.personNo" disabled="true"><button class="left-space"
            @click="onCheckAlreadyRegist">重複確認</button>
    </div>
    <div class="clear-both"></div>

    <!-- 姓名入力 -->
    <ViewInputPersonName :edit-dto="inputPersonNoDto.inputName" :is-raise-edit-view="true"
        @send-input-person-name-interface="recieveInputPersonNameInterface"></ViewInputPersonName>

    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="inputPersonNoDto.inputAddress" :is-raise-edit-view="true"
        @send-input-address-interface="recieveInputAddressInterface"></ViewInputAddress>

    <div class="left-area">
        職業(1)
    </div>
    <div class="right-area">
        <span>業種(日本標準産業分類)&nbsp;<select v-model="inputPersonNoDto.gyoushu">
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
        <span class="left-space">役職&nbsp;<select v-model="inputPersonNoDto.yakushoku">
                <option :value="yakushokuFree">フリーランス(所属団体なし・法人登記なし)</option>
                <option :value="yakushokuGeneral">課長級以下職員(パート含む)</option>
                <option :value="yakushokuDirector">法人役員(一人企業の社長)</option>
                <option :value="yakushokuNoJob">定職なし</option>
            </select></span>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        職業(2)
    </div>
    <div class="right-area">
        <div v-if="yakushokuDirector !== inputPersonNoDto.yakushoku"><input type="text"
                v-model="inputPersonNoDto.shokugyouUserWrite" :disabled="isShokugyoEdit" class="max-input"></div>
        <div v-if="yakushokuDirector === inputPersonNoDto.yakushoku">
            <input type="text" v-model="corpNo" class="code-input">
            <input type="text" v-model="corpPrefecture" class="code-input left-space">
            <input type="text" v-model="corpName" class="left-space text-input">
            <button class="left-space" @click="onSearchCorpNo">企業／団体検索</button>
        </div>
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>連絡先(情報確認のため使用、非公開)</h3>
    <div class="left-area">
        メールアドレス
    </div>
    <div class="right-area">
        <input type="email">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        SNSアカウント
    </div>
    <div class="right-area">
        <input type="email">
    </div>
    <div class="clear-both"></div>

    <h3>編集内容(違反判定情報)</h3>

    <div class="left-area">
        国籍
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="isGaikokuHoujin" disabled="true">外国人である<span class="left-space"><button
                @click="nationarityConfirm">確認する</button></span>
    </div>
    <div class="clear-both"></div>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <div v-if="isCorpSearch" class="overBackground"></div>
    <div v-if="isCorpSearch">
        <div class="overComponent">
            <SearchCorpNo :list="listCorp" :is-footer="true" @send-corp-no-interface="recieveCorpNoInterface"
                @send-canceel-corp-no="recieveCancelCorpNo"></SearchCorpNo>
        </div>
    </div>

</template>



<style scoped></style>
