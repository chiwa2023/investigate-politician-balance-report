import { expect, it } from "vitest";
import closeInputIncomeForm from "./closeInputIncomeForm";
import BalancesheetIncomeDto from "../balancesheet/balancesheetIncomeDto";

it("closeInput", () => {
    const dto: BalancesheetIncomeDto = new BalancesheetIncomeDto();
    closeInputIncomeForm(dto);

    //様式区分を非表示にします
    expect(dto.isUseYoushikiKbn).toBe(false);

    //様式区分枝項目を非表示にします
    expect(dto.isUseYoushikiEdaKbn).toBe(false);

    //あっせん期間部分を非表示にします
    expect(dto.isUseMediation).toBe(false);

    //団体名称を非表示にします
    expect(dto.isUseOrgName).toBe(false);

    //項目名を非表示にします
    expect(dto.isUseItemName).toBe(false);

    //団体住所を非表示にします
    expect(dto.isUseAddress).toBe(false);

    //備考を非表示にします
    expect(dto.isUseBiko).toBe(false);

    //寄付金控除を非表示にします
    expect(dto.isUseCreditTax).toBe(false);

    //パーティ名称は非表示にします
    expect(dto.isUsePartyName).toBe(false);

    //パーティ日付を非表示にします
    expect(dto.isUsePartyDate).toBe(false);
});