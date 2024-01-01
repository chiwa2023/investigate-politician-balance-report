package mitei.mitei.investigate.report.balance.politician.trial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.investigate.report.balance.politician.dto.SelectOptionDto;

/**
 * API接続確認Controller
 */
@RestController
public class TrialApiConnectionRestController {

    /** リスト生成  */
    @Autowired
    private MakeTrialTestDataLogic makeTrialTestDataLogic;

    /**
     * ダミーのオプション項目リストを返却
     *
     * @return オプション項目リスト
     */
    @GetMapping("/trial-api-rest")
    public List<SelectOptionDto> trialApiConnection() {

        return makeTrialTestDataLogic.practice();
    }
}
