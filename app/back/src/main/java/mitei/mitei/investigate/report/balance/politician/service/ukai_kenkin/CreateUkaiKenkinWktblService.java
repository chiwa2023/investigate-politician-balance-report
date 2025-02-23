package mitei.mitei.investigate.report.balance.politician.service.ukai_kenkin;


import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinConditionCapsuleDto;

/**
 * 迂回献金抽出用ワークテーブル作成Service
 */
public class CreateUkaiKenkinWktblService {

    ///** 収入データ取得Logic */
    //@Autowired
    //private ConvertUkaiKenkinIncomeByCodeY2022Logic convertUkaiKenkinIncomeByCodeY2022Logic;
    
    ///** 政治団体識別コードから政治団体属性取得Logic */
    //@Autowired
    //private GetRelationPersonPoliOrgByCodeY2022Logic getRelationPersonPoliOrgByCodeY2022Logic;

    ///** 政治団体属性関連者が一致する政治団体同一識別コードリスト取得Logic */
    //@Autowired
    //private GetSamePersonPoliOrgByCodeLogic getSamePersonPoliOrgByCodeLogic;

    
    public void practice(final UkaiKenkinConditionCapsuleDto capsuleDto) {

        // 指定した政治団体同一識別コードから政治団体関連者を抽出する
        //PoliticalOrganizationPropertyEntity propertyEntity = getRelationPersonPoliOrgByCodeY2022Logic.practice(null);

        // 政治団体関連者から該当政治団体同一識別コードを抽出する
        //List<Integer> listPoliOrgCode0 = getSamePersonPoliOrgByCodeLogic.paractice(propertyEntity);
        
        // 抽出した団体すべての寄付データを抽出して複写する

        // 複写したデータの寄付者側の政治団体が記載者となるデータを抽出して複写する

    }
}
