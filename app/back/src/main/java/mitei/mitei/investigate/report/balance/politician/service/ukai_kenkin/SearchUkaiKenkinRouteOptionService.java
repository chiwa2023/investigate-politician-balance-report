package mitei.mitei.investigate.report.balance.politician.service.ukai_kenkin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.ukai_kenkin.CreateUkaiKenkinRouteSelectOptionLogic;

/**
 * 経路別選択肢作成Searvice
 */
@Service
public class SearchUkaiKenkinRouteOptionService {

    /** 経路別選択肢作成Logic */
    @Autowired
    private CreateUkaiKenkinRouteSelectOptionLogic createUkaiKenkinRouteSelectOptionLogic;

    /**
     * 処理を行う
     *
     * @param userCode 操作ユーザ同一識別コード
     * @return 選択肢
     */
    public List<SelectOptionDto> practice(final Integer userCode) {

        return createUkaiKenkinRouteSelectOptionLogic.practice(userCode);
    }

}
