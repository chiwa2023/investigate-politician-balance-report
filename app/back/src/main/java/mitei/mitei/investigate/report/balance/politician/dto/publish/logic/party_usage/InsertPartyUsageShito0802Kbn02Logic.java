package mitei.mitei.investigate.report.balance.politician.dto.publish.logic.party_usage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080202Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0802Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.OfferingPartyUsage0802Kbn02Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.OfferingPartyUsage0802Kbn02Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 使途報告書様式8その2区分2を保存する
 */
@Component
public class InsertPartyUsageShito0802Kbn02Logic {
    
    /** 様式8その2区分2Repository */
    @Autowired
    private OfferingPartyUsage0802Kbn02Report2025Repository offeringPartyUsage0802Kbn02Report2025Repository;
    
    /**
     * 登録作業を行う
     *
     * @param kbn080202Dto 様式8その2区分2
     */
    public int practice (final Long documentCode,final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,final Kbn080202Dto kbn080202Dto, final CheckPrivilegeDto checkPrivilegeDto) {
        
        Long goukei = kbn080202Dto.getTotalAmount();
        List<OfferingPartyUsage0802Kbn02Report2025Entity> list = new ArrayList<>();
        
        
        //同一識別コードの設定
        Long code = 0L;
        Optional<OfferingPartyUsage0802Kbn02Report2025Entity> optional = offeringPartyUsage0802Kbn02Report2025Repository.findFirstByOrderByPartyUsage0802Kbn02ReportCodeDesc();
        if(!optional.isEmpty()) {
            code = optional.get().getPartyUsage0802Kbn02ReportCode();
        }
        
        for(RowShito0802Dto rowShito0802Dto : kbn080202Dto.getList()) {
            code++;
            list.add(this.createEntity(code,documentCode,partyUsageDocumentPoliticalPropertyDto,goukei,rowShito0802Dto,checkPrivilegeDto));
        }
        
        //repositoryで保存
        offeringPartyUsage0802Kbn02Report2025Repository.saveAll(list);
        
        return list.size();
    }
    
    private OfferingPartyUsage0802Kbn02Report2025Entity createEntity(final Long code ,final Long documentCode,final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,final Long goukei,final RowShito0802Dto rowShito0802Dto, final CheckPrivilegeDto checkPrivilegeDto) {
        OfferingPartyUsage0802Kbn02Report2025Entity reportEntity= new OfferingPartyUsage0802Kbn02Report2025Entity();
        
        BeanUtils.copyProperties(rowShito0802Dto, reportEntity);
        BeanUtils.copyProperties(partyUsageDocumentPoliticalPropertyDto, reportEntity);
        reportEntity.setDocumentCode(documentCode);
        reportEntity.setPartyUsage0802Kbn02ReportCode(code);

        //手で設定
        reportEntity.setTotalAmount(goukei);
        
        SetTableDataHistoryUtil.practice(checkPrivilegeDto, reportEntity, DataHistoryStatusConstants.INSERT);

        return reportEntity;
    }
}
