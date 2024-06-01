import PublicationFormalItemInterface from "../../../../dto/make_balancesheet_link/publicationFormalItemDto";
import PublicationFormalItemDto from "../../../../dto/make_balancesheet_link/publicationFormalItemDto";

/**
 * 収支報告書公開リンクのMockデータを生成する
 * @returns リンクリスト
 */
export default function mockGetPublicLink(): PublicationFormalItemInterface[] {

    const list: PublicationFormalItemInterface[] = [];

    list.push(createDto(2024,1));
    list.push(createDto(2025,1));
    list.push(createDto(2024,2));
    list.push(createDto(2025,2));
    list.push(createDto(2024,3));
    list.push(createDto(2024,4));
    list.push(createDto(2025,3));
    list.push(createDto(2025,4));

    return list;
}

/**
 * 収支報告書公開リンクDtoを生成する
 * @param year 提出年
 * @param times 提出回数
 * @returns Dto
 */
function createDto(year:number,times: number): PublicationFormalItemInterface {
    const dto: PublicationFormalItemInterface = new PublicationFormalItemDto();

    const index = year*2 + times;

    dto.publicationFormalItemId = year*100+10+times;
    dto.publicationFormalItemCode = year*100+times;

    dto.offeringYear = year;
    dto.offeringTimes = times;
    dto.offeringDate = year + "-0" + times + "-12";
    dto.electionCommitionCode = year*2+times;
    dto.electionCommitionName = "選挙管理委員会"+index;
    dto.publicationLinkUrl = "https://balancesheet/store" + index;
    dto.publishingDate = "2022/12/01";

    dto.politicalOrgnaizationId = 33;
    dto.politicalOrgnaizationCode = 30;
    dto.politicalOrgnaizationName = "サンプル政治団体3";

    return dto;
}