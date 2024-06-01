<script setup lang="ts">
import { ref, Ref } from "vue";
import PoliticalOrganizationLeastInterface from "../../../dto/political_organization/politicalOrganizationLeastDto";
import PoliticalOrganizationLeastDto from "../../../dto/political_organization/politicalOrganizationLeastDto";
import SearchPoliticalOrganization from "../../common/search_political_organization/SearchPoliticalOrganization.vue";
import LinkBalancesheetVersionDto from "../../../dto/make_balancesheet_link/linkBalancesheetVersionDto";
import mockGetLinkVersionList from "./mock/mockGetLinkVersionList";
import createNewLinkversionDto from "../../../dto/make_balancesheet_link/createNewLinkversionDto";
import { useRouter } from "vue-router";

//版Dtoリスト
const listVersion: Ref<LinkBalancesheetVersionDto[]> = ref([]);

//router
const router = useRouter();

/* 政治団体 */
const isVisibleSearchPoliticalOrganizationLeast: Ref<boolean> = ref(false);
const politicalOrgnaizationId: Ref<number> = ref(0);
const politicalOrgnaizationCode: Ref<number> = ref(0);
const politicalOrgnaizationName: Ref<string> = ref("");
const politicalDto: Ref<PoliticalOrganizationLeastInterface> = ref(new PoliticalOrganizationLeastDto());

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
    politicalDto.value = sendDto;
    //政治団体を設定
    politicalOrgnaizationId.value = sendDto.politicalOrganizationId;
    politicalOrgnaizationCode.value = sendDto.politicalOrganizationCode;
    politicalOrgnaizationName.value = sendDto.politicalOrganizationName;

    //非表示
    isVisibleSearchPoliticalOrganizationLeast.value = false;
}

/* 表示期間年 */
const nowDate = new Date();
//7年前から今年まで(選挙管理員会によって異なるが、基本的に今年分は「欠損」で表示される)
const yearStart: Ref<number> = ref(nowDate.getFullYear() - 7);
const yearEnd: Ref<number> = ref(nowDate.getFullYear());

const addOfferingYear: Ref<number> = ref(nowDate.getFullYear() - 1);
const addOfferingDate: Ref<string> = ref(nowDate.getFullYear() - 1 + "-11-15");

//追加作業時のページ管理番号(実際に永続化するときはあらためて振り番する、削除の際のマイナス厳禁!)
let maxVersionId: number = 0;
let maxVersionCode: number = 0;

/**
 * 収支報告書検索選択
 */
function onSearch() {
    //政治団体が未定の場合はロジックを抜ける
    if (politicalOrgnaizationId.value === 0) {
        alert("政治団体を指定してください");
        return;
    }
    //政治団体と提出年から最新状態を検索
    listVersion.value = mockGetLinkVersionList(yearStart.value, yearEnd.value);
}
/**
 * 新バージョン追加
 */
function onAdd() {
    if (addOfferingYear.value < yearStart.value) {
        alert("確認期間より前の提出年を指定しています");
        //入力不備のためメソッドを抜ける
        return;
    }
    if (addOfferingYear.value > yearEnd.value) {
        alert("確認期間より後の提出年を指定しています");
        //入力不備のためメソッドを抜ける
        return;
    }

    const listCheck: LinkBalancesheetVersionDto[] = listVersion.value
        .filter((dto) => dto.offeringYear === addOfferingYear.value)
        .filter((dto) => dto.offeringDate === addOfferingDate.value);

    if (listCheck.length > 0) {
        alert("提出年と提出日が重複しています");
    } else {
        let index = 0;
        let isAdd = false;
        for (const dto of listVersion.value) {
            if (dto.offeringYear === addOfferingYear.value) {
                const dateParamCompare: string[] = dto.offeringDate.split("-");
                const dateCompare: Date = new Date(parseInt(dateParamCompare[0]), parseInt(dateParamCompare[1]), parseInt(dateParamCompare[2]));

                const dateParamAdd: string[] = addOfferingDate.value.split("-");
                const dateAdd: Date = new Date(parseInt(dateParamAdd[0]), parseInt(dateParamAdd[1]), parseInt(dateParamAdd[2]));
                //発行日が既存のデータより前の場合はその前に差し込む
                if (dateCompare > dateAdd) {
                    listVersion.value.splice(index, 0, createNewDto());
                    isAdd = true;
                    break;
                }
            }
            //同年中に追加できなかった場合、同年の末尾
            if (dto.offeringYear === addOfferingYear.value + 1) {
                listVersion.value.splice(index, 0, createNewDto());
                isAdd = true;
                break;
            }
            index++;
        }

        //ループ内で追加できなかった場合は最終年の一番最後の公開日
        if (!isAdd) {
            maxVersionId++;
            maxVersionCode++;
            listVersion.value.push(createNewDto());
        }
        //提出回数を直す
        reNumberOfferingTime();
    }
}
/** 
 * 新たな版を追加する 
 * @returns 版Dto
 */
