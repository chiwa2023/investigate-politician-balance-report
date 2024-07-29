export default function mockPasswordComplexStatus(password: string): number {

    let rank:number =1;     

    //TODO Back側にアクセスしてチェックする
    if (password.length < 4) {
        return rank;
    }

    const ratz = /[a-z]/;
    const rAtZ = /[A-Z]/;
    const r0t9 = /[0-9]/;
    const rChar = /[!-/:-@[-`{-~]/

    if(ratz.test(password)){
        rank = rank +1;
    }
    if(rAtZ.test(password)){
        rank = rank +1;
    }
    if(r0t9.test(password)){
        rank = rank +1;
    }
    if(rChar.test(password)){
        rank = rank +1;
    }

    return rank;
}