import RenketsuKoufukinWkTblInterface from "../../../../dto/renketsu_koufukin/renketsuKoufukinWkTblDto";
import RenketsuKoufukinWkTblDto from "../../../../dto/renketsu_koufukin/renketsuKoufukinWkTblDto";import WkTblRenketsuKoufukinSelectInterface from "../../../../entity/wkTblRenketsuKoufukinSelectDto";
import WkTblRenketsuKoufukinSelectDto from "../../../../entity/wkTblRenketsuKoufukinSelectDto";

export default function mockGetOutcomeDuplicate(houkokuNen:number):RenketsuKoufukinWkTblInterface{

        const dto:RenketsuKoufukinWkTblInterface = new RenketsuKoufukinWkTblDto();
    
        dto.countAll = 16;
        dto.pageNumber = 0;
        dto.offset = 50;

        dto.listSuccess.splice(0);
        dto.listSuccess.push(createRenlketuRow(45,houkokuNen))
        dto.listSuccess.push(createRenlketuRow(45,houkokuNen))

    return dto;
}

function createRenlketuRow(index: number, houkokuNen: number): WkTblRenketsuKoufukinSelectInterface {
    const dto: WkTblRenketsuKoufukinSelectInterface = new WkTblRenketsuKoufukinSelectDto();

    dto.accrualDateValue = new Date(houkokuNen, 11, 18);

    dto.usageHimoku = "政治活動費";
    dto.usageShishutsuName = "選挙運動費";
    dto.usageUsageItem = "ポスター代金";

    dto.amountKoufukin = index * 11000;
    dto.amountMyFunds = index % 2 * 10000;
    dto.amountAll = dto.amountKoufukin + dto.amountMyFunds;

    dto.payeeName = "政治団体" + String(index);
    dto.payeeAddress = "山梨件架空市";

    return dto;
}
