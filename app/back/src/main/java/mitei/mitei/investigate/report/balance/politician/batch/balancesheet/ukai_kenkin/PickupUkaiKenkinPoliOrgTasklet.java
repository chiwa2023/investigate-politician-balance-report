package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage01Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage02Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage03Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage04Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage05Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage06Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage07Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage08Dto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.RouteUkaiKenkinStage09Dto;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinPickupRouteEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.ConvertUkaiKenkinDetailToRouteByExternalPersonLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.GetSamePersonPoliOrgByCodeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes01Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes02Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes03Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes04Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes05Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes06Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes07Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes08Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.InsertUkaiKenkinRouteTimes09Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.PickupSamePoliOrgPartnerLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.PickupSameRelationPersonDataLogic;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationPropertyRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinPickupRouteRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;

@Component
public class PickupUkaiKenkinPoliOrgTasklet implements Tasklet {

    /** 関係者が同じである政治団体取得Logic */
    @Autowired
    private GetSamePersonPoliOrgByCodeLogic getSamePersonPoliOrgByCodeLogic;

    /** 関係者が同じである個人・企業明細から経路抽出Logic */
    @Autowired
    private PickupSameRelationPersonDataLogic pickupSameRelationPersonDataLogic;

    /** 関係者が同じである政治団体明細から経路抽出Logic */
    @Autowired
    private PickupSamePoliOrgPartnerLogic pickupSamePoliOrgPartnerLogic;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinPickupRouteRepository wkTblUkaiKenkinPickupRouteRepository;

    /** 政治団体属性Repository */
    @Autowired
    private PoliticalOrganizationPropertyRepository politicalOrganizationPropertyRepository;

    /** 迂回献金経路ワークテーブルRepository */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    /** 操作者同一識別コード */
    private Long userId;

    /** 操作者同一識別コード */
    private Integer userCode;

    /** 操作者同一識別コード */
    private String userName;

    /** (操作者指定)政治団体同一識別コード */
    private Integer poliOrgCode;

    /** 交付金検索有無 */
    private Boolean isSearchKoufukin;

    
    /** 取得階層1回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes01Logic insertUkaiKenkinRouteTimes01Logic;

    /** 取得階層2回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes02Logic insertUkaiKenkinRouteTimes02Logic;

    /** 取得階層3回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes03Logic insertUkaiKenkinRouteTimes03Logic;
    
    /** 取得階層4回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes04Logic insertUkaiKenkinRouteTimes04Logic;
    
    /** 取得階層5回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes05Logic insertUkaiKenkinRouteTimes05Logic;
    
    /** 取得階層6回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes06Logic insertUkaiKenkinRouteTimes06Logic;
    
    /** 取得階層7回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes07Logic insertUkaiKenkinRouteTimes07Logic;
    
    /** 取得階層8回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes08Logic insertUkaiKenkinRouteTimes08Logic;
    
    /** 取得階層9回抽出ルート挿入Logic */
    @Autowired
    private InsertUkaiKenkinRouteTimes09Logic insertUkaiKenkinRouteTimes09Logic;
    
    
    
    
    
    
    
    /**
     * JobParameterを取得する
     *
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        // Jobパラメータの取得
        userId = stepExecution.getJobParameters().getLong("userId");
        userCode = Math.toIntExact(stepExecution.getJobParameters().getLong("userCode"));
        userName = stepExecution.getJobParameters().getString("userName");
        poliOrgCode = Math.toIntExact(stepExecution.getJobParameters().getLong("poliOrgCode"));
        isSearchKoufukin = Boolean.valueOf(stepExecution.getJobParameters().getString("isSearchKoufukin"));
    }

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // 政治団体データを抽出する
        Optional<PoliticalOrganizationPropertyEntity> optional = politicalOrganizationPropertyRepository
                .findByPoliticalOrganizationCodeAndSaishinKbn(poliOrgCode, DataHistoryStatusConstants.INSERT.value());

        PoliticalOrganizationPropertyEntity propertyEntiy = optional.get();

        // 献金が『手元に戻ってきた』瞬間のデータを抽出する
        List<WkTblUkaiKenkinPickupRouteEntity> listPoliOrg = pickupSamePoliOrgPartnerLogic.practice(userCode,
                getSamePersonPoliOrgByCodeLogic.paractice(propertyEntiy), propertyEntiy);

        for (WkTblUkaiKenkinPickupRouteEntity entity : listPoliOrg) {

            // if(entity.getPickupStage() == 4) {
            // List<RouteUkaiKenkinStage04Dto> list =
            // wkTblUkaiKenkinRepository.findRouteStage04(userCode,entity.getTradingPartnerCode());
            // for(RouteUkaiKenkinStage04Dto dto: list) {
            // System.out.println("経路"+dto.getCode00() + "→" +dto.getCode01() + "→"
            // +dto.getCode02() + "→" +dto.getCode03() + "→" +dto.getCode04() ) ;
            // }
            // }

            switch (entity.getPickupStage()) {
                // 取得階層1
                case 1:
                    List<RouteUkaiKenkinStage01Dto> list1 = wkTblUkaiKenkinRepository.findRouteStage01(userCode,
                            entity.getTradingPartnerCode());
                    break;

                // 取得階層1
                case 2:
                    List<RouteUkaiKenkinStage02Dto> list2 = wkTblUkaiKenkinRepository.findRouteStage02(userCode,
                            entity.getTradingPartnerCode());
                    break;
                // 取得階層1
                case 3:
                    List<RouteUkaiKenkinStage03Dto> list3 = wkTblUkaiKenkinRepository.findRouteStage03(userCode,
                            entity.getTradingPartnerCode());
                    break;
                // 取得階層1
                case 4:
                    List<RouteUkaiKenkinStage04Dto> list4 = wkTblUkaiKenkinRepository.findRouteStage04(userCode,
                            entity.getTradingPartnerCode());
                    break;
                // 取得階層1
                case 5:
                    List<RouteUkaiKenkinStage05Dto> list5 = wkTblUkaiKenkinRepository.findRouteStage05(userCode,
                            entity.getTradingPartnerCode());
                    break;
                // 取得階層1
                case 6:
                    List<RouteUkaiKenkinStage06Dto> list6 = wkTblUkaiKenkinRepository.findRouteStage06(userCode,
                            entity.getTradingPartnerCode());
                    break;
                // 取得階層1
                case 7:
                    List<RouteUkaiKenkinStage07Dto> list7 = wkTblUkaiKenkinRepository.findRouteStage07(userCode,
                            entity.getTradingPartnerCode());
                    break;
                // 取得階層1
                case 8:
                    List<RouteUkaiKenkinStage08Dto> list8 = wkTblUkaiKenkinRepository.findRouteStage08(userCode,
                            entity.getTradingPartnerCode());
                    break;
                // 取得階層1
                case 9:
                    List<RouteUkaiKenkinStage09Dto> list9 = wkTblUkaiKenkinRepository.findRouteStage09(userCode,
                            entity.getTradingPartnerCode());
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected value: " + entity.getPickupStage());
            }

        }

        wkTblUkaiKenkinPickupRouteRepository.saveAll(listPoliOrg);

        // 個人・企業データを抽出する
        // List<WkTblUkaiKenkinPickupRouteEntity> listPerson =
        // pickupSameRelationPersonDataLogic.practice(userCode);
        // wkTblUkaiKenkinPickupRouteRepository.saveAll(listPerson);

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
