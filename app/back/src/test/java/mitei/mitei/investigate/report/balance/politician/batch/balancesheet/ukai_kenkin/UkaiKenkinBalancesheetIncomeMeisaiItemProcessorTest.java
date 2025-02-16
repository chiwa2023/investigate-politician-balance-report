package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.OfferingBalancesheetIncomeEntity;

/**
 * UkaiKenkinBalancesheetIncomeMeisaiItemProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UkaiKenkinBalancesheetIncomeMeisaiItemProcessorTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UkaiKenkinBalancesheetIncomeMeisaiItemProcessor ukaiKenkinBalancesheetIncomeMeisaiItemProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("tasklet_stage0_poli_org_property.sql")
    void test() throws Exception { // NOPMD

        OfferingBalancesheetIncomeEntity incomeEntity = new OfferingBalancesheetIncomeEntity();

        incomeEntity.setOfferingBalancesheetIncomeId(106L);
        incomeEntity.setOfferingBalancesheetIncomeCode(105L);
        incomeEntity.setSaishinKbn(1);
        incomeEntity.setDocumentCode(882L);
        incomeEntity.setHoukokuNen(2022);
        incomeEntity.setOfferingDate(LocalDate.of(2023, 2, 17));
        incomeEntity.setPoliticalOrganizationId(105L);
        incomeEntity.setPoliticalOrganizationCode(100);
        incomeEntity.setPoliticalOrganizationName("ABCD団体");
        incomeEntity.setDantaiKbn("04");
        incomeEntity.setDaihyouName("代表者あいうえお");
        incomeEntity.setDantaiName("えーびーしーでぃーだんたい");
        incomeEntity.setRelationKbn(1);
        incomeEntity.setRelationPersonIdDelegate(9898L);
        incomeEntity.setRelationPersonCodeDelegate(9867);
        incomeEntity.setRelationPersonNameDelegate("システム代表者");
        incomeEntity.setYoushikiKbn(7);
        incomeEntity.setYoushikiEdaKbn(3);
        incomeEntity.setPageTotal(0L);
        incomeEntity.setMimanTotal("");
        incomeEntity.setSonotaTotal("");
        incomeEntity.setPartyName("");
        incomeEntity.setSortNo("");
        incomeEntity.setIchirenNo(3);
        incomeEntity.setItemName("寄付");
        incomeEntity.setKingaku(15_003L);
        incomeEntity.setAccrualDate("R4/9/30");
        incomeEntity.setAccrualDateValue(LocalDate.of(2022, 9, 30));
        incomeEntity.setBikou("");
        incomeEntity.setPartnerName("ちゃらんぽらん政治団体3");
        incomeEntity.setPartnerJuusho("山形県実在市");
        incomeEntity.setShiharaisu(0);
        incomeEntity.setKaisaiBasho("");
        incomeEntity.setFlgZeigakuKohjo(Short.valueOf("0"));
        incomeEntity.setTohshibangou(3);
        incomeEntity.setGyoukubun(Short.valueOf("0"));
        incomeEntity.setShokugyou("ちゃらんぽらん　2三郎");
        incomeEntity.setPeriodMediate("");
        incomeEntity.setRelationPersonIdIncome(0L);
        incomeEntity.setRelationPersonCodeIncome(0);
        incomeEntity.setRelationPersonNameIncome("");
        incomeEntity.setRelationCorpIdIncome(0L);
        incomeEntity.setRelationCorpCodeIncome(0);
        incomeEntity.setRelationCorpNameInccome("");
        incomeEntity.setRelationPoliticalOrgIdIncome(11_413L);
        incomeEntity.setRelationPoliticalOrgCodeIncome(11_410);
        incomeEntity.setRelationPoliticalOrgNameIncome("ちゃらんぽらん政治団体3");

        incomeEntity.setSearchWords("政治団体迂回寄付(3)");

        WkTblUkaiKenkinEntity kenkinEntity = ukaiKenkinBalancesheetIncomeMeisaiItemProcessor.process(incomeEntity);

        assertEquals("R4/9/30", kenkinEntity.getAccrualDate(), "発生日が一致");
        assertEquals(LocalDate.of(2022, 9, 30), kenkinEntity.getAccrualDateValue(), "発生日実値が一致");
        assertEquals(2022, kenkinEntity.getHoukokuNen(), "報告年が一致");
        assertEquals("寄付", kenkinEntity.getItemName(), "項目名が一致");
        assertEquals(15_003L, kenkinEntity.getKingaku(), "金額一致");
        assertEquals(0, kenkinEntity.getPickupStage(), "取得階層が一致");
        assertEquals(3, kenkinEntity.getRenban(), "連番が一致");
        assertEquals(1, kenkinEntity.getSaishinKbn(), "最新区分が一致");
        assertEquals(106L, kenkinEntity.getTablleId(), "収入テーブルIdが一致");

        assertEquals(105L, kenkinEntity.getPoliticalOrgId(), "献金受け取り政治団体Idが一致");
        assertEquals(100, kenkinEntity.getPoliticalOrgCode(), "献金受け取り政治団体Codeが一致");
        assertEquals("ABCD団体", kenkinEntity.getPoliticalOrgName(), "献金受け取り政治団体名称が一致");

        assertEquals(45L, kenkinEntity.getPoliOrgDelegateId(), "献金受け取り政治団体代表者Idが一致");
        assertEquals(40, kenkinEntity.getPoliOrgDelegateCode(), "献金受け取り政治団体代表者Codeが一致");
        assertEquals("ABCD代表者 太郎", kenkinEntity.getPoliOrgDelegateName(), "献金受け取り政治団体代表者名称が一致");

        assertEquals(59L, kenkinEntity.getPoliOrgAccountManagerId(), "献金受け取り政治団体会計責任者Idが一致");
        assertEquals(50, kenkinEntity.getPoliOrgAccountManagerCode(), "献金受け取り政治団体会計責任者Codeが一致");
        assertEquals("ABCD会計責任者 良子", kenkinEntity.getPoliOrgAccountManagerName(), "献金受け取り政治団体会計責任者名称が一致");

        assertEquals(625L, kenkinEntity.getPoliOrgShikinDantaiId(), "献金受け取り政治団体資金管理団体責任者Idが一致");
        assertEquals(620, kenkinEntity.getPoliOrgShikinDantaiCode(), "献金受け取り政治団体資金管理団体責任者Codeが一致");
        assertEquals("資金管理団体責任者　三郎", kenkinEntity.getPoliOrgShikinDantaiName(), "献金受け取り政治団体資金管理団体責任者名称が一致");

        assertEquals(30_225L, kenkinEntity.getPoliOrgKokkaiGiin1Id(), "献金受け取り政治団体国会議員1Idが一致");
        assertEquals(30_220, kenkinEntity.getPoliOrgKokkaiGiin1Code(), "献金受け取り政治団体国会議員1Codeが一致");
        assertEquals("国会議員 1太郎", kenkinEntity.getPoliOrgKokkaiGiin1Name(), "献金受け取り政治団体国会議員1名称が一致");

        assertEquals(30_491L, kenkinEntity.getPoliOrgKokkaiGiin2Id(), "献金受け取り政治団体国会議員2Idが一致");
        assertEquals(30_490, kenkinEntity.getPoliOrgKokkaiGiin2Code(), "献金受け取り政治団体国会議員2Codeが一致");
        assertEquals("国会議員 2太郎", kenkinEntity.getPoliOrgKokkaiGiin2Name(), "献金受け取り政治団体国会議員2名称が一致");

        assertEquals(30_588L, kenkinEntity.getPoliOrgKokkaiGiin3Id(), "献金受け取り政治団体国会議員3Idが一致");
        assertEquals(30_580, kenkinEntity.getPoliOrgKokkaiGiin3Code(), "献金受け取り政治団体国会議員3Codeが一致");
        assertEquals("国会議員 3太郎", kenkinEntity.getPoliOrgKokkaiGiin3Name(), "献金受け取り政治団体国会議員3名称が一致");

        assertEquals(11_413L, kenkinEntity.getTradingPartnerId(), "取引相手(献金渡す)政治団体Idが一致");
        assertEquals(11_410, kenkinEntity.getTradingPartnerCode(), "取引相手政治団体Codegが一致");
        assertEquals("ちゃらんぽらん政治団体3", kenkinEntity.getTradingPartnerName(), "取引相手政治団体名称が一致");
        assertEquals("山形県実在市", kenkinEntity.getTradingPartnerAddress(), "取引相手政治団体住所が一致");

        assertEquals(148L, kenkinEntity.getTradingPartnerDelegateId(), "取引相手政治団体代表者Idが一致");
        assertEquals(140, kenkinEntity.getTradingPartnerDelegateCode(), "取引相手政治団体代表者Codeが一致");
        assertEquals("ちゃらんぽらん代表者 太郎", kenkinEntity.getTradingPartnerDelegateName(), "取引相手政治団体代表者名称が一致");

        assertEquals(159L, kenkinEntity.getTradingOrgAccountManagerId(), "取引相手政治団体会計責任者Idが一致");
        assertEquals(150, kenkinEntity.getTradingOrgAccountManagerCode(), "取引相手政治団体会計責任者Codeが一致");
        assertEquals("ちゃらんぽらん会計責任者 良子", kenkinEntity.getTradingOrgAccountManagerName(), "取引相手政治団体会計責任者名称が一致");

        assertEquals(365L, kenkinEntity.getTradingOrgShikinDantaiId(), "取引相手政治団体資金管理団体責任者Idが一致");
        assertEquals(360, kenkinEntity.getTradingOrgShikinDantaiCode(), "取引相手政治団体資金管理団体責任Code者が一致");
        assertEquals("迂回　献金太郎", kenkinEntity.getTradingOrgShikinDantaiName(), "取引相手政治団体資金管理団体責任者名称が一致");

        assertEquals(130_225L, kenkinEntity.getTradingOrgKokkaiGiin1Id(), "取引相手政治団体国会議員1Idが一致");
        assertEquals(130_220, kenkinEntity.getTradingOrgKokkaiGiin1Code(), "取引相手政治団体国会議員1Codeが一致");
        assertEquals("ちゃらんぽらん国会議員 1太郎", kenkinEntity.getTradingOrgKokkaiGiin1Name(), "取引相手政治団体国会議員1名称が一致");

        assertEquals(130_491L, kenkinEntity.getTradingOrgKokkaiGiin2Id(), "取引相手政治団体国会議員2Idが一致");
        assertEquals(130_490, kenkinEntity.getTradingOrgKokkaiGiin2Code(), "取引相手政治団体国会議員2Codeが一致");
        assertEquals("ちゃらんぽらん国会議員 2太郎", kenkinEntity.getTradingOrgKokkaiGiin2Name(), "取引相手政治団体国会議員2名称が一致");

        assertEquals(130_588L, kenkinEntity.getTradingOrgKokkaiGiin3Id(), "取引相手政治団体国会議員3Idが一致");
        assertEquals(130_580, kenkinEntity.getTradingOrgKokkaiGiin3Code(), "取引相手政治団体国会議員3Codeが一致");
        assertEquals("ちゃらんぽらん国会議員 3太郎", kenkinEntity.getTradingOrgKokkaiGiin3Name(), "取引相手政治団体国会議員3名称が一致");

        // ユーザ情報はItemWriterで上書き
    }

}
