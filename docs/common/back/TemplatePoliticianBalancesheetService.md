# TemplatePoliticianBalancesheetServices設計書

## package

未定

## 目的

Backend処理のうちService部分で共通化できる処理をフレームワーク化すること

## Serviceにおける処理

- 利用権限チェックを行うこと
- 排他制御を実施すること
- ログを記録すること
- 拡張したサービスで要求された特有のビジネス処理を実施すること
- Controllerに処理した結果を返却すること

## Controllerとの役割分担

[AbstractPoliticianBalancesheetControlller.md](AbstractPoliticianBalancesheetController.md##-Serviceとの役割分担)を参照

## メソッド

### 1. practice

- 引数:ビジネス処理に必要な情報Dto
- 特有なビジネス処理を行い、その結果を返却すること
- 処理を行ったことをログに記録にするため、自身のwriteLogメソッドを呼び出すこと

### 2. boolean checkPrivilege

- 引数:(パッケージ未定)PrivilegeDto
- 処理内容：TODO
- NG時の処理：false/javax.naming.AuthenticationExceptionを返却すること
- OK時の処理：trueを返却すること

### 3. boolean checkPrivilege

- 引数:(パッケージ未定)TransactionControllDto
- 処理内容：TODO
- NG時の処理：false/org.springframework.dao.DuplicateKeyExceptionを返却すること
- OK時の処理：trueを返却すること
