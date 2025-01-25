import AbstractCapsuleDto from "../abstractCapsuleDto";

export default interface TemplateWithPagingCapsuleInterface {

}

export default class TemplateWithPagingCapsuleDto extends AbstractCapsuleDto implements TemplateWithPagingCapsuleInterface {

    /** 表示件数  */
    countAll: number;

    /** 件数上限  */
    limit: number;

    /** 検索位置  */
    offset: number;

    constructor() {
        super();

        // 初期データ
        const INIT_NUMBER = 0;

        this.countAll = INIT_NUMBER;
        this.limit = INIT_NUMBER;
        this.offset = INIT_NUMBER;
    }
}