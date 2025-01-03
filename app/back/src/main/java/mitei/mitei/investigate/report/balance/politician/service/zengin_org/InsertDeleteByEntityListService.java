package mitei.mitei.investigate.report.balance.politician.service.zengin_org;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgChangeBranchRepository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 廃止店舗に対して異動店舗指定入力保存Service
 */
@Service
public class InsertDeleteByEntityListService {

    /** 全銀変更内容保存Repository */
    @Autowired
    private ZenginOrgChangeBranchRepository zenginOrgChangeBranRepository;

    /**
     * 指定された移動先店舗を設定する
     *
     * @param listSrc      取得リスト全体
     * @param privilegeDto 権限確認Dto
     * @return 更新行数
     */
    public int practice(final List<ZenginOrgChangeBranchEntity> listSrc, final CheckPrivilegeDto privilegeDto) {

        List<ZenginOrgChangeBranchEntity> listRelresh = new ArrayList<>();
        for (ZenginOrgChangeBranchEntity entity : listSrc) {

            // フリー入力しておらず、指定していない値は来てもマスタにない値は来ないという想定
            // 初期値でない値のみ更新対象
            if (entity.getZenginOrgMoveId() != 0L && entity.getZenginOrgMoveCode() != 0
                    && !  "".equals(entity.getZenginOrgMoveName())) {

                listRelresh.add(this.createNewEntity(entity, privilegeDto));
                listRelresh.add(this.getOrginalEntity(entity.getZenginOrgChangeBranchId(), privilegeDto));
            }
        }

        return zenginOrgChangeBranRepository.saveAll(listRelresh).size();
    }

    // 新たな積み上げデータを作成する
    private ZenginOrgChangeBranchEntity createNewEntity(final ZenginOrgChangeBranchEntity entitySrc,
            final CheckPrivilegeDto privilegeDto) {
        ZenginOrgChangeBranchEntity entity = new ZenginOrgChangeBranchEntity();
        BeanUtils.copyProperties(entitySrc, entity);

        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.INSERT);
        entity.setZenginOrgChangeBranchId(0); // auto_increment

        return entity;
    }

    // 元データを履歴にする
    private ZenginOrgChangeBranchEntity getOrginalEntity(final Integer tableId, final CheckPrivilegeDto privilegeDto) {

        ZenginOrgChangeBranchEntity entity = zenginOrgChangeBranRepository.findById(tableId).get();
        SetTableDataHistoryUtil.practice(privilegeDto, entity, DataHistoryStatusConstants.UPDATE);

        return entity;
    }
}
