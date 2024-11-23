package mitei.mitei.investigate.report.balance.politician.logic.storage.y2024;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.storage.SaveFileStorage2024Entity;
import mitei.mitei.investigate.report.balance.politician.repository.storage.SaveFileStorage2024Repository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * SaveStorageFileY2024Logic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class SaveStorageFileY2024LogicTest {

    /** テスト対象 */
    @Autowired
    private SaveStorageFileY2024Logic saveStorageFileY2024Logic;

    /** ファイル保存Repository(2024年) */
    @Autowired
    private SaveFileStorage2024Repository saveFileStorage2024Repository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("truncate_save_file_storage_2024.sql")
    void testPractice() throws Exception {

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();

        // この機能では実際のファイルが存在するか、ファイルの中身は問わず呼び出し情報だけを保存する
        String childDir = "abcdf/ghij";
        String fileName = "zxcv.txt";
        String unixTime = "20221201123456";

        SaveStorageResultDto resultDto = saveStorageFileY2024Logic.practice(privilegeDto, unixTime, childDir, fileName);

        // assertEquals(null, resultDto.getCharset()); // ワークテーブル保存と関係がありません
        assertEquals(childDir, resultDto.getChildDir(),"");
        // assertEquals(null, resultDto.getCompressCount()); // 圧縮展開時にしか使いません
        // assertEquals(null, resultDto.getFailureCount()); // ワークテーブル保存と関係がありません
        assertEquals(fileName, resultDto.getFileName(),"");
        assertEquals(childDir + "/" + fileName, resultDto.getFullPath(),"");
        // assertEquals(null, resultDto.getIsOk()); // 全体の登録結果…というときに使います
        // assertEquals(null, resultDto.getMessage()); // ワークテーブル保存と関係がありません
        assertEquals(unixTime, resultDto.getRegistTimeText(),"");
        // assertEquals(null, resultDto.getShoshouId());テーブル登録時に設定
        // assertEquals(null, resultDto.getShoshouCode());テーブル登録時に設定
        // assertEquals(null, resultDto.getShoshouKbn()); // 未決定
        // assertEquals(null, resultDto.getSuccessCount()); // ワークテーブル保存と関係がありません

        // 空にしたので全件取得すると登録した1件だけが取得できる
        List<SaveFileStorage2024Entity> list = saveFileStorage2024Repository.findAll();
        assertEquals(1, list.size(),"");

        SaveFileStorage2024Entity entity00 = list.get(0);

        assertEquals(entity00.getChildDir(), resultDto.getChildDir(),"");
        assertEquals(entity00.getFileName(), resultDto.getFileName(),"");
        assertEquals(entity00.getRegistTimeText(), resultDto.getRegistTimeText(),"");
        assertEquals(entity00.getSaveFileStorageId(), resultDto.getShoshouId(),"");
        assertEquals(entity00.getSaveFileStorageCode(), resultDto.getShoshouCode(),"");
        assertEquals(entity00.getShoshoKbn(), resultDto.getShoshouKbn(),"");

    }

}
