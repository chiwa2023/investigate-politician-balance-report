import AuditOpinionIncomeInterface from "../audit_opinion/auditOpinionIncomeDto";
import changeStateIncomeEdaKbn from "./changeStateIncomeEdaKbn";

/**
 * 収入様式区分の値が変更になったら修正する
 * @param incomeDto 収支報告書収入データ
 * @returns 収支報告書収入データ
 */
export default function changeIncomeYoushikiKbnState(incomeDto:AuditOpinionIncomeInterface):AuditOpinionIncomeInterface {

    //大前提として大項目は使用します
    incomeDto.isUseYoushikiKbn = true;

    //大前提として備考は使用します
    incomeDto.isUseBiko = true;

    switch (String(incomeDto.youshikiKbn)) {
    case "3":
        //枝区分は不要です
        incomeDto.isUseYoushikiEdaKbn = false;
        //団体名称は不要です
        incomeDto.isUseOrgName = false;
        //団体住所は不要です
        incomeDto.isUseAddress = false;
        //項目は必要です
        incomeDto.isUseItemName = true;
        incomeDto.attentionItemName = "事業の種類";
        //寄付金控除は不要です
        incomeDto.isUseCreditTax = false;
        //パーティ名称は不要です
        incomeDto.isUsePartyName = false;
        //パーティ日付は不要です
        incomeDto.isUsePartyDate = false;
        //あっせん項目は不要です
        incomeDto.isUseMediation = false;
        break;

    case "4":
        //枝区分は不要です
        incomeDto.isUseYoushikiEdaKbn = false;
        //団体名称は不要です
        incomeDto.isUseOrgName = false;
        //団体住所は不要です
        incomeDto.isUseAddress = false;
        //項目は必要です
        incomeDto.isUseItemName = true;
        incomeDto.attentionItemName = "借入した相手";
        //寄付金控除は不要です
        incomeDto.isUseCreditTax = false;
        //パーティ名称は不要です
        incomeDto.isUsePartyName = false;
        //パーティ日付は不要です
        incomeDto.isUsePartyDate = false;
        //あっせん項目は不要です
        incomeDto.isUseMediation = false;
        break;
    case "5":
        //枝区分は不要です
        incomeDto.isUseYoushikiEdaKbn = false;
        //団体名称は必要です
        incomeDto.isUseOrgName = true;
        incomeDto.attentionOrgName = "本部または支部名称";
        //団体住所は必要です
        incomeDto.isUseAddress = true;
        incomeDto.attentionAddress = "本部または支部住所";
        //項目は不要です
        incomeDto.isUseItemName = false;
        //寄付金控除は不要です
        incomeDto.isUseCreditTax = false;
        //パーティ名称は不要です
        incomeDto.isUsePartyName = false;
        //パーティ日付は不要です
        incomeDto.isUsePartyDate = false;
        //あっせん項目は不要です
        incomeDto.isUseMediation = false;
        break;

    case "6":
        //枝区分は不要です
        incomeDto.isUseYoushikiEdaKbn = false;
        //団体名称は不要です
        incomeDto.isUseOrgName = false;
        //団体住所は不要です
        incomeDto.isUseAddress = false;
        //項目は必要です
        incomeDto.isUseItemName = true;
        incomeDto.attentionItemName = "収入の摘要";
        //寄付金控除は不要です
        incomeDto.isUseCreditTax = false;
        //パーティ名称は不要です
        incomeDto.isUsePartyName = false;
        //パーティ日付は不要です
        incomeDto.isUsePartyDate = false;
        //あっせん項目は不要です
        incomeDto.isUseMediation = false;
        break;

    case "7":
        //枝区分は必要です
        incomeDto.isUseYoushikiEdaKbn = true;
        //団体名称は必要です
        incomeDto.isUseOrgName = true;
        incomeDto.attentionOrgName = "団体代表者名／個人職業";
        //団体住所は必要です
        incomeDto.isUseAddress = true;
        incomeDto.attentionAddress = "寄付者自身または団体の住所";
        //項目は必要です
        incomeDto.isUseItemName = true;
        incomeDto.attentionItemName = "個人氏名／団体名称";
        //寄付金控除は不要です
        //incomeDto.isUseCreditTax = false;
        //パーティ名称は不要です
        incomeDto.isUsePartyName = false;
        //パーティ日付は不要です
        incomeDto.isUsePartyDate = false;
        //あっせん項目は不要です
        incomeDto.isUseMediation = false;
        break;
    case "8":
        //枝区分は必要です
        incomeDto.isUseYoushikiEdaKbn = true;
        //あっせん項目は期間の記載が必要となります
        incomeDto.isUseMediation = true;
        incomeDto.attentionMediation = "寄付をあっせんした期間";
        //団体名称は必要です
        incomeDto.isUseOrgName = true;
        incomeDto.attentionOrgName = "団体の場合は代表者名、個人の場合は職業";
        //団体住所は必要です
        incomeDto.isUseAddress = true;
        incomeDto.attentionAddress = "寄付あっせん者自身または団体の住所";
        //概要は必要です
        incomeDto.isUseItemName = true;
        incomeDto.attentionItemName = "寄付あっせん者の個人氏名または団体名称";
        //寄付金控除は不要です
        incomeDto.isUseCreditTax = false;
        //パーティ名称は不要です
        incomeDto.isUsePartyName = false;
        //パーティ日付は不要です
        incomeDto.isUsePartyDate = false;
        break;

    case "9":
        //枝区分は不要です
        incomeDto.isUseYoushikiEdaKbn = false;
        //団体名称は不要です
        incomeDto.isUseOrgName = false;
        //団体住所は不要です
        incomeDto.isUseAddress = false;
        //項目は必要です
        incomeDto.isUseItemName = true;
        incomeDto.attentionItemName = "政党匿名寄付を受けた場所";
        //寄付金控除は不要です
        incomeDto.isUseCreditTax = false;
        //パーティ名称は不要です
        incomeDto.isUsePartyName = false;
        //パーティ日付は不要です
        incomeDto.isUsePartyDate = false;
        //あっせん日付は不要です
        incomeDto.isUseMediation = false;
        break;

    case "10":
        //枝区分は不要です
        incomeDto.isUseYoushikiEdaKbn = false;
        //団体名称は必要です
        incomeDto.isUseOrgName = true;
        incomeDto.attentionOrgName = "パーティ名称";
        //団体住所は必要です
        incomeDto.isUseAddress = true;
        incomeDto.attentionAddress = "パーティ開催場所住所";
        //概要は必要です
        incomeDto.isUseItemName = true;
        incomeDto.attentionItemName = "対価を支払った人数";
        //寄付金控除は不要です
        incomeDto.isUseCreditTax = false;
        //パーティ名称は不要です
        incomeDto.isUsePartyName = false;
        //パーティ日付は不要です
        incomeDto.isUsePartyDate = false;
        //あっせん日付は不要です
        incomeDto.isUseMediation = false;
        break;

    case "11":
        //枝区分は必要です
        incomeDto.isUseYoushikiEdaKbn = true;
        //団体名称は必要です
        incomeDto.isUseOrgName = true;
        incomeDto.attentionOrgName = "団体の場合は代表者名、個人の場合は職業";
        //団体住所は必要です
        incomeDto.isUseAddress = true;
        incomeDto.attentionAddress = "個人または団体住所";
        //概要は必要です
        incomeDto.isUseItemName = true;
        incomeDto.attentionItemName = "費用を支払った氏名";
        //寄付金控除は不要です
        incomeDto.isUseCreditTax = false;
        //パーティ名称は必要です
        incomeDto.isUsePartyName = true;
        incomeDto.attentionPartyName = "開催したパーティ名称";
        //パーティ日付は必要です
        incomeDto.isUsePartyDate = true;
        incomeDto.attentionPartyDate = "パーティ開催日";
        //あっせん日付は不要です
        incomeDto.isUseMediation = false;
        break;

    case "12":
        //枝区分は必要です
        incomeDto.isUseYoushikiEdaKbn = true;
        //あっせん項目は期間の記載が必要となります
        incomeDto.isUseMediation = true;
        incomeDto.attentionMediation = "寄付をあっせんした期間";
        //団体名称は必要です
        incomeDto.isUseOrgName = true;
        incomeDto.attentionOrgName = "団体の場合は代表者名、個人の場合は職業";
        //団体住所は必要です
        incomeDto.isUseAddress = true;
        incomeDto.attentionAddress = "個人または団体住所";
        //概要は必要です
        incomeDto.isUseItemName = true;
        incomeDto.attentionItemName = "費用を支払った氏名";
        //寄付金控除は不要です
        incomeDto.isUseCreditTax = false;
        //パーティ名称は必要です
        incomeDto.isUsePartyName = true;
        incomeDto.attentionPartyName = "開催したパーティ名称";
        //パーティ日付は必要です
        incomeDto.isUsePartyDate = true;
        incomeDto.attentionPartyDate = "パーティ開催日";
        //あっせん日付は必要です
        incomeDto.isUseMediation = true;
        break;
    default:
        //初期データの時は該当
        break;
    }

    //枝区分設定            
    changeStateIncomeEdaKbn(incomeDto);

    return incomeDto;
}
