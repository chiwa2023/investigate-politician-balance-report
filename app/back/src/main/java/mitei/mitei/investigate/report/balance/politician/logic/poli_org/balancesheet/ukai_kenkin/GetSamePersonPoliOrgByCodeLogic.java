package mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationPropertyRepository;

/**
 * 関連者が共通する政治団体同一識別コードを抽出する
 */
@Component
public class GetSamePersonPoliOrgByCodeLogic {

    /** 政治団体属性Repository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    /**
     * 処理を行う
     *
     * @param propertyEntity 政治団体属性Entity
     * @return 関連者を含む政治団体同一識別コードリスト
     */
    public List<Integer> paractice(final PoliticalOrganizationPropertyEntity propertyEntity) {

        List<Integer> listPersonCode = new ArrayList<>();

        // 代表者
        this.addList(listPersonCode, propertyEntity.getDelegateRelationPersonCode());
        // 会計責任者
        this.addList(listPersonCode, propertyEntity.getAccountManagerRelationPersonCode());
        // 資金管理団体責任者
        this.addList(listPersonCode, propertyEntity.getShikinDaihyouRelationPersonCode());
        // 国会議員1
        this.addList(listPersonCode, propertyEntity.getGiin1RelationPersonCode());
        // 国会議員2
        this.addList(listPersonCode, propertyEntity.getGiin2RelationPersonCode());
        // 国会議員3
        this.addList(listPersonCode, propertyEntity.getGiin3RelationPersonCode());

        return politicalOrganizationPropertyRepository.findSameRelationPerson(listPersonCode);
    }

    // 抽出するコードを追加する
    private void addList(final List<Integer> listPersonCode, final Integer personCode) {

        if (!listPersonCode.contains(personCode) && 0 != personCode) {
            listPersonCode.add(personCode);
        }
    }

}
