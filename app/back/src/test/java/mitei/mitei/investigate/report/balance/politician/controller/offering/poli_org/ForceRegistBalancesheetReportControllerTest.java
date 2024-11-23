package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_org;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplateFrameworkCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * ForceRegistBalancesheetReportController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class ForceRegistBalancesheetReportControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 政治資金収支報告書登録準備ワークテーブルレポジトリ */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

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
    @Sql("../../../batch/poli_org/balancesheet/regist/wk_tbl_poli_org_balancesheet_report_allwrite.sql")
    void test() throws Exception {
        
        // 全件取得してChunkと要求されているファイルを作成してセット
        List<WkTblPoliOrgBalancesheetReportEntity> listTask = wkTblPoliOrgBalancesheetReportRepository.findAll();
        for (WkTblPoliOrgBalancesheetReportEntity entity : listTask) {
            Path path = Paths.get(storageFolder, entity.getFullPath());
            Files.write(path, this.readBinaryFile(entity.getFileName()));
        }
        
        TemplateFrameworkCapsuleDto capsuleDto = new TemplateFrameworkCapsuleDto();
        // 共通チェックとドキュメント種類
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "http://localhost:9080/force-balancesheet/regist";

        // サーバステータスがOK(200)
        assertThat(mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    // バイナリで指定されたファイルの中身を読み込む
    private byte[] readBinaryFile(final String fileName) throws IOException {
        final String horie = "2022_ホリエモン新党_SYUUSI.xml";
        if (horie.equals(fileName)) {
            Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(),
                    "/sample/balancesheet/2022_ホリエモン新党_SYUUSI.xml");
            return Files.readAllBytes(path);
        } else {
            Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(),
                    "/sample/balancesheet/2022_政治家女子48党_SYUUSI.xml");
            return Files.readAllBytes(path);
        }
    }

}
