<script setup lang="ts">
import { computed, ref, toRaw, type ComputedRef, type Ref } from 'vue';
import SearchHoujinNo from '../search_houjin_no/SearchHoujinNo.vue';
import type HoujinNoInterface from '../../../dto/partner_corp/houjinNoDto';
import type CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import CheckRegistCorpResultInterface from '../../../dto/partner_corp/checkRegistCorpResultDto';
import mockCheckAlreadyRegist from './mock/mockCheckAlreadyRegist';
import HoujinSbtsConstants from '../../../dto/partner_corp/houjinSbtsConstants';
import SearchPersonNo from '../search_person_no/SearchPersonNo.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';

const props = defineProps<{ editDto: CorpNoInterface }>();
const editCorpDto: ComputedRef<CorpNoInterface> = computed(() => { return props.editDto });

// 編集用Dto
const addressDtoStored: Ref<InputAddressDto> = ref(new InputAddressDto());

const allCorpName: ComputedRef<string> = computed(() => {
    if (editCorpDto.value.isShiten) { return editCorpDto.value.corpName + "　" + branchName.value; }
     else { return editCorpDto.value.corpName; }
});

// 検索リスト
const listCorp: Ref<CorpNoInterface[]> = ref([]);

// 法人検索
const isCorpSearch: Ref<boolean> = ref(false);
const corpName: Ref<string> = ref("");
const corpNameKana: Ref<string> = ref("");
const branchName: Ref<string> = ref("");
const branchNamekana: Ref<string> = ref("");
const isGaikokuHoujin: ComputedRef<boolean> = computed(() => HoujinSbtsConstants.GAIKOKU === editCorpDto.value.houjinSbts);

function onHoujinSearch() {
    isCorpSearch.value = true;
}

/**
 * 法人番号表示
 */
function recieveCorpNoInterface(sendDto: HoujinNoInterface) {

    // 法人番号から取得情報の設定
    editCorpDto.value.houjinNo = sendDto.houjinNo;
    editCorpDto.value.corpName = sendDto.houjinName;
    editCorpDto.value.houjinSbts = sendDto.houjinSbts;
    corpName.value = sendDto.houjinName;
    corpNameKana.value = sendDto.houjinNameKana;
    const postalCode: string = sendDto.postalcode;
    // 郵便番号が正常7桁の場合は分割
    if (7 === postalCode.length) {
        editCorpDto.value.inputAddress.postalcode1 = postalCode.substring(0, 3);
        editCorpDto.value.inputAddress.postalcode2 = postalCode.substring(3, 7);
    } else {
        editCorpDto.value.inputAddress.postalcode1 = postalCode;
    }
    editCorpDto.value.inputAddress.addressPostal = sendDto.addressPrefecture + sendDto.addressCity;
    editCorpDto.value.inputAddress.addressBlock = sendDto.addressBlock;
    editCorpDto.value.inputAddress.addressBuilding = "";
    // 支店フラグ悪用防止用に検索時情報をストア
    addressDtoStored.value = structuredClone(toRaw(editCorpDto.value.inputAddress));

    // 法人番号DBに代表者情報はないので初期化
    editCorpDto.value.orgDelegateCode = "";
    editCorpDto.value.orgDelegate = "";

    // コード確認を別ボタンでする
    editCorpDto.value.corpNo = "";

    //非表示
    isCorpSearch.value = false;
}

/**
* 法人番号キャンセル
*/
function recieveCancelCorpNo() {
    //非表示
    isCorpSearch.value = false;
}

/**
 * すでに同じ法人番号で登録されているかチェック
 */
function onCheckAlreadyRegist() {
    const resultDto: CheckRegistCorpResultInterface = mockCheckAlreadyRegist(editCorpDto.value.corpNo, editCorpDto.value.houjinNo);
    editCorpDto.value.corpNo = resultDto.savedCorpNo;
    alert(resultDto.message);
    listCorp.value.splice(0);
    for (const dto of resultDto.listCorptDto) {
        listCorp.value.push(dto);
    }
}

const isPersonSearch: Ref<boolean> = ref(false);

/**
 * 関連者個人検索を表示する
 */
function onPersonSearch() {
    isPersonSearch.value = true;
}

/**
 * 検索キャンセルを受信する
 */
function recieveCancelPersonNo() {

    isPersonSearch.value = false;
}

/**
 * 選択された関連者個人を受信を表示する
 */
function recievePersonNoInterface(sendDto: PersonNoInterface) {
    editCorpDto.value.orgDelegate = sendDto.nameAll;
    editCorpDto.value.orgDelegateCode = sendDto.personNo;

    isPersonSearch.value = false;
}


function onCancel() {
    alert("キャンセル");
}
function onSave() {
    alert("保存");
}
</script>
<template>
    <h3>収支報告書公開情報</h3>
    <div class="left-area">
        企業／団体名称
    </div>
    <div class="right-area">
        <input type="text" v-model="allCorpName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.inputAddress.addressPostal" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.orgDelegate" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>編集内容(連絡先)</h3>

    <div class="left-area">
        政治資金関連者コード(企業団体)
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.corpNo" disabled="true"><button class="left-space"
            @click="onCheckAlreadyRegist">重複確認</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        法人番号
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.houjinNo" class="text-input" disabled="true"><button class="left-space"
            @click="onHoujinSearch">検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        支店
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="editCorpDto.isShiten">支店(法人番号を変えずに名称追加、住所変更)
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        商号名称カナ
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.corpNameKana" class="text-input" disabled="true">
        <span class="left-space" v-if="editCorpDto.isShiten">支店：<input type="text" v-model="branchNamekana"
                class="text-input" :disabled="!editCorpDto.isShiten"></span>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        商号名称
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.corpName" class="text-input" disabled="true">
        <span class="left-space" v-if="editCorpDto.isShiten">支店：<input type="text" v-model="branchName"
                class="text-input" :disabled="!editCorpDto.isShiten"></span>
    </div>
    <div class="clear-both"></div>
    <br>
    <ViewInputAddress :edit-dto="editCorpDto.inputAddress" :is-raise-edit-view="editCorpDto.isShiten">
    </ViewInputAddress>
    <br>
    <div class="left-area">
        団体代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.orgDelegateCode" class="code-input" disabled="true">
        <input type="text" v-model="editCorpDto.orgDelegate" class="text-input left-space" disabled="true"><button
            class="left-space" @click="onPersonSearch">検索</button>
    </div>
    <div class="clear-both"></div>
    <hr>

    <h3>編集内容(違反判定情報)</h3>

    <div class="left-area">
        外国籍企業
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="isGaikokuHoujin" disabled="true">外国籍企業である
    </div>
    <div class="clear-both"></div>



    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <hr>

    <div v-if="isCorpSearch" class="overBackground"></div>
    <div v-if="isCorpSearch">
        <div class="overComponent">
            <SearchHoujinNo v-if="isCorpSearch" @send-cancel-houjin-no="recieveCancelCorpNo"
                @send-houjin-no-interface="recieveCorpNoInterface"></SearchHoujinNo>
        </div>
    </div>

    <div v-if="isPersonSearch" class="overBackground"></div>
    <div v-if="isPersonSearch">
        <div class="overComponent">
            <SearchPersonNo :is-footer="true" @send-canceel-person-no="recieveCancelPersonNo"
                @send-person-no-interface="recievePersonNoInterface"></SearchPersonNo>
        </div>
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
</style>
