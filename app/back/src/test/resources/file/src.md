# マークダウンサンプル(中身はすべて適当で意味はない)

なんか適当な文章
なんか適当な文章

## JPA

### テーブル詳細

|      物理名       |                 論理名                 |                                  説明                                   |                             初期値                              |
| ----------------- | -------------------------------------- | ----------------------------------------------------------------------- | --------------------------------------------------------------- |
| pathOut           | 出力先                                 | 各種データを解析し作成したJavaファイルの出力フォルダパス                | c:\temp                                                         |
| basePackage       | 生成するjavaファイルのpackage          | 共通package                                                             | c:\temp                                                         |
| urlSql            | CREATE TABLE文格納ファイル             | CREATE TABLE文が格納されたテキストファイルパス※1                       | show_create.txt※1この設定を使用するときは※2は使用できません。 |
| urlDbConn         | データベース接続設定                   | データベース接続をする際のユーザ名などの格納するテキストファイルパス※2 | 設定なし※2この設定を使用するときは※1は使用できません。        |
| connTable         | 解析テーブル名                         | 解析対象のTable名※2                                                    | 設定なし※2この設定を使用するときは※1は使用できません。        |
| urlEntity         | Repositoryテンプレート                 | JPA Repositoryを作成する際のテンプレートファイルパス                    | jpaType/EntityTemplate.java                                     |
| urlRepository     | Entityテンプレート                     | JPA Entityを作成する際のテンプレートファイルパス                        | jpaType/RepositoryTemplate.java                                 |
| urlKeyEntiy       | KeyEntityテンプレート                  | JPA KeyEntityを作成する際のテンプレートファイルパス                     | jpaType/KeyTemplate.java                                        |
| urlCondition      | SearchConditionDtoテンプレート         | JPA SearchConditionDtoを作成する際のテンプレートファイルパス            | jpaType/SearchDtoTemplate.java                                  |
| urlRepositoryTest | RepositoryUnitTestテンプレート         | JPA Repositoryを作成する際のテンプレートファイルパス                    | jpaType/EntityTemplate.java                                     |
| urlEntiy          | EntityUnitTestテンプレート             | JPA Repositoryを作成する際のテンプレートファイルパス                    | jpaType/RepositoryTemplate.java                                 |
| urlKeyEntiy       | KeyEntityUnitTestテンプレート          | JPA Repositoryを作成する際のテンプレートファイルパス                    | jpaType/KeyTemplate.java                                        |
| urlConditionTest  | SearchConditionDtoUnitTestテンプレート | JPA Repositoryを作成する際のテンプレートファイルパス                    | jpaType/SearchDtoTemplate.java                                  |

### テーブル詳細2

|       変数        | 数字 |    型     |                              パス                               |
| ----------------- | ---- | --------- | --------------------------------------------------------------- |
| pathOut           | 10   | String    | c:\temp                                                         |
| basePackage       | 5    | Integer   | c:\temp                                                         |
| urlSql            | 7    | Timedamp  | show_create.txt※1この設定を使用するときは※2は使用できません。 |
| urlDbConn         | 3    | String    | 設定なし※2この設定を使用するときは※1は使用できません。        |
| connTable         | 800  | Date      | 設定なし※2この設定を使用するときは※1は使用できません。        |
| urlEntity         | 25   | Long      | jpaType/EntityTemplate.java                                     |
| urlRepository     | 66   | LocalDate | jpaType/RepositoryTemplate.java                                 |
| urlKeyEntiy       | 73   | String    | jpaType/KeyTemplate.java                                        |
| urlCondition      | 99   | String    | jpaType/SearchDtoTemplate.java                                  |
| urlRepositoryTest | 104  | String    | jpaType/EntityTemplate.java                                     |
| urlEntiy          | 39   | String    | jpaType/RepositoryTemplate.java                                 |
| urlKeyEntiy       | 41   | String    | jpaType/KeyTemplate.java                                        |
| urlConditionTest  | 66   | String    | jpaType/SearchDtoTemplate.java                                  |

なんか適当な文章
なんか適当な文章

## 一般DTOタイプ

