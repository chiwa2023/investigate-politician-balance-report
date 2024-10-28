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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0801Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * InsertPoliticalOrganizationByPartyUsageLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class InsertPoliticalOrganizationByPartyUsageLogicTest {

    /** テスト対象 */
    @Autowired
    private InsertPoliticalOrganizationByPartyUsageLogic insertPoliticalOrganizationByPartyUsageLogic;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPractice() throws Exception {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        Sheet0801Dto sheet0801Dto00 = new Sheet0801Dto();
        sheet0801Dto00.setPartyName("ちゃらんぽらん政党");
        sheet0801Dto00.setPartyNameKana("ちゃらんぽらんせいとう");
        sheet0801Dto00.setOfficeAddress("東京都千代田区霞が関　三角ビル903");
        sheet0801Dto00.setDelegateName("代表者　花子");
        sheet0801Dto00.setAccountManagerName("会計責任者　次郎");
        sheet0801Dto00.setWorker1Name("事務担当者　三郎");

        // 正常データが入っているときは想定通りの値が入る
        PoliticalOrganizationEntity entity00 = insertPoliticalOrganizationByPartyUsageLogic.practice(sheet0801Dto00,
                privilegeDto);

        assertThat(entity00.getPoliticalOrganizationName()).isEqualTo(sheet0801Dto00.getPartyName());
        assertThat(entity00.getPoliticalOrganizationNameKana()).isEqualTo(sheet0801Dto00.getPartyNameKana());
        assertThat(entity00.getDantaiKbn()).isEqualTo("1"); // TODO 政治団体政党固定値 定数を設定次第修正する

        // 住所
        assertThat(entity00.getAddressAll()).isEqualTo(sheet0801Dto00.getOfficeAddress());

        // 代表者
        assertThat(entity00.getDaihyoshaLastName()).isEqualTo("代表者");
        assertThat(entity00.getDaihyoshaFirstName()).isEqualTo("花子");
        assertThat(entity00.getDaihyoshaName()).isEqualTo(sheet0801Dto00.getDelegateName());

        // 会計責任者
        assertThat(entity00.getKaikeiSekininshaLastName()).isEqualTo("会計責任者");
        assertThat(entity00.getKaikeiSekininshaFirstName()).isEqualTo("次郎");
        assertThat(entity00.getKaikeiSekininshaName()).isEqualTo(sheet0801Dto00.getAccountManagerName());

        // 会計責任者の職務代行者
        assertThat(entity00.getKaikeiDaikoLastName()).isEqualTo("事務担当者");
        assertThat(entity00.getKaikeiDaikoFirstName()).isEqualTo("三郎");
        assertThat(entity00.getKaikeiDaikoName()).isEqualTo(sheet0801Dto00.getWorker1Name());

        assertThat(entity00.getSearchText()).isEqualTo("ちゃらんぽらん政党代表者花子東京都千代田区霞が関三角ビル903");

        assertThat(entity00.getSaishinKbn()).isEqualTo(DataHistoryStatusConstants.INSERT.value());
        assertThat(entity00.getInsertUserId()).isEqualTo(privilegeDto.getLoginUserId());
        assertThat(entity00.getInsertUserCode()).isEqualTo(privilegeDto.getLoginUserCode());
        assertThat(entity00.getInsertUserName()).isEqualTo(privilegeDto.getLoginUserName());

        Sheet0801Dto sheet0801Dto01 = new Sheet0801Dto();
        sheet0801Dto01.setPartyName("ちゃらんぽらん政党");
        sheet0801Dto01.setPartyNameKana("ちゃらんぽらんせいとう");
        sheet0801Dto01.setOfficeAddress("東京都千代田区霞が関　三角ビル903");
        sheet0801Dto01.setDelegateName("代表者花子");
        sheet0801Dto01.setAccountManagerName("会計責任者次郎");
        sheet0801Dto01.setWorker1Name("事務担当者三郎");

        // 名前が姓＋全角スペース＋名の公式で定められた形式をしていないときは姓・名専用のカラムに値が入らない
        PoliticalOrganizationEntity entity01 = insertPoliticalOrganizationByPartyUsageLogic.practice(sheet0801Dto01,
                privilegeDto);

        final String empty = "";
        // 代表者
        assertThat(entity01.getDaihyoshaLastName()).isEqualTo(empty);
        assertThat(entity01.getDaihyoshaFirstName()).isEqualTo(empty);

        // 会計責任者
        assertThat(entity01.getKaikeiSekininshaLastName()).isEqualTo(empty);
        assertThat(entity01.getKaikeiSekininshaFirstName()).isEqualTo(empty);

        // 会計責任者の職務代行者
        assertThat(entity01.getKaikeiDaikoLastName()).isEqualTo(empty);
        assertThat(entity01.getKaikeiDaikoFirstName()).isEqualTo(empty);

        // 姓名を入れるカラムはDtoそのままの値が入ることは変わらない
        assertThat(entity01.getDaihyoshaName()).isEqualTo(sheet0801Dto01.getDelegateName());
        assertThat(entity01.getKaikeiSekininshaName()).isEqualTo(sheet0801Dto01.getAccountManagerName());
        assertThat(entity01.getKaikeiDaikoName()).isEqualTo(sheet0801Dto01.getWorker1Name());

    }

}
