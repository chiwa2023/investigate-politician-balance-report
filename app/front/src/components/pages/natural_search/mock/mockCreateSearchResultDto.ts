import IncomeAndOutcomeNaturalSearchResultDto from "../../../../dto/natural_search/incomeAndOutcomeNaturalSearchResultDto";
import IncomeAndOutcomeSearchLineDto from "../../../../dto/natural_search/incomeAndOutcomeSearchLineDto";


export default function mockCreateSearchResultDto(): IncomeAndOutcomeNaturalSearchResultDto {
    const resultDto: IncomeAndOutcomeNaturalSearchResultDto = new IncomeAndOutcomeNaturalSearchResultDto();

    // ダミーのヒット件数
    resultDto.countIncome = 879;
    resultDto.countOutcome = 123;

    // 収入
    resultDto.listIncome.push(createLineDtoIncome(9797));

    // 支出
    resultDto.listOutcome.push(createLineDtoOutcome(8686));

    return resultDto;
}

/* 支出ダミーデータを作成する  */
function createLineDtoIncome(amount: number): IncomeAndOutcomeSearchLineDto {

    const dto: IncomeAndOutcomeSearchLineDto = new IncomeAndOutcomeSearchLineDto();

    // 項目名称
    dto.itemName = "寄付";
    // 目的 
    dto.mokuteki = "";
    // 金額 
    dto.kingaku = amount;
    // 発生日 
    dto.accrualDate = "R4.10.13";
    // 発生日実値 
    dto.accrualDateValue = new Date(2022,10,13);
    // 原文書政治団体代表者名
    dto.daihyouName = "代表者　姓名";
    // 原文書政治団体名称 
    dto.dantaiName = "ちゃらんぽらん政治団体";
    // 様式区分 
    dto.youshikiKbn = 7;
    // 様式枝区分項目 
    dto.youshikiEdaKbn = 1;
    // 支出した相手先名 
    dto.partnerName = "寄付した一般人A";
    // 支出した相手先住所 
    dto.partnerJuusho = "相手先住所1";
    // 金額表示テキスト収入 
    dto.kingakuIncomeText = String(amount);
    // 金額表示テキスト支出 
    dto.kingakuOutcomeText = "";
    // 集計用金額 
    dto.kingakuShuukei = amount;

    return dto;
}

/* 支出ダミーデータを作成する  */
function createLineDtoOutcome(amount: number): IncomeAndOutcomeSearchLineDto {

    const dto: IncomeAndOutcomeSearchLineDto = new IncomeAndOutcomeSearchLineDto();

    // 項目名称
    dto.itemName = "寄付";
    // 目的 
    dto.mokuteki = "";
    // 金額 
    dto.kingaku = amount;
    // 発生日 
    dto.accrualDate = "R4.10.19";
    // 発生日実値 
    dto.accrualDateValue = new Date(2022,10,19);
    // 原文書政治団体代表者名
    dto.daihyouName = "代表者　姓名";
    // 原文書政治団体名称 
    dto.dantaiName = "ちゃらんぽらん政治団体";
    // 様式区分 
    dto.youshikiKbn = 15;
    // 様式枝区分項目 
    dto.youshikiEdaKbn = 8;
    // 支出した相手先名 
    dto.partnerName = "パーティ開催業者";
    // 支出した相手先住所 
    dto.partnerJuusho = "相手住所2";
    // 金額表示テキスト収入 
    dto.kingakuIncomeText = "";
    // 金額表示テキスト支出 
    dto.kingakuOutcomeText = String(amount);
    // 集計用金額 
    dto.kingakuShuukei = amount * -1;

    return dto;
}