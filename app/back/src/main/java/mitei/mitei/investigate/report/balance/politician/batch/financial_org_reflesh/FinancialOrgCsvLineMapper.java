package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 全銀協金融機関店舗Csv用LineMapper
 */
@Component
public class FinancialOrgCsvLineMapper implements LineMapper<FinancialOrgCsvDto> {

    /** 金融機関カラム位置 */
    private static final int POS_ORG_CODE = 0;

    /** 店舗コード */
    private static final int POS_BRANCH_CODE = 1;

    /** 金融機関名(カナ) */
    private static final int POS_ORG_NAME_KANA = 2;

    /** 金融機関名(漢字) */
    private static final int POS_ORG_NAME = 3;

    /** 店舗名(カナ) */
    private static final int POS_BRANCH_NAME_KANA = 4;

    /** 店舗名(漢字) */
    private static final int POS_BRANCH_NAME = 5;

    /** 郵便番号 */
    private static final int POS_POSTAL_CODE = 6;

    /** 店舗所在地(漢字) */
    private static final int POS_BRANCH_ADDRESS = 7;

    /** 電話番号 */
    private static final int POS_PHONE_NUMBER = 8;

    /** 手形交換所番号 */
    private static final int POS_BILL_EXCHANGE_NUMBER = 9;

    /** 並びコード */
    private static final int POS_ORDER_CODE = 10;

    /** 内国為替制度加盟 */
    private static final int POS_FLG_NAIKOKU_KAWASE = 11;

    /**
     * CSVの行をPersonDtoに変換する
     */
    @Override
    public FinancialOrgCsvDto mapLine(final String line, final int lineNumber) throws Exception {
        
        FinancialOrgCsvDto dto = new FinancialOrgCsvDto();

        String[] cell = line.split(",");

        // 金融機関コード
        dto.setOrgCode(this.removeQuote(cell[POS_ORG_CODE]));

        // 店舗コード
        dto.setBranchCode(this.removeQuote(cell[POS_BRANCH_CODE]));

        // 金融機関名(カナ)
        dto.setOrgNameKana(this.removeQuote(cell[POS_ORG_NAME_KANA]));

        // 金融機関名(漢字)
        dto.setOrgName(this.removeQuote(cell[POS_ORG_NAME]));

        // 店舗名(カナ)
        dto.setBranchNameKana(this.removeQuote(cell[POS_BRANCH_NAME_KANA]));

        // 店舗名(漢字)
        dto.setBranchName(this.removeQuote(cell[POS_BRANCH_NAME]));

        // 郵便番号
        dto.setPostalCode(this.removeQuote(cell[POS_POSTAL_CODE]));

        // 店舗所在地(漢字)
        dto.setBranchAddress(this.removeQuote(cell[POS_BRANCH_ADDRESS]));

        // 電話番号
        dto.setPhonNumber(this.removeQuote(cell[POS_PHONE_NUMBER]));

        // 手形交換所番号
        dto.setBillExchangeNumber(this.removeQuote(cell[POS_BILL_EXCHANGE_NUMBER]));

        // 並びコード
        dto.setOrderCode(this.removeQuote(cell[POS_ORDER_CODE]));

        // 内国為替制度加盟
        dto.setFlgNaikokuKawase(this.removeQuote(cell[POS_FLG_NAIKOKU_KAWASE]));

        return dto;
    }

    private String removeQuote(final String data) {
        int posStart = 0;
        int posEnd = data.length();

        if (data.startsWith("\"")) {
            posStart++;
        }

        if (data.endsWith("\"")) {
            posEnd--;
        }

        return data.substring(posStart, posEnd);
    }
}
