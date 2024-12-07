package mitei.mitei.investigate.report.balance.politician.service.offering.poli_party;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblPoliPartyUsageReportRepository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書一括処理ワークテーブルデータを編集する
 */
@Service
public class UpdatePartyUsageWkTbService {

    /** 政治資金収支報告書一括処理ワークテーブルRepository */
    @Autowired
    private WkTblPoliPartyUsageReportRepository wkTblPoliPartyUsageReportRepository;

    /**
     * 更新処理を行う
     *
     * @param reportEntity 更新Entity
     * @param privilegeDto 権限確認Dto
     * @return 更新後のId
     */
    public long practice(final WkTblPoliPartyUsageReportEntity reportEntity,
            final CheckPrivilegeDto privilegeDto) {

        // 呼び出し元データを更新にして参照できないようにする
        Optional<WkTblPoliPartyUsageReportEntity> optopnal = wkTblPoliPartyUsageReportRepository
                .findById(reportEntity.getWkTblPoliPartyUsageReportId());

        if (optopnal.isEmpty()) {
            throw new EmptyResultDataAccessException("編集しようとしたデータをIdで呼び出そうとしましたができません", 0);
        }

        WkTblPoliPartyUsageReportEntity originalEntity = optopnal.get();

        SetTableDataHistoryUtil.practice(privilegeDto, originalEntity, DataHistoryStatusConstants.UPDATE);
        wkTblPoliPartyUsageReportRepository.save(originalEntity);

        WkTblPoliPartyUsageReportEntity insertEntity = new WkTblPoliPartyUsageReportEntity();
        BeanUtils.copyProperties(reportEntity, insertEntity);
        SetTableDataHistoryUtil.practice(privilegeDto, insertEntity, DataHistoryStatusConstants.INSERT);
        insertEntity.setWkTblPoliPartyUsageReportId(0L);

        return wkTblPoliPartyUsageReportRepository.save(insertEntity).getWkTblPoliPartyUsageReportId();
    }

}
