CREATE TABLE `task_plan_balancesheet_document` (
  `task_plan_balancesheet_document_id` bigint NOT NULL AUTO_INCREMENT COMMENT '政治資金収支報告書と文書識別コードId',
  `saishin_kbn` tinyint DEFAULT NULL COMMENT '最新区分',
  `task_plan_balancesheet_detail_id` bigint DEFAULT NULL COMMENT '政治資金収支報告書処理計画Id',
  `document_code` bigint DEFAULT NULL COMMENT '文書同一識別コード',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`task_plan_balancesheet_document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci