import InputAddressDto from "../../../../dto/Input_address/inputAddressDto";
import type PoliOrgNoInterface from "../../../../dto/partner_poli_org/poliOrgNoDto";
import PoliOrgNoDto from "../../../../dto/partner_poli_org/poliOrgNoDto";

export default function mockGetPoliOrgNoList(): PoliOrgNoInterface[] {

    const list: PoliOrgNoInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(2));
    list.push(createDto(3));
    list.push(createDto(4));
    list.push(createDto(5));

    return list;
}

function createDto(index: number): PoliOrgNoInterface {

    const dto: PoliOrgNoInterface = new PoliOrgNoDto();
    dto.poliOrgNo = "124-3455" + index;

    dto.inputName.orgName = "ちゃらんぽらん政治団体" + index;
    dto.inputName.orgNameKana = "ちゃらんぽらんせいじだんたい" + index;

    dto.delegateNo = "980987" + index;
    dto.delegateName = "代表者　太郎" + index;

    const addressDto: InputAddressDto = new InputAddressDto();
    addressDto.addressPostal = "和歌山県実在市" + index;
    addressDto.addressBlock = "山麓町" + index;
    addressDto.addressBuilding = "四角ビル" + index;
    addressDto.addressAll = addressDto.addressPostal + addressDto.addressBlock + addressDto.addressBuilding;
    addressDto.tel1 = "11" + index;
    addressDto.tel2 = "22" + index;
    addressDto.tel3 = "333" + index;

    addressDto.postalcode1 = "56" + index;
    addressDto.postalcode2 = "789" + index;

    addressDto.lgCode = "1" + index;
    addressDto.blkId = "3" + index;
    addressDto.rsdtId = "4" + index;
    addressDto.machiazaId = "2" + index;

    dto.inputAddress = addressDto;

    return dto;
}
