package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022;


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
 * CheckAllreadyRegistDataY2022Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CheckAllreadyRegistDataPartyUsageY2022LogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CheckAllreadyRegistDataPartyUsageY2022Logic checkAllreadyRegistDataY2022Logic;
    
    
    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;
    
    @Test
    @Transactional
    @Sql("check_allready_regist_data_2022.sql")
    void testPractice() {

        // 政治団体基礎情報
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentPropertyDto.setNendo(2022); // 実際には表紙からコピー
        documentPropertyDto.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate("R5/3/21")); // 実際には宣誓書からコピー
        documentPropertyDto.setPoliticalOrganizationId(433L);
        documentPropertyDto.setPoliticalOrganizationCode(431);
        documentPropertyDto.setPoliticalOrganizationName("ちゃらんぽらん政治団体");
        documentPropertyDto.setDantaiName("ちゃらん団体");
        documentPropertyDto.setDaihyouName("代表者 世間芸名");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationPersonIdDelegate(9898L);
        documentPropertyDto.setRelationPersonCodeDelegate(9867);
        documentPropertyDto.setRelationPersonNameDelegate("代表者　戸籍の名前");
        
        List<Long> list = checkAllreadyRegistDataY2022Logic.practice(documentPropertyDto);
        
        // テストデータで挿入される文書同一識別コードは320,479,881,645,217,836
        // 881は提出日違いで更新対象から除外される
        // 645は政治団体Id違いで更新対象から除外される
        assertTrue(list.contains(320L), "コード320は更新対象に含まれる");
        assertTrue(list.contains(479L), "コード479は更新対象に含まれる");
        assertFalse(list.contains(881L), "コード881は更新対象に含まれない(提出日)");
        assertFalse(list.contains(645L), "コード645は更新対象に含まれない(政治団体同一識別コード)");
        assertTrue(list.contains(217L), "コード217は更新対象に含まれる");
        assertTrue(list.contains(836L), "コード836は更新対象に含まれる");
    }

}
