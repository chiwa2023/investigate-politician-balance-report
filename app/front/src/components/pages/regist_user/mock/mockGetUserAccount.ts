import UserAccountDto from "../../../../dto/user_account/userAccountDto";

export default function mockGetUserAccount(code:string):UserAccountDto{

    const dto = new UserAccountDto();

    //登録されているのはメアドと電話番号
    dto.loginUserMailAddress = code+"_aaa@hogehogehoge.net";
    dto.loginUserTel1 = "090";
    dto.loginUserTel2 = "0090";
    dto.loginUserTel3 = "xxxx";

    return dto;
}