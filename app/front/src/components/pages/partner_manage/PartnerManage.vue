<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import PartnerPersonEdit from '../../common/partner_person_edit/PartnerPersonEdit.vue';
import SearchPersonNo from '../../common/search_person_no/SearchPersonNo.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
import PersonNoDto from '../../../dto/partner_person/personNoDto';
import PartnerCorpEdit from '../../common/partner_corp_edit/PartnerCorpEdit.vue';
import PartnerPoliOrgEdit from '../../common/partner_poli_org_edit/PartnerPoliOrgEdit.vue';
import type PoliOrgNoInterface from '../../../dto/partner_poli_org/poliOrgNoDto';
import PoliOrgNoDto from '../../../dto/partner_poli_org/poliOrgNoDto';
import SearchCorpNo from '../../common/search_corp_no/SearchCorpNo.vue';
import type CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import CorpNoDto from '../../../dto/partner_corp/corpNoDto';
import SearchPoliOrg from '../../common/search_poli_org/SearchPoliOrg.vue';

// 表示
const viewStatus: Ref<number> = ref(1);



/** 選択された関連者個人を受信する */
const inputPersonDto: Ref<PersonNoInterface> = ref(new PersonNoDto());
function recievePersonNoInterface(sendDto: PersonNoInterface) {
    inputPersonDto.value = structuredClone(toRaw(sendDto));
}

/** 選択された関連者政治団体を受信する */
const inputPoliOrgDto: Ref<PoliOrgNoInterface> = ref(new PoliOrgNoDto());
function recievePoliOrgNoInterface(sendDto: PoliOrgNoInterface) {
    inputPoliOrgDto.value = structuredClone(toRaw(sendDto));
}

// 検索リスト
const listCorp: Ref<CorpNoInterface[]> = ref([]);

const inputCorpNoDto: Ref<CorpNoInterface> = ref(new CorpNoDto());
/** 選択された関連者企業／団体を受信する */
function recieveCorpCoInterface(sendDto: CorpNoInterface) {
    inputCorpNoDto.value = structuredClone(toRaw(sendDto));
}

</script>
<template>
    <h1>関連者管理</h1>
    ※複数の関連者の編集権限があるので検索画面必要

    <div class="clear-both"></div>
    <div class="left-area">
        関連者区分
    </div>
    <div class="right-area">
        <span><input type="radio" id="editSelect" v-model="viewStatus" value="1">1.個人</span>
        <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="2">2.企業／団体</span>
        <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="3">3.政治団体</span>
    </div>
    <div class="clear-both"></div>

    <hr>

    <div v-if="viewStatus == 1" class="one-line">
        <!-- 検索 -->
        <SearchPersonNo :is-footer="false" @send-person-no-interface="recievePersonNoInterface"></SearchPersonNo>
        <hr>
        <PartnerPersonEdit :edit-dto="inputPersonDto"></PartnerPersonEdit>
    </div>

    <div v-if="viewStatus == 2" class="one-line">
        <!-- 検索 -->
        <SearchCorpNo :list="listCorp" :is-footer="false" @send-corp-no-interface="recieveCorpCoInterface">
        </SearchCorpNo>
        <hr>
        <PartnerCorpEdit :edit-dto="inputCorpNoDto"></PartnerCorpEdit>
    </div>

    <div v-if="viewStatus == 3" class="one-line">
        <!-- 検索 -->
        <SearchPoliOrg :is-footer="false" @send-poli-org-no-interface="recievePoliOrgNoInterface"></SearchPoliOrg>
        <hr>
        <PartnerPoliOrgEdit :edit-dto="inputPoliOrgDto"></PartnerPoliOrgEdit>
    </div>


</template>
<style scoped></style>
