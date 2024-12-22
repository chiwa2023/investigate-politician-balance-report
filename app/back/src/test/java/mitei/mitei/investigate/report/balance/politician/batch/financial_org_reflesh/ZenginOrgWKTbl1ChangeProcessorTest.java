package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchWk1Entity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * ZenginOrgWKtbl1ChangeProcessor単体テスト
 */
class ZenginOrgWKTbl1ChangeProcessorTest {

    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        ZenginOrgBranchWk1Entity wk1Entity = new ZenginOrgBranchWk1Entity();
        wk1Entity.setOrgCode("111");
        //wk1Entity.setOrgNumber(999);
        wk1Entity.setBranchCode("112");
        wk1Entity.setOrgNameKana("113");
        wk1Entity.setOrgName("114");
        wk1Entity.setBranchNameKana("115");
        wk1Entity.setBranchName("116");
        wk1Entity.setPostalCode("117");
        wk1Entity.setBranchAddress("118");
        wk1Entity.setPhonNumber("119");
        wk1Entity.setBillExchangeNumber("120");
        wk1Entity.setOrderCode("121");
        wk1Entity.setFlgNaikokuKawase("122");

        
        ZenginOrgWKTbl1ChangeProcessor zenginOrgWKtbl1ChangeProcessor = new ZenginOrgWKTbl1ChangeProcessor();
        
        ZenginOrgChangeBranchEntity changeEntity = zenginOrgWKtbl1ChangeProcessor.process(wk1Entity);
        
        assertEquals(changeEntity.getOrgCode(), wk1Entity.getOrgCode(),"店舗コードを複写");
        assertEquals(changeEntity.getBranchCode(), wk1Entity.getBranchCode(),"支店コードを複写");
        assertEquals(changeEntity.getOrgNameKana(), wk1Entity.getOrgNameKana(),"金機関名かなを複写");
        assertEquals(changeEntity.getOrgName(), wk1Entity.getOrgName(),"金融機関名を複写");
        assertEquals(changeEntity.getBranchNameKana(), wk1Entity.getBranchNameKana(),"支店名かなを複写");
        assertEquals(changeEntity.getBranchName(), wk1Entity.getBranchName(),"支店名を複写");
        assertEquals(changeEntity.getPostalCode(), wk1Entity.getPostalCode(),"郵便番号を複写");
        assertEquals(changeEntity.getBranchAddress(), wk1Entity.getBranchAddress(),"住所を複写");
        assertEquals(changeEntity.getPhonNumber(), wk1Entity.getPhonNumber(),"電話番号を複写");
        assertEquals(changeEntity.getBillExchangeNumber(), wk1Entity.getBillExchangeNumber(),"手形交換所番号を複写");
        assertEquals(changeEntity.getOrderCode(), wk1Entity.getOrderCode(),"並び順を複写");
        assertEquals(changeEntity.getFlgNaikokuKawase(), wk1Entity.getFlgNaikokuKawase(),"内国為替加盟制度フラグを複写");
        
    }

}
