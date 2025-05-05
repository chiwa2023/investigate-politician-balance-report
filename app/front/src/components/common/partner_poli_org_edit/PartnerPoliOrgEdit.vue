<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
import SearchPersonNo from '../search_person_no/SearchPersonNo.vue';
import type PoliOrgNoInterface from '../../../dto/partner_poli_org/poliOrgNoDto';
import InputOrgNameInterface from '../../../dto/input_org_name/inputOrgNameDto';
import InputOrgName from '../input_org_name/InputOrgName.vue';

const props = defineProps<{ editDto: PoliOrgNoInterface }>();
const editPoliOrgDto: ComputedRef<PoliOrgNoInterface> = computed(() => { return props.editDto });

const BLANK: string = "";

// 関連者個人検索
const isPersonSearch: Ref<boolean> = ref(false);
let searchPersonType: string = "";
const delegateId: string = "delegate";
const accountMgrId: string = "accountMgr";
/**
 * 関連者個人検索を表示する
 */
function onPersonSearch(searchId: string) {

    searchPersonType = searchId;
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

    // 会計責任者から呼び出した場合は選択結果は会計責任者に設定
    if (accountMgrId === searchPersonType) {
        editPoliOrgDto.value.accountMgrName = sendDto.nameAll;
        editPoliOrgDto.value.accountMgrNo = sendDto.personNo;
    }

    // 代表者から呼び出した場合は選択結果は代表者に設定
    if (delegateId === searchPersonType) {
        editPoliOrgDto.value.delegateName = sendDto.nameAll;
        editPoliOrgDto.value.delegateNo = sendDto.personNo;
    }

    isPersonSearch.value = false;
}

// 住所・法人名とも支店フラグがOnなら編集許可Offなら検索時データを強制設定
const allAddress: ComputedRef<string> = computed(() => {
    return editPoliOrgDto.value.inputAddress.addressPostal;
});


function resetData() {
    // 関連者コード初期化
    editPoliOrgDto.value.poliOrgNo = BLANK;

    // 住所初期化    
    editPoliOrgDto.value.inputAddress = new InputAddressDto();

    // 団体名初期化
    editPoliOrgDto.value.inputName = new InputOrgNameInterface();

    // 代表者初期化    
    editPoliOrgDto.value.delegateNo = BLANK;
    editPoliOrgDto.value.delegateName = BLANK;

    // 会計責任者初期化    
    editPoliOrgDto.value.accountMgrNo= BLANK;
    editPoliOrgDto.value.accountMgrName = BLANK;
}


/**
 * すでに同じ法人番号で登録されているかチェック
 */
 function onCheckAlreadyRegist() {
    if(editPoliOrgDto.value.poliOrgNo !== BLANK){
        alert("現在既存または新規と確定したデータを編集中です");
    }else{
        // 仮で時効の秒数基準で既存だったり新規だったり動作を変更する
        // TOD Back側で同一判定処理ができたら連結する
        const date:Date = new Date();
        if(date.getSeconds() % 2 == 0){
            alert("新規データでした");
            editPoliOrgDto.value.poliOrgNo = "新規";
        }else{
            alert("既存データが存在します。変更が必要な場合はデータ検索からやり直してください");
            editPoliOrgDto.value.poliOrgNo = "1234-tyeer";
        }
    }
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
        政治団体名称
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.inputName.orgName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所
    </div>
    <div class="right-area">
        <input type="text" v-model="allAddress" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.delegateName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>連絡先</h3>

    <div class="left-area">
        (編集→)新規作成
    </div>
    <div class="right-area">
        <button @click="resetData">入力情報のリセット</button>
    </div>
    <div class="clear-both"></div>
    
    <div class="left-area">
        政治団体仮コード
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.poliOrgNo" disabled="true"><button class="left-space"
        @click="onCheckAlreadyRegist">重複確認</button>
    </div>
    <div class="clear-both"></div>

    <InputOrgName :edit-dto="editPoliOrgDto.inputName"></InputOrgName>

    <ViewInputAddress :edit-dto="editPoliOrgDto.inputAddress" :is-raise-edit-view="true"></ViewInputAddress>

    <div class="left-area">
        団体代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.delegateNo" class="code-input" disabled="true">
        <input type="text" v-model="editPoliOrgDto.delegateName" class="text-input left-space" disabled="true"><button
            class="left-space" @click="onPersonSearch(delegateId)">検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        会計責任者
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.accountMgrNo" class="code-input" disabled="true">
        <input type="text" v-model="editPoliOrgDto.accountMgrName" class="text-input left-space" disabled="true"><button
            class="left-space" @click="onPersonSearch(accountMgrId)">検索</button>
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
    <hr>
    <h3>政治団体情報</h3>
    <div class="left-area">
        団体区分
    </div>
    <div class="right-area">
        <span><input type="radio" :value="1"> 政党要件を満たす政党</span>
        <span class="left-space"><input type="radio" :value="2"> 政党の支部</span>
        <span class="left-space"><input type="radio" :value="3"> 政治資金団体</span>
        <br>
        <span><input type="radio" :value="4"> 政治資金規正法第18条の2第1項の規定による政治団体</span>
        <span class="left-space"><input type="radio" :value="5"> その他の政治団体</span>
        <span class="left-space"><input type="radio" :value="6"> その他の政治団体の支部</span>
    </div>
    <div class="clear-both"></div>
    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- 関連者個人検索 -->
    <div v-if="isPersonSearch" class="overBackground"></div>
    <div v-if="isPersonSearch">
        <div class="overComponent">
            <SearchPersonNo :is-footer="true" @send-canceel-person-no="recieveCancelPersonNo"
                @send-person-no-interface="recievePersonNoInterface"></SearchPersonNo>
        </div>
    </div>

</template>
<style scoped></style>
