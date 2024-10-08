﻿import AbstactCapsuleDto from "../abstactCapsuleDto";
import BalancesheetIncomeInterface from "./balancesheetIncomeDto";
import BalancesheetOutcomeInterface from "./balancesheetOutcomeDto";

export default class RegistBalancesheetInOutCapsuleDto extends AbstactCapsuleDto {

    /** 収入項目リスト */
    listIncome: BalancesheetIncomeInterface[];

    /** 支出項目リスト */
    listOutcome: BalancesheetOutcomeInterface[];

    constructor() {
        super();
        this.listIncome = [];
        this.listOutcome = [];
    }

}