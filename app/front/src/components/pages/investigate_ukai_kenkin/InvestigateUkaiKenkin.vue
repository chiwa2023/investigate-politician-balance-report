<script setup lang="ts">
import { computed, Ref, ref } from "vue";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import mockGetUkaikenkinDetail from "./mock/mockGetUkaikenkinDetail";
import getHoukokunen from "../../../dto/houkokunen/getHoukokunen";
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import PoliticalOrganizationLeastDto from "../../../dto/political_organization/politicalOrganizationLeastDto";
import SearchPoliticalOrganization from "../../common/search_political_organization/SearchPoliticalOrganization.vue";
import SearchPoliticalOrganizationLeastCapsuleDto from "../../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import UkaiKenkinConditionCapsuleInterface from "../../../dto/ukai_kenkin/ukaiKenkinConditionCapsuleDto";
import UkaiKenkinConditionCapsuleDto from "../../../dto/ukai_kenkin/ukaiKenkinConditionCapsuleDto";
import getPagingOption from "../paging/getPagingOption";
import mockGetUkaikenkinPickupRoute from "./mock/mockGetUkaikenkinPickupRoute";
import TemplateWithTaskPlanInfoResultInterface from "../../../dto/common_check/templateWithTaskPlanInfoResultDto";
import TemplateWithTaskPlanInfoResultDto from "../../../dto/common_check/templateWithTaskPlanInfoResultDto";
import UkaiKenkinSearchCapsuleInterface from "../../../dto/ukai_kenkin/ukaiKenkinSearchCapsuleDto";
import UkaiKenkinSearchCapsuleDto from "../../../dto/ukai_kenkin/ukaiKenkinSearchCapsuleDto";
import TemplateWithPagingCapsuleInterface from "../../../dto/common_check/templateWithPagingCapsuleDto";
import TemplateWithPagingCapsuleDto from "../../../dto/common_check/templateWithPagingCapsuleDto";
import UkaiKenkinPickupRouteResultInterface from "../../../dto/ukai_kenkin/ukaiKenkinPickupRouteResultDto";
import UkaiKenkinPickupRouteResultDto from "../../../dto/ukai_kenkin/ukaiKenkinPickupRouteResultDto";
import UkaiKenkinDetaillResultInterface from "../../../dto/ukai_kenkin/ukaiKenkinDetaillResultDto";
import UkaiKenkinDetaillResultDto from "../../../dto/ukai_kenkin/ukaiKenkinDetaillResultDto";
import WkTblUkaiKenkinInterface from "../../../entity/wkTblUkaiKenkinEntity";
import mockGetUkaikenkinUketorisha from "./mock/mockGetUkaikenkinUketorisha";
import YoushikiKbnIncomeConstants from "../../../dto/balancesheet/youshikiKbnIncomeConstants";
import YoushikiEdaKbnIncomeConstants from "../../../dto/balancesheet/youshikiEdaKbnIncomeConstants";
import WkTblUkaiKenkinPickupRouteInterface from "../../../entity/wkTblUkaiKenkinPickupRouteEntity";
import mockGetUkaikenkinRouteByRoute from "./mock/mockGetUkaikenkinRouteByRoute";
import mockGetUkaikenkinRoutePageList from "./mock/mockGetUkaikenkinRoutePageList";

// ラジオボタン設定
const searchCode: Ref<number> = ref(1);
const isSearchCode = computed(() => searchCode.value === 1);

// 1ページ当たりoffset
const PAGING_OFFSET: number = 100;

//政治団体検索コンポーネント
const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);
const searchPoliticalOrganizationLeastCapsuleDto: SearchPoliticalOrganizationLeastCapsuleDto = new SearchPoliticalOrganizationLeastCapsuleDto();
searchPoliticalOrganizationLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
searchPoliticalOrganizationLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
searchPoliticalOrganizationLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない
// 政治団体仮変数
const orgCode: Ref<number> = ref(0);
const orgName: Ref<string> = ref("");

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

const poliOrgLeastDto: Ref<PoliticalOrganizationLeastInterface> = ref(new PoliticalOrganizationLeastDto());
/**
 * 政治団体検索選択
 * @param sendDto 選択Dto
 */
