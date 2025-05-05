<script setup lang="ts">
import { ref, watch, type Ref } from 'vue';
import type CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import mockGetCorpList from './mock/mockGetCorpList';

//props,emit
const props = defineProps<{ list: CorpNoInterface[], isFooter: boolean }>();
const emits = defineEmits(["sendCorpNoInterface", "sendCanceelCorpNo"]);


//const listProps: ComputedRef<CorpInterface[]> = computed(() => { return props.list });
const listCorp: Ref<CorpNoInterface[]> = ref([]);
watch(props.list, () => {
    //alert("変更");
    listCorp.value.splice(0);
    for (const dto of props.list) {
        listCorp.value.push(dto);
    }
});

// 検索リスト
function onCorpSearch() {
    listCorp.value = mockGetCorpList();
}

/** 行選択 */
function onSelectRow(selectedNo: string) {
    // 検索データからコピーすべき元データを抽出
    const selectedDto: CorpNoInterface = listCorp.value.filter((e) => e.corpNo === selectedNo)[0];
    emits("sendCorpNoInterface", selectedDto);
}

function sendCancelCorpNo() {
    emits("sendCanceelCorpNo");
}

</script>
<template>
    <h3>検索条件</h3>
    <div class="left-area">
        法人番号(前方一致)
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
        <button @click="onCorpSearch">検索</button>
    </div>
    <div class="clear-both"></div>
    <hr>
    <h3>検索結果</h3>
    <div class="one-line">

        <table>
            <tbody>
                <tr>
                    <th>関連者コード(企業／団体)</th>
                    <th>法人番号</th>
                    <th>法人名</th>
                    <th>住所</th>
                    <th>代表者</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="dto of listCorp" :key="dto.corpNo">
                    <td>{{ dto.corpNo }}</td>
                    <td>{{ dto.houjinNo }}</td>
                    <td>{{ dto.corpName }}</td>
                    <td>{{ dto.inputAddress.addressPostal }}</td>
                    <td>{{ dto.orgDelegate }}</td>
                    <td><button @click="onSelectRow(dto.corpNo)">選択</button></td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="clear-both"><br></div>

    <div class="footer" v-if="props.isFooter">
        <button @click="sendCancelCorpNo" class="footer-button">キャンセル</button>
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
