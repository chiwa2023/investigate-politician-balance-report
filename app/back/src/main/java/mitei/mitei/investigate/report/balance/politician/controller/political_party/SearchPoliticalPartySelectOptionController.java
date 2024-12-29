package mitei.mitei.investigate.report.balance.politician.controller.political_party;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;
import mitei.mitei.investigate.report.balance.politician.service.political_party.SearchPoliticalPartySelectOptionService;

/**
 * 政党選択肢取得Controller
 */
@Controller
@RequestMapping("/political-party")
public class SearchPoliticalPartySelectOptionController {

    /** 政党選択肢取得Service */
    @Autowired
    private SearchPoliticalPartySelectOptionService searchPoliticalPartySelectOptionService;

    /**
     * 処理を行う
     *
     * @param isNoSelect 指定なしを追加有無
     * @return 選択肢リスト
     */
    @GetMapping("/get-select-option")
    public ResponseEntity<List<SelectOptionDto>> practice(final @RequestParam(name = "isNoSelect") boolean isNoSelect) {
        
        /* ここに固有のビジネス処理を記載する */

        return ResponseEntity.ok(searchPoliticalPartySelectOptionService.practice(isNoSelect));
    }

}
