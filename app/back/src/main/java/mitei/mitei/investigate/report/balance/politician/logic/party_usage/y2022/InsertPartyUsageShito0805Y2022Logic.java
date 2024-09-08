package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0805Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0805Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2022.OfferingPartyUsage0805Report2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2022.OfferingPartyUsage0805Report2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 使途報告書様式8その5を保存する
 */
@Component
public class InsertPartyUsageShito0805Y2022Logic {

    /** 様式8その5Repository */
    @Autowired
    private OfferingPartyUsage0805Report2022Repository offeringPartyUsage0805Report2022Repository;

    /**
     * 登録作業を行う
     *
     * @param shito0805Dto 様式8その5
     */
    public int practice(final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final Shito0805Dto shito0805Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        Long sumAmount = shito0805Dto.getSheet0805Dto().getSumAmount();

        List<OfferingPartyUsage0805Report2022Entity> list = new ArrayList<>();

        // 同一識別コード最大値を取得
        Long code = 0L;
        Optional<OfferingPartyUsage0805Report2022Entity> optional = offeringPartyUsage0805Report2022Repository
                .findFirstByOrderByPartyUsage0805ReportCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getPartyUsage0805ReportCode();
        }

        for (RowShito0805Dto rowShito0805Dto : shito0805Dto.getSheet0805Dto().getList()) {
            code++;
            list.add(this.createEntity(code, documentCode, partyUsageDocumentPoliticalPropertyDto, sumAmount,
                    rowShito0805Dto, checkPrivilegeDto));
        }

        // repositoryで保存
        return offeringPartyUsage0805Report2022Repository.saveAll(list).size();
    }

    private OfferingPartyUsage0805Report2022Entity createEntity(final Long code, final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto, final Long sumAmount,
            final RowShito0805Dto rowShito0805Dto, final CheckPrivilegeDto checkPrivilegeDto) {
        OfferingPartyUsage0805Report2022Entity reportEntity = new OfferingPartyUsage0805Report2022Entity();

        BeanUtils.copyProperties(rowShito0805Dto, reportEntity);

        BeanUtils.copyProperties(partyUsageDocumentPoliticalPropertyDto, reportEntity);
        reportEntity.setDocumentCode(documentCode);
        reportEntity.setPartyUsage0805ReportCode(code);

        // シート集計は手で設定
        reportEntity.setSumAmount(sumAmount);

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, reportEntity, DataHistoryStatusConstants.INSERT);

        return reportEntity;
    }

}
