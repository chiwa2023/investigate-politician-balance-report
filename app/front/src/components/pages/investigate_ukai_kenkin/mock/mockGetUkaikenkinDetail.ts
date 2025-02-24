import UkaiKenkinDetaillResultInterface from "../../../../dto/ukai_kenkin/ukaiKenkinDetaillResultDto";
import UkaiKenkinDetaillResultDto from "../../../../dto/ukai_kenkin/ukaiKenkinDetaillResultDto";
import WkTblUkaiKenkinInterface from "../../../../entity/wkTblUkaiKenkinEntity";
import WkTblUkaiKenkinEntity from "../../../../entity/wkTblUkaiKenkinEntity";

export default function mockGetUkaikenkinDetail(offset: number): UkaiKenkinDetaillResultInterface {

    const dto: UkaiKenkinDetaillResultInterface = new UkaiKenkinDetaillResultDto();
    dto.countAll = 380;
    dto.limit = 50;
    dto.offset = offset;
    dto.listDetailEntity.push(createEntity(1 + offset));
    dto.listDetailEntity.push(createEntity(2 + offset));
    dto.listDetailEntity.push(createEntity(3 + offset));

    return dto;
}

function createEntity(index: number): WkTblUkaiKenkinInterface {

    const entity: WkTblUkaiKenkinInterface = new WkTblUkaiKenkinEntity();

    entity.wkTblUkaiKenkinEntityId = index * 21;
    entity.wkTblUkaiKenkinEntityCode = index * 20;


    entity.poliOrgDelegateId = index * 110;
    entity.poliOrgDelegateCode = index * 100;
    entity.poliOrgDelegateName = "もらう代表者姓名" + index;

    entity.poliOrgAccountManagerId = index * 210;
    entity.poliOrgAccountManagerCode = index * 200;
    entity.poliOrgAccountManagerName = "もらう会計責任者姓名" + index;

    entity.poliOrgShikinDantaiId = index * 310;
    entity.poliOrgShikinDantaiCode = index * 300;
    entity.poliOrgShikinDantaiName = "もらう資金管理団体責任者" + index;

    entity.poliOrgKokkaiGiin1Id = index * 410;
    entity.poliOrgKokkaiGiin1Code = index * 400;
    entity.poliOrgKokkaiGiin1Name = "もらう国会議員1-" + index;

    entity.poliOrgKokkaiGiin2Id = index * 410;
    entity.poliOrgKokkaiGiin2Code = index * 400;
    entity.poliOrgKokkaiGiin2Name = "もらう国会議員2-" + index;

    // 国会議員3は入力なし
    //entity.poliOrgKokkaiGiin3Id= index*410;
    //entity.poliOrgKokkaiGiin3Code = index*400;
    //entity.poliOrgKokkaiGiin3Name = "もらう国会議員3-"+index;



    // 報告書記載政治団体
    entity.politicalOrgId = index * 511;
    entity.politicalOrgCode = index * 510;
    entity.politicalOrgName = "もらう政治団体" + index;

    // 項目
    entity.accrualDate = "R4.12.5";
    entity.accrualDateValue = new Date(2022, 12, 5);
    entity.itemName = "寄付受け取り";
    entity.youshikiKbn = 7;
    entity.youshikiEdaKbn = 3;
    entity.kingaku = 450000;
    entity.pickupStage = 3;
    entity.renban = 41;

    // 取引相手
    entity.tradingPartnerId = index * 611;
    entity.tradingPartnerCode = index * 610;
    entity.tradingPartnerName = "あげる団体" + index;
    entity.tradingPartnerAddress = "和歌山県架空市";

    //取引相手団体代表者
    entity.tradingPartnerDelegateId = index * 711;
    entity.tradingPartnerDelegateCode = index * 710;
    entity.tradingPartnerDelegateName = "あげる代表者" + index;

    // 取引団体会計責任者
    entity.tradingOrgAccountManagerId = index * 810;
    entity.tradingOrgAccountManagerCode = index * 811;
    entity.tradingOrgAccountManagerName = "あげる会計責任者" + index;

    // 取引団体資金管理団体責任者
    entity.tradingOrgShikinDantaiId = index * 911;
    entity.tradingOrgShikinDantaiCode = index * 910;
    entity.tradingOrgShikinDantaiName = "あげる資金団体責任者" + index;

    // 取引団体団体関連国会議員1
    entity.tradingOrgKokkaiGiin1Id = index * 1011;
    entity.tradingOrgKokkaiGiin1Code = index * 1010;
    entity.tradingOrgKokkaiGiin1Name = "あげる国会議員1-" + index;

    // 取引団体団体関連国会議員2
    //entity.tradingOrgKokkaiGiin2Id =  index*1111;
    //entity.tradingOrgKokkaiGiin2Code =  index*1110;
    //entity.tradingOrgKokkaiGiin2Name = "あげる国会議員2-" + index;

    // 取引団体団体関連国会議員3本来は飛び番はしないが表示テスト用
    entity.tradingOrgKokkaiGiin3Id = index * 1211;
    entity.tradingOrgKokkaiGiin3Code = index * 1210;
    entity.tradingOrgKokkaiGiin3Name = "あげる国会議員3-" + index;


    return entity;
}