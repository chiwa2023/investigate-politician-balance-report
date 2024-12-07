package mitei.mitei.investigate.report.balance.politician.service.political.organization;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PoliticalOrganizationLeastDto;
import mitei.mitei.investigate.report.balance.politician.dto.political.organization.SearchPoliticalOrganizationLeastCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalOrganizationEntity;
import mitei.mitei.investigate.report.balance.politician.logic.natural_search.CreateSerachWordsBooleanModeLogic;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalOrganizationRepository;

/**
 * 政治団体検索を行う
 */
@Service
public class SearchPoliticalOrganizationLeastService {

    /** 政治団体Repository */
    @Autowired
    private PoliticalOrganizationRepository politicalOrganizationRepository;

    /** BooleanMode用検索語変換 */
    @Autowired
    private CreateSerachWordsBooleanModeLogic createSerachWordsBooleanModeLogic;

    /**
     * 検索処理を行う
     *
     * @param capsuleDto 検索条件Dto
     * @return 検索結果リスト
     */
    public List<PoliticalOrganizationLeastDto> practice(final SearchPoliticalOrganizationLeastCapsuleDto capsuleDto) {

        String searchWords = createSerachWordsBooleanModeLogic.practice(capsuleDto.getSearchWords());
        
        List<PoliticalOrganizationEntity> listEntity;
        if (capsuleDto.getIsHisory()) {
            // 履歴データもあわせて検索する
            listEntity = politicalOrganizationRepository.findFullTextPlushistory(searchWords);
        } else {
            listEntity = politicalOrganizationRepository.findFullText(searchWords);
        }

        // Dtoに変換
        List<PoliticalOrganizationLeastDto> list = new ArrayList<>();
        for (PoliticalOrganizationEntity entity : listEntity) {
            list.add(this.createLeastDto(entity));
        }

        return list;
    }

    private PoliticalOrganizationLeastDto createLeastDto(final PoliticalOrganizationEntity entity) {
        PoliticalOrganizationLeastDto dto = new PoliticalOrganizationLeastDto();

        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

}
