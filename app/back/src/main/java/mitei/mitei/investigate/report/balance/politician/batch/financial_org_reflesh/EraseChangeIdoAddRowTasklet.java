package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.ZenginOrgChangeKbnConstants;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchMasterRepository;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgChangeBranchRepository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 全銀金融機関支店異動テーブルからマスタコピーを確認して終了フラグを立てる
 */
@Component
public class EraseChangeIdoAddRowTasklet implements Tasklet, StepExecutionListener {

    /** 金融機関支店異動Repository */
    @Autowired
    private ZenginOrgChangeBranchRepository zenginOrgChangeBranchRepository;

    /** 金融機関支店マスタRepository */
    @Autowired
    private ZenginOrgBranchMasterRepository zenginOrgBranchMasterRepository;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        List<Integer> listCheckAddId = new ArrayList<>();
        listCheckAddId.add(0);

        List<ZenginOrgChangeBranchEntity> listAdd = zenginOrgChangeBranchRepository
                .findByYetFinished(ZenginOrgChangeKbnConstants.ADD, listCheckAddId);

        // ユーザ設定する
        ZenginOrgChangeBranchEntity tempEnity;
        CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();
        if (!listAdd.isEmpty()) {
            tempEnity = listAdd.get(0);
            privilegeDto.setLoginUserId(tempEnity.getInsertUserId());
            privilegeDto.setLoginUserCode(tempEnity.getInsertUserCode());
            privilegeDto.setLoginUserName(tempEnity.getInsertUserName());
        }

        while (!listAdd.isEmpty()) {

            List<ZenginOrgChangeBranchEntity> listAddHistory = new ArrayList<>(); // NOPMD
            for (ZenginOrgChangeBranchEntity addEntity : listAdd) {
                listCheckAddId.add(addEntity.getZenginOrgChangeBranchId());

                List<ZenginOrgBranchMasterEntity> listMaster = zenginOrgBranchMasterRepository
                        .findByZenginOrgTempoMasterNameAndSaishinKbn(addEntity.getZenginOrgTempoMasterName(),
                                DataHistoryStatusConstants.INSERT.value());
                
                if (!listMaster.isEmpty()) {
                    SetTableDataHistoryUtil.practice(privilegeDto, addEntity, DataHistoryStatusConstants.UPDATE);
                    listAddHistory.add(addEntity);
                    listAddHistory.add(this.createChangeData(addEntity, privilegeDto));
                }

            }
            zenginOrgChangeBranchRepository.saveAllAndFlush(listAddHistory);

            // loopが終わったらリストを更新
            listAdd = zenginOrgChangeBranchRepository.findByYetFinished(ZenginOrgChangeKbnConstants.ADD,
                    listCheckAddId);
        }

        // 処理終了
        return RepeatStatus.FINISHED;

    }

    // 新しい作業完了履歴を作成する
    private ZenginOrgChangeBranchEntity createChangeData(final ZenginOrgChangeBranchEntity entitySrc,
            final CheckPrivilegeDto privilegeDto) {
        ZenginOrgChangeBranchEntity entity = new ZenginOrgChangeBranchEntity();
        BeanUtils.copyProperties(entitySrc, entity);

        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);
        entity.setIsFinished(true);
        entity.setZenginOrgChangeBranchId(0); // auto_increment
        return entity;
    }

}
