import YoushikiEdaKbnIncomeConstants from "../../../../dto/balancesheet/youshikiEdaKbnIncomeConstants";
import KifuJougenTradingInfoInterface from "../../../../dto/kifu_jougen/kifuJougenTradingInfoDto";
import KifuJougenTradingInfoDto from "../../../../dto/kifu_jougen/kifuJougenTradingInfoDto";
import SearchKifuJougenMeisaiBalancesheetResultInterface from "../../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";
import SearchKifuJougenMeisaiBalancesheetResultDto from "../../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";
import KobetsuKiseiMeisaiInterface from "../../../../entity/kobetsuKiseiMeisaiEntity";
import KobetsuKiseiMeisaiDto from "../../../../entity/kobetsuKiseiMeisaiEntity";

export default function mockGetSouryouMeisai(edaKbn: number,pageNum:number): SearchKifuJougenMeisaiBalancesheetResultInterface {

    const resultDto: SearchKifuJougenMeisaiBalancesheetResultInterface = new SearchKifuJougenMeisaiBalancesheetResultDto();

    resultDto.countAll = 200;
    resultDto.pageNumber = pageNum;

    resultDto.listTradingGroup.push(createDto(edaKbn, 1));
    resultDto.listTradingGroup.push(createDto(edaKbn, 3));
    resultDto.listTradingGroup.push(createDto(edaKbn, 8));

    return resultDto;
}


function createDto(edaKbn: number, i: number): KifuJougenTradingInfoInterface {

    const EDA_KBN_KOJIN: number = parseInt(YoushikiEdaKbnIncomeConstants.PERSONAL);
    const EDA_KBN_CORP: number = parseInt(YoushikiEdaKbnIncomeConstants.CORPORATION);
    const EDA_KBN_POLI_ORG: number = parseInt(YoushikiEdaKbnIncomeConstants.POLITIC_ORG);

    const DANTAI: string = "政治団体";
    const KOJIN: string = "個人";
    const CORP: string = "企業";

    let key = "";
    let limit = 0;
    let rate = 0;
    switch (edaKbn) {
        case EDA_KBN_KOJIN:
            key = KOJIN;
            limit = 1500000; // 一個人150万円
            rate = 1;
            break
        case EDA_KBN_CORP:
            key = CORP;
            limit = -1; // 上限は示さない
            rate = 3;
            break
        case EDA_KBN_POLI_ORG:
            key = DANTAI;
            limit = 50000000; // 一団体5000万円
            rate = 4;
            break
    }
    const trading = key + (i * 7);
    const tradingInfo = new KifuJougenTradingInfoDto();
    tradingInfo.partnerName = trading;
    tradingInfo.partnerAddress = "宮崎県架空市";
    tradingInfo.relationId = i * 11;
    tradingInfo.relationCode = i * 10;
    tradingInfo.youshikiEdaKbn = edaKbn;
    for (let x = 0; x <= i; x++) {
        tradingInfo.listTradingMeisai.push(createMeisaiEntity(trading, (x + 1) * rate));
    }

    let sum = 0;
    for (const e of tradingInfo.listTradingMeisai) {
        sum += e.kingaku;
    }

    tradingInfo.sumKifu = sum;
    tradingInfo.isLimitOver = tradingInfo.sumKifu > limit;

    return tradingInfo;
}

function createMeisaiEntity(trading: string, i: number): KobetsuKiseiMeisaiInterface {

    const entity: KobetsuKiseiMeisaiInterface = new KobetsuKiseiMeisaiDto();

    entity.accrualDateValue = new Date(2022, 12, 15);
    entity.accrualDate = "2022-12-15";
    entity.kingaku = 500000 * i;
    entity.itemName = "寄付受け取り";
    entity.dantaiName = "目標政治団体";
    entity.partnerName = trading;
    entity.partnerJuusho = "和歌山県架空市";

    return entity;
}