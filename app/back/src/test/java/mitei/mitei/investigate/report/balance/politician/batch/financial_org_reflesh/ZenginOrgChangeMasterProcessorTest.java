package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * ZenginOrgChangeMasterProcessor単体テスト
 */
class ZenginOrgChangeMasterProcessorTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        ZenginOrgChangeBranchEntity changeEntity = new ZenginOrgChangeBranchEntity();

        SetTableDataHistoryUtil.practice(CreateTestPrivilegeDtoUtil.pracitce(), changeEntity,
                DataHistoryStatusConstants.INSERT);

        changeEntity.setZenginOrgTempoMasterId(300);
        changeEntity.setZenginOrgTempoMasterCode(301);
        changeEntity.setZenginOrgTempoMasterName("名称");
        changeEntity.setChangeKbn(4);
        changeEntity.setChangeKbnText("追加");
        changeEntity.setIsFinished(false);
        changeEntity.setOrgCode("400");
        changeEntity.setBranchCode("500");
        changeEntity.setOrgNameKana("きんゆうきかんかな");
        changeEntity.setOrgName("金融機関");
        changeEntity.setBranchNameKana("してんかな");
        changeEntity.setBranchName("支店");
        changeEntity.setPostalCode("123456");
        changeEntity.setBranchAddress("東京都千代田区霞ヶ浦");
        changeEntity.setPhonNumber("000-xxxyyyy");
        changeEntity.setBillExchangeNumber("1");
        changeEntity.setOrderCode("1");
        changeEntity.setFlgNaikokuKawase("1");

        List<ZenginOrgChangeBranchEntity> list = new ArrayList<>();
        list.add(changeEntity);

        ZenginOrgChangeMasterProcessor zenginOrgChangeMasterProcessor = new ZenginOrgChangeMasterProcessor();
        ZenginOrgBranchMasterEntity entityAnswer = zenginOrgChangeMasterProcessor.process(changeEntity);

        assertEquals(changeEntity.getZenginOrgTempoMasterId(), entityAnswer.getZenginOrgTempoMasterId(), "masterIdが一致");
        assertEquals(changeEntity.getZenginOrgTempoMasterCode(), entityAnswer.getZenginOrgTempoMasterCode(),
                "masterCodseが一致");
        assertEquals(changeEntity.getZenginOrgTempoMasterName(), entityAnswer.getZenginOrgTempoMasterName(),
                "masterNameが一致");
        assertEquals(changeEntity.getOrgCode(), entityAnswer.getOrgCode(), "金融機関コードが一致");
        assertEquals(changeEntity.getBranchCode(), entityAnswer.getBranchCode(), "支店コードが一致");
        assertEquals(changeEntity.getOrgNameKana(), entityAnswer.getOrgNameKana(), "金融機関名称かなが一致");
        assertEquals(changeEntity.getOrgName(), entityAnswer.getOrgName(), "金融機関名称が一致");
        assertEquals(changeEntity.getBranchNameKana(), entityAnswer.getBranchNameKana(), "支店かなが一致");
        assertEquals(changeEntity.getBranchName(), entityAnswer.getBranchName(), "支店名が一致");
        assertEquals(changeEntity.getPostalCode(), entityAnswer.getPostalCode(), "郵便番号が一致");
        assertEquals(changeEntity.getBranchAddress(), entityAnswer.getBranchAddress(), "支店住所が一致");
        assertEquals(changeEntity.getPhonNumber(), entityAnswer.getPhonNumber(), "支店電話番号が一致");
        assertEquals(changeEntity.getBillExchangeNumber(), entityAnswer.getBillExchangeNumber(), "   手形交換所番号が一致");
        assertEquals(changeEntity.getOrderCode(), entityAnswer.getOrderCode(), "並び順が一致");
        assertEquals(changeEntity.getFlgNaikokuKawase(), entityAnswer.getFlgNaikokuKawase(), "内国為替加盟フラグが一致");

    }

}