function recievePoliticalOrganizationLeastInterface(sendDto: PoliticalOrganizationLeastInterface) {

    poliOrgLeastDto.value = sendDto;

    //政治団体を設定
    capsuleCreateDto.value.poliOrgCode = sendDto.politicalOrganizationCode;
    orgCode.value = sendDto.politicalOrganizationCode;
    orgName.value = sendDto.politicalOrganizationName;

    orgCode.value = 100;
    orgName.value = "ABCD団体";

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}


// 抽出結果の一覧とページング
const listPickupRoutePage: Ref<SelectOptionInterface[]> = ref([]);
const pickupRouteResultDto: Ref<UkaiKenkinPickupRouteResultInterface> = ref(new UkaiKenkinPickupRouteResultDto());

// 完全検索リストの明細一覧とページング
//const listMeisaiAll: Ref<WkTblUkaiKenkinInterface[]> = ref([]);
const detailResulDto: Ref<UkaiKenkinDetaillResultInterface> = ref(new UkaiKenkinDetaillResultDto());
const listDetailPage: Ref<SelectOptionInterface[]> = ref([]);

// 受取者関係団体一覧
const listUketorisha: Ref<WkTblUkaiKenkinInterface[]> = ref([]);

// ワークテーブル作成条件
const capsuleCreateDto: Ref<UkaiKenkinConditionCapsuleInterface> = ref(new UkaiKenkinConditionCapsuleDto());
// 報告年リスト
const houkokunenList: Ref<SelectOptionInterface[]> = ref(getHoukokunen());
capsuleCreateDto.value.houkokuNen = 2022;

// 作成条件を送った結果Dto    
const createReultDto: TemplateWithTaskPlanInfoResultInterface = new TemplateWithTaskPlanInfoResultDto();

function onCreateWkTbl() {

    capsuleCreateDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleCreateDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    capsuleCreateDto.value.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない
    capsuleCreateDto.value.isNameSearch = isSearchCode.value;
    capsuleCreateDto.value.offset = PAGING_OFFSET;

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(該当データ)
    // const urlSouryou:string = "http://localhost:9080/"

    // 作成処理依頼後の非同期作業中メッセージ
    createReultDto.taskPlanCode = 375;
    createReultDto.message = "データ作成を実行しています…";
    alert(createReultDto.message);

    // TODO 仮で次回抽出結果を要求したときにOKとする
}

// ワークテーブル検索条件
const capsuleSearchDto: Ref<UkaiKenkinSearchCapsuleInterface> = ref(new UkaiKenkinSearchCapsuleDto());

// 結果ページング
const pageResultDto: Ref<TemplateWithPagingCapsuleInterface> = ref(new TemplateWithPagingCapsuleDto());
// 詳細ページング
const pageDetailDto: Ref<TemplateWithPagingCapsuleInterface> = ref(new TemplateWithPagingCapsuleDto());

// 経路リスト(経路単位)
const listRoute: Ref<WkTblUkaiKenkinPickupRouteInterface[]> = ref([]);

// 経路単位選択肢
const listRouteOptiuon: Ref<SelectOptionInterface[]> = ref([]);

function onSearch() {

    capsuleSearchDto.value.createCapsuleDto = capsuleCreateDto.value;
    capsuleSearchDto.value.createCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleSearchDto.value.createCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    capsuleSearchDto.value.createCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない
    // 作成確認のために作成時に取得したタスク同一識別コードも送信
    capsuleSearchDto.value.taskPlanCode = createReultDto.taskPlanCode;
    capsuleSearchDto.value.year = createReultDto.year;

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(該当データ)
    // const urlSouryou:string = "http://localhost:9080/"
    pickupRouteResultDto.value = mockGetUkaikenkinPickupRoute(0);
    listPickupRoutePage.value = getPagingOption(pickupRouteResultDto.value.countAll, pickupRouteResultDto.value.limit);

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(全抽出検索)
    // const urlSouryou:string = "http://localhost:9080/"
    detailResulDto.value = mockGetUkaikenkinDetail(0);
    listDetailPage.value = getPagingOption(detailResulDto.value.countAll, detailResulDto.value.limit);

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(受取者関係者全団体)
    // const urlSouryou:string = "http://localhost:9080/"
    listUketorisha.value = mockGetUkaikenkinUketorisha();

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(経路選択肢)
    // const urlSouryou:string = "http://localhost:9080/"
    listRouteOptiuon.value = mockGetUkaikenkinRoutePageList();

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(経路ごと)
    // const urlSouryou:string = "http://localhost:9080/"
    listRoute.value = mockGetUkaikenkinRouteByRoute(0);
}


