package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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

import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * CheckAllreadyRegistDataPartyUsageLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CheckAllreadyRegistDataPartyUsageLogicTest {
    // CHECKSTYLE:OFF

    /** 既存新規データ確認Logic */
    @Autowired
    private CheckAllreadyRegistDataPartyUsageLogic checkAllreadyRegistDataPartyUsageLogic;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    @Test
    @Transactional
    @Sql("check_allready_regist_data.sql")
    void testPractice() {

        // 政治団体基礎情報
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto1 = new PartyUsageDocumentPoliticalPropertyDto();
        documentPropertyDto1.setNendo(2022); // 実際には表紙からコピー
        documentPropertyDto1.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate("R5/3/21")); // 実際には宣誓書からコピー
        documentPropertyDto1.setPoliticalOrganizationId(433L);
        documentPropertyDto1.setPoliticalOrganizationCode(431);
        documentPropertyDto1.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto1.setDantaiName("ちゃらん団体");
        documentPropertyDto1.setDaihyouName("代表者 世間芸名");
        documentPropertyDto1.setRelationKbn(223);
        documentPropertyDto1.setRelationPersonIdDelegate(9898L);
        documentPropertyDto1.setRelationPersonCodeDelegate(9867);
        documentPropertyDto1.setRelationPersonNameDelegate("代表者　戸籍の名前");

        List<Long> list1 = checkAllreadyRegistDataPartyUsageLogic.practice(documentPropertyDto1);

        // テストデータで挿入される文書同一識別コードは320,479,881,645,217,836
        // 881は提出日違いで更新対象から除外される
        // 645は政治団体Id違いで更新対象から除外される
        assertTrue(list1.contains(320L), "コード320は更新対象に含まれる");
        assertTrue(list1.contains(479L), "コード479は更新対象に含まれる");
        assertFalse(list1.contains(881L), "コード881は更新対象に含まれない(提出日)");
        assertFalse(list1.contains(645L), "コード645は更新対象に含まれない(政治団体同一識別コード)");
        assertTrue(list1.contains(217L), "コード217は更新対象に含まれる");
        assertTrue(list1.contains(836L), "コード836は更新対象に含まれる");

        // その他の報告年、2025年テーブルからは一切抽出されない
        assertFalse(list1.contains(1020L), "コード1020は更新対象に含まれない(テーブル違い)");
        assertFalse(list1.contains(1021L), "コード1021は更新対象に含まれない(テーブル違い)");
        assertFalse(list1.contains(1022L), "コード1022は更新対象に含まれない(テーブル違い)");
        assertFalse(list1.contains(1023L), "コード1023は更新対象に含まれない(テーブル違い)");
        assertFalse(list1.contains(1024L), "コード1024は更新対象に含まれない(テーブル違い)");
        assertFalse(list1.contains(1025L), "コード1025は更新対象に含まれない(テーブル違い)");

        /* 文書属性Dtoの報告年によって登録テーブルが切り替わること */
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto2 = new PartyUsageDocumentPoliticalPropertyDto();
        documentPropertyDto2.setNendo(2025); // 実際には表紙からコピー
        documentPropertyDto2.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate("R5/3/21")); // 実際には宣誓書からコピー
        documentPropertyDto2.setPoliticalOrganizationId(433L);
        documentPropertyDto2.setPoliticalOrganizationCode(431);
        documentPropertyDto2.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto2.setDantaiName("ちゃらん団体");
        documentPropertyDto2.setDaihyouName("代表者 世間芸名");
        documentPropertyDto2.setRelationKbn(223);
        documentPropertyDto2.setRelationPersonIdDelegate(9898L);
        documentPropertyDto2.setRelationPersonCodeDelegate(9867);
        documentPropertyDto2.setRelationPersonNameDelegate("代表者　戸籍の名前");

        List<Long> list2 = checkAllreadyRegistDataPartyUsageLogic.practice(documentPropertyDto2);

        // その他の報告年、2025年テーブルからは一切抽出されない
        assertTrue(list2.contains(1020L), "コード1020は更新対象に含まれる");
        assertTrue(list2.contains(1021L), "コード1021は更新対象に含まれる");
        assertFalse(list2.contains(1022L), "コード1022は更新対象に含まれない(提出日)");
        assertFalse(list2.contains(1023L), "コード1023は更新対象に含まれない(政治団体同一識別コード)");
        assertTrue(list2.contains(1024L), "コード1024は更新対象に含まれる");
        assertTrue(list2.contains(1025L), "コード1025は更新対象に含まれる");

        assertFalse(list2.contains(320L), "コード320は更新対象に含まれない(テーブル違い)");
        assertFalse(list2.contains(479L), "コード479は更新対象に含まれない(テーブル違い)");
        assertFalse(list2.contains(881L), "コード881は更新対象に含まれない(テーブル違い)");
        assertFalse(list2.contains(645L), "コード645は更新対象に含まれない(テーブル違い)");
        assertFalse(list2.contains(217L), "コード217は更新対象に含まれない(テーブル違い)");
        assertFalse(list2.contains(836L), "コード836は更新対象に含まれない(テーブル違い)");

    }

}
