package mitei.mitei.investigate.report.balance.politician.controller.offering.document.regist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;

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

import mitei.mitei.investigate.report.balance.politician.constants.DocumentRecognizeKeyConstants;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.repository.storage.SaveFileStorage2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;

/**
 * ReadXmlDocumentByCompressedZipControllerWorksBand単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ReadXmlDocumentByCompressedZipControllerWorksBandTest {
    // CHECKSTYLE:OFF

    /** */
    @Autowired
    private ReadXmlDocumentByCompressedZipControllerWorksBand readXmlDocumentByCompressedZipControllerWorksBand;

    /** ファイル保存Repository(2024年) */
    @Autowired
    private SaveFileStorage2024Repository saveFileStorage2024Repository;

    @Test
    @Tag("TableTruncate")
    @Sql({ "truncate_task_plan_balancesheet_detail.sql", "task_info.sql", "user_web_access2.sql" })
    void test() throws Exception {

        final long count = saveFileStorage2024Repository.count();

        ReadXmlByFileCapsuleDto capsuleDto = new ReadXmlByFileCapsuleDto();

        // ファイル情報を設定
        String fileName = "all.zip";
        Path pathFile = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/zip", fileName);
        String fileContent = Base64.getEncoder().encodeToString(Files.readAllBytes(pathFile));
        capsuleDto.setFileName(fileName);
        capsuleDto.setFileContent(fileContent);

        // 共通チェックとドキュメント種類
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);
        capsuleDto.setDocumentKey(DocumentRecognizeKeyConstants.SOFT_BALANCESHEET);
        // 破綻要因…必要なユーザ情報がない

        LocalDateTime shori = LocalDateTime.of(2024, 6, 19, 0, 0, 0);

        try {
            readXmlDocumentByCompressedZipControllerWorksBand.wakeBusiness(capsuleDto, shori);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        assertEquals(count, saveFileStorage2024Repository.count(), "ロールバックされているので件数に変化なし");
    }

}
