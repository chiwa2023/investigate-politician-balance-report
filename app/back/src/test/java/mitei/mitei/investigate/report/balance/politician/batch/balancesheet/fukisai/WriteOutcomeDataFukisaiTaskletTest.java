package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.fukisai;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblFukisaiBalancesheetRepository;

/**
 * WriteOutcomeDataFukisaiTasklet単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBatchTest
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class WriteOutcomeDataFukisaiTaskletTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private WriteOutcomeDataFukisaiTasklet writeOutcomeDataFukisaiTasklet;

    /** 不記載検出ワークテーブル */
    @Autowired
    private WkTblFukisaiBalancesheetRepository wkTblFukisaiBalancesheetRepository;

    /** 報告年 */
    private static final int houkokunen = 2022;
    /** ユーザid */
    private static final long userId = 1022;
    /** ユーザ同一識別コード */
    private static final int userCode = 970;
    /** ユーザ名 */
    private static final String userName = "ユーザ";
    /** 政治団体コード */
    private static final int poliOrgCode = 431;

    /** コード検索該否1 */
    private static final boolean isSearchCode1 = true;

    /** コード検索該否2 */
    private static final boolean isSearchCode2 = false;

    @Test
    @Tag("TableTruncate")
    //@Transactional
    @Sql({ "convert_outcome_fukisai_2022.sql", "outcome_wk_tbl_fukisai.sql" })
    void testSearchCode() throws Exception {

        assertEquals(5L, wkTblFukisaiBalancesheetRepository.count(), "初期状態で別ユーザー2件と対象3件");

        StepExecution execution = this.getStepExecutionCode();
        writeOutcomeDataFukisaiTasklet.beforeStep(execution);

        assertEquals(RepeatStatus.FINISHED, writeOutcomeDataFukisaiTasklet.execute(null, null), "終了ステータスが戻る");

        List<WkTblFukisaiBalancesheetEntity> listTemp = wkTblFukisaiBalancesheetRepository.findAll();

        List<WkTblFukisaiBalancesheetEntity> listEntity = listTemp.stream()
                .filter(e -> e.getInsertUserCode().equals(userCode) && e.getDocumentCodeInput().equals(0L)).toList();

        assertEquals(3, listEntity.size(), "3件登録");

        // 取得コード特定
        WkTblFukisaiBalancesheetEntity entity00 = listEntity.get(0);
        assertEquals(7501L, entity00.getKingakuOutput(), "金額収入1");

        WkTblFukisaiBalancesheetEntity entity01 = listEntity.get(1);
        assertEquals(7502L, entity01.getKingakuOutput(), "金額収入2");

        WkTblFukisaiBalancesheetEntity entity02 = listEntity.get(2);
        assertEquals(7503L, entity02.getKingakuOutput(), "金額収入3");

        // 詳細
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
        assertEquals("ホリエモン新党", entity00.getPartnerName(), "原文書取引相手名称"); // NOPMD
        assertEquals("東京都台東区谷中", entity00.getPartnerJuusho(), "取引相手住所");
        assertEquals("", entity00.getShokugyou(), "取引相手職業");
        assertEquals(433L, entity00.getRelationPoliticalOrgId(), "取引相手Id");
        assertEquals(431, entity00.getRelationPoliticalOrgCode(), "取引相手同一識別コード");
        assertEquals("ホリエモン新党", entity00.getRelationPoliticalOrgName(), "取引相手名称");

        assertEquals(1, entity00.getSaishinKbn(), "最新区分");

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "convert_outcome_fukisai_2022.sql", "outcome_wk_tbl_fukisai.sql" })
    void testSearchName() throws Exception {

        assertEquals(5L, wkTblFukisaiBalancesheetRepository.count(), "初期状態で別ユーザー2件と対象3件");

        StepExecution execution = this.getStepExecutionName();
        writeOutcomeDataFukisaiTasklet.beforeStep(execution);

        assertEquals(RepeatStatus.FINISHED, writeOutcomeDataFukisaiTasklet.execute(null, null), "終了ステータスが戻る");

        List<WkTblFukisaiBalancesheetEntity> listTemp = wkTblFukisaiBalancesheetRepository.findAll();

        List<WkTblFukisaiBalancesheetEntity> listEntity = listTemp.stream()
                .filter(e -> e.getInsertUserCode().equals(userCode) && e.getDocumentCodeInput().equals(0L)).toList();

        assertEquals(3, listEntity.size(), "3件登録");

        // 取得コード特定
        WkTblFukisaiBalancesheetEntity entity00 = listEntity.get(0);
        assertEquals(7501L, entity00.getKingakuOutput(), "金額収入1");

        WkTblFukisaiBalancesheetEntity entity01 = listEntity.get(1);
        assertEquals(7502L, entity01.getKingakuOutput(), "金額収入2");

        WkTblFukisaiBalancesheetEntity entity02 = listEntity.get(2);
        assertEquals(7503L, entity02.getKingakuOutput(), "金額収入3");

        // 詳細
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

        assertEquals(1, entity00.getSaishinKbn(), "最新区分");

    }

    private StepExecution getStepExecutionCode() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", userId).addLong("userCode", Long.valueOf(userCode)).addString("userName", userName)
                .addLong("poliOrgCode", Long.valueOf(poliOrgCode)).addLong("houkokunen", Long.valueOf(houkokunen))
                .addString("isSearchCode", String.valueOf(isSearchCode1)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

    private StepExecution getStepExecutionName() {

        // JobParameterの設定
        JobParameters jobParameters = new JobParametersBuilder().addLocalDateTime("now", LocalDateTime.now())
                .addLong("userId", userId).addLong("userCode", Long.valueOf(userCode)).addString("userName", userName)
                .addLong("poliOrgCode", Long.valueOf(poliOrgCode)).addLong("houkokunen", Long.valueOf(houkokunen))
                .addString("isSearchCode", String.valueOf(isSearchCode2)).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
