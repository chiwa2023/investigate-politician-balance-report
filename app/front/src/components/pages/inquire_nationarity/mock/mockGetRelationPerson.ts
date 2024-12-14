import getRandomString from "../../../../dto/getRandomString";
import getUnixTimeString from "../../../../dto/getUnixTimeString";
import PoliticalOrganizationLeastInterface from "../../../../dto/political_organization/politicalOrganizationLeastDto";
import RelationPersonInterface from "../../../../dto/relation/relationPersonDto";
import RelationPersonDto from "../../../../dto/relation/relationPersonDto";
import { ThisApplication } from "../../../../dto/thisApplication";


export default function mockGetRelationPerson(sendDto: PoliticalOrganizationLeastInterface): RelationPersonInterface[] {

    const list: RelationPersonInterface[] = [];

    list.push(createDtoNotRelation(sendDto,1));
    list.push(createDtoNotRelation(sendDto,2));
    list.push(createDtoNotRelation(sendDto,31));

    list.push(createDto(sendDto,11));
    list.push(createDto(sendDto,12));
    list.push(createDto(sendDto,13));
    list.push(createDto(sendDto,14));
    list.push(createDto(sendDto,15));
    list.push(createDto(sendDto,16));
    list.push(createDto(sendDto,17));

    return list
}

function createDtoNotRelation(sendDto: PoliticalOrganizationLeastInterface, num: number): RelationPersonInterface {

    const dto: RelationPersonInterface = new RelationPersonDto();

    const INIT_NUMBER: number = 0;
    const INIT_STRING: string = "";

    dto.inquireId = getUnixTimeString() + "-" + getRandomString();
    dto.nationalityAnswerKbn = INIT_STRING; // 回答で使用
    dto.nationalityAnswerKbnText = INIT_STRING; // 回答で使用

    dto.applicationComonCode = ThisApplication.code;
    dto.applicationName = ThisApplication.name;

    dto.relationPersonId = INIT_NUMBER; // 未設定
    dto.relationPersonCode = INIT_NUMBER; // 未設定
    dto.politicalOrganizationId = sendDto.politicalOrganizationId;
    dto.politicalOrganizationCode = sendDto.politicalOrganizationCode;
    dto.politicalOrganizationName = sendDto.politicalOrganizationName;
    dto.isSearch = false;

    dto.nameAll = "寄付者　太郎" + num // 名前、という単項目でしか入っていない
    dto.firstName = INIT_STRING; // 未設定
    dto.firstName = INIT_STRING; // 未設定
    dto.firstNameKana = INIT_STRING; // 未設定
    dto.lastName = INIT_STRING; // 未設定
    dto.lastNameKana = INIT_STRING; // 未設定
    dto.middleName = INIT_STRING; // 未設定
    dto.middleNameKana = INIT_STRING; // 未設定
    dto.tel1 = INIT_STRING; // 未設定
    dto.tel2 = INIT_STRING; // 未設定
    dto.tel3 = INIT_STRING; // 未設定
    dto.addressAll = "大阪府架空市実在町" // 住所、という単項目でしか入っていない
    dto.postalcode1 = INIT_STRING; // 未設定
    dto.postalcode2 = INIT_STRING; // 未設定
    dto.addressPostal = INIT_STRING; // 未設定
    dto.addressBlock = INIT_STRING; // 未設定
    dto.addressBuilding = INIT_STRING; // 未設定
    // コード設定時に全住所が変更になるので問い合わせ時検証用として絶対変更しないデータを残す
    dto.orginAddressAll = "大阪府架空市実在町" 
    
    dto.lgCode = INIT_STRING; // 未設定
    dto.machiazaId = INIT_STRING; // 未設定
    dto.blkId = INIT_STRING; // 未設定
    dto.rsdtId = INIT_STRING; // 未設定
    dto.kanrenshaCode = INIT_STRING; // 未設定

    return dto;
}

function createDto(sendDto: PoliticalOrganizationLeastInterface, num: number): RelationPersonInterface {

    const dto: RelationPersonInterface = new RelationPersonDto();

    const INIT_STRING: string = "";

    dto.inquireId = getUnixTimeString() + "-" + getRandomString();
    dto.nationalityAnswerKbn = INIT_STRING; // 回答で使用
    dto.nationalityAnswerKbnText = INIT_STRING; // 回答で使用

    dto.applicationComonCode = ThisApplication.code;
    dto.applicationName = ThisApplication.name;

    dto.relationPersonId = num;
    dto.relationPersonCode = num + 24;
    dto.politicalOrganizationId = sendDto.politicalOrganizationId;
    dto.politicalOrganizationCode = sendDto.politicalOrganizationCode;
    dto.politicalOrganizationName = sendDto.politicalOrganizationName;
    dto.isSearch = false;

    dto.firstName = "会計責任者";
    dto.firstNameKana = "かいけいせきにんしゃ";
    dto.lastName = "正夫" + num;
    dto.lastNameKana = "まさお";
    dto.middleName = "ジョージ";
    dto.middleNameKana = "じょーじ";
    dto.tel1 = "000";
    dto.tel2 = "111";
    dto.tel3 = "xxxx";
    dto.postalcode1 = "012";
    dto.postalcode2 = "34yy";
    dto.addressPostal = "東京都千代田区霞が関";
    dto.addressBlock = "９９９番地";
    dto.addressBuilding = "三角ビル";

    // 表示用に結合
    dto.nameAll = dto.firstName + "　" + dto.middleName + dto.lastName;
    dto.addressAll = dto.addressPostal + dto.addressBlock + "　" + dto.addressBuilding;
    dto.orginAddressAll = dto.addressPostal + dto.addressBlock + "　" + dto.addressBuilding;

    dto.lgCode = "123456";
    dto.machiazaId = "1234567";
    dto.blkId = "123";
    dto.rsdtId = "123";
    // 共通APIが運営されている前提(本格運営柱ならこの項目しか不要、あとは軽く整合性を見るくらい)
    dto.kanrenshaCode = "aaa-bbb-ccc"; 

    return dto;
}