package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080201Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0802Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0802Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0803Dto;
import mitei.mitei.common.publish.party.usage.report.logic.CreateShito0802Kbn01WrapperLogic;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0802And0803Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingPartyUsage0802And0803Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * InsertPartyUsageShito0802And0803Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@SuppressWarnings({"PMD.UseUnderscoresInNumericLiterals","PMD.LawOfDemeter"})
class InsertPartyUsageShito0802And0803LogicTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0802And0803Logic insertPartyUsageShito0802And0803Logic;

    /** 様式8その2区分1と3Repository */
    @Autowired
    private OfferingPartyUsage0802And0803Report2025Repository offeringPartyUsage0802And0803Report2025Repository;

    @Test
    @Transactional
    void testPractice() { // NOPMD

        // 政治団体基礎情報
        Long documentCode = 3434L;
        PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        partyUsageDocumentPoliticalPropertyDto.setNendo(2022); // 実際には表紙からコピー
        partyUsageDocumentPoliticalPropertyDto.setOfferingDate(LocalDate.of(2022, 12, 1)); // 実際には宣誓書からコピー
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationId(433L);
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationCode(431);
        partyUsageDocumentPoliticalPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        partyUsageDocumentPoliticalPropertyDto.setDantaiName("ちゃらん団体");
        partyUsageDocumentPoliticalPropertyDto.setDaihyouName("代表者 世間芸名");
        partyUsageDocumentPoliticalPropertyDto.setRelationKbn(223);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonIdDelegate(9898L);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonCodeDelegate(9867);
        partyUsageDocumentPoliticalPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        /* 様式8その3 */
        Shito0803Dto shito3 = new Shito0803Dto();

        shito3.getSheet0803Dto().setTotalShibuKoufuAll(10001L);
        shito3.getSheet0803Dto().setTotalShibuKoufuJutoKoufukin(10002L);
        shito3.getSheet0803Dto().setTotalShibuKoufuJutoMyFunds(10003L);
        shito3.getSheet0803Dto().setTotalShibuKoufuBikou("aaaa");
        shito3.getSheet0803Dto().setTotalJinkenhiAll(20001L);
        shito3.getSheet0803Dto().setTotalJinkenhiJutoKoufukin(20002L);
        shito3.getSheet0803Dto().setTotalJinkenhiJutoMyFunds(20003L);
        shito3.getSheet0803Dto().setTotalJinkenhiBikou("bbbb");
        shito3.getSheet0803Dto().setTotalKounetsuhiAll(30001L);
        shito3.getSheet0803Dto().setTotalKounetsuhiJutoKoufukin(30002L);
        shito3.getSheet0803Dto().setTotalKounetsuhiJutoMyFunds(30003L);
        shito3.getSheet0803Dto().setTotalKounetsuhiBikou("cccc");
        shito3.getSheet0803Dto().setTotalBihinAll(40001L);
        shito3.getSheet0803Dto().setTotalBihinJutoKoufukin(40002L);
        shito3.getSheet0803Dto().setTotalBihinJutoMyFunds(40003L);
        shito3.getSheet0803Dto().setTotalBihinBikou("dddd");
        shito3.getSheet0803Dto().setTotalJimushoAll(50001L);
        shito3.getSheet0803Dto().setTotalJimushoJutoKoufukin(50002L);
        shito3.getSheet0803Dto().setTotalJimushoJutoMyFunds(50003L);
        shito3.getSheet0803Dto().setTotalJimushoBikou("rrrr");
        shito3.getSheet0803Dto().setTotalKeihiAll(60001L);
        shito3.getSheet0803Dto().setTotalKeihiJutoKoufukin(60002L);
        shito3.getSheet0803Dto().setTotalKeihiJutoMyFunds(60003L);
        shito3.getSheet0803Dto().setTotalKeihiBikou("eeee");
        shito3.getSheet0803Dto().setTotalSoshikiAll(70001L);
        shito3.getSheet0803Dto().setTotalSoshikiJutoKoufukin(70002L);
        shito3.getSheet0803Dto().setTotalSoshikiJutoMyFunds(70003L);
        shito3.getSheet0803Dto().setTotalSoshikiBikou("ffff");
        shito3.getSheet0803Dto().setTotalSenkyoAll(80001L);
        shito3.getSheet0803Dto().setTotalSenkyoJutoKoufukin(80002L);
        shito3.getSheet0803Dto().setTotalSenkyoJutoMyFunds(80003L);
        shito3.getSheet0803Dto().setTotalSenkyoBikou("gggg");
        shito3.getSheet0803Dto().setTotalAllJigyouAll(90001L);
        shito3.getSheet0803Dto().setTotalAllJigyouJutoKoufukin(90002L);
        shito3.getSheet0803Dto().setTotalAllJigyouJutoMyFunds(90003L);
        shito3.getSheet0803Dto().setTotalAllJigyouBikou("qqqq");
        shito3.getSheet0803Dto().setTotalKikanshiAll(100001L);
        shito3.getSheet0803Dto().setTotalKikanshiJutoKoufukin(100002L);
        shito3.getSheet0803Dto().setTotalKikanshiJutoMyFunds(100003L);
        shito3.getSheet0803Dto().setTotalKikanshiBikou("hhhh");
        shito3.getSheet0803Dto().setTotalSendenAll(110001L);
        shito3.getSheet0803Dto().setTotalSendenJutoKoufukin(110002L);
        shito3.getSheet0803Dto().setTotalSendenJutoMyFunds(110003L);
        shito3.getSheet0803Dto().setTotalSendenBikou("pppp");
        shito3.getSheet0803Dto().setTotalPartyAll(120001L);
        shito3.getSheet0803Dto().setTotalPartyJutoKoufukin(120002L);
        shito3.getSheet0803Dto().setTotalPartyJutoMyFunds(120003L);
        shito3.getSheet0803Dto().setTotalPartyBikou("iiii");
        shito3.getSheet0803Dto().setTotalSonotaJigyouAll(130001L);
        shito3.getSheet0803Dto().setTotalSonotaJigyouJutoKoufukin(130002L);
        shito3.getSheet0803Dto().setTotalSonotaJigyouJutoMyFunds(130003L);
        shito3.getSheet0803Dto().setTotalSonotaJigyouBikou("jjjj");
        shito3.getSheet0803Dto().setTotalChousaAll(140001L);
        shito3.getSheet0803Dto().setTotalChousaJutoKoufukin(140002L);
        shito3.getSheet0803Dto().setTotalChousaJutoMyFunds(140003L);
        shito3.getSheet0803Dto().setTotalChousaBikou("kkkk");
        shito3.getSheet0803Dto().setTotalKifuAll(150001L);
        shito3.getSheet0803Dto().setTotalKifuJutoKoufukin(150002L);
        shito3.getSheet0803Dto().setTotalKifuJutoMyFunds(150003L);
        shito3.getSheet0803Dto().setTotalKifuBikou("llll");
        shito3.getSheet0803Dto().setTotalSonotaKeihiAll(160001L);
        shito3.getSheet0803Dto().setTotalSonotaKeihiJutoKoufukin(160002L);
        shito3.getSheet0803Dto().setTotalSonotaKeihiJutoMyFunds(160003L);
        shito3.getSheet0803Dto().setTotalSonotaKeihiBikou("mmmm");
        shito3.getSheet0803Dto().setTotalAllActionAll(170001L);
        shito3.getSheet0803Dto().setTotalAllActionJutoKoufukin(170002L);
        shito3.getSheet0803Dto().setTotalAllActionJutoMyFunds(170003L);
        shito3.getSheet0803Dto().setTotalAllActionBikou("nnnn");
        shito3.getSheet0803Dto().setTotalAllAmountAll(180001L);
        shito3.getSheet0803Dto().setTotalAllAmountJutoKoufukin(180002L);
        shito3.getSheet0803Dto().setTotalAllAmountJutoMyFunds(180003L);
        shito3.getSheet0803Dto().setTotalAllAmountBikou("oooo");

        /* 様式8その2区分1 */
        CreateShito0802Kbn01WrapperLogic createShito0802Kbn01WrapperLogic = new CreateShito0802Kbn01WrapperLogic();

        Kbn080201Dto Kbn080201Dto = createShito0802Kbn01WrapperLogic.getDto();

        // 他のDtoをテストに介在させないためにここでは特別にしているが、業務的にはきわめて望ましくない触り方
        Kbn080201Dto.getList().get(0).setAmount(12345L);
        Kbn080201Dto.getList().get(1).setAmount(23456L);
        Kbn080201Dto.getList().get(2).setAmount(34567L);
        Kbn080201Dto.getList().get(3).setAmount(45678L);
        Kbn080201Dto.getList().get(4).setAmount(56789L);
        Kbn080201Dto.getList().get(5).setAmount(67890L);
        Kbn080201Dto.getList().get(6).setAmount(78901L);
        Kbn080201Dto.getList().get(7).setAmount(89012L);
        Kbn080201Dto.getList().get(8).setAmount(90123L);
        Kbn080201Dto.getList().get(9).setAmount(65432L);

        Sheet0802Dto sheet = new Sheet0802Dto();
        sheet.setKbn080201Dto(Kbn080201Dto);

        Shito0802Dto shito2 = new Shito0802Dto();
        shito2.setSheet0802Dto(sheet);

        int size = insertPartyUsageShito0802And0803Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                shito2, shito3, CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0802And0803Report2025Entity> list = offeringPartyUsage0802And0803Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0802And0803ReportId(documentCode);

        assertThat(size).isEqualTo(1); // 1件だけ登録
        assertThat(list.size()).isEqualTo(size); // 設定と取得が同じ行数

        OfferingPartyUsage0802And0803Report2025Entity entity1 = list.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity1.getSaishinKbn()).isEqualTo(SetTableDataHistoryUtil.IS_SAISHIN);
        assertThat(entity1.getNendo()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getNendo());
        assertThat(entity1.getOfferingDate()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getOfferingDate());
        assertThat(entity1.getPoliticalOrganizationId())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationId());
        assertThat(entity1.getPoliticalOrganizationCode())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity1.getPoliticalOrganizationName())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getPoliticalOrganizationName());
        assertThat(entity1.getDantaiName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDantaiName());
        assertThat(entity1.getDaihyouName()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getDaihyouName());
        assertThat(entity1.getRelationKbn()).isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationKbn());
        assertThat(entity1.getRelationPersonIdDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity1.getRelationPersonCodeDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity1.getRelationPersonNameDelegate())
                .isEqualTo(partyUsageDocumentPoliticalPropertyDto.getRelationPersonNameDelegate());

        assertThat(entity1.getTotalShibuKoufuAll()).isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuAll());
        assertThat(entity1.getTotalShibuKoufuJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuJutoKoufukin());
        assertThat(entity1.getTotalShibuKoufuJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuJutoMyFunds());
        assertThat(entity1.getTotalShibuKoufuBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuBikou());
        assertThat(entity1.getTotalJinkenhiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiAll());
        assertThat(entity1.getTotalJinkenhiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiJutoKoufukin());
        assertThat(entity1.getTotalJinkenhiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiJutoMyFunds());
        assertThat(entity1.getTotalJinkenhiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiBikou());
        assertThat(entity1.getTotalKounetsuhiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiAll());
        assertThat(entity1.getTotalKounetsuhiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiJutoKoufukin());
        assertThat(entity1.getTotalKounetsuhiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiJutoMyFunds());
        assertThat(entity1.getTotalKounetsuhiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiBikou());
        assertThat(entity1.getTotalBihinAll()).isEqualTo(shito3.getSheet0803Dto().getTotalBihinAll());
        assertThat(entity1.getTotalBihinJutoKoufukin()).isEqualTo(shito3.getSheet0803Dto().getTotalBihinJutoKoufukin());
        assertThat(entity1.getTotalBihinJutoMyFunds()).isEqualTo(shito3.getSheet0803Dto().getTotalBihinJutoMyFunds());
        assertThat(entity1.getTotalBihinBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalBihinBikou());
        assertThat(entity1.getTotalJimushoAll()).isEqualTo(shito3.getSheet0803Dto().getTotalJimushoAll());
        assertThat(entity1.getTotalJimushoJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJimushoJutoKoufukin());
        assertThat(entity1.getTotalJimushoJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJimushoJutoMyFunds());
        assertThat(entity1.getTotalJimushoBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalJimushoBikou());
        assertThat(entity1.getTotalKeihiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKeihiAll());
        assertThat(entity1.getTotalKeihiJutoKoufukin()).isEqualTo(shito3.getSheet0803Dto().getTotalKeihiJutoKoufukin());
        assertThat(entity1.getTotalKeihiJutoMyFunds()).isEqualTo(shito3.getSheet0803Dto().getTotalKeihiJutoMyFunds());
        assertThat(entity1.getTotalKeihiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKeihiBikou());
        assertThat(entity1.getTotalSoshikiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiAll());
        assertThat(entity1.getTotalSoshikiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiJutoKoufukin());
        assertThat(entity1.getTotalSoshikiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiJutoMyFunds());
        assertThat(entity1.getTotalSoshikiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiBikou());
        assertThat(entity1.getTotalSenkyoAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoAll());
        assertThat(entity1.getTotalSenkyoJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoJutoKoufukin());
        assertThat(entity1.getTotalSenkyoJutoMyFunds()).isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoJutoMyFunds());
        assertThat(entity1.getTotalSenkyoBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoBikou());
        assertThat(entity1.getTotalAllJigyouAll()).isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouAll());
        assertThat(entity1.getTotalAllJigyouJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouJutoKoufukin());
        assertThat(entity1.getTotalAllJigyouJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouJutoMyFunds());
        assertThat(entity1.getTotalAllJigyouBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouBikou());
        assertThat(entity1.getTotalKikanshiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiAll());
        assertThat(entity1.getTotalKikanshiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiJutoKoufukin());
        assertThat(entity1.getTotalKikanshiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiJutoMyFunds());
        assertThat(entity1.getTotalKikanshiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiBikou());
        assertThat(entity1.getTotalSendenAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSendenAll());
        assertThat(entity1.getTotalSendenJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSendenJutoKoufukin());
        assertThat(entity1.getTotalSendenJutoMyFunds()).isEqualTo(shito3.getSheet0803Dto().getTotalSendenJutoMyFunds());
        assertThat(entity1.getTotalSendenBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSendenBikou());
        assertThat(entity1.getTotalPartyAll()).isEqualTo(shito3.getSheet0803Dto().getTotalPartyAll());
        assertThat(entity1.getTotalPartyJutoKoufukin()).isEqualTo(shito3.getSheet0803Dto().getTotalPartyJutoKoufukin());
        assertThat(entity1.getTotalPartyJutoMyFunds()).isEqualTo(shito3.getSheet0803Dto().getTotalPartyJutoMyFunds());
        assertThat(entity1.getTotalPartyBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalPartyBikou());
        assertThat(entity1.getTotalSonotaJigyouAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouAll());
        assertThat(entity1.getTotalSonotaJigyouJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouJutoKoufukin());
        assertThat(entity1.getTotalSonotaJigyouJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouJutoMyFunds());
        assertThat(entity1.getTotalSonotaJigyouBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouBikou());
        assertThat(entity1.getTotalChousaAll()).isEqualTo(shito3.getSheet0803Dto().getTotalChousaAll());
        assertThat(entity1.getTotalChousaJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalChousaJutoKoufukin());
        assertThat(entity1.getTotalChousaJutoMyFunds()).isEqualTo(shito3.getSheet0803Dto().getTotalChousaJutoMyFunds());
        assertThat(entity1.getTotalChousaBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalChousaBikou());
        assertThat(entity1.getTotalKifuAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKifuAll());
        assertThat(entity1.getTotalKifuJutoKoufukin()).isEqualTo(shito3.getSheet0803Dto().getTotalKifuJutoKoufukin());
        assertThat(entity1.getTotalKifuJutoMyFunds()).isEqualTo(shito3.getSheet0803Dto().getTotalKifuJutoMyFunds());
        assertThat(entity1.getTotalKifuBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKifuBikou());
        assertThat(entity1.getTotalSonotaKeihiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiAll());
        assertThat(entity1.getTotalSonotaKeihiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiJutoKoufukin());
        assertThat(entity1.getTotalSonotaKeihiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiJutoMyFunds());
        assertThat(entity1.getTotalSonotaKeihiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiBikou());
        assertThat(entity1.getTotalAllActionAll()).isEqualTo(shito3.getSheet0803Dto().getTotalAllActionAll());
        assertThat(entity1.getTotalAllActionJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllActionJutoKoufukin());
        assertThat(entity1.getTotalAllActionJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllActionJutoMyFunds());
        assertThat(entity1.getTotalAllActionBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalAllActionBikou());
        assertThat(entity1.getTotalAllAmountAll()).isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountAll());
        assertThat(entity1.getTotalAllAmountJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountJutoKoufukin());
        assertThat(entity1.getTotalAllAmountJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountJutoMyFunds());
        assertThat(entity1.getTotalAllAmountBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountBikou());

        // 政党交付金（支部政党交付金）の総額 --------------------------------------------①
        // list.get(0).getAmount(); party_all_koufukin
        assertThat(entity1.getPartyAllKoufukin()).isEqualTo(Kbn080201Dto.getList().get(0).getAmount());

        // 前年末政党基金（支部基金）残高 ------------------------------------------------②
        // list.get(1).getAmount(); last_year_rest
        assertThat(entity1.getLastYearRest()).isEqualTo(Kbn080201Dto.getList().get(1).getAmount());

        // 政党交付金（支部政党交付金）による支出総額（④＋⑤） --------------------------③
        // list.get(2).getAmount(); outcome_koufukin_all
        assertThat(entity1.getOutcomeKoufukinAll()).isEqualTo(Kbn080201Dto.getList().get(2).getAmount());

        // 政党交付金（支部政党交付金）支出充当額総額 ----------------------------------④
        // list.get(3).getAmount(); outcome_koufukin
        assertThat(entity1.getOutcomeKoufukin()).isEqualTo(Kbn080201Dto.getList().get(3).getAmount());

        // 政党基金（支部基金）支出充当額総額 ------------------------------------------⑤
        // list.get(4).getAmount(); outcome_shibu_kikin
        assertThat(entity1.getOutcomeShibuKikin()).isEqualTo(Kbn080201Dto.getList().get(4).getAmount());

        // 政党基金（支部基金）の積立に充てるために取り崩した政党基金（支部基金）の額 ----⑥
        // list.get(5).getAmount(); //withdrawal_funds
        assertThat(entity1.getWithdrawalFunds()).isEqualTo(Kbn080201Dto.getList().get(5).getAmount());

        // 政党基金（支部基金）積立総額（果実を含む。） ----------------------------------⑦
        // list.get(6).getAmount(); accumulate_funds
        assertThat(entity1.getAccumulateFunds()).isEqualTo(Kbn080201Dto.getList().get(6).getAmount());

        // 政党基金（支部基金）の運用により収受した果実の総額
        // list.get(7).getAmount(); funds_sum_gain
        assertThat(entity1.getFundsSumGain()).isEqualTo(Kbn080201Dto.getList().get(7).getAmount());

        // 本年末等政党基金（支部基金）残高（②－⑤－⑥＋⑦） ----------------------------⑧
        // list.get(8).getAmount(); this_year_rest
        assertThat(entity1.getThisYearRest()).isEqualTo(Kbn080201Dto.getList().get(8).getAmount());

        // （備 考） ①－③＋②－⑧
        // list.get(9).getAmount(); bikou_differ
        assertThat(entity1.getBikouDiffer()).isEqualTo(Kbn080201Dto.getList().get(9).getAmount());

    }

}
