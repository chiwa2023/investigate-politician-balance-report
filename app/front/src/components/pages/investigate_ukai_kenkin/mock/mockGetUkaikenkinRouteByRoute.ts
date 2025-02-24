import WkTblUkaiKenkinPickupRouteInterface from "../../../../entity/wkTblUkaiKenkinPickupRouteEntity";
import WkTblUkaiKenkinPickupRouteEntity from "../../../../entity/wkTblUkaiKenkinPickupRouteEntity";

export default function mockGetUkaikenkinRouteByRoute(offset: number): WkTblUkaiKenkinPickupRouteInterface[] {

    const list:WkTblUkaiKenkinPickupRouteInterface[] = [];
    list.push(createEntity(1 + offset,"ABCD団体","中間団体1",0,offset));
    list.push(createEntity(1 + offset,"中間団体1","中間団体2",1,offset));
    list.push(createEntity(1 + offset,"中間団体2","中間団体3",2,offset));
    list.push(createEntity(1 + offset,"中間団体3","中間団体4",3,offset));
    list.push(createEntity(1 + offset,"中間団体4","関連団体E",4,offset));
    
    return list;
}

function createEntity(index: number,poliOrg:string,tradinPartner:string,stage:number,route:number): WkTblUkaiKenkinPickupRouteInterface {

    const entity: WkTblUkaiKenkinPickupRouteInterface = new WkTblUkaiKenkinPickupRouteEntity();

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