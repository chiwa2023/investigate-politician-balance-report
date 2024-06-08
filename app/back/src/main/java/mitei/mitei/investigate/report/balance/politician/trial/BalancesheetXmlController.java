package mitei.mitei.investigate.report.balance.politician.trial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.create.report.balance.politician.dto.publish.AllBookDto;

/**
 * (仮)政治資金収支報告書Dtoを返却するController
 */
@RestController
public class BalancesheetXmlController {

    /** テスト用MockDto取得ロジック */
    @Autowired
    private MockGetAllBookDtoLogic mockGetAllBookDtoLogic;
    
    /**
     * 政治資金収支報告書Dtoを返却する
     *
     * @return 政治資金収支報告書Dto
     */
    @GetMapping("/convert-balancesheet-xml")
    public AllBookDto trialApiConnection() {

        return mockGetAllBookDtoLogic.practice();
    }

}
