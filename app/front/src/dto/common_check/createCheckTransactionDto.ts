import CheckTransactionDto from "./checkTransactionDto";

/**
 * 排他制御チェックDtoを生成する
 * @param isSelectOnly 照会のみフラグ
 * @returns 排他制御チェックDto
 */
export default function createCheckTransactionDto(isSelectOnly:boolean):CheckTransactionDto{
    // NOTE:フィールドが変わるたびに実装修正
    const transactionDto:CheckTransactionDto = new CheckTransactionDto();
    transactionDto.isRaiseExcception = false;
    transactionDto.isResult = true;
    transactionDto.isSelectOnly = isSelectOnly;
    
    return transactionDto;
}