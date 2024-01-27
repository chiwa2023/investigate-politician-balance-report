# TemplatePoliticianBalanceSheetController設計書

## 1.package

未定

## 2.目的

すべてのBackendAPI処理をフレームワーク化し共通化すること

## 3.Backend処理におけるフレームワーク

- front側からの情報を受け取ること
- セキュリティチェックを行うこと
- 利用権限チェックを行うこと
- 排他制御を実施すること
- ログを記録すること
- 拡張したサービスで要求された特有のビジネス処理を実施すること
- フロントに処理した結果を返却すること

## 4.Serviceとの役割分担

|                       実施項目                       | Controller | Service |                          NG時の処理                           |
| ---------------------------------------------------- | :--------: | :-----: | ------------------------------------------------------------- |
| frontend側からの情報を受け取ること                   |     〇     |    -    | -                                                             |
| セキュリティチェックを行うこと                       |     〇     |    -    | java.lang.SecurirtyExceptionをスローすること                  |
| 利用権限チェックを行うこと                           |     -      |   〇    | javax.naming.AuthenticationExceptionスローすること            |
| 排他制御を実施すること                               |     -      |   〇    | org.springframework.dao.DuplicateKeyExceptionをスローすること |
| ログを記録すること                                   |     -      |   〇    | 未定                                                          |
| 継承されたサービスで特有のビジネス処理を実施すること |     -      |   〇    | 個別に異なる                                                  |
| frontend側に処理した結果を返却すること               |     〇     |    -    | -                                                             |

## 5.フレームワーク設定に伴う結論

基本的にこのControllerを継承したControllerは下記のような実装となる。

        //ビジネスロジック以外はすべてフレームワークに沿った作業
        try {
            SecurityDto securityDto = new SecurityDto(); //実際は引数Dtoから取り出し
            PrivilegeDto privilegeDto = new PrivilegeDto(); //実際は引数Dtoから取り出し
            TransactionControllDto transactionControllDto = new TransactionControllDto(); //実際は引数Dtoから取り出し
            
            //セキュリティチェック
            if(!super.checkSecurity(securityDto)) {
                return this.makeBadGatewayResponse();
            }

            //権限チェック
            if(!politicianViewService.checkPrivilege(privilegeDto)) {
                return this.makeForbiddenResponse();
            }

            //排他制御チェック
            if(!politicianViewService.checkTransactionControll(transactionControllDto)) {
                return this.makeConflictResponse();
            }
            
            // TODO ビジネス処理
            //politicianViewService.practice();
            
            return ResponseEntity.ok().body(new Object());
        }
        catch (SecurityException securityException) {
            //セキュリティ事故
            return super.makeBadGatewayResponse();
        }
        catch (AuthenticationException authenticationException) {
            //権限不足
            return super.makeForbiddenResponse();
        }
        catch (DuplicateKeyException duplicateKeyException) {
            //排他の対象
            return super.makeConflictResponse();
        }

## 6.メソッド

### 1. (public ResponseEntity\<Object\> practice)

- 引数:個別に設定するが、下記のDtoを含まなければならない
- 引数に含まれなければならない情報
  - セキュリティ情報を含むSecurityInfoDto
  - 権限情報が入力されたPrivilegeInfoDto
  - 排他情報を入力されたTransactionInfoDto
  - ログ情報を入力されたLogInfoDto
  - ビジネス処理特有情報Dto
- セキュリティチェックとして`checkSecurity(SecurityInfoDto)`を呼び出すこと
- 権限チェックとして`【同名】Servicre.checkPrivilege(PrivilegeInfoDto)`を呼び出すこと
- 排他チェックとして`【同名】Servicre.checkTransactionControll(TransactionInfoDto)`を呼び出すこと
- 【同名】とはOverrideしたControllerが「AbcdefgController」であれば、対応するServiceである`AbcdefgService`の【Abcdefg】の部分を指す
- 各チェックに対応する実装を行うこと
  - アクセス情報改竄などのセキュリティ事故が疑われて、自身のController.checkSecurityメソッドからのfalse/または例外を受信した場合はサーバステータス502 Bad Gatewayを返却すること
  - ユーザが権限を持たず、false/または例外を受信した場合はサーバステータス403 Forbiddenのみを返却すること
  - 他のユーザが編集を希望しており、排他の対象になり、false/または例外を受信した場合はサーバステータス409 Conflictを返却すること。排他制御一覧ページを案内する場合は、サーバーステータス201で行わているようにそのURLをResponseHeaderのLocationに格納すること
  - その他のすべてのユーザが理由を説明されても対処できないため、運営に連絡が必要な例外が発生した場合はサーバステータス500 Internal Server Errorのみを返却すること

### 2. checkSecurity

- 引数:(パッケージ未定)SecurityInfoDto
- 処理内容:TODO
- チェックしてNGの場合:falseまたはjava.lang.SecurirtyExceptionを返却すること

### 3. makeBadGatewayResponse

- 引数:なし
- 処理内容:`return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build()`

### 4. makeForbiddenResponse

- 引数:なし
- 処理内容:`return ResponseEntity.status(HttpStatus.FORBIDDEN).build()`

### 5. makeConflictResponse

- 引数:なし
- 処理内容:`return ResponseEntity.status(HttpStatus.CONFRICT).build()`
