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
@Transactional
@Sql("tasklet_stage0_poli_org_property.sql")
class UkaiKenkinBalancesheetIncomeMeisaiItemProcessorTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UkaiKenkinBalancesheetIncomeMeisaiItemProcessor ukaiKenkinBalancesheetIncomeMeisaiItemProcessor;

    @Test
    @Tag("TableTruncate") // NOPMD
    void testPoliOrg() throws Exception { // NOPMD

        OfferingBalancesheetIncomeEntity incomeEntity = new OfferingBalancesheetIncomeEntity();

        incomeEntity.setOfferingBalancesheetIncomeId(106L);
        incomeEntity.setOfferingBalancesheetIncomeCode(105L);
        incomeEntity.setSaishinKbn(1);
        incomeEntity.setDocumentCode(882L);
        incomeEntity.setHoukokuNen(2022);
        incomeEntity.setOfferingDate(LocalDate.of(2023, 2, 17));
        incomeEntity.setPoliticalOrganizationId(105L);
        incomeEntity.setPoliticalOrganizationCode(100);
        incomeEntity.setPoliticalOrganizationName("ABCD団体"); // NOPMD
        incomeEntity.setDantaiKbn("04");
        incomeEntity.setDaihyouName("代表者あいうえお"); // NOPMD
        incomeEntity.setDantaiName("えーびーしーでぃーだんたい"); // NOPMD
        incomeEntity.setRelationKbn(1);
        incomeEntity.setRelationPersonIdDelegate(9898L);
        incomeEntity.setRelationPersonCodeDelegate(9867);
        incomeEntity.setRelationPersonNameDelegate("システム代表者"); // NOPMD
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
        incomeEntity.setAccrualDate("R4/9/30"); // NOPMD
        incomeEntity.setAccrualDateValue(LocalDate.of(2022, 9, 30));
        incomeEntity.setBikou("");
        incomeEntity.setPartnerName("ちゃらんぽらん政治団体3"); // NOPMD
        incomeEntity.setPartnerJuusho("山形県実在市"); // NOPMD
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

        assertEquals("R4/9/30", kenkinEntity.getAccrualDate(), "発生日が一致"); // NOPMD
        assertEquals(LocalDate.of(2022, 9, 30), kenkinEntity.getAccrualDateValue(), "発生日実値が一致"); // NOPMD
        assertEquals(2022, kenkinEntity.getHoukokuNen(), "報告年が一致"); // NOPMD
        assertEquals("寄付", kenkinEntity.getItemName(), "項目名が一致"); // NOPMD
        assertEquals(15_003L, kenkinEntity.getKingaku(), "金額一致"); // NOPMD
        assertEquals(0, kenkinEntity.getPickupStage(), "取得階層が一致"); // NOPMD
        assertEquals(3, kenkinEntity.getRenban(), "連番が一致"); // NOPMD
        assertEquals(1, kenkinEntity.getSaishinKbn(), "最新区分が一致"); // NOPMD
        assertEquals(106L, kenkinEntity.getTablleId(), "収入テーブルIdが一致"); // NOPMD
        assertEquals(7, kenkinEntity.getYoushikiKbn(), "様式区分が一致"); // NOPMD
        assertEquals(3, kenkinEntity.getYoushikiEdaKbn(), "様式枝区分項目が一致"); // NOPMD

        assertEquals(105L, kenkinEntity.getPoliticalOrgId(), "献金受け取り政治団体Idが一致"); // NOPMD
        assertEquals(100, kenkinEntity.getPoliticalOrgCode(), "献金受け取り政治団体Codeが一致"); // NOPMD
        assertEquals("ABCD団体", kenkinEntity.getPoliticalOrgName(), "献金受け取り政治団体名称が一致"); // NOPMD

        assertEquals(45L, kenkinEntity.getPoliOrgDelegateId(), "献金受け取り政治団体代表者Idが一致"); // NOPMD
        assertEquals(40, kenkinEntity.getPoliOrgDelegateCode(), "献金受け取り政治団体代表者Codeが一致"); // NOPMD
        assertEquals("ABCD代表者 太郎", kenkinEntity.getPoliOrgDelegateName(), "献金受け取り政治団体代表者名称が一致"); // NOPMD

        assertEquals(59L, kenkinEntity.getPoliOrgAccountManagerId(), "献金受け取り政治団体会計責任者Idが一致"); // NOPMD
        assertEquals(50, kenkinEntity.getPoliOrgAccountManagerCode(), "献金受け取り政治団体会計責任者Codeが一致"); // NOPMD
        assertEquals("ABCD会計責任者 良子", kenkinEntity.getPoliOrgAccountManagerName(), "献金受け取り政治団体会計責任者名称が一致"); // NOPMD

        assertEquals(625L, kenkinEntity.getPoliOrgShikinDantaiId(), "献金受け取り政治団体資金管理団体責任者Idが一致"); // NOPMD
        assertEquals(620, kenkinEntity.getPoliOrgShikinDantaiCode(), "献金受け取り政治団体資金管理団体責任者Codeが一致"); // NOPMD
        assertEquals("資金管理団体責任者　三郎", kenkinEntity.getPoliOrgShikinDantaiName(), "献金受け取り政治団体資金管理団体責任者名称が一致"); // NOPMD

        assertEquals(30_225L, kenkinEntity.getPoliOrgKokkaiGiin1Id(), "献金受け取り政治団体国会議員1Idが一致"); // NOPMD
        assertEquals(30_220, kenkinEntity.getPoliOrgKokkaiGiin1Code(), "献金受け取り政治団体国会議員1Codeが一致"); // NOPMD
        assertEquals("国会議員 1太郎", kenkinEntity.getPoliOrgKokkaiGiin1Name(), "献金受け取り政治団体国会議員1名称が一致"); // NOPMD

        assertEquals(30_491L, kenkinEntity.getPoliOrgKokkaiGiin2Id(), "献金受け取り政治団体国会議員2Idが一致"); // NOPMD
        assertEquals(30_490, kenkinEntity.getPoliOrgKokkaiGiin2Code(), "献金受け取り政治団体国会議員2Codeが一致"); // NOPMD
        assertEquals("国会議員 2太郎", kenkinEntity.getPoliOrgKokkaiGiin2Name(), "献金受け取り政治団体国会議員2名称が一致"); // NOPMD

        assertEquals(30_588L, kenkinEntity.getPoliOrgKokkaiGiin3Id(), "献金受け取り政治団体国会議員3Idが一致"); // NOPMD
        assertEquals(30_580, kenkinEntity.getPoliOrgKokkaiGiin3Code(), "献金受け取り政治団体国会議員3Codeが一致"); // NOPMD
        assertEquals("国会議員 3太郎", kenkinEntity.getPoliOrgKokkaiGiin3Name(), "献金受け取り政治団体国会議員3名称が一致"); // NOPMD

        assertEquals(11_413L, kenkinEntity.getTradingPartnerId(), "取引相手(献金渡す)政治団体Idが一致"); // NOPMD
        assertEquals(11_410, kenkinEntity.getTradingPartnerCode(), "取引相手政治団体Codegが一致"); // NOPMD
        assertEquals("ちゃらんぽらん政治団体3", kenkinEntity.getTradingPartnerName(), "取引相手政治団体名称が一致"); // NOPMD
        assertEquals("山形県実在市", kenkinEntity.getTradingPartnerAddress(), "取引相手政治団体住所が一致"); // NOPMD

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

        assertEquals(130_588L, kenkinEntity.getTradingOrgKokkaiGiin3Id(), "取引相手政治団体国会議員3Idが一致"); // NOPMD
        assertEquals(130_580, kenkinEntity.getTradingOrgKokkaiGiin3Code(), "取引相手政治団体国会議員3Codeが一致"); // NOPMD
        assertEquals("ちゃらんぽらん国会議員 3太郎", kenkinEntity.getTradingOrgKokkaiGiin3Name(), "取引相手政治団体国会議員3名称が一致");

        // ユーザ情報はItemWriterで上書き
    }

    
    
    
    @Test
    @Tag("TableTruncate")
    void testKoufukin() throws Exception { // NOPMD

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
        incomeEntity.setYoushikiKbn(5);
        incomeEntity.setYoushikiEdaKbn(0);
        incomeEntity.setPageTotal(0L);
        incomeEntity.setMimanTotal("");
        incomeEntity.setSonotaTotal("");
        incomeEntity.setPartyName("");
        incomeEntity.setSortNo("");
        incomeEntity.setIchirenNo(3);
        incomeEntity.setItemName("交付金");
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
        assertEquals("交付金", kenkinEntity.getItemName(), "項目名が一致");
        assertEquals(15_003L, kenkinEntity.getKingaku(), "金額一致");
        assertEquals(0, kenkinEntity.getPickupStage(), "取得階層が一致");
        assertEquals(3, kenkinEntity.getRenban(), "連番が一致");
        assertEquals(1, kenkinEntity.getSaishinKbn(), "最新区分が一致");
        assertEquals(106L, kenkinEntity.getTablleId(), "収入テーブルIdが一致");
        assertEquals(5, kenkinEntity.getYoushikiKbn(), "様式区分が一致");
        assertEquals(0, kenkinEntity.getYoushikiEdaKbn(), "様式枝区分項目が一致");

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

    
    @Test
    @Tag("TableTruncate")
    void testPerson() throws Exception { // NOPMD

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
        incomeEntity.setYoushikiEdaKbn(1);
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
        incomeEntity.setPartnerName("迂回献金　太郎"); // NOPMD
        incomeEntity.setPartnerJuusho("和歌山県架空市"); // NOPMD
        incomeEntity.setShiharaisu(0);
        incomeEntity.setKaisaiBasho("");
        incomeEntity.setFlgZeigakuKohjo(Short.valueOf("0"));
        incomeEntity.setTohshibangou(3);
        incomeEntity.setGyoukubun(Short.valueOf("0"));
        incomeEntity.setShokugyou("医師");
        incomeEntity.setPeriodMediate("");
        incomeEntity.setRelationPersonIdIncome(493L);
        incomeEntity.setRelationPersonCodeIncome(490);
        incomeEntity.setRelationPersonNameIncome("迂回献金　太郎");
        incomeEntity.setRelationCorpIdIncome(0L);
        incomeEntity.setRelationCorpCodeIncome(0);
        incomeEntity.setRelationCorpNameInccome("");
        incomeEntity.setRelationPoliticalOrgIdIncome(0L);
        incomeEntity.setRelationPoliticalOrgCodeIncome(0);
        incomeEntity.setRelationPoliticalOrgNameIncome("");

        incomeEntity.setSearchWords("検索語");

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
        assertEquals(7, kenkinEntity.getYoushikiKbn(), "様式区分が一致");
        assertEquals(1, kenkinEntity.getYoushikiEdaKbn(), "様式枝区分項目が一致");

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

        assertEquals(493L, kenkinEntity.getTradingPartnerId(), "取引相手(献金渡す)政治団体Idが一致");
        assertEquals(490, kenkinEntity.getTradingPartnerCode(), "取引相手政治団体Codegが一致");
        assertEquals("迂回献金　太郎", kenkinEntity.getTradingPartnerName(), "取引相手政治団体名称が一致");
        assertEquals("和歌山県架空市", kenkinEntity.getTradingPartnerAddress(), "取引相手政治団体住所が一致");

        assertEquals(0L, kenkinEntity.getTradingPartnerDelegateId(), "取引相手政治団体代表者Idが初期値");
        assertEquals(0, kenkinEntity.getTradingPartnerDelegateCode(), "取引相手政治団体代表者Codeが初期値");
        assertEquals("", kenkinEntity.getTradingPartnerDelegateName(), "取引相手政治団体代表者名称が初期値");

        assertEquals(0L, kenkinEntity.getTradingOrgAccountManagerId(), "取引相手政治団体会計責任者Idが初期値");
        assertEquals(0, kenkinEntity.getTradingOrgAccountManagerCode(), "取引相手政治団体会計責任者Codeが初期値");
        assertEquals("", kenkinEntity.getTradingOrgAccountManagerName(), "取引相手政治団体会計責任者名称が初期値");

        assertEquals(0L, kenkinEntity.getTradingOrgShikinDantaiId(), "取引相手政治団体資金管理団体責任者Idが初期値");
        assertEquals(0, kenkinEntity.getTradingOrgShikinDantaiCode(), "取引相手政治団体資金管理団体責任Code者が初期値");
        assertEquals("", kenkinEntity.getTradingOrgShikinDantaiName(), "取引相手政治団体資金管理団体責任者名称が初期値");

        assertEquals(0L, kenkinEntity.getTradingOrgKokkaiGiin1Id(), "取引相手政治団体国会議員1Idが初期値");
        assertEquals(0, kenkinEntity.getTradingOrgKokkaiGiin1Code(), "取引相手政治団体国会議員1Codeが初期値");
        assertEquals("", kenkinEntity.getTradingOrgKokkaiGiin1Name(), "取引相手政治団体国会議員1名称が初期値");

        assertEquals(0L, kenkinEntity.getTradingOrgKokkaiGiin2Id(), "取引相手政治団体国会議員2Idが初期値");
        assertEquals(0, kenkinEntity.getTradingOrgKokkaiGiin2Code(), "取引相手政治団体国会議員2Codeが初期値");
        assertEquals("", kenkinEntity.getTradingOrgKokkaiGiin2Name(), "取引相手政治団体国会議員2名称が初期値");

        assertEquals(0L, kenkinEntity.getTradingOrgKokkaiGiin3Id(), "取引相手政治団体国会議員3Idが一致");
        assertEquals(0, kenkinEntity.getTradingOrgKokkaiGiin3Code(), "取引相手政治団体国会議員3Codeが一致");
        assertEquals("", kenkinEntity.getTradingOrgKokkaiGiin3Name(), "取引相手政治団体国会議員3名称が初期値");

        // ユーザ情報はItemWriterで上書き
    }
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    void testCorp() throws Exception { // NOPMD

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
        incomeEntity.setYoushikiEdaKbn(2);
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
        incomeEntity.setPartnerName("株式会社いろはに");
        incomeEntity.setPartnerJuusho("和歌山県架空市");
        incomeEntity.setShiharaisu(0);
        incomeEntity.setKaisaiBasho("");
        incomeEntity.setFlgZeigakuKohjo(Short.valueOf("0"));
        incomeEntity.setTohshibangou(3);
        incomeEntity.setGyoukubun(Short.valueOf("0"));
        incomeEntity.setShokugyou("迂回献金　太郎");
        incomeEntity.setPeriodMediate("");
        incomeEntity.setRelationPersonIdIncome(493L);
        incomeEntity.setRelationPersonCodeIncome(490);
        incomeEntity.setRelationPersonNameIncome("迂回献金　太郎");
        incomeEntity.setRelationCorpIdIncome(3_875L);
        incomeEntity.setRelationCorpCodeIncome(3_870);
        incomeEntity.setRelationCorpNameInccome("株式会社いろはに");
        incomeEntity.setRelationPoliticalOrgIdIncome(0L);
        incomeEntity.setRelationPoliticalOrgCodeIncome(0);
        incomeEntity.setRelationPoliticalOrgNameIncome("");

        incomeEntity.setSearchWords("検索語");

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
        assertEquals(7, kenkinEntity.getYoushikiKbn(), "様式区分が一致");
        assertEquals(2, kenkinEntity.getYoushikiEdaKbn(), "様式枝区分項目が一致");

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

        assertEquals(3_875L, kenkinEntity.getTradingPartnerId(), "取引相手(献金渡す)政治団体Idが一致");
        assertEquals(3_870, kenkinEntity.getTradingPartnerCode(), "取引相手政治団体Codegが一致");
        assertEquals("株式会社いろはに", kenkinEntity.getTradingPartnerName(), "取引相手政治団体名称が一致");
        assertEquals("和歌山県架空市", kenkinEntity.getTradingPartnerAddress(), "取引相手政治団体住所が一致");

        assertEquals(493L, kenkinEntity.getTradingPartnerDelegateId(), "取引相手政治団体代表者Idが初期値");
        assertEquals(490, kenkinEntity.getTradingPartnerDelegateCode(), "取引相手政治団体代表者Codeが初期値");
        assertEquals("迂回献金　太郎", kenkinEntity.getTradingPartnerDelegateName(), "取引相手政治団体代表者名称が初期値");

        assertEquals(0L, kenkinEntity.getTradingOrgAccountManagerId(), "取引相手政治団体会計責任者Idが初期値");
        assertEquals(0, kenkinEntity.getTradingOrgAccountManagerCode(), "取引相手政治団体会計責任者Codeが初期値");
        assertEquals("", kenkinEntity.getTradingOrgAccountManagerName(), "取引相手政治団体会計責任者名称が初期値");

        assertEquals(0L, kenkinEntity.getTradingOrgShikinDantaiId(), "取引相手政治団体資金管理団体責任者Idが初期値");
        assertEquals(0, kenkinEntity.getTradingOrgShikinDantaiCode(), "取引相手政治団体資金管理団体責任Code者が初期値");
        assertEquals("", kenkinEntity.getTradingOrgShikinDantaiName(), "取引相手政治団体資金管理団体責任者名称が初期値");

        assertEquals(0L, kenkinEntity.getTradingOrgKokkaiGiin1Id(), "取引相手政治団体国会議員1Idが初期値");
        assertEquals(0, kenkinEntity.getTradingOrgKokkaiGiin1Code(), "取引相手政治団体国会議員1Codeが初期値");
        assertEquals("", kenkinEntity.getTradingOrgKokkaiGiin1Name(), "取引相手政治団体国会議員1名称が初期値");

        assertEquals(0L, kenkinEntity.getTradingOrgKokkaiGiin2Id(), "取引相手政治団体国会議員2Idが初期値");
        assertEquals(0, kenkinEntity.getTradingOrgKokkaiGiin2Code(), "取引相手政治団体国会議員2Codeが初期値");
        assertEquals("", kenkinEntity.getTradingOrgKokkaiGiin2Name(), "取引相手政治団体国会議員2名称が初期値");

        assertEquals(0L, kenkinEntity.getTradingOrgKokkaiGiin3Id(), "取引相手政治団体国会議員3Idが一致");
        assertEquals(0, kenkinEntity.getTradingOrgKokkaiGiin3Code(), "取引相手政治団体国会議員3Codeが一致");
        assertEquals("", kenkinEntity.getTradingOrgKokkaiGiin3Name(), "取引相手政治団体国会議員3名称が初期値");

        // ユーザ情報はItemWriterで上書き
    }
    
}
