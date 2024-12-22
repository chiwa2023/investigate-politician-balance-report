package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk2Entity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * ZenginOrgWKtbl2ChangeProcessor単体テスト
 */
class ZenginOrgWKTbl2ChangeProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ZenginOrgBranchWk2Entity wk2Entity = new ZenginOrgBranchWk2Entity();
        wk2Entity.setOrgCode("111");
        wk2Entity.setBranchCode("112");
        wk2Entity.setOrgNameKana("113");
        wk2Entity.setOrgName("114");
        wk2Entity.setBranchNameKana("115");
        wk2Entity.setBranchName("116");
        wk2Entity.setPostalCode("117");
        wk2Entity.setBranchAddress("118");
        wk2Entity.setPhonNumber("119");
        wk2Entity.setBillExchangeNumber("120");
        wk2Entity.setOrderCode("121");
        wk2Entity.setFlgNaikokuKawase("122");

        ZenginOrgWKTbl2ChangeProcessor zenginOrgWKtbl2ChangeProcessor = new ZenginOrgWKTbl2ChangeProcessor();

        ZenginOrgChangeBranchEntity changeEntity = zenginOrgWKtbl2ChangeProcessor.process(wk2Entity);

        assertEquals(changeEntity.getOrgCode(), wk2Entity.getOrgCode(), "店舗コードを複写");
        assertEquals(changeEntity.getBranchCode(), wk2Entity.getBranchCode(), "支店コードを複写");
        assertEquals(changeEntity.getOrgNameKana(), wk2Entity.getOrgNameKana(), "金機関名かなを複写");
        assertEquals(changeEntity.getOrgName(), wk2Entity.getOrgName(), "金融機関名を複写");
        assertEquals(changeEntity.getBranchNameKana(), wk2Entity.getBranchNameKana(), "支店名かなを複写");
        assertEquals(changeEntity.getBranchName(), wk2Entity.getBranchName(), "支店名を複写");
        assertEquals(changeEntity.getPostalCode(), wk2Entity.getPostalCode(), "郵便番号を複写");
        assertEquals(changeEntity.getBranchAddress(), wk2Entity.getBranchAddress(), "住所を複写");
        assertEquals(changeEntity.getPhonNumber(), wk2Entity.getPhonNumber(), "電話番号を複写");
        assertEquals(changeEntity.getBillExchangeNumber(), wk2Entity.getBillExchangeNumber(), "手形交換所番号を複写");
        assertEquals(changeEntity.getOrderCode(), wk2Entity.getOrderCode(), "並び順を複写");
        assertEquals(changeEntity.getFlgNaikokuKawase(), wk2Entity.getFlgNaikokuKawase(), "内国為替加盟制度フラグを複写");

    }

}
