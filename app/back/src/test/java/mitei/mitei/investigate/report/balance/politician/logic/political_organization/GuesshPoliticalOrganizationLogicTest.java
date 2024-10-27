package mitei.mitei.investigate.report.balance.politician.logic.political_organization;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet072000OathDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;

/**
 * GuesshPoliticalOrganizationLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GuesshPoliticalOrganizationLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GuesshPoliticalOrganizationLogic guesshPoliticalOrganizationLogic;

    @Test
    @Sql("political_organization.sql")
    void testPractice() { //NOPMD

        // 初期登録から変更がなく、すべて一致
        Sheet070100CoverAndOrganizationDetailsDto coverDto00 = new Sheet070100CoverAndOrganizationDetailsDto();
        // 政治団体名
        coverDto00.setDantaiName01("ちゃらんぽらん政治団体1");
        // 代表者名
        coverDto00.setDaihyoushaNameFirst("太郎"); // NOPMD
        coverDto00.setDaihyoushaNameLast("代表者");
        // 報告年
        coverDto00.setHoukokuNen(2022);
        // 団体区分
        coverDto00.setDantaiKbn("3");
        // 事務所住所
        coverDto00.setJimushoJusho("東京都千代田区霞が関"); // NOPMD
        coverDto00.setJimushoJushoTatemono("三角ビル309");
        // 宣誓日
        Sheet072000OathDto oathDto00 = new Sheet072000OathDto();
        oathDto00.setDateOath("R5/2/17");

        // Idと代表者名が想定と一致していること
        BalancesheetReportDocumentPoliticalPropertyDto dto00 = guesshPoliticalOrganizationLogic.practice(coverDto00,
                oathDto00);
        assertThat(dto00.getPoliticalOrganizationId()).isEqualTo(114L);
        assertThat(dto00.getPoliticalOrganizationName()).isEqualTo(coverDto00.getDantaiName01());
        assertThat(dto00.getHoukokuNen()).isEqualTo(coverDto00.getHoukokuNen());

        // 団体名一致が存在していない
        coverDto00.setDantaiName01("ちゃらんぽらん政治団体X");
        BalancesheetReportDocumentPoliticalPropertyDto dto01 = guesshPoliticalOrganizationLogic.practice(coverDto00,
                oathDto00);
        assertThat(dto01.getPoliticalOrganizationId()).isEqualTo(0L); // 空Dto

        // 代表者名一致が存在していない
        coverDto00.setDaihyoushaNameFirst("代表者");
        coverDto00.setDaihyoushaNameLast("太郎Ver2");
        coverDto00.setDantaiName01("ちゃらんぽらん政治団体1");
        BalancesheetReportDocumentPoliticalPropertyDto dto02 = guesshPoliticalOrganizationLogic.practice(coverDto00,
                oathDto00);
        assertThat(dto02.getPoliticalOrganizationId()).isEqualTo(0L); // 空Dto

        // 団体区分一致が存在していない
        coverDto00.setDaihyoushaNameFirst("代表者");
        coverDto00.setDaihyoushaNameLast("太郎");
        coverDto00.setDantaiKbn("1");
        BalancesheetReportDocumentPoliticalPropertyDto dto03 = guesshPoliticalOrganizationLogic.practice(coverDto00,
                oathDto00);
        assertThat(dto03.getPoliticalOrganizationId()).isEqualTo(0L); // 空Dto

        // 住所一致が存在していない
        coverDto00.setDantaiKbn("3");
        coverDto00.setJimushoJusho("東京都千代田区霞が関");
        coverDto00.setJimushoJushoTatemono("三角ビル310");
        BalancesheetReportDocumentPoliticalPropertyDto dto04 = guesshPoliticalOrganizationLogic.practice(coverDto00,
                oathDto00);
        assertThat(dto04.getPoliticalOrganizationId()).isEqualTo(0L); // 空Dto

        // nullの場合、落ちずに空Dtoを返すだけ
        coverDto00.setDantaiName01(null);
        coverDto00.setDaihyoushaNameFirst(null);
        coverDto00.setDaihyoushaNameLast(null);
        coverDto00.setDantaiKbn(null);
        BalancesheetReportDocumentPoliticalPropertyDto dto05 = guesshPoliticalOrganizationLogic.practice(coverDto00,
                oathDto00);
        assertThat(dto05.getPoliticalOrganizationId()).isEqualTo(0L); // 空Dto

        // 住所や会計責任者で変更があったが、すべて基準日より前の変更なので最新データ
        Sheet070100CoverAndOrganizationDetailsDto coverDto01 = new Sheet070100CoverAndOrganizationDetailsDto();
        // 政治団体名
        coverDto01.setDantaiName01("ちゃらんぽらん政治団体2");
        // 代表者名
        coverDto01.setDaihyoushaNameFirst("太郎");
        coverDto01.setDaihyoushaNameLast("代表者");
        // 報告年
        coverDto01.setHoukokuNen(2022);
        // 団体区分
        coverDto01.setDantaiKbn("3");
        // 事務所住所
        coverDto01.setJimushoJusho("東京都千代田区霞が関");
        coverDto01.setJimushoJushoTatemono("三角ビル309");
        // 宣誓日
        Sheet072000OathDto oathDto01 = new Sheet072000OathDto();
        oathDto01.setDateOath("R5/2/17");

        BalancesheetReportDocumentPoliticalPropertyDto dto11 = guesshPoliticalOrganizationLogic.practice(coverDto01,
                oathDto01);
        assertThat(dto11.getPoliticalOrganizationId()).isEqualTo(496L); // 最新データ

        // どういうわけだか報告年のところでくるくる値を変えているので、報告年までの該当データ
        Sheet070100CoverAndOrganizationDetailsDto coverDto02 = new Sheet070100CoverAndOrganizationDetailsDto();
        // 政治団体名
        coverDto02.setDantaiName01("ちゃらんぽらん政治団体3");
        // 代表者名
        coverDto02.setDaihyoushaNameFirst("太郎");
        coverDto02.setDaihyoushaNameLast("代表者");
        // 報告年
        coverDto02.setHoukokuNen(2022);
        // 団体区分
        coverDto02.setDantaiKbn("3");
        // 事務所住所
        coverDto02.setJimushoJusho("東京都千代田区霞が関");
        coverDto02.setJimushoJushoTatemono("三角ビル309");
        // 宣誓日
        Sheet072000OathDto oathDto02 = new Sheet072000OathDto();
        oathDto02.setDateOath("R5/2/17");

        BalancesheetReportDocumentPoliticalPropertyDto dto12 = guesshPoliticalOrganizationLogic.practice(coverDto02,
                oathDto02);
        assertThat(dto12.getPoliticalOrganizationId()).isEqualTo(611L); // 範囲名での最新データ
        // ※2022年の報告書を出そうとしたとき、2023年3月提出の報告書で、1月に団体名(代表者名)を変更した場合、
        // 新しい団体名で報告書を出す場合、本当は推測を出してはいけない
        // 現状2022年末の基準日に旧団体名であったので、旧団体名で出しているはずだ、という推定の元、実装を行っている。したがって
        // TODO 運用に対応して変更する可能性がある

    }


}
