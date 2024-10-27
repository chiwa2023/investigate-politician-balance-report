package mitei.mitei.investigate.report.balance.politician.service.offering.poli_org;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.political_organization.GuesshPoliticalOrganizationLogic;
import mitei.mitei.investigate.report.balance.politician.logic.storage.SaveStorageFileLogic;

/**
 * 読み取りしたXML情報を解析して、既存情報と比較して登録できるようにする
 */
@Service
public class ReadXmlBalancesheetByFileService {

    /** アップロードファイルストレージ保存Logic */
    @Autowired
    private SaveStorageFileLogic saveStorageFileLogic;

    /** 政治団体推測Logic */
    @Autowired
    private GuesshPoliticalOrganizationLogic guesshPoliticalOrganizationLogic;

    /**
     * XML本体の解析とアップロードファイルの保存を行う
     *
     * @param readXmlByFileCapsuleDto アップロードXML格納Dto
     * @return 解析結果Dto
     * @throws JsonMappingException    JSONマッピングがうまくいかなった例外
     * @throws JsonProcessingException JSONプロセッシングがうまくいかなった例外
     */
    public ReadXmlBalancesheetResultDto practice(final ReadXmlByFileCapsuleDto readXmlByFileCapsuleDto,
            final LocalDateTime datetimeShori) throws JsonMappingException, JsonProcessingException, IOException {

        // 公式XML読み取り
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(Include.ALWAYS);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        AllBookDto allBookDto = xmlMapper.readValue(readXmlByFileCapsuleDto.getFileContent(), new TypeReference<>() {
        });

        ReadXmlBalancesheetResultDto resultDto = new ReadXmlBalancesheetResultDto();

        // アップロード保存
        resultDto.setSaveStorageResultDto(saveStorageFileLogic.practiceText(readXmlByFileCapsuleDto.getFileName(),
                readXmlByFileCapsuleDto.getFileContent(), readXmlByFileCapsuleDto.getCharset(),
                readXmlByFileCapsuleDto.getCheckPrivilegeDto(), datetimeShori));
        resultDto.getSaveStorageResultDto().setCharset(readXmlByFileCapsuleDto.getCharset());

        Sheet070100CoverAndOrganizationDetailsDto cover0701Dto = allBookDto
                .getAllSheet0701CoverAndOrganizationDetailsDto().getSheet070100CoverAndOrganizationDetailsDto();

        // 表紙1データをセット
        resultDto.setCoverDto(cover0701Dto);

        // 登録済政治団体情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = guesshPoliticalOrganizationLogic
                .practice(cover0701Dto, allBookDto.getAllSheet0720OathDto().getSheet072000OathDto());

        resultDto.setDocumentPropertyDto(documentPropertyDto);

        return resultDto;
    }
}
