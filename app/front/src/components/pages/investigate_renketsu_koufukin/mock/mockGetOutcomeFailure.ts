import RenketsuKoufukinFailureInterface from "../../../../dto/renketsu_koufukin/renketsuKoufukinFailureDto";
import RenketsuKoufukinFailureDto from "../../../../dto/renketsu_koufukin/renketsuKoufukinFailureDto";
import WkTblUsageOutcomeInterface from "../../../../entity/wkTblUsageOutcomeEntity";
import WkTblUsageOutcomeEntity from "../../../../entity/wkTblUsageOutcomeEntity";

export default function mockGetOutcomeFailure(houkokuNen:number):RenketsuKoufukinFailureInterface{

    const dto:RenketsuKoufukinFailureInterface = new RenketsuKoufukinFailureDto();

    dto.countAll = 66;
    dto.pageNumber = 0;
    dto.offset = 50;
    dto.listFailure.splice(0);
    dto.listFailure.push(createFailureRow(33,houkokuNen));

    return dto;
}

function createFailureRow(index: number, houkokuNen: number): WkTblUsageOutcomeInterface {

    const entity: WkTblUsageOutcomeInterface = new WkTblUsageOutcomeEntity();

    entity.accrualDateValue = new Date(houkokuNen, 11, 18);

    entity.sheetHimoku = "事務所費";
    entity.shishutsuKbnName = "通信";
    entity.usageItem = "事務所内ネットワーク構築";

    entity.amountKoufukin = index * 11000;
    entity.amountMyFunds = index % 2 * 10000;
    entity.amountAll = entity.amountKoufukin + entity.amountMyFunds;

    entity.payeeName = "政治団体" + String(index);
    entity.address = "和歌山県架空市";

    return entity;
}