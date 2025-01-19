<script setup lang="ts">
import { computed, Ref, ref, toRaw } from "vue";
import SearchPoliticalOrganization from '../../common/search_political_organization/SearchPoliticalOrganization.vue';
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import PoliticalOrganizationLeastDto from "../../../dto/political_organization/politicalOrganizationLeastDto";
import SearchPoliticalOrganizationLeastCapsuleDto from "../../../dto/political_organization/searchPoliticalOrganizationLeastCapsuleDto";
import SessionStorageCommonCheck from "../../../dto/common_check/sessionStorageCommonCheck";
import createCheckTransactionDto from "../../../dto/common_check/createCheckTransactionDto";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import getHoukokunen from "../../../dto/houkokunen/getHoukokunen";
import mockGetKobetsuResultAll from "./mock/mockGetKobetsuResultAll";
import mockGetSouryouJougen from "./mock/mockGetSouryouJougen";
import getPagingOption from "../paging/getPagingOption";
import KifuJougenConditionCapsuleInterface from "../../../dto/kifu_jougen/kifuJougenConditionCapsuleDto";
import KifuJougenConditionCapsuleDto from "../../../dto/kifu_jougen/kifuJougenConditionCapsuleDto";
import KobetsuKifuJougenResultInterface from "../../../dto/kifu_jougen/kobetsuKifuJougenResultDto";
import KobetsuKifuJougenResultDto from "../../../dto/kifu_jougen/kobetsuKifuJougenResultDto";
import KifuSouryouSeigenAllResultInterface from "../../../dto/kifu_jougen/kifuSouryouSeigenAllResultDto";
import KifuSouryouSeigenAllResultDto from "../../../dto/kifu_jougen/kifuSouryouSeigenAllResultDto";
import SouryouKiseiRelationCodeInterface from "../../../dto/kifu_jougen/souryouKiseiRelationCodeDto";
import mockGetLimitAmount from "./mock/mockGetLimitAmount";
import YoushikiEdaKbnIncomeConstants from "../../../dto/balancesheet/youshikiEdaKbnIncomeConstants";
import KifuJougenConstnts from "../../../dto/kifu_jougen/kifuJougenConstnts";
import mockGetSouryouMeisai from "./mock/mockGetSouryouMeisai";
import SearchKifuJougenMeisaiBalancesheetResultInterface from "../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";
import SearchKifuJougenMeisaiBalancesheetResultDto from "../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";

// 検索条件
const capsuleDto: Ref<KifuJougenConditionCapsuleInterface> = ref(new KifuJougenConditionCapsuleDto());

// 1ページ当たりoffset
const PAGING_OFFSET: number = 100;

// 検索条件定数
const EDA_KBN_KOJIN: number = parseInt(YoushikiEdaKbnIncomeConstants.PERSONAL);
const EDA_KBN_CORP: number = parseInt(YoushikiEdaKbnIncomeConstants.CORPORATION);
const EDA_KBN_POLI_ORG: number = parseInt(YoushikiEdaKbnIncomeConstants.POLITIC_ORG);

const SEARCH_PARTY: number = KifuJougenConstnts.SEARCH_PARTY;
const SEARCH_OTHER: number = KifuJougenConstnts.SEARCH_OTHER;



//政治団体検索コンポーネント
const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);
const searchPoliticalOrganizationLeastCapsuleDto: SearchPoliticalOrganizationLeastCapsuleDto = new SearchPoliticalOrganizationLeastCapsuleDto();
searchPoliticalOrganizationLeastCapsuleDto.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
searchPoliticalOrganizationLeastCapsuleDto.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
searchPoliticalOrganizationLeastCapsuleDto.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない

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
    capsuleDto.value.poliOrgCode = sendDto.politicalOrganizationCode;
    orgCode.value = sendDto.politicalOrganizationCode;
    orgName.value = sendDto.politicalOrganizationName;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

// 報告年リスト
const houkokunenList: Ref<SelectOptionInterface[]> = ref(getHoukokunen());
capsuleDto.value.houkokuNen = 2022;

