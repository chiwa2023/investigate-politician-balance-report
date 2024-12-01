package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliOrgPartyUsageReportRepository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書一括処理ワークテーブルデータを編集する
 */
@Service
public class UpdatePartyUsageWkTbService {

    /** 政治資金収支報告書一括処理ワークテーブルRepository */
    @Autowired
    private WkTblPoliOrgPartyUsageReportRepository wkTblPoliOrgPartyUsageReportRepository;

    /**
     * 更新処理を行う
     *
     * @param reportEntity 更新Entity
     * @param privilegeDto 権限確認Dto
     * @return 更新後のId
     */
    public long practice(final WkTblPoliOrgPartyUsageReportEntity reportEntity,
            final CheckPrivilegeDto privilegeDto) {

        // 呼び出し元データを更新にして参照できないようにする
        Optional<WkTblPoliOrgPartyUsageReportEntity> optopnal = wkTblPoliOrgPartyUsageReportRepository
                .findById(reportEntity.getWkTblPoliOrgPartyUsageReportId());

        if (optopnal.isEmpty()) {
            throw new EmptyResultDataAccessException("編集しようとしたデータをIdで呼び出そうとしましたができません", 0);
        }

        WkTblPoliOrgPartyUsageReportEntity originalEntity = optopnal.get();

        SetTableDataHistoryUtil.practice(privilegeDto, originalEntity, DataHistoryStatusConstants.UPDATE);
        wkTblPoliOrgPartyUsageReportRepository.save(originalEntity);

        WkTblPoliOrgPartyUsageReportEntity insertEntity = new WkTblPoliOrgPartyUsageReportEntity();
        BeanUtils.copyProperties(reportEntity, insertEntity);
        SetTableDataHistoryUtil.practice(privilegeDto, insertEntity, DataHistoryStatusConstants.INSERT);
        insertEntity.setWkTblPoliOrgPartyUsageReportId(0L);

        return wkTblPoliOrgPartyUsageReportRepository.save(insertEntity).getWkTblPoliOrgPartyUsageReportId();
    }

}
