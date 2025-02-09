package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.GetSamePersonPoliOrgByCodeLogic;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationPropertyRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * ConvertUkaiKenkinIncomeY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertUkaiKenkinIncomeByCodeY2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ConvertUkaiKenkinIncomeByCodeY2022Logic convertUkaiKenkinIncomeByCodeY2022Logic;

    /** 政治団体属性Repository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    /** 政治団体属性から関連者が同一である政治団体同一識別コードリスト取得Logic */
    @Autowired
    private GetSamePersonPoliOrgByCodeLogic getSamePersonPoliOrgByCodeLogic;

    @Test
    @Tag("TableTruncate")
    @Sql({ "convert_ukai_kenkin_income_2022.sql", "../ukai_kenkin/political_organization_property.sql" })
    void test() throws Exception { // NOPMD

        // 初期データ
        final String INIT_String = "";
        final Integer INIT_Integer = 0;
        final Long INIT_Long = 0L;

        PoliticalOrganizationPropertyEntity propertyEntity = politicalOrganizationPropertyRepository.findById(765L)
                .get();
        List<Integer> listCode = getSamePersonPoliOrgByCodeLogic.paractice(propertyEntity);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        // 寄付と交付金が対象
        List<Integer> listYoushikiKbn = new ArrayList<>();
        listYoushikiKbn.add(BalancesheetYoushikiKbnConstants.YoushikiKbn.DONATE);
        listYoushikiKbn.add(BalancesheetYoushikiKbnConstants.YoushikiKbn.KOUFUKIN);

        List<WkTblUkaiKenkinEntity> list = convertUkaiKenkinIncomeByCodeY2022Logic.practice(listCode, propertyEntity,
                listYoushikiKbn, privilegeDto);

        assertEquals(4, list.size(), "取得件数");

        /* 個人 */
        WkTblUkaiKenkinEntity entity00 = list.get(0);
        assertEquals(1001L, entity00.getTablleId(), "複写元テーブルIdデータ1");
        assertEquals(2022, entity00.getHoukokuNen(), "報告年データ1");

        // 収支報告書記載団体代表者
        assertEquals(45L, entity00.getPoliOrgDelegateId(), "収支報告書記載団体代表者Idデータ1");
        assertEquals(40, entity00.getPoliOrgDelegateCode(), "収支報告書記載団体代表者同一識別コードデータ1");
        assertEquals("ABCD代表者 太郎", entity00.getPoliOrgDelegateName(), "収支報告書記載団体代表者名称データ1");

        // 収支報告書記載団体会計責任者
        assertEquals(59L, entity00.getPoliOrgAccountManagerId(), "収支報告書記載団体会計責任者Idデータ1");
        assertEquals(50, entity00.getPoliOrgAccountManagerCode(), "収支報告書記載団体会計責任者同一識別コードデータ1");
        assertEquals("ABCD会計責任者 良子", entity00.getPoliOrgAccountManagerName(), "収支報告書記載団体会計責任者名称データ1");

        // 収支報告書資金管理団体登録者
        assertEquals(625L, entity00.getPoliOrgShikinDantaiId(), "収支報告書資金管理団体登録者Idデータ1");
        assertEquals(620, entity00.getPoliOrgShikinDantaiCode(), "収支報告書資金管理団体登録者同一識別コードデータ1");
        assertEquals("資金管理団体責任者　三郎", entity00.getPoliOrgShikinDantaiName(), "収支報告書資金管理団体登録者名称データ1");

        // 収支報告書国会議員
        assertEquals(30_225L, entity00.getPoliOrgKokkaiGiin1Id(), "収支報告書国会議員1関連者データ1");
        assertEquals(30_220, entity00.getPoliOrgKokkaiGiin1Code(), "収支報告書国会議員1関連者同一識別コードデータ1");
        assertEquals("国会議員 1太郎", entity00.getPoliOrgKokkaiGiin1Name(), "収支報告書国会議員1関連者名称データ1");
        assertEquals(30_491L, entity00.getPoliOrgKokkaiGiin2Id(), "収支報告書国会議員2関連者Idデータ1");
        assertEquals(30_490, entity00.getPoliOrgKokkaiGiin2Code(), "収支報告書国会議員2関連者同一識別コードデータ1");
        assertEquals("国会議員 2太郎", entity00.getPoliOrgKokkaiGiin2Name(), "収支報告書国会議員2関連者名称データ1");
        assertEquals(30_588L, entity00.getPoliOrgKokkaiGiin3Id(), "収支報告書国会議員3関連者Idデータ1");
        assertEquals(30_580, entity00.getPoliOrgKokkaiGiin3Code(), "収支報告書国会議員3関連者同一識別コードデータ1");
        assertEquals("国会議員 3太郎", entity00.getPoliOrgKokkaiGiin3Name(), "収支報告書国会議員3関連者名称データ1");

        // 収支報告書政治団体Id
        assertEquals(105L, entity00.getPoliticalOrgId(), "収支報告書政治団体Idデータ1");
        assertEquals(100, entity00.getPoliticalOrgCode(), "収支報告書政治団体同一識別コードデータ1");
        assertEquals("ABCD団体", entity00.getPoliticalOrgName(), "収支報告書政治団体名称データ1");

        // 項目
        assertEquals("R4/9/30", entity00.getAccrualDate(), "発生日データ1");
        assertEquals(LocalDate.of(2022, 9, 30), entity00.getAccrualDateValue(), "発生日実値データ1");
        assertEquals(7, entity00.getYoushikiKbn(), "様式区分データ1");
        assertEquals(1, entity00.getYoushikiEdaKbn(), "様式枝区分項目データ1");
        assertEquals("寄付", entity00.getItemName(), "項目名データ1");
        assertEquals(15_001L, entity00.getKingaku(), "金額データ1");
        assertEquals(0, entity00.getPickupStage(), "抽出階層データ1");
        assertEquals(3, entity00.getRenban(), "行連番データ1");

        // 取引相手
        assertEquals(365L, entity00.getTradingPartnerId(), "取引相手Idデータ1");
        assertEquals(360, entity00.getTradingPartnerCode(), "取引相手同一識別コードデータ1");
        assertEquals("個人寄付太郎", entity00.getTradingPartnerName(), "取引相手名称データ1");
        assertEquals("和歌山県実在市", entity00.getTradingPartnerAddress(), "取引相手住所データ1");

        // 取引相手団体代表者
        assertEquals(INIT_Long, entity00.getTradingPartnerDelegateId(), "取引相手団体代表者Idデータ1");
        assertEquals(INIT_Integer, entity00.getTradingPartnerDelegateCode(), "取引相手団体代表者同一識別コードデータ1");
        assertEquals(INIT_String, entity00.getTradingPartnerDelegateName(), "取引相手団体代表者データ1");

        // 取引相手団体会計責任者
        assertEquals(INIT_Long, entity00.getTradingOrgAccountManagerId(), "取引相手団体会計責任者Idデータ1");
        assertEquals(INIT_Integer, entity00.getTradingOrgAccountManagerCode(), "取引相手団体会計責任者同一識別コードデータ1");
        assertEquals(INIT_String, entity00.getTradingOrgAccountManagerName(), "取引相手団体会計責任者名称データ1");

        // 取引相手資金管理団体登録者
        assertEquals(INIT_Long, entity00.getTradingOrgShikinDantaiId(), "取引相手資金管理団体登録者Idデータ1");
        assertEquals(INIT_Integer, entity00.getTradingOrgShikinDantaiCode(), "取引相手資金管理団体登録者同一識別コードデータ1");
        assertEquals(INIT_String, entity00.getTradingOrgShikinDantaiName(), "取引相手書資金管理団体登録者名称データ1");

        // 取引相手国会議員1関連者
        assertEquals(INIT_Long, entity00.getTradingOrgKokkaiGiin1Id(), "取引相手国会議員1関連者Idデータ1");
        assertEquals(INIT_Integer, entity00.getTradingOrgKokkaiGiin1Code(), "取引相手国会議員1関連者同一識別コードデータ1");
        assertEquals(INIT_String, entity00.getTradingOrgKokkaiGiin1Name(), "取引相手国会議員1関連者名称データ1");

        // 取引相手国会議員2関連者
        assertEquals(INIT_Long, entity00.getTradingOrgKokkaiGiin2Id(), "取引相手国会議員2関連者Idデータ1");
        assertEquals(INIT_Integer, entity00.getTradingOrgKokkaiGiin2Code(), "取引相手国会議員2関連者同一識別コードデータ1");
        assertEquals(INIT_String, entity00.getTradingOrgKokkaiGiin2Name(), "取引相手国会議員2関連者名称1");

        // 取引相手国会議員3関連者
        assertEquals(INIT_Long, entity00.getTradingOrgKokkaiGiin3Id(), "取引相手国会議員3関連者Idデータ1");
        assertEquals(INIT_Integer, entity00.getTradingOrgKokkaiGiin3Code(), "取引相手国会議員3関連者同一識別コードデータ1");
        assertEquals(INIT_String, entity00.getTradingOrgKokkaiGiin3Name(), "取引相手国会議員3関連者名称データ1");

        // 最新区分
        assertEquals(DataHistoryStatusConstants.INSERT.value(), entity00.getSaishinKbn(), "最新区分");

        /* 企業・団体 */
        WkTblUkaiKenkinEntity entity01 = list.get(1);
        assertEquals(2001L, entity01.getTablleId(), "複写元テーブルIdデータ2");

        // 取引相手
        assertEquals(2773L, entity01.getTradingPartnerId(), "取引相手Idデータ2");
        assertEquals(2770, entity01.getTradingPartnerCode(), "取引相手同一識別コードデータ2");
        assertEquals("株式会社いろはに", entity01.getTradingPartnerName(), "取引相手名称データ2");
        assertEquals("宮崎県実在市", entity01.getTradingPartnerAddress(), "取引相手住所データ2");

        // 取引相手団体代表者
        assertEquals(INIT_Long, entity01.getTradingPartnerDelegateId(), "取引相手団体代表者Idデータ2");
        assertEquals(INIT_Integer, entity01.getTradingPartnerDelegateCode(), "取引相手団体代表者同一識別コードデータ2");
        assertEquals(INIT_String, entity01.getTradingPartnerDelegateName(), "取引相手団体代表者データ2");

        // 取引相手団体会計責任者
        assertEquals(INIT_Long, entity01.getTradingOrgAccountManagerId(), "取引相手団体会計責任者Idデータ2");
        assertEquals(INIT_Integer, entity01.getTradingOrgAccountManagerCode(), "取引相手団体会計責任者同一識別コードデータ2");
        assertEquals(INIT_String, entity01.getTradingOrgAccountManagerName(), "取引相手団体会計責任者名称データ2");

        // 取引相手資金管理団体登録者
        assertEquals(INIT_Long, entity01.getTradingOrgShikinDantaiId(), "取引相手資金管理団体登録者Idデータ2");
        assertEquals(INIT_Integer, entity01.getTradingOrgShikinDantaiCode(), "取引相手資金管理団体登録者同一識別コードデータ2");
        assertEquals(INIT_String, entity01.getTradingOrgShikinDantaiName(), "取引相手書資金管理団体登録者名称データ2");

        // 取引相手国会議員1関連者
        assertEquals(INIT_Long, entity01.getTradingOrgKokkaiGiin1Id(), "取引相手国会議員1関連者Idデータ2");
        assertEquals(INIT_Integer, entity01.getTradingOrgKokkaiGiin1Code(), "取引相手国会議員1関連者同一識別コードデータ2");
        assertEquals(INIT_String, entity01.getTradingOrgKokkaiGiin1Name(), "取引相手国会議員1関連者名称データ2");

        // 取引相手国会議員2関連者
        assertEquals(INIT_Long, entity01.getTradingOrgKokkaiGiin2Id(), "取引相手国会議員2関連者Idデータ2");
        assertEquals(INIT_Integer, entity01.getTradingOrgKokkaiGiin2Code(), "取引相手国会議員2関連者同一識別コードデータ2");
        assertEquals(INIT_String, entity01.getTradingOrgKokkaiGiin2Name(), "取引相手国会議員2関連者名称2");

        // 取引相手国会議員3関連者
        assertEquals(INIT_Long, entity01.getTradingOrgKokkaiGiin3Id(), "取引相手国会議員3関連者Idデータ2");
        assertEquals(INIT_Integer, entity01.getTradingOrgKokkaiGiin3Code(), "取引相手国会議員3関連者同一識別コードデータ2");
        assertEquals(INIT_String, entity01.getTradingOrgKokkaiGiin3Name(), "取引相手国会議員3関連者名称データ2");

        /* 政治団体 */
        WkTblUkaiKenkinEntity entity02 = list.get(2);
        assertEquals(3001L, entity02.getTablleId(), "複写元テーブルIdデータ3");

        // 取引相手
        assertEquals(11_398L, entity02.getTradingPartnerId(), "取引相手Idデータ3");
        assertEquals(11_390, entity02.getTradingPartnerCode(), "取引相手同一識別コードデータ3");
        assertEquals("ちゃらんぽらん政治団体", entity02.getTradingPartnerName(), "取引相手名称データ3");
        assertEquals("山形県実在市", entity02.getTradingPartnerAddress(), "取引相手住所データ3");

        // 取引相手団体代表者
        assertEquals(145L, entity02.getTradingPartnerDelegateId(), "取引相手団体代表者Idデータ3");
        assertEquals(140, entity02.getTradingPartnerDelegateCode(), "取引相手団体代表者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん代表者 太郎", entity02.getTradingPartnerDelegateName(), "取引相手団体代表者データ3");

        // 取引相手団体会計責任者
        assertEquals(159L, entity02.getTradingOrgAccountManagerId(), "取引相手団体会計責任者Idデータ3");
        assertEquals(150, entity02.getTradingOrgAccountManagerCode(), "取引相手団体会計責任者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん会計責任者 良子", entity02.getTradingOrgAccountManagerName(), "取引相手団体会計責任者名称データ3");

        // 取引相手資金管理団体登録者
        assertEquals(1625L, entity02.getTradingOrgShikinDantaiId(), "取引相手資金管理団体登録者Idデータ3");
        assertEquals(1620, entity02.getTradingOrgShikinDantaiCode(), "取引相手資金管理団体登録者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん資金管理団体責任者　三郎", entity02.getTradingOrgShikinDantaiName(), "取引相手書資金管理団体登録者名称データ3");

        // 取引相手国会議員1関連者
        assertEquals(130_225L, entity02.getTradingOrgKokkaiGiin1Id(), "取引相手国会議員1関連者Idデータ3");
        assertEquals(130_220, entity02.getTradingOrgKokkaiGiin1Code(), "取引相手国会議員1関連者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん国会議員 1太郎", entity02.getTradingOrgKokkaiGiin1Name(), "取引相手国会議員1関連者名称データ3");

        // 取引相手国会議員2関連者
        assertEquals(130_491L, entity02.getTradingOrgKokkaiGiin2Id(), "取引相手国会議員2関連者Idデータ3");
        assertEquals(130_490, entity02.getTradingOrgKokkaiGiin2Code(), "取引相手国会議員2関連者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん国会議員 2太郎", entity02.getTradingOrgKokkaiGiin2Name(), "取引相手国会議員2関連者名称3");

        // 取引相手国会議員3関連者
        assertEquals(130_588L, entity02.getTradingOrgKokkaiGiin3Id(), "取引相手国会議員3関連者Idデータ3");
        assertEquals(130_580, entity02.getTradingOrgKokkaiGiin3Code(), "取引相手国会議員3関連者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん国会議員 3太郎", entity02.getTradingOrgKokkaiGiin3Name(), "取引相手国会議員3関連者名称データ3");

        /* 交付金 */
        WkTblUkaiKenkinEntity entity03 = list.get(3);
        assertEquals(4001L, entity03.getTablleId(), "複写元テーブルIdデータ4");

        // 収支報告書記載団体代表者
        assertEquals(45L, entity03.getPoliOrgDelegateId(), "収支報告書記載団体代表者Idデータ4");
        assertEquals(40, entity03.getPoliOrgDelegateCode(), "収支報告書記載団体代表者同一識別コードデータ4");
        assertEquals("ABCD代表者 太郎", entity03.getPoliOrgDelegateName(), "収支報告書記載団体代表者名称データ4");

        // 収支報告書記載団体会計責任者
        assertEquals(80_201L, entity03.getPoliOrgAccountManagerId(), "収支報告書記載団体会計責任者Idデータ4");
        assertEquals(80_200, entity03.getPoliOrgAccountManagerCode(), "収支報告書記載団体会計責任者同一識別コードデータ4");
        assertEquals("1会計責任者 良子", entity03.getPoliOrgAccountManagerName(), "収支報告書記載団体会計責任者名称データ4");

        // 収支報告書資金管理団体登録者
        assertEquals(80_401L, entity03.getPoliOrgShikinDantaiId(), "収支報告書資金管理団体登録者Idデータ4");
        assertEquals(80_400, entity03.getPoliOrgShikinDantaiCode(), "収支報告書資金管理団体登録者同一識別コードデータ4");
        assertEquals("1資金管理団体責任者　三郎", entity03.getPoliOrgShikinDantaiName(), "収支報告書資金管理団体登録者名称データ4");

        // 収支報告書国会議員
        assertEquals(80_501L, entity03.getPoliOrgKokkaiGiin1Id(), "収支報告書国会議員1関連者データ4");
        assertEquals(80_500, entity03.getPoliOrgKokkaiGiin1Code(), "収支報告書国会議員1関連者同一識別コードデータ4");
        assertEquals("1国会議員 1太郎", entity03.getPoliOrgKokkaiGiin1Name(), "収支報告書国会議員1関連者名称データ4");
        assertEquals(80_601L, entity03.getPoliOrgKokkaiGiin2Id(), "収支報告書国会議員2関連者Idデータ4");
        assertEquals(80_600, entity03.getPoliOrgKokkaiGiin2Code(), "収支報告書国会議員2関連者同一識別コードデータ4");
        assertEquals("1国会議員 2太郎", entity03.getPoliOrgKokkaiGiin2Name(), "収支報告書国会議員2関連者名称データ4");
        assertEquals(80_701L, entity03.getPoliOrgKokkaiGiin3Id(), "収支報告書国会議員3関連者Idデータ4");
        assertEquals(80_700, entity03.getPoliOrgKokkaiGiin3Code(), "収支報告書国会議員3関連者同一識別コードデータ4");
        assertEquals("1国会議員 3太郎", entity03.getPoliOrgKokkaiGiin3Name(), "収支報告書国会議員3関連者名称データ4");

        // 収支報告書政治団体Id
        assertEquals(1021L, entity03.getPoliticalOrgId(), "収支報告書政治団体Idデータ4");
        assertEquals(1020, entity03.getPoliticalOrgCode(), "収支報告書政治団体同一識別コードデータ4");
        assertEquals("2団体", entity03.getPoliticalOrgName(), "収支報告書政治団体名称データ4");

        // 項目
        assertEquals("R4/8/30", entity03.getAccrualDate(), "発生日データ4");
        assertEquals(LocalDate.of(2022, 8, 30), entity03.getAccrualDateValue(), "発生日実値データ4");
        assertEquals(5, entity03.getYoushikiKbn(), "様式区分データ4");
        assertEquals(0, entity03.getYoushikiEdaKbn(), "様式枝区分項目データ4");
        assertEquals("交付金", entity03.getItemName(), "項目名データ4");
        assertEquals(15_004L, entity03.getKingaku(), "金額データ4");
        assertEquals(0, entity03.getPickupStage(), "抽出階層データ4");
        assertEquals(3, entity03.getRenban(), "行連番データ4");

        // 取引相手
        assertEquals(11_398L, entity03.getTradingPartnerId(), "取引相手Idデータ3");
        assertEquals(11_390, entity03.getTradingPartnerCode(), "取引相手同一識別コードデータ3");
        assertEquals("ちゃらんぽらん政治団体", entity03.getTradingPartnerName(), "取引相手名称データ3");
        assertEquals("山形県実在市", entity03.getTradingPartnerAddress(), "取引相手住所データ3");

        // 取引相手団体代表者
        assertEquals(145L, entity03.getTradingPartnerDelegateId(), "取引相手団体代表者Idデータ3");
        assertEquals(140, entity03.getTradingPartnerDelegateCode(), "取引相手団体代表者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん代表者 太郎", entity03.getTradingPartnerDelegateName(), "取引相手団体代表者データ3");

        // 取引相手団体会計責任者
        assertEquals(159L, entity03.getTradingOrgAccountManagerId(), "取引相手団体会計責任者Idデータ3");
        assertEquals(150, entity03.getTradingOrgAccountManagerCode(), "取引相手団体会計責任者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん会計責任者 良子", entity03.getTradingOrgAccountManagerName(), "取引相手団体会計責任者名称データ3");

        // 取引相手資金管理団体登録者
        assertEquals(1625L, entity03.getTradingOrgShikinDantaiId(), "取引相手資金管理団体登録者Idデータ3");
        assertEquals(1620, entity03.getTradingOrgShikinDantaiCode(), "取引相手資金管理団体登録者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん資金管理団体責任者　三郎", entity03.getTradingOrgShikinDantaiName(), "取引相手書資金管理団体登録者名称データ3");

        // 取引相手国会議員1関連者
        assertEquals(130_225L, entity03.getTradingOrgKokkaiGiin1Id(), "取引相手国会議員1関連者Idデータ3");
        assertEquals(130_220, entity03.getTradingOrgKokkaiGiin1Code(), "取引相手国会議員1関連者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん国会議員 1太郎", entity03.getTradingOrgKokkaiGiin1Name(), "取引相手国会議員1関連者名称データ3");

        // 取引相手国会議員2関連者
        assertEquals(130_491L, entity03.getTradingOrgKokkaiGiin2Id(), "取引相手国会議員2関連者Idデータ3");
        assertEquals(130_490, entity03.getTradingOrgKokkaiGiin2Code(), "取引相手国会議員2関連者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん国会議員 2太郎", entity03.getTradingOrgKokkaiGiin2Name(), "取引相手国会議員2関連者名称3");

        // 取引相手国会議員3関連者
        assertEquals(130_588L, entity03.getTradingOrgKokkaiGiin3Id(), "取引相手国会議員3関連者Idデータ3");
        assertEquals(130_580, entity03.getTradingOrgKokkaiGiin3Code(), "取引相手国会議員3関連者同一識別コードデータ3");
        assertEquals("ちゃらんぽらん国会議員 3太郎", entity03.getTradingOrgKokkaiGiin3Name(), "取引相手国会議員3関連者名称データ3");

        fail("Not yet implemented");
    }

}
