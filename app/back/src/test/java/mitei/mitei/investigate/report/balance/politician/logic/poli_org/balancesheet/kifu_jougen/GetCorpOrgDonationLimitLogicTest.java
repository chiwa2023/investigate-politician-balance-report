package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

import mitei.mitei.investigate.report.balance.politician.constants.CorpDonationLimitDantaiKbnConstants;

/**
 * GetCorpOrgDonationLimitLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("donation_limit.sql")
class GetCorpOrgDonationLimitLogicTest {  // NOPMD
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetCorpOrgDonationLimitLogic getCorpOrgDonationLimitLogic;

    /** 企業 */
    private static final int CORP = CorpDonationLimitDantaiKbnConstants.CORP;
    /** 組合 */
    private static final int UNION = CorpDonationLimitDantaiKbnConstants.UNION;
    /** その他団体 */
    private static final int OTHER = CorpDonationLimitDantaiKbnConstants.OTHER;

    /** テストタグ */
    private static final String TEST_TAG = "TableTruncate";
    
    
    @Test
    @Tag(TEST_TAG)
    void test750() {
        final Long limit = 7_500_000L;
        // 下限(厳密には0)
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 1L), "資本金1円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 1L), "組合人数1人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 1L), "前年経費1円");

        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 20L), "最新区分0：履歴の500万円制限は無効20円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 20L), "最新区分0：履歴の500万円制限は無効20人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 20L), "最新区分0：履歴の500万円制限は無効20円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 999_999_999L), "資本金999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 49_999L), "組合人数49,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 19_999_999L), "前年経費19,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test1500() {

        final Long limit = 15_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 1_000_000_000L), "資本金1,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 50_000L), "組合人数50,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 20_000_000L), "前年経費20,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 4_999_999_999L), "資本金4,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 99_999L), "組合人数99,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 59_999_999L), "前年経費59,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test3000() {

        final Long limit = 30_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 5_000_000_000L), "資本金5,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 100_000L), "組合人数100,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 60_000_000L), "前年経費60,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 9_999_999_999L), "資本金9,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 149_999L), "組合人数149,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 79_999_999L), "前年経費79,999,999円");
    }

    @Test
    @Tag(TEST_TAG)
    void test3500() {

        final Long limit = 35_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 10_000_000_000L), "資本金10,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 150_000L), "組合人数150,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 80_000_000L), "前年経費80,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 14_999_999_999L), "資本金14,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 199_999L), "組合人数199,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 99_999_999L), "前年経費99,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test4000() {

        final Long limit = 40_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 15_000_000_000L), "資本金15,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 200_000L), "組合人数200,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 100_000_000L), "前年経費100,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 19_999_999_999L), "資本金19,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 249_999L), "組合人数249,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 119_999_999L), "前年経費119,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test4500() {

        final Long limit = 45_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 20_000_000_000L), "資本金20,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 250_000L), "組合人数250,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 120_000_000L), "前年経費120,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 24_999_999_999L), "資本金24,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 299_999L), "組合人数299,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 139_999_999L), "前年経費139,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test5000() {

        final Long limit = 50_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 25_000_000_000L), "資本金25,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 300_000L), "組合人数300,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 140_000_000L), "前年経費140,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 29_999_999_999L), "資本金29,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 349_999L), "組合人数349,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 159_999_999L), "前年経費159,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test5500() {

        final Long limit = 55_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 30_000_000_000L), "資本金30,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 350_000L), "組合人数350,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 160_000_000L), "前年経費160,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 34_999_999_999L), "資本金34,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 399_999L), "組合人数399,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 179_999_999L), "前年経費179,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test6000() {

        final Long limit = 60_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 35_000_000_000L), "資本金35,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 400_000L), "組合人数400,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 180_000_000L), "前年経費180,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 39_999_999_999L), "資本金39,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 449_999L), "組合人数449,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 199_999_999L), "前年経費199,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test6300() {

        final Long limit = 63_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 40_000_000_000L), "資本金40,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 450_000L), "組合人数450,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 200_000_000L), "前年経費200,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 44_999_999_999L), "資本金44,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 499_999L), "組合人数499,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 219_999_999L), "前年経費219,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test6600() {

        final Long limit = 66_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 45_000_000_000L), "資本金45,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 500_000L), "組合人数500,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 220_000_000L), "前年経費220,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 49_999_999_999L), "資本金49,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 549_999L), "組合人数549,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 239_999_999L), "前年経費239,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test6900() {

        final Long limit = 69_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 50_000_000_000L), "資本金50,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 550_000L), "組合人数550,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 240_000_000L), "前年経費240,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 54_999_999_999L), "資本金54,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 599_999L), "組合人数599,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 259_999_999L), "前年経費259,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test7200() {

        final Long limit = 72_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 55_000_000_000L), "資本金55,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 600_000L), "組合人数600,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 260_000_000L), "前年経費260,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 59_999_999_999L), "資本金59,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 649_999L), "組合人数649,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 279_999_999L), "前年経費279,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test7500() {

        final Long limit = 75_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 60_000_000_000L), "資本金60,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 650_000L), "組合人数650,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 280_000_000L), "前年経費280,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 64_999_999_999L), "資本金64,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 699_999L), "組合人数699,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 299_999_999L), "前年経費299,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test7800() {

        final Long limit = 78_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 65_000_000_000L), "資本金65,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 700_000L), "組合人数700,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 300_000_000L), "前年経費300,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 69_999_999_999L), "資本金69,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 749_999L), "組合人数749,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 319_999_999L), "前年経費319,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test8100() {

        final Long limit = 81_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 70_000_000_000L), "資本金70,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 750_000L), "組合人数750,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 320_000_000L), "前年経費320,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 74_999_999_999L), "資本金74,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 799_999L), "組合人数799,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 339_999_999L), "前年経費339,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test8400() {

        final Long limit = 84_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 75_000_000_000L), "資本金75,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 800_000L), "組合人数800,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 340_000_000L), "前年経費340,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 79_999_999_999L), "資本金79,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 849_999L), "組合人数849,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 359_999_999L), "前年経費359,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test8700() {

        final Long limit = 87_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 80_000_000_000L), "資本金80,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 850_000L), "組合人数850,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 360_000_000L), "前年経費360,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 84_999_999_999L), "資本金84,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 899_999L), "組合人数899,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 379_999_999L), "前年経費379,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test9000() {

        final Long limit = 90_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 85_000_000_000L), "資本金85,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 900_000L), "組合人数900,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 380_000_000L), "前年経費380,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 89_999_999_999L), "資本金89,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 949_999L), "組合人数949,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 399_999_999L), "前年経費399,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test9300() {

        final Long limit = 93_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 90_000_000_000L), "資本金90,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 950_000L), "組合人数950,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 400_000_000L), "前年経費400,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 94_999_999_999L), "資本金94,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 999_999L), "組合人数999,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 419_999_999L), "前年経費419,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test9600() {

        final Long limit = 96_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 95_000_000_000L), "資本金95,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 1_000_000L), "組合人数1,000,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 420_000_000L), "前年経費420,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 99_999_999_999L), "資本金99,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 1_049_999L), "組合人数1,049,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 439_999_999L), "前年経費439,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test9900() {

        final Long limit = 99_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 100_000_000_000L), "資本金100,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 1_050_000L), "組合人数1,050,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 440_000_000L), "前年経費440,000,000円");

        // 上限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 104_999_999_999L), "資本金104,999,999,999円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 1_099_999L), "組合人数1,099,999人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 459_999_999L), "前年経費459,999,999円");

    }

    @Test
    @Tag(TEST_TAG)
    void test10000() {

        final Long limit = 100_000_000L;

        // 下限
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(CORP, 105_000_000_000L), "資本金105,000,000,000円");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(UNION, 1_100_000L), "組合人数1,100,000人");
        assertEquals(limit, getCorpOrgDonationLimitLogic.practice(OTHER, 460_000_000L), "前年経費460,000,000円");

        // 上限はない
    }

    @Test
    @Tag(TEST_TAG)
    void testException() {
        assertThrows(IllegalArgumentException.class, () -> getCorpOrgDonationLimitLogic.practice(6, 200L), "団体区分にない値");
        assertThrows(IllegalArgumentException.class, () -> getCorpOrgDonationLimitLogic.practice(CORP, -3L),
                "1未満は許容しない");
    }

}
