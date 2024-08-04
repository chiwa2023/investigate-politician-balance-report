package mitei.mitei.investigate.report.balance.politician.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.investigate.report.balance.politician.dto.storage.OneFileBlobDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.logic.MockCallFileByShoshouLogic;

/**
 * TODO フレームワークに沿った実装をbackブランチで行って上書き
 */
@RestController
public class GetFileBinaryController {

    /** ファイル呼び出しMockLogic */
    @Autowired
    private MockCallFileByShoshouLogic callFileByShoshouLogic;
    
    
    /**
     * 保存済書証Dtoから実際のファイルをバイナリで呼び出す
     *
     * @param saveStorageResultDto 保存済書証Dto
     * @return バイナリファイル内容Dto
     * @throws IOException ファイルの呼び出しに失敗　
     */
    @PostMapping("/get-file-binary")
    public ResponseEntity<OneFileBlobDto> practice(@RequestBody final SaveStorageResultDto saveStorageResultDto)
            throws IOException {
        
        return ResponseEntity.ok(callFileByShoshouLogic.practice());
    }
}
