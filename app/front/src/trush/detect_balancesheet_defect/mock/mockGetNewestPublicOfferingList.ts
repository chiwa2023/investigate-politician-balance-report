import PublicationInformalDataInterface from "../../../../dto/make_balancesheet_link/publicationInformalDataDto";
import PublicationInformalDataDto from "../../../../dto/make_balancesheet_link/publicationInformalDataDto";
import PublicationFormalItemInterface from "../../../../dto/make_balancesheet_link/publicationFormalItemDto";
import PublicationFormalItemDto from "../../../../dto/make_balancesheet_link/publicationFormalItemDto";

/**
 * 最新年リストを取得する 
 * @param yearStart 開始年
 * @param yearEnd 終了年
 * @returns 最新年リスト
 */
export default function mockGetNewestPublicOfferingList(yearStart: number, yearEnd: number): PublicationFormalItemInterface[] {

    const list: PublicationFormalItemInterface[] = [];
    const max = yearEnd + 1;
    for (let year = yearStart; year < max; year++) {
        list.push(createDto(year, year % 3 + 1));
    }
    return list;
}

/**
 * 公式情報Dtoを生成する
 * @param year 提出年
 * @param times 提出回数
 * @returns 公式情報Dto
 */
function createDto(year: number, times: number): PublicationFormalItemInterface {
    const dto: PublicationFormalItemInterface = new PublicationFormalItemDto();
    if (year % 10 === 0) {
        //dto.offeringYear = year;
        //dto.offeringTimes = times;
        //dto.politicalOrgnaizationName = "政治団体" + times;
        //dto.electionCommitionName = "提出選挙管理委員会" + times;
        //dto.publicLinkUrl = "https://dsjkljfsler/org/" + times;
        //dto.listInformalData = createInformalList(year, times);
        //dto.isImported = year % 2 ? true : false;
        dto.isDataSet = false;
        dto.isClosed = true;
    }
    else {
        dto.offeringYear = year;
        dto.offeringTimes = times;
        dto.politicalOrgnaizationName = "政治団体" + times;
        dto.electionCommitionName = "提出選挙管理委員会" + times;
        dto.publicationLinkUrl = "https://dsjkljfsler/org/" + times;
        dto.listInformalData = createInformalList(year, times);
        dto.publishingDate = year + 1 + "-12-01";
        dto.isImported = year % 2 ? true : false;
        const nowYear = new Date().getFullYear();
        dto.isClosed = nowYear - year > 4 ? true : false;
    }

    return dto;
}

/**
 * 非公式情報リストを生成する
 * @param year 提出年
 * @param times 提出回数
 * @returns 非公式情報リスト
 */
function createInformalList(year: number, times: number): PublicationInformalDataInterface[] {

    const list: PublicationInformalDataInterface[] = [];

    list.push(createDtoInformal(year, times, 1));
    list.push(createDtoInformal(year, times, 2));
    list.push(createDtoInformal(year, times, 3));

    return list;
}

/**
 * 非公式情報Dtoを生成する
 * @param year 提出年
 * @param times 提出回数
 * @param index 適当な番号
 * @returns 非公式情報Dto
 */
function createDtoInformal(year: number, times: number, index: number): PublicationInformalDataInterface {
    const dto: PublicationInformalDataInterface = new PublicationInformalDataDto();
    dto.offeringYear = year;
    dto.offeringTimes = times;
    dto.linkLabel = "市民団体" + index + "サイト";
    dto.linkUrl = "https://werkojdgz.com/" + year + "/" + times;
    return dto;
}