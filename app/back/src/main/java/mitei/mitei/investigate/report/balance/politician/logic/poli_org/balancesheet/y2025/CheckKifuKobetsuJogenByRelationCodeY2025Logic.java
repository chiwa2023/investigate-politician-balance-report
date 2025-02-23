package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2025;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiEdaKbn;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.KifuJougenTradingInfoDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.kifu_jogen.SearchKifuJougenMeisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;
import mitei.mitei.investigate.report.balance.politician.entity.RelationCorpEntity;
import mitei.mitei.investigate.report.balance.politician.entity.RelationPersonEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.KobetsuKiseiMeisaiEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Entity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.kifu_jougen.GetDantaiKbnListLogic;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationRepository;
import mitei.mitei.investigate.report.balance.politician.repository.RelationCorpRepository;
import mitei.mitei.investigate.report.balance.politician.repository.RelationPersonRepository;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2025.OfferingBalancesheetIncome2025Repository;

/**
 * 寄付個別制限データを関連者区分同一識別コード基準取得Logic
 */
@Component
public class CheckKifuKobetsuJogenByRelationCodeY2025Logic {

    /** 政治資金収支報告書収入Repository(2025) */
    @Autowired
    private OfferingBalancesheetIncome2025Repository offeringBalancesheetIncome2025Repository;

    /** 団体区分リスト取得Logic */
    @Autowired
    private GetDantaiKbnListLogic getDantaiKbnListLogic;

    /** 関連者個人取得Logic */
    @Autowired
    private RelationPersonRepository relationPersonRepository;

    /** 関連者企業・法人取得Logic */
    @Autowired
    private RelationCorpRepository relationCorpRepository;

    /** 政治団体取得Logic */
    @Autowired
    private PoliticalOrganizationRepository politicalOrganizationRepository;

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

        List<OfferingBalancesheetIncome2025Entity> listOrgMeisai = this.getSearchResult(poliOrgCode, youshikiEdaKbn,
                listOther, pageable);

        SearchKifuJougenMeisaiBalancesheetResultDto resultOrgDto = new SearchKifuJougenMeisaiBalancesheetResultDto();

