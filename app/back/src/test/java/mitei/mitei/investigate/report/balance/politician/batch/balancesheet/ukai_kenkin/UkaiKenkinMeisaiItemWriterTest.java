package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;

/**
 * UkaiKenkinMeisaiItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UkaiKenkinMeisaiItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UkaiKenkinMeisaiItemWriter ukaiKenkinMeisaiItemWriter;

    /** 迂回献金明細ワークテーブル1Repository */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("truncate_only_wk_tbl_ukai_kenkin.sql")
    void test() throws Exception { // NOPMD

        // 起動条件をセット
        StepExecution execution = this.getStepExecution();
        ukaiKenkinMeisaiItemWriter.beforeStep(execution);

        // リスト作成
        WkTblUkaiKenkinEntity entity = new WkTblUkaiKenkinEntity();

        entity.setAccrualDate("R4/9/30");
        entity.setAccrualDateValue(LocalDate.of(2022, 9, 30));
        entity.setHoukokuNen(2022);
        entity.setItemName("寄付");
        entity.setKingaku(15_003L);
        entity.setPickupStage(2);
        entity.setRenban(3);
        entity.setSaishinKbn(1);
        entity.setTablleId(106L);
        entity.setPoliticalOrgId(105L);
        entity.setPoliticalOrgCode(100);
        entity.setPoliticalOrgName("ABCD団体");
        entity.setPoliOrgDelegateId(45L);
        entity.setPoliOrgDelegateCode(40);
        entity.setPoliOrgDelegateName("ABCD代表者 太郎");
        entity.setPoliOrgAccountManagerId(59L);
        entity.setPoliOrgAccountManagerCode(50);
        entity.setPoliOrgAccountManagerName("ABCD会計責任者 良子");
        entity.setPoliOrgShikinDantaiId(625L);
        entity.setPoliOrgShikinDantaiCode(620);
        entity.setPoliOrgShikinDantaiName("資金管理団体責任者　三郎");
        entity.setPoliOrgKokkaiGiin1Id(30_225L);
        entity.setPoliOrgKokkaiGiin1Code(30_220);
        entity.setPoliOrgKokkaiGiin1Name("国会議員 1太郎");
        entity.setPoliOrgKokkaiGiin2Id(30_491L);
        entity.setPoliOrgKokkaiGiin2Code(30_490);
        entity.setPoliOrgKokkaiGiin2Name("国会議員 2太郎");
        entity.setPoliOrgKokkaiGiin3Id(30_588L);
        entity.setPoliOrgKokkaiGiin3Code(30_580);
        entity.setPoliOrgKokkaiGiin3Name("国会議員 3太郎");
        entity.setTradingPartnerId(11_413L);
        entity.setTradingPartnerCode(11_410);
        entity.setTradingPartnerName("ちゃらんぽらん政治団体3");
        entity.setTradingPartnerAddress("山形県実在市");
        entity.setTradingPartnerDelegateId(148L);
        entity.setTradingPartnerDelegateCode(140);
        entity.setTradingPartnerDelegateName("ちゃらんぽらん代表者 太郎");
        entity.setTradingOrgAccountManagerId(159L);
        entity.setTradingOrgAccountManagerCode(150);
        entity.setTradingOrgAccountManagerName("ちゃらんぽらん会計責任者 良子");
        entity.setTradingOrgShikinDantaiId(365L);
        entity.setTradingOrgShikinDantaiCode(360);
        entity.setTradingOrgShikinDantaiName("迂回　献金太郎");
        entity.setTradingOrgKokkaiGiin1Id(130_225L);
        entity.setTradingOrgKokkaiGiin1Code(130_220);
        entity.setTradingOrgKokkaiGiin1Name("ちゃらんぽらん国会議員 1太郎");
        entity.setTradingOrgKokkaiGiin2Id(130_491L);
        entity.setTradingOrgKokkaiGiin2Code(130_490);
        entity.setTradingOrgKokkaiGiin2Name("ちゃらんぽらん国会議員 2太郎");
        entity.setTradingOrgKokkaiGiin3Id(130_588L);
        entity.setTradingOrgKokkaiGiin3Code(130_580);
        entity.setTradingOrgKokkaiGiin3Name("ちゃらんぽらん国会議員 3太郎");

        List<WkTblUkaiKenkinEntity> listItems = new ArrayList<>();
        listItems.add(entity);

        // Chunkを作成してセット
        Chunk<? extends WkTblUkaiKenkinEntity> items = new Chunk<>(listItems);

        ukaiKenkinMeisaiItemWriter.write(items);

        List<WkTblUkaiKenkinEntity> list = wkTblUkaiKenkinRepository.findAll();
        assertEquals(1, list.size(), "1件記録");

        WkTblUkaiKenkinEntity kenkinEntity = list.get(0);
        assertEquals(entity.getAccrualDate(), kenkinEntity.getAccrualDate(), "発生日が一致");
        assertEquals(entity.getAccrualDateValue(), kenkinEntity.getAccrualDateValue(), "発生日実値が一致");
        assertEquals(entity.getHoukokuNen(), kenkinEntity.getHoukokuNen(), "報告年が一致");
        assertEquals(entity.getItemName(), kenkinEntity.getItemName(), "項目名が一致");
        assertEquals(entity.getKingaku(), kenkinEntity.getKingaku(), "金額一致");
        assertEquals(entity.getPickupStage(), kenkinEntity.getPickupStage(), "取得階層が一致");
        assertEquals(entity.getRenban(), kenkinEntity.getRenban(), "連番が一致");
        assertEquals(entity.getSaishinKbn(), kenkinEntity.getSaishinKbn(), "最新区分が一致");
        assertEquals(entity.getTablleId(), kenkinEntity.getTablleId(), "収入テーブルIdが一致");
        assertEquals(entity.getPoliticalOrgId(), kenkinEntity.getPoliticalOrgId(), "献金受け取り政治団体Idが一致");
        assertEquals(entity.getPoliticalOrgCode(), kenkinEntity.getPoliticalOrgCode(), "献金受け取り政治団体Codeが一致");
        assertEquals(entity.getPoliticalOrgName(), kenkinEntity.getPoliticalOrgName(), "献金受け取り政治団体名称が一致");
        assertEquals(entity.getPoliOrgDelegateId(), kenkinEntity.getPoliOrgDelegateId(), "献金受け取り政治団体代表者Idが一致");
        assertEquals(entity.getPoliOrgDelegateCode(), kenkinEntity.getPoliOrgDelegateCode(), "献金受け取り政治団体代表者Codeが一致");
        assertEquals(entity.getPoliOrgDelegateName(), kenkinEntity.getPoliOrgDelegateName(), "献金受け取り政治団体代表者名称が一致");
        assertEquals(entity.getPoliOrgAccountManagerId(), kenkinEntity.getPoliOrgAccountManagerId(),
                "献金受け取り政治団体会計責任者Idが一致");
        assertEquals(entity.getPoliOrgAccountManagerCode(), kenkinEntity.getPoliOrgAccountManagerCode(),
                "献金受け取り政治団体会計責任者Codeが一致");
        assertEquals(entity.getPoliOrgAccountManagerName(), kenkinEntity.getPoliOrgAccountManagerName(),
                "献金受け取り政治団体会計責任者名称が一致");
        assertEquals(entity.getPoliOrgShikinDantaiId(), kenkinEntity.getPoliOrgShikinDantaiId(),
                "献金受け取り政治団体資金管理団体責任者Idが一致");
        assertEquals(entity.getPoliOrgShikinDantaiCode(), kenkinEntity.getPoliOrgShikinDantaiCode(),
                "献金受け取り政治団体資金管理団体責任者Codeが一致");
        assertEquals(entity.getPoliOrgShikinDantaiName(), kenkinEntity.getPoliOrgShikinDantaiName(),
                "献金受け取り政治団体資金管理団体責任者名称が一致");
        assertEquals(entity.getPoliOrgKokkaiGiin1Id(), kenkinEntity.getPoliOrgKokkaiGiin1Id(), "献金受け取り政治団体国会議員1Idが一致");
        assertEquals(entity.getPoliOrgKokkaiGiin1Code(), kenkinEntity.getPoliOrgKokkaiGiin1Code(),
                "献金受け取り政治団体国会議員1Codeが一致");
        assertEquals(entity.getPoliOrgKokkaiGiin1Name(), kenkinEntity.getPoliOrgKokkaiGiin1Name(),
                "献金受け取り政治団体国会議員1名称が一致");
        assertEquals(entity.getPoliOrgKokkaiGiin2Id(), kenkinEntity.getPoliOrgKokkaiGiin2Id(), "献金受け取り政治団体国会議員2Idが一致");
        assertEquals(entity.getPoliOrgKokkaiGiin2Code(), kenkinEntity.getPoliOrgKokkaiGiin2Code(),
                "献金受け取り政治団体国会議員2Codeが一致");
        assertEquals(entity.getPoliOrgKokkaiGiin2Name(), kenkinEntity.getPoliOrgKokkaiGiin2Name(),
                "献金受け取り政治団体国会議員2名称が一致");
        assertEquals(entity.getPoliOrgKokkaiGiin3Id(), kenkinEntity.getPoliOrgKokkaiGiin3Id(), "献金受け取り政治団体国会議員3Idが一致");
        assertEquals(entity.getPoliOrgKokkaiGiin3Code(), kenkinEntity.getPoliOrgKokkaiGiin3Code(),
                "献金受け取り政治団体国会議員3Codeが一致");
        assertEquals(entity.getPoliOrgKokkaiGiin3Name(), kenkinEntity.getPoliOrgKokkaiGiin3Name(),
                "献金受け取り政治団体国会議員3名称が一致");
        assertEquals(entity.getTradingPartnerId(), kenkinEntity.getTradingPartnerId(), "取引相手(献金渡す)政治団体Idが一致");
        assertEquals(entity.getTradingPartnerCode(), kenkinEntity.getTradingPartnerCode(), "取引相手政治団体Codegが一致");
        assertEquals(entity.getTradingPartnerName(), kenkinEntity.getTradingPartnerName(), "取引相手政治団体名称が一致");
        assertEquals(entity.getTradingPartnerAddress(), kenkinEntity.getTradingPartnerAddress(), "取引相手政治団体住所が一致");
        assertEquals(entity.getTradingPartnerDelegateId(), kenkinEntity.getTradingPartnerDelegateId(),
                "取引相手政治団体代表者Idが一致");
        assertEquals(entity.getTradingPartnerDelegateCode(), kenkinEntity.getTradingPartnerDelegateCode(),
                "取引相手政治団体代表者Codeが一致");
        assertEquals(entity.getTradingPartnerDelegateName(), kenkinEntity.getTradingPartnerDelegateName(),
                "取引相手政治団体代表者名称が一致");
        assertEquals(entity.getTradingOrgAccountManagerId(), kenkinEntity.getTradingOrgAccountManagerId(),
                "取引相手政治団体会計責任者Idが一致");
        assertEquals(entity.getTradingOrgAccountManagerCode(), kenkinEntity.getTradingOrgAccountManagerCode(),
                "取引相手政治団体会計責任者Codeが一致");
        assertEquals(entity.getTradingOrgAccountManagerName(), kenkinEntity.getTradingOrgAccountManagerName(),
                "取引相手政治団体会計責任者名称が一致");
        assertEquals(entity.getTradingOrgShikinDantaiId(), kenkinEntity.getTradingOrgShikinDantaiId(),
                "取引相手政治団体資金管理団体責任者Idが一致");
        assertEquals(entity.getTradingOrgShikinDantaiCode(), kenkinEntity.getTradingOrgShikinDantaiCode(),
                "取引相手政治団体資金管理団体責任Code者が一致");
        assertEquals(entity.getTradingOrgShikinDantaiName(), kenkinEntity.getTradingOrgShikinDantaiName(),
                "取引相手政治団体資金管理団体責任者名称が一致");
        assertEquals(entity.getTradingOrgKokkaiGiin1Id(), kenkinEntity.getTradingOrgKokkaiGiin1Id(),
                "取引相手政治団体国会議員1Idが一致");
        assertEquals(entity.getTradingOrgKokkaiGiin1Code(), kenkinEntity.getTradingOrgKokkaiGiin1Code(),
                "取引相手政治団体国会議員1Codeが一致");
        assertEquals(entity.getTradingOrgKokkaiGiin1Name(), kenkinEntity.getTradingOrgKokkaiGiin1Name(),
                "取引相手政治団体国会議員1名称が一致");
        assertEquals(entity.getTradingOrgKokkaiGiin2Id(), kenkinEntity.getTradingOrgKokkaiGiin2Id(),
                "取引相手政治団体国会議員2Idが一致");
        assertEquals(entity.getTradingOrgKokkaiGiin2Code(), kenkinEntity.getTradingOrgKokkaiGiin2Code(),
                "取引相手政治団体国会議員2Codeが一致");
        assertEquals(entity.getTradingOrgKokkaiGiin2Name(), kenkinEntity.getTradingOrgKokkaiGiin2Name(),
                "取引相手政治団体国会議員2名称が一致");
        assertEquals(entity.getTradingOrgKokkaiGiin3Id(), kenkinEntity.getTradingOrgKokkaiGiin3Id(),
                "取引相手政治団体国会議員3Idが一致");
        assertEquals(entity.getTradingOrgKokkaiGiin3Code(), kenkinEntity.getTradingOrgKokkaiGiin3Code(),
                "取引相手政治団体国会議員3Codeが一致");
        assertEquals(entity.getTradingOrgKokkaiGiin3Name(), kenkinEntity.getTradingOrgKokkaiGiin3Name(),
                "取引相手政治団体国会議員3名称が一致");

    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", 123_321L).addLong("userCode", 987L).addString("userName", "ユーザ")
                .addLong("houkokuNen", 2022L).addString("isSearchKoufukin", "false")
                .addLocalDateTime("executeTime", LocalDateTime.now()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
