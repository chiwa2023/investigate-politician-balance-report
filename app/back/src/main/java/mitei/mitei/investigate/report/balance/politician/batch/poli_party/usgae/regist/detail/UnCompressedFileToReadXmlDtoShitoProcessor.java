package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.detail;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0801Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.ReadXmlPartyUsageResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.logic.political_organization.GuessPoliticalOrganizationByPartyUsageLogic;

/**
 * 非圧縮XML(書証保存)から政治資金収支報告書XmlReadDtoに変換する
 */
@Component
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
public class UnCompressedFileToReadXmlDtoShitoProcessor
        implements ItemProcessor<TaskPlanPartyUsageDetailEntity, ReadXmlPartyUsageResultDto> {

    /** 政治団体推測Logic */
    @Autowired
    private GuessPoliticalOrganizationByPartyUsageLogic guessPoliticalOrganizationLogic;

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

    /**
     * 変換処理を行う
     */
    @Override
    public ReadXmlPartyUsageResultDto process(final TaskPlanPartyUsageDetailEntity item) throws Exception {

        // 書証Dto
        SaveStorageResultDto saveStorageResultDto = new SaveStorageResultDto();
        BeanUtils.copyProperties(item, saveStorageResultDto);

        ReadXmlPartyUsageResultDto resultDto = new ReadXmlPartyUsageResultDto();
        resultDto.setSaveStorageResultDto(saveStorageResultDto);

        // 権限確認Dto
        CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();
        privilegeDto.setLoginUserId(item.getInsertUserId());
        privilegeDto.setLoginUserCode(item.getInsertUserCode());
        privilegeDto.setLoginUserName(item.getInsertUserName());
        
        resultDto.setCheckPrivilegeDto(privilegeDto);
        
        // 公式XML読み取り
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(Include.ALWAYS);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        Path path = Paths.get(storageFolder, saveStorageResultDto.getFullPath());

        String fileContent = Files.readString(path, Charset.forName(saveStorageResultDto.getCharset()));

        AllShitoBook allShitoBook = xmlMapper.readValue(fileContent, new TypeReference<>() {
        });


        Sheet0801Dto cover0801Dto = allShitoBook.getShito0801Dto().getSheet0801Dto();

        // 表紙1データをセット
        resultDto.setCoverDto(cover0801Dto);

        // 登録済政治団体情報
        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto = guessPoliticalOrganizationLogic
                .practice(cover0801Dto, allShitoBook.getShito0807Dto().getSheet0807Dto());

        resultDto.setDocumentPropertyDto(documentPropertyDto);

        // idとCodeは持ち運ぶ
        resultDto.setTaskPlanPartyUsageDetailId(item.getTaskPlanPartyUsageDetailId());
        resultDto.setTaskPlanPartyUsageDetailCode(item.getTaskPlanPartyUsageDetailCode());
        
        return resultDto;
    }
}
