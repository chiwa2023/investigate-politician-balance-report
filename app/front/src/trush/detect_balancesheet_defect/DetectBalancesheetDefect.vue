<script setup lang="ts">
import { ref, Ref } from "vue";
import PublicationFormalItemItemface from "../../dto/make_balancesheet_link/publicationFormalItemDto";
import mockGetNewestPublicOfferingList from "./mock/mockGetNewestPublicOfferingList";
import SearchPoliticalOrganizationLeastCapsuleDto from "../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto";
import SessionStorageCommonCheck from "../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../dto/common_check/createCheckTransactionDto";
import PoliticalOrganizationLeastInterface from "../../dto/political_organization/politicalOrganizationLeastDto";
import SearchPoliticalOrganization from "../../components/common/search_political_organization/SearchPoliticalOrganization.vue";

/* 各提出年最新データリスト */
const listPublishing: Ref<PublicationFormalItemItemface[]> = ref([]);

/* 政治団体 */
const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);
const searchPoliticalOrganizationLeastCapsuleDto: SearchPoliticalOrganizationLeastCapsuleDto = new SearchPoliticalOrganizationLeastCapsuleDto();
searchPoliticalOrganizationLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
searchPoliticalOrganizationLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
searchPoliticalOrganizationLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない

const politicalOrgnaizationId: Ref<number> = ref(0);
const politicalOrgnaizationCode: Ref<number> = ref(0);
const politicalOrgnaizationName: Ref<string> = ref("");
/**
 * 政治団体検索コンポーネント表示
 */
function onSearchPoliticalOrgnaization() {
    isVisibleSearchPoliticalOrganizationLeast.value = true;
}
/**
 * 政治団体検索キャンセル
 */
function recieveCancelSearchPoliticalOrganizationLeast() {
    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}
/**
 * 政治団体検索選択
 * @param sendDto 選択Dto
 */
function recievePoliticalOrganizationLeastInterface(sendDto: PoliticalOrganizationLeastInterface) {

    //政治団体を設定
    politicalOrgnaizationId.value = sendDto.politicalOrganizationId;
    politicalOrgnaizationCode.value = sendDto.politicalOrganizationCode;
    politicalOrgnaizationName.value = sendDto.politicalOrganizationName;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

/* 確認期間年 */
const nowDate = new Date();
//7年前から今年まで(選挙管理員会によって異なるが、基本的に今年分は「欠損」で表示される)
const yearStart: Ref<number> = ref(nowDate.getFullYear() - 7);
const yearEnd: Ref<number> = ref(nowDate.getFullYear());

/**
 * 収支報告書検索選択
 */
function onSearch() {
    //政治団体と提出年から最新状態を検索
    listPublishing.value = mockGetNewestPublicOfferingList(yearStart.value, yearEnd.value);
}

/** 参照ページへ遷移 */
function onShowBalancesheet() {
    alert("参照ページへ遷移");
}

</script>
<template>
    <h1>収支報告書参照欠損検出</h1>

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="number" v-model="politicalOrgnaizationCode" disabled="true" class="code-input">
        <input type="text" v-model="politicalOrgnaizationName" disabled="true" class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalOrgnaization">政治団体検索</button>
    </div>
    <div class="clear-both" />

    <div class="left-area">
        確認期間
    </div>
    <div class="right-area">
        <input type="number" v-model="yearStart" class="code-input">年から
        <input type="number" v-model="yearEnd" class="code-input">年まで
        <button class="left-margin" @click="onSearch">検索</button>
    </div>
    <div class="clear-both" />

    <h3>最新一覧</h3>
    <div class="one-line">
        <table>
            <tr>
                <th>提出年</th>
                <th>団体名称</th>
                <th>提出回数</th>
                <th>システム登録</th>
                <th>公式公開</th>
                <th>公式保存期間</th>
                <th>リンク</th>
            </tr>
            <tr v-for="(publishingDto) of listPublishing" :key="publishingDto.publicationFormalItemId">
                <td>{{ publishingDto.offeringYear }}</td>
                <td>
                    <div v-show="publishingDto.isDataSet">{{ publishingDto.politicalOrgnaizationName }}</div>
                    <div v-show="!publishingDto.isDataSet">欠損</div>
                </td>
                <td>
                    <div v-show="publishingDto.isDataSet">{{ publishingDto.offeringTimes }}</div>
                </td>
                <td>
                    <div v-show="publishingDto.isImported">
                        <button @click="onShowBalancesheet">システム参照</button>
                    </div>
                    <div v-show="!publishingDto.isImported">
                        保存なし
                    </div>
                </td>
                <td>
                    <div v-show="publishingDto.isDataSet">
                        <a :href="publishingDto.publicationLinkUrl">提出先:{{ publishingDto.electionCommitionName }} </a>
                    </div>
                </td>
                <td>
                    <div v-show="publishingDto.isClosed">終了</div>
                    <div v-show="!publishingDto.isClosed">{{ publishingDto.publishingDate }}から3年間</div>
                </td>
                <td>
                    <div v-show="publishingDto.isDataSet">
                        <span v-for="(informalDto) in publishingDto.listInformalData"
                            :key="informalDto.publicationInformalId">
                            <a :href="informalDto.linkUrl">{{ informalDto.linkLabel }}</a> <br>
                        </span>
                    </div>
                </td>
            </tr>
        </table>
    </div>

    <!-- ベースを操作禁止するレイヤ -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast" class="overBackground"></div>
    <!-- 政治団体検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast">
        <div class="overComponent">
            <SearchPoliticalOrganization :search-dto="searchPoliticalOrganizationLeastCapsuleDto"
                @send-cancel-search-political-organization-least="recieveCancelSearchPoliticalOrganizationLeast"
                @send-political-organization-least-interface="recievePoliticalOrganizationLeastInterface">
            </SearchPoliticalOrganization>
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
