package mitei.mitei.investigate.report.balance.politician.controller.poli_party;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.usage.report.ReadXmlPartyUsageResultDto;
import mitei.mitei.investigate.report.balance.politician.service.offering.poli_party.ReadXmlPartyUsageByFileService;

/**
 * ビジネスロジックをまとめるWorksBandController(Transaction用)
 */
@Component
public class ReadXmlPartyUsageByFileControllerWorksBand {

    /** アップロードXML保存解析Service */
    @Autowired
    private ReadXmlPartyUsageByFileService readXmlPartyUsageByFileService;

    /**
     * 処理を行う
     *
     * @param readXmlByFileCapsuleDto XML読み取りDto
     * @return 読み取り結果Dto
     * @throws IOException             ファイル読み取り例外
     * @throws JsonMappingException    JSONマッピング例外
     * @throws JsonProcessingException JSON変換例外
     */
    @Transactional
    public ReadXmlPartyUsageResultDto wakeBusiness(final ReadXmlByFileCapsuleDto readXmlByFileCapsuleDto ,final LocalDateTime shori)
            throws IOException, JsonMappingException, JsonProcessingException {

        return readXmlPartyUsageByFileService.practice(readXmlByFileCapsuleDto, shori);
    }
}
