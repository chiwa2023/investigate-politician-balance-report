package mitei.mitei.investigate.report.balance.politician.service.offering.poli_org;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgBalancesheetReportRepository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書一括処理ワークテーブルデータを編集する
 */
@Service
public class UpdateBalancesheetWkTbService {

    /** 政治資金収支報告書一括処理ワークテーブルRepository */
    @Autowired
    private WkTblPoliOrgBalancesheetReportRepository wkTblPoliOrgBalancesheetReportRepository;

    /**
     * 更新処理を行う
     *
     * @param reportEntity 更新Entity
     * @param privilegeDto 権限確認Dto
     * @return 更新後のId
     */
    public long practice(final WkTblPoliOrgBalancesheetReportEntity reportEntity,
            final CheckPrivilegeDto privilegeDto) {

        // 呼び出し元データを更新にして参照できないようにする
        Optional<WkTblPoliOrgBalancesheetReportEntity> optopnal = wkTblPoliOrgBalancesheetReportRepository
                .findById(reportEntity.getWkTblPoliOrgBalancesheetReportId());

        if (optopnal.isEmpty()) {
            throw new EmptyResultDataAccessException("編集しようとしたデータをIdで呼び出そうとしましたができません", 0);
        }

        WkTblPoliOrgBalancesheetReportEntity originalEntity = optopnal.get();

        SetTableDataHistoryUtil.practice(privilegeDto, originalEntity, DataHistoryStatusConstants.UPDATE);
        wkTblPoliOrgBalancesheetReportRepository.save(originalEntity);

        WkTblPoliOrgBalancesheetReportEntity insertEntity = new WkTblPoliOrgBalancesheetReportEntity();
        BeanUtils.copyProperties(reportEntity, insertEntity);
        SetTableDataHistoryUtil.practice(privilegeDto, insertEntity, DataHistoryStatusConstants.INSERT);
        insertEntity.setWkTblPoliOrgBalancesheetReportId(0L);

        return wkTblPoliOrgBalancesheetReportRepository.save(insertEntity).getWkTblPoliOrgBalancesheetReportId();
    }

}
