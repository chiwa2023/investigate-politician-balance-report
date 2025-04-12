package mitei.mitei.investigate.report.balance.politician.service.renketsu_koufukin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplatePagingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.RenketsuKoufukinFailureDto;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUsageOutcomeRepository;

/**
 * 交付金連結支出紐づけ失敗データ取得Service
 */
@Service
public class SearchRenketsuOutcomeFailureService {

    /** 1回取得件数 */
    private static final int LIMIT = 50;

    /** 使途報告書支出Repository */
    @Autowired
    private WkTblUsageOutcomeRepository wkTblUsageOutcomeRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果
     */
    public RenketsuKoufukinFailureDto practice(final TemplatePagingCapsuleDto capsuleDto) {
        
        Integer userCode = capsuleDto.getCheckPrivilegeDto().getLoginUserCode();

        // 件数が0の時は件数を確認
        final Integer init = 0;
        if (init.equals(capsuleDto.getAllCount())) {
            capsuleDto.setOffset(LIMIT);
            capsuleDto.setAllCount(wkTblUsageOutcomeRepository.findCountFailureRenketsuByUserCode(userCode));
        }

        RenketsuKoufukinFailureDto failureDto = new RenketsuKoufukinFailureDto();
        
        // 検索結果
        failureDto.setOffset(capsuleDto.getOffset());
        failureDto.setCountAll(capsuleDto.getAllCount());
        failureDto.setPageNumber(capsuleDto.getPageNumber());

        Pageable pageable = Pageable.ofSize(capsuleDto.getOffset()).withPage(capsuleDto.getPageNumber());

        failureDto.setListFailure(
                wkTblUsageOutcomeRepository.findFailureRenketsuByUserCode(userCode, pageable));

        return failureDto;
    }
    
}
