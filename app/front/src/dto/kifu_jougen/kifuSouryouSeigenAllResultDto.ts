import SouryouKiseiRelationCodeInterface from "./souryouKiseiRelationCodeDto";

export default interface KifuSouryouSeigenAllResultInterface {

}

/**
 * 総量規制データを個人・企業団体など格納するDto
 */
export default class KifuSouryouSeigenAllResultDto implements KifuSouryouSeigenAllResultInterface {

    /** 個人が政党寄付(通称A枠)上限額Dtoリスト */
    listDtoKojinParty: SouryouKiseiRelationCodeInterface[];

    /** 個人がその他団体寄付(通称B枠)上限額Dtoリスト */
    listDtoKojinOtherOrg: SouryouKiseiRelationCodeInterface[];

    /** 企業・団体が政党寄付(通称A枠)上限額Dtoリスト */
    listDtoKigyouParty: SouryouKiseiRelationCodeInterface[];

    /** 企業・団体がその他団体寄付(通称B枠)上限額Dtoリスト */
    listDtoKigyouOtherOrg: SouryouKiseiRelationCodeInterface[];

    constructor() {

        this.listDtoKojinParty = [];
        this.listDtoKojinOtherOrg = [];
        this.listDtoKigyouParty = [];
        this.listDtoKigyouOtherOrg = [];
    }

}