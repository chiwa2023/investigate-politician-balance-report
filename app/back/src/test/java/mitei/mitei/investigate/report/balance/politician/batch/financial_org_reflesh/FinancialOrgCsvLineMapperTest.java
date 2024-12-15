package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * FinancialOrgCsvLineMapper単体テスト
 */
class FinancialOrgCsvLineMapperTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        FinancialOrgCsvLineMapper financialOrgCsvLineMapper = new FinancialOrgCsvLineMapper();

        String data = "\"1311\",\"035\",\"トウキョウシティシンヨウキンコ\",\"東京シティ信用金庫\",\"ニンギョウマチシュッチョウショ\",\"日本橋支店 人形町出張所\",\"post\",\"中央区日本橋人形町3-7-9\",\"0356145160\",\"1\",\"1\",\"1\"";

        FinancialOrgCsvDto csvDto = financialOrgCsvLineMapper.mapLine(data, 0);

        assertEquals("1311", csvDto.getOrgCode(), "金融機関コード");
        assertEquals("035", csvDto.getBranchCode(), "店舗コード");
        assertEquals("トウキョウシティシンヨウキンコ", csvDto.getOrgNameKana(), "金融機関名(カナ)");
        assertEquals("東京シティ信用金庫", csvDto.getOrgName(), "金融機関名(漢字)");
        assertEquals("ニンギョウマチシュッチョウショ", csvDto.getBranchNameKana(), "店舗名(カナ)");
        assertEquals("日本橋支店 人形町出張所", csvDto.getBranchName(), "店舗名(漢字)");
        assertEquals("post", csvDto.getPostalCode(), "郵便番号");
        assertEquals("中央区日本橋人形町3-7-9", csvDto.getBranchAddress(), "店舗所在地(漢字)");
        assertEquals("0356145160", csvDto.getPhonNumber(), "電話番号");
        assertEquals("1", csvDto.getBillExchangeNumber(), "手形交換所番号");
        assertEquals("1", csvDto.getOrderCode(), "並びコード");
        assertEquals("1", csvDto.getFlgNaikokuKawase(), "内国為替制度加盟");

    }

}
