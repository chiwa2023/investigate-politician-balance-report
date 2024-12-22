import ZenginOrgChangeBranchInterface from "../../../../entity/zenginOrgChangeBranchEntity";
import ZenginOrgChangeBranchDto from "../../../../entity/zenginOrgChangeBranchEntity";

export default function mockGetZenginChangeList(): ZenginOrgChangeBranchInterface[] {

    const list: ZenginOrgChangeBranchInterface[] = [];

    list.push(createEntity(1));
    list.push(createEntity(2));
    list.push(createEntity(3));
    list.push(createEntity(4));
    list.push(createEntity(5));
    list.push(createEntity(6));
    list.push(createEntity(7));
    list.push(createEntity(8));
    list.push(createEntity(9));

    return list;
}

function createEntity(index: number) {

    const entity: ZenginOrgChangeBranchInterface = new ZenginOrgChangeBranchDto();
    entity.zenginOrgChangeBranchId = index;
    entity.orgCode = String(index * 100);
    entity.orgName = String(index * 100) + "銀行";
    entity.branchCode = String(index * 30);
    entity.branchName = String(index * 30) + "支店";

    if (index % 2 === 1) {
        entity.isFinished = true;
    } else {
        entity.isFinished = false;
    }

    switch (index % 3) {
        case 1:
            entity.changeKbn = 1;
            entity.changeKbnText = "追加";
            break;
        case 2:
            entity.changeKbn = 2;
            entity.changeKbnText = "廃止";
            break;
        case 0:
            entity.changeKbn = 1;
            entity.changeKbnText = "移転";

            break;
    }

    entity.postalCode = "123-xx";
    entity.branchAddress = "和歌山県架空市実在町";
    entity.phonNumber = "000-111x-yyyy";

    return entity;
}