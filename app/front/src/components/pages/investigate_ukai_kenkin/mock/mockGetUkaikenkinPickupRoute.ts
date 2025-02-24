import UkaiKenkinPickupRouteResultInterface from "../../../../dto/ukai_kenkin/ukaiKenkinPickupRouteResultDto";
import UkaiKenkinPickupRouteResultDto from "../../../../dto/ukai_kenkin/ukaiKenkinPickupRouteResultDto";
import WkTblUkaiKenkinPickupRouteInterface from "../../../../entity/wkTblUkaiKenkinPickupRouteEntity";
import WkTblUkaiKenkinPickupRouteDto from "../../../../entity/wkTblUkaiKenkinPickupRouteEntity";

export default function mockGetUkaikenkinPickupRoute(offset: number): UkaiKenkinPickupRouteResultInterface {

    const dto: UkaiKenkinPickupRouteResultInterface = new UkaiKenkinPickupRouteResultDto()
    dto.countAll = 7;
    dto.limit = 1;
    dto.offset = offset;

    dto.listFirstRouteEntity.push(createEntity(1 + offset,"ABCD団体","中間団体1",0,7));
    dto.listFirstRouteEntity.push(createEntity(1 + offset,"関連団体","ちゃらんぽらん団体1",0,3));
    dto.listPickupRouteEntity.push(createEntitySub(3 + offset,1,7));
    dto.listPickupRouteEntity.push(createEntitySub(4 + offset,2,7));
    dto.listPickupRouteEntity.push(createEntitySub(5 + offset,3,7));
    dto.listLastRouteEntity.push(createEntity(2 + offset,"中間団体6","関連団体E",4,7));
    dto.listLastRouteEntity.push(createEntity(2 + offset,"ちゃらんぽらん団体","関連団体F",4,3));

    return dto;
}

function createEntitySub(index: number,stage:number,route:number): WkTblUkaiKenkinPickupRouteInterface {

    const entity: WkTblUkaiKenkinPickupRouteInterface = new WkTblUkaiKenkinPickupRouteDto();

    // 報告書記載政治団体
    entity.politicalOrgId = index * 511;
    entity.politicalOrgCode = index * 510;
    entity.politicalOrgName = "中間団体" + index;
    entity.wkTblUkaiKenkinPickupRouteCode = route;

    // 項目
    entity.accrualDate = "R4.12.5";
    entity.accrualDateValue = new Date(2022, 12, 5);
    entity.itemName = "寄付受取り";
    entity.youshikiKbn = 7;
    entity.youshikiEdaKbn = 3;
    entity.kingaku = 450000;
    entity.pickupStage = stage;
    entity.renban = 41;

    // 取引相手
    entity.tradingPartnerId = index * 611;
    entity.tradingPartnerCode = index * 610;
    entity.tradingPartnerName = "中間団体" + (index+1);
    entity.tradingPartnerAddress = "和歌山県架空市";

    return entity;
}

function createEntity(index: number,poliOrg:string,tradinPartner:string,stage:number,route:number): WkTblUkaiKenkinPickupRouteInterface {

    const entity: WkTblUkaiKenkinPickupRouteInterface = new WkTblUkaiKenkinPickupRouteDto();

    // 報告書記載政治団体
    entity.politicalOrgId = index * 511;
    entity.politicalOrgCode = index * 510;
    entity.politicalOrgName = poliOrg ;
    entity.wkTblUkaiKenkinPickupRouteCode = route;

    // 項目
    entity.accrualDate = "R4.12.5";
    entity.accrualDateValue = new Date(2022, 12, 5);
    entity.itemName = "寄付受取り";
    entity.youshikiKbn = 7;
    entity.youshikiEdaKbn = 3;
    entity.kingaku = 450000;
    entity.pickupStage = stage;
    entity.renban = 41;

    // 取引相手
    entity.tradingPartnerId = index * 611;
    entity.tradingPartnerCode = index * 610;
    entity.tradingPartnerName = tradinPartner;
    entity.tradingPartnerAddress = "和歌山県架空市";


    // 記載団体側関連者
    entity.poliOrgRelationPersonId = index * 411;
    entity.poliOrgRelationPersonCode = index * 410;
    entity.poliOrgRelationPersonName = "会計責任者　一郎" + index;

    // 取引相手関連者
    entity.tradingRelationPersonId = index * 711;
    entity.tradingRelationPersonCode = index * 710;
    entity.tradingRelationPersonName = "代表者　太郎" + index;

    entity.poliOrgRelationPersonYakuari = "会計責任者";
    entity.tradingRelationPersonYakuari = "代表者";

    return entity;
}