        if (!listOrgMeisai.isEmpty()) {

            // 最初1件を作成
            KifuJougenTradingInfoDto tradingDto = this.createTradingDto(listOrgMeisai.get(0));
            resultOrgDto.getListTradingGroup().add(tradingDto);

            Long relationIdPre = tradingDto.getRelationId();
            Integer relationCodePre = tradingDto.getRelationCode();

            Long relationId;
            Integer relationCode;

            Long sum = 0L;
            for (OfferingBalancesheetIncome2025Entity entity : listOrgMeisai) {
                relationId = this.getRelationId(entity);
                relationCode = this.getRelationCode(entity);
                if (relationCodePre.equals(relationCode) && relationIdPre.equals(relationId)) {
                    // 取引相手名と住所が異ならない同じ取引相手であれば既存のDtoに明細を追加して合計を増やす
                    tradingDto.getListTradingMeisai().add(this.convertEntiy(entity, tradingDto));
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
                    relationIdPre = this.getRelationId(entity);
                    relationCodePre = this.getRelationCode(entity);

                    // 最初明細を投入
                    tradingDto.getListTradingMeisai().add(this.convertEntiy(entity, tradingDto));
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

    // 様式枝区分ことにリストを取得する
    private List<OfferingBalancesheetIncome2025Entity> getSearchResult(final Integer poliOrgCode,
            final Integer youshikiEdaKbn, final List<String> listOther, final Pageable pageable) {

        switch (youshikiEdaKbn) {
            case YoushikiEdaKbn.KOJIN:
                List<Integer> listPerson = offeringBalancesheetIncome2025Repository
                        .findRelationPersonByPoliOrg(poliOrgCode);
                return offeringBalancesheetIncome2025Repository
                        .findByRelationPersonCodeIncomeInAndDantaiKbnInAndYoushikiEdaKbnAndSaishinKbnOrderByRelationPersonIdIncomeAsc(
                                listPerson, listOther, youshikiEdaKbn, DataHistoryStatusConstants.INSERT.value(),
                                pageable);

            case YoushikiEdaKbn.KIGYOU_DANTAI:
                List<Integer> listCorp = offeringBalancesheetIncome2025Repository
                        .findRelationCorpByPoliOrg(poliOrgCode);
                return offeringBalancesheetIncome2025Repository
                        .findByRelationCorpCodeIncomeInAndDantaiKbnInAndYoushikiEdaKbnAndSaishinKbnOrderByRelationCorpCodeIncomeAsc(
                                listCorp, listOther, youshikiEdaKbn, DataHistoryStatusConstants.INSERT.value(),
                                pageable);

            case YoushikiEdaKbn.SEIJI_DANTAI:

                List<Integer> listOrg = offeringBalancesheetIncome2025Repository
                        .findRelationPoliOrgByPoliOrg(poliOrgCode);

                return offeringBalancesheetIncome2025Repository
                        .findByRelationPoliticalOrgCodeIncomeInAndDantaiKbnInAndYoushikiEdaKbnAndSaishinKbnOrderByRelationPoliticalOrgCodeIncomeAsc(
                                listOrg, listOther, youshikiEdaKbn, DataHistoryStatusConstants.INSERT.value(),
                                pageable);
            default:
                throw new IllegalArgumentException("Unexpected value: " + youshikiEdaKbn); // NOPMD
        }

    }

    // 収入Entityから上限規制用Entityに変換する
    private KobetsuKiseiMeisaiEntity convertEntiy(final OfferingBalancesheetIncome2025Entity entity,
            final KifuJougenTradingInfoDto tradingDto) {

        KobetsuKiseiMeisaiEntity meisaiEntity = new KobetsuKiseiMeisaiEntity();
        BeanUtils.copyProperties(entity, meisaiEntity);

        meisaiEntity.setRelationId(tradingDto.getRelationId());
        meisaiEntity.setRelationCode(tradingDto.getRelationCode());
        meisaiEntity.setPartnerName(tradingDto.getPartnerName());
        meisaiEntity.setPartnerJuusho(tradingDto.getPartnerAddress());

        return meisaiEntity;
    }

    // 取引明細Dtoを作成する
    private KifuJougenTradingInfoDto createTradingDto(final OfferingBalancesheetIncome2025Entity entity) {

        KifuJougenTradingInfoDto tradingDto = new KifuJougenTradingInfoDto();

        tradingDto.setYoushikiEdaKbn(entity.getYoushikiEdaKbn());
        tradingDto.setPartnerName(entity.getPartnerName());
        tradingDto.setPartnerAddress(entity.getPartnerJuusho());

        // コードとIdは個人・企業・政治団体で共通化したので人力で入力する
        switch (entity.getYoushikiEdaKbn()) {
            case YoushikiEdaKbn.KOJIN:
                tradingDto.setRelationId(entity.getRelationPersonIdIncome());
                tradingDto.setRelationCode(entity.getRelationPersonCodeIncome());
                RelationPersonEntity personEntity = relationPersonRepository
                        .findById(entity.getRelationPersonIdIncome()).get();
                tradingDto.setPartnerName(personEntity.getRelationPersonName());
                tradingDto.setPartnerAddress(personEntity.getAddressName());
                break;

            case YoushikiEdaKbn.KIGYOU_DANTAI:
                tradingDto.setRelationId(entity.getRelationCorpIdIncome());
                tradingDto.setRelationCode(entity.getRelationCorpCodeIncome());

                RelationCorpEntity corpEntity = relationCorpRepository.findById(entity.getRelationCorpIdIncome()).get();
                tradingDto.setPartnerName(corpEntity.getRelationCorpName());
                tradingDto.setPartnerAddress(corpEntity.getAddressName());
                break;

            case YoushikiEdaKbn.SEIJI_DANTAI:
                tradingDto.setRelationId(entity.getRelationPoliticalOrgIdIncome());
                tradingDto.setRelationCode(entity.getRelationPoliticalOrgCodeIncome());
                PoliticalOrganizationEntity organizationEntity = politicalOrganizationRepository
                        .findById(tradingDto.getRelationId()).get();
                tradingDto.setPartnerName(organizationEntity.getPoliticalOrganizationName());
                tradingDto.setPartnerAddress(organizationEntity.getAddressAll());
                break;

            default:
                throw new IllegalArgumentException("Unexpected value: " + entity.getYoushikiEdaKbn());
        }

        return tradingDto;
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

    // 関連Idを取得する
    private Long getRelationId(final OfferingBalancesheetIncome2025Entity entity) {

        switch (entity.getYoushikiEdaKbn()) {
            case YoushikiEdaKbn.KOJIN:
                return entity.getRelationPersonIdIncome();

            case YoushikiEdaKbn.KIGYOU_DANTAI:
                return entity.getRelationCorpIdIncome();

            case YoushikiEdaKbn.SEIJI_DANTAI:
                return entity.getRelationPoliticalOrgIdIncome();

            default:
                throw new IllegalArgumentException("Unexpected value: " + entity.getYoushikiEdaKbn());
        }

    }

    // 関連Codeを取得する
    private Integer getRelationCode(final OfferingBalancesheetIncome2025Entity entity) {

        switch (entity.getYoushikiEdaKbn()) {
            case YoushikiEdaKbn.KOJIN:
                return entity.getRelationPersonCodeIncome();

            case YoushikiEdaKbn.KIGYOU_DANTAI:
                return entity.getRelationCorpCodeIncome();

            case YoushikiEdaKbn.SEIJI_DANTAI:
                return entity.getRelationPoliticalOrgCodeIncome();

            default:
                throw new IllegalArgumentException("Unexpected value: " + entity.getYoushikiEdaKbn());
        }
    }

    
}
