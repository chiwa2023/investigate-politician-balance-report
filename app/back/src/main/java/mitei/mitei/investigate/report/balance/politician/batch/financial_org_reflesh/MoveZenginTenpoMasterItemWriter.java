package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgBranchMasterEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgBranchMasterRepository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 異動金融機関支店移転データをマスタテーブルに登録する
 */
@Component
public class MoveZenginTenpoMasterItemWriter extends JpaItemWriter<ZenginOrgBranchMasterEntity> {

    /** 全銀協記入機関・店舗マスタRepository */
    @Autowired
    private ZenginOrgBranchMasterRepository zenginOrgBranchMasterRepository;

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public MoveZenginTenpoMasterItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /** 権限確認Dto */
    private final CheckPrivilegeDto privilegeDto = new CheckPrivilegeDto();

    /**
     * ログインユーザ情報を取得する
     *
     * @param stepExecution Step実行
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        privilegeDto.setLoginUserId(stepExecution.getJobParameters().getLong("loginUserId"));
        privilegeDto.setLoginUserCode(Integer.parseInt(stepExecution.getJobParameters().getString("loginUserCode")));
        privilegeDto.setLoginUserName(stepExecution.getJobParameters().getString("loginUserName"));
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends ZenginOrgBranchMasterEntity> items) {

        List<ZenginOrgBranchMasterEntity> listMoved = new ArrayList<>();
        for (ZenginOrgBranchMasterEntity entity : items) {
            
            
            SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.UPDATE);
            listMoved.add(entity);
            listMoved.add(this.createMoveEntity(entity));
        }
        
        zenginOrgBranchMasterRepository.saveAllAndFlush(listMoved);

        // TODO 仮に調査側で金融機関情報を含むマスタが存在する場合は更新する
        // 作成側では少なくとも継続事業登録は決定。関連者マスタの参照用に口座を紐づける場合は関連者マスタ
    }

    private ZenginOrgBranchMasterEntity createMoveEntity(final ZenginOrgBranchMasterEntity entitySrc) {

        ZenginOrgBranchMasterEntity entity = new ZenginOrgBranchMasterEntity();
        BeanUtils.copyProperties(entitySrc, entity);
        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);
        entity.setZenginOrgTempoMasterId(0); // 新たな履歴用なのでauto increment

        return entity;
    }
}