// ラジオボタン設定
const searchCode: Ref<number> = ref(1);
const isSearchCode = computed(() => searchCode.value === 1);

// 仮変数
const orgCode: Ref<number> = ref(0);
const orgName: Ref<string> = ref("");

function onCancel() {
    alert("キャンセル");
}

// 総量規制表示用Dto
const kobetsuAllDto: Ref<KobetsuKifuJougenResultInterface> = ref(new KobetsuKifuJougenResultDto());


// 個別規制表示用Dto
const pageOptionPoliOrg: Ref<SelectOptionInterface[]> = ref([]);
const pageOptionPerson: Ref<SelectOptionInterface[]> = ref([]);
const souryouSeigenAllDto: Ref<KifuSouryouSeigenAllResultInterface> = ref(new KifuSouryouSeigenAllResultDto());

function onSearch() {
    capsuleDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    capsuleDto.value.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない
    capsuleDto.value.isNameSearch = isSearchCode.value;

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(総量規制)
    // const urlSouryou:string = "http://localhost:9080/kifu-jougen/souryou-search"

    souryouSeigenAllDto.value = mockGetSouryouJougen();

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(個別規制)
    //const urlKobetsu: string = "http://localhost:9080/kifu-jougen/kobetsu-search"

    kobetsuAllDto.value = mockGetKobetsuResultAll();
    pageOptionPoliOrg.value = getPagingOption(kobetsuAllDto.value.resultOrgDto.countAll, PAGING_OFFSET);
    pageOptionPerson.value = getPagingOption(kobetsuAllDto.value.resultOrgDto.countAll, PAGING_OFFSET);

}

// 上限突破時セルを赤くする
function getAlert(isOver: boolean): string {
    if (isOver) {
        return "alert";
    } else {
        return "";
    }
}

// 上限突破時違反テキストを表示する
function getOverText(isOver: boolean): string {
    if (isOver) {
        return "違反";
    } else {
        return "限度額以下";
    }
}

// 企業・団体A枠の場合、違反の場合のみ違反とする
function getOverTextCorpA(isOver: boolean, limit: number): string {
    if (isOver) {
        return "違反";
    } else {

        if (limit === -1) {
            return "";
        } else {
            return "制限額以下";

        }
    }
}

// 紐づくテキストを表示
function getKijunText(dantaiKbn: string): string {
    switch (dantaiKbn) {
        case "1":
            return "資本金";

        case "2":
            return "組合員数";

        case "3":
            return "前年の使用経費額";

        default:
            return "";
    }
}

// 上限制限値を呼び出しする
function callLimit(index: number) {

    const calldto: SouryouKiseiRelationCodeInterface = souryouSeigenAllDto.value.listDtoKigyouParty[index];

    if (calldto.dantaiKbn !== "1" && calldto.dantaiKbn !== "2" && calldto.dantaiKbn !== "3") {
        alert("団体の種類が選択されていません");
        return;
    }

    if (Number.isNaN(parseInt(String(calldto.kijunAmount)))) {
        calldto.limitAmount = -1;
        alert("数値以外の値が入っています");
        return;
    }

    // 下記URL団体区分と基準値を使用して『GET』で取得する         
    //const urlLimit:string = "http://localhost:9090/"; 

    calldto.limitAmount = mockGetLimitAmount(calldto.dantaiKbn, calldto.kijunAmount);
    calldto.isLimitOver = calldto.sum > calldto.limitAmount;
}


// 総量規制明細確認用
const pageOptionKojinParty: Ref<SelectOptionInterface[]> = ref([]);
const pageOptionCorpParty: Ref<SelectOptionInterface[]> = ref([]);
const pageOptionKojinOther: Ref<SelectOptionInterface[]> = ref([]);
const pageOptionCorpOther: Ref<SelectOptionInterface[]> = ref([]);

