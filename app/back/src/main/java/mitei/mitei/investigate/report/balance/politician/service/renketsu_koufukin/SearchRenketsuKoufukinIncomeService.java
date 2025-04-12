package mitei.mitei.investigate.report.balance.politician.service.renketsu_koufukin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.constants.RenketsuKoufukinConstants;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.RenketsuKoufukinIncomeDto;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblRenketsuKoufukinRepository;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUsageIncomeRepository;

/**
 * 交付金連結収入データを取得する 収入データは業務上の性質から、一般的に少ないのでページングなし
 */
@Service
public class SearchRenketsuKoufukinIncomeService {

    /** 交付金連結結果Repository */
    @Autowired
    private WkTblRenketsuKoufukinRepository wkTblRenketsuKoufukinRepository;

    /** 使途報告書収入Repository */
    @Autowired
    private WkTblUsageIncomeRepository wkTblUsageIncomeRepository;

    /**
     * 処理を行う
     *
     * @param userCode ユーザ同一識別コード
     * @return 検索結果
     */
    public RenketsuKoufukinIncomeDto practice(final Integer userCode) {

        RenketsuKoufukinIncomeDto incomeDto = new RenketsuKoufukinIncomeDto();
        incomeDto.setListSuccess(wkTblRenketsuKoufukinRepository.findByInsertUserCodeAndDataKbn(userCode,
                RenketsuKoufukinConstants.INCOME));
        incomeDto.setListDuplicate(wkTblRenketsuKoufukinRepository.findByInsertUserCodeAndDataKbn(userCode,
                RenketsuKoufukinConstants.INCOME_DUPLICATE));
        incomeDto.setListFailure(wkTblUsageIncomeRepository.findFailureRenketsuByUserCode(userCode));

        return incomeDto;
    }

}
