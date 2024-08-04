package mitei.mitei.investigate.report.balance.politician.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.stereotype.Component;

import mitei.mitei.investigate.report.balance.politician.dto.storage.OneFileBlobDto;

/**
 * ファイルをバイナリで呼び出して送信Dtoに格納する
 */
@Component
public class CallFileByShoshouLogic {

    /**
     * ファイル名を格納し、ファイルをbinary配列で格納しBase64文字列に変換する
     *
     * @param shoshouId 書証Id(サブディレクトリ)
     * @param dirParent 書証を保存する全体ディレクトリ
     * @param fileName  ファイル名
     * @return ファイル送信Dto
     * @throws IOException ファイル読み取りができなかった場合
     */
    public OneFileBlobDto practice(final String dirParent, final String shoshouId, final String fileName)
            throws IOException {

        Path path = Paths.get(dirParent.toString(), shoshouId, fileName);

        OneFileBlobDto oneFileBlobDto = new OneFileBlobDto();

        oneFileBlobDto.setFileName(path.getFileName().toString());
        oneFileBlobDto.setFileContentBase64(Base64.getEncoder().encodeToString(Files.readAllBytes(path)));

        return oneFileBlobDto;
    }
}
