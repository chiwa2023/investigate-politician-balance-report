package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party;

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

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0801Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.ReadXmlPartyUsageResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.logic.political_organization.GuesshPoliticalOrganizationByPartyUsageLogic;
import mitei.mitei.investigate.report.balance.politician.logic.storage.SaveStorageFileLogic;

/**
 * 読み取りしたXML情報を解析して、既存情報と比較して登録できるようにする
 */
@Service
public class ReadXmlPartyUsageByFileService {

    /** アップロードファイルストレージ保存Logic */
    @Autowired
    private SaveStorageFileLogic saveStorageFileLogic;

    /** 政治団体推測Logic */
    @Autowired
    private GuesshPoliticalOrganizationByPartyUsageLogic guesshPoliticalOrganizationLogic;

    /**
     * XML本体の解析とアップロードファイルの保存を行う
     *
     * @param readXmlByFileCapsuleDto アップロードXML格納Dto
     * @return 解析結果Dto
     * @throws JsonMappingException    JSONマッピングがうまくいかなった例外
     * @throws JsonProcessingException JSONプロセッシングがうまくいかなった例外
     */
    public ReadXmlPartyUsageResultDto practice(final ReadXmlByFileCapsuleDto readXmlByFileCapsuleDto,
            final LocalDateTime datetimeShori) throws JsonMappingException, JsonProcessingException, IOException {

        // 公式XML読み取り
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(Include.ALWAYS);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        AllShitoBook allBookDto = xmlMapper.readValue(readXmlByFileCapsuleDto.getFileContent(), new TypeReference<>() {
        });

        ReadXmlPartyUsageResultDto resultDto = new ReadXmlPartyUsageResultDto();

        // アップロード保存
        resultDto.setSaveStorageResultDto(saveStorageFileLogic.practiceText(readXmlByFileCapsuleDto.getFileName(),
                readXmlByFileCapsuleDto.getFileContent(), readXmlByFileCapsuleDto.getCharset(),
                readXmlByFileCapsuleDto.getCheckPrivilegeDto(), datetimeShori));
        resultDto.getSaveStorageResultDto().setCharset(readXmlByFileCapsuleDto.getCharset()); // NOPMD

        Sheet0801Dto cover0801Dto = allBookDto.getShito0801Dto().getSheet0801Dto();

        // 表紙1データをセット
        resultDto.setCoverDto(cover0801Dto);

        // 登録済政治団体情報
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto = guesshPoliticalOrganizationLogic
                .practice(cover0801Dto, allBookDto.getShito0807Dto().getSheet0807Dto());

        resultDto.setDocumentPropertyDto(documentPropertyDto);

        return resultDto;
    }
}
