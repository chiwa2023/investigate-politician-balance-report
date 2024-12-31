import FukisaiTradingInfoInterface from "../../../../dto/fukisai_balancesheet/fukisaiTradingInfoDto";
import FukisaiTradingInfoDto from "../../../../dto/fukisai_balancesheet/fukisaiTradingInfoDto";
import SearchFukisaiBalancesheetResultInterface from "../../../../dto/fukisai_balancesheet/searchFukisaiBalancesheetResultDto";
import SearchFukisaiBalancesheetResultDto from "../../../../dto/fukisai_balancesheet/searchFukisaiBalancesheetResultDto";
import wkTblFukisaiBalancesheetInterface from "../../../../entity/wkTblFukisaiBalancesheetEntity";
import wkTblFukisaiBalancesheetEntity from "../../../../entity/wkTblFukisaiBalancesheetEntity";

export default function mockGetFukisaiMeisaiData(): SearchFukisaiBalancesheetResultInterface {

    const groupDto: SearchFukisaiBalancesheetResultInterface = new SearchFukisaiBalancesheetResultDto();

    groupDto.listTradingGroup.push(createGroupDto(1));
    groupDto.listTradingGroup.push(createGroupDto(2));
    groupDto.listTradingGroup.push(createGroupDto(3));
    groupDto.listTradingGroup.push(createGroupDto(4));
    groupDto.listTradingGroup.push(createGroupDto(5));


    groupDto.countAll = 140;
    groupDto.isOk = true;
    groupDto.message = "データ作成できました";
    groupDto.pageNumber = 0;

    return groupDto;
}


function createGroupDto(index: number): FukisaiTradingInfoInterface {
    const dto: FukisaiTradingInfoInterface = new FukisaiTradingInfoDto();

    dto.politicalOrgName = "ちゃらんぽらん政治団体";
    dto.partnerName = "のんびり政党" + index;

    if (index % 2 === 1) {
        dto.sumIncome = index * 1000 + index * 10;
        dto.sumOutcome = index * 1000 + index * 10;

    } else {
        dto.sumIncome = index * 1000 + index * 10 + index;
        dto.sumOutcome = index * 1000 + index * 10;
    }
    dto.isKingakuDiffer = dto.sumIncome !== dto.sumOutcome;

    // TODO リスト作成

    dto.listMeisai.push(createMeisaiEntity(1));
    dto.listMeisai.push(createMeisaiEntity(2));

    return dto;
}


function createMeisaiEntity(index: number): wkTblFukisaiBalancesheetInterface {
    const meisaiEntity: wkTblFukisaiBalancesheetInterface = new wkTblFukisaiBalancesheetEntity();

    const dantai = "のんびり政党"
    const poliName = "ちゃらんぽらん政治団体"

    if (index % 2 == 1) {

        meisaiEntity.daihyouName = poliName;
        meisaiEntity.politicalOrganizationName = poliName + "(登録)";
        meisaiEntity.accrualDate = "R4/12/0" + index
        meisaiEntity.youshikiKbn = 7;
        meisaiEntity.itemName = "寄付"
        meisaiEntity.kingakuInput = 25341;
        meisaiEntity.kingakuOutput = 0;
        meisaiEntity.partnerName = dantai
        meisaiEntity.relationPoliticalOrgName = dantai + "(登録)";
    } else{
        meisaiEntity.daihyouName = dantai;
        meisaiEntity.politicalOrganizationName = dantai + "(登録)";
        meisaiEntity.accrualDate = "R4/12/0" + index
        meisaiEntity.youshikiKbn = 15;
        meisaiEntity.itemName = "寄付入金"
        meisaiEntity.kingakuInput = 0;
        meisaiEntity.kingakuOutput = 17171;
        meisaiEntity.partnerName = poliName
        meisaiEntity.relationPoliticalOrgName = poliName + "(登録)";
    }


    return meisaiEntity;
}