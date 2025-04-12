package mitei.mitei.investigate.report.balance.politician.service.renketsu_koufukin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.RenketsuKofukinDocumentCodeOptionResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.SearchDocumentCodeByOfferingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.logic.renketsu_koufukin.GetBalanceDocumentCodeOptionLogic;
import mitei.mitei.investigate.report.balance.politician.logic.renketsu_koufukin.GetUsageDocumentCodeOptionLogic;

/**
 * 収支報告書・使途報告書文書コードリスト取得Service
 * 
 */
@Service
public class GetBothDocumentCodeOptionService {

    /** 収支報告書文書コードリスト取得 */
    @Autowired
    private GetBalanceDocumentCodeOptionLogic getBalanceDocumentCodeOptionLogic;

    /** 使途報告書文書コードリスト取得 */
    @Autowired
    private GetUsageDocumentCodeOptionLogic getUsageDocumentCodeOptionLogic;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果Dto
     */
    public RenketsuKofukinDocumentCodeOptionResultDto practice(
            final SearchDocumentCodeByOfferingCapsuleDto capsuleDto) {

        RenketsuKofukinDocumentCodeOptionResultDto resultDto = new RenketsuKofukinDocumentCodeOptionResultDto();

        resultDto.setListBalance(getBalanceDocumentCodeOptionLogic.practice(capsuleDto));
        resultDto.setListUsage(getUsageDocumentCodeOptionLogic.practice(capsuleDto));

        return resultDto;
    }

}
