import KifuSouryouSeigenAllResultInterface from "../../../../dto/kifu_jougen/kifuSouryouSeigenAllResultDto";
import KifuSouryouSeigenAllResultDto from "../../../../dto/kifu_jougen/kifuSouryouSeigenAllResultDto";
import SouryouKiseiRelationCodeInterface from "../../../../dto/kifu_jougen/souryouKiseiRelationCodeDto";
import SouryouKiseiRelationCodeDto from "../../../../dto/kifu_jougen/souryouKiseiRelationCodeDto";

export default function mockGetSouryouJougen(): KifuSouryouSeigenAllResultInterface {

    const resultDto: KifuSouryouSeigenAllResultInterface = new KifuSouryouSeigenAllResultDto();

    // 個人 対 政党(A枠)
    resultDto.listDtoKojinParty.push(createDto(1,"個人A",100000,20000000));
    resultDto.listDtoKojinParty.push(createDto(2,"個人A",700000,20000000));
    resultDto.listDtoKojinParty.push(createDto(3,"個人A",40000,20000000));
    resultDto.listDtoKojinParty.push(createDto(4,"個人A",5500000,20000000));
    resultDto.listDtoKojinParty.push(createDto(5,"個人A",80090000,20000000));

    // 企業団体 対 政党(A枠)
    resultDto.listDtoKigyouParty.push(createDto(1,"企業",3000,-1));
    resultDto.listDtoKigyouParty.push(createDto(2,"組合",3000000,-1));
    resultDto.listDtoKigyouParty.push(createDto(3,"その他団体",30000000,-1));
    resultDto.listDtoKigyouParty.push(createDto(5,"組合",900000000,-1));

    // 個人 対 その他団体(B枠)
    resultDto.listDtoKojinOtherOrg.push(createDto(1,"個人B",60000,10000000));
    resultDto.listDtoKojinOtherOrg.push(createDto(1,"個人B",60000,10000000));

    // 企業団体 対 その他団体(問答無用で違反)
    resultDto.listDtoKigyouOtherOrg.push(createDto(1,"企業",3000,0));

    return resultDto;
}

function createDto(i: number,trading:string ,sum:number ,limit: number): SouryouKiseiRelationCodeInterface {

    const dto: SouryouKiseiRelationCodeInterface = new SouryouKiseiRelationCodeDto();

    dto.relationId = i * 11;
    dto.relationCode = i * 10;
    dto.partnerName = trading+i;
    dto.partnerJusho = "山形県架空市";

    dto.sum = sum;
    dto.limitAmount = limit
    if(limit != -1){
        dto.isLimitOver = dto.sum > limit;
    }

    return dto;
}