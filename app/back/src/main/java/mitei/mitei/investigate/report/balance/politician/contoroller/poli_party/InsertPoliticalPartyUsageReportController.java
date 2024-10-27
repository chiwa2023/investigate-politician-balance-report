package mitei.mitei.investigate.report.balance.politician.contoroller.poli_party;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.RegistPoliticalPartyUsageReportResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.service.offering.poli_party.InsertPartyUsageReportService;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * 政党交付金使途報告書の公式XMLを登録する
 */
@Controller
public class InsertPoliticalPartyUsageReportController {

    /** 政治資金収支報告書の公式XMLを登録Service */
    @Autowired
    private InsertPartyUsageReportService insertPartyUsageReportService;

    
    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;
    
    @Transactional
    @PostMapping("/insert-political-party-usage-report") // SUPPRESS CHECKSTYLE
    public ResponseEntity<String> practice()throws Exception {
        // TODO 後でPostMappingに直す(気づくと思うけど)

        // 簡易登録アクセス
        //String path = "http://localhost:8080/insert-political-party-usage-report";

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

        Path pathAnswer = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/usage/2022_政治家女子48党_SITO.xml");
        String readText = Files.readString(pathAnswer, Charset.forName("windows-31j"));


        AllShitoBook allBookDto = xmlMapper.readValue(readText, new TypeReference<>() {
        });

        // 政治団体基礎情報
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto();
        documentPropertyDto.setNendo(allBookDto.getShito0801Dto().getSheet0801Dto().getNendo());
        documentPropertyDto.setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate(allBookDto.getShito0807Dto().getSheet0807Dto().getAccrualDate()));
        documentPropertyDto.setPoliticalOrganizationId(9898L);
        documentPropertyDto.setPoliticalOrganizationCode(9890);
        documentPropertyDto.setPoliticalOrganizationName("政治家女子４８党");
        documentPropertyDto.setDantaiName("政治家女子４８党");
        documentPropertyDto.setDaihyouName("大津綾香");
        documentPropertyDto.setRelationKbn(223);
        documentPropertyDto.setRelationKbn(224);//　自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonIdDelegate(10_255L);//　自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonCodeDelegate(10_250);//　自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonNameDelegate("大津綾香");//　自ソフトウェア呼び出し
        
        // 登録作業
        RegistPoliticalPartyUsageReportResultDto resultDto = insertPartyUsageReportService
                .practice(documentPropertyDto,checkPrivilegeDto, allBookDto, false);

        return ResponseEntity.ok(resultDto.getMessage());

    }
}
