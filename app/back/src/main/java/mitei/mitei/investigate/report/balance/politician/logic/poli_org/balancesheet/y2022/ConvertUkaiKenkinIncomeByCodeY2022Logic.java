package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetIncome2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationPropertyRepository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetIncome2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 取得した収支報告書収入データを迂回献金ワークテーブルリストに変換する
 */
@Component
public class ConvertUkaiKenkinIncomeByCodeY2022Logic {

    /** 政治団体属性Repository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    /** 収支報告書収入テーブルRepository(2022) */
    @Autowired // 2022
    private OfferingBalancesheetIncome2022Repository offeringBalancesheetIncome2022Repository;

    /**
     * 収入テーブルから迂回献金ワークテーブルEntityに変換する
     *
     * @param listPoliOrgCode 政治団体同一識別コードリスト
     * @param propertyEntity  政治団体属性Entity
     * @param listYoushikiKbn 検索様式区分リスト
     * @param privilegeDto    権限確認Dto
     * @return 迂回献金ワークテーブルリスト
     */
    public List<WkTblUkaiKenkinEntity> practice(final List<Integer> listPoliOrgCode,
            final PoliticalOrganizationPropertyEntity propertyEntity, final List<Integer> listYoushikiKbn,
            final CheckPrivilegeDto privilegeDto) { // NOPMD

        final int insertValue = DataHistoryStatusConstants.INSERT.value();

        // 政治団体同一識別コードから該当取引をすべて取得する
        List<OfferingBalancesheetIncome2022Entity> listByPoliOrg = offeringBalancesheetIncome2022Repository
                .findByPoliticalOrganizationCodeInAndYoushikiKbnInAndSaishinKbn(listPoliOrgCode, listYoushikiKbn,
                        insertValue);

        List<WkTblUkaiKenkinEntity> list = new ArrayList<>();
        for (OfferingBalancesheetIncome2022Entity entityIncome : listByPoliOrg) {

            list.add(
                    this.convertEntity(entityIncome,
                            politicalOrganizationPropertyRepository.findByPoliticalOrganizationIdAndSaishinKbn(
                                    entityIncome.getPoliticalOrganizationId(), insertValue).get(),
                            privilegeDto));
        }

        return list;
    }

    // 収入Entityから迂回献金ワークテーブルに変換する
    private WkTblUkaiKenkinEntity convertEntity(final OfferingBalancesheetIncome2022Entity entityIncome,
            final PoliticalOrganizationPropertyEntity propertyEntity, final CheckPrivilegeDto privilegeDto) {

        WkTblUkaiKenkinEntity entity = new WkTblUkaiKenkinEntity();
        BeanUtils.copyProperties(entityIncome, entity);
        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);

        // 複写元Id
        entity.setTablleId(entityIncome.getOfferingBalancesheetIncomeId());
        // 行番号
        entity.setRenban(entityIncome.getIchirenNo());

        // 収支報告書記載団体代表者
        entity.setPoliOrgDelegateId(propertyEntity.getDelegateRelationPersonId());
        entity.setPoliOrgDelegateCode(propertyEntity.getDelegateRelationPersonCode());
        entity.setPoliOrgDelegateName(propertyEntity.getDelegateKoushokuName());

        // 収支報告書記載団体会計責任者
        entity.setPoliOrgAccountManagerId(propertyEntity.getAccountManagerRelationPersonId());
        entity.setPoliOrgAccountManagerCode(propertyEntity.getAccountManagerRelationPersonCode());
        entity.setPoliOrgAccountManagerName(propertyEntity.getAccountManagerKoushokuName());

        // 収支報告書政治団体
        entity.setPoliticalOrgId(propertyEntity.getPoliticalOrganizationId());
        entity.setPoliticalOrgCode(propertyEntity.getPoliticalOrganizationCode());
        entity.setPoliticalOrgName(propertyEntity.getPoliticalOrganizationName());

        // 資金管理団体責任者と国会議員1から3(未入力でも当たり前なので初期値のところに初期値を複写しているがif判定するよりマシ)
        entity.setPoliOrgShikinDantaiId(propertyEntity.getShikinDaihyouRelationPersonId());
        entity.setPoliOrgShikinDantaiCode(propertyEntity.getShikinDaihyouRelationPersonCode());
        entity.setPoliOrgShikinDantaiName(propertyEntity.getShikinDaihyouKoushokuName());
        entity.setPoliOrgKokkaiGiin1Id(propertyEntity.getGiin1RelationPersonId());
        entity.setPoliOrgKokkaiGiin1Code(propertyEntity.getGiin1RelationPersonCode());
        entity.setPoliOrgKokkaiGiin1Name(propertyEntity.getGiin1KoushokuName());
        entity.setPoliOrgKokkaiGiin2Id(propertyEntity.getGiin2RelationPersonId());
        entity.setPoliOrgKokkaiGiin2Code(propertyEntity.getGiin2RelationPersonCode());
        entity.setPoliOrgKokkaiGiin2Name(propertyEntity.getGiin2KoushokuName());
        entity.setPoliOrgKokkaiGiin3Id(propertyEntity.getGiin3RelationPersonId());
        entity.setPoliOrgKokkaiGiin3Code(propertyEntity.getGiin3RelationPersonCode());
        entity.setPoliOrgKokkaiGiin3Name(propertyEntity.getGiin3KoushokuName());

