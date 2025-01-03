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
import mitei.mitei.investigate.report.balance.politician.util.CreatePrivilegeDtoByParamUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 削除データが積みあがっているのを確認して終了フラグとする
 */
@Component
public class EraseChangeIdoDeleteRowTasklet implements Tasklet, StepExecutionListener {

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

        // 対象リストを取得する
        List<Integer> listCheckDeleteId = new ArrayList<>();
        listCheckDeleteId.add(0);

        List<ZenginOrgChangeBranchEntity> listDelete = zenginOrgChangeBranchRepository
                .findByYetFinished(ZenginOrgChangeKbnConstants.DELETE, listCheckDeleteId);

        // ユーザ設定する
        ZenginOrgChangeBranchEntity tempEntity;
        CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();
        if (!listDelete.isEmpty()) {
            tempEntity = listDelete.get(0);
            privilegeDto = CreatePrivilegeDtoByParamUtil.practice(tempEntity.getInsertUserId(),
                    tempEntity.getInsertUserCode(), tempEntity.getInsertUserName());
        }

        while (!listDelete.isEmpty()) {
            List<ZenginOrgChangeBranchEntity> listDeleteHistory = new ArrayList<>(); // NOPMD
            for (ZenginOrgChangeBranchEntity deleteEntity : listDelete) {

                listCheckDeleteId.add(deleteEntity.getZenginOrgChangeBranchId());

                List<ZenginOrgBranchMasterEntity> listMaster = zenginOrgBranchMasterRepository
                        .findByZenginOrgTempoMasterNameAndSaishinKbn(deleteEntity.getZenginOrgTempoMasterName(),
                                DataHistoryStatusConstants.INSERT.value());

                if (listMaster.isEmpty()) {
                    SetTableDataHistoryUtil.practice(privilegeDto, deleteEntity, DataHistoryStatusConstants.UPDATE);

                    listDeleteHistory.add(deleteEntity);
                    listDeleteHistory.add(this.createChangeData(deleteEntity, privilegeDto));
                }
            }

            zenginOrgChangeBranchRepository.saveAllAndFlush(listDeleteHistory);

            // loopが終わったらリストを更新
            listDelete = zenginOrgChangeBranchRepository.findByYetFinished(ZenginOrgChangeKbnConstants.DELETE,
                    listCheckDeleteId);
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
