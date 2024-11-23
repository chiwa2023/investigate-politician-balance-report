package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDetailRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;

/**
 * WkTblPoliOrgBalancesheetReportItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class WkTblPoliOrgBalancesheetReportItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private WkTblPoliOrgBalancesheetReportItemWriter wkTblPoliOrgBalancesheetReportItemWriter;

    /** 政治資金収支報告書登録準備ワークテーブルレポジトリ */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

    /** 政治資金収支報告書登録計画レポジトリ */
    @Autowired
    private TaskPlanBalancesheetDetailRepository taskPlanBalancesheetDetailRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "wk_tbl_poli_org_balancesheet_report.sql", "task_plan_balancesheet_detail.sql" })
    void test() { // NOPMD

        WkTblPoliOrgBalancesheetReportEntity entity00 = new WkTblPoliOrgBalancesheetReportEntity();

        entity00.setWkTblPoliOrgBalancesheetReportCode(103);
        entity00.setTaskPlanBalancesheetDetailId(101L);

        /* 書証保存Dto */
        entity00.setCharset("Windows-31J");
        entity00.setChildDir("912/20220403/abc");
        entity00.setFileName("sito/xml");
        entity00.setRegistTimeText("20220403");
        entity00.setShoshouCode(321L);
        entity00.setShoshouId(320L);
        entity00.setShoshouKbn(0);

        /* 政治資金収支報告書政治団体Dto */
        entity00.setDaihyouName("代表名");
        entity00.setDantaiName("団体名");
        entity00.setHoukokuNen(2022);
        entity00.setIsAddOrganization(true);
        entity00.setOfferingDate(LocalDate.of(2022, 12, 19));
        entity00.setPoliticalOrganizationCode(444);
        entity00.setPoliticalOrganizationId(440L);
        entity00.setPoliticalOrganizationName("登録政治団体名");
        entity00.setRelationKbn(2);
        entity00.setRelationPersonCodeDelegate(525);
        entity00.setRelationPersonIdDelegate(520L);
        entity00.setRelationPersonNameDelegate("登録代表者名");

        /* 政治資金収支報告書表紙(様式7その1)Dto */
        entity00.setDaihyoushaNameFirst("代表者姓");
        entity00.setDaihyoushaNameLast("代表者名");
        entity00.setDantaiKbn("2");
        entity00.setDantaiName01("原文書団体名");
        entity00.setDantaiNameKana("団体名かな");
        entity00.setDateKaisai("特別パーティ開催日");
        entity00.setGiinDantantaiTokureiPeriodEnd("期間終了");
        entity00.setGiinDantantaiTokureiPeriodRest("追加の期間");
        entity00.setGiinDantantaiTokureiPeriodStart("期間開始");
        // entity00.setHoukokuNen();
        entity00.setJimushoJusho("事務所住所");
        entity00.setJimushoJushoTatemono("事務所建物");
        entity00.setJimuTantousha1NameFirst("担当者名1");
        entity00.setJimuTantousha1NameLast("担当者姓1");
        entity00.setJimuTantousha1Tel("000-111");
        entity00.setJimuTantousha2NameFirst("担当者名2");
        entity00.setJimuTantousha2NameLast("担当者姓2");
        entity00.setJimuTantousha2Tel("111-222");
        entity00.setJimuTantousha3NameFirst("担当者名3");
        entity00.setJimuTantousha3NameLast("担当者姓3");
        entity00.setJimuTantousha3Tel("222-333");
        entity00.setKaikeiSekinnshaNameFirst("会計責任者名");
        entity00.setKaikeiSekinnshaNameLast("会計責任者姓");
        entity00.setKanriDantaiPeriodEnd("管理団体期間終了");
        entity00.setKanriDantaiPeriodRest("期間残り");
        entity00.setKanriDantaiPeriodStart("管理団体期間開始");
        entity00.setKatsudouKuikiKbn(2);
        entity00.setKokkaiGiin1GenKouho("候補1");
        entity00.setKokkaiGiin1NameFirst("国会議員1名");
        entity00.setKokkaiGiin1NameLast("国会議員1姓");
        entity00.setKokkaiGiin1ShuuSan("衆参1");
        entity00.setKokkaiGiin2GenKouho("候補2");
        entity00.setKokkaiGiin2NameFirst("国会議員2名");
        entity00.setKokkaiGiin2NameLast("国会議員2姓");
        entity00.setKokkaiGiin2ShuuSan("衆参2");
        entity00.setKokkaiGiin3GenKouho("候補3");
        entity00.setKokkaiGiin3NameFirst("国会議員3名");
        entity00.setKokkaiGiin3NameLast("国会議員3姓");
        entity00.setKokkaiGiin3ShuuSan("衆参3");
        entity00.setKokkaiGiinDantaiKbn(1);
        entity00.setKoushokuName("公職名");
        entity00.setShikinDaihyouName1("資金団体名1");
        entity00.setShikinDaihyouName2("資金団体名2");
        entity00.setUmuShikinKanrenDantai(0);

        WkTblPoliOrgBalancesheetReportEntity entity01 = new WkTblPoliOrgBalancesheetReportEntity();
        entity01.setWkTblPoliOrgBalancesheetReportCode(214);
        entity01.setGiinDantantaiTokureiPeriodEnd("abcdef");
        entity01.setTaskPlanBalancesheetDetailId(201L);

        List<WkTblPoliOrgBalancesheetReportEntity> list = new ArrayList<>();
        list.add(entity00);
        list.add(entity01);

        // Chunkを作成してセット
        Chunk<? extends WkTblPoliOrgBalancesheetReportEntity> items = new Chunk<>(list);
        wkTblPoliOrgBalancesheetReportItemWriter.write(items);

        List<WkTblPoliOrgBalancesheetReportEntity> listResult = wkTblPoliOrgBalancesheetReportRepository.findAll();
        assertThat(listResult.size()).isEqualTo(3); // 追加2件と元からある1件
        listResult.sort(
                (e1, e2) -> e1.getWkTblPoliOrgBalancesheetReportCode() - e2.getWkTblPoliOrgBalancesheetReportCode());

        // 1件目
        WkTblPoliOrgBalancesheetReportEntity result01 = listResult.get(1);
        assertThat(result01.getWkTblPoliOrgBalancesheetReportCode()).isEqualTo(3);

        /* 書証保存Dto */
        assertThat(result01.getCharset()).isEqualTo(entity00.getCharset());
        assertThat(result01.getChildDir()).isEqualTo(entity00.getChildDir());
        assertThat(result01.getFileName()).isEqualTo(entity00.getFileName());
        assertThat(result01.getRegistTimeText()).isEqualTo(entity00.getRegistTimeText());
        assertThat(result01.getShoshouCode()).isEqualTo(entity00.getShoshouCode());
        assertThat(result01.getShoshouId()).isEqualTo(entity00.getShoshouId());
        assertThat(result01.getShoshouKbn()).isEqualTo(entity00.getShoshouKbn());

        /* 政治資金収支報告書政治団体Dto */
        assertThat(result01.getDaihyouName()).isEqualTo(entity00.getDaihyouName());
        assertThat(result01.getDantaiName()).isEqualTo(entity00.getDantaiName());
        assertThat(result01.getHoukokuNen()).isEqualTo(entity00.getHoukokuNen());
        assertThat(result01.getIsAddOrganization()).isEqualTo(entity00.getIsAddOrganization());
        assertThat(result01.getOfferingDate()).isEqualTo(entity00.getOfferingDate());
        assertThat(result01.getPoliticalOrganizationCode()).isEqualTo(entity00.getPoliticalOrganizationCode());
        assertThat(result01.getPoliticalOrganizationId()).isEqualTo(entity00.getPoliticalOrganizationId());
        assertThat(result01.getPoliticalOrganizationName()).isEqualTo(entity00.getPoliticalOrganizationName());
        assertThat(result01.getRelationKbn()).isEqualTo(entity00.getRelationKbn());
        assertThat(result01.getRelationPersonCodeDelegate()).isEqualTo(entity00.getRelationPersonCodeDelegate());
        assertThat(result01.getRelationPersonIdDelegate()).isEqualTo(entity00.getRelationPersonIdDelegate());
        assertThat(result01.getRelationPersonNameDelegate()).isEqualTo(entity00.getRelationPersonNameDelegate());

        /* 政治資金収支報告書表紙(様式7その1)Dto */
        assertThat(result01.getDaihyoushaNameFirst()).isEqualTo(entity00.getDaihyoushaNameFirst());
        assertThat(result01.getDaihyoushaNameLast()).isEqualTo(entity00.getDaihyoushaNameLast());
        assertThat(result01.getDantaiKbn()).isEqualTo(entity00.getDantaiKbn());
        assertThat(result01.getDantaiName01()).isEqualTo(entity00.getDantaiName01());
        assertThat(result01.getDantaiNameKana()).isEqualTo(entity00.getDantaiNameKana());
        assertThat(result01.getDateKaisai()).isEqualTo(entity00.getDateKaisai());
        assertThat(result01.getGiinDantantaiTokureiPeriodEnd()).isEqualTo(entity00.getGiinDantantaiTokureiPeriodEnd());
        assertThat(result01.getGiinDantantaiTokureiPeriodRest())
                .isEqualTo(entity00.getGiinDantantaiTokureiPeriodRest());
        assertThat(result01.getGiinDantantaiTokureiPeriodStart())
                .isEqualTo(entity00.getGiinDantantaiTokureiPeriodStart());
        assertThat(result01.getHoukokuNen()).isEqualTo(entity00.getHoukokuNen());
        assertThat(result01.getJimushoJusho()).isEqualTo(entity00.getJimushoJusho());
        assertThat(result01.getJimushoJushoTatemono()).isEqualTo(entity00.getJimushoJushoTatemono());
        assertThat(result01.getJimuTantousha1NameFirst()).isEqualTo(entity00.getJimuTantousha1NameFirst());
        assertThat(result01.getJimuTantousha1NameLast()).isEqualTo(entity00.getJimuTantousha1NameLast());
        assertThat(result01.getJimuTantousha1Tel()).isEqualTo(entity00.getJimuTantousha1Tel());
        assertThat(result01.getJimuTantousha2NameFirst()).isEqualTo(entity00.getJimuTantousha2NameFirst());
        assertThat(result01.getJimuTantousha2NameLast()).isEqualTo(entity00.getJimuTantousha2NameLast());
        assertThat(result01.getJimuTantousha2Tel()).isEqualTo(entity00.getJimuTantousha2Tel());
        assertThat(result01.getJimuTantousha3NameFirst()).isEqualTo(entity00.getJimuTantousha3NameFirst());
        assertThat(result01.getJimuTantousha3NameLast()).isEqualTo(entity00.getJimuTantousha3NameLast());
        assertThat(result01.getJimuTantousha3Tel()).isEqualTo(entity00.getJimuTantousha3Tel());
        assertThat(result01.getKaikeiSekinnshaNameFirst()).isEqualTo(entity00.getKaikeiSekinnshaNameFirst());
        assertThat(result01.getKaikeiSekinnshaNameLast()).isEqualTo(entity00.getKaikeiSekinnshaNameLast());
        assertThat(result01.getKanriDantaiPeriodEnd()).isEqualTo(entity00.getKanriDantaiPeriodEnd());
        assertThat(result01.getKanriDantaiPeriodRest()).isEqualTo(entity00.getKanriDantaiPeriodRest());
        assertThat(result01.getKanriDantaiPeriodStart()).isEqualTo(entity00.getKanriDantaiPeriodStart());
        assertThat(result01.getKatsudouKuikiKbn()).isEqualTo(entity00.getKatsudouKuikiKbn());
        assertThat(result01.getKokkaiGiin1GenKouho()).isEqualTo(entity00.getKokkaiGiin1GenKouho());
        assertThat(result01.getKokkaiGiin1NameFirst()).isEqualTo(entity00.getKokkaiGiin1NameFirst());
        assertThat(result01.getKokkaiGiin1NameLast()).isEqualTo(entity00.getKokkaiGiin1NameLast());
        assertThat(result01.getKokkaiGiin1ShuuSan()).isEqualTo(entity00.getKokkaiGiin1ShuuSan());
        assertThat(result01.getKokkaiGiin2GenKouho()).isEqualTo(entity00.getKokkaiGiin2GenKouho());
        assertThat(result01.getKokkaiGiin2NameFirst()).isEqualTo(entity00.getKokkaiGiin2NameFirst());
        assertThat(result01.getKokkaiGiin2NameLast()).isEqualTo(entity00.getKokkaiGiin2NameLast());
        assertThat(result01.getKokkaiGiin2ShuuSan()).isEqualTo(entity00.getKokkaiGiin2ShuuSan());
        assertThat(result01.getKokkaiGiin3GenKouho()).isEqualTo(entity00.getKokkaiGiin3GenKouho());
        assertThat(result01.getKokkaiGiin3NameFirst()).isEqualTo(entity00.getKokkaiGiin3NameFirst());
        assertThat(result01.getKokkaiGiin3NameLast()).isEqualTo(entity00.getKokkaiGiin3NameLast());
        assertThat(result01.getKokkaiGiin3ShuuSan()).isEqualTo(entity00.getKokkaiGiin3ShuuSan());
        assertThat(result01.getKokkaiGiinDantaiKbn()).isEqualTo(entity00.getKokkaiGiinDantaiKbn());
        assertThat(result01.getKoushokuName()).isEqualTo(entity00.getKoushokuName());
        assertThat(result01.getShikinDaihyouName1()).isEqualTo(entity00.getShikinDaihyouName1());
        assertThat(result01.getShikinDaihyouName2()).isEqualTo(entity00.getShikinDaihyouName2());
        assertThat(result01.getUmuShikinKanrenDantai()).isEqualTo(entity00.getUmuShikinKanrenDantai());

        // 2件目
        WkTblPoliOrgBalancesheetReportEntity result02 = listResult.get(2);
        assertThat(result02.getWkTblPoliOrgBalancesheetReportCode()).isEqualTo(4);
        assertThat(result02.getGiinDantantaiTokureiPeriodEnd()).isEqualTo("abcdef");

        List<TaskPlanBalancesheetDetailEntity> listTask = taskPlanBalancesheetDetailRepository.findAll();

        TaskPlanBalancesheetDetailEntity task01 = this.getTask(101L, listTask);
        assertThat(task01.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());
        assertTrue(task01.getIsFinished(), "処理完了");

        TaskPlanBalancesheetDetailEntity task02 = this.getTask(201L, listTask);
        assertThat(task02.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.UPDATE.value());
        assertTrue(task02.getIsFinished(), "処理完了");

    }

    private TaskPlanBalancesheetDetailEntity getTask(final Long taskPlanId,
            final List<TaskPlanBalancesheetDetailEntity> listTask) {

        for (TaskPlanBalancesheetDetailEntity entity : listTask) {
            if (taskPlanId.equals(entity.getTaskPlanBalancesheetDetailId())) {
                return entity;
            }
        }

        return null;
    }
}
