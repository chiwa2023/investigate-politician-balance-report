package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party;


import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

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
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.ReadXmlPartyUsageResultDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * ReadXmlPartyUsageByFileService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ReadXmlPartyUsageByFileServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ReadXmlPartyUsageByFileService readXmlPartyUsageByFileService;
    
    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPractice()throws Exception {

        ReadXmlByFileCapsuleDto readXmlByFileCapsuleDto = new ReadXmlByFileCapsuleDto();

        CreateCommonCheckDtoTestOnlyUtil.practice(readXmlByFileCapsuleDto);

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(),
                "sample/usage/2022_政治家女子48党_SITO.xml");
        
        String charset = "Windows-31J";
        String fileContent = Files.readString(path, Charset.forName(charset));

        readXmlByFileCapsuleDto.setFileName(path.getFileName().toString());
        readXmlByFileCapsuleDto.setFileContent(fileContent);
        readXmlByFileCapsuleDto.setCharset(charset);

        // 処理日時は2024年想定
        LocalDateTime shori = LocalDateTime.of(2024, 5, 28, 13, 24, 36);
        
        
        ReadXmlPartyUsageResultDto resultDto =  readXmlPartyUsageByFileService.practice(readXmlByFileCapsuleDto, shori);
        
        Sheet0801Dto sheet0801 = resultDto.getCoverDto(); // NOPMD
        assertThat(sheet0801.getNendo()).isEqualTo(2022);
        assertThat(sheet0801.getDelegateName()).isEqualTo("大津　綾香");

        assertThat(resultDto.getDocumentPropertyDto().getNendo()).isEqualTo(2022); // NOPMD
        assertThat(resultDto.getDocumentPropertyDto().getDantaiName()).isEqualTo("政治家女子４８党"); // NOPMD
    }

}
