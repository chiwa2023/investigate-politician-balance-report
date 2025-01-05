package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2023;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.fukisai.FukisaiSearchConditionDto;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2023.OfferingBalancesheetIncome2023Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2023.OfferingBalancesheetIncome2023Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 提出済収支報告書収入から該当データを検索し、不記載ワークテーブルEntityに変換する
 */
@Component
public class ConvertFukisaiIncomeY2023Logic {

    /** 政治資金収支報告書収入提出分 */
    @Autowired
    private OfferingBalancesheetIncome2023Repository offeringBalancesheetIncome2023Repository;

    /**
     * 処理を行う
     *
     * @param conditionDto 検索条件Dto
     * @param privilegeDto 権限確認Dto
     * @return 不記載ワークテーブル
     */
    public List<WkTblFukisaiBalancesheetEntity> practice(final FukisaiSearchConditionDto conditionDto,
            final CheckPrivilegeDto privilegeDto) {

        List<OfferingBalancesheetIncome2023Entity> listIncome;

        // 収入テーブルから政治団体でデータを抽出する
        // 個人と企業・団体は取引情報を文書公開していないため除外
        // TODO 政治団体・関連者のコード標準化が達成されたら原団体名称に機能を削除する
        if (conditionDto.getIsSearchCode()) {
            listIncome = offeringBalancesheetIncome2023Repository
                    .findByRelationPoliticalOrgCodeIncomeNotAndSaishinKbnAndPoliticalOrganizationCode(0,
                            DataHistoryStatusConstants.INSERT.value(), conditionDto.getPoliOrgCode());

        } else {
            // 取引相手は初期値でないのが条件

            // 基本的には同じはずだが、政治団体名称と原文書団体名が異なる場合に備えて、別途原文書団体名を取得する
            String dantaiName;
            Optional<OfferingBalancesheetIncome2023Entity> optional = offeringBalancesheetIncome2023Repository
                    .findFirstByPoliticalOrganizationCodeAndSaishinKbn(conditionDto.getPoliOrgCode(),
                            DataHistoryStatusConstants.INSERT.value());
            if (optional.isEmpty()) {
                // 空リスト原団体名が空ということはないはずなので、そのまま進行しても良いが中断したほうがよさそう
                return new ArrayList<>();
            } else {
                dantaiName = optional.get().getDantaiName();
            }
            final String initPartner = "";

            listIncome = offeringBalancesheetIncome2023Repository.findByPartnerNameIsNotAndSaishinKbnAndDantaiName(
                    initPartner, DataHistoryStatusConstants.INSERT.value(), dantaiName);

        }

        // 抽出したデータをワークテーブルで変換する
        List<WkTblFukisaiBalancesheetEntity> listWkTbl = new ArrayList<>();
        for (OfferingBalancesheetIncome2023Entity entity : listIncome) {

            String searchKey;
            if (conditionDto.getIsSearchCode()) {
                searchKey = String.format("%020d", entity.getRelationPoliticalOrgCodeIncome());
            } else {
                searchKey = entity.getPartnerName();
            }

            listWkTbl.add(this.copyEntity(entity, privilegeDto, searchKey));
        }

        return listWkTbl;
    }

    private WkTblFukisaiBalancesheetEntity copyEntity(final OfferingBalancesheetIncome2023Entity entity,
            final CheckPrivilegeDto privilegeDto, final String searchKey) {
        WkTblFukisaiBalancesheetEntity entityFukisai = new WkTblFukisaiBalancesheetEntity();

        BeanUtils.copyProperties(entity, entityFukisai);

        SetTableDataHistoryUtil.practice(privilegeDto, entityFukisai, DataHistoryStatusConstants.INSERT);
        // 手入力が必要
        entityFukisai.setDocumentCodeInput(entity.getDocumentCode());
        entityFukisai.setKingakuInput(entity.getKingaku());
        entityFukisai.setRelationPoliticalOrgId(entity.getRelationPoliticalOrgIdIncome());
        entityFukisai.setRelationPoliticalOrgCode(entity.getRelationPoliticalOrgCodeIncome());
        entityFukisai.setRelationPoliticalOrgName(entity.getRelationPoliticalOrgNameIncome());

        // 取引相手を入れる
        entityFukisai.setSearchOrderKey(searchKey);

        return entityFukisai;
    }

}
