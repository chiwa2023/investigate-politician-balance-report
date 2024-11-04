package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist;

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

import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.Sheet070100CoverAndOrganizationDetailsDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.logic.political_organization.GuesshPoliticalOrganizationLogic;

/**
 * 非圧縮XML(書証保存)から政治資金収支報告書XmlReadDtoに変換する
 */
@Component
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
public class UnCompressedFileToReadXmlDtoProcessor
        implements ItemProcessor<TaskPlanBalancesheetDetailEntity, ReadXmlBalancesheetResultDto> {

    /** 政治団体推測Logic */
    @Autowired
    private GuesshPoliticalOrganizationLogic guesshPoliticalOrganizationLogic;

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
    public ReadXmlBalancesheetResultDto process(final TaskPlanBalancesheetDetailEntity item) throws Exception {

        SaveStorageResultDto saveStorageResultDto = new SaveStorageResultDto();
        BeanUtils.copyProperties(item, saveStorageResultDto);

        ReadXmlBalancesheetResultDto resultDto = new ReadXmlBalancesheetResultDto();
        resultDto.setSaveStorageResultDto(saveStorageResultDto);

        // 公式XML読み取り
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(Include.ALWAYS);
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);

        Path path = Paths.get(storageFolder, saveStorageResultDto.getFullPath());

        String fileContent = Files.readString(path, Charset.forName(saveStorageResultDto.getCharset()));

        AllBookDto allBookDto = xmlMapper.readValue(fileContent, new TypeReference<>() {
        });

        Sheet070100CoverAndOrganizationDetailsDto cover0701Dto = allBookDto
                .getAllSheet0701CoverAndOrganizationDetailsDto().getSheet070100CoverAndOrganizationDetailsDto();

        // 表紙1データをセット
        resultDto.setCoverDto(cover0701Dto);

        // 登録済政治団体情報
        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto = guesshPoliticalOrganizationLogic
                .practice(cover0701Dto, allBookDto.getAllSheet0720OathDto().getSheet072000OathDto());

        resultDto.setDocumentPropertyDto(documentPropertyDto);

        // idとCodeは持ち運ぶ
        resultDto.setTaskPlanBalancesheetDetailId(item.getTaskPlanBalancesheetDetailId());
        resultDto.setTaskPlanBalancesheetDetailCode(item.getTaskPlanBalancesheetDetailCode());
        
        return resultDto;
    }
}
