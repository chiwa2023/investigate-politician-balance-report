import SearchKifuJougenMeisaiBalancesheetResultInterface from "./searchKifuJougenMeisaiBalancesheetResultDto";
import SearchKifuJougenMeisaiBalancesheetResultDto from "./searchKifuJougenMeisaiBalancesheetResultDto";

export default interface KobetsuKifuJougenResultInterface {

}

export default class KobetsuKifuJougenResultDto implements KobetsuKifuJougenResultInterface {

    /** 政治団体解析結果 */
    resultOrgDto: SearchKifuJougenMeisaiBalancesheetResultInterface;

    /** 個人解析結果 */
    resultPersonalDto: SearchKifuJougenMeisaiBalancesheetResultInterface;

    constructor() {
        this.resultOrgDto = new SearchKifuJougenMeisaiBalancesheetResultDto();
        this.resultPersonalDto = new SearchKifuJougenMeisaiBalancesheetResultDto();
    }

}