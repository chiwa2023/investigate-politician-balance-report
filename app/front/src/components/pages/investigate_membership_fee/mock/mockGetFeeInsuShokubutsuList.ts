import PoliOrgFeeInsuDto from "../../../../dto/membership_fee/poliOrgFeeInsuDto";

export default function mockGetFeeInsuShokubutsuList(): PoliOrgFeeInsuDto[] {

    const list: PoliOrgFeeInsuDto[] = [];

    list.push(createDto(
        11,
        10,
       "鉢植え２つ",
        101,
        100,
        "バラさん大将",
        "党費一致",
        62400,
        78,
        800
     ));

     list.push(createDto(
        21,
        20,
       "花束４つ",
        201,
        200,
        "カトレアさん先鋒",
        "党費一致",
        35200,
        44,
        800
     ));

     list.push(createDto(
        31,
        30,
       "河原全体",
        301,
        300,
        "たんぽぽさん次鋒",
        "党費一致",
        67200,
        84,
        800
     ));

     list.push(createDto(
        41,
        40,
       "プランター６個",
        401,
        400,
        "すみれちゃん中堅",
        "党費一致",
        30400,
        38,
        800
     ));

     list.push(createDto(
        51,
        50,
       "湿地帯3か所",
        501,
        500,
        "白百合さん副将",
        "党費一致",
        42400,
        53,
        800
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