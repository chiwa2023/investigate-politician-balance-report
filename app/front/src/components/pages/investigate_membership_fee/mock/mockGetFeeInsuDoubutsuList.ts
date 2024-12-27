import PoliOrgFeeInsuDto from "../../../../dto/membership_fee/poliOrgFeeInsuDto";

export default function mockGetFeeInsuDoubutsuList(): PoliOrgFeeInsuDto[] {

    const list: PoliOrgFeeInsuDto[] = [];

    list.push(createDto(
        11,
        10,
       "ぞうさんの群れ",
        101,
        100,
        "ぞうさん大将",
        "党費未満",
        23400,
        8,
        243.8
     ));

     list.push(createDto(
        21,
        20,
       "にゃんにゃんの群れ",
        201,
        200,
        "にゃんにゃん先鋒",
        "党費未満",
        13200,
        5,
        220
     ));

     list.push(createDto(
        31,
        30,
       "キリンさんの群れ",
        301,
        300,
        "キリンさん次鋒",
        "注意水準",
        25200,
        3,
        700
     ));

     list.push(createDto(
        41,
        40,
       "わんちゃんの群れ",
        401,
        400,
        "わんちゃん中堅",
        "党費超え",
        11400,
        2,
        475
     ));

     list.push(createDto(
        51,
        50,
       "かばさんの群れ",
        501,
        500,
        "かばさん副将",
        "党費未満",
        15900,
        14,
        94.6
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