const meisaiKojinPartyDto: Ref<SearchKifuJougenMeisaiBalancesheetResultInterface> = ref(new SearchKifuJougenMeisaiBalancesheetResultDto());
const meisaiCorpPartyDto: Ref<SearchKifuJougenMeisaiBalancesheetResultInterface> = ref(new SearchKifuJougenMeisaiBalancesheetResultDto());
const meisaiKojinOtherDto: Ref<SearchKifuJougenMeisaiBalancesheetResultInterface> = ref(new SearchKifuJougenMeisaiBalancesheetResultDto());
const meisaiCorpOtherDto: Ref<SearchKifuJougenMeisaiBalancesheetResultInterface> = ref(new SearchKifuJougenMeisaiBalancesheetResultDto());

// 明細を取得する(総量)
function searchMeisaiSouryou(edaKbn: number, kifuKbn: number, pageNum: number) {

    const capsuleMeisaiDto: Ref<KifuJougenConditionCapsuleInterface> = ref(new KifuJougenConditionCapsuleDto());

    capsuleMeisaiDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleMeisaiDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    capsuleMeisaiDto.value.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない
    capsuleMeisaiDto.value.isNameSearch = isSearchCode.value;
    capsuleMeisaiDto.value.poliOrgCode = capsuleDto.value.poliOrgCode;
    capsuleMeisaiDto.value.houkokuNen = capsuleMeisaiDto.value.houkokuNen;

    capsuleMeisaiDto.value.seachKifuKbn = kifuKbn;
    capsuleMeisaiDto.value.youshikiEdaKbn = edaKbn;
    capsuleMeisaiDto.value.offset = PAGING_OFFSET;
    capsuleMeisaiDto.value.pageNum = pageNum;

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(ページングあり明細)
    // const urlSouryou:string = "http://localhost:9080/kifu-jougen//get-jouegn-meisai"

    // fetchのmockはこの1行のみ
    const resultDto: SearchKifuJougenMeisaiBalancesheetResultInterface = mockGetSouryouMeisai(edaKbn, pageNum);

    switch (edaKbn) {
        // 個人
        case EDA_KBN_KOJIN:
            if (kifuKbn === SEARCH_PARTY) {
                meisaiKojinPartyDto.value = structuredClone(toRaw(resultDto));
                pageOptionKojinParty.value = getPagingOption(resultDto.countAll, PAGING_OFFSET);
            } else {
                meisaiKojinOtherDto.value = structuredClone(toRaw(resultDto));
                pageOptionKojinOther.value = getPagingOption(resultDto.countAll, PAGING_OFFSET);
            }
            break;
        // 企業・団体
        case EDA_KBN_CORP:
            if (kifuKbn === SEARCH_PARTY) {
                meisaiCorpPartyDto.value = structuredClone(toRaw(resultDto));
                pageOptionCorpParty.value = getPagingOption(resultDto.countAll, PAGING_OFFSET);
            } else {
                meisaiCorpOtherDto.value = structuredClone(toRaw(resultDto));
                pageOptionCorpOther.value = getPagingOption(resultDto.countAll, PAGING_OFFSET);
            }
            break;
        // 実装ミス
        default:
            return;
    }
}


