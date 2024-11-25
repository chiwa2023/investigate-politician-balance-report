package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;

/**
 * UnCompressedFileToReadXmlDtoProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class UnCompressedFileToReadXmlDtoProcessorTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private UnCompressedFileToReadXmlDtoProcessor unCompressedFileToReadXmlDtoProcessor;

    /** 保存親フォルダ */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // テンプレートファイルの呼び出し
        String fileName = "2022_ホリエモン新党_SYUUSI.xml";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/balancesheet", fileName);
        String charset = "cp932";
        String fileContent = Files.readString(path, Charset.forName(charset));

        String childDir = "test_user/expand";

        // ファイルの複写
        Path pathCopy = Paths.get(storageFolder, childDir, fileName);
        Files.createDirectories(pathCopy.getParent());
        Files.writeString(pathCopy, fileContent, Charset.forName(charset));

        TaskPlanBalancesheetDetailEntity detailEntity = new TaskPlanBalancesheetDetailEntity();
        detailEntity.setTaskPlanBalancesheetDetailId(824L);
        detailEntity.setTaskPlanBalancesheetDetailCode(820L);
        detailEntity.setCharset(charset);
        detailEntity.setChildDir(childDir);
        detailEntity.setFileName(fileName);
        detailEntity.setFullPath(childDir + "/" + fileName);
        detailEntity.setShoshouId(108L);
        detailEntity.setShoshouCode(100L);
        detailEntity.setShoshouKbn(0);

        ReadXmlBalancesheetResultDto resultDto = unCompressedFileToReadXmlDtoProcessor.process(detailEntity);

        Sheet070100CoverAndOrganizationDetailsDto sheet0701 = resultDto.getCoverDto(); // NOPMD
        assertThat(sheet0701.getHoukokuNen()).isEqualTo(2022);
        assertThat(sheet0701.getDaihyoushaNameFirst()).isEqualTo("孝志");
        assertThat(sheet0701.getDaihyoushaNameLast()).isEqualTo("立花");

        assertThat(resultDto.getDocumentPropertyDto().getHoukokuNen()).isEqualTo(2022); // NOPMD
        assertThat(resultDto.getDocumentPropertyDto().getDantaiName()).isEqualTo("ホリエモン新党"); // NOPMD

        assertThat(resultDto.getSaveStorageResultDto().getCharset()).isEqualTo(charset);
        assertThat(resultDto.getSaveStorageResultDto().getChildDir()).isEqualTo(childDir);
        assertThat(resultDto.getSaveStorageResultDto().getFileName()).isEqualTo(fileName);

        assertThat(resultDto.getTaskPlanBalancesheetDetailId()).isEqualTo(824L);
        assertThat(resultDto.getTaskPlanBalancesheetDetailCode()).isEqualTo(820L);

    }

}
