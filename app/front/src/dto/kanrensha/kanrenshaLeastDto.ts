export default interface KanrenshaLeastInterface {
}

export default class KanrenshaLeastDto implements KanrenshaLeastInterface {
    /** 関連者Id */
    relationId: number;

    /** 関連者同一識別コード */
    relationCode: number;

    /** 関連者名称 */
    relationName: string;

    /** 関連者区分 */
    relationKbn: number;

    constructor() {
        const INIT_NUMBER: number = 0;
        const INIT_STRING: string = "";

        this.relationId = INIT_NUMBER;
        this.relationCode = INIT_NUMBER;
        this.relationName = INIT_STRING;
        this.relationKbn = INIT_NUMBER;
    }
}