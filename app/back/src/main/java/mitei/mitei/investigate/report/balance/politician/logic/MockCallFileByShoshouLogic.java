package mitei.mitei.investigate.report.balance.politician.logic;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.constants.GetThisResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.storage.OneFileBlobDto;

/**
 * 保存書証からファイルを読み込むMockLogic
 */
@Component
public class MockCallFileByShoshouLogic {

    /** 本番で使用する(多分)ロジック */
    @Autowired
    private CallFileByShoshouLogic callFileByShoshouLogic;

    /**
     * 常に固定のファイルを送信Dto形式で呼び出す
     *
     * @return ファイルを送信Dto
     * @throws IOException ファイルが読み込めなかった例外
     */
    public OneFileBlobDto practice() throws IOException {

        return callFileByShoshouLogic.practice(GetThisResourcePath.getBackTestResourcePath(), "file", "src.md");
    }

}