// 明細を取得する(個別)
function searchMeisaiKobetsu(edaKbn: number, pageNum: number) {

    const capsuleMeisaiDto: Ref<KifuJougenConditionCapsuleInterface> = ref(new KifuJougenConditionCapsuleDto());

    capsuleMeisaiDto.value.checkSecurityDto = SessionStorageCommonCheck.getSecurity();
    capsuleMeisaiDto.value.checkPrivilegeDto = SessionStorageCommonCheck.getPrivilege();
    capsuleMeisaiDto.value.checkTransactionDto = createCheckTransactionDto(true);// 変更を許可しない
    capsuleMeisaiDto.value.isNameSearch = isSearchCode.value;
    capsuleMeisaiDto.value.poliOrgCode = capsuleDto.value.poliOrgCode;
    capsuleMeisaiDto.value.houkokuNen = capsuleMeisaiDto.value.houkokuNen;

    capsuleMeisaiDto.value.seachKifuKbn = SEARCH_OTHER;
    capsuleMeisaiDto.value.youshikiEdaKbn = edaKbn;
    capsuleMeisaiDto.value.offset = PAGING_OFFSET;
    capsuleMeisaiDto.value.pageNum = pageNum;

    // 下記Urlと上記検索条件Dtoを用いてBackアクセス(ページングあり明細)
    // const urlSouryou:string = "http://localhost:9080/kifu-jougen//get-jouegn-meisai"

    // fetchのmockはこの1行のみ
    const resultDto: SearchKifuJougenMeisaiBalancesheetResultInterface = mockGetSouryouMeisai(edaKbn, pageNum);

    switch (edaKbn) {
        // 個人
        case EDA_KBN_KOJIN:
            kobetsuAllDto.value.resultPersonalDto = structuredClone(toRaw(resultDto));
            pageOptionPerson.value = getPagingOption(resultDto.countAll, PAGING_OFFSET);
            break;
        // 政治団体
        case EDA_KBN_CORP:
            kobetsuAllDto.value.resultOrgDto = structuredClone(toRaw(resultDto));
            pageOptionPoliOrg.value = getPagingOption(resultDto.countAll, PAGING_OFFSET);
            break;
        // 実装ミス
        default:
            return;
    }
}

