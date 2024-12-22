import ZenginFinancialOrgInterface from "../../../../dto/financial/zenginFinancialOrg";
import ZenginFinancialOrgDto from "../../../../dto/financial/zenginFinancialOrg";

/**
 * 各種PayのMockデータ配列を取得する
 * @returns 生成配列
 */
export default function mockGetZenginFinancialOrg():ZenginFinancialOrgInterface[]{

    const list:ZenginFinancialOrgInterface[] = [];

    list.push(createDto(1,"全銀金融機関1"));
    list.push(createDto(2,"全銀金融機関2"));
    list.push(createDto(3,"全銀金融機関3"));
    list.push(createDto(4,"全銀金融機関4"));

    return list;
}

/**
 * 各種Pay項目を生成する
 * @param id id
 * @param name 名称
 * @returns 各種PayDto
 */
function createDto(id:number,name:string):ZenginFinancialOrgInterface{

    const dto:ZenginFinancialOrgInterface = new ZenginFinancialOrgDto();
    dto.zenginFinancialOrgId = id;
    dto.zenginFinancialOrgCode = id;
    dto.zenginFinancialOrgName = name;

    return dto;
}