package mitei.mitei.investigate.report.balance.politician.batch.balancesheet.ukai_kenkin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import mitei.mitei.investigate.report.balance.politician.constants.BalancesheetYoushikiKbnConstants.YoushikiKbn;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationPropertyEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblUkaiKenkinEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.GetSamePersonPoliOrgByCodeLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.ConvertUkaiKenkinIncomeByCodeY2022Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.y2022.GetRelationPersonPoliOrgByCodeY2022Logic;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;

/**
 * 階層0(迂回なし)での関連全データを抽出する
 */
@Component
public class CreateUkaiKenkinDataStageZeroTasklet implements Tasklet, StepExecutionListener {

    /** 収入データ取得Logic */
    @Autowired
    private ConvertUkaiKenkinIncomeByCodeY2022Logic convertUkaiKenkinIncomeByCodeY2022Logic;

    /** 政治団体識別コードから政治団体属性取得Logic */
    @Autowired
    private GetRelationPersonPoliOrgByCodeY2022Logic getRelationPersonPoliOrgByCodeY2022Logic;

    /** 政治団体属性関連者が一致する政治団体同一識別コードリスト取得Logic */
    @Autowired
    private GetSamePersonPoliOrgByCodeLogic getSamePersonPoliOrgByCodeLogic;

    /** 迂回献金(明細)Dao */
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

    /**
     * JobParameterを取得する
     *
     */
    @Override
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
    @Transactional
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        // 指定した政治団体同一識別コードから政治団体関連者を抽出する
        PoliticalOrganizationPropertyEntity propertyEntity = getRelationPersonPoliOrgByCodeY2022Logic
                .practice(poliOrgCode);

        // 政治団体関連者から該当政治団体同一識別コードを抽出する
        List<Integer> listPoliOrgCode0 = getSamePersonPoliOrgByCodeLogic.paractice(propertyEntity);

        CheckPrivilegeDto privilegeDto = CreatePrivilegeDtoByParamUtil.practice(userId, userCode, userName);

        // 検索する様式区分を設定する
        List<Integer> listYoushikiKbn = new ArrayList<>();
        listYoushikiKbn.add(YoushikiKbn.DONATE);
        if (isSearchKoufukin) {
            listYoushikiKbn.add(YoushikiKbn.KOUFUKIN);
        }

        // 関連者が同じ政治団体団体取り引きを抽出する
        List<WkTblUkaiKenkinEntity> list = convertUkaiKenkinIncomeByCodeY2022Logic.practice(listPoliOrgCode0,
                propertyEntity, listYoushikiKbn, privilegeDto);
        
        // テーブル用同一識別コードを算出
        Integer code = 1;
        Optional<WkTblUkaiKenkinEntity> optional = wkTblUkaiKenkinRepository
                .findFirstByOrderByWkTblUkaiKenkinCodeDesc();
        if (!optional.isEmpty()) {
            code += optional.get().getWkTblUkaiKenkinCode();
        }

        // テーブル用同一識別コードをセット
        for (WkTblUkaiKenkinEntity entity : list) {
            entity.setWkTblUkaiKenkinCode(code);
            code++;
        }

        // 全保存
        wkTblUkaiKenkinRepository.saveAll(list);

        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
