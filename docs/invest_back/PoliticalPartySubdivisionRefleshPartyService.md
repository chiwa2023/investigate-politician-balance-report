# PoliticalPartySubdivisionRefleshPartyService設計書

## package

未定

## 目的

政党支部情報内の該当正常情報をを更新すること

## extends,implements

(パッケージ未定)TemplatePoliticianBalancesheetService

## メソッド

### 1. practice

- 引数:ビジネス処理に必要な情報Dto
- 返り値：正常に処理された結果`true`を返却すること。ロールバック処理のため、異常な場合は例外をControllerに投げること

## Serviceにおける処理

- 指定された政党Id,政党同一識別コードかつ最新区分が`1:最新`に該当するデータリストを抽出すること
- 抽出された該当するデータを、すべて最新区分を`0:最新ではない`とマークすること
- データリストに編集後の政党Id、政党同一識別コード、新しいid:PRIMARY KEYを自動で振番するよう`0`を設定すること
- 変更対象データにの最新区分を`1:最新`である、と実装で明示的にマークすること。
- 抽出された編集されたデータを新規登録すること

- ログ記録Dtoである`LogContentDto`を呼び出し編集内容を設定すること
- 政党データが変更されたことを`super.writeLog(logContentDto)`を呼び出し、ログを記録すること
- Controllerに処理した結果：正常を返却すること。
- 異常が発生した場合は例外を投げてControllerのメソッドに付与した`@Transactional`でロールバック処理を行うこと

## フローチャート

分岐がないため省略
