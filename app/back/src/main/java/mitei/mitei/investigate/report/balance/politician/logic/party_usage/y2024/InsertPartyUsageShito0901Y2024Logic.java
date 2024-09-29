package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0901Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0901Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2024.OfferingPartyUsage0901Report2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0901Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 様式9(その1)2024年データを登録する
 */
@Component
public class InsertPartyUsageShito0901Y2024Logic {

    /** 様式9(その1)Repository */
    @Autowired
    private OfferingPartyUsage0901Report2024Repository offeringPartyUsage0901Report2024Repository;

    /**
     * 登録作業を行う
     *
     * @param documentCode        文書同一識別コード
     * @param documentPropertyDto 文書属性Dto
     * @param shito0901Dto        様式9(その1)Dto
     * @param checkPrivilegeDto   権限確認Dto
     * @return 想定登録結果
     */
    public int practice(final Long documentCode, final PartyUsageDocumentPoliticalPropertyDto documentPropertyDto,
            final Shito0901Dto shito0901Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0901Report2024Entity> list = new ArrayList<>();

        for (RowShito0901Dto rowDto : shito0901Dto.getSheet0901Dto().getList()) {
            list.add(this.createEntity(documentCode, documentPropertyDto, rowDto, checkPrivilegeDto));
        }

        // 同一識別コードを設定
        Long code = 0L;
        Optional<OfferingPartyUsage0901Report2024Entity> optional = offeringPartyUsage0901Report2024Repository
                .findFirstByOrderByPartyUsage0901ReportCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getPartyUsage0901ReportCode();
        }

        for (OfferingPartyUsage0901Report2024Entity entity : list) {
            code++;
            entity.setPartyUsage0901ReportCode(code);
        }

        // repositoryで保存
        return offeringPartyUsage0901Report2024Repository.saveAll(list).size();
    }

    private OfferingPartyUsage0901Report2024Entity createEntity(final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final RowShito0901Dto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingPartyUsage0901Report2024Entity reportEntity = new OfferingPartyUsage0901Report2024Entity();

        BeanUtils.copyProperties(partyUsageDocumentPoliticalPropertyDto, reportEntity);
        BeanUtils.copyProperties(rowDto, reportEntity);
        reportEntity.setDocumentCode(documentCode);

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, reportEntity, DataHistoryStatusConstants.INSERT);

        return reportEntity;
    }

}
