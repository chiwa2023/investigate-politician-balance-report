package mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee;

import java.io.Serializable;

import mitei.mitei.investigate.report.balance.politician.dto.AbstractCapsuleDto;

/**
 * 政党ごとの会費員数合計検索条件格納Dto
 */
public class CalcMebershipFeeInsuCapsuleDto extends AbstractCapsuleDto implements Serializable{

    /** SerialId */
    private static final long serialVersionUID = 1L;

    /** 政党同一識別コード */
    private Integer politicalPartyCode;

    /**
     * 政党同一識別コードを取得する
     *
     * @return 政党同一識別コード
     */
    public Integer getPoliticalPartyCode() {
        return politicalPartyCode;
    }

    /**
     * 政党同一識別コードを設定する
     *
     * @param politicalPartyCode 政党同一識別コード
     */
    public void setPoliticalPartyCode(final Integer politicalPartyCode) {
        this.politicalPartyCode = politicalPartyCode;
    }
    
}
