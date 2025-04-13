import RenketsuKoufukinIncomeInterface from "../../../../dto/renketsu_koufukin/renketsuKoufukinIncomeDto";
import WkTblRenketsuKoufukinSelectInterface from "../../../../entity/wkTblRenketsuKoufukinSelectDto";
import WkTblRenketsuKoufukinSelectDto from "../../../../entity/wkTblRenketsuKoufukinSelectDto";
import WkTblUsageIncomeInterface from "../../../../entity/wkTblUsageIncomeEntity";
import WkTblUsageIncomeEntity from "../../../../entity/wkTblUsageIncomeEntity";

export default function mockGetRenketsuIncome(renketsuKoufukinIncomeDto: RenketsuKoufukinIncomeInterface, houkokuNen: number): RenketsuKoufukinIncomeInterface {


    renketsuKoufukinIncomeDto.listSuccess.splice(0);
    renketsuKoufukinIncomeDto.listSuccess.push(createRenlketuRow(10, houkokuNen));
    renketsuKoufukinIncomeDto.listSuccess.push(createRenlketuRow(11, houkokuNen));
    renketsuKoufukinIncomeDto.listSuccess.push(createRenlketuRow(12, houkokuNen));
    renketsuKoufukinIncomeDto.listSuccess.push(createRenlketuRow(13, houkokuNen));

    renketsuKoufukinIncomeDto.listDuplicate.splice(0);
    renketsuKoufukinIncomeDto.listDuplicate.push(createRenlketuRow(61, houkokuNen));
    renketsuKoufukinIncomeDto.listDuplicate.push(createRenlketuRow(82, houkokuNen));

    renketsuKoufukinIncomeDto.listFailure.splice(0);
    renketsuKoufukinIncomeDto.listFailure.push(createFailureRow(46, houkokuNen));
    renketsuKoufukinIncomeDto.listFailure.push(createFailureRow(88, houkokuNen));

    return renketsuKoufukinIncomeDto;
}


function createRenlketuRow(index: number, houkokuNen: number): WkTblRenketsuKoufukinSelectInterface {
    const dto: WkTblRenketsuKoufukinSelectInterface = new WkTblRenketsuKoufukinSelectDto();

    dto.accrualDateValue = new Date(houkokuNen, 11, 18);

    dto.usageHimoku = "政治活動費";
    dto.usageShishutsuName = "寄付";
    dto.usageUsageItem = "寄付(受け取り)";

    dto.amountKoufukin = index * 11000;
    dto.amountMyFunds = index % 2 * 10000;
    dto.amountAll = dto.amountKoufukin + dto.amountMyFunds;

    dto.payeeName = "政治団体" + String(index);
    dto.payeeAddress = "和歌山県架空市";

    return dto;
}

function createFailureRow(index: number, houkokuNen: number): WkTblUsageIncomeInterface {

    const entity: WkTblUsageIncomeInterface = new WkTblUsageIncomeEntity();

    entity.accrualDateValue = new Date(houkokuNen, 11, 18);

    entity.itemName = "交付金";
    entity.totalAmount = index * 600000;
    entity.amount = index * 500000;

    entity.relationPersonCodeDelegate = 425;
    entity.relationPersonNameDelegate = "不明";

    return entity;
}