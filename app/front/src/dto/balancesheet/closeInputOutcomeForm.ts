import BalancesheetOutcomeDto from "./balancesheetOutcomeDto";

/**
 * 支出の収支報告しないので不要な入力フォームを閉じます
 * @param outcomeDto 収支報告書支出データ
 */
export default function closeInputOutcomeForm(outcomeDto:BalancesheetOutcomeDto) {
    //様式区分を非表示にします
    outcomeDto.isUseYoushikiKbn = false;
    //様式区分枝項目を非表示にします
    outcomeDto.isUseYoushikiEdaKbn = false;
    //団体名称を非表示にします
    outcomeDto.isUseOrgName = false;
    //項目名を非表示にします
    outcomeDto.isUseItemName = false;
    //団体住所を非表示にします
    outcomeDto.isUseAddress = false;
    //備考を非表示にします
    outcomeDto.isUseBiko = false;
    //寄付金控除を非表示にします
    outcomeDto.isUseCollectRecipt = false;
    outcomeDto.isUseRelatedGrants = false;
    //パーティ名称は非表示にします
    outcomeDto.isUseCategorizedGroup = false;
    //パーティ日付を非表示にします
    outcomeDto.isUseItemName = false;
}
