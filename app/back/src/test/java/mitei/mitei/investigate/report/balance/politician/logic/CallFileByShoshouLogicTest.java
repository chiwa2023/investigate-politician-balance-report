package mitei.mitei.investigate.report.balance.politician.logic;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.investigate.report.balance.politician.constants.GetThisResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.storage.OneFileBlobDto;

/**
 * CallFileByShoshouLogic単体テスト
 */
@SpringJUnitConfig
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CallFileByShoshouLogicTest {

    /** テスト対象 */
    @Autowired
    private CallFileByShoshouLogic callFileByShoshouLogic;
    
    @Test
    @Tag("TableTruncate")
    void testPractice()throws Exception {

        final  String fileName = "src.md";
        OneFileBlobDto oneFileBlobDto = callFileByShoshouLogic.practice(GetThisResourcePath.getBackTestResourcePath(), "file", fileName);
        
        assertThat(oneFileBlobDto.getFileName()).isEqualTo(fileName);
        
        // 読み取り対象(src.md)をDtoバイナリ配列(binary[])のフィールドでfront側に持ち込むとJSON内にBase64文字列で変換されて送信される
        // この送信されたBase64文字列をそのまま保存したのが比較対象のsrcmd_base64.txt
        // Java側binary[] → (spring bootがBase64変換) → なのでfront側DtoをStringにしておけばBackで変換しなくても受け取れるが
        // なんとなくfrontとbackで型が全然異なるのがしっくりこないのでBack側で変換した
        Path path = Paths.get(GetThisResourcePath.getBackTestResourcePath(), "file/srcmd_base64.txt");
        String base64Text = Files.readString(path);
        
        assertThat(oneFileBlobDto.getFileContentBase64()).isEqualTo(base64Text);
    }

}
