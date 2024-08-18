package mitei.mitei.investigate.report.balance.politician.logic.party_usage;

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
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0901Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingPartyUsage0901Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 使途報告書様式9その1を保存する
 */
@Component
public class InsertPartyUsageShito0901Logic {

    /** 様式9(その1)Repository */
    @Autowired
    private OfferingPartyUsage0901Report2025Repository offeringPartyUsage0901Report2025Repository;

    /**
     * 登録作業を行う
     *
     * @param shito0901Dto 様式9(その1)
     */
    public int practice(final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final Shito0901Dto shito0901Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0901Report2025Entity> list = new ArrayList<>();

        // 同一識別コードを設定
        Long code = 0L;
        Optional<OfferingPartyUsage0901Report2025Entity> optional = offeringPartyUsage0901Report2025Repository
                .findFirstByOrderByPartyUsage0901ReportCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getPartyUsage0901ReportCode();
        }

        for (RowShito0901Dto rowDto : shito0901Dto.getSheet0901Dto().getList()) {
            code++;
            list.add(this.createEntity(code, documentCode, partyUsageDocumentPoliticalPropertyDto, rowDto,
                    checkPrivilegeDto));
        }

        // repositoryで保存
        offeringPartyUsage0901Report2025Repository.saveAll(list);

        return list.size();
    }

    private OfferingPartyUsage0901Report2025Entity createEntity(final Long code, final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final RowShito0901Dto rowDto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingPartyUsage0901Report2025Entity reportEntity = new OfferingPartyUsage0901Report2025Entity();

        BeanUtils.copyProperties(partyUsageDocumentPoliticalPropertyDto, reportEntity);
        BeanUtils.copyProperties(rowDto, reportEntity);
        reportEntity.setDocumentCode(documentCode);
        reportEntity.setPartyUsage0901ReportCode(code);

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, reportEntity, DataHistoryStatusConstants.INSERT);

        return reportEntity;
    }
}
