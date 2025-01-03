package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.ZenginOrgChangeKbnConstants;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgChangeBranchRepository;

/**
 * 全銀変更テーブル他伝送用データ取得Logic
 */
@Component
public class TransferChageListGetLogic {

    /** 全銀変更テーブルRepository */
    @Autowired
    private ZenginOrgChangeBranchRepository zenginOrgChangeBranchRepository;

    /**
     * 処理を実行する
     *
     * @return 検索結果
     */
    public List<ZenginOrgChangeBranchEntity> practice() {

        List<ZenginOrgChangeBranchEntity> listChange = new ArrayList<>();
        Pageable pageable = Pageable.unpaged();
        List<ZenginOrgChangeBranchEntity> list1 = zenginOrgChangeBranchRepository
                .findByChangeKbnAndIsFinishedAndSaishinKbn(ZenginOrgChangeKbnConstants.ADD, false,
                        DataHistoryStatusConstants.INSERT.value(), pageable)
                .toList();

        listChange.addAll(list1);

        List<ZenginOrgChangeBranchEntity> list2 = zenginOrgChangeBranchRepository
                .findByChangeKbnAndIsFinishedAndSaishinKbn(ZenginOrgChangeKbnConstants.MOVE, false,
                        DataHistoryStatusConstants.INSERT.value(), pageable)
                .toList();

        listChange.addAll(list2);

        List<ZenginOrgChangeBranchEntity> list3 = zenginOrgChangeBranchRepository
                .findByChangeKbnAndIsFinishedAndSaishinKbnAndZenginOrgMoveIdNotAndZenginOrgMoveCodeNotAndZenginOrgMoveNameNot(
                        ZenginOrgChangeKbnConstants.DELETE, false, DataHistoryStatusConstants.INSERT.value(), 0, 0, "",
                        pageable)
                .toList();

        listChange.addAll(list3);

        return listChange;
    }
}
