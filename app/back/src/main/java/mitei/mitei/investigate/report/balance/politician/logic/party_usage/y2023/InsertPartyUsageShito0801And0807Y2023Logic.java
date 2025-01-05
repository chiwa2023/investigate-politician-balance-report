package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2023;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2023.OfferingPartyUsage0801And0807Report2023Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2023.OfferingPartyUsage0801And0807Report2023Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 使途報告書ヘッダ、表紙を保存する
 */
@Component
public class InsertPartyUsageShito0801And0807Y2023Logic {

    /** 政党交付金使途報告書Repository */
    @Autowired
    private OfferingPartyUsage0801And0807Report2023Repository offeringPartyUsage0801And0807Report2023Repository;

    /**
     * 表紙と宣誓書、ヘッダ部分を保存する
     *
     * @param allShitoBook 使途報告書XML
     */
    public Long practice(final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final AllShitoBook allShitoBook, final CheckPrivilegeDto checkPrivilegeDto) {

        // 必ず情報が入っている表紙と宣誓書を同じテーブルで保存する
        OfferingPartyUsage0801And0807Report2023Entity reportEntity = new OfferingPartyUsage0801And0807Report2023Entity();

        // 政治団体基礎情報(団体名称・代表者名)
        BeanUtils.copyProperties(partyUsageDocumentPoliticalPropertyDto, reportEntity);

        // ヘッダ
        BeanUtils.copyProperties(allShitoBook.getBookHeadDto(), reportEntity);

        // 有無テキスト
        reportEntity.setJohoUmuText(allShitoBook.getSitoUmuFlgDto().getUmuStatusText());

        // 様式8その1
        BeanUtils.copyProperties(allShitoBook.getShito0801Dto().getSheet0801Dto(), reportEntity);

        // 様式8その7
        BeanUtils.copyProperties(allShitoBook.getShito0807Dto().getSheet0807Dto(), reportEntity);

        // 未記載基準
        reportEntity
                .setKaikeiKijunKingaku(allShitoBook.getKaikeiShishutuKijunDto().getKaikeiKijunKingakuDto().getAmount());
        // KaikeiKijunKingaku ---- kaikei_kijun_kingaku

        SetTableDataHistoryUtil.practice(checkPrivilegeDto, reportEntity, DataHistoryStatusConstants.INSERT);

        Long code = 1L;
        // 同一識別コードの最大値を取得し、＋１する
        Optional<OfferingPartyUsage0801And0807Report2023Entity> optional = offeringPartyUsage0801And0807Report2023Repository
                .findFirstByOrderByPartyUsage0801And0807ReportCodeDesc();
        if (!optional.isEmpty()) {
            code = code + optional.get().getPartyUsage0801And0807ReportCode();
        }

        reportEntity.setPartyUsage0801And0807ReportCode(code);
        reportEntity.setPartyUsage0801And0807ReportId(0L);

        // repositoryで保存
        offeringPartyUsage0801And0807Report2023Repository.save(reportEntity);

        return reportEntity.getPartyUsage0801And0807ReportCode();
    }

}
