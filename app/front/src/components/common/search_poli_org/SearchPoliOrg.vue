<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type poliOrgNoInterface from '../../../dto/partner_poli_org/poliOrgNoDto';
import mockGetPoliOrgNoList from './mock/mockGetPoliOrgNoList';

//props,emit
const props = defineProps<{ isFooter: boolean }>();
const emits = defineEmits(["sendCancelPoliOrgNo", "sendPoliOrgNoInterface"]);
const poliOrgList: Ref<poliOrgNoInterface[]> = ref([])

function onSearch() {
    poliOrgList.value = mockGetPoliOrgNoList();
}

/**  
 * 入力内容を破棄する
 */
function onCancel() {
    emits("sendCancelPoliOrgNo");
}

/**  
 * 入力内容を保存する
 */
function onSave(selectedRow: string) {
    // Idでフィルタしているので1件だけ取得できる
    const selectedDto: poliOrgNoInterface = poliOrgList.value.filter((e) => e.poliOrgNo === selectedRow)[0];
    emits("sendPoliOrgNoInterface", selectedDto);
}

</script>
<template>
    <h3>検索条件</h3>
    <div class="left-area">
        関連者政治団体番号(前方一致)
    </div>
    <div class="right-area">
        <input type="text" class="text-input">
    </div>
    <div class="clear-both"><br></div>

    <div class="left-area">
        名前(前方一致)
    </div>
    <div class="right-area">
        <input type="text" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        郵便番号
    </div>
    <div class="right-area">
        <input type="text" class="code-input">&nbsp;-&nbsp;
        <input type="text" class="code-input">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        住所(前方一致)
    </div>
    <div class="right-area">
        <input type="text" class="max-input">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onSearch">検索</button>
    </div>
    <div class="clear-both"></div>

    <hr>


    <h3>検索結果</h3>

    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>政治団体番号</th>
                    <th>政治団体名称</th>
                    <th>市区町村</th>
                    <th>代表者</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="dto in poliOrgList" :key="dto.poliOrgNo">
                    <td>{{ dto.poliOrgNo }}</td>
                    <td>({{ dto.inputName.orgNameKana }}) <br>{{ dto.inputName.orgName }}</td>
                    <td>{{ dto.inputAddress.addressPostal }}</td>
                    <td>({{ dto.delegateNo }})<br> {{ dto.delegateName }}</td>
                    <td><button @click="onSave(dto.poliOrgNo)">選択</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <div class="footer" v-if="props.isFooter">
        <button @click="onCancel" class="footer-button">キャンセル</button>
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
