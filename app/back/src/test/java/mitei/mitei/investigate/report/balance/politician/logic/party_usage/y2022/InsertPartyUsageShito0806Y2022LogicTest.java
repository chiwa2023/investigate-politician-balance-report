package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022;


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
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080601Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080602Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080603Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0806Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0806Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0806Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0806Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.service.only_test.usage.CreateTestDataPartyUsageShito0806Logic;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPartyUsageShito0806Y2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPartyUsageShito0806Y2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPartyUsageShito0806Y2022Logic insertPartyUsageShito0806Y2022Logic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;


    /** 様式8その6Repository */
    @Autowired
    private OfferingPartyUsage0806Report2022Repository offeringPartyUsage0806Report2022Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPractice() { // NOPMD
        
        int houkokuNen = 2022;
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

        /* 様式8その6 */
        CreateTestDataPartyUsageShito0806Logic createTestDataPartyUsageShito0806Logic = new CreateTestDataPartyUsageShito0806Logic();
        createTestDataPartyUsageShito0806Logic.practice(allShitoBook);
        Sheet0806Dto sheet0806 = allShitoBook.getShito0806Dto().getList().get(0);
        RowShito0806Dto row0806 = sheet0806.getKbn080601Dto().getList().get(0);
        Kbn080601Dto kbn080601 = sheet0806.getKbn080601Dto();
        Kbn080602Dto kbn080602 = sheet0806.getKbn080602Dto();
        Kbn080603Dto kbn080603 = sheet0806.getKbn080603Dto();

        int size = insertPartyUsageShito0806Y2022Logic.practice(documentCode, documentPropertyDto, allShitoBook.getShito0806Dto(), CreateTestPrivilegeDtoUtil.pracitce());
        
        
        List<OfferingPartyUsage0806Report2022Entity> list0806 = offeringPartyUsage0806Report2022Repository
                .findByDocumentCodeOrderByPartyUsage0806ReportId(documentCode);

        assertThat(list0806.size()).isEqualTo(size);// 区分データ3件と集計データの計4件

        OfferingPartyUsage0806Report2022Entity entity080601 = list0806.get(0);
        OfferingPartyUsage0806Report2022Entity entity080602 = list0806.get(1);
        OfferingPartyUsage0806Report2022Entity entity080603 = list0806.get(2);
        OfferingPartyUsage0806Report2022Entity entity080604 = list0806.get(3);

        /* 全テーブル共通政治団体基礎情報 */
        assertThat(entity080601.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity080601.getNendo()).isEqualTo(documentPropertyDto.getNendo());
        assertThat(entity080601.getOfferingDate()).isEqualTo(documentPropertyDto.getOfferingDate());
        assertThat(entity080601.getPoliticalOrganizationId())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationId());
        assertThat(entity080601.getPoliticalOrganizationCode())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationCode());
        assertThat(entity080601.getPoliticalOrganizationName())
                .isEqualTo(documentPropertyDto.getPoliticalOrganizationName());
        assertThat(entity080601.getDantaiName()).isEqualTo(documentPropertyDto.getDantaiName());
        assertThat(entity080601.getDaihyouName()).isEqualTo(documentPropertyDto.getDaihyouName());
        assertThat(entity080601.getRelationKbn()).isEqualTo(documentPropertyDto.getRelationKbn());
        assertThat(entity080601.getRelationPersonIdDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonIdDelegate());
        assertThat(entity080601.getRelationPersonCodeDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonCodeDelegate());
        assertThat(entity080601.getRelationPersonNameDelegate())
                .isEqualTo(documentPropertyDto.getRelationPersonNameDelegate());

        /* 初期値を繰り返し使うので最初に設定 */
        final String INIT_STRING = "";
        final Integer INIT_INTEGER = 0;
        final Long INIT_LONG = 0L;

        /* 集計データ */
        assertThat(entity080601.getFundsName()).isEqualTo(sheet0806.getFundsName());
        assertThat(entity080601.getTotalLastYear()).isEqualTo(sheet0806.getTotalLastYear());
        assertThat(entity080601.getFundsPurpose()).isEqualTo(sheet0806.getFundsPurpose());
        assertThat(entity080601.getTotal()).isEqualTo(sheet0806.getTotal());
        assertThat(entity080601.getTotalBikou()).isEqualTo(sheet0806.getTotalBikou());
        assertThat(entity080601.getTotalThisYear()).isEqualTo(sheet0806.getTotalThisYear());
        assertThat(entity080601.getTotalThisYearBikou()).isEqualTo(sheet0806.getTotalThisYearBikou());
        assertThat(entity080601.getTotalIncrease()).isEqualTo(sheet0806.getTotalIncrease());
        assertThat(entity080601.getTotalIncreaseBikou()).isEqualTo(sheet0806.getTotalIncreaseBikou());
        /* 入力なしの初期値 */
        assertThat(entity080601.getSubTotal()).isEqualTo(INIT_LONG);
        assertThat(entity080601.getSubTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080601.getRowNo()).isEqualTo(INIT_INTEGER);
        assertThat(entity080601.getAccrualDate()).isEqualTo(INIT_STRING);
        assertThat(entity080601.getAmount()).isEqualTo(INIT_LONG);
        assertThat(entity080601.getBikou()).isEqualTo(INIT_STRING);

        /* 区分明細データ(区分1) */
        assertThat(entity080602.getSubTotal()).isEqualTo(kbn080601.getSubTotal());
        assertThat(entity080602.getSubTotalBikou()).isEqualTo(kbn080601.getSubTotalBikou());
        assertThat(entity080602.getRowNo()).isEqualTo(row0806.getRowNo());
        assertThat(entity080602.getAccrualDate()).isEqualTo(row0806.getAccrualDate());
        assertThat(entity080602.getAmount()).isEqualTo(row0806.getAmount());
        assertThat(entity080602.getBikou()).isEqualTo(row0806.getBikou());
        /* 入力なしの初期値 */
        assertThat(entity080602.getFundsName()).isEqualTo(INIT_STRING);
        assertThat(entity080602.getTotalLastYear()).isEqualTo(INIT_LONG);
        assertThat(entity080602.getFundsPurpose()).isEqualTo(INIT_STRING);
        assertThat(entity080602.getTotal()).isEqualTo(INIT_LONG);
        assertThat(entity080602.getTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080602.getTotalThisYear()).isEqualTo(INIT_LONG);
        assertThat(entity080602.getTotalThisYearBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080602.getTotalIncrease()).isEqualTo(INIT_LONG);
        assertThat(entity080602.getTotalIncreaseBikou()).isEqualTo(INIT_STRING);

        /* 区分明細データ(区分2) */
        assertThat(entity080603.getSubTotal()).isEqualTo(kbn080602.getSubTotal());
        assertThat(entity080603.getSubTotalBikou()).isEqualTo(kbn080602.getSubTotalBikou());
        assertThat(entity080603.getRowNo()).isEqualTo(row0806.getRowNo());
        assertThat(entity080603.getAccrualDate()).isEqualTo(row0806.getAccrualDate());
        assertThat(entity080603.getAmount()).isEqualTo(row0806.getAmount());
        assertThat(entity080603.getBikou()).isEqualTo(row0806.getBikou());
        /* 入力なしの初期値 */
        assertThat(entity080603.getFundsName()).isEqualTo(INIT_STRING);
        assertThat(entity080603.getTotalLastYear()).isEqualTo(INIT_LONG);
        assertThat(entity080603.getFundsPurpose()).isEqualTo(INIT_STRING);
        assertThat(entity080603.getTotal()).isEqualTo(INIT_LONG);
        assertThat(entity080603.getTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080603.getTotalThisYear()).isEqualTo(INIT_LONG);
        assertThat(entity080603.getTotalThisYearBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080603.getTotalIncrease()).isEqualTo(INIT_LONG);
        assertThat(entity080603.getTotalIncreaseBikou()).isEqualTo(INIT_STRING);

        /* 区分明細データ(区分3) */
        assertThat(entity080604.getSubTotal()).isEqualTo(kbn080603.getSubTotal());
        assertThat(entity080604.getSubTotalBikou()).isEqualTo(kbn080603.getSubTotalBikou());
        assertThat(entity080604.getRowNo()).isEqualTo(row0806.getRowNo());
        assertThat(entity080604.getAccrualDate()).isEqualTo(row0806.getAccrualDate());
        assertThat(entity080604.getAmount()).isEqualTo(row0806.getAmount());
        assertThat(entity080604.getBikou()).isEqualTo(row0806.getBikou());
        /* 入力なしの初期値 */
        assertThat(entity080604.getFundsName()).isEqualTo(INIT_STRING);
        assertThat(entity080604.getTotalLastYear()).isEqualTo(INIT_LONG);
        assertThat(entity080604.getFundsPurpose()).isEqualTo(INIT_STRING);
        assertThat(entity080604.getTotal()).isEqualTo(INIT_LONG);
        assertThat(entity080604.getTotalBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080604.getTotalThisYear()).isEqualTo(INIT_LONG);
        assertThat(entity080604.getTotalThisYearBikou()).isEqualTo(INIT_STRING);
        assertThat(entity080604.getTotalIncrease()).isEqualTo(INIT_LONG);
        assertThat(entity080604.getTotalIncreaseBikou()).isEqualTo(INIT_STRING);

    }

}
