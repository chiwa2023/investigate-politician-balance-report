
﻿import TemplateFrameworkResultDto from "../template/templateFrameworkResultDto";

/**
 * 1件の保存済書証Dto
 */
export default class SaveStorageResultDto extends TemplateFrameworkResultDto{

    /** 書証Id */
    shoshouKbn: string;

    /** 書証Id */
    shoshouId: number;

    /** 書証同一識別コード */
    shoshouCode: number;

    /** 書証保存パス */
    fullPath: string;

    /** 保存子フォルダ */
    childPath: string;

    /** ファイル名 */
    fileName: string;

    /** 登録時間 */
    registTimeText: string;

    /** 文字コード */
    charset: string;

    /** (圧縮ファイルの場合)配下ファイル数 */
    compressCount: number;

    constructor() {
        super();
        
        const INIT_STRING: string = "";
        const INIT_NUMBER: number = 0;

        this.shoshouKbn = INIT_STRING;
        this.shoshouId = INIT_NUMBER;
        this.shoshouCode = INIT_NUMBER;
        this.fullPath = INIT_STRING;
        this.childPath = INIT_STRING;
        this.fileName = INIT_STRING;
        this.registTimeText = INIT_STRING;
        this.charset = INIT_STRING;
        this.compressCount = INIT_NUMBER;
    }
}
