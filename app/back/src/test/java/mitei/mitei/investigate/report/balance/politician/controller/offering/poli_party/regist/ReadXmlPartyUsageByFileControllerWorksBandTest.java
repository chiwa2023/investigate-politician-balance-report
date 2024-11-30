package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_party.regist;


import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.repository.storage.SaveFileStorage2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * ReadXmlPartyUsageByFileControllerWorksBandTest単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = BackApplication.class) // 全体起動
class ReadXmlPartyUsageByFileControllerWorksBandTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ReadXmlPartyUsageByFileControllerWorksBand readXmlPartyUsageByFileControllerWorksBand;

    /** ファイル保存Repository(2024年) */
    @Autowired
    private SaveFileStorage2024Repository saveFileStorage2024Repository;

    @Test
    @Tag("TableTruncate")
    @Sql("truncate_save_file_storage_2024.sql")
    void test() throws Exception {

        assertEquals(0, saveFileStorage2024Repository.count(), "truncate直後で0件");

        ReadXmlByFileCapsuleDto readXmlByFileCapsuleDto = new ReadXmlByFileCapsuleDto();

        String charset = "UTF-8";
        CreateCommonCheckDtoTestOnlyUtil.practice(readXmlByFileCapsuleDto);

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/usage/2022_政治家女子48党_SITO.xml");

        String fileContent = Files.readString(path, Charset.forName(charset));

        readXmlByFileCapsuleDto.setFileName(path.getFileName().toString());
        readXmlByFileCapsuleDto.setFileContent(fileContent);
        readXmlByFileCapsuleDto.setCharset(charset);
        // 破綻要因
        readXmlByFileCapsuleDto.getCheckPrivilegeDto().setLoginUserName(
                "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        try {
            LocalDateTime shori = LocalDateTime.of(2024, 3, 19, 12, 34, 56);
            readXmlPartyUsageByFileControllerWorksBand.wakeBusiness(readXmlByFileCapsuleDto, shori);
        } catch (Exception exception) { // NOPMD
            exception.printStackTrace(); // NOPMD
        }

        assertEquals(0, saveFileStorage2024Repository.count(), "ロールバックして0件");
    }

}
