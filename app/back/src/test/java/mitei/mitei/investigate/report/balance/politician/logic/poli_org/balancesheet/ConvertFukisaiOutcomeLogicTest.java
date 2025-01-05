package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

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
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiSearchConditionDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;
// 2022
// 2025
// 2024
// import追加指定位置

/**
 * ConvertFukisaiOutcomeLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ConvertFukisaiOutcomeLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ConvertFukisaiOutcomeLogic convertFukisaiOutcomeLogic;
    
    // テストタグ
    private static final String TEST_TAG = "TableTruncate"; // NOPMD

    @Test
    @Tag(TEST_TAG)
    @Transactional
    @Sql({"y2022/convert_outcome_fukisai_2022.sql","y2022/outcome_wk_tbl_fukisai.sql"})
    void test() throws Exception {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        privilegeDto.setLoginUserCode(970);
        
        int poliOrgCode = 431;

        List<Integer> listRelation = new ArrayList<>();
        listRelation.add(620);
        listRelation.add(720);
        listRelation.add(820);

        FukisaiSearchConditionDto conditionDto = new FukisaiSearchConditionDto();
        conditionDto.setIsSearchCode(true);
        conditionDto.setPoliOrgCode(poliOrgCode);
        conditionDto.setDantaiName("ホリエモン新党"); // NOPMD
        conditionDto.setHoukokuNen(2022);

        List<WkTblFukisaiBalancesheetEntity> listEntity = convertFukisaiOutcomeLogic.practice(conditionDto,
                privilegeDto);

        assertEquals(3, listEntity.size(), "取得件数3件");

        // 取得コード特定
        WkTblFukisaiBalancesheetEntity entity00 = listEntity.get(0);
        assertEquals(7501L, entity00.getKingakuOutput(), "金額収入1");

        WkTblFukisaiBalancesheetEntity entity01 = listEntity.get(1);
        assertEquals(7502L, entity01.getKingakuOutput(), "金額収入2");

        WkTblFukisaiBalancesheetEntity entity02 = listEntity.get(2);
        assertEquals(7503L, entity02.getKingakuOutput(), "金額収入3");

        // 詳細
        assertEquals(0, entity00.getWkTblFukisaiBalancesheetId(), "全体Id");
        assertEquals(2022, entity00.getHoukokuNen(), "報告年");
        assertEquals(LocalDate.of(2023, 2, 17), entity00.getOfferingDate(), "提出日");
        assertEquals(0, entity00.getDocumentCodeInput(), "文書同一識別コード収入");
        assertEquals(882, entity00.getDocumentCodeOutput(), "文書同一識別コード支出");
        assertEquals("のんびり党新宿", entity00.getDantaiName(), "団体名");
        assertEquals("代表者1", entity00.getDaihyouName(), "代表者名");
        assertEquals(622L, entity00.getPoliticalOrganizationId(), "政治団体Id");
        assertEquals(620, entity00.getPoliticalOrganizationCode(), "政治団体同一識別コード");
        assertEquals("のんびり党登録1", entity00.getPoliticalOrganizationName(), "政治団体名称");
        assertEquals(9898L, entity00.getRelationPersonIdDelegate(), "代表者Id");
        assertEquals(9867, entity00.getRelationPersonCodeDelegate(), "代表者同一識別コード");
        assertEquals("立花孝志", entity00.getRelationPersonNameDelegate(), "代表者名称");
        assertEquals(15, entity00.getYoushikiKbn(), "様式区分");
        assertEquals(8, entity00.getYoushikiEdaKbn(), "様式区分コード");
        assertEquals("寄付", entity00.getItemName(), "項目名");
        assertEquals(0L, entity00.getKingakuInput(), "金額収入");
        assertEquals(7501L, entity00.getKingakuOutput(), "金額支出");
        assertEquals("R4/12/9", entity00.getAccrualDate(), "発生日");
        assertEquals(LocalDate.of(2022, 12, 9), entity00.getAccrualDateValue(), "発生日実値");
        assertEquals("ホリエモン新党", entity00.getPartnerName(), "原文書取引相手名称");
        assertEquals("東京都台東区谷中", entity00.getPartnerJuusho(), "取引相手住所");
        assertEquals("", entity00.getShokugyou(), "取引相手職業");
        assertEquals(433L, entity00.getRelationPoliticalOrgId(), "取引相手Id");
        assertEquals(431, entity00.getRelationPoliticalOrgCode(), "取引相手同一識別コード");
        assertEquals("ホリエモン新党", entity00.getRelationPoliticalOrgName(), "取引相手名称");

        assertEquals("00000000000000000620", entity00.getSearchOrderKey(), "検索順Key");
        assertEquals(1, entity00.getSaishinKbn(), "最新区分");

    }

    // テンプレート開始位置
    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2022/convert_outcome_fukisai_2022.sql")
    void testPractice2022() {
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        FukisaiSearchConditionDto conditionDto = new FukisaiSearchConditionDto();
        conditionDto.setIsSearchCode(true);
        conditionDto.setPoliOrgCode(431);
        conditionDto.setDantaiName("ホリエモン新党");
        conditionDto.setHoukokuNen(2022);

        List<WkTblFukisaiBalancesheetEntity> listEntity = convertFukisaiOutcomeLogic.practice(conditionDto,
                privilegeDto);

        assertEquals(3, listEntity.size(), "指定したテーブルから取得されている");
    }
    // テンプレート終了位置

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2025/convert_outcome_fukisai_2025.sql")
    void testPractice2025() {
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        FukisaiSearchConditionDto conditionDto = new FukisaiSearchConditionDto();
        conditionDto.setIsSearchCode(true);
        conditionDto.setPoliOrgCode(431);
        conditionDto.setDantaiName("ホリエモン新党");
        conditionDto.setHoukokuNen(2025);

        List<WkTblFukisaiBalancesheetEntity> listEntity = convertFukisaiOutcomeLogic.practice(conditionDto,
                privilegeDto);

        assertEquals(3, listEntity.size(), "指定したテーブルから取得されている");
    }

    @Test
    @Transactional
    @Tag(TEST_TAG)
    @Sql("y2024/convert_outcome_fukisai_2024.sql")
    void testPractice2024() {
        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        FukisaiSearchConditionDto conditionDto = new FukisaiSearchConditionDto();
        conditionDto.setIsSearchCode(true);
        conditionDto.setPoliOrgCode(431);
        conditionDto.setDantaiName("ホリエモン新党");
        conditionDto.setHoukokuNen(2024);

        List<WkTblFukisaiBalancesheetEntity> listEntity = convertFukisaiOutcomeLogic.practice(conditionDto,
                privilegeDto);

        assertEquals(3, listEntity.size(), "指定したテーブルから取得されている");
    }

    // 追加位置
}
