package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgChangeBranchRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * ZenginOrgChangeBranchItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ZenginOrgChangeBranchItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ZenginOrgChangeBranchItemWriter zenginOrgChangeBranchItemWriter;

    /** 全銀協記入機関・店舗マスタRepository */
    @Autowired
    private ZenginOrgChangeBranchRepository zenginOrgChangeBranchRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("truncate_zengin_org_change_branch.sql")
    void test() throws Exception {

        assertEquals(0L, zenginOrgChangeBranchRepository.count(), "truncate直後は0件");

        ZenginOrgChangeBranchEntity entity = new ZenginOrgChangeBranchEntity();
        SetTableDataHistoryUtil.practice(CreateTestPrivilegeDtoUtil.pracitce(), entity,
                DataHistoryStatusConstants.INSERT);

        entity.setZenginOrgTempoMasterId(300);
        entity.setZenginOrgTempoMasterCode(301);
        entity.setZenginOrgTempoMasterName("名称");
        entity.setChangeKbn(4);
        entity.setChangeKbnText("追加");
        entity.setIsFinished(false);
        entity.setOrgCode("400");
        entity.setBranchCode("500");
        entity.setOrgNameKana("きんゆうきかんかな");
        entity.setOrgName("金融機関");
        entity.setBranchNameKana("してんかな");
        entity.setBranchName("支店");
        entity.setPostalCode("123456");
        entity.setBranchAddress("東京都千代田区霞ヶ浦");
        entity.setPhonNumber("000-xxxyyyy");
        entity.setBillExchangeNumber("1");
        entity.setOrderCode("1");
        entity.setFlgNaikokuKawase("1");

        List<ZenginOrgChangeBranchEntity> list = new ArrayList<>();
        list.add(entity);

        // Chunkを作成してセット
        Chunk<? extends ZenginOrgChangeBranchEntity> items = new Chunk<>(list);

        zenginOrgChangeBranchItemWriter.write(items);

        assertEquals(1L, zenginOrgChangeBranchRepository.count(), "登録したらは1件");

        ZenginOrgChangeBranchEntity entityRecorded = list.get(0);

        assertEquals(entity.getZenginOrgTempoMasterId(), entityRecorded.getZenginOrgTempoMasterId(), "masterIdが一致");
        assertEquals(entity.getZenginOrgTempoMasterCode(), entityRecorded.getZenginOrgTempoMasterCode(),
                "masterCodseが一致");
        assertEquals(entity.getZenginOrgTempoMasterName(), entityRecorded.getZenginOrgTempoMasterName(),
                "masterNameが一致");
        assertEquals(entity.getChangeKbn(), entityRecorded.getChangeKbn(), "変更区分が一致");
        assertEquals(entity.getChangeKbnText(), entityRecorded.getChangeKbnText(), "変更区分表示が一致");
        assertEquals(entity.getIsFinished(), entityRecorded.getIsFinished(), "終了フラグが一致");
        assertEquals(entity.getOrgCode(), entityRecorded.getOrgCode(), "金融機関コードが一致");
        assertEquals(entity.getBranchCode(), entityRecorded.getBranchCode(), "支店コードが一致");
        assertEquals(entity.getOrgNameKana(), entityRecorded.getOrgNameKana(), "金融機関名称かなが一致");
        assertEquals(entity.getOrgName(), entityRecorded.getOrgName(), "金融機関名称が一致");
        assertEquals(entity.getBranchNameKana(), entityRecorded.getBranchNameKana(), "支店かなが一致");
        assertEquals(entity.getBranchName(), entityRecorded.getBranchName(), "支店名が一致");
        assertEquals(entity.getPostalCode(), entityRecorded.getPostalCode(), "郵便番号が一致");
        assertEquals(entity.getBranchAddress(), entityRecorded.getBranchAddress(), "支店住所が一致");
        assertEquals(entity.getPhonNumber(), entityRecorded.getPhonNumber(), "支店電話番号が一致");
        assertEquals(entity.getBillExchangeNumber(), entityRecorded.getBillExchangeNumber(), "   手形交換所番号が一致");
        assertEquals(entity.getOrderCode(), entityRecorded.getOrderCode(), "並び順が一致");
        assertEquals(entity.getFlgNaikokuKawase(), entityRecorded.getFlgNaikokuKawase(), "内国為替加盟フラグが一致");

        assertEquals(1, entity.getSaishinKbn(), "最新データ");

    }

}
