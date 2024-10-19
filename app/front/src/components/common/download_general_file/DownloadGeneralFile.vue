<script setup lang="ts">
import OneFileBlobDto from '../../../dto/storage/oneFileBlobDto';
import SaveStorageResultDto from '../../../dto/storage/saveStorageResultDto';

//props
const props = defineProps<{ saveStorageResultDto: SaveStorageResultDto, }>();

/**
 * ファイルダウンロード
 */
async function onDownload() {

    if (props.saveStorageResultDto.shoshouId === "") {
        //書証Dtoが初期値のままの場合は即離脱
        alert("呼び出すファイルを指定していません");
        return;
    }
    //実接続
    const url = "http://localhost:9080/get-file-binary";
    const method = "POST";
    const body = JSON.stringify(props.saveStorageResultDto);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    fetch(url, { method, headers, body })
        .then(async (response) => {
            //データを取得
            if (200 === response.status) {
                //レスポンスの取得
                const oneFileBlobDto: OneFileBlobDto = await response.json();

                //Base64文字列からMIMEType不明(=application/octet-stream)Blobに変換
                const bin: string = atob(oneFileBlobDto.fileContentBase64);
                const buffer = new Uint8Array(bin.length);
                for (var i = 0; i < bin.length; i++) {
                    buffer[i] = bin.charCodeAt(i);
                }
                const blob: Blob = new Blob([buffer.buffer], { "type": "application/octet-stream", });

                //リンクを作成して強制発火
                let anchorElement = document.createElement('a');
                anchorElement.href = URL.createObjectURL(blob);
                anchorElement.download = oneFileBlobDto.fileName;
                document.body.appendChild(anchorElement);
                anchorElement.click();
            }

        })
        .catch((error) => { alert(error); });

    //ダミーデータ
    //const data: string = "1234";
    //const blob: Blob = new Blob([data], { "type": "text/plain'", });

    //リンクを作成して強制発火
    //let anchorElement = document.createElement('a');
    //anchorElement.href = URL.createObjectURL(blob);
    //anchorElement.download = "1234.txt";
    //document.body.appendChild(anchorElement);
    //anchorElement.click();
}
</script>
<template>
    <button @click="onDownload">保存された書証をダウンロード</button>
</template>
<style scoped></style>
