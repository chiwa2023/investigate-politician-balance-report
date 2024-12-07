package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080201Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0803Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2024.OfferingPartyUsage0802And0803Report2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0802And0803Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0802And0803Logic;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPartyUsageShito0802And0803Y2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0802And0803Y2024LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0802And0803Y2024Logic insertPartyUsageShito0802And0803Y2024Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 様式8その2区分1と3Repository */
    @Autowired
    private OfferingPartyUsage0802And0803Report2024Repository offeringPartyUsage0802And0803Report2024Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPractice() { // NOPMD

        int houkokuNen = 2024;
        String oathDateDtring = "R6/12/01";

        // 政治団体基礎情報
        Long documentCode = 3434L;
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentPropertyDto.setNendo(houkokuNen);
        documentPropertyDto.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate(oathDateDtring));
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");

        AllShitoBook allShitoBook = new AllShitoBook();

        /* 様式8集計表その2と3 */
        CreateTestDataPartyUsageShito0802And0803Logic createTestDataPartyUsageShito0802And0803Logic = new CreateTestDataPartyUsageShito0802And0803Logic();
        createTestDataPartyUsageShito0802And0803Logic.practice(allShitoBook);

        insertPartyUsageShito0802And0803Y2024Logic.practice(documentCode, documentPropertyDto,
                allShitoBook.getShito0802Dto(), allShitoBook.getShito0803Dto(), CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0802And0803Report2024Entity> listSummary = offeringPartyUsage0802And0803Report2024Repository
                .findByDocumentCodeOrderByPartyUsage0802And0803ReportId(documentCode);

        assertThat(listSummary.size()).isEqualTo(1); // 1件だけ登録

        OfferingPartyUsage0802And0803Report2024Entity entity08021 = listSummary.get(0);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity08021.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity08021.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity08021.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity08021.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity08021.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity08021.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity08021.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity08021.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity08021.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity08021.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity08021.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity08021.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        Shito0803Dto shito3 = allShitoBook.getShito0803Dto();
        assertThat(entity08021.getTotalShibuKoufuAll()).isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuAll());
        assertThat(entity08021.getTotalShibuKoufuJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuJutoKoufukin());
        assertThat(entity08021.getTotalShibuKoufuJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuJutoMyFunds());
        assertThat(entity08021.getTotalShibuKoufuBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalShibuKoufuBikou());
        assertThat(entity08021.getTotalJinkenhiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiAll());
        assertThat(entity08021.getTotalJinkenhiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiJutoKoufukin());
        assertThat(entity08021.getTotalJinkenhiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiJutoMyFunds());
        assertThat(entity08021.getTotalJinkenhiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalJinkenhiBikou());
        assertThat(entity08021.getTotalKounetsuhiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiAll());
        assertThat(entity08021.getTotalKounetsuhiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiJutoKoufukin());
        assertThat(entity08021.getTotalKounetsuhiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiJutoMyFunds());
        assertThat(entity08021.getTotalKounetsuhiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKounetsuhiBikou());
        assertThat(entity08021.getTotalBihinAll()).isEqualTo(shito3.getSheet0803Dto().getTotalBihinAll());
        assertThat(entity08021.getTotalBihinJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalBihinJutoKoufukin());
        assertThat(entity08021.getTotalBihinJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalBihinJutoMyFunds());
        assertThat(entity08021.getTotalBihinBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalBihinBikou());
        assertThat(entity08021.getTotalJimushoAll()).isEqualTo(shito3.getSheet0803Dto().getTotalJimushoAll());
        assertThat(entity08021.getTotalJimushoJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJimushoJutoKoufukin());
        assertThat(entity08021.getTotalJimushoJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalJimushoJutoMyFunds());
        assertThat(entity08021.getTotalJimushoBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalJimushoBikou());
        assertThat(entity08021.getTotalKeihiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKeihiAll());
        assertThat(entity08021.getTotalKeihiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKeihiJutoKoufukin());
        assertThat(entity08021.getTotalKeihiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKeihiJutoMyFunds());
        assertThat(entity08021.getTotalKeihiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKeihiBikou());
        assertThat(entity08021.getTotalSoshikiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiAll());
        assertThat(entity08021.getTotalSoshikiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiJutoKoufukin());
        assertThat(entity08021.getTotalSoshikiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiJutoMyFunds());
        assertThat(entity08021.getTotalSoshikiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSoshikiBikou());
        assertThat(entity08021.getTotalSenkyoAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoAll());
        assertThat(entity08021.getTotalSenkyoJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoJutoKoufukin());
        assertThat(entity08021.getTotalSenkyoJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoJutoMyFunds());
        assertThat(entity08021.getTotalSenkyoBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSenkyoBikou());
        assertThat(entity08021.getTotalAllJigyouAll()).isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouAll());
        assertThat(entity08021.getTotalAllJigyouJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouJutoKoufukin());
        assertThat(entity08021.getTotalAllJigyouJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouJutoMyFunds());
        assertThat(entity08021.getTotalAllJigyouBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalAllJigyouBikou());
        assertThat(entity08021.getTotalKikanshiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiAll());
        assertThat(entity08021.getTotalKikanshiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiJutoKoufukin());
        assertThat(entity08021.getTotalKikanshiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiJutoMyFunds());
        assertThat(entity08021.getTotalKikanshiBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKikanshiBikou());
        assertThat(entity08021.getTotalSendenAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSendenAll());
        assertThat(entity08021.getTotalSendenJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSendenJutoKoufukin());
        assertThat(entity08021.getTotalSendenJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSendenJutoMyFunds());
        assertThat(entity08021.getTotalSendenBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalSendenBikou());
        assertThat(entity08021.getTotalPartyAll()).isEqualTo(shito3.getSheet0803Dto().getTotalPartyAll());
        assertThat(entity08021.getTotalPartyJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalPartyJutoKoufukin());
        assertThat(entity08021.getTotalPartyJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalPartyJutoMyFunds());
        assertThat(entity08021.getTotalPartyBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalPartyBikou());
        assertThat(entity08021.getTotalSonotaJigyouAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouAll());
        assertThat(entity08021.getTotalSonotaJigyouJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouJutoKoufukin());
        assertThat(entity08021.getTotalSonotaJigyouJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouJutoMyFunds());
        assertThat(entity08021.getTotalSonotaJigyouBikou())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaJigyouBikou());
        assertThat(entity08021.getTotalChousaAll()).isEqualTo(shito3.getSheet0803Dto().getTotalChousaAll());
        assertThat(entity08021.getTotalChousaJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalChousaJutoKoufukin());
        assertThat(entity08021.getTotalChousaJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalChousaJutoMyFunds());
        assertThat(entity08021.getTotalChousaBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalChousaBikou());
        assertThat(entity08021.getTotalKifuAll()).isEqualTo(shito3.getSheet0803Dto().getTotalKifuAll());
        assertThat(entity08021.getTotalKifuJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalKifuJutoKoufukin());
        assertThat(entity08021.getTotalKifuJutoMyFunds()).isEqualTo(shito3.getSheet0803Dto().getTotalKifuJutoMyFunds());
        assertThat(entity08021.getTotalKifuBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalKifuBikou());
        assertThat(entity08021.getTotalSonotaKeihiAll()).isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiAll());
        assertThat(entity08021.getTotalSonotaKeihiJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiJutoKoufukin());
        assertThat(entity08021.getTotalSonotaKeihiJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiJutoMyFunds());
        assertThat(entity08021.getTotalSonotaKeihiBikou())
                .isEqualTo(shito3.getSheet0803Dto().getTotalSonotaKeihiBikou());
        assertThat(entity08021.getTotalAllActionAll()).isEqualTo(shito3.getSheet0803Dto().getTotalAllActionAll());
        assertThat(entity08021.getTotalAllActionJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllActionJutoKoufukin());
        assertThat(entity08021.getTotalAllActionJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllActionJutoMyFunds());
        assertThat(entity08021.getTotalAllActionBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalAllActionBikou());
        assertThat(entity08021.getTotalAllAmountAll()).isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountAll());
        assertThat(entity08021.getTotalAllAmountJutoKoufukin())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountJutoKoufukin());
        assertThat(entity08021.getTotalAllAmountJutoMyFunds())
                .isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountJutoMyFunds());
        assertThat(entity08021.getTotalAllAmountBikou()).isEqualTo(shito3.getSheet0803Dto().getTotalAllAmountBikou());

        Kbn080201Dto Kbn080201Dto = allShitoBook.getShito0802Dto().getSheet0802Dto().getKbn080201Dto();
        // 政党交付金（支部政党交付金）の総額 --------------------------------------------①
        // list.get(0).getAmount(); party_all_koufukin
        assertThat(entity08021.getPartyAllKoufukin()).isEqualTo(Kbn080201Dto.getList().get(0).getAmount());

        // 前年末政党基金（支部基金）残高 ------------------------------------------------②
        // list.get(1).getAmount(); last_year_rest
        assertThat(entity08021.getLastYearRest()).isEqualTo(Kbn080201Dto.getList().get(1).getAmount());

        // 政党交付金（支部政党交付金）による支出総額（④＋⑤） --------------------------③
        // list.get(2).getAmount(); outcome_koufukin_all
        assertThat(entity08021.getOutcomeKoufukinAll()).isEqualTo(Kbn080201Dto.getList().get(2).getAmount());

        // 政党交付金（支部政党交付金）支出充当額総額 ----------------------------------④
        // list.get(3).getAmount(); outcome_koufukin
        assertThat(entity08021.getOutcomeKoufukin()).isEqualTo(Kbn080201Dto.getList().get(3).getAmount());

        // 政党基金（支部基金）支出充当額総額 ------------------------------------------⑤
        // list.get(4).getAmount(); outcome_shibu_kikin
        assertThat(entity08021.getOutcomeShibuKikin()).isEqualTo(Kbn080201Dto.getList().get(4).getAmount());

        // 政党基金（支部基金）の積立に充てるために取り崩した政党基金（支部基金）の額 ----⑥
        // list.get(5).getAmount(); //withdrawal_funds
        assertThat(entity08021.getWithdrawalFunds()).isEqualTo(Kbn080201Dto.getList().get(5).getAmount());

        // 政党基金（支部基金）積立総額（果実を含む。） ----------------------------------⑦
        // list.get(6).getAmount(); accumulate_funds
        assertThat(entity08021.getAccumulateFunds()).isEqualTo(Kbn080201Dto.getList().get(6).getAmount());

        // 政党基金（支部基金）の運用により収受した果実の総額
        // list.get(7).getAmount(); funds_sum_gain
        assertThat(entity08021.getFundsSumGain()).isEqualTo(Kbn080201Dto.getList().get(7).getAmount());

        // 本年末等政党基金（支部基金）残高（②－⑤－⑥＋⑦） ----------------------------⑧
        // list.get(8).getAmount(); this_year_rest
        assertThat(entity08021.getThisYearRest()).isEqualTo(Kbn080201Dto.getList().get(8).getAmount());

        // （備 考） ①－③＋②－⑧
        // list.get(9).getAmount(); bikou_differ
        assertThat(entity08021.getBikouDiffer()).isEqualTo(Kbn080201Dto.getList().get(9).getAmount());
    }

}
