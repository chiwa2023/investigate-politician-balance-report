import CheckPrivilegeDto from "./common_check/checkPrivilegeDto";
import CheckSecurityDto from "./common_check/checkSecurityDto";
import CheckTransactionDto from "./common_check/checkTransactionDto";

/**
 * 共通処理に関する情報を格納するカプセルDto
 */
export default class AbstractCapsuleDto {

    /** セキュリティDto */
    checkSecurityDto: CheckSecurityDto;

    /** 権限Dto */
    checkPrivilegeDto: CheckPrivilegeDto;

    /** 排他制御to */
    checkTransactionDto: CheckTransactionDto;

    constructor() {
        this.checkSecurityDto = new CheckSecurityDto();
        this.checkPrivilegeDto = new CheckPrivilegeDto();
        this.checkTransactionDto = new CheckTransactionDto();
    }
}