package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_party.regist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.RegistPoliticalPartyUsageReportCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0801And0807Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * InsertPoliticalPartyUsageReportControllerWorksBand単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = BackApplication.class) // 全体起動
class InsertPoliticalPartyUsageReportControllerWorksBandTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private InsertPoliticalPartyUsageReportControllerWorksBand insertPoliticalPartyUsageReportControllerWorksBand;
    

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;
    
    /** 政党交付金使途報告書Repository */
    @Autowired
    private OfferingPartyUsage0801And0807Report2022Repository offeringPartyUsage0801And0807Report2022Repository;

    @Test
    @Tag("TableTruncate")
    @Sql("truncate_usgae_2024.sql")
   void test()throws Exception {
        
        assertEquals(0, offeringPartyUsage0801And0807Report2022Repository.count(),"truncateしたので0件");
        
        RegistPoliticalPartyUsageReportCapsuleDto capsuleDto = new RegistPoliticalPartyUsageReportCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        String charset = "UTF-8";

        // 公式XML読み取り
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(Include.ALWAYS);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        String fullPath = GetCurrentResourcePath.getBackTestResourcePath() + "/sample/usage/2022_政治家女子48党_SITO.xml";
        String readText = Files.readString(Paths.get(fullPath), Charset.forName(charset));

        AllShitoBook allBookDto = xmlMapper.readValue(readText, new TypeReference<>() {
        });

        // 政治団体基礎情報
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentPropertyDto.setNendo(allBookDto.getShito0801Dto().getSheet0801Dto().getNendo());
        documentPropertyDto.setOfferingDate(dateConvertUtil
                .practiceWarekiToLocalDate(allBookDto.getShito0807Dto().getSheet0807Dto().getAccrualDate()));
        documentPropertyDto.setPoliticalOrganizationId(9898L);
        documentPropertyDto.setPoliticalOrganizationCode(9890);
        documentPropertyDto.setPoliticalOrganizationName("政治家女子４８党");
        documentPropertyDto.setDantaiName("政治家女子４８党");
        documentPropertyDto.setDaihyouName("大津綾香");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationKbn(224);// 自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonIdDelegate(10_255L);// 自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonCodeDelegate(10_250);// 自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonNameDelegate("大津綾香");// 自ソフトウェア呼び出し

        SaveStorageResultDto saveStorageResultDto = new SaveStorageResultDto();
        saveStorageResultDto.setFullPath(fullPath); // 実際はストレージフォルダ内の子パスだが、絶対パスを渡してもよい
        saveStorageResultDto.setCharset(charset);
        capsuleDto.setDocumentPropertyDto(documentPropertyDto);
        capsuleDto.setSaveStorageResultDto(saveStorageResultDto);
        // 破綻要因
        capsuleDto.getCheckPrivilegeDto().setLoginUserName(
                "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        try {

            insertPoliticalPartyUsageReportControllerWorksBand.wakeBusiness(capsuleDto);
            
        }catch (Exception exception) { // NOPMD
            exception.printStackTrace(); // NOPMD
        }
        
        assertEquals(0, offeringPartyUsage0801And0807Report2022Repository.count(),"ロールバックしたので0件");
        
    }

}
