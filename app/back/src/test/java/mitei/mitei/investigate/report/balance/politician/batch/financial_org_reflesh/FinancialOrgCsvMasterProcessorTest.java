package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;

/**
 * FinancialOrgCsvMasterProcessor単体テスト
 */
class FinancialOrgCsvMasterProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        FinancialOrgCsvLineMapper financialOrgCsvLineMapper = new FinancialOrgCsvLineMapper();

        String data = "\"1311\",\"035\",\"トウキョウシティシンヨウキンコ\",\"東京シティ信用金庫\",\"ニンギョウマチシュッチョウショ\",\"日本橋支店 人形町出張所\",\"post\",\"中央区日本橋人形町3-7-9\",\"0356145160\",\"1\",\"1\",\"1\"";

        FinancialOrgCsvDto csvDto = financialOrgCsvLineMapper.mapLine(data, 0);

        FinancialOrgCsvMasterProcessor financialOrgCsvMasterProcessor = new FinancialOrgCsvMasterProcessor();

        ZenginOrgBranchMasterEntity masterEntity = financialOrgCsvMasterProcessor.process(csvDto);

        assertEquals(masterEntity.getOrgCode(), csvDto.getOrgCode(), "金融機関コード");
        assertEquals(masterEntity.getBranchCode(), csvDto.getBranchCode(), "店舗コード");
        assertEquals(masterEntity.getOrgNameKana(), csvDto.getOrgNameKana(), "金融機関名(カナ)");
        assertEquals(masterEntity.getOrgName(), csvDto.getOrgName(), "金融機関名(漢字)");
        assertEquals(masterEntity.getBranchNameKana(), csvDto.getBranchNameKana(), "店舗名(カナ)");
        assertEquals(masterEntity.getBranchName(), csvDto.getBranchName(), "店舗名(漢字)");
        assertEquals(masterEntity.getPostalCode(), csvDto.getPostalCode(), "郵便番号");
        assertEquals(masterEntity.getBranchAddress(), csvDto.getBranchAddress(), "店舗所在地(漢字)");
        assertEquals(masterEntity.getPhonNumber(), csvDto.getPhonNumber(), "電話番号");
        assertEquals(masterEntity.getBillExchangeNumber(), csvDto.getBillExchangeNumber(), "手形交換所番号");
        assertEquals(masterEntity.getOrderCode(), csvDto.getOrderCode(), "並びコード");
        assertEquals(masterEntity.getFlgNaikokuKawase(), csvDto.getFlgNaikokuKawase(), "内国為替制度加盟");

    }

}
