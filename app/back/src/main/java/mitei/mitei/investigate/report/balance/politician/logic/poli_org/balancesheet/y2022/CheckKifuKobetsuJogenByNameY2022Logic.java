package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenTradingInfoDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.KobetsuKiseiMeisaiEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetIncome2022Entity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen.GetDantaiKbnListLogic;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetIncome2022Repository;

/**
 * 政治資金収支報告書寄付個別制限データを取得する
 */
@Component
public class CheckKifuKobetsuJogenByNameY2022Logic {

    /** 政治資金収支報告書収入Repository(2022) */
    @Autowired
    private OfferingBalancesheetIncome2022Repository offeringBalancesheetIncome2022Repository;

    /** 団体区分リスト取得Logic */
    @Autowired
    private GetDantaiKbnListLogic getDantaiKbnListLogic;

    /**
     * 処理を行う
     *
     * @param poliOrgCode    政治団体同一識別コード
     * @param youshikiEdaKbn 様式枝区分
     * @param pageable       ページング要素
     * @return 処理結果
     */
    public SearchKifuJougenMeisaiBalancesheetResultDto practice(final Integer poliOrgCode, final Integer seachKifuKbn ,final Integer youshikiEdaKbn,
            final Pageable pageable) {

        Long limit = this.getLimit(youshikiEdaKbn);

        // 政治団体区分を取得する
        List<String> listOther = getDantaiKbnListLogic.practice(seachKifuKbn);

        // 原文書名称の政治団体名を取得
        List<String> listOrgName = offeringBalancesheetIncome2022Repository.findPartnerNameByPoliOrg(poliOrgCode,
                youshikiEdaKbn);

        // 政治団体の明細を取得する
        List<OfferingBalancesheetIncome2022Entity> listOrgMeisai = offeringBalancesheetIncome2022Repository
                .findByPartnerNameInAndDantaiKbnInAndYoushikiEdaKbnAndSaishinKbnOrderByPartnerNameAscPartnerJuushoAsc(
                        listOrgName, listOther, youshikiEdaKbn, DataHistoryStatusConstants.INSERT.value(), pageable);

        SearchKifuJougenMeisaiBalancesheetResultDto resultOrgDto = new SearchKifuJougenMeisaiBalancesheetResultDto();

        if (!listOrgMeisai.isEmpty()) {

            // 最初1件を作成
            KifuJougenTradingInfoDto tradingDto = this.createTradingDto(listOrgMeisai.get(0));
            resultOrgDto.getListTradingGroup().add(tradingDto);

            String partnerNamePre = tradingDto.getPartnerName();
            String partnerJushoPre = tradingDto.getPartnerAddress();
            String partnerName;
            String partnerJusho;
            Long sum = 0L;
            for (OfferingBalancesheetIncome2022Entity entity : listOrgMeisai) {
                partnerName = entity.getPartnerName();
                partnerJusho = entity.getPartnerJuusho();
                if (partnerJushoPre.equals(partnerJusho) && partnerNamePre.equals(partnerName)) {
                    // 取引相手名と住所が異ならない同じ取引相手であれば既存のDtoに明細を追加して合計を増やす
                    tradingDto.getListTradingMeisai().add(this.convertEntiy(entity));
                    sum += entity.getKingaku();
                } else {
                    // 取引相手名と住所が異なり、新たな取引先になった場合は新たにDtoを作成する
                    // が、まず今までのデータを保存する
                    tradingDto.setSumKifu(sum);
                    tradingDto.setIsLimitOver(sum > limit);

                    // 合計値初期化と新たなDto作成
                    resultOrgDto.getListTradingGroup().add(this.createTradingDto(entity));
                    tradingDto = resultOrgDto.getListTradingGroup().getLast();
                    sum = 0L;
                    partnerNamePre = entity.getPartnerName();
                    partnerJushoPre = entity.getPartnerJuusho();

                    // 最初明細を投入
                    tradingDto.getListTradingMeisai().add(this.convertEntiy(entity));
                    sum += entity.getKingaku();
                }
            }

            // 最後に登録できないデータをセットする
            tradingDto.setSumKifu(sum);
            tradingDto.setIsLimitOver(sum > limit);
        }

        // 検索条件を単純にコピー
        resultOrgDto.setCountAll(listOrgMeisai.size());
        resultOrgDto.setPageNumber(pageable.getPageNumber());

        return resultOrgDto;
    }

    // 取引明細Dtoを作成する
    private KifuJougenTradingInfoDto createTradingDto(final OfferingBalancesheetIncome2022Entity entity) {

        KifuJougenTradingInfoDto tradingDto = new KifuJougenTradingInfoDto();

        tradingDto.setYoushikiEdaKbn(entity.getYoushikiEdaKbn());
        tradingDto.setPartnerName(entity.getPartnerName());
        tradingDto.setPartnerAddress(entity.getPartnerJuusho());

        return tradingDto;
    }

    // 収入テーブルから寄付上限チェック用Entityに変換する
    private KobetsuKiseiMeisaiEntity convertEntiy(final OfferingBalancesheetIncome2022Entity entity) {

        KobetsuKiseiMeisaiEntity meisaiEntity = new KobetsuKiseiMeisaiEntity();
        BeanUtils.copyProperties(entity, meisaiEntity);

        // 名称で検索する場合はコードを共通部分に入力する必要がない

        return meisaiEntity;
    }

    // 1団体当たりの上限金額を取得する
    private Long getLimit(final Integer youshikiEdaKbn) {

        switch (youshikiEdaKbn) {
            case YoushikiEdaKbn.KOJIN:
                return 1_500_000L; // 1団体150万円 SUPPRESS CHECKSTYLE MagicNumber

            // TODO 企業・団体の情報が整備された時点で
            case YoushikiEdaKbn.KIGYOU_DANTAI:
                return -1L; // 影響のない値

            case YoushikiEdaKbn.SEIJI_DANTAI:
                return 50_000_000L; // 1団体5000万円 SUPPRESS CHECKSTYLE MagicNumber

            default:
                throw new IllegalArgumentException("Unexpected value: " + youshikiEdaKbn);
        }

    }

}
