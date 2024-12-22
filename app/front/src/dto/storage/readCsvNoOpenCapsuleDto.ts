import AbstactCapsuleDto from "../abstactCapsuleDto";

export default interface ReadCsvNoOpenCapsuleInterface {


}

export default class ReadCsvNoOpenCapsuleDto extends AbstactCapsuleDto implements ReadCsvNoOpenCapsuleInterface {

    /** csvテキスト形式  */
    content: string;

    /** ヘッダ有無  */
    hasHeader: boolean;

    constructor() {
        super();
        this.content = "";
        this.hasHeader = false;

    }
}