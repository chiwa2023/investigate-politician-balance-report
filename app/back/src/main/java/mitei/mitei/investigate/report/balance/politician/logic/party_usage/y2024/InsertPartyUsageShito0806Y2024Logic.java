package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2024;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080601Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080602Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Kbn080603Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShito0806Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0806Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0806Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2024.OfferingPartyUsage0806Report2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2024.OfferingPartyUsage0806Report2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 使途報告書様式8その6を保存する
 */
@Component
public class InsertPartyUsageShito0806Y2024Logic {

    /** 様式8その6区分1 */
    public static final int KUBUN01 = 1;

    /** 様式8その6区分2 */
    public static final int KUBUN02 = 2;

    /** 様式8その6区分3 */
    public static final int KUBUN03 = 3;

    /** 様式8その6Repository */
    @Autowired
    private OfferingPartyUsage0806Report2024Repository offeringPartyUsage0806Report2024Repository;

    /**
     * 登録作業行う
     *
     * @param shito0806Dto 様式8その6
     */
    public int practice(final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final Shito0806Dto shito0806Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0806Report2024Entity> list = new ArrayList<>();

        // 基金=シートの数だけループする
        for (Sheet0806Dto sheet0806Dto : shito0806Dto.getList()) {
            list.addAll(this.createEntity(documentCode, partyUsageDocumentPoliticalPropertyDto, sheet0806Dto,
                    checkPrivilegeDto));
        }

        // 同一識別コードを取得する
        Long code = 0L;
        Optional<OfferingPartyUsage0806Report2024Entity> optional = offeringPartyUsage0806Report2024Repository
                .findFirstByOrderByPartyUsage0806ReportCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getPartyUsage0806ReportCode();
        }

        // 最後に同一識別コードをふる
        for (OfferingPartyUsage0806Report2024Entity entity : list) {
            code++;
            entity.setPartyUsage0806ReportCode(code);
        }

        // repositoryで保存
        return offeringPartyUsage0806Report2024Repository.saveAll(list).size();
    }

    private List<OfferingPartyUsage0806Report2024Entity> createEntity(final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final Sheet0806Dto sheet0806Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingPartyUsage0806Report2024Entity reportEntity = new OfferingPartyUsage0806Report2024Entity();

        BeanUtils.copyProperties(sheet0806Dto, reportEntity);
        BeanUtils.copyProperties(partyUsageDocumentPoliticalPropertyDto, reportEntity);
        reportEntity.setDocumentCode(documentCode);

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, reportEntity, DataHistoryStatusConstants.INSERT);

        // 集計データ
        List<OfferingPartyUsage0806Report2024Entity> list = new ArrayList<>();
        list.add(reportEntity);

        // 各区分詳細データ
        list.addAll(this.createEntityByKbn01(documentCode, partyUsageDocumentPoliticalPropertyDto,
                sheet0806Dto.getKbn080601Dto(), checkPrivilegeDto));
        list.addAll(this.createEntityByKbn02(documentCode, partyUsageDocumentPoliticalPropertyDto,
                sheet0806Dto.getKbn080602Dto(), checkPrivilegeDto));
        list.addAll(this.createEntityByKbn03(documentCode, partyUsageDocumentPoliticalPropertyDto,
                sheet0806Dto.getKbn080603Dto(), checkPrivilegeDto));

        return list;
    }

    private List<OfferingPartyUsage0806Report2024Entity> createEntityByKbn01(final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final Kbn080601Dto kbn080601Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0806Report2024Entity> list = new ArrayList<>();

        Long subTotal = kbn080601Dto.getSubTotal();
        String subTotalBikou = kbn080601Dto.getSubTotalBikou();
        for (RowShito0806Dto rowShito0806Dto : kbn080601Dto.getList()) {
            list.add(this.createEntityByRow0(KUBUN01, documentCode, partyUsageDocumentPoliticalPropertyDto, subTotal,
                    subTotalBikou, rowShito0806Dto, checkPrivilegeDto));
        }

        return list;
    }

    private List<OfferingPartyUsage0806Report2024Entity> createEntityByKbn02(final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final Kbn080602Dto kbn080602Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0806Report2024Entity> list = new ArrayList<>();

        Long subTotal = kbn080602Dto.getSubTotal();
        String subTotalBikou = kbn080602Dto.getSubTotalBikou();
        for (RowShito0806Dto rowShito0806Dto : kbn080602Dto.getList()) {
            list.add(this.createEntityByRow0(KUBUN02, documentCode, partyUsageDocumentPoliticalPropertyDto, subTotal,
                    subTotalBikou, rowShito0806Dto, checkPrivilegeDto));
        }

        return list;
    }

    private List<OfferingPartyUsage0806Report2024Entity> createEntityByKbn03(final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final Kbn080603Dto kbn080603Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        List<OfferingPartyUsage0806Report2024Entity> list = new ArrayList<>();

        Long subTotal = kbn080603Dto.getSubTotal();
        String subTotalBikou = kbn080603Dto.getSubTotalBikou();
        for (RowShito0806Dto rowShito0806Dto : kbn080603Dto.getList()) {
            list.add(this.createEntityByRow0(KUBUN03, documentCode, partyUsageDocumentPoliticalPropertyDto, subTotal,
                    subTotalBikou, rowShito0806Dto, checkPrivilegeDto));
        }

        return list;
    }

    private OfferingPartyUsage0806Report2024Entity createEntityByRow0(final int kbn, final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto, final Long subTotal,
            final String subTotalBikou, final RowShito0806Dto rowShito0806Dto,
            final CheckPrivilegeDto checkPrivilegeDto) {

        OfferingPartyUsage0806Report2024Entity reportEntity = new OfferingPartyUsage0806Report2024Entity();
        BeanUtils.copyProperties(rowShito0806Dto, reportEntity);
        BeanUtils.copyProperties(partyUsageDocumentPoliticalPropertyDto, reportEntity);
        reportEntity.setDocumentCode(documentCode);

        reportEntity.setSubTotal(subTotal);
        reportEntity.setSubTotalBikou(subTotalBikou);
        reportEntity.setKubunNum(kbn);

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, reportEntity, DataHistoryStatusConstants.INSERT);

        return reportEntity;
    }

}