function createNewDto(): LinkBalancesheetVersionDto {
    maxVersionId++;
    maxVersionCode++;
    return createNewLinkversionDto(maxVersionId, maxVersionCode, politicalDto.value, addOfferingYear.value, 99, addOfferingDate.value);
}

/** 
 * 増減に合わせて提出回数を振りなおす
 */
function reNumberOfferingTime() {
    let year: number = yearStart.value;
    let count: number = 0;

    for (const dto of listVersion.value) {
        //提出年が切り替わったので提出回数を初期化
        if (year < dto.offeringYear) {
            year = dto.offeringYear;
            count = 0;
        }
        count++;
        dto.offeringTimes = count;
    }
}

/**
 * 版削除
 * @param rowId 選択された行のId
 */
function onDelete(rowId: number) {
    //Idをフィルタであれば一意このページでの一貫性を保証できる
    listVersion.value = listVersion.value.filter((dto) => dto.linkBalancesheetVersionId !== rowId);
    //提出回数を直す
    reNumberOfferingTime();
}

/**
 * 版参照
 * @param rowId 選択された行のId
 */
function onRefer(rowId: number) {
    let formalId: number = 0;
    const list = listVersion.value.filter((dto) => dto.linkBalancesheetVersionId !== rowId);
    if (list.length > 0) {
        //稼働時にはListサイズは常に1
        formalId = list[0].publicationFormalItemId;
        
    }
    //個別編集ページへ遷移
    router.push({
        name: "MakeLinkBalancesheet",
        query: { rowId: formalId },
    });
}

/** キャンセル処理 */
function onCancel() {
    alert("キャンセル");

}

/** 保存処理 */
function onSave() {
    alert("保存");
}

</script>
<template>
    <h1>政治資金収支報告書欠損検出(版管理)</h1>

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
        表示期間
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
                <th>提出日</th>
                <th>公式情報</th>
                <th>公式保存期間</th>
                <th>非公式情報</th>
                <th>編集ページ</th>
                <th>削除</th>
            </tr>
            <tr v-for="(versionDto) in listVersion" :key="versionDto.linkBalancesheetVersionId">
                <td>{{ versionDto.offeringYear }}</td>
                <td>{{ versionDto.politicalOrgnaizationName }}</td>
                <td>{{ versionDto.offeringTimes }}</td>
                <td><input type="date" v-model="versionDto.offeringDate"></td>
                <td>
                    <div v-show="versionDto.isDataSetFormal"> <a :href="versionDto.publicationLinkUrl">選挙管理員会</a></div>
                    <div v-show="!versionDto.isDataSetFormal">欠損</div>
                </td>
                <td>
                    <div v-show="versionDto.isClosed">終了</div>
                    <div v-show="!versionDto.isClosed">{{ versionDto.publicationDate }}から3年</div>
                </td>
                <td>
                    <div v-show="versionDto.isDataSetInformal">あり</div>
                    <div v-show="!versionDto.isDataSetInformal">欠損</div>
                </td>
                <td><button :disabled="!versionDto.isDataSetFormal"
                        @click="onRefer(versionDto.linkBalancesheetVersionId)">編集</button></td>
                <td><button @click="onDelete(versionDto.linkBalancesheetVersionId)">削除</button></td>
            </tr>
        </table>
        <br>
        新規追加<br>&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="number" v-model="addOfferingYear" class="code-input">年&nbsp;
        <input type="date" v-model="addOfferingDate" class="code-input left-margin">提出日
        <button class="left-margin" @click="onAdd">追加</button>
    </div>
    <div class="clear-both" />

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button">保存</button>
    </div>

    <!-- ベースを操作禁止するレイヤ -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast" class="overBackground"></div>
    <!-- 政治団体検索コンポーネント -->
    <div v-if="isVisibleSearchPoliticalOrganizationLeast">
        <div class="overComponent">
            <SearchPoliticalOrganization :isEditable="false"
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
