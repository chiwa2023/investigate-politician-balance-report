import InputAddressDto from "../../../../dto/Input_address/inputAddressDto";
import type CorpNoInterface from "../../../../dto/partner_corp/corpNoDto";
import CorpNoDto from "../../../../dto/partner_corp/corpNoDto";

export default function mockGetCorpList(): CorpNoInterface[] {
    const list: CorpNoInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(3));
    list.push(createDto(5));
    list.push(createDto(7));

    return list;
}

function createDto(index: number): CorpNoInterface {
    const dto: CorpNoInterface = new CorpNoDto();

    dto.houjinNo = "1234" + index;
    dto.corpNo = "1234" + index + "-abcde";

    dto.corpName = "ABCD企業" + index;
    dto.corpNameKana = "えーびーしーでぃーきぎょう" + index;

    dto.isShiten = false;
    const addressDto: InputAddressDto = new InputAddressDto();
    addressDto.addressPostal = "宮崎県架空市実在町" + index;
    addressDto.addressBlock = "6丁目" + index;
    addressDto.addressBuilding = "五角ビル" + index + "階";
    addressDto.postalcode1 = "12" + index;
    addressDto.postalcode2 = "345" + index;
    addressDto.addressAll = addressDto.addressPostal + addressDto.addressBlock + addressDto.addressBuilding;
    dto.inputAddress = addressDto;

    addressDto.tel1 = "11" +index;
    addressDto.tel2 = "22" +index;
    addressDto.tel3 = "333" +index;

    addressDto.lgCode = "1" +index;
    addressDto.blkId = "3" +index;
    addressDto.rsdtId = "4" +index;
    addressDto.machiazaId = "2" +index;

    dto.orgDelegateCode = "39-1244" + index;
    dto.orgDelegate = "代表者　太郎" + index;

    return dto;
}