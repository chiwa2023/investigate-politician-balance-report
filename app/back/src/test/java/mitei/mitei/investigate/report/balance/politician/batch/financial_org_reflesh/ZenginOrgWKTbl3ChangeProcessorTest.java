package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk3Entity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * ZenginOrgWKtbl3ChangeProcessor単体テスト
 */
class ZenginOrgWKTbl3ChangeProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {
        ZenginOrgBranchWk3Entity wk3Entity = new ZenginOrgBranchWk3Entity();
        wk3Entity.setOrgCode("111");
        // wk3Entity.setOrgNumber(999);
        wk3Entity.setBranchCode("112");
        wk3Entity.setOrgNameKana("113");
        wk3Entity.setOrgName("114");
        wk3Entity.setBranchNameKana("115");
        wk3Entity.setBranchName("116");
        wk3Entity.setPostalCode("117");
        wk3Entity.setBranchAddress("118");
        wk3Entity.setPhonNumber("119");
        wk3Entity.setBillExchangeNumber("120");
        wk3Entity.setOrderCode("121");
        wk3Entity.setFlgNaikokuKawase("122");

        ZenginOrgWKTbl3ChangeProcessor zenginOrgWKtbl3ChangeProcessor = new ZenginOrgWKTbl3ChangeProcessor();

        ZenginOrgChangeBranchEntity changeEntity = zenginOrgWKtbl3ChangeProcessor.process(wk3Entity);

        assertEquals(changeEntity.getOrgCode(), wk3Entity.getOrgCode(), "店舗コードを複写");
        assertEquals(changeEntity.getBranchCode(), wk3Entity.getBranchCode(), "支店コードを複写");
        assertEquals(changeEntity.getOrgNameKana(), wk3Entity.getOrgNameKana(), "金機関名かなを複写");
        assertEquals(changeEntity.getOrgName(), wk3Entity.getOrgName(), "金融機関名を複写");
        assertEquals(changeEntity.getBranchNameKana(), wk3Entity.getBranchNameKana(), "支店名かなを複写");
        assertEquals(changeEntity.getBranchName(), wk3Entity.getBranchName(), "支店名を複写");
        assertEquals(changeEntity.getPostalCode(), wk3Entity.getPostalCode(), "郵便番号を複写");
        assertEquals(changeEntity.getBranchAddress(), wk3Entity.getBranchAddress(), "住所を複写");
        assertEquals(changeEntity.getPhonNumber(), wk3Entity.getPhonNumber(), "電話番号を複写");
        assertEquals(changeEntity.getBillExchangeNumber(), wk3Entity.getBillExchangeNumber(), "手形交換所番号を複写");
        assertEquals(changeEntity.getOrderCode(), wk3Entity.getOrderCode(), "並び順を複写");
        assertEquals(changeEntity.getFlgNaikokuKawase(), wk3Entity.getFlgNaikokuKawase(), "内国為替加盟制度フラグを複写");
    }

}
