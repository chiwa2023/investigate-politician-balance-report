package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.fukisai;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiTradingInfoDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.SearchFukisaiBalancesheetResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblFukisaiBalancesheetRepository;

/**
 * 不記載検出ワークテーブル検索Logic
 */
@Component
public class SearchWkTblFukisaiLogic {

    /** 検索上限(検索キー数。データ実数ではない) */
    private static final int LIMIT = 50;

    /** 不記載検出ワークテーブル */
    @Autowired
    private WkTblFukisaiBalancesheetRepository wkTblFukisaiBalancesheetRepository;

    /**
     * 処理を行う
     *
     * @param userCode   ユーザ同一識別コード
     * @param count      件数
     * @param pageNumber ページ番号
     * @return 不記載検出検索結果Dto
     */
    public SearchFukisaiBalancesheetResultDto practice(final Integer userCode, final Integer count,
            final Integer pageNumber) {

        // ページ処理
        int countAll = count;
        if (count == 0) {
            countAll = wkTblFukisaiBalancesheetRepository.findCountRelationByUser(userCode);
        }

        SearchFukisaiBalancesheetResultDto resultDto = new SearchFukisaiBalancesheetResultDto();
        resultDto.setCountAll(countAll);
        resultDto.setPageNumber(pageNumber);

        final String initKey = "";
        String preKey = initKey;
        FukisaiTradingInfoDto tradingDto = new FukisaiTradingInfoDto();
        Long sumIncome = 0L;
        Long sumOutcome = 0L;

        // ページ数に合わせた検索キーを取得する
        Pageable pageable = Pageable.ofSize(LIMIT).withPage(pageNumber);
        List<String> listKey = wkTblFukisaiBalancesheetRepository.findSearchOrderKeyListByUser(userCode, pageable);

        List<WkTblFukisaiBalancesheetEntity> listEntity = wkTblFukisaiBalancesheetRepository
                .findByInsertUserCodeAndSearchOrderKeyInOrderBySearchOrderKeyAscYoushikiKbnAscAccrualDateValueAsc(
                        userCode, listKey);

        for (WkTblFukisaiBalancesheetEntity entity : listEntity) {

            if (preKey.equals(entity.getSearchOrderKey())) {
                // Entityを登録して合計を算出
                tradingDto.getListMeisai().add(entity);
                sumIncome += entity.getKingakuInput();
                sumOutcome += entity.getKingakuOutput();

            } else {
                if (!preKey.equals(initKey)) {
                    // 過去の計算結果を残す
                    tradingDto.setSumIncome(sumIncome);
                    tradingDto.setSumOutcome(sumOutcome);
                    tradingDto.setIsKingakuDiffer(!sumIncome.equals(sumOutcome));
                    tradingDto = new FukisaiTradingInfoDto(); // NOPMD
                }

                // 取引グループ情報の初期化
                preKey = entity.getSearchOrderKey();
                sumIncome = 0L;
                sumOutcome = 0L;
                resultDto.getListTradingGroup().add(tradingDto);
                tradingDto.setPoliticalOrgName(entity.getPoliticalOrganizationName());
                tradingDto.setPartnerName(entity.getRelationPoliticalOrgName());

                // Entityを登録して合計を算出
                tradingDto.getListMeisai().add(entity);
                sumIncome += entity.getKingakuInput();
                sumOutcome += entity.getKingakuOutput();
            }
        }
        
        // 最後に残った分を処理
        tradingDto.setSumIncome(sumIncome);
        tradingDto.setSumOutcome(sumOutcome);
        tradingDto.setIsKingakuDiffer(!sumIncome.equals(sumOutcome));

        resultDto.setIsOk(true);
        resultDto.setMessage("取得できました");
        
        return resultDto;
    }

}
