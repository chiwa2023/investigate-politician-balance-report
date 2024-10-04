import NotRegistOrganizaionDto from "../../../../dto/start_manage_political_organization/notRegistOrganizaionDto";

export default function mockGetNoteRegitOrganizationList(): NotRegistOrganizaionDto[] {

    const list: NotRegistOrganizaionDto[] = [];

    list.push(getDto(1));
    list.push(getDto(2));

    return list;
}

function getDto(index: number): NotRegistOrganizaionDto {
    const dto1: NotRegistOrganizaionDto = new NotRegistOrganizaionDto();

    dto1.notRegistOrganizaionId = index * 30;
    dto1.notRegistOrganizaionCode = index;
    dto1.politicalOrganizationName = "未登録団体" + index;
    dto1.electionCommissionLeastDto.electionCommissionId = index * 100;
    dto1.electionCommissionLeastDto.electionCommissionCode = index * 150;
    dto1.electionCommissionLeastDto.electionCommissionName = "実在県選挙管理委員会";

    dto1.saveStorageResultDto.shoshouId ="aaa/bbb/ccc/test.xml";
    dto1.saveStorageResultDto.shoshouCode = index;

    return dto1;
}