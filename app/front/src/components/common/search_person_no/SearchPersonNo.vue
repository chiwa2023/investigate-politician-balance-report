<script setup lang="ts">
import { ref, type Ref } from 'vue';
import mockGetPersonList from './mock/mockGetPersonList';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';

//props,emit
const props = defineProps<{ isFooter: boolean }>();
const emits = defineEmits(["sendPersonNoInterface", "sendCanceelPersonNo"]);

const listPerson: Ref<PersonNoInterface[]> = ref([]);

function onPersonSearch() {
    listPerson.value = mockGetPersonList();
}

function onSelectRow(personNo: string) {
    // コードから選択された個人を抽出する
    const selectedDto: PersonNoInterface = listPerson.value.filter((e) => e.personNo === personNo)[0];
    emits("sendPersonNoInterface", selectedDto);
}

function sendCancelPersonNo() {
    emits("sendCanceelPersonNo");
}

</script>
<template>
    <h3>関連者個人検索</h3>

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
    <div class="clear-both"><br></div>

    <div class="left-area">
        関連者コード(前方一致)
    </div>
    <div class="right-area">
        <input type="text" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onPersonSearch">検索</button>
    </div>
    <div class="clear-both"></div>
    <hr>
    <h3>検索結果</h3>
    <table>
        <tbody>
            <tr>
                <th>関連者コード(個人)</th>
                <th>氏名</th>
                <th>住所</th>
                <th>職業</th>
                <th>&nbsp;</th>
            </tr>
            <tr v-for="dto of listPerson" :key="dto.personNo">
                <td>{{ dto.personNo }}</td>
                <td>{{ dto.nameAll }}</td>
                <td>{{ dto.juushoAll }}</td>
                <td>{{ dto.shokugyou }}</td>
                <td><button @click="onSelectRow(dto.personNo)">選択</button></td>
            </tr>
        </tbody>
    </table>
    <div class="clear-both"><br></div>

    <div class="footer" v-if="props.isFooter">
        <button @click="sendCancelPersonNo" class="footer-button">キャンセル</button>
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