</script>
<template>
    <h1>寄付上限チェッカー</h1>

    <div class="one-line">
        抽出条件<br>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        報告年
    </div>
    <div class="right-area">
        <select v-model="capsuleDto.houkokuNen">
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
        検索
    </div>
    <div class="right-area">
        <button @click="onSearch">検索</button>
    </div>
    <div class="clear-both"><br></div>

    <h3>総量規制</h3>
    <div class="one-line">
        ※個人A枠テーブル(合計) <button class="left-space" :disabled="souryouSeigenAllDto.listDtoKojinParty.length < 1"
            @click="searchMeisaiSouryou(EDA_KBN_KOJIN, SEARCH_PARTY, 0)">明細を確認する</button><br>
        <table>
            <tr>
                <th>寄付者</th>
                <th>年間合計</th>
                <th>判定</th>
                <th>制限額</th>
            </tr>
            <tr v-for="dto, index of souryouSeigenAllDto.listDtoKojinParty" :key="index">
                <td>({{ dto.relationCode }}){{ dto.partnerName }} <br>{{ dto.partnerJusho }}</td>
                <td :class="getAlert(dto.isLimitOver)">{{ dto.sum }}</td>
                <td :class="getAlert(dto.isLimitOver)">{{ getOverText(dto.isLimitOver) }}</td>
                <td>{{ dto.limitAmount }}</td>
            </tr>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <div v-if="souryouSeigenAllDto.listDtoKojinParty.length > 0">
        <div class="left-area">
            明細
        </div>
        <div class="right-area">
            <!-- ページング -->
            <select v-model="meisaiKojinPartyDto.pageNumber"
                @change="searchMeisaiSouryou(EDA_KBN_KOJIN, SEARCH_PARTY, meisaiKojinPartyDto.pageNumber)">
                <option v-for="option in pageOptionKojinParty" :key="option.value" :value="option.value"> {{ option.text
                    }}
                </option>
            </select><br>
            <div v-for="dto, index of meisaiKojinPartyDto.listTradingGroup" :key="index">
                {{ dto.partnerName }} {{ dto.partnerAddress }}
                <table>
                    <tr>
                        <th>受け取り政治団体</th>
                        <th>発生日</th>
                        <th>項目名</th>
                        <th>金額</th>
                        <th>取引相手</th>
                    </tr>
                    <tr v-for="entity of dto.listTradingMeisai" :key="entity.offeringBalancesheetIncomeId">
                        <td>({{ entity.relationCode }})<br>{{ entity.dantaiName }}</td>
                        <td>{{ entity.accrualDate }}</td>
                        <td>{{ entity.itemName }}</td>
                        <td>{{ entity.kingaku }}</td>
                        <td>({{ entity.relationCode }})<br>{{ entity.partnerName }}</td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                        <td :class="getAlert(dto.isLimitOver)">{{ dto.sumKifu }}</td>
                        <td :class="getAlert(dto.isLimitOver)">{{ getOverText(dto.isLimitOver) }}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="clear-both"><br></div>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        ※企業団体A枠テーブル(合計)<button class="left-space" :disabled="souryouSeigenAllDto.listDtoKigyouParty.length < 1"
            @click="searchMeisaiSouryou(EDA_KBN_CORP, SEARCH_PARTY, 0)">明細を確認する</button><br>
        <table>
            <tr>
                <th>寄付者</th>
                <th>年間合計</th>
                <th>制限額</th>
                <th>判定</th>
                <th>団体の種類</th>
                <th>金額</th>
            </tr>
            <tr v-for="dto, index of souryouSeigenAllDto.listDtoKigyouParty" :key="index">
                <td>({{ dto.relationCode }}){{ dto.partnerName }} <br>{{ dto.partnerJusho }}</td>
                <td :class="getAlert(dto.isLimitOver)">{{ dto.sum }}</td>
                <td><input type="text" v-model="dto.limitAmount" disabled="true"></td>
                <td :class="getAlert(dto.isLimitOver)">{{ getOverTextCorpA(dto.isLimitOver, dto.limitAmount) }}</td>
                <td><select v-model="dto.dantaiKbn">
                        <option value="1">企業</option>
                        <option value="2">組合</option>
                        <option value="3">その他団体</option>
                    </select></td>
                <td>{{ getKijunText(dto.dantaiKbn) }}<input type="text" v-model="dto.kijunAmount"
                        @change="callLimit(index)"></td>
            </tr>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <div v-if="souryouSeigenAllDto.listDtoKigyouParty.length > 0">
        <div class="left-area">
            明細
        </div>
        <div class="right-area">
            <!-- ページング -->
            <select v-model="meisaiCorpPartyDto.pageNumber"
                @change="searchMeisaiSouryou(EDA_KBN_CORP, SEARCH_PARTY, meisaiCorpPartyDto.pageNumber)">
                <option v-for="option in pageOptionCorpParty" :key="option.value" :value="option.value"> {{ option.text
                    }}
                </option>
            </select><br>
            <div v-for="dto, index of meisaiCorpPartyDto.listTradingGroup" :key="index">
                {{ dto.partnerName }} {{ dto.partnerAddress }}
                <table>
                    <tr>
                        <th>受け取り政治団体</th>
                        <th>発生日</th>
                        <th>項目名</th>
                        <th>金額</th>
                        <th>取引相手</th>
                    </tr>
                    <tr v-for="entity of dto.listTradingMeisai" :key="entity.offeringBalancesheetIncomeId">
                        <td>({{ entity.relationCode }})<br>{{ entity.dantaiName }}</td>
                        <td>{{ entity.accrualDate }}</td>
                        <td>{{ entity.itemName }}</td>
                        <td>{{ entity.kingaku }}</td>
                        <td>({{ entity.relationCode }})<br>{{ entity.partnerName }}</td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                        <td :class="getAlert(dto.isLimitOver)">{{ dto.sumKifu }}</td>
                        <td :class="getAlert(dto.isLimitOver)">{{ getOverText(dto.isLimitOver) }}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        ※個人B枠テーブル(合計)<button class="left-space" :disabled="souryouSeigenAllDto.listDtoKojinOtherOrg.length < 1"
            @click="searchMeisaiSouryou(EDA_KBN_KOJIN, SEARCH_OTHER, 0)">明細を確認する</button><br>
        <table>
            <tr>
                <th>寄付者</th>
                <th>年間合計</th>
                <th>判定</th>
                <th>制限額</th>
            </tr>
            <tr v-for="dto, index of souryouSeigenAllDto.listDtoKojinOtherOrg" :key="index">
                <td>({{ dto.relationCode }}){{ dto.partnerName }} <br>{{ dto.partnerJusho }}</td>
                <td :class="getAlert(dto.isLimitOver)">{{ dto.sum }}</td>
                <td :class="getAlert(dto.isLimitOver)">{{ getOverText(dto.isLimitOver) }}</td>
                <td>{{ dto.limitAmount }}</td>
            </tr>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <div v-if="souryouSeigenAllDto.listDtoKojinOtherOrg.length > 0">
        <div class="left-area">
            明細
        </div>
        <div class="right-area">
            <!-- ページング -->
            <select v-model="meisaiKojinOtherDto.pageNumber"
                @change="searchMeisaiSouryou(EDA_KBN_KOJIN, SEARCH_OTHER, meisaiKojinOtherDto.pageNumber)">
                <option v-for="option in pageOptionKojinOther" :key="option.value" :value="option.value"> {{ option.text
                    }}
                </option>
            </select><br>
            <div v-for="dto, index of meisaiKojinOtherDto.listTradingGroup" :key="index">
                {{ dto.partnerName }} {{ dto.partnerAddress }}
                <table>
                    <tr>
                        <th>受け取り政治団体</th>
                        <th>発生日</th>
                        <th>項目名</th>
                        <th>金額</th>
                        <th>取引相手</th>
                    </tr>
                    <tr v-for="entity of dto.listTradingMeisai" :key="entity.offeringBalancesheetIncomeId">
                        <td>({{ entity.relationCode }})<br>{{ entity.dantaiName }}</td>
                        <td>{{ entity.accrualDate }}</td>
                        <td>{{ entity.itemName }}</td>
                        <td>{{ entity.kingaku }}</td>
                        <td>({{ entity.relationCode }})<br>{{ entity.partnerName }}</td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                        <td :class="getAlert(dto.isLimitOver)">{{ dto.sumKifu }}</td>
                        <td :class="getAlert(dto.isLimitOver)">{{ getOverText(dto.isLimitOver) }}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        ※企業・団体B枠テーブル(存在確認)<button class="left-space" :disabled="souryouSeigenAllDto.listDtoKigyouOtherOrg.length < 1"
            @click="searchMeisaiSouryou(EDA_KBN_CORP, SEARCH_OTHER, 0)">明細を確認する</button><br>
        <table>
            <tr>
                <th>寄付者</th>
                <th>年間合計</th>
                <th>判定</th>
                <th>制限額</th>
            </tr>
            <tr v-for="dto, index of souryouSeigenAllDto.listDtoKigyouOtherOrg" :key="index">
                <td>({{ dto.relationCode }}){{ dto.partnerName }} <br>{{ dto.partnerJusho }}</td>
                <td :class="getAlert(dto.isLimitOver)">{{ dto.sum }}</td>
                <td :class="getAlert(dto.isLimitOver)">{{ getOverText(dto.isLimitOver) }}</td>
                <td>{{ dto.limitAmount }}</td>
            </tr>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <div v-if="souryouSeigenAllDto.listDtoKigyouOtherOrg.length > 0">
        <div class="left-area">
            明細
        </div>
        <div class="right-area">
            <!-- ページング -->
            <select v-model="meisaiCorpOtherDto.pageNumber"
                @change="searchMeisaiSouryou(EDA_KBN_CORP, SEARCH_OTHER, meisaiCorpOtherDto.pageNumber)">
                <option v-for="option in pageOptionCorpOther" :key="option.value" :value="option.value"> {{ option.text
                    }}
                </option>
            </select><br>
            <div v-for="dto, index of meisaiCorpOtherDto.listTradingGroup" :key="index">
                {{ dto.partnerName }} {{ dto.partnerAddress }}
                <table>
                    <tr>
                        <th>受け取り政治団体</th>
                        <th>発生日</th>
                        <th>項目名</th>
                        <th>金額</th>
                        <th>取引相手</th>
                    </tr>
                    <tr v-for="entity of dto.listTradingMeisai" :key="entity.offeringBalancesheetIncomeId">
                        <td>({{ entity.relationCode }})<br>{{ entity.dantaiName }}</td>
                        <td>{{ entity.accrualDate }}</td>
                        <td>{{ entity.itemName }}</td>
                        <td>{{ entity.kingaku }}</td>
                        <td>({{ entity.relationCode }})<br>{{ entity.partnerName }}</td>
                    </tr>
                    <tr>
                        <td colspan="3">&nbsp;</td>
                        <td :class="getAlert(dto.isLimitOver)">{{ dto.sumKifu }}</td>
                        <td :class="getAlert(dto.isLimitOver)">{{ getOverText(dto.isLimitOver) }}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="clear-both"><br></div>

    <h3>個別規制</h3>

    <div class="one-line">
        ※政治団体5000万円(明細)<br>
        <!-- ページング -->
        <select v-model="kobetsuAllDto.resultOrgDto.pageNumber"
            @change="searchMeisaiKobetsu(EDA_KBN_POLI_ORG, kobetsuAllDto.resultOrgDto.pageNumber)">
            <option v-for="option in pageOptionPoliOrg" :key="option.value" :value="option.value"> {{ option.text }}
            </option>
        </select><br>
        <div v-for="dto, index of kobetsuAllDto.resultOrgDto.listTradingGroup" :key="index">
            {{ dto.partnerName }} {{ dto.partnerAddress }}
            <table>
                <tr>
                    <th>受け取り政治団体</th>
                    <th>発生日</th>
                    <th>項目名</th>
                    <th>金額</th>
                    <th>取引相手</th>
                </tr>
                <tr v-for="entity of dto.listTradingMeisai" :key="entity.offeringBalancesheetIncomeId">
                    <td>({{ entity.relationCode }})<br>{{ entity.dantaiName }}</td>
                    <td>{{ entity.accrualDate }}</td>
                    <td>{{ entity.itemName }}</td>
                    <td>{{ entity.kingaku }}</td>
                    <td>({{ entity.relationCode }})<br>{{ entity.partnerName }}</td>
                </tr>
                <tr>
                    <td colspan="3">&nbsp;</td>
                    <td :class="getAlert(dto.isLimitOver)">{{ dto.sumKifu }}</td>
                    <td :class="getAlert(dto.isLimitOver)">{{ getOverText(dto.isLimitOver) }}</td>
                </tr>
            </table>

        </div>

    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        ※個別規制個人B枠(明細)<br>
        <!-- ページング -->
        <select v-model="kobetsuAllDto.resultPersonalDto.pageNumber"
            @change="searchMeisaiKobetsu(EDA_KBN_KOJIN, kobetsuAllDto.resultPersonalDto.pageNumber)">
            <option v-for="option in pageOptionPerson" :key="option.value" :value="option.value"> {{ option.text }}
            </option>
        </select><br>
        <div v-for="dto, index of kobetsuAllDto.resultPersonalDto.listTradingGroup" :key="index">
            {{ dto.partnerName }} {{ dto.partnerAddress }}
            <table>
                <tr>
                    <th>受け取り政治団体</th>
                    <th>発生日</th>
                    <th>項目名</th>
                    <th>金額</th>
                    <th>取引相手</th>
                </tr>
                <tr v-for="entity of dto.listTradingMeisai" :key="entity.offeringBalancesheetIncomeId">
                    <td>({{ entity.relationCode }})<br>{{ entity.dantaiName }}</td>
                    <td>{{ entity.accrualDate }}</td>
                    <td>{{ entity.itemName }}</td>
                    <td>{{ entity.kingaku }}</td>
                    <td>({{ entity.relationCode }})<br>{{ entity.partnerName }}</td>
                </tr>
                <tr>
                    <td colspan="3">&nbsp;</td>
                    <td :class="getAlert(dto.isLimitOver)">{{ dto.sumKifu }}</td>
                    <td :class="getAlert(dto.isLimitOver)">{{ getOverText(dto.isLimitOver) }}</td>
                </tr>
            </table>

        </div>
    </div>
    <div class="clear-both"><br></div>

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
