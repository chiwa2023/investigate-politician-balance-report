import PoliOrgFeeInsuDto from "../../../../dto/membership_fee/poliOrgFeeInsuDto";

export default function mockGetSeijikaJoshiList(): PoliOrgFeeInsuDto[] {

    const list: PoliOrgFeeInsuDto[] = [];

    list.push(createDto(
        11,
        10,
       "ホリエモン新党",
        101,
        100,
        "立花孝志",
        "****",
        3446442,
        168,
        20514.5
     ));

     list.push(createDto(
        21,
        20,
       "政治家女子48党",
        201,
        200,
        "大津綾香",
        "****",
        1498000,
        772,
        1940.4
     ));


    return list;
}


function createDto(
    politicalOrgnaizationId: number,
    politicalOrgnaizationCode: number,
    politicalOrgnaizationName: string,
    poliOrgDaihyoushaId: number,
    poliOrgDaihyoushaCode: number,
    poliOrgDaihyoushaName: string,
    rating: string,
    fee: number,
    insu: number,
    average: number
) {
    const dto: PoliOrgFeeInsuDto = new PoliOrgFeeInsuDto();

    dto.politicalOrgnaizationId = politicalOrgnaizationId;
    dto.politicalOrgnaizationCode = politicalOrgnaizationCode;
    dto.politicalOrgnaizationName = politicalOrgnaizationName;

    dto.poliOrgDaihyoushaId = poliOrgDaihyoushaId;
    dto.poliOrgDaihyoushaCode = poliOrgDaihyoushaCode;
    dto.poliOrgDaihyoushaName = poliOrgDaihyoushaName;
    dto.rating = rating;
    dto.fee = fee;
    dto.insu = insu;
    dto.average = average;
    return dto;
}