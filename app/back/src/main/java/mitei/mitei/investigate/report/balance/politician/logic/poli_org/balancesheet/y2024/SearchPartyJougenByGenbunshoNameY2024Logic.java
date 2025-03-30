package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2024;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.blancesheet_report.IncomeYoushikiKbnConstants;
import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiKbn;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenTradingInfoDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.KobetsuKiseiMeisaiEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.SouryouKiseiGenBunshoEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2024.OfferingBalancesheetIncome2024Repository;

/**
 * パーティ購入額を団体文書名、住所ごとに集計する
 */
@Component
public class SearchPartyJougenByGenbunshoNameY2024Logic {

    /** 収支報告書収入テーブル2024 */
    @Autowired
    private OfferingBalancesheetIncome2024Repository offeringBalancesheetIncome2024Repository;

    /**
     * 処理をおこなう
     *
     * @param capsuleDto 検索条件格納Dto
     * @return 寄付上限検索結果Dto
     */
    public SearchKifuJougenMeisaiBalancesheetResultDto practice(final KifuJougenConditionCapsuleDto capsuleDto) {

        List<Integer> listYoushikiKbn = new ArrayList<>();
        listYoushikiKbn.add(IncomeYoushikiKbnConstants.YOUSHIKI_KBN_11);

        List<Integer> listEdaKbn = new ArrayList<>();
        listEdaKbn.add(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_KOJIN);
        listEdaKbn.add(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_HOUJIN);
        listEdaKbn.add(IncomeYoushikiKbnConstants.YOUSHIKI_SHUNYU_EDA_KBN_SEIJIDANTAI);


        Integer poliOrgCode = capsuleDto.getPoliOrgCode();

        // 原文書名称の政治団体名を取得
        SearchKifuJougenMeisaiBalancesheetResultDto resultDto = new SearchKifuJougenMeisaiBalancesheetResultDto();

        // ページングによる検索でない場合は、全件件数を取得する
        if (capsuleDto.getAllCount().equals(0)) {
            Integer allCount = offeringBalancesheetIncome2024Repository.findCountTradingPartnerPartyByName(poliOrgCode);
            resultDto.setCountAll(allCount);
        }

        Pageable pageable = Pageable.ofSize(capsuleDto.getOffset()).withPage(capsuleDto.getPageNum());
        resultDto.setPageNumber(pageable.getPageNumber());

        List<SouryouKiseiGenBunshoEntity> listSum = offeringBalancesheetIncome2024Repository
                .findTradingPartnerPartyByName(poliOrgCode, pageable);

        List<KifuJougenTradingInfoDto> listData = new ArrayList<>();

        for (SouryouKiseiGenBunshoEntity entity : listSum) {

            // 各関連者に紐づく入金明細を取得
            List<OfferingBalancesheetIncome2024Entity> listMeisaiOffering = offeringBalancesheetIncome2024Repository
                    .findByPoliticalOrganizationCodeAndPartnerNameAndPartnerJuushoAndYoushikiKbnAndSaishinKbn(
                            poliOrgCode, entity.getPartnerName(), entity.getPartnerJusho(), YoushikiKbn.PARTY,
                            DataHistoryStatusConstants.INSERT.value());

            listData.add(this.createDto(entity, listMeisaiOffering));
        }

        resultDto.setListTradingGroup(listData);

        return resultDto;
    }

    /*
     * 収入Entityから上限規制用Entityに変換する
     *
     * @param entity 収入Enity2024
     * @param tradingDto 寄付上限
     * @return 明細Enitty
     */
    private KobetsuKiseiMeisaiEntity convertEntiy(final OfferingBalancesheetIncome2024Entity entity,
            final KifuJougenTradingInfoDto tradingDto) {

        KobetsuKiseiMeisaiEntity meisaiEntity = new KobetsuKiseiMeisaiEntity();
        BeanUtils.copyProperties(entity, meisaiEntity);

        meisaiEntity.setRelationId(entity.getTradingPartnerId());
        meisaiEntity.setRelationCode(entity.getTradingPartnerCode());
        meisaiEntity.setPartnerName(tradingDto.getPartnerName());
        meisaiEntity.setPartnerJuusho(tradingDto.getPartnerAddress());

        return meisaiEntity;
    }

    /*
     * 寄付上限Dtoを作成する
     *
     * @param entity GROUP BYされた関連者と合計の格納Dto
     * @param listMeisaiOffering 関連者の明細リスト
     * @return 寄付上限Dto
     */
    private KifuJougenTradingInfoDto createDto(final SouryouKiseiGenBunshoEntity entity,
            final List<OfferingBalancesheetIncome2024Entity> listMeisaiOffering) {
        KifuJougenTradingInfoDto infoDto = new KifuJougenTradingInfoDto();

        // 合計を設定
        infoDto.setSumKifu(entity.getSum());

        // 代表として1件目を表示
        OfferingBalancesheetIncome2024Entity entityIncomeFirst = listMeisaiOffering.get(0);
        infoDto.setPartnerName(entityIncomeFirst.getPartnerName());
        infoDto.setPartnerAddress(entityIncomeFirst.getPartnerJuusho());
        // infoDto.setRelationId(entityIncomeFirst.getTradingPartnerId());
        // infoDto.setRelationCode(entityIncomeFirst.getTradingPartnerCode());
        // infoDto.setRelationCode(entityIncomeFirst.getTradingPartnerCode());

        List<KobetsuKiseiMeisaiEntity> listMeisai = new ArrayList<>();
        for (OfferingBalancesheetIncome2024Entity entityIncome : listMeisaiOffering) {
            listMeisai.add(this.convertEntiy(entityIncome, infoDto));
        }
        infoDto.setListTradingMeisai(listMeisai);

        return infoDto;
    }

}
