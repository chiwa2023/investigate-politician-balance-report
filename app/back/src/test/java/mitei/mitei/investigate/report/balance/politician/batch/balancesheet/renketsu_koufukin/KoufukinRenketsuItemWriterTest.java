package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.renketsu_koufukin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import mitei.mitei.investigate.report.balance.politician.entity.WkTblRenketsuKoufukinEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblRenketsuKoufukinRepository;

/**
 * KoufukinRenketsuItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class KoufukinRenketsuItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private KoufukinRenketsuItemWriter koufukinRenketsuItemWriter;

    /** 交付金連結ワークテーブルRepository */
    @Autowired
    private WkTblRenketsuKoufukinRepository wkTblRenketsuKoufukinRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("truncate_wk_tbl_renketsu_koufukin.sql")
    void test() { // NOPMD

        WkTblRenketsuKoufukinEntity entity = new WkTblRenketsuKoufukinEntity();
        entity.setAccrualDateValue(LocalDate.of(2022, 7, 20));
        entity.setAmountAll(12_345L);
        entity.setAmountKoufukin(10_000L);
        entity.setAmountMyFunds(2345L);
        entity.setBalanceHimoku("a");
        entity.setBalanceMokuteki("b");
        entity.setBalancesheetId(999L);
        entity.setBalancesheetOfferingDate(LocalDate.of(2023, 2, 19));
        entity.setDaihyouName("c");
        entity.setDantaiName("k");
        entity.setDataKbn(2);
        entity.setHoukokuNen(2022);
        entity.setInsertTimestamp(LocalDateTime.of(1999, 9, 19, 9, 19));
        entity.setInsertUserCode(111);
        entity.setInsertUserId(112L);
        entity.setInsertUserName("d");
        entity.setPayeeAddress("e");
        entity.setPayeeName("f");
        entity.setSaishinKbn(1);
        entity.setUpdateTimestamp(LocalDateTime.of(2007, 7, 17, 7, 17));
        entity.setUpdateUserCode(333);
        entity.setUpdateUserId(334L);
        entity.setUpdateUserName("g");
        entity.setUsageHimoku("h");
        entity.setUsageOfferingDate(LocalDate.of(2023, 1, 16));
        entity.setUsageReportId(777L);
        entity.setUsageShishutsuName("i");
        entity.setUsageUsageItem("j");
        entity.setWkTblRenketsuKoufukinId(0L); // auto_increment

        List<WkTblRenketsuKoufukinEntity> list = new ArrayList<>();
        list.add(entity);

        // Chunkを作成してセット
        Chunk<? extends WkTblRenketsuKoufukinEntity> items = new Chunk<>(list);

        koufukinRenketsuItemWriter.write(items);

        List<WkTblRenketsuKoufukinEntity> listAnswer = wkTblRenketsuKoufukinRepository.findAll();
        assertEquals(1, listAnswer.size(), "1件登録");

        WkTblRenketsuKoufukinEntity entityRecord = listAnswer.get(0);

        assertEquals(entity.getAccrualDateValue(), entityRecord.getAccrualDateValue());
        assertEquals(entity.getAmountAll(), entityRecord.getAmountAll());
        assertEquals(entity.getAmountKoufukin(), entityRecord.getAmountKoufukin());
        assertEquals(entity.getAmountMyFunds(), entityRecord.getAmountMyFunds());
        assertEquals(entity.getBalanceHimoku(), entityRecord.getBalanceHimoku());
        assertEquals(entity.getBalanceMokuteki(), entityRecord.getBalanceMokuteki());
        assertEquals(entity.getBalancesheetId(), entityRecord.getBalancesheetId());
        assertEquals(entity.getBalancesheetOfferingDate(), entityRecord.getBalancesheetOfferingDate());
        assertEquals(entity.getDaihyouName(), entityRecord.getDaihyouName());
        assertEquals(entity.getDantaiName(), entityRecord.getDantaiName());
        assertEquals(entity.getDataKbn(), entityRecord.getDataKbn());
        assertEquals(entity.getHoukokuNen(), entityRecord.getHoukokuNen());
        assertEquals(entity.getInsertTimestamp(), entityRecord.getInsertTimestamp());
        assertEquals(entity.getInsertUserCode(), entityRecord.getInsertUserCode());
        assertEquals(entity.getInsertUserId(), entityRecord.getInsertUserId());
        assertEquals(entity.getInsertUserName(), entityRecord.getInsertUserName());
        assertEquals(entity.getPayeeAddress(), entityRecord.getPayeeAddress());
        assertEquals(entity.getPayeeName(), entityRecord.getPayeeName());
        assertEquals(entity.getSaishinKbn(), entityRecord.getSaishinKbn());
        assertEquals(entity.getUpdateTimestamp(), entityRecord.getUpdateTimestamp());
        assertEquals(entity.getUpdateUserCode(), entityRecord.getUpdateUserCode());
        assertEquals(entity.getUpdateUserId(), entityRecord.getUpdateUserId());
        assertEquals(entity.getUpdateUserName(), entityRecord.getUpdateUserName());
        assertEquals(entity.getUsageHimoku(), entityRecord.getUsageHimoku());
        assertEquals(entity.getUsageOfferingDate(), entityRecord.getUsageOfferingDate());
        assertEquals(entity.getUsageReportId(), entityRecord.getUsageReportId());
        assertEquals(entity.getUsageShishutsuName(), entityRecord.getUsageShishutsuName());
        assertEquals(entity.getUsageUsageItem(), entityRecord.getUsageUsageItem());
    }

}
