package mitei.mitei.investigate.report.balance.politician.controller.zengin_org_master;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.zengin_org_master.RegistZenginOrgWkTbleCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * RegistZenginOrgWkTblController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = BackApplication.class) // 全体起動
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class RegistZenginOrgWkTblControllerTest {

    /** mockMvc */
    @Autowired
    private MockMvc mockMvc;

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
    @Transactional
    void test() throws Exception {

        // ファイルの準備
        String childDir = "zengin";
        String fileName1 = "nkyk1.csv";
        String fileName2 = "nkyk2.csv";
        String fileName3 = "nkyk3.csv";
        String fileName4 = "nkyk4.csv";

        this.writeFile(childDir, fileName1);
        this.writeFile(childDir, fileName2);
        this.writeFile(childDir, fileName3);
        this.writeFile(childDir, fileName4);

        SaveStorageResultDto resultDto1 = new SaveStorageResultDto();
        resultDto1.setFullPath(childDir + "/" + fileName1);

        SaveStorageResultDto resultDto2 = new SaveStorageResultDto();
        resultDto2.setFullPath(childDir + "/" + fileName2);

        SaveStorageResultDto resultDto3 = new SaveStorageResultDto();
        resultDto3.setFullPath(childDir + "/" + fileName3);

        SaveStorageResultDto resultDto4 = new SaveStorageResultDto();
        resultDto4.setFullPath(childDir + "/" + fileName4);

        RegistZenginOrgWkTbleCapsuleDto capsuleDto = new RegistZenginOrgWkTbleCapsuleDto();
        CreateCommonCheckDtoTestOnlyUtil.practice(capsuleDto);

        capsuleDto.setStorageWkTbl1Dto(resultDto1);
        capsuleDto.setStorageWkTbl2Dto(resultDto2);
        capsuleDto.setStorageWkTbl3Dto(resultDto3);
        capsuleDto.setStorageWkTbl4Dto(resultDto4);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD
                .perform(post("/zengin-org-master/copy-wktbl") // 以下引数指定
                        .content(objectMapper.writeValueAsString(capsuleDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse().getStatus(), "レスポンスokが戻る");
    }

    // サンプルファイルを読みだして書き出し
    private void writeFile(final String childDir, final String fileName) throws IOException {
        // ファイル準備(読み取り)
        Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/bank/pro", fileName);
        String srcData = Files.readString(pathSrc);

        // 確実にテストを行うため都度書き出し
        Path pathCopy = Paths.get(storageFolder, childDir, fileName);
        Files.createDirectories(pathCopy.getParent());
        Files.writeString(pathCopy, srcData);

    }

}
