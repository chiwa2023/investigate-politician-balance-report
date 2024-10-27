package mitei.mitei.investigate.report.balance.politician.controller.offering.poli_org.regist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.BackApplication;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.RegistPoliticalOrgBalancesheetReportCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.GetObjectMapperWithTimeModuleUtil;

/**
 * InsertPoliticalOrgnaizationBalancesheetReportController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ContextConfiguration(classes = BackApplication.class) // 全体起動
class InsertPoliticalOrgnaizationBalancesheetReportControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    @Test
    @Transactional
    void testPractice() throws Exception {

        // 権限Dto
        CheckPrivilegeDto checkPrivilegeDto = new CheckPrivilegeDto();
        final long ID = 123321L; // NOPMD
        final int CODE = 987;
        final String NAME = "ユーザ";
        checkPrivilegeDto.setLoginUserId(ID);
        checkPrivilegeDto.setLoginUserCode(CODE);
        checkPrivilegeDto.setLoginUserName(NAME);

        // 公式XML読み取り
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(Include.ALWAYS);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        Path pathAnswer = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(),
                "sample/balancesheet/2022_政治家女子48党_SYUUSI.xml");
        String readText = Files.readString(pathAnswer, Charset.forName("windows-31j"));

        AllBookDto allBookDto = xmlMapper.readValue(readText, new TypeReference<AllBookDto>() {
        });

        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(allBookDto.getAllSheet0701CoverAndOrganizationDetailsDto()
                .getSheet070100CoverAndOrganizationDetailsDto().getHoukokuNen());
        documentPropertyDto.setOfferingDate(dateConvertUtil
                .practiceWarekiToLocalDate(allBookDto.getAllSheet0720OathDto().getSheet072000OathDto().getDateOath()));
        documentPropertyDto.setPoliticalOrganizationId(9898L);
        documentPropertyDto.setPoliticalOrganizationCode(9890);
        documentPropertyDto.setPoliticalOrganizationName("政治家女子４８党"); // 自ソフトウェア呼び出し
        documentPropertyDto.setDantaiName("政治家女子４８党"); // 自ソフトウェア呼び出し
        documentPropertyDto.setDaihyouName("大津綾香"); // 自ソフトウェア呼び出し
        documentPropertyDto.setRelationKbn(224);// 自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonIdDelegate(10_255L);// 自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonCodeDelegate(10_250);// 自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonNameDelegate("大津綾香");// 自ソフトウェア呼び出し

        SaveStorageResultDto saveStorageResultDto = new SaveStorageResultDto();
        String charset = "Windows-31J";
        String fullPath = GetCurrentResourcePath.getBackTestResourcePath()
                + "/sample/balancesheet/2022_政治家女子48党_SYUUSI.xml";
        saveStorageResultDto.setFullPath(fullPath); // 実際はストレージフォルダ内の子パスだが、絶対パスを渡してもよい
        saveStorageResultDto.setCharset(charset);

        RegistPoliticalOrgBalancesheetReportCapsuleDto capsuleDto = new RegistPoliticalOrgBalancesheetReportCapsuleDto();
        capsuleDto.setCheckPrivilegeDto(checkPrivilegeDto);
        capsuleDto.setDocumentPropertyDto(documentPropertyDto);
        capsuleDto.setSaveStorageResultDto(saveStorageResultDto);
        
        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        assertThat(mockMvc // NOPMD LawOfDemeter
                .perform(post("/insert-political-orgnaization-balancesheet-report")
                        .content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

}