function onCancel() {
    alert("キャンセル");
}

function onPreResearch() {
    alert("前回データ保存");
}


pageResultDto.value.limit = 1; // 1団体ずつ表示
function onPagingResult() {
    pageResultDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    pageResultDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    pageResultDto.value.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない
    pageResultDto.value.countAll;
    pageResultDto.value.offset;
    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(全抽出検索)
    // const urlSouryou:string = "http://localhost:9080/"
    pickupRouteResultDto.value = mockGetUkaikenkinPickupRoute(pickupRouteResultDto.value.offset);
    listPickupRoutePage.value = getPagingOption(pickupRouteResultDto.value.countAll, pickupRouteResultDto.value.limit);
}

// 表示経路選択
const slectedRoute: Ref<string> = ref("0");
function onPagingRoute() {
    listRoute.value = mockGetUkaikenkinRouteByRoute(parseInt(slectedRoute.value));
}

function onPagingDetail() {

    pageDetailDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    pageDetailDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    pageDetailDto.value.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない
    pageDetailDto.value.countAll;
    pageDetailDto.value.offset;

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(全抽出検索)
    // const urlSouryou:string = "http://localhost:9080/"
    detailResulDto.value = mockGetUkaikenkinDetail(detailResulDto.value.offset);
    listDetailPage.value = getPagingOption(detailResulDto.value.countAll, detailResulDto.value.limit);
}

function convertYoushikiKbnText(youshikiKbn: number): string {
    return YoushikiKbnIncomeConstants.convertText(youshikiKbn);
}
function convertYoushikiEdaKbnText(youshikiEdaKbn: number): string {
    return YoushikiEdaKbnIncomeConstants.convertText(youshikiEdaKbn);
}

