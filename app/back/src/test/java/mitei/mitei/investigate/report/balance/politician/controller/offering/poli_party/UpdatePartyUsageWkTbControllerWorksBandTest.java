package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_party;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.RandomStringUtils;
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

import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.UpdatePartyUsageWkTblCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliPartyUsageReportRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * UpdatePartyUsageWkTbControllerWorksBand単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class UpdatePartyUsageWkTbControllerWorksBandTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UpdatePartyUsageWkTbControllerWorksBand updatePartyUsageWkTbControllerWorksBand;
    

    /** 政党交付金使途報告書一括処理ワークテーブルRepository */
    @Autowired
    private WkTblPoliPartyUsageReportRepository wkTblPoliPartyUsageReportRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("../../../service/offering/poli_party/wk_tbl_poli_party_usage_report.sql")
    void test()throws Exception {
        
        
        Long count = wkTblPoliPartyUsageReportRepository.count();
        
        UpdatePartyUsageWkTblCapsuleDto capsuleDto = new UpdatePartyUsageWkTblCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        Long callId = 2239L;
        WkTblPoliPartyUsageReportEntity entitySrc = wkTblPoliPartyUsageReportRepository.findById(callId).get();

        Long poliOrgId = 326L;
        Integer poliOrgCode = 320;
        String poliOrgName = "ちゃらんぽらん政治団体";

        entitySrc.setPoliticalOrganizationId(poliOrgId);
        entitySrc.setPoliticalOrganizationCode(poliOrgCode);
        entitySrc.setPoliticalOrganizationName(poliOrgName);
        capsuleDto.setWkTblPoliPartyUsageReportEntity(entitySrc);
        // 阻害要因
        capsuleDto.getCheckPrivilegeDto().setLoginUserName(RandomStringUtils.secure().nextAlphabetic(400));
        
        try {
            updatePartyUsageWkTbControllerWorksBand.wakeBusiness(capsuleDto);
        }catch (Exception exception) { // NOPMD
            exception.printStackTrace(); // NOPMD
        }
        
        WkTblPoliPartyUsageReportEntity entitySrc2 = wkTblPoliPartyUsageReportRepository.findById(callId).get();
        
        assertEquals(1, entitySrc2.getSaishinKbn(),"ロールアックされて変更なし"); // NOPMD
        assertEquals(0L, entitySrc2.getPoliticalOrganizationId(),"ロールアックされて変更なし");
        assertEquals(0, entitySrc2.getPoliticalOrganizationCode(),"ロールアックされて変更なし");
        assertEquals(count, wkTblPoliPartyUsageReportRepository.count(),"ロールアックされて変更なし");
        
    }

}
