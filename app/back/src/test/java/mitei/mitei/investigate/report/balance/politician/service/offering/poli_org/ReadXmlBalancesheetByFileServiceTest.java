package mitei.mitei.investigate.report.balance.politician.service.offering.poli_org;

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

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * ReadXmlBalancesheetByFileService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ReadXmlBalancesheetByFileServiceTest {

    /** テスト対象 */
    @Autowired
    private ReadXmlBalancesheetByFileService readXmlBalancesheetByFileService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPractice() throws Exception {
        // CHECKSTYLE:OFF

        ReadXmlByFileCapsuleDto readXmlByFileCapsuleDto = new ReadXmlByFileCapsuleDto();

        CreateCommonCheckDtoTestOnlyUtil.practice(readXmlByFileCapsuleDto);

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(),
                "sample/balancesheet/2022_ホリエモン新党_SYUUSI.xml");
        String charset = "cp932";
        String fileContent = Files.readString(path, Charset.forName(charset));

        readXmlByFileCapsuleDto.setFileName(path.getFileName().toString());
        readXmlByFileCapsuleDto.setFileContent(fileContent);
        readXmlByFileCapsuleDto.setCharset(charset);

        // 処理日時は2024年想定
        LocalDateTime shori = LocalDateTime.of(2024, 5, 28, 13, 24, 36);

        ReadXmlBalancesheetResultDto resultDto = readXmlBalancesheetByFileService.practice(readXmlByFileCapsuleDto,
                shori);

        Sheet070100CoverAndOrganizationDetailsDto sheet0701 = resultDto.getCoverDto(); // NOPMD
        assertThat(sheet0701.getHoukokuNen()).isEqualTo(2022);
        assertThat(sheet0701.getDaihyoushaNameFirst()).isEqualTo("孝志");
        assertThat(sheet0701.getDaihyoushaNameLast()).isEqualTo("立花");

        assertThat(resultDto.getDocumentPropertyDto().getHoukokuNen()).isEqualTo(2022); // NOPMD
        assertThat(resultDto.getDocumentPropertyDto().getDantaiName()).isEqualTo("ホリエモン新党"); // NOPMD

    }

}
