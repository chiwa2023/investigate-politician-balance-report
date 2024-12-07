package mitei.mitei.investigate.report.balance.politician.logic.political_organization;

import static org.assertj.core.api.Assertions.assertThat;

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

import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0801Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0807Dto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;

/**
 * GuesshPoliticalOrganizationByPartyUsageLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GuesshPoliticalOrganizationByPartyUsageLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GuessPoliticalOrganizationByPartyUsageLogic guessPoliticalOrganizationByPartyUsageLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("political_organization_party.sql")
    void testPractice() throws Exception {

        // 初期登録から変更がなく、すべて一致
        Sheet0801Dto sheet0801Dto = new Sheet0801Dto();
        // 政治団体(政党)名
        sheet0801Dto.setPartyName("ちゃらんぽらん政党1");
        // 代表者名
        sheet0801Dto.setDelegateName("代表者　太郎"); // NOPMD
        // 報告年
        sheet0801Dto.setNendo(2022);
        // 団体区分検索条件だがDtoに存在しないのでLogicで補う 政党の定数固定なので補っても問題ない TODO 定数化した時点で修正する
        // 事務所住所
        sheet0801Dto.setOfficeAddress("東京都千代田区霞が関　三角ビル309"); // NOPMD

        Sheet0807Dto sheet0807Dto = new Sheet0807Dto();
        // 宣誓日
        sheet0807Dto.setAccrualDate("R5/2/17");

        // Idと代表者名と報告年が想定と一致していること
        PartyUsageDocumentPoliticalPropertyDto dto00 = guessPoliticalOrganizationByPartyUsageLogic
                .practice(sheet0801Dto, sheet0807Dto);
        assertThat(dto00.getPoliticalOrganizationId()).isEqualTo(129L);
        assertThat(dto00.getPoliticalOrganizationName()).isEqualTo(sheet0801Dto.getPartyName());
        assertThat(dto00.getNendo()).isEqualTo(sheet0801Dto.getNendo());

        // 団体名一致が存在していない
        sheet0801Dto.setPartyName("ちゃらんぽらん政党X");
        PartyUsageDocumentPoliticalPropertyDto dto01 = guessPoliticalOrganizationByPartyUsageLogic
                .practice(sheet0801Dto, sheet0807Dto);
        assertThat(dto01.getPoliticalOrganizationId()).isEqualTo(0L); // 空Dto

        // 代表者名一致が存在していない
        sheet0801Dto.setDelegateName("代表者　太郎Ver2"); // NOPMD
        sheet0801Dto.setPartyName("ちゃらんぽらん政党1");
        PartyUsageDocumentPoliticalPropertyDto dto02 = guessPoliticalOrganizationByPartyUsageLogic
                .practice(sheet0801Dto, sheet0807Dto);
        assertThat(dto02.getPoliticalOrganizationId()).isEqualTo(0L); // 空Dto

        // 住所一致が存在していない
        sheet0801Dto.setDelegateName("代表者　太郎"); // NOPMD
        sheet0801Dto.setOfficeAddress("東京都千代田区霞が関　三角ビル319"); // NOPMD
        PartyUsageDocumentPoliticalPropertyDto dto04 = guessPoliticalOrganizationByPartyUsageLogic
                .practice(sheet0801Dto, sheet0807Dto);
        assertThat(dto04.getPoliticalOrganizationId()).isEqualTo(0L); // 空Dto

        // nullの場合、落ちずに空Dtoを返すだけ
        sheet0801Dto.setPartyName(null);
        sheet0801Dto.setDelegateName(null);
        PartyUsageDocumentPoliticalPropertyDto dto05 = guessPoliticalOrganizationByPartyUsageLogic
                .practice(sheet0801Dto, sheet0807Dto);
        assertThat(dto05.getPoliticalOrganizationId()).isEqualTo(0L); // 空Dto

        // 検索条件である政党名と代表者名が一致していても団体区分が一致していなければはデータは取得できない(Id75は取れない)
        Sheet0801Dto sheet0801Dto01 = new Sheet0801Dto();
        // 政治団体(政党)名
        sheet0801Dto01.setPartyName("いいかげん政党X");
        // 代表者名
        sheet0801Dto01.setDelegateName("代表者　太郎"); // NOPMD
        // 報告年
        sheet0801Dto01.setNendo(2022);
        // 事務所住所
        sheet0801Dto01.setOfficeAddress("大阪府中央区　互角ビル309"); // NOPMD

        PartyUsageDocumentPoliticalPropertyDto dto06 = guessPoliticalOrganizationByPartyUsageLogic
                .practice(sheet0801Dto01, sheet0807Dto);
        assertThat(dto06.getPoliticalOrganizationId()).isEqualTo(0L); // 空Dto

        // 住所や会計責任者で変更があったが、すべて基準日より前の変更なので最新データ
        Sheet0801Dto sheet0801Dto02 = new Sheet0801Dto();
        // 政治団体(政党)名
        sheet0801Dto02.setPartyName("ちゃらんぽらん政党2");
        // 代表者名
        sheet0801Dto02.setDelegateName("代表者　太郎"); // NOPMD
        // 報告年
        sheet0801Dto02.setNendo(2022);
        // 団体区分検索条件だがDtoに存在しないのでLogicで補う 政党の定数固定なので補っても問題ない TODO 定数化した時点で修正する
        // 事務所住所
        sheet0801Dto02.setOfficeAddress("東京都千代田区霞が関　三角ビル309"); // NOPMD
        PartyUsageDocumentPoliticalPropertyDto dto11 = guessPoliticalOrganizationByPartyUsageLogic
                .practice(sheet0801Dto02, sheet0807Dto);
        assertThat(dto11.getPoliticalOrganizationId()).isEqualTo(501L); // 最新データ

        // どういうわけだか報告年のところでくるくる値を変えているので、報告年までの該当データから最新データを抽出
        Sheet0801Dto sheet0801Dto03 = new Sheet0801Dto();
        // 政治団体(政党)名
        sheet0801Dto03.setPartyName("ちゃらんぽらん政党3");
        // 代表者名
        sheet0801Dto03.setDelegateName("代表者　太郎"); // NOPMD
        // 報告年
        sheet0801Dto03.setNendo(2022);
        // 団体区分検索条件だがDtoに存在しないのでLogicで補う 政党の定数固定なので補っても問題ない TODO 定数化した時点で修正する
        // 事務所住所
        sheet0801Dto03.setOfficeAddress("東京都千代田区霞が関　三角ビル309"); // NOPMD
        PartyUsageDocumentPoliticalPropertyDto dto12 = guessPoliticalOrganizationByPartyUsageLogic
                .practice(sheet0801Dto03, sheet0807Dto);
        assertThat(dto12.getPoliticalOrganizationId()).isEqualTo(612L); // 報告年の末日基準で最新データ

        // ※2022年の報告書を出そうとしたとき、2023年3月提出の報告書で、1月に団体名(代表者名)を変更した場合、
        // 新しい団体名で報告書を出す場合、本当は推測を出してはいけない
        // 現状2022年末の基準日に旧団体名であったので、旧団体名で出しているはずだ、という推定の元、実装を行っている。したがって
        // TODO 運用に対応して実装を変更する可能性がある

    }

}
