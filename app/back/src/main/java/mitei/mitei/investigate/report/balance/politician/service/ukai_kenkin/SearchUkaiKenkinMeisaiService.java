package mitei.mitei.investigate.report.balance.politician.service.ukai_kenkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinDetaillResultDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.ukai_kenkin.UkaiKenkinSearchCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.repository.WkTblUkaiKenkinRepository;

/**
 * 迂回献金明細検索Service
 */
@Service
public class SearchUkaiKenkinMeisaiService {

    /** 迂回献金明細Service */
    @Autowired
    private WkTblUkaiKenkinRepository wkTblUkaiKenkinRepository;

    /**
     * 処理を行う
     *
     * @param capsuleDto 迂回献金検索Dto
     * @return 検索結果
     */
    public UkaiKenkinDetaillResultDto practice(final UkaiKenkinSearchCapsuleDto capsuleDto) {

        UkaiKenkinDetaillResultDto resultDto = new UkaiKenkinDetaillResultDto();
        Integer userCode = capsuleDto.getCheckPrivilegeDto().getLoginUserCode();
        Pageable pageable = Pageable.ofSize(capsuleDto.getLimit()).withPage(capsuleDto.getPageNumber());

        resultDto.setListDetailEntity(wkTblUkaiKenkinRepository
                .findByInsertUserCodeOrderByPoliticalOrgCodeAscPickupStageAsc(userCode, pageable));

        // 全件数が未設定の時は設定する
        if (resultDto.getCountAll() == 0) {
            resultDto.setCountAll(wkTblUkaiKenkinRepository.findCount(userCode));
        }

        resultDto.setLimit(capsuleDto.getLimit());
        resultDto.setOffset(capsuleDto.getPageNumber());

        return resultDto;
    }

}
