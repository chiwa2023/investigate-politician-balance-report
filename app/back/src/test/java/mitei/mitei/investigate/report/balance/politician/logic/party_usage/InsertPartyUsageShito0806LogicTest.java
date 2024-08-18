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

import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080601Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080602Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080603Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0806Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0806Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0806Dto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0806Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingPartyUsage0806Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * InsertPartyUsageShito0806Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@SuppressWarnings("PMD.UseUnderscoresInNumericLiterals")
class InsertPartyUsageShito0806LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0806Logic insertPartyUsageShito0806Logic;

    /** 様式8その6Repository */
    @Autowired
    private OfferingPartyUsage0806Report2025Repository offeringPartyUsage0806Report2025Repository;

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

        Sheet0806Dto sheet = new Sheet0806Dto();
        sheet.setFundsName("極悪同盟専用");
        sheet.setTotalLastYear(99999999L);
        sheet.setFundsPurpose("強烈な裏金買収");
        sheet.setTotal(1111L);
        sheet.setTotalBikou("合計備考");
        sheet.setTotalThisYear(91919191919L);
        sheet.setTotalThisYearBikou("本年残備考");
        sheet.setTotalIncrease(334L);
        sheet.setTotalIncreaseBikou("増減備考");

        RowShito0806Dto row = new RowShito0806Dto();
        row.setRowNo(22);
        row.setAccrualDate("R4/9/19");
        row.setAmount(997755L);
        row.setBikou("備品");

        Kbn080601Dto kbn1 = new Kbn080601Dto();
        kbn1.setSubTotal(654654L);
        kbn1.setSubTotalBikou("積み立て特別備考");
        kbn1.getList().add(row);

        Kbn080602Dto kbn2 = new Kbn080602Dto();
        kbn2.setSubTotal(754654L);
        kbn2.setSubTotalBikou("果実特別備考");
        kbn2.getList().add(row);

        Kbn080603Dto kbn3 = new Kbn080603Dto();
        kbn3.setSubTotal(854654L);
        kbn3.setSubTotalBikou("取り崩し特別備考");
        kbn3.getList().add(row);

        sheet.setKbn080601Dto(kbn1);
        sheet.setKbn080602Dto(kbn2);
        sheet.setKbn080603Dto(kbn3);

        Shito0806Dto shito0806Dto = new Shito0806Dto();
        shito0806Dto.getList().add(sheet);

        int size = insertPartyUsageShito0806Logic.practice(documentCode, partyUsageDocumentPoliticalPropertyDto,
                shito0806Dto, CreateTestPrivilegeDtoUtil.pracitce());

        List<OfferingPartyUsage0806Report2025Entity> list = offeringPartyUsage0806Report2025Repository
                .findByDocumentCodeOrderByPartyUsage0806ReportId(documentCode);

        assertThat(size).isEqualTo(4);// 区分データ3件と集計データの計4件
        assertThat(list.size()).isEqualTo(size);// 設定数と取得数が同じ

        OfferingPartyUsage0806Report2025Entity entity1 = list.get(0);
        OfferingPartyUsage0806Report2025Entity entity2 = list.get(1);
        OfferingPartyUsage0806Report2025Entity entity3 = list.get(2);
        OfferingPartyUsage0806Report2025Entity entity4 = list.get(3);

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

        /* 初期値を繰り返し使うので最初に設定 */
        final String INIT_STRING = "";
        final Integer INIT_INTEGER = 0;
        final Long INIT_LONG = 0L;

        /* 集計データ */
        assertThat(entity1.getFundsName()).isEqualTo(sheet.getFundsName());
        assertThat(entity1.getTotalLastYear()).isEqualTo(sheet.getTotalLastYear());
        assertThat(entity1.getFundsPurpose()).isEqualTo(sheet.getFundsPurpose());
        assertThat(entity1.getTotal()).isEqualTo(sheet.getTotal());
        assertThat(entity1.getTotalBikou()).isEqualTo(sheet.getTotalBikou());
        assertThat(entity1.getTotalThisYear()).isEqualTo(sheet.getTotalThisYear());
        assertThat(entity1.getTotalThisYearBikou()).isEqualTo(sheet.getTotalThisYearBikou());
        assertThat(entity1.getTotalIncrease()).isEqualTo(sheet.getTotalIncrease());
        assertThat(entity1.getTotalIncreaseBikou()).isEqualTo(sheet.getTotalIncreaseBikou());
        /* 入力なしの初期値 */
        assertThat(entity1.getSubTotal()).isEqualTo(INIT_LONG);
        assertThat(entity1.getSubTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity1.getRowNo()).isEqualTo(INIT_INTEGER);
        assertThat(entity1.getAccrualDate()).isEqualTo(INIT_STRING);
        assertThat(entity1.getAmount()).isEqualTo(INIT_LONG);
        assertThat(entity1.getBikou()).isEqualTo(INIT_STRING);

        /* 区分明細データ(区分1) */
        assertThat(entity2.getSubTotal()).isEqualTo(kbn1.getSubTotal());
        assertThat(entity2.getSubTotalBikou()).isEqualTo(kbn1.getSubTotalBikou());
        assertThat(entity2.getRowNo()).isEqualTo(row.getRowNo());
        assertThat(entity2.getAccrualDate()).isEqualTo(row.getAccrualDate());
        assertThat(entity2.getAmount()).isEqualTo(row.getAmount());
        assertThat(entity2.getBikou()).isEqualTo(row.getBikou());
        /* 入力なしの初期値 */
        assertThat(entity2.getFundsName()).isEqualTo(INIT_STRING);
        assertThat(entity2.getTotalLastYear()).isEqualTo(INIT_LONG);
        assertThat(entity2.getFundsPurpose()).isEqualTo(INIT_STRING);
        assertThat(entity2.getTotal()).isEqualTo(INIT_LONG);
        assertThat(entity2.getTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity2.getTotalThisYear()).isEqualTo(INIT_LONG);
        assertThat(entity2.getTotalThisYearBikou()).isEqualTo(INIT_STRING);
        assertThat(entity2.getTotalIncrease()).isEqualTo(INIT_LONG);
        assertThat(entity2.getTotalIncreaseBikou()).isEqualTo(INIT_STRING);

        /* 区分明細データ(区分2) */
        assertThat(entity3.getSubTotal()).isEqualTo(kbn2.getSubTotal());
        assertThat(entity3.getSubTotalBikou()).isEqualTo(kbn2.getSubTotalBikou());
        assertThat(entity3.getRowNo()).isEqualTo(row.getRowNo());
        assertThat(entity3.getAccrualDate()).isEqualTo(row.getAccrualDate());
        assertThat(entity3.getAmount()).isEqualTo(row.getAmount());
        assertThat(entity3.getBikou()).isEqualTo(row.getBikou());
        /* 入力なしの初期値 */
        assertThat(entity3.getFundsName()).isEqualTo(INIT_STRING);
        assertThat(entity3.getTotalLastYear()).isEqualTo(INIT_LONG);
        assertThat(entity3.getFundsPurpose()).isEqualTo(INIT_STRING);
        assertThat(entity3.getTotal()).isEqualTo(INIT_LONG);
        assertThat(entity3.getTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity3.getTotalThisYear()).isEqualTo(INIT_LONG);
        assertThat(entity3.getTotalThisYearBikou()).isEqualTo(INIT_STRING);
        assertThat(entity3.getTotalIncrease()).isEqualTo(INIT_LONG);
        assertThat(entity3.getTotalIncreaseBikou()).isEqualTo(INIT_STRING);

        /* 区分明細データ(区分3) */
        assertThat(entity4.getSubTotal()).isEqualTo(kbn3.getSubTotal());
        assertThat(entity4.getSubTotalBikou()).isEqualTo(kbn3.getSubTotalBikou());
        assertThat(entity4.getRowNo()).isEqualTo(row.getRowNo());
        assertThat(entity4.getAccrualDate()).isEqualTo(row.getAccrualDate());
        assertThat(entity4.getAmount()).isEqualTo(row.getAmount());
        assertThat(entity4.getBikou()).isEqualTo(row.getBikou());
        /* 入力なしの初期値 */
        assertThat(entity4.getFundsName()).isEqualTo(INIT_STRING);
        assertThat(entity4.getTotalLastYear()).isEqualTo(INIT_LONG);
        assertThat(entity4.getFundsPurpose()).isEqualTo(INIT_STRING);
        assertThat(entity4.getTotal()).isEqualTo(INIT_LONG);
        assertThat(entity4.getTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity4.getTotalThisYear()).isEqualTo(INIT_LONG);
        assertThat(entity4.getTotalThisYearBikou()).isEqualTo(INIT_STRING);
        assertThat(entity4.getTotalIncrease()).isEqualTo(INIT_LONG);
        assertThat(entity4.getTotalIncreaseBikou()).isEqualTo(INIT_STRING);

    }

}
