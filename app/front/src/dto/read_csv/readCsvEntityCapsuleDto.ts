import PropseCsvReadTemplateEntity from "../../entity/proposeCsvReadTemplateEntity";
import AbstactCapsuleDto from "../abstactCapsuleDto";

class ReadCsvEntityCapsuleDto extends AbstactCapsuleDto {

    /** 申請csv読み取り仕様Entity */
    proposeCsvReadTemplateEntity: PropseCsvReadTemplateEntity;

    constructor() {
        super();
        this.proposeCsvReadTemplateEntity = new PropseCsvReadTemplateEntity();
    }
}

export { ReadCsvEntityCapsuleDto }