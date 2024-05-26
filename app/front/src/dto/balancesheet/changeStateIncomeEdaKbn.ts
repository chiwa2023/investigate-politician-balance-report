import BalancesheetIncomeDto from ".//balancesheetIncomeDto";
import YoushikiEdaKbnIncomeConstants from "./youshikiEdaKbnIncomeConstants";

/**
 * 収入様式枝区分の値が変更になったら修正
 * @param incomeDto 収支報告書収入データ
 * @returns 収支報告書収入データ
 */
export default function changeStateIncomeEdaKbn(incomeDto:BalancesheetIncomeDto):BalancesheetIncomeDto {
    //枝区分によって特殊な動きをするのは様式区分7の場合のみです
    if (String(incomeDto.youshikiKbn) === "7") {
        switch (String(incomeDto.youshikiEdaKbn)) {
        case YoushikiEdaKbnIncomeConstants.PERSONAL:
            //個人寄付の時、遺贈チェックと寄付金控除チェックをオンにします
            incomeDto.isUseCreditTax = true;
            incomeDto.isUseBequest = true;
            incomeDto.isUsePrimeListedOrForeign = false;
            break;
        case YoushikiEdaKbnIncomeConstants.CORPORATION:
            //法人寄付の時、上場・外資50%チェックをオンにします
            incomeDto.isUseCreditTax = false;
            incomeDto.isUseBequest = false;
            incomeDto.isUsePrimeListedOrForeign = true;
            break;
        case YoushikiEdaKbnIncomeConstants.POLITIC_ORG:
            //政治団体の場合は特殊なInputをすべて消します。
            incomeDto.isUseCreditTax = false;
            incomeDto.isUseBequest = false;
            incomeDto.isUsePrimeListedOrForeign = false;
            break;
        }
    }
    else {
        //それ以外の場合は特殊なInputをすべて消します。
        incomeDto.isUseCreditTax = false;
        incomeDto.isUseBequest = false;
        incomeDto.isUsePrimeListedOrForeign = false;
    }
    return incomeDto;
}
