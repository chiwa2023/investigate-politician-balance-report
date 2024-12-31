package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblFukisaiBalancesheetEntity;
import mitei.mitei.investigate.report.balance.politician.entity.poli_org.balancesheet.y2022.OfferingBalancesheetOutcome2022Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_org.balancesheet.y2022.OfferingBalancesheetOutcome2022Repository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 支出テーブルを不記載ワークテーブルEntityに変換する
 */
@Component
public class ConvertFukisaiOutcomeY2022Logic {

    /** 政治資金収支報告書支出提出分Repository */
    @Autowired
    private OfferingBalancesheetOutcome2022Repository offeringBalancesheetOutcome2022Repository;

    /**
     * 政治団体同一識別コードと取引相手政治団体同一識別コードで該当データを検出する
     *
     * @param poliOrgCode      政治団体同一識別コード
     * @param listRelationCode 取引相手政治団体同一識別コードリスト
     * @param privilegeDto     権限確認Dto
     * @return 変換された不記載検出ワークテーブルEntity
     */
    public List<WkTblFukisaiBalancesheetEntity> practiceCode(final Integer poliOrgCode,
            final List<Integer> listRelationCode, final CheckPrivilegeDto privilegeDto) {

        List<OfferingBalancesheetOutcome2022Entity> listOutcome = offeringBalancesheetOutcome2022Repository
                .findByRelationPoliticalOrgCodeOutcomeAndSaishinKbnAndPoliticalOrganizationCodeIn(poliOrgCode,
                        DataHistoryStatusConstants.INSERT.value(), listRelationCode);

        List<WkTblFukisaiBalancesheetEntity> listWkTbl = new ArrayList<>();
        for (OfferingBalancesheetOutcome2022Entity entity : listOutcome) {

            listWkTbl.add(this.copyEntity(entity, privilegeDto,
                    String.format("%020d", entity.getPoliticalOrganizationCode())));
        }

        return listWkTbl;
    }

    /**
     * 原文書政治団体名と原文書取引相手政治団体名称で該当データを検出する
     *
     * @param partnerName     団体名
     * @param listPertnerName 取引相手リスト
     * @param privilegeDto    権限確認Dto
     * @return 変換された不記載検出ワークテーブルEntity
     */
    public List<WkTblFukisaiBalancesheetEntity> practiceName(final String partnerName,
            final List<String> listPertnerName, final CheckPrivilegeDto privilegeDto) {

        List<OfferingBalancesheetOutcome2022Entity> listOutcome = offeringBalancesheetOutcome2022Repository
                .findByPartnerNameAndSaishinKbnAndDantaiNameIn(partnerName, DataHistoryStatusConstants.INSERT.value(),
                        listPertnerName);

        List<WkTblFukisaiBalancesheetEntity> listWkTbl = new ArrayList<>();
        for (OfferingBalancesheetOutcome2022Entity entity : listOutcome) {

            listWkTbl.add(this.copyEntity(entity, privilegeDto, entity.getDantaiName()));

        }

        return listWkTbl;
    }

    private WkTblFukisaiBalancesheetEntity copyEntity(final OfferingBalancesheetOutcome2022Entity entity,
            final CheckPrivilegeDto privilegeDto, final String searchKey) {
        WkTblFukisaiBalancesheetEntity entityFukisai = new WkTblFukisaiBalancesheetEntity();

        BeanUtils.copyProperties(entity, entityFukisai);

        SetTableDataHistoryUtil.practice(privilegeDto, entityFukisai, DataHistoryStatusConstants.INSERT);
        // 手入力が必要
        entityFukisai.setDocumentCodeOutput(entity.getDocumentCode());
        entityFukisai.setKingakuOutput(entity.getKingaku());
        entityFukisai.setItemName(entity.getMokuteki());

        entityFukisai.setRelationPoliticalOrgId(entity.getRelationPoliticalOrgIdOutcome());
        entityFukisai.setRelationPoliticalOrgCode(entity.getRelationPoliticalOrgCodeOutcome());
        entityFukisai.setRelationPoliticalOrgName(entity.getRelationPoliticalOrgNameOutcome());

        // 取引相手(＝支出の場合は文書作成団体=団体名)を入れる
        entityFukisai.setSearchOrderKey(searchKey);

        return entityFukisai;
    }

}
