package mitei.mitei.investigate.report.balance.politician.service.political_party;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.PoliticalPartyEntity;
import mitei.mitei.investigate.report.balance.politician.repository.PoliticalPartyRepository;

/**
 * 政党選択肢を作成するService
 */
@Service
public class SearchPoliticalPartySelectOptionService {

    /** 政党Repository */
    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    /**
     * 政党選択肢を作成する
     *
     * @param isNoSelect 『指定なし』選択肢の有無
     * @return 選択肢項目
     */
    public List<SelectOptionDto> practice(final boolean isNoSelect) {

        // TODO 取得だけであれば政治団体テーブルから最新かつ団体が政党で取得できるが、
        // 各種業務での政党との紐づきテーブルを失ってもいいかどうか検討中(政治団体との紐づきになる)
        // なしの場合は修正する
        List<PoliticalPartyEntity> listEntity = politicalPartyRepository
                .findBySaishinKbn(DataHistoryStatusConstants.INSERT.value());

        List<SelectOptionDto> list = new ArrayList<>();
        
        // 政党を指定しないを選択肢に加える
        if(isNoSelect) {
            list.add(this.createOption(0, "指定なし"));
        }
        
        for(PoliticalPartyEntity entity : listEntity) {
            list.add(this.createOption(entity.getPoliticalPartyCode(), entity.getPoliticalPartyName()));
        }

        return list;
    }

    private SelectOptionDto createOption(final Integer code ,final String name) {
        
        SelectOptionDto dto = new SelectOptionDto();
        
        dto.setValue(String.valueOf(code));
        dto.setText(name);
        
        return dto;
    }
}
