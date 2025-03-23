import KifuJougenTradingInfoInterface from "../../../../dto/kifu_jougen/kifuJougenTradingInfoDto";
import KifuJougenTradingInfoDto from "../../../../dto/kifu_jougen/kifuJougenTradingInfoDto";
import SearchKifuJougenMeisaiBalancesheetResultInterface from "../../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";
import SearchKifuJougenMeisaiBalancesheetResultDto from "../../../../dto/kifu_jougen/searchKifuJougenMeisaiBalancesheetResultDto";
import KobetsuKiseiMeisaiInterface from "../../../../entity/kobetsuKiseiMeisaiEntity";
import KobetsuKiseiMeisaiDto from "../../../../entity/kobetsuKiseiMeisaiEntity";

export default function mockGetPartyJougenData(): SearchKifuJougenMeisaiBalancesheetResultInterface {

    const resultDto: SearchKifuJougenMeisaiBalancesheetResultInterface = new SearchKifuJougenMeisaiBalancesheetResultDto();
    resultDto.pageNumber = 0;
    resultDto.countAll = 140;
    resultDto.listTradingGroup.splice(0);
    resultDto.listTradingGroup.push(createSumDto(1, 1));
    resultDto.listTradingGroup.push(createSumDto(2, 1));
    resultDto.listTradingGroup.push(createSumDto(3, 2));
    resultDto.listTradingGroup.push(createSumDto(4, 3));

    return resultDto;
}

function createSumDto(index: number, edaKbn: number): KifuJougenTradingInfoInterface {

    const dto: KifuJougenTradingInfoInterface = new KifuJougenTradingInfoDto();

    let name = "";

    switch (edaKbn) {
        case 1:
            name = "パーティ　次郎"
            break;
        case 2:
            name = "財閥子会社"
            break;
        case 3:
            name = "所在不明政治団体"
            break;
    }

    dto.partnerName = name+index;
    dto.partnerAddress = "三重県架空市";
    dto.relationId = index * 11;
    dto.relationCode = index * 10 + 2;
    dto.sumKifu = index * 5000 + index;
    dto.youshikiEdaKbn = edaKbn;
    dto.listTradingMeisai = createMeisaiDto(index,dto.partnerName);
    return dto;
}


function createMeisaiDto(index: number, partner: string): KobetsuKiseiMeisaiInterface[] {

    const list:KobetsuKiseiMeisaiInterface[] = [];
    list.splice(0);
    
    const date:string = "2022-12-05";
    const poliOrrgName:string = "ちゃらんぽらん政治団体";
    const poliOrgDaihyou:string = "代表者太郎";
    const item:string = "パーティ参加費";
    const kingaku:number = 6000;    
    const dto1:KobetsuKiseiMeisaiInterface = new KobetsuKiseiMeisaiDto();

    dto1.accrualDate=date;
    dto1.dantaiName=poliOrrgName;
    dto1.daihyouName=poliOrgDaihyou;
    dto1.itemName=item;
    dto1.kingaku=kingaku;
    dto1.relationCode=index * 10 + 2; 
    dto1.partnerName = partner;
    dto1.partnerJuusho="三重県架空市";




    list.push(dto1);
    const dto2:KobetsuKiseiMeisaiInterface = new KobetsuKiseiMeisaiDto();
    dto2.accrualDate=date;
    dto2.dantaiName=poliOrrgName;
    dto2.daihyouName=poliOrgDaihyou;
    dto2.itemName=item;
    dto2.kingaku=kingaku;
    dto2.relationCode=index * 10 + 2; 
    dto2.partnerName = partner;
    dto2.partnerJuusho="三重県架空市";

    list.push(dto2);
    const dto3:KobetsuKiseiMeisaiInterface = new KobetsuKiseiMeisaiDto();
    dto3.accrualDate=date;
    dto3.dantaiName=poliOrrgName;
    dto3.daihyouName=poliOrgDaihyou;
    dto3.itemName=item;
    dto3.kingaku=kingaku;
    dto3.relationCode=index * 10 + 2; 
    dto3.partnerName = partner;
    dto3.partnerJuusho="三重県架空市";
    list.push(dto3);
    const dto4:KobetsuKiseiMeisaiInterface = new KobetsuKiseiMeisaiDto();
    list.push(dto4);
    dto4.accrualDate=date;
    dto4.dantaiName=poliOrrgName;
    dto4.daihyouName=poliOrgDaihyou;
    dto4.itemName=item;
    dto4.kingaku=kingaku;
    dto4.relationCode=index * 10 + 2; 
    dto4.partnerName = partner;
    dto4.partnerJuusho="三重県架空市";
    return list;
}