(別表2) データベースのカラムに関するデータ(ColumnPropertyDto)
|      物理名      |          論理名          |                        説明                         |    型     |                      関係性                       |
| ---------------- | ------------------------ | --------------------------------------------------- | --------- | ------------------------------------------------- |
| columnName       | データベースカラム名     | データベースのテーブルカラム名(ex.last_update)      | String    | 解析テーブル名.個別情報.台帳100.終了期限          |
| fieldName        | Javaフィールド名         | テーブルカラム名のキャメルパターン(ex.lastUpdate)   | Integer   | 主キー該当.共通情報.台帳201.処理日                |
| methodName       | Javaメソッド名           | フィールド名の始まり大文字(ex.LastUpdate)           | LocalDate | 未定情報.台帳403.メールアドレス                   |
| dataTypeDatabase | データベースデータタイプ | テーブル格納データタイプ(ex.timestamp)              | Short     | 個別情報.台帳506.支払金額                         |
| dataType         | Javaデータタイプ         | Javaに格納するときのクラス(ex.(java.sql.)Timestamp) | Integer   | 共通情報.台帳708.処理日                           |
| isKey            | 主キー該当               | 主キーを構成する場合は`true`                        | String    | KeyEntityテンプレート.未定情報.台帳908.フィールド |
| isNull           | null値許容               | null値を許容する場合は`true`                        | Date      | 個別情報.台帳302.文書番号                         |
| comment          | 注釈                     | データベースカラムに記載されているコメント          | String    | 共通情報.台帳100.姓                               |
| digitNum         | 桁数                     | データ格納桁数                                      | Integer   | 未定情報.台帳888.フィールド                       |

なんか適当な文章
なんか適当な文章
なんか適当な文章
なんか適当な文章

## 付記.MySQL対応カラム

|      データ型       | 対応  |   Java型   |
| ------------------- | :---: | ---------- |
| INT                 |   -   | Integer    |
| VARCHAR()           |   -   | String     |
| DECIMAL()           |   -   | Integer    |
| DATETIME            |   -   | LocaleDate |
| BLOB                |   -   | -          |
| BINARY\(\)          |   -   | Byte\[\]   |
| BLOB\(\)            |   -   | -          |
| LONGBLOB            |   -   | -          |
| MEDIUMBLOB          |   -   | -          |
| TINYBLOB            |   -   | -          |
| VARBINARY           |   -   | -          |
| DATE                |   -   | Date       |
| DATETIME()          |   -   | LocalDate  |
| TIME()              |   -   | Time       |
| TIMESTAMP()         |   -   | Timestamp  |
| YEAR                |   -   | Integer    |
| GEOMETRY            |   -   | Byte\[\]   |
| GEOMETORYCOLLECTION |   -   | -          |
| LINESTRING          |   -   | String     |
| MULTILINESTIRNG     |   -   | String     |
| MULTIPOINT          |   -   | -          |
| MULTIPOLYGON        |   -   | -          |
| POINT               |   -   | -          |
| POLYGON             |   -   | -          |
| BIGINT              |   -   | Long       |
| DECIMAL             |   -   | BigDecimal |
| DOUBLE              |   -   | Double     |
| FLOAT               |   -   | Float      |
| INT()               |   -   | Integer    |
| MEDIUMINT()         |   -   | Integer    |
| REAL                |   -   | -          |
| SMALLINT()          |   -   | Short      |
| TINYINT()           |   -   | Byte       |
| CHAR()              |   -   | String     |
| JSON()              |   -   | String     |
| NCHAR()             |   -   | String     |
| NVARCHAR()          |   -   | String     |
| LONGTEXT            |   -   | String     |
| MEDIUMTEXT          |   -   | String     |
| TEXT()              |   -   | String     |
| TINYTEXT            |   -   | String     |
| BIT()               |   -   | -          |
| BOOLEAN             |   -   | Boolean    |
| ENUM()              |   -   | -          |
| SET()               |   -   | -          |

## 付記2.関係性リスト

| 検索対象 |     置換語      |
| -------- | --------------- |
| 台帳100  | vat100Daicho    |
| 台帳201  | vat100Dai       |
| 台帳302  | vat100Daicho    |
| 台帳403  | vat100Dai       |
| 台帳504  | vat100Daicho    |
| 台帳605  | vat100Dai       |
| 台帳706  | vat100Daicho    |
| 台帳807  | vat100Dai       |
| 台帳908  | vat100Daicho    |
| 個別情報 | personalInfo    |
| 共通情報 | infoCommonDto   |
| 処理日   | shoriBi         |
| 姓       | firstName       |
| 文書番号 | bunshoNo        |
| 支払金額 | shiharaiKingaku |

なんか適当な文章
なんか適当な文章

## 全部左寄せ

| データ型 | 対応 | Java型  |
| :------- | :--- | :------ |
| timestamp      | -    | LocalDate |

## 全部右寄せ

| データ型 | 対応 | Java型  |
| -------: | ---: | ------: |
|      INT |    - | Integer |

## 全部中央揃え

| データ型 | 対応  | Java型  |
| :------: | :---: | :-----: |
|   bigint    |   -   | Long |

## 両端ボーダー省略

 データ型 | 対応 | Java型
 -------- | ---- | -------
 INT      | -    | Integer

## ヘッダ部とデータ部の仕切りがフォーマット通りでない

| データ型 | 対応  | Java型  |
| :------:| :---| :-----:|
|   bigint    |   -   | Long |


おまけの解説

なんか適当な文章
なんか適当な文章
