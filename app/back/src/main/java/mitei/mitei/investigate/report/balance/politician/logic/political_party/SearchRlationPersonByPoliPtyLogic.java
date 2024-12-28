package mitei.mitei.investigate.report.balance.politician.logic.political_party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.poli_party.search.SearchPoliPartyRelationPersonResultDto;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalPartyRelationPersonRepository;

/**
 * 検索条件とした政党
 */
@Component
public class SearchRlationPersonByPoliPtyLogic {

    /** 一検索あたりの取得数(100) */
    private static final int SEARCH_LIMIT = 100;
    
    /** 政党関連者紐づけテーブル */
    @Autowired
    private PoliticalPartyRelationPersonRepository politicalPartyRelationPersonRepository;
    
    /**
     * 検索処理を行う
     *
     * @param poliPartyCode 政党同一識別コード
     * @param pageNumber ページ番号
     * @return 検索結果
     */
    public SearchPoliPartyRelationPersonResultDto practice(final Integer poliPartyCode,final int pageNumber) {
        
        SearchPoliPartyRelationPersonResultDto resultDto = new SearchPoliPartyRelationPersonResultDto();
        
        resultDto.setCountAll(politicalPartyRelationPersonRepository.countSaishin(poliPartyCode));

        Pageable page = Pageable.ofSize(SEARCH_LIMIT).withPage(pageNumber);

        resultDto.setPosPage(page.getPageNumber());
        
        resultDto.setListPerson(politicalPartyRelationPersonRepository.findByPoliticalPartyCodeAndSaishinKbn(poliPartyCode, 1, page));
        
        return resultDto;
    }
    
}