        // TODO 関連者データを追加する

        // 住所だけは原文書の値を使用
        entity.setTradingPartnerAddress(entityIncome.getPartnerJuusho());

        // 関連者を設定する
        this.setKanrenSha(entity, entityIncome);

        return entity;
    }

    private void setKanrenSha(final WkTblUkaiKenkinEntity entity,
            final OfferingBalancesheetIncome2022Entity entityIncome) {

        switch (entityIncome.getYoushikiEdaKbn()) {
            case YoushikiEdaKbn.KOJIN:
                // 個人データを複写
                entity.setTradingPartnerId(entityIncome.getRelationPersonIdIncome());
                entity.setTradingPartnerCode(entityIncome.getRelationPersonCodeIncome());
                entity.setTradingPartnerName(entityIncome.getRelationPersonNameIncome());
                break;

            case YoushikiEdaKbn.KIGYOU_DANTAI:
                // 企業・団体データを複写
                entity.setTradingPartnerId(entityIncome.getRelationCorpIdIncome());
                entity.setTradingPartnerCode(entityIncome.getRelationCorpCodeIncome());
                entity.setTradingPartnerName(entityIncome.getRelationCorpNameInccome());
                entity.setTradingPartnerDelegateId(entityIncome.getRelationPersonIdIncome());
                entity.setTradingPartnerDelegateCode(entityIncome.getRelationPersonCodeIncome());
                entity.setTradingPartnerDelegateName(entityIncome.getRelationPersonNameIncome());
                break;

            // 寄付の政治団体分と交付金も抽出対象にした場合
            case YoushikiEdaKbn.SEIJI_DANTAI, YoushikiEdaKbn.NO_SET:

                // 政治団体データを複写
                entity.setTradingPartnerId(entityIncome.getRelationPoliticalOrgIdIncome());
                entity.setTradingPartnerCode(entityIncome.getRelationPoliticalOrgCodeIncome());
                entity.setTradingPartnerName(entityIncome.getRelationPoliticalOrgNameIncome());

                // 政治団体と属性を取得
                Optional<PoliticalOrganizationPropertyEntity> optionalProperty = politicalOrganizationPropertyRepository
                        .findByPoliticalOrganizationIdAndSaishinKbn(entity.getTradingPartnerId(),
                                DataHistoryStatusConstants.INSERT.value());

                PoliticalOrganizationPropertyEntity tradingPropertyEntity = new PoliticalOrganizationPropertyEntity();

                if (!optionalProperty.isEmpty()) {
                    tradingPropertyEntity = optionalProperty.get();
                }

                // 取引相手団体代表者
                entity.setTradingPartnerDelegateId(tradingPropertyEntity.getDelegateRelationPersonId());
                entity.setTradingPartnerDelegateCode(tradingPropertyEntity.getDelegateRelationPersonCode());
                entity.setTradingPartnerDelegateName(tradingPropertyEntity.getDelegateKoushokuName());

                // 取引相手団体会計責任者
                entity.setTradingOrgAccountManagerId(tradingPropertyEntity.getAccountManagerRelationPersonId());
                entity.setTradingOrgAccountManagerCode(tradingPropertyEntity.getAccountManagerRelationPersonCode());
                entity.setTradingOrgAccountManagerName(tradingPropertyEntity.getAccountManagerKoushokuName());

                // 資金管理団体責任者と国会議員1から3(未入力でも当たり前なので初期値のところに初期値を複写しているがif判定するよりマシ)
                entity.setTradingOrgShikinDantaiId(tradingPropertyEntity.getShikinDaihyouRelationPersonId());
                entity.setTradingOrgShikinDantaiCode(tradingPropertyEntity.getShikinDaihyouRelationPersonCode());
                entity.setTradingOrgShikinDantaiName(tradingPropertyEntity.getShikinDaihyouKoushokuName());
                entity.setTradingOrgKokkaiGiin1Id(tradingPropertyEntity.getGiin1RelationPersonId());
                entity.setTradingOrgKokkaiGiin1Code(tradingPropertyEntity.getGiin1RelationPersonCode());
                entity.setTradingOrgKokkaiGiin1Name(tradingPropertyEntity.getGiin1KoushokuName());
                entity.setTradingOrgKokkaiGiin2Id(tradingPropertyEntity.getGiin2RelationPersonId());
                entity.setTradingOrgKokkaiGiin2Code(tradingPropertyEntity.getGiin2RelationPersonCode());
                entity.setTradingOrgKokkaiGiin2Name(tradingPropertyEntity.getGiin2KoushokuName());
                entity.setTradingOrgKokkaiGiin3Id(tradingPropertyEntity.getGiin3RelationPersonId());
                entity.setTradingOrgKokkaiGiin3Code(tradingPropertyEntity.getGiin3RelationPersonCode());
                entity.setTradingOrgKokkaiGiin3Name(tradingPropertyEntity.getGiin3KoushokuName());

                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + entityIncome.getYoushikiEdaKbn());
        }
    }

}
