package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk4Entity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * ZenginOrgWKtbl4ChangeProcessor単体テスト
 */
class ZenginOrgWKTbl4ChangeProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        ZenginOrgBranchWk4Entity wk4Entity = new ZenginOrgBranchWk4Entity();
        wk4Entity.setOrgCode("111");
        wk4Entity.setBranchCode("112");
        wk4Entity.setOrgNameKana("113");
        wk4Entity.setOrgName("114");
        wk4Entity.setBranchNameKana("115");
        wk4Entity.setBranchName("116");
        wk4Entity.setPostalCode("117");
        wk4Entity.setBranchAddress("118");
        wk4Entity.setPhonNumber("119");
        wk4Entity.setBillExchangeNumber("120");
        wk4Entity.setOrderCode("121");
        wk4Entity.setFlgNaikokuKawase("122");

        ZenginOrgWKTbl4ChangeProcessor zenginOrgWKtbl4ChangeProcessor = new ZenginOrgWKTbl4ChangeProcessor();
        
        ZenginOrgChangeBranchEntity changeEntity = zenginOrgWKtbl4ChangeProcessor.process(wk4Entity);
        
        assertEquals(changeEntity.getOrgCode(), wk4Entity.getOrgCode(),"店舗コードを複写");
        assertEquals(changeEntity.getBranchCode(), wk4Entity.getBranchCode(),"支店コードを複写");
        assertEquals(changeEntity.getOrgNameKana(), wk4Entity.getOrgNameKana(),"金機関名かなを複写");
        assertEquals(changeEntity.getOrgName(), wk4Entity.getOrgName(),"金融機関名を複写");
        assertEquals(changeEntity.getBranchNameKana(), wk4Entity.getBranchNameKana(),"支店名かなを複写");
        assertEquals(changeEntity.getBranchName(), wk4Entity.getBranchName(),"支店名を複写");
        assertEquals(changeEntity.getPostalCode(), wk4Entity.getPostalCode(),"郵便番号を複写");
        assertEquals(changeEntity.getBranchAddress(), wk4Entity.getBranchAddress(),"住所を複写");
        assertEquals(changeEntity.getPhonNumber(), wk4Entity.getPhonNumber(),"電話番号を複写");
        assertEquals(changeEntity.getBillExchangeNumber(), wk4Entity.getBillExchangeNumber(),"手形交換所番号を複写");
        assertEquals(changeEntity.getOrderCode(), wk4Entity.getOrderCode(),"並び順を複写");
        assertEquals(changeEntity.getFlgNaikokuKawase(), wk4Entity.getFlgNaikokuKawase(),"内国為替加盟制度フラグを複写");
    }

}
