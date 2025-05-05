import InputAddressDto from "../../../../dto/Input_address/inputAddressDto";
import InputPersonNameInterface from "../../../../dto/input_person_name/inputPersonNameDto";
import InputPersonNameDto from "../../../../dto/input_person_name/inputPersonNameDto";
import PersonNoInterface from "../../../../dto/partner_person/personNoDto";
import PersonNoDto from "../../../../dto/partner_person/personNoDto";

export default function mockGetPersonList(): PersonNoInterface[] {

    const list: PersonNoInterface[] = [];

    list.push(createDto(1));
    list.push(createDto(2));
    list.push(createDto(3));
    list.push(createDto(4));
    list.push(createDto(5));

    return list;
}


function createDto(index: number): PersonNoInterface {
    const dto: PersonNoInterface = new PersonNoDto();
    dto.personNo = "11-333" + index;
    dto.nameAll = "迂回　献金太郎" + index;
    dto.juushoAll = "山梨県架空市湖畔町" + index;
    dto.shokugyou = "建設業従事者・職員" + index;

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

    const nameDto: InputPersonNameInterface = new InputPersonNameDto();

    nameDto.lastName = "迂回";
    nameDto.middleName = "ミカエル";
    nameDto.firstName = "献金太郎";

    nameDto.lastNameKana = "うかい";
    nameDto.middleNameKana = "みかえる";
    nameDto.firstNameKana = "けんきんたろう";

    nameDto.allNameKana = nameDto.lastNameKana + "　" + nameDto.middleNameKana + nameDto.firstNameKana;
    nameDto.allName = nameDto.lastName + "　" + nameDto.middleName + nameDto.firstName;

    dto.inputName = nameDto;

    dto.gyoushu = "建設";
    dto.yakushoku = "一般職員";
    dto.shokugyouUserWrite = "申告職業" + index;

    return dto;
}