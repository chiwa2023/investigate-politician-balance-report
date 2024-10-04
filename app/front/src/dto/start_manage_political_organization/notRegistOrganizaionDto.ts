import ElectionCommissionLeastInterface from "../election_commission/electionCommissionDto";
import ElectionCommissionLeastDto from "../election_commission/electionCommissionDto";
import SaveStorageResultDto from "../storage/saveStorageResultDto";

export default class NotRegistOrganizaionDto {

    /** 登録申請Id */
    notRegistOrganizaionId:number;
    /** 登録申請同一識別コード */
    notRegistOrganizaionCode:number;

    /** 選挙管理委員会最小限情報 */
    electionCommissionLeastDto: ElectionCommissionLeastInterface;

    /** 書証情報 */
    saveStorageResultDto: SaveStorageResultDto;

    /** 政治団体名称 */
    politicalOrganizationName: string;

    constructor() {

        const INIT_NUMBER = 0;
        const INIT_STRING = "";

        this.electionCommissionLeastDto = new ElectionCommissionLeastDto;
        this.saveStorageResultDto = new SaveStorageResultDto();
        this.politicalOrganizationName = INIT_STRING;

        this.notRegistOrganizaionId = INIT_NUMBER;
        this.notRegistOrganizaionCode = INIT_NUMBER;

    }

}