package mitei.mitei.investigate.report.balance.politician.service.zengin_org;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.zengin_org_change.SearchZenginChangeConditionCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import mitei.mitei.investigate.report.balance.politician.repository.ZenginOrgChangeBranchRepository;

/**
 * 全銀l金融機関支店異動テーブルを検索する
 */
@Service
public class SearchZenginOrgChangeService {

    /** 全銀金融機関支店異動repository */
    @Autowired
    private ZenginOrgChangeBranchRepository zenginOrgChangeBranchRepository;

    /**
     * 検索条件に基づき金融機関支店を取得する
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    public List<ZenginOrgChangeBranchEntity> practice(final SearchZenginChangeConditionCapsuleDto capsuleDto) {
        final String notCodeInput = "";
        if (capsuleDto.isMishoriOnly()) {
            return zenginOrgChangeBranchRepository.findByIsFinishedAndSaishinKbn(false,
                    DataHistoryStatusConstants.INSERT.value());
        } else {
            if (notCodeInput.equals(capsuleDto.getFinancialCode())) {
                
                return zenginOrgChangeBranchRepository.findByChangeKbnInAndSaishinKbnAndInsertTimestampBetween(
                        capsuleDto.getListChangeKbn(), DataHistoryStatusConstants.INSERT.value(),
                        capsuleDto.getStartDate().atStartOfDay(), // 改行よけ 
                        capsuleDto.getEndDate().atTime(23, 59, 59)); // SUPPRESS CHECKSTYLE MagicNumber
            } else {

                return zenginOrgChangeBranchRepository
                        .findByChangeKbnInAndSaishinKbnAndInsertTimestampBetweenAndOrgCode(
                                capsuleDto.getListChangeKbn(), DataHistoryStatusConstants.INSERT.value(),
                                capsuleDto.getStartDate().atStartOfDay(), // 改行よけ
                                capsuleDto.getEndDate().atTime(23, 59, 59), // SUPPRESS CHECKSTYLE MagicNumber
                                capsuleDto.getFinancialCode());
            }

        }

    }

}