</script>
<template>
    <h1>迂回献金キャッチャー</h1>

    <div class="one-line">
        抽出条件<br>
        ※前回調査分が残っている場合は削除されます<button @click="onPreResearch">保存</button><br>
        ※違法でない単純な資金異動も抽出されます。単純な0階層迂回献金でも必ず追加調査を行ってください
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        報告年
    </div>
    <div class="right-area">
        <select v-model="capsuleCreateDto.houkokuNen">
            <option v-for="option of houkokunenList" :key="option.value">{{ option.text }}</option>
        </select>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治団体
    </div>
    <div class="right-area">
        <input type="number" v-model="orgCode" disabled="true" class="code-input">
        <input type="text" v-model="orgName" disabled="true" class="left-space text-input">
        <button class="left-space" @click="onSearchPoliticalOrgnaization">政治団体検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索基準
    </div>
    <div class="right-area">
        <input type="radio" v-model="searchCode" :value="1">コード基準<span class="left-space"><input type="radio"
                v-model="searchCode" :value="2">原文書団体名基準</span>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        想定階層
    </div>
    <div class="right-area">
        <select v-model="capsuleCreateDto.pickupTimes" class="code-input">
            <option value="1">1階層</option>
            <option value="2">2階層</option>
            <option value="3">3階層</option>
            <option value="4">4階層</option>
            <option value="5">5階層</option>
            <option value="6">6階層</option>
            <option value="7">7階層</option>
            <option value="8">8階層</option>
            <option value="1">9階層</option>
        </select>
        まで迂回していると想定
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        交付金検索
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="capsuleCreateDto.isKoufukin">交付金も迂回ルートに含める<br>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        データ抽出
    </div>
    <div class="right-area">
        <button @click="onCreateWkTbl">データ抽出</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        抽出結果
    </div>
    <div class="right-area">
        <button @click="onSearch">表示する</button>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        <h3>抽出ルート(ページ)</h3>
        <div style="overflow-x: scroll">
            <!-- ページング -->
            <select v-model="pickupRouteResultDto.offset" @change="onPagingResult">
                <option v-for="option in listPickupRoutePage" :key="option.value" :value="option.value"> {{ option.text
                    }}
                </option>
            </select>
            <table>
                <tr>
                    <th>(記載)役割</th>
                    <th>(記載)対象者</th>
                    <th>(記載)政治団体</th>
                    <th>経路</th>
                    <th>発生日</th>
                    <th>様式区分</th>
                    <th>項目</th>
                    <th>金額</th>
                    <th>抽出回(番目)</th>
                    <th>連番(記載行)</th>
                    <th>(取引)取引相手</th>
                    <th>(取引)対象者</th>
                    <th>(役割)役割</th>
                </tr>

                <tr v-for="entity of pickupRouteResultDto.listFirstRouteEntity"
                    :key="entity.wkTblUkaiKenkinPickupRouteId">
                    <td>
                        <!-- 役割 -->
                        {{ entity.poliOrgRelationPersonYakuari }}
                    </td>
                    <td>
                        <!-- 関連者 -->
                        <div v-if="entity.poliOrgRelationPersonId !== 0">
                            <span v-if="entity.poliOrgRelationPersonId !== -1">({{ entity.poliOrgRelationPersonCode
                            }})<br></span>
                            {{ entity.poliOrgRelationPersonName }}
                        </div>
                    </td>
                    <td>
                        <!-- 政治団体 -->
                        <span v-if="entity.politicalOrgId !== -1">({{ entity.politicalOrgCode }})<br></span>
                        {{ entity.politicalOrgName }}
                    </td>
                    <td>
                        <!-- 経路 -->
                        {{ entity.wkTblUkaiKenkinPickupRouteCode }}
                    </td>
                    <td>
                        <!-- 発生 -->
                        {{ entity.accrualDate }}
                    </td>
                    <td>
                        <!-- 様式区分 -->
                        {{ convertYoushikiKbnText(entity.youshikiKbn) }}-{{
                            convertYoushikiEdaKbnText(entity.youshikiEdaKbn) }}
                    </td>

                    <td>
                        <!-- 項目 -->
                        {{ entity.itemName }}
                    </td>
                    <td>
                        <!-- 金額 -->
                        {{ entity.kingaku }}
                    </td>
                    <td>
                        <!-- 抽出階層 -->
                        {{ entity.pickupStage }}
                    </td>
                    <td>
                        <!-- 行連番 -->
                        {{ entity.renban }}({{ entity.tablleId }})
                    </td>
                    <td>
                        <!-- 取引相手 -->
                        <span v-if="entity.tradingPartnerId !== -1">({{ entity.tradingPartnerCode }}) {{
                            entity.tradingPartnerName }}<br></span>
                        {{ entity.tradingPartnerAddress }}
                    </td>
                    <td>
                        <!-- 取引相手関連者 -->
                        <div v-if="entity.tradingRelationPersonId !== -0">
                            <span v-if="entity.tradingRelationPersonId !== -1">({{ entity.tradingRelationPersonCode
                            }})<br></span>
                            {{ entity.tradingRelationPersonName }}
                        </div>
                    </td>
                    <td>
                        <!-- 取引相手役割 -->
                        {{ entity.tradingRelationPersonYakuari }}
                    </td>
                </tr>

                <tr v-for="entity of pickupRouteResultDto.listPickupRouteEntity"
                    :key="entity.wkTblUkaiKenkinPickupRouteId">
                    <td>
                        <!-- 役割 -->
                        {{ entity.poliOrgRelationPersonYakuari }}
                    </td>
                    <td>
                        <!-- 関連者 -->
                        <div v-if="entity.poliOrgRelationPersonId !== 0">
                            <span v-if="entity.poliOrgRelationPersonId !== -1">({{ entity.poliOrgRelationPersonCode
                            }})<br></span>
                            {{ entity.poliOrgRelationPersonName }}
                        </div>
                    </td>
                    <td>
                        <!-- 政治団体 -->
                        <span v-if="entity.politicalOrgId !== -1">({{ entity.politicalOrgCode }})<br></span>
                        {{ entity.politicalOrgName }}
                    </td>
                    <td>
                        <!-- 経路 -->
                        {{ entity.wkTblUkaiKenkinPickupRouteCode }}
                    </td>
                    <td>
                        <!-- 発生 -->
                        {{ entity.accrualDate }}
                    </td>
                    <td>
                        <!-- 様式区分 -->
                        {{ convertYoushikiKbnText(entity.youshikiKbn) }}-{{
                            convertYoushikiEdaKbnText(entity.youshikiEdaKbn) }}
                    </td>

                    <td>
                        <!-- 項目 -->
                        {{ entity.itemName }}

                    </td>
                    <td>
                        <!-- 金額 -->
                        {{ entity.kingaku }}
                    </td>
                    <td>
                        <!-- 抽出階層 -->
                        {{ entity.pickupStage }}
                    </td>
                    <td>
                        <!-- 行連番 -->
                        {{ entity.renban }}({{ entity.tablleId }})
                    </td>
                    <td>
                        <!-- 取引相手 -->
                        <span v-if="entity.tradingPartnerId !== -1">({{ entity.tradingPartnerCode }}) {{
                            entity.tradingPartnerName }}<br></span>
                        {{ entity.tradingPartnerAddress }}
                    </td>
                    <td>
                        <!-- 取引相手関連者 -->
                        <div v-if="entity.tradingRelationPersonId !== -0">
                            <span v-if="entity.tradingRelationPersonId !== -1">({{ entity.tradingRelationPersonCode
                            }})<br></span>
                            {{ entity.tradingRelationPersonName }}
                        </div>
                    </td>
                    <td>
                        <!-- 取引相手役割 -->
                        {{ entity.tradingRelationPersonYakuari }}
                    </td>
                </tr>


                <tr v-for="entity of pickupRouteResultDto.listLastRouteEntity"
                    :key="entity.wkTblUkaiKenkinPickupRouteId">
                    <td>
                        <!-- 役割 -->
                        {{ entity.poliOrgRelationPersonYakuari }}
                    </td>
                    <td>
                        <!-- 関連者 -->
                        <div v-if="entity.poliOrgRelationPersonId !== 0">
                            <span v-if="entity.poliOrgRelationPersonId !== -1">({{ entity.poliOrgRelationPersonCode
                            }})<br></span>
                            {{ entity.poliOrgRelationPersonName }}
                        </div>
                    </td>
                    <td>
                        <!-- 政治団体 -->
                        <span v-if="entity.politicalOrgId !== -1">({{ entity.politicalOrgCode }})<br></span>
                        {{ entity.politicalOrgName }}
                    </td>
                    <td>
                        <!-- 経路 -->
                        {{ entity.wkTblUkaiKenkinPickupRouteCode }}
                    </td>
                    <td>
                        <!-- 発生 -->
                        {{ entity.accrualDate }}
                    </td>
                    <td>
                        <!-- 様式区分 -->
                        {{ convertYoushikiKbnText(entity.youshikiKbn) }}-{{
                            convertYoushikiEdaKbnText(entity.youshikiEdaKbn) }}
                    </td>
                    <td>
                        <!-- 項目 -->
                        {{ entity.itemName }}

                    </td>
                    <td>
                        <!-- 金額 -->
                        {{ entity.kingaku }}
                    </td>
                    <td>
                        <!-- 抽出階層 -->
                        {{ entity.pickupStage }}
                    </td>
                    <td>
                        <!-- 行連番 -->
                        {{ entity.renban }}({{ entity.tablleId }})
                    </td>
                    <td>
                        <!-- 取引相手 -->
                        <span v-if="entity.tradingPartnerId !== -1">({{ entity.tradingPartnerCode }}) {{
                            entity.tradingPartnerName }}<br></span>
                        {{ entity.tradingPartnerAddress }}
                    </td>
                    <td>
                        <!-- 取引相手関連者 -->
                        <div v-if="entity.tradingRelationPersonId !== -0">
                            <span v-if="entity.tradingRelationPersonId !== -1">({{ entity.tradingRelationPersonCode
                            }})<br></span>
                            {{ entity.tradingRelationPersonName }}
                        </div>
                    </td>
                    <td>
                        <!-- 取引相手役割 -->
                        {{ entity.tradingRelationPersonYakuari }}
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="clear-both"><br></div>


    <div class="one-line">
        <h3>抽出ルート(経路)</h3>
        <div style="overflow-x: scroll">
            <!-- ページング -->
            <select v-model="slectedRoute" @change="onPagingRoute">
                <option v-for="option in listRouteOptiuon" :key="option.value" :value="option.value"> {{ option.text
                    }}
                </option>
            </select>
            <table>
                <tr>
                    <th>(記載)役割</th>
                    <th>(記載)対象者</th>
                    <th>(記載)政治団体</th>
                    <th>経路</th>
                    <th>発生日</th>
                    <th>様式区分</th>
                    <th>項目</th>
                    <th>金額</th>
                    <th>抽出回(番目)</th>
                    <th>連番(記載行)</th>
                    <th>(取引)取引相手</th>
                    <th>(取引)対象者</th>
                    <th>(役割)役割</th>
                </tr>

                <tr v-for="entity of listRoute" :key="entity.wkTblUkaiKenkinPickupRouteId">
                    <td>
                        <!-- 役割 -->
                        {{ entity.poliOrgRelationPersonYakuari }}
                    </td>
                    <td>
                        <!-- 関連者 -->
                        <div v-if="entity.poliOrgRelationPersonId !== 0">
                            <span v-if="entity.poliOrgRelationPersonId !== -1">({{ entity.poliOrgRelationPersonCode
                            }})<br></span>
                            {{ entity.poliOrgRelationPersonName }}
                        </div>
                    </td>
                    <td>
                        <!-- 政治団体 -->
                        <span v-if="entity.politicalOrgId !== -1">({{ entity.politicalOrgCode }})<br></span>
                        {{ entity.politicalOrgName }}
                    </td>
                    <td>
                        <!-- 経路 -->
                        {{ entity.wkTblUkaiKenkinPickupRouteCode }}
                    </td>
                    <td>
                        <!-- 発生 -->
                        {{ entity.accrualDate }}
                    </td>
                    <td>
                        <!-- 様式区分 -->
                        {{ convertYoushikiKbnText(entity.youshikiKbn) }}-{{
                            convertYoushikiEdaKbnText(entity.youshikiEdaKbn) }}
                    </td>

                    <td>
                        <!-- 項目 -->
                        {{ entity.itemName }}
                    </td>
                    <td>
                        <!-- 金額 -->
                        {{ entity.kingaku }}
                    </td>
                    <td>
                        <!-- 抽出階層 -->
                        {{ entity.pickupStage }}
                    </td>
                    <td>
                        <!-- 行連番 -->
                        {{ entity.renban }}({{ entity.tablleId }})
                    </td>
                    <td>
                        <!-- 取引相手 -->
                        <span v-if="entity.tradingPartnerId !== -1">({{ entity.tradingPartnerCode }}) {{
                            entity.tradingPartnerName }}<br></span>
                        {{ entity.tradingPartnerAddress }}
                    </td>
                    <td>
                        <!-- 取引相手関連者 -->
                        <div v-if="entity.tradingRelationPersonId !== -0">
                            <span v-if="entity.tradingRelationPersonId !== -1">({{ entity.tradingRelationPersonCode
                            }})<br></span>
                            {{ entity.tradingRelationPersonName }}
                        </div>
                    </td>
                    <td>
                        <!-- 取引相手役割 -->
                        {{ entity.tradingRelationPersonYakuari }}
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        <h3>キャッチのために抽出したすべてのデータの一覧</h3>
        <!-- ページング -->
        <select v-model="detailResulDto.offset" @change="onPagingDetail">
            <option v-for="option in listDetailPage" :key="option.value" :value="option.value"> {{ option.text }}
            </option>
        </select>
        <div style="overflow-x: scroll">
            <table>
                <tr>
                    <th>(記載)代表者</th>
                    <th>(記載)会計責任者</th>
                    <th>(記載)資金団体届出者</th>
                    <th>(記載)関連国会議員</th>
                    <th>(記載)政治団体</th>
                    <th>発生日</th>
                    <th>様式区分</th>
                    <th>項目</th>
                    <th>金額</th>
                    <th>抽出回(番目)</th>
                    <th>(取引)取引相手</th>
                    <th>(取引)団体代表者</th>
                    <th>(取引)会計責任者</th>
                    <th>(取引)資金団体届出者</th>
                    <th>(取引)関連国会議員</th>
                </tr>
                <tr v-for="entity of detailResulDto.listDetailEntity" :key="entity.wkTblUkaiKenkinEntityId">
                    <td>
                        <!-- 寄付される側代表者 -->
                        <div v-if="entity.poliOrgDelegateId !== 0">
                            <span v-if="entity.poliOrgDelegateId !== -1">({{ entity.poliOrgDelegateCode }})<br></span>
                            {{ entity.poliOrgDelegateName }}
                        </div>
                    </td>
                    <td>
                        <!-- 寄付される側会計責任者 -->
                        <div v-if="entity.poliOrgAccountManagerId !== 0">
                            <span v-if="entity.poliOrgAccountManagerId !== -1">({{ entity.poliOrgAccountManagerCode
                            }})<br></span>
                            {{ entity.poliOrgAccountManagerName }}
                        </div>
                    </td>
                    <td>
                        <!-- 寄付される側団体資金管理団体責任者 -->
                        <div v-if="entity.poliOrgShikinDantaiId !== 0">
                            <span v-if="entity.poliOrgShikinDantaiId !== -1">({{ entity.poliOrgShikinDantaiCode
                            }})<br></span>
                            {{ entity.poliOrgShikinDantaiName }}
                        </div>
                    </td>
                    <td>
                        <!-- 寄付される側国会議員関連団体国会議員 -->
                        <div v-if="entity.poliOrgKokkaiGiin1Id !== 0">
                            <span v-if="entity.poliOrgKokkaiGiin1Id !== -1">({{ entity.poliOrgKokkaiGiin1Code
                            }})<br></span>
                            {{ entity.poliOrgKokkaiGiin1Name }}
                        </div>

                        <div v-if="entity.poliOrgKokkaiGiin2Id !== 0">
                            <span v-if="entity.poliOrgKokkaiGiin2Id !== -1">({{ entity.poliOrgKokkaiGiin2Code
                            }})<br></span>
                            {{ entity.poliOrgKokkaiGiin2Name }}
                        </div>

                        <div v-if="entity.poliOrgKokkaiGiin3Id !== 0">
                            <span v-if="entity.poliOrgKokkaiGiin3Id !== -1">({{ entity.poliOrgKokkaiGiin3Code
                            }})<br></span>
                            {{ entity.poliOrgKokkaiGiin3Name }}
                        </div>
                    </td>
                    <td>
                        <!-- 政治団体 -->
                        ({{ entity.politicalOrgCode }})<br>
                        {{ entity.politicalOrgName }}
                    </td>
                    <td>
                        <!-- 発生 -->
                        {{ entity.accrualDate }}
                    </td>
                    <td>
                        <!-- 様式区分 -->
                        {{ convertYoushikiKbnText(entity.youshikiKbn) }}-{{
                            convertYoushikiEdaKbnText(entity.youshikiEdaKbn) }}
                    </td>
                    <td>
                        <!-- 項目 -->
                        {{ entity.itemName }}

                    </td>
                    <td>
                        <!-- 金額 -->
                        {{ entity.kingaku }}
                    </td>
                    <td>
                        <!-- 抽出階層 -->
                        {{ entity.pickupStage }}
                    </td>
                    <td>
                        <!-- 取引相手 -->
                        <span v-if="entity.tradingPartnerId !== -1">({{ entity.tradingPartnerCode }}) {{
                            entity.tradingPartnerName }}<br></span>
                        {{ entity.tradingPartnerAddress }}

                    </td>
                    <td>
                        <!-- 取引相手代表者名 -->
                        <div v-if="entity.tradingPartnerDelegateId !== 0">
                            <span v-if="entity.tradingPartnerDelegateId !== -1">({{ entity.tradingPartnerDelegateCode
                            }})<br></span>
                            {{ entity.tradingPartnerDelegateName }}
                        </div>
                    </td>
                    <td>
                        <!-- 寄付される側会計責任者 -->
                        <div v-if="entity.tradingOrgAccountManagerId !== 0">
                            <span v-if="entity.tradingOrgAccountManagerId !== -1">({{
                                entity.tradingOrgAccountManagerCode }})<br></span>
                            {{ entity.tradingOrgAccountManagerName }}
                        </div>
                    </td>
                    <td>
                        <!-- 寄付される側団体資金管理団体責任者 -->
                        <div v-if="entity.tradingOrgShikinDantaiId !== 0">
                            <span v-if="entity.tradingOrgShikinDantaiId !== -1">({{ entity.tradingOrgShikinDantaiCode
                            }})<br></span>
                            {{ entity.tradingOrgShikinDantaiName }}
                        </div>
                    </td>

                    <td>
                        <!-- 寄付される側国会議員関連団体国会議員 -->
                        <div v-if="entity.tradingOrgKokkaiGiin1Id !== 0">
                            <span v-if="entity.tradingOrgKokkaiGiin1Id !== -1">({{ entity.tradingOrgKokkaiGiin1Code
                            }})<br></span>
                            {{ entity.tradingOrgKokkaiGiin1Name }}
                        </div>

                        <div v-if="entity.tradingOrgKokkaiGiin2Id !== 0">
                            <span v-if="entity.tradingOrgKokkaiGiin2Id !== -1">({{ entity.tradingOrgKokkaiGiin2Code
                            }})<br></span>
                            {{ entity.tradingOrgKokkaiGiin2Name }}
                        </div>

                        <div v-if="entity.tradingOrgKokkaiGiin3Id !== 0">
                            <span v-if="entity.tradingOrgKokkaiGiin3Id !== -1">({{ entity.tradingOrgKokkaiGiin3Code
                            }})<br></span>
                            {{ entity.tradingOrgKokkaiGiin3Name }}
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        <h3>(参考)寄付受取者所属団体一覧</h3>
        <div style="overflow-y: scroll">
            <table>
                <tr>
                    <th>(記載)代表者</th>
                    <th>(記載)会計責任者</th>
                    <th>(記載)資金団体届出者</th>
                    <th>(記載)関連国会議員</th>
                    <th>(記載)政治団体</th>
                </tr>
                <tr v-for="entity of listUketorisha" :key="entity.wkTblUkaiKenkinEntityId">
                    <td>
                        <!-- 寄付される側代表者 -->
                        ({{ entity.poliOrgDelegateCode }})<br>
                        {{ entity.poliOrgDelegateName }}
                    </td>
                    <td>
                        <!-- 寄付される側会計責任者 -->
                        ({{ entity.poliOrgAccountManagerCode }})<br>
                        {{ entity.poliOrgAccountManagerName }}
                    </td>
                    <td>
                        <!-- 寄付される側団体資金管理団体責任者 -->
                        <div v-if="entity.poliOrgShikinDantaiCode !== 0">
                            ({{ entity.poliOrgShikinDantaiCode }})<br>
                            {{ entity.poliOrgShikinDantaiName }}
                        </div>
                    </td>
                    <td>
                        <!-- 寄付される側国会議員関連団体国会議員 -->
                        <div v-if="entity.poliOrgKokkaiGiin1Id !== 0">
                            ({{ entity.poliOrgKokkaiGiin1Code }})<br>
                            {{ entity.poliOrgKokkaiGiin1Name }}
                        </div>

                        <div v-if="entity.poliOrgKokkaiGiin2Id !== 0">
                            ({{ entity.poliOrgKokkaiGiin2Code }})<br>
                            {{ entity.poliOrgKokkaiGiin2Name }}
                        </div>

                        <div v-if="entity.poliOrgKokkaiGiin3Id !== 0">
                            ({{ entity.poliOrgKokkaiGiin3Code }})<br>
                            {{ entity.poliOrgKokkaiGiin3Name }}
                        </div>
                    </td>
                    <td>
                        <!-- 政治団体 -->
                        ({{ entity.politicalOrgCode }})<br>
                        {{ entity.politicalOrgName }}
                    </td>
                </tr>
            </table>
        </div>

        <div class="footer">
            <button @click="onCancel" class="footer-button">キャンセル</button>
        </div>

        <!-- 政治団体検索コンポーネント -->
        <div v-if="isVisibleSearchPoliticalOrganizationLeast" class="overBackground"></div>
        <div v-if="isVisibleSearchPoliticalOrganizationLeast">
            <div class="overComponent">
                <SearchPoliticalOrganization :search-dto="searchPoliticalOrganizationLeastCapsuleDto"
                    @send-cancel-search-political-organization-least="recieveCancelSearchPoliticalOrganizationLeast"
                    @send-political-organization-least-interface="recievePoliticalOrganizationLeastInterface">
                </SearchPoliticalOrganization>
            </div>
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

td.alert {
    background-color: red;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
