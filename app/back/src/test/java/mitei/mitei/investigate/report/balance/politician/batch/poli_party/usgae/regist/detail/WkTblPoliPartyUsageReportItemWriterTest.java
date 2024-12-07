package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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

import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliPartyUsageReportRepository;

/**
 * WkTblPoliPartyusageReportItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class WkTblPoliPartyUsageReportItemWriterTest {

    /** テスト対象 */
    @Autowired
    private WkTblPoliPartyUsageReportItemWriter wkTblPoliPartyusageReportItemWriter;

    /** 政治資金収支報告書登録準備ワークテーブルレポジトリ */
    @Autowired
    private WkTblPoliPartyUsageReportRepository wkTblPoliPartyUsageReportRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("wk_tbl_poli_party_usage_report_read.sql")
    void test() { // NOPMD
        // CHECKSTYLE:OFF

        WkTblPoliPartyUsageReportEntity entity00 = new WkTblPoliPartyUsageReportEntity();

        entity00.setWkTblPoliPartyUsageReportCode(103);
        entity00.setTaskPlanPartyUsageDetailId(101L);

        /* 書証保存Dto */
        entity00.setCharset("Windows-31J");
        entity00.setChildDir("912/20220403/abc");
        entity00.setFileName("sito/xml");
        entity00.setRegistTimeText("20220403");
        entity00.setShoshouCode(321L);
        entity00.setShoshouId(320L);
        entity00.setShoshouKbn(0);

        /* 政治資金収支報告書政治団体Dto */
        entity00.setVersion("20191220");
        entity00.setApliName("使途等報告書作成ソフト");
        entity00.setFlgApli("0");
        entity00.setFlgHonbu("0");

        // データ有無テキスト
        entity00.setJohoUmuText("111100000000001100");
        entity00.setKaikeiKijunKingaku(45_456L);

        entity00.setDaihyouName("代表名");
        entity00.setDantaiName("団体名");
        entity00.setNendo(2022);
        entity00.setOfferingDate(LocalDate.of(2022, 12, 19));
        entity00.setPoliticalOrganizationCode(444);
        entity00.setPoliticalOrganizationId(440L);
        entity00.setPoliticalOrganizationName("登録政治団体名");
        entity00.setRelationKbn(2);
        entity00.setRelationPersonCodeDelegate(525);
        entity00.setRelationPersonIdDelegate(520L);
        entity00.setRelationPersonNameDelegate("登録代表者名");

        entity00.setNendo(2022);
        entity00.setPartyName("政党名称");
        entity00.setPartyNameKana("政党名称かな");
        entity00.setOfficeAddress("主たる事務所住所");
        entity00.setDelegateName("代表者姓名");
        entity00.setAccountManagerName("会計責任者姓名");
        entity00.setWorker1Name("担当者1姓名");
        entity00.setWorker1Tel("012-3456-xxxx");
        entity00.setWorker2Name("担当者2姓名");
        entity00.setWorker2Tel("098-7654-xxxx");
        entity00.setShibuKbn(2);
        entity00.setKaisanKbn(1);
        entity00.setKaisanDate("R4/12/31");
        entity00.setSeiriNo("445566");
        entity00.setUketsukeNo("556677");

        WkTblPoliPartyUsageReportEntity entity01 = new WkTblPoliPartyUsageReportEntity();
        entity01.setWkTblPoliPartyUsageReportCode(214);
        entity01.setApliName("abcdef");
        entity01.setTaskPlanPartyUsageDetailId(201L);

        List<WkTblPoliPartyUsageReportEntity> list = new ArrayList<>();
        list.add(entity00);
        list.add(entity01);

        // Chunkを作成してセット
        Chunk<? extends WkTblPoliPartyUsageReportEntity> items = new Chunk<>(list);
        wkTblPoliPartyusageReportItemWriter.write(items);

        List<WkTblPoliPartyUsageReportEntity> listResult = wkTblPoliPartyUsageReportRepository.findAll();
        assertEquals(11, listResult.size(), ""); // 追加2件と元からある9件
        listResult.sort((e1, e2) -> e1.getWkTblPoliPartyUsageReportCode() - e2.getWkTblPoliPartyUsageReportCode());

        // 1件目
        WkTblPoliPartyUsageReportEntity result01 = listResult.get(9); // 必ず最終行(の一つ前)
        assertEquals(101L, result01.getTaskPlanPartyUsageDetailId(), "");

        /* 書証保存Dto */
        assertEquals(entity00.getCharset(), result01.getCharset(),"");
        assertEquals(entity00.getChildDir(), result01.getChildDir(), "");
        assertEquals(entity00.getFileName(), result01.getFileName(), "");
        assertEquals(entity00.getRegistTimeText(), result01.getRegistTimeText(), "");
        assertEquals(entity00.getShoshouCode(), result01.getShoshouCode(), "");
        assertEquals(entity00.getShoshouId(), result01.getShoshouId(), "");
        assertEquals(entity00.getShoshouKbn(), result01.getShoshouKbn(), "");

        /* 政治資金収支報告書政治団体Dto */
        assertEquals(entity00.getDaihyouName(), result01.getDaihyouName(), "");
        assertEquals(entity00.getDantaiName(), result01.getDantaiName(), "");
        assertEquals(entity00.getNendo(), result01.getNendo(), "");
        assertEquals(entity00.getOfferingDate(), result01.getOfferingDate(), "");
        assertEquals(entity00.getPoliticalOrganizationCode(), result01.getPoliticalOrganizationCode(), "");
        assertEquals(entity00.getPoliticalOrganizationId(), result01.getPoliticalOrganizationId(), "");
        assertEquals(entity00.getPoliticalOrganizationName(), result01.getPoliticalOrganizationName(), "");
        assertEquals(entity00.getRelationKbn(), result01.getRelationKbn(), "");
        assertEquals(entity00.getRelationPersonCodeDelegate(), result01.getRelationPersonCodeDelegate(), "");
        assertEquals(entity00.getRelationPersonIdDelegate(), result01.getRelationPersonIdDelegate(), "");
        assertEquals(entity00.getRelationPersonNameDelegate(), result01.getRelationPersonNameDelegate(), "");

        /* 政党交付金使途報告書表紙(様式8その1)Dto */
        assertEquals(entity00.getVersion(), result01.getVersion(), "");
        assertEquals(entity00.getApliName(), result01.getApliName(), "");
        assertEquals(entity00.getFlgApli(), result01.getFlgApli(), "");
        assertEquals(entity00.getFlgHonbu(), result01.getFlgHonbu(), "");

        assertEquals(entity00.getJohoUmuText(), result01.getJohoUmuText(), "");

        assertEquals(entity00.getKaikeiKijunKingaku(), result01.getKaikeiKijunKingaku(), "");

        assertEquals(entity00.getCopyRecipt(), result01.getCopyRecipt(), "");
        assertEquals(entity00.getAuditOption(), result01.getAuditOption(), "");
        assertEquals(entity00.getAuditReport(), result01.getAuditReport(), "");
        assertEquals(entity00.getShibuDocument(), result01.getShibuDocument(), "");
        assertEquals(entity00.getGoverningDocument(), result01.getGoverningDocument(), "");
        assertEquals(entity00.getFlgConfirm(), result01.getFlgConfirm(), "");
        assertEquals(entity00.getAccrualDate(), result01.getAccrualDate(), "");

        assertEquals(entity00.getNendo(), result01.getNendo(), "");
        assertEquals(entity00.getPartyName(), result01.getPartyName(), "");
        assertEquals(entity00.getPartyNameKana(), result01.getPartyNameKana(), "");
        assertEquals(entity00.getOfficeAddress(), result01.getOfficeAddress(), "");
        assertEquals(entity00.getDelegateName(), result01.getDelegateName(), "");
        assertEquals(entity00.getAccountManagerName(), result01.getAccountManagerName(), "");
        assertEquals(entity00.getWorker1Name(), result01.getWorker1Name(), "");
        assertEquals(entity00.getWorker1Tel(), result01.getWorker1Tel(), "");
        assertEquals(entity00.getWorker2Name(), result01.getWorker2Name(), "");
        assertEquals(entity00.getWorker2Tel(), result01.getWorker2Tel(), "");
        assertEquals(entity00.getShibuKbn(), result01.getShibuKbn(), "");
        assertEquals(entity00.getKaisanKbn(), result01.getKaisanKbn(), "");
        assertEquals(entity00.getKaisanDate(), result01.getKaisanDate(), "");
        assertEquals(entity00.getSeiriNo(), result01.getSeiriNo(), "");
        assertEquals(entity00.getUketsukeNo(), result01.getUketsukeNo(), "");

        // 2件目
        WkTblPoliPartyUsageReportEntity result02 = listResult.get(10); // 必ず最終行
        assertEquals(201L, result02.getTaskPlanPartyUsageDetailId(), "");
        assertEquals("abcdef", result02.getApliName(), "");

    }

}
