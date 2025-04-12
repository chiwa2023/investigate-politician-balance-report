package mitei.mitei.investigate.report.balance.politician.logic.renketsu_koufukin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplatePagingCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.renketsu_koufukin.RenketsuKoufukinWkTblDto;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblRenketsuKoufukinRepository;

/**
 * 交付金連結ページング検索Logic
 */
@Component
public class SearchRenketsuKoufukinPagingLogic {

    /** 交付金連結結果Repository */
    @Autowired
    private WkTblRenketsuKoufukinRepository wkTblRenketsuKoufukinRepository;

    /** 1回取得件数 */
    private static final int LIMIT = 50;

    /**
     * 処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @param dataKbn    データ区分
     * @return 検索結果
     */
    public RenketsuKoufukinWkTblDto practice(final TemplatePagingCapsuleDto capsuleDto, final Integer dataKbn) {

        RenketsuKoufukinWkTblDto wktblDto = new RenketsuKoufukinWkTblDto();

        Integer userCode = capsuleDto.getCheckPrivilegeDto().getLoginUserCode();

        // 件数が0の時は件数を確認
        final Integer init = 0;
        if (init.equals(capsuleDto.getAllCount())) {
            capsuleDto.setOffset(LIMIT);
            capsuleDto.setAllCount(wkTblRenketsuKoufukinRepository.findCountByDataKbnAndUser(userCode, dataKbn));
        }

        // 検索結果
        wktblDto.setOffset(capsuleDto.getOffset());
        wktblDto.setCountAll(capsuleDto.getAllCount());
        wktblDto.setPageNumber(capsuleDto.getPageNumber());

        Pageable pageable = Pageable.ofSize(capsuleDto.getOffset()).withPage(capsuleDto.getPageNumber());

        wktblDto.setListSuccess(
                wkTblRenketsuKoufukinRepository.findByInsertUserCodeAndDataKbn(userCode, dataKbn, pageable));

        return wktblDto;
    }

}
