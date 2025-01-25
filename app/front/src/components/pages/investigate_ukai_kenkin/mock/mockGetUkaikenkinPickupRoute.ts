import UkaiKenkinPickupRouteResultInterface from "../../../../dto/ukai_kenkin/ukaiKenkinPickupRouteResultDto";
import UkaiKenkinPickupRouteResultDto from "../../../../dto/ukai_kenkin/ukaiKenkinPickupRouteResultDto";
import WkTblUkaiKenkinPickupRouteInterface from "../../../../entity/wkTblUkaiKenkinPickupRouteEntity";
import WkTblUkaiKenkinPickupRouteDto from "../../../../entity/wkTblUkaiKenkinPickupRouteEntity";

export default function mockGetUkaikenkinPickupRoute(offset: number): UkaiKenkinPickupRouteResultInterface {

    const dto: UkaiKenkinPickupRouteResultInterface = new UkaiKenkinPickupRouteResultDto()
    dto.countAll = 7;
    dto.limit = 1;
    dto.offset = offset;

    dto.listFirstRouteEntity.push(createEntity(1 + offset));
    dto.listPickupRouteEntity.push(createEntitySub(3 + offset));
    dto.listPickupRouteEntity.push(createEntitySub(4 + offset));
    dto.listPickupRouteEntity.push(createEntitySub(5 + offset));
    dto.listLastRouteEntity.push(createEntity(2 + offset));

    return dto;
}

function createEntitySub(index: number): WkTblUkaiKenkinPickupRouteInterface {

    const entity: WkTblUkaiKenkinPickupRouteInterface = new WkTblUkaiKenkinPickupRouteDto();

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

    return entity;
}

function createEntity(index: number): WkTblUkaiKenkinPickupRouteInterface {

    const entity: WkTblUkaiKenkinPickupRouteInterface = new WkTblUkaiKenkinPickupRouteDto();

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