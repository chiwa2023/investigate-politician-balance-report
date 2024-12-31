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
 * 全銀金融機関支店異動テーブルからマスタコピーを確認して終了フラグを立てる
 */
@Component
public class EraseChangeIdoMoveRowTasklet implements Tasklet, StepExecutionListener {

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

        // ユーザ設定する
        ZenginOrgChangeBranchEntity tempEntity;
        CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();

        List<Integer> listCheckMoveId = new ArrayList<>();
        listCheckMoveId.add(0);
        List<ZenginOrgChangeBranchEntity> listMove = zenginOrgChangeBranchRepository
                .findByYetFinished(ZenginOrgChangeKbnConstants.MOVE, listCheckMoveId);

        if (!listMove.isEmpty()) {
            tempEntity = listMove.get(0);
            privilegeDto = CreatePrivilegeDtoByParamUtil.practice(tempEntity.getInsertUserId(),
                    tempEntity.getInsertUserCode(), tempEntity.getInsertUserName());
        }

        // 取得リストが空でなく権限確認Dtoが未設定の場合
        if (!listMove.isEmpty()) {
            tempEntity = listMove.get(0);
            privilegeDto = CreatePrivilegeDtoByParamUtil.practice(tempEntity.getInsertUserId(),
                    tempEntity.getInsertUserCode(), tempEntity.getInsertUserName());
        }

        while (!listMove.isEmpty()) {

            List<ZenginOrgChangeBranchEntity> listMoveHistory = new ArrayList<>(); // NOPMD
            for (ZenginOrgChangeBranchEntity moveEntity : listMove) {
                listCheckMoveId.add(moveEntity.getZenginOrgChangeBranchId());

                List<ZenginOrgBranchMasterEntity> listMaster = zenginOrgBranchMasterRepository
                        .findByZenginOrgTempoMasterNameAndSaishinKbn(moveEntity.getZenginOrgTempoMasterName(),
                                DataHistoryStatusConstants.INSERT.value());

                if (!listMaster.isEmpty()) {

                    for (ZenginOrgBranchMasterEntity masterEntity : listMaster) {

                        if (this.checkNewData(moveEntity, masterEntity)) {
                            SetTableDataHistoryUtil.practice(privilegeDto, moveEntity,
                                    DataHistoryStatusConstants.UPDATE);
                            listMoveHistory.add(moveEntity);
                            listMoveHistory.add(this.createChangeData(moveEntity, privilegeDto));
                            break;
                        }
                    }
                }
            }
            zenginOrgChangeBranchRepository.saveAllAndFlush(listMoveHistory);

            // loopが終わったらリストを更新
            listMove = zenginOrgChangeBranchRepository.findByYetFinished(ZenginOrgChangeKbnConstants.MOVE,
                    listCheckMoveId);
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

    // 同一データか判定する
    private boolean checkNewData(final ZenginOrgChangeBranchEntity changeEntity,
            final ZenginOrgBranchMasterEntity masterEntity) {

        return changeEntity.getPostalCode().equals(masterEntity.getPostalCode())
                && changeEntity.getBranchAddress().equals(masterEntity.getBranchAddress())
                && changeEntity.getPhonNumber().equals(masterEntity.getPhonNumber());
    }
}
