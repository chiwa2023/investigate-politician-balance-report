package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import reactor.util.function.Tuple2;

/**
 * GetDonationLimitListLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql("donation_limit.sql")
class GetDonationLimitListLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetDonationLimitListLogic getDonationLimitListLogic;

    /** 件数テキスト */
    private static final String kensuText = "23件取得";
    /** 寄付テキスト */
    private static final String limitText = "寄付上限";
    /** 経費テキスト */
    private static final String keihiText = "経費上限";
    /** 資本テキスト */
    private static final String shihonText = "資本上限";
    /** 員数テキスト */
    private static final String insuText = "員数上限";

    @Test
    @Tag("TableTruncate")
    void testKeihi() throws Exception { // NOPMD

        List<Tuple2<Long, Long>> list = getDonationLimitListLogic.practiceKeihi();

        assertEquals(23, list.size(), kensuText);

        Tuple2<Long, Long> item00 = list.get(0);
        assertEquals(100_000_000L, item00.get(1), limitText);
        assertEquals(460_000_000L, item00.get(0), keihiText);

        Tuple2<Long, Long> item01 = list.get(1);
        assertEquals(99_000_000L, item01.get(1), limitText);
        assertEquals(440_000_000L, item01.get(0), keihiText);

        Tuple2<Long, Long> item02 = list.get(2);
        assertEquals(96_000_000L, item02.get(1), limitText);
        assertEquals(420_000_000L, item02.get(0), keihiText);

        Tuple2<Long, Long> item03 = list.get(3);
        assertEquals(93_000_000L, item03.get(1), limitText);
        assertEquals(400_000_000L, item03.get(0), keihiText);

        Tuple2<Long, Long> item04 = list.get(4);
        assertEquals(90_000_000L, item04.get(1), limitText);
        assertEquals(380_000_000L, item04.get(0), keihiText);

        Tuple2<Long, Long> item05 = list.get(5);
        assertEquals(87_000_000L, item05.get(1), limitText);
        assertEquals(360_000_000L, item05.get(0), keihiText);

        Tuple2<Long, Long> item06 = list.get(6);
        assertEquals(84_000_000L, item06.get(1), limitText);
        assertEquals(340_000_000L, item06.get(0), keihiText);

        Tuple2<Long, Long> item07 = list.get(7);
        assertEquals(81_000_000L, item07.get(1), limitText);
        assertEquals(320_000_000L, item07.get(0), keihiText);

        Tuple2<Long, Long> item08 = list.get(8);
        assertEquals(78_000_000L, item08.get(1), limitText);
        assertEquals(300_000_000L, item08.get(0), keihiText);

        Tuple2<Long, Long> item09 = list.get(9);
        assertEquals(75_000_000L, item09.get(1), limitText);
        assertEquals(280_000_000L, item09.get(0), keihiText);

        Tuple2<Long, Long> item10 = list.get(10);
        assertEquals(72_000_000L, item10.get(1), limitText);
        assertEquals(260_000_000L, item10.get(0), keihiText);

        Tuple2<Long, Long> item11 = list.get(11);
        assertEquals(69_000_000L, item11.get(1), limitText);
        assertEquals(240_000_000L, item11.get(0), keihiText);

        Tuple2<Long, Long> item12 = list.get(12);
        assertEquals(66_000_000L, item12.get(1), limitText);
        assertEquals(220_000_000L, item12.get(0), keihiText);

        Tuple2<Long, Long> item13 = list.get(13);
        assertEquals(63_000_000L, item13.get(1), limitText);
        assertEquals(200_000_000L, item13.get(0), keihiText);

        Tuple2<Long, Long> item14 = list.get(14);
        assertEquals(60_000_000L, item14.get(1), limitText);
        assertEquals(180_000_000L, item14.get(0), keihiText);

        Tuple2<Long, Long> item15 = list.get(15);
        assertEquals(55_000_000L, item15.get(1), limitText);
        assertEquals(160_000_000L, item15.get(0), keihiText);

        Tuple2<Long, Long> item16 = list.get(16);
        assertEquals(50_000_000L, item16.get(1), limitText);
        assertEquals(140_000_000L, item16.get(0), keihiText);

        Tuple2<Long, Long> item17 = list.get(17);
        assertEquals(45_000_000L, item17.get(1), limitText);
        assertEquals(120_000_000L, item17.get(0), keihiText);

        Tuple2<Long, Long> item18 = list.get(18);
        assertEquals(40_000_000L, item18.get(1), limitText);
        assertEquals(100_000_000L, item18.get(0), keihiText);

        Tuple2<Long, Long> item19 = list.get(19);
        assertEquals(35_000_000L, item19.get(1), limitText);
        assertEquals(80_000_000L, item19.get(0), keihiText);

        Tuple2<Long, Long> item20 = list.get(20);
        assertEquals(30_000_000L, item20.get(1), limitText);
        assertEquals(60_000_000L, item20.get(0), keihiText);

        Tuple2<Long, Long> item21 = list.get(21);
        assertEquals(15_000_000L, item21.get(1), limitText);
        assertEquals(20_000_000L, item21.get(0), keihiText);

        Tuple2<Long, Long> item22 = list.get(22);
        assertEquals(7_500_000L, item22.get(1), limitText);
        assertEquals(0L, item22.get(0), keihiText);

    }

    @Test
    @Tag("TableTruncate")
    void testInsu() throws Exception { // NOPMD

        List<Tuple2<Long, Long>> list = getDonationLimitListLogic.practiceShozokuInsuu();

        assertEquals(23, list.size(), kensuText);

        Tuple2<Long, Long> item00 = list.get(0);
        assertEquals(100_000_000L, item00.get(1), limitText);
        assertEquals(1_100_000L, item00.get(0), insuText);

        Tuple2<Long, Long> item01 = list.get(1);
        assertEquals(99_000_000L, item01.get(1), limitText);
        assertEquals(1_050_000L, item01.get(0), insuText);

        Tuple2<Long, Long> item02 = list.get(2);
        assertEquals(96_000_000L, item02.get(1), limitText);
        assertEquals(1_000_000L, item02.get(0), insuText);

        Tuple2<Long, Long> item03 = list.get(3);
        assertEquals(93_000_000L, item03.get(1), limitText);
        assertEquals(950_000L, item03.get(0), insuText);

        Tuple2<Long, Long> item04 = list.get(4);
        assertEquals(90_000_000L, item04.get(1), limitText);
        assertEquals(900_000L, item04.get(0), insuText);

        Tuple2<Long, Long> item05 = list.get(5);
        assertEquals(87_000_000L, item05.get(1), limitText);
        assertEquals(850_000L, item05.get(0), insuText);

        Tuple2<Long, Long> item06 = list.get(6);
        assertEquals(84_000_000L, item06.get(1), limitText);
        assertEquals(800_000L, item06.get(0), insuText);

        Tuple2<Long, Long> item07 = list.get(7);
        assertEquals(81_000_000L, item07.get(1), limitText);
        assertEquals(750_000L, item07.get(0), insuText);

        Tuple2<Long, Long> item08 = list.get(8);
        assertEquals(78_000_000L, item08.get(1), limitText);
        assertEquals(700_000L, item08.get(0), insuText);

        Tuple2<Long, Long> item09 = list.get(9);
        assertEquals(75_000_000L, item09.get(1), limitText);
        assertEquals(650_000L, item09.get(0), insuText);

        Tuple2<Long, Long> item10 = list.get(10);
        assertEquals(72_000_000L, item10.get(1), limitText);
        assertEquals(600_000L, item10.get(0), insuText);

        Tuple2<Long, Long> item11 = list.get(11);
        assertEquals(69_000_000L, item11.get(1), limitText);
        assertEquals(550_000L, item11.get(0), insuText);

        Tuple2<Long, Long> item12 = list.get(12);
        assertEquals(66_000_000L, item12.get(1), limitText);
        assertEquals(500_000L, item12.get(0), insuText);

        Tuple2<Long, Long> item13 = list.get(13);
        assertEquals(63_000_000L, item13.get(1), limitText);
        assertEquals(450_000L, item13.get(0), insuText);

        Tuple2<Long, Long> item14 = list.get(14);
        assertEquals(60_000_000L, item14.get(1), limitText);
        assertEquals(400_000L, item14.get(0), insuText);

        Tuple2<Long, Long> item15 = list.get(15);
        assertEquals(55_000_000L, item15.get(1), limitText);
        assertEquals(350_000L, item15.get(0), insuText);

        Tuple2<Long, Long> item16 = list.get(16);
        assertEquals(50_000_000L, item16.get(1), limitText);
        assertEquals(300_000L, item16.get(0), insuText);

        Tuple2<Long, Long> item17 = list.get(17);
        assertEquals(45_000_000L, item17.get(1), limitText);
        assertEquals(250_000L, item17.get(0), insuText);

        Tuple2<Long, Long> item18 = list.get(18);
        assertEquals(40_000_000L, item18.get(1), limitText);
        assertEquals(200_000L, item18.get(0), insuText);

        Tuple2<Long, Long> item19 = list.get(19);
        assertEquals(35_000_000L, item19.get(1), limitText);
        assertEquals(150_000L, item19.get(0), insuText);

        Tuple2<Long, Long> item20 = list.get(20);
        assertEquals(30_000_000L, item20.get(1), limitText);
        assertEquals(100_000L, item20.get(0), insuText);

        Tuple2<Long, Long> item21 = list.get(21);
        assertEquals(15_000_000L, item21.get(1), limitText);
        assertEquals(50_000L, item21.get(0), insuText);

        Tuple2<Long, Long> item22 = list.get(22);
        assertEquals(7_500_000L, item22.get(1), limitText);
        assertEquals(0L, item22.get(0), insuText);

    }

    @Test
    @Tag("TableTruncate")
    void testShihon() throws Exception { // NOPMD

        List<Tuple2<Long, Long>> list = getDonationLimitListLogic.practiceShihonkin();

        assertEquals(23, list.size(), kensuText);

        Tuple2<Long, Long> item00 = list.get(0);
        assertEquals(100_000_000L, item00.get(1), limitText);
        assertEquals(105_000_000_000L, item00.get(0), shihonText);

        Tuple2<Long, Long> item01 = list.get(1);
        assertEquals(99_000_000L, item01.get(1), limitText);
        assertEquals(100_000_000_000L, item01.get(0), shihonText);

        Tuple2<Long, Long> item02 = list.get(2);
        assertEquals(96_000_000L, item02.get(1), limitText);
        assertEquals(95_000_000_000L, item02.get(0), shihonText);

        Tuple2<Long, Long> item03 = list.get(3);
        assertEquals(93_000_000L, item03.get(1), limitText);
        assertEquals(90_000_000_000L, item03.get(0), shihonText);

        Tuple2<Long, Long> item04 = list.get(4);
        assertEquals(90_000_000L, item04.get(1), limitText);
        assertEquals(85_000_000_000L, item04.get(0), shihonText);

        Tuple2<Long, Long> item05 = list.get(5);
        assertEquals(87_000_000L, item05.get(1), limitText);
        assertEquals(80_000_000_000L, item05.get(0), shihonText);

        Tuple2<Long, Long> item06 = list.get(6);
        assertEquals(84_000_000L, item06.get(1), limitText);
        assertEquals(75_000_000_000L, item06.get(0), shihonText);

        Tuple2<Long, Long> item07 = list.get(7);
        assertEquals(81_000_000L, item07.get(1), limitText);
        assertEquals(70_000_000_000L, item07.get(0), shihonText);

        Tuple2<Long, Long> item08 = list.get(8);
        assertEquals(78_000_000L, item08.get(1), limitText);
        assertEquals(65_000_000_000L, item08.get(0), shihonText);

        Tuple2<Long, Long> item09 = list.get(9);
        assertEquals(75_000_000L, item09.get(1), limitText);
        assertEquals(60_000_000_000L, item09.get(0), shihonText);

        Tuple2<Long, Long> item10 = list.get(10);
        assertEquals(72_000_000L, item10.get(1), limitText);
        assertEquals(55_000_000_000L, item10.get(0), shihonText);

        Tuple2<Long, Long> item11 = list.get(11);
        assertEquals(69_000_000L, item11.get(1), limitText);
        assertEquals(50_000_000_000L, item11.get(0), shihonText);

        Tuple2<Long, Long> item12 = list.get(12);
        assertEquals(66_000_000L, item12.get(1), limitText);
        assertEquals(45_000_000_000L, item12.get(0), shihonText);

        Tuple2<Long, Long> item13 = list.get(13);
        assertEquals(63_000_000L, item13.get(1), limitText);
        assertEquals(40_000_000_000L, item13.get(0), shihonText);

        Tuple2<Long, Long> item14 = list.get(14);
        assertEquals(60_000_000L, item14.get(1), limitText);
        assertEquals(35_000_000_000L, item14.get(0), shihonText);

        Tuple2<Long, Long> item15 = list.get(15);
        assertEquals(55_000_000L, item15.get(1), limitText);
        assertEquals(30_000_000_000L, item15.get(0), shihonText);

        Tuple2<Long, Long> item16 = list.get(16);
        assertEquals(50_000_000L, item16.get(1), limitText);
        assertEquals(25_000_000_000L, item16.get(0), shihonText);

        Tuple2<Long, Long> item17 = list.get(17);
        assertEquals(45_000_000L, item17.get(1), limitText);
        assertEquals(20_000_000_000L, item17.get(0), shihonText);

        Tuple2<Long, Long> item18 = list.get(18);
        assertEquals(40_000_000L, item18.get(1), limitText);
        assertEquals(15_000_000_000L, item18.get(0), shihonText);

        Tuple2<Long, Long> item19 = list.get(19);
        assertEquals(35_000_000L, item19.get(1), limitText);
        assertEquals(10_000_000_000L, item19.get(0), shihonText);

        Tuple2<Long, Long> item20 = list.get(20);
        assertEquals(30_000_000L, item20.get(1), limitText);
        assertEquals(5_000_000_000L, item20.get(0), shihonText);

        Tuple2<Long, Long> item21 = list.get(21);
        assertEquals(15_000_000L, item21.get(1), limitText);
        assertEquals(1_000_000_000L, item21.get(0), shihonText);

        Tuple2<Long, Long> item22 = list.get(22);
        assertEquals(7_500_000L, item22.get(1), limitText);
        assertEquals(0L, item22.get(0), shihonText);

    }
}
