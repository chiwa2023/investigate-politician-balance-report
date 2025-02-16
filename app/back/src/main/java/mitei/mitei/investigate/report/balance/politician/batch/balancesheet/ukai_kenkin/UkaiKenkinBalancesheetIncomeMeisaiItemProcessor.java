package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import java.util.Optional;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.OfferingBalancesheetIncomeEntity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationPropertyRepository;

/**
 * 収支報告書収入データと迂回献金明細を変換するプロセッサ
 */
@Component
public class UkaiKenkinBalancesheetIncomeMeisaiItemProcessor
        implements ItemProcessor<OfferingBalancesheetIncomeEntity, WkTblUkaiKenkinEntity> {

    /** 政治団体属性Repository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    /**
     * 変換を行う
     */
    @Override
    public WkTblUkaiKenkinEntity process(final OfferingBalancesheetIncomeEntity item) throws Exception {

        WkTblUkaiKenkinEntity entity = new WkTblUkaiKenkinEntity();
        BeanUtils.copyProperties(item, entity);
        entity.setRenban(item.getIchirenNo());
        entity.setTablleId(item.getOfferingBalancesheetIncomeId());

        entity.setPoliticalOrgId(item.getPoliticalOrganizationId());
        entity.setPoliticalOrgCode(item.getPoliticalOrganizationCode());
        entity.setPoliticalOrgName(item.getPoliticalOrganizationName());

        entity.setTradingPartnerId(item.getRelationPoliticalOrgIdIncome());
        entity.setTradingPartnerCode(item.getRelationPoliticalOrgCodeIncome());
        entity.setTradingPartnerName(item.getRelationPoliticalOrgNameIncome());
        entity.setTradingPartnerAddress(item.getPartnerJuusho());

        final int saishinKbn = DataHistoryStatusConstants.INSERT.value();

        // 報告書記載政治団体関連者を複写
        Optional<PoliticalOrganizationPropertyEntity> optionalPoliOrg = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationIdAndSaishinKbn(item.getRelationPoliticalOrgIdIncome(), saishinKbn);
        if (!optionalPoliOrg.isEmpty()) {

            PoliticalOrganizationPropertyEntity poliOrg = optionalPoliOrg.get();

            entity.setPoliOrgDelegateId(poliOrg.getDelegateRelationPersonId());
            entity.setPoliOrgDelegateCode(poliOrg.getDelegateRelationPersonCode());
            entity.setPoliOrgDelegateName(poliOrg.getDelegateKoushokuName());

            entity.setPoliOrgAccountManagerId(poliOrg.getAccountManagerRelationPersonId());
            entity.setPoliOrgAccountManagerCode(poliOrg.getAccountManagerRelationPersonCode());
            entity.setPoliOrgAccountManagerName(poliOrg.getAccountManagerKoushokuName());

            entity.setPoliOrgShikinDantaiId(poliOrg.getShikinDaihyouRelationPersonId());
            entity.setPoliOrgShikinDantaiCode(poliOrg.getShikinDaihyouRelationPersonCode());
            entity.setPoliOrgShikinDantaiName(poliOrg.getShikinDaihyouKoushokuName());

            entity.setPoliOrgKokkaiGiin1Id(poliOrg.getGiin1RelationPersonId());
            entity.setPoliOrgKokkaiGiin1Code(poliOrg.getGiin1RelationPersonCode());
            entity.setPoliOrgKokkaiGiin1Name(poliOrg.getGiin1KoushokuName());

            entity.setPoliOrgKokkaiGiin2Id(poliOrg.getGiin2RelationPersonId());
            entity.setPoliOrgKokkaiGiin2Code(poliOrg.getGiin2RelationPersonCode());
            entity.setPoliOrgKokkaiGiin2Name(poliOrg.getGiin2KoushokuName());

            entity.setPoliOrgKokkaiGiin3Id(poliOrg.getGiin3RelationPersonId());
            entity.setPoliOrgKokkaiGiin3Code(poliOrg.getGiin3RelationPersonCode());
            entity.setPoliOrgKokkaiGiin3Name(poliOrg.getGiin3KoushokuName());

        }

        // 取り引き相手政治団体関連者を複写
        Optional<PoliticalOrganizationPropertyEntity> optionalPartner = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationIdAndSaishinKbn(item.getRelationPoliticalOrgIdIncome(), saishinKbn);
        if (!optionalPartner.isEmpty()) {

            PoliticalOrganizationPropertyEntity partner = optionalPartner.get();

            entity.setTradingPartnerDelegateId(partner.getDelegateRelationPersonId());
            entity.setTradingPartnerDelegateCode(partner.getDelegateRelationPersonCode());
            entity.setTradingPartnerDelegateName(partner.getDelegateKoushokuName());

            entity.setTradingOrgAccountManagerId(partner.getAccountManagerRelationPersonId());
            entity.setTradingOrgAccountManagerCode(partner.getAccountManagerRelationPersonCode());
            entity.setTradingOrgAccountManagerName(partner.getAccountManagerKoushokuName());

            entity.setTradingOrgShikinDantaiId(partner.getShikinDaihyouRelationPersonId());
            entity.setTradingOrgShikinDantaiCode(partner.getShikinDaihyouRelationPersonCode());
            entity.setTradingOrgShikinDantaiName(partner.getShikinDaihyouKoushokuName());

            entity.setTradingOrgKokkaiGiin1Id(partner.getGiin1RelationPersonId());
            entity.setTradingOrgKokkaiGiin1Code(partner.getGiin1RelationPersonCode());
            entity.setTradingOrgKokkaiGiin1Name(partner.getGiin1KoushokuName());

            entity.setTradingOrgKokkaiGiin2Id(partner.getGiin2RelationPersonId());
            entity.setTradingOrgKokkaiGiin2Code(partner.getGiin2RelationPersonCode());
            entity.setTradingOrgKokkaiGiin2Name(partner.getGiin2KoushokuName());

            entity.setTradingOrgKokkaiGiin3Id(partner.getGiin3RelationPersonId());
            entity.setTradingOrgKokkaiGiin3Code(partner.getGiin3RelationPersonCode());
            entity.setTradingOrgKokkaiGiin3Name(partner.getGiin3KoushokuName());

        }

        return entity;
    }
}
