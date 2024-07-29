import PersonManagerInterface from "../../../../dto/person_manager/personManager";
import PersonManagerDto from "../../../../dto/person_manager/personManager";

export default function mockGetUserInfo(loginUserId : number):PersonManagerInterface{

    //現段階で保存されているのはメアドと電話番号で、その他は空白
    const personManagerDto:PersonManagerInterface = new PersonManagerDto();

    personManagerDto.mailAddress = loginUserId + "@hogehoge.net";
    personManagerDto.tel1 = "090";
    personManagerDto.tel2 = "0090";
    personManagerDto.tel3 = "xxxx";

    return personManagerDto;
}