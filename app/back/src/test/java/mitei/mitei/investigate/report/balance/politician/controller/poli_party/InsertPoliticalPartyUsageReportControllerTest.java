package mitei.mitei.investigate.report.balance.politician.controller.poli_party; // NOPMD

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.transaction.Transactional;
import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.RegistPoliticalPartyUsageReportCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.util.CreateCommonCheckDtoTestOnlyUtil;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * InsertPoliticalPartyUsageReportController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = BackApplication.class) // 全体起動
class InsertPoliticalPartyUsageReportControllerTest {
    // CHECKSTYLE:OFF

    /** mockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPractice() throws Exception {

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

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertThat(mockMvc // NOPMD LawOfDemeter
                .perform(post("/insert-political-party-usage-report")
                        .content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

    }

}
