<script setup lang="ts">

const props = defineProps<{
    terminologyId: number,
    terminologyName: string,
    helpExplain: string,
}>();
const emits = defineEmits(['sendCloseTerminology']);

/** 自身を閉じる通知を親画面にする */
function myCloseInfo() {
    emits("sendCloseTerminology");
}
</script>
<template>
    <!-- 
        TODO cssはデザインが得意な方に依頼する
        (用語ヘルプは要素そのものは簡単ではあるが、すべて用語ヘルプがこのコンポーネントを使うので万全を期したい) 
    -->
    <div class="back-over">
        <div class="window-sub">            
            <div style="min-height:20%;">
                用語：{{ props.terminologyName }}
            </div>
            <hr>
            <div style="min-height:35%;">
                用語説明:<br>
                {{  props.helpExplain }}
            </div>
            <hr>
            <div style="text-align:right;padding-right:5%;height:10%;">
            <button @click="myCloseInfo">閉じる</button>
            </div>
        </div>
    </div>
</template>
<style scoped>
/** サブウインドウを載せる背景 */
div.back-over {
    z-index: 9;
    background: rgba(96, 96, 96, .5);
    width: 100%;
    height: 100%;
    position: fixed;
    top: 0;
    left: 0;
}
/** サブウインドウそのもの */
div.window-sub {
    z-index: 10;
    width: 50%;
    height: 50%;
    background: white;
    position: fixed;
    top: 25%;
    left: 25%;
    overflow-y: scroll;
    padding:1%;
}
</style>
