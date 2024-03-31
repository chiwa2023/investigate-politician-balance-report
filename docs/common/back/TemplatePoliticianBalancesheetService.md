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

### 1. boolean practice

- 引数:ビジネス処理に必要な情報Dto
- 特有なビジネス処理を行い、その結果を返却すること
- 処理を行ったことをログに記録にするため、自身のwriteLogメソッドを呼び出すこと

### 2. boolean writeLog

- 引数:(パッケージ未定)LogContentDto
- 処理内容：LogContentDtoの内容
- NG時の処理：例外を投げること
- OK時の処理：trueを返却すること
