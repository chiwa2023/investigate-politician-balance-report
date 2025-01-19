import KifuJougenTradingInfoInterface from "../../../../dto/kifu_jougen/kifuJougenTradingInfoDto";
import KifuJougenTradingInfoDto from "../../../../dto/kifu_jougen/kifuJougenTradingInfoDto";
import KobetsuKifuJougenResultInterface from "../../../../dto/kifu_jougen/kobetsuKifuJougenResultDto";
import KobetsuKifuJougenResultDto from "../../../../dto/kifu_jougen/kobetsuKifuJougenResultDto";
import SearchKifuJougenMeisaiBalancesheetResultDto from "../../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";
import KobetsuKiseiMeisaiInterface from "../../../../entity/kobetsuKiseiMeisaiEntity";
import KobetsuKiseiMeisaiDto from "../../../../entity/kobetsuKiseiMeisaiEntity";

export default function mockGetKobetsuResultAll(): KobetsuKifuJougenResultInterface {

    const resultDto: KobetsuKifuJougenResultInterface = new KobetsuKifuJougenResultDto();


    resultDto.resultOrgDto = new SearchKifuJougenMeisaiBalancesheetResultDto();
    resultDto.resultOrgDto.countAll = 380;
    resultDto.resultOrgDto.pageNumber = 0;
    resultDto.resultOrgDto.listTradingGroup.push(createDto(3, 1));
    resultDto.resultOrgDto.listTradingGroup.push(createDto(3, 7));



    resultDto.resultPersonalDto = new SearchKifuJougenMeisaiBalancesheetResultDto();
    resultDto.resultPersonalDto.countAll = 190;
    resultDto.resultPersonalDto.pageNumber = 1;
    resultDto.resultPersonalDto.listTradingGroup.push(createDto(1, 1));
    resultDto.resultPersonalDto.listTradingGroup.push(createDto(1, 3));
    resultDto.resultPersonalDto.listTradingGroup.push(createDto(1, 8));

    return resultDto;
}


function createDto(edaKbn: number, i: number): KifuJougenTradingInfoInterface {

    const DANTAI: string = "政治団体";
    const KOJIN: string = "個人";

    let key = "";
    let limit = 0;
    let rate = 0;
    switch (edaKbn) {
        case 1:
            key = KOJIN;
            limit = 1500000; // 一個人150万円
            rate = 1;
            break
        case 2:
            break
        case 3:
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