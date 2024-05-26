import BalancesheetIncomeDto from "./balancesheetIncomeDto";

/**
 * 収入収支報告しないので不要な入力フォームを閉じます
 * @param incomeDto 収入データDto
 */
export default function closeInputIncomeForm(incomeDto:BalancesheetIncomeDto) {
    //様式区分を非表示にします
    incomeDto.isUseYoushikiKbn = false;
    //様式区分枝項目を非表示にします
    incomeDto.isUseYoushikiEdaKbn = false;
    //あっせん期間部分を非表示にします
    incomeDto.isUseMediation = false;
    //団体名称を非表示にします
    incomeDto.isUseOrgName = false;
    //項目名を非表示にします
    incomeDto.isUseItemName = false;
    //団体住所を非表示にします
    incomeDto.isUseAddress = false;
    //備考を非表示にします
    incomeDto.isUseBiko = false;
    //寄付金控除を非表示にします
    incomeDto.isUseCreditTax = false;
    //パーティ名称は非表示にします
    incomeDto.isUsePartyName = false;
    //パーティ日付を非表示にします
    incomeDto.isUsePartyDate = false;
}
