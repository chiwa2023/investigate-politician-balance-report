package mitei.mitei.investigate.report.balance.politician.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;

/**
 * ConverCheckPrivilegeDtoUtil単体テスト
 */
class ConverCheckPrivilegeDtoUtilTest {
    // CHECKSTYLE:OFF

    @Test
    @Tag("TableTruncate")
    void testPractice() {

        ConvertCheckPrivilegeDtoUtil converCheckPrivilegeDtoUtil = new ConvertCheckPrivilegeDtoUtil();

        WkTblPoliOrgBalancesheetReportEntity entity = new WkTblPoliOrgBalancesheetReportEntity();
        entity.setInsertUserId(723L);
        entity.setInsertUserCode(720);
        entity.setInsertUserName("会計責任者　正夫");

        CheckPrivilegeDto privilegeDto = converCheckPrivilegeDtoUtil.practice(entity);

        assertEquals(723L, privilegeDto.getLoginUserId(), "Idが同一");
        assertEquals(720, privilegeDto.getLoginUserCode(), "Codeが同一");
        assertEquals("会計責任者　正夫", privilegeDto.getLoginUserName(), "Nameが同一");
    }

}
