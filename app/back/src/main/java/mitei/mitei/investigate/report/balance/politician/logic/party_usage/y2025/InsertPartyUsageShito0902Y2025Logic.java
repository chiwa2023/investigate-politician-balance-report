package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0902Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0902Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0902Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0902Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 使途報告書様式9その2を保存する
 */
@Component
public class InsertPartyUsageShito0902Y2025Logic {

    /** 様式その9その2テーブルRepository */
    @Autowired
    private OfferingPartyUsage0902Report2025Repository offeringPartyUsage0902Report2025Repository;

    /**
     * 様式9その2を保存する
     *
     * @param shito0902Dto 様式9その2
     */
    public int practice(final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final Shito0902Dto shito0902Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0902Report2025Entity> list = new ArrayList<>();

        // 初期同一識別コードを取得
        Long code = 0L;
        Optional<OfferingPartyUsage0902Report2025Entity> optional = offeringPartyUsage0902Report2025Repository
                .findFirstByOrderByPartyUsage0902ReportCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getPartyUsage0902ReportCode();
        }

        for (Sheet0902Dto dto : shito0902Dto.getList()) {
            code++;
            list.add(this.createEntity(code, documentCode, partyUsageDocumentPoliticalPropertyDto, dto,
                    checkPrivilegeDto));
        }

        // repositoryで保存
        return offeringPartyUsage0902Report2025Repository.saveAllAndFlush(list).size();
    }

    private OfferingPartyUsage0902Report2025Entity createEntity(final Long code, final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto, final Sheet0902Dto dto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingPartyUsage0902Report2025Entity entity = new OfferingPartyUsage0902Report2025Entity();

        BeanUtils.copyProperties(dto, entity);
        BeanUtils.copyProperties(partyUsageDocumentPoliticalPropertyDto, entity);
        entity.setDocumentCode(documentCode);
        entity.setPartyUsage0902ReportCode(code);

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, entity, DataHistoryStatusConstants.INSERT);

        return entity;
    }

}
