package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk3Entity;

/**
 * FinancialOrgCsvWkTbl3Processor単体テスト
 */
class FinancialOrgCsvWkTbl3ProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        FinancialOrgCsvLineMapper financialOrgCsvLineMapper = new FinancialOrgCsvLineMapper();

        String data = "\"1311\",\"035\",\"トウキョウシティシンヨウキンコ\",\"東京シティ信用金庫\",\"ニンギョウマチシュッチョウショ\",\"日本橋支店 人形町出張所\",\"post\",\"中央区日本橋人形町3-7-9\",\"0356145160\",\"1\",\"1\",\"1\"";

        FinancialOrgCsvDto csvDto = financialOrgCsvLineMapper.mapLine(data, 0);

        FinancialOrgCsvWkTbl3Processor financialOrgCsvWkTbl3Processor = new FinancialOrgCsvWkTbl3Processor();

        ZenginOrgBranchWk3Entity wkEntity = financialOrgCsvWkTbl3Processor.process(csvDto);

        assertEquals(wkEntity.getOrgCode(), csvDto.getOrgCode(), "金融機関コード");
        assertEquals(wkEntity.getBranchCode(), csvDto.getBranchCode(), "店舗コード");
        assertEquals(wkEntity.getOrgNameKana(), csvDto.getOrgNameKana(), "金融機関名(カナ)");
        assertEquals(wkEntity.getOrgName(), csvDto.getOrgName(), "金融機関名(漢字)");
        assertEquals(wkEntity.getBranchNameKana(), csvDto.getBranchNameKana(), "店舗名(カナ)");
        assertEquals(wkEntity.getBranchName(), csvDto.getBranchName(), "店舗名(漢字)");
        assertEquals(wkEntity.getPostalCode(), csvDto.getPostalCode(), "郵便番号");
        assertEquals(wkEntity.getBranchAddress(), csvDto.getBranchAddress(), "店舗所在地(漢字)");
        assertEquals(wkEntity.getPhonNumber(), csvDto.getPhonNumber(), "電話番号");
        assertEquals(wkEntity.getBillExchangeNumber(), csvDto.getBillExchangeNumber(), "手形交換所番号");
        assertEquals(wkEntity.getOrderCode(), csvDto.getOrderCode(), "並びコード");
        assertEquals(wkEntity.getFlgNaikokuKawase(), csvDto.getFlgNaikokuKawase(), "内国為替制度加盟");

    }

}
