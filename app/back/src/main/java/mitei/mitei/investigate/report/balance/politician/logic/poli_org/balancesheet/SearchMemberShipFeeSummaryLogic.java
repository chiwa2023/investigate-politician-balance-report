package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee.PoliOrgFeeInsuDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.membership_fee.SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.search.SearchMembershipFeeCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_party.search.SearchPoliPartyRelationPersonResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheet0702And0713And0717Summary2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheet0702And0713And0717Summary2022Repository;
// importを追加

/**
 * 会費要約検索Logic
 */
@Component
public class SearchMemberShipFeeSummaryLogic {

    /** 政治資金収支報告書様式2,13,17Repository */
    @Autowired
    private OfferingBalancesheet0702And0713And0717Summary2022Repository balancesheet0702And0713And0717Summary2022Repository;

    /** 党費分類同額 */
    private static final String LEVEL_THOHI = "党費と同額";

    /** 党費分類未満 */
    private static final String LEVEL_MIMAN = "党費未満";

    /** 党費分類注意 */
    private static final String LEVEL_ATTENTION = "注意水準";

    /** 党費分類警告 */
    private static final String LEVEL_WARNING = "警告水準";

    /** 党費分類より上 */
    private static final String LEVEL_OVER = "党費より上";

    /**
     * 検索処理を行う
     *
     * @param capsuleDto      検索条件Dto
     * @param searchResultDto 関連者検索結果Dto
     * @return 検索結果Dto
     */
    public SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto practice(
            final SearchMembershipFeeCapsuleDto capsuleDto,
            final SearchPoliPartyRelationPersonResultDto searchResultDto) {

        List<OfferingBalancesheet0702And0713And0717Summary2022Entity> listEntity;
        if (capsuleDto.getPoliticalOrgnaizationId().equals(0L)) {

            // 政治団体Idが指定されていない場合は政党所属代表者を抽出する
            listEntity = balancesheet0702And0713And0717Summary2022Repository
                    .findByRelationPersonCodeDelegateInAndSaishinKbn(searchResultDto.getListPerson(),
                            DataHistoryStatusConstants.INSERT.value());
            searchResultDto.setPosPage(searchResultDto.getPosPage());
        } else {

            // 政治団体が指定されている場合は直接取得(運営上1件しか取れない)
            listEntity = balancesheet0702And0713And0717Summary2022Repository
                    .findByPoliticalOrganizationCodeAndSaishinKbn(capsuleDto.getPoliticalOrgnaizationCode(),
                            DataHistoryStatusConstants.INSERT.value());

            // ページングは即した値に
            searchResultDto.setPosPage(0);
        }

        List<PoliOrgFeeInsuDto> listSummary = new ArrayList<>();
        for (OfferingBalancesheet0702And0713And0717Summary2022Entity entity : listEntity) {
            listSummary.add(this.createFeeInsuDto(entity, capsuleDto));
        }

        SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto resultDto = new SearchPoliOrgBalancesheetMembershipFeeSummaryResultDto();

        // リストを格納
        resultDto.setListSummary(listSummary);
        // ページング情報を保存
        resultDto.setCountAll(listEntity.size());
        resultDto.setPosPage(searchResultDto.getPosPage());

        return resultDto;
    }

    private PoliOrgFeeInsuDto createFeeInsuDto(final OfferingBalancesheet0702And0713And0717Summary2022Entity entity,
            final SearchMembershipFeeCapsuleDto capsuleDto) {

        PoliOrgFeeInsuDto feeInsuDto = new PoliOrgFeeInsuDto();

        // entityから基礎情報をコピー
        feeInsuDto.setFee(Long.parseLong(entity.getKojiFutanGoukei()));
        feeInsuDto.setInsu(Integer.parseInt(entity.getKojiFutanSuu()));

        feeInsuDto.setPoliOrgDaihyoushaId(entity.getRelationPersonIdDelegate());
        feeInsuDto.setPoliOrgDaihyoushaCode(entity.getRelationPersonCodeDelegate());
        feeInsuDto.setPoliOrgDaihyoushaName(entity.getRelationPersonNameDelegate());
        feeInsuDto.setPoliticalOrgnaizationId(entity.getPoliticalOrganizationId());
        feeInsuDto.setPoliticalOrgnaizationCode(entity.getPoliticalOrganizationCode());
        feeInsuDto.setPoliticalOrgnaizationName(entity.getPoliticalOrganizationName());

        // 小数点2位切り捨て表示
        float avg = Float.valueOf(feeInsuDto.getFee()) / feeInsuDto.getInsu(); // NOPMD

        feeInsuDto.setAverage(String.format("%.2f", avg));

        // 会費を年額基準に調整する
        int fee;
        final int yearMonth = 12;
        if (capsuleDto.getFeeMonth() == 0) {
            fee = capsuleDto.getFeeYear();
        } else {
            fee = capsuleDto.getFeeMonth() * yearMonth;
        }

        // 評価値を設定
        feeInsuDto
                .setRating(this.convertRating(avg, fee, capsuleDto.getLevelAttention(), capsuleDto.getLevelWarning()));

        return feeInsuDto;
    }

    // 評価値を作成する
    private String convertRating(final float avg, final int fee, final int levelAttention, final int levelWarning) {

        // +-1に収まっていた場合は党費同額
        if (avg > fee - 1 && avg < fee + 1) {
            return LEVEL_THOHI;
        }

        // 1以下の場合は未満
        if (avg <= fee - 1) {
            return LEVEL_MIMAN;
        }

        String data = "";
        //
        if (avg >= fee + 1) {
            data = LEVEL_OVER; // 注意水準、警告水準未入力に備えて、値を返却しない
        }

        // 警告水準以上
        if (levelWarning > 0 && levelWarning * fee < avg) {
            return LEVEL_WARNING;
        }

        // 注意水準以上
        if (levelAttention > 0 && levelAttention * fee < avg) {
            return LEVEL_ATTENTION;
        }

        // すべてに該当しない場合はより上
        return data;
    }

}
