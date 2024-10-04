import PublicationInformalDataInterface from "../../../../dto/make_balancesheet_link/publicationInformalDataDto";
import PublicationInformalDataDto from "../../../../dto/make_balancesheet_link/publicationInformalDataDto";

/** 
 * mockDataリストを取得する
 * @returns mockリスト
 */
export default function mockGetInformalData(): PublicationInformalDataInterface[] {
    const list: PublicationInformalDataInterface[] = [];

    list.push(createDto(2024, 1));
    list.push(createDto(2025, 1));
    list.push(createDto(2024, 2));
    list.push(createDto(2025, 2));
    list.push(createDto(2024, 3));
    list.push(createDto(2025, 3));
    list.push(createDto(2024, 4));
    list.push(createDto(2025, 4));
    list.push(createDto(2025, 5));

    return list;
}

/** 
 * mockDataリストを取得する
 * @param year 提出年
 * @param times 提出回数
 * @returns MockDto
 */
function createDto(year: number, times: number): PublicationInformalDataInterface {

    const dto:PublicationInformalDataInterface = new PublicationInformalDataDto();

    dto.publicationInformalId = year*3+times;
    dto.publicationInformalCode = year*2+times*3;

    dto.offeringYear = year;

    dto.linkLabel = "サイト" + year  + times;
    dto.offeringTimes = times;
    dto.offeringDate = year + "-0" + times + "-12";

    dto.linkUrl = "http://inform.site/site" + year  + times;
    return dto;
}