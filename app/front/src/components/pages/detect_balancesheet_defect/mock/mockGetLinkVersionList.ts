import LinkBalancesheetVersionDto from "../../../../dto/make_balancesheet_link/linkBalancesheetVersionDto";

/**
 * 収支報告書版Dtoリストを取得する
 * @param startYear 開始年
 * @param endYear 終了年
 * @returns 収支報告書版Dtoリスト
 */
export default function mockGetLinkVersionList(startYear: number, endYear: number): LinkBalancesheetVersionDto[] {
    const list: LinkBalancesheetVersionDto[] = [];

    for (let year = startYear; year <= endYear; year++) {
        const versionDto = createDto(year, 1);
        list.push(versionDto);

        //10の倍数でない年は2回目も存在
        if (year % 10 !== 0) {
            const versionDto = createDto(year, 2);
            list.push(versionDto);
        }
    }

    return list;
}

/**
 * 収支報告書版Droを生成する
 * @param year 設定年
 * @param times 提出回数
 * @returns Dto
 */
function createDto(year: number, times: number) {
    const dto: LinkBalancesheetVersionDto = new LinkBalancesheetVersionDto();

    dto.linkBalancesheetVersionCode = year * 100 + 1;
    dto.linkBalancesheetVersionId = year * 100 + 11;
    dto.offeringYear = year;
    dto.politicalOrgnaizationName = "サンプル政治団体";
    dto.isDataSetFormal = false;

    //2xx0年は公式情報まで丸ごと欠損しているというMockデータ
    if (year % 10 !== 0) {
        dto.offeringTimes = times;
        dto.offeringDate = year + "-11-" + (10 * times);
        dto.electionCommitionName = "選挙管理員会";
        dto.publicationLinkUrl = "https:/ariahihfir.esrjes/" + year;
        //3の倍数の年は非公式データが欠損しているというMockデータ
        if (year % 3 !== 0) {
            dto.isDataSetInformal = true;
        }
        dto.isDataSetFormal = true;
        dto.publicationFormalItemId = year * 200 + 11;
        dto.publicationFormalItemCode = year * 200 + 1;
    }

    return dto;
}