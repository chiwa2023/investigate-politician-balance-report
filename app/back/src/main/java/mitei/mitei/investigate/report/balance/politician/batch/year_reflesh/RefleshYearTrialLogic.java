package mitei.mitei.investigate.report.balance.politician.batch.year_reflesh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;

/**
 * 試運転用仮の複写ロジック
 */
public class RefleshYearTrialLogic {

    /** 複写元となる年 */
    private String baseYear;
    /** 複写先となる年 */
    private String copyYear;

    /** キーワード@Tableを探すための検索キー */
    private static final String KEY_TABLE = "@Table(name = \"";

    /** 複写先取得用相対ディレクトリ */
    private static final String copyPath = "main/resources/SQL/DDL";

    /** 複写先絶対ディレクトリ */
    private String copyAbsPath;

    /**
     * 複写作業を行う
     *
     * @param baseYear 複写元の年
     * @param copyYear 複写先の年
     * @throws IOException ファイル作成時の例外全般
     */
    public void practice(final String baseYear, final String copyYear) throws IOException {

        this.baseYear = baseYear;
        this.copyYear = copyYear;
        this.copyAbsPath = GetCurrentResourcePath.getBackSrcPath(copyPath);

        // テーブル名検索のためにEntityに入る
        final String pathSearchUsage = "main/java/mitei/mitei/investigate/report/balance/politician/entity/poli_party/usage/y";
        final String pathSearchBalancesheet = "main/java/mitei/mitei/investigate/report/balance/politician/entity/poli_org/balancesheet/y";

        // テーブル名リストを作成する
        List<String> listTablename = new ArrayList<>();
        listTablename.addAll(this.createTableList(pathSearchUsage + baseYear));
        listTablename.addAll(this.createTableList(pathSearchBalancesheet + baseYear));

        for (String tablename : listTablename) {
            this.copyFile(tablename);
        }
    }

    private List<String> createTableList(final String pathSearch) throws IOException {

        String searchAbsPath = GetCurrentResourcePath.getBackSrcPath(pathSearch);

        List<String> listTablename = new ArrayList<>();

        // Entityフォルダをループ
        List<Path> listEntity = Files.list(Paths.get(searchAbsPath)).toList();
        for (Path entry : listEntity) {
            listTablename.add(this.pickupTableInFile(entry));
        }

        return listTablename;
    }

    private String pickupTableInFile(final Path entry) throws IOException {

        // Entityクラスソースを行単位で開く
        List<String> list = Files.readAllLines(entry);

        for (String line : list) {
            if (line.startsWith(KEY_TABLE)) {

                int posStart = KEY_TABLE.length();
                int posEnd = line.indexOf("\"", posStart + 1);
                return line.substring(posStart, posEnd);
            }
        }

        throw new IllegalArgumentException(KEY_TABLE + "で開始する行が見つからず、テーブルが特定できませんでした");
    }

    private void copyFile(final String tablename) throws IOException {

        Path entry = Paths.get(copyAbsPath, tablename.toLowerCase(Locale.JAPANESE) + ".sql");
        // 内容はbaseYearをcopyYearで全置換
        String baseContent = Files.readString(entry);
        String copyContent = baseContent.replaceAll(baseYear, copyYear);

        // ファイル名も同様
        String fileName = entry.getFileName().toString().replaceAll(baseYear, copyYear);

        Files.writeString(Paths.get(copyAbsPath, fileName), copyContent);

    }
}
