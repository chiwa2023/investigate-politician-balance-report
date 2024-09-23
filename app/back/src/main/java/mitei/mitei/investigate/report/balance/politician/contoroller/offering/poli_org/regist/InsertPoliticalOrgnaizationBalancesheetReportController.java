package mitei.mitei.investigate.report.balance.politician.contoroller.offering.poli_org.regist;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.RegistPoliticalOrgBalancesheetReportResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.service.offering.poli_org.InsertPoliticalOrgnaizationBalancesheetReportService;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;

/**
 * 政治資金収支報告書の公式XMLを登録する
 */
@Controller
public class InsertPoliticalOrgnaizationBalancesheetReportController {

    /** 政治資金収支報告書の公式XMLを登録Service */
    @Autowired
    private InsertPoliticalOrgnaizationBalancesheetReportService insertPoliticalOrgnaizationBalancesheetReportService;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;
    
    @Transactional
    @GetMapping("/insert-political-orgnaization-balancesheet-report") // SUPPRESS CHECKSTYLE
    public ResponseEntity<String> practice()throws Exception {
        // TODO 後でPostMappingに直す(気づくと思うけど)

        // 簡易登録アクセス
        //String path = "http://localhost:8080/insert-political-orgnaization-balancesheet-report";

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

        Path pathAnswer = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/balancesheet/2022_政治家女子48党_SYUUSI.xml");
        String readText = Files.readString(pathAnswer, Charset.forName("windows-31j"));

        AllBookDto allBookDto = xmlMapper.readValue(readText, new TypeReference<>() {
        });
        
        // 政治団体基礎情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto();
        documentPropertyDto.setHoukokuNen(allBookDto.getAllSheet0701CoverAndOrganizationDetailsDto().getSheet070100CoverAndOrganizationDetailsDto().getHoukokuNen());
        documentPropertyDto
                .setOfferingDate(dateConvertUtil.practiceWarekiToLocalDate(allBookDto.getAllSheet0720OathDto().getSheet072000OathDto().getDateOath()));
        documentPropertyDto.setPoliticalOrganizationId(9898L);
        documentPropertyDto.setPoliticalOrganizationCode(9890);
        documentPropertyDto.setPoliticalOrganizationName("政治家女子４８党"); //　自ソフトウェア呼び出し
        documentPropertyDto.setDantaiName("政治家女子４８党"); //　自ソフトウェア呼び出し
        documentPropertyDto.setDaihyouName("大津綾香"); //　自ソフトウェア呼び出し
        documentPropertyDto.setRelationKbn(224);//　自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonIdDelegate(10_255L);//　自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonCodeDelegate(10_250);//　自ソフトウェア呼び出し
        documentPropertyDto.setRelationPersonNameDelegate("大津綾香");//　自ソフトウェア呼び出し

        
        // 登録作業
        RegistPoliticalOrgBalancesheetReportResultDto resultDto = insertPoliticalOrgnaizationBalancesheetReportService
                .practice(documentPropertyDto, allBookDto, checkPrivilegeDto);

        return ResponseEntity.ok(resultDto.getMessage());
    }

}
