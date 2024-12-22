package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;

/**
 * ZenginOrgMasterChangeProcessor単体テスト
 */
class ZenginOrgMasterChangeProcessorTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void test()throws Exception {
        
        ZenginOrgBranchMasterEntity masterEntity = new ZenginOrgBranchMasterEntity();
        masterEntity.setZenginOrgTempoMasterId(800);
        masterEntity.setZenginOrgTempoMasterCode(900);
        masterEntity.setZenginOrgTempoMasterName("金融機関支店");
        masterEntity.setOrgCode("111");
        masterEntity.setOrgNumber(999);
        masterEntity.setBranchCode("112");
        masterEntity.setOrgNameKana("113");
        masterEntity.setOrgName("114");
        masterEntity.setBranchNameKana("115");
        masterEntity.setBranchName("116");
        masterEntity.setPostalCode("117");
        masterEntity.setBranchAddress("118");
        masterEntity.setPhonNumber("119");
        masterEntity.setBillExchangeNumber("120");
        masterEntity.setOrderCode("121");
        masterEntity.setFlgNaikokuKawase("122");
        
        ZenginOrgMasterChangeProcessor zenginOrgMasterChangeProcessor = new ZenginOrgMasterChangeProcessor();
        
        ZenginOrgChangeBranchEntity changeEntity = zenginOrgMasterChangeProcessor.process(masterEntity);
        assertEquals(changeEntity.getZenginOrgTempoMasterId(), masterEntity.getZenginOrgTempoMasterId(),"idを複写");
        assertEquals(changeEntity.getZenginOrgTempoMasterCode(), masterEntity.getZenginOrgTempoMasterCode(),"同一識別コードを複写");
        assertEquals(changeEntity.getZenginOrgTempoMasterName(), masterEntity.getZenginOrgTempoMasterName(),"名称を複写");
        assertEquals(changeEntity.getOrgCode(), masterEntity.getOrgCode(),"金融機関コードを複写");
        assertEquals(changeEntity.getBranchCode(), masterEntity.getBranchCode(),"店舗コードを複写");
        assertEquals(changeEntity.getOrgNameKana(), masterEntity.getOrgNameKana(),"金融機関名かなを複写");
        assertEquals(changeEntity.getOrgName(), masterEntity.getOrgName(),"金融機関名を複写");
        assertEquals(changeEntity.getBranchNameKana(), masterEntity.getBranchNameKana(),"支店名かなを複写");
        assertEquals(changeEntity.getBranchName(), masterEntity.getBranchName(),"支店名を複写");
        assertEquals(changeEntity.getPostalCode(), masterEntity.getPostalCode(),"郵便番号を複写");
        assertEquals(changeEntity.getBranchAddress(), masterEntity.getBranchAddress(),"店舗住所を複写");
        assertEquals(changeEntity.getPhonNumber(), masterEntity.getPhonNumber(),"電話番号を複写");
        assertEquals(changeEntity.getBillExchangeNumber(), masterEntity.getBillExchangeNumber(),"手形交換所番号を複写");
        assertEquals(changeEntity.getOrderCode(), masterEntity.getOrderCode(),"並び順を複写");
        assertEquals(changeEntity.getFlgNaikokuKawase(), masterEntity.getFlgNaikokuKawase(),"内国為替対応フラグを複写");
        
    }

}
