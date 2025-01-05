CREATE TABLE `offering_party_usage_0901_report_2025` (
  `party_usage_0901_report_id` bigint NOT NULL AUTO_INCREMENT COMMENT '使途報告書様式9(その1)Id',
  `party_usage_0901_report_code` bigint DEFAULT NULL COMMENT '使途報告書様式9(その1)同一識別コード',
  `saishin_kbn` int DEFAULT NULL COMMENT '最新区分',
  `document_code` bigint DEFAULT NULL COMMENT '文書同一識別コード',
  `nendo` int DEFAULT NULL COMMENT '報告年度',
  `offering_date` date DEFAULT NULL COMMENT '提出日',
  `political_organization_id` bigint DEFAULT NULL COMMENT '政治団体Id',
  `political_organization_code` int DEFAULT NULL COMMENT '政治団体同一識別コード',
  `political_organization_name` varchar(210) DEFAULT NULL COMMENT '政治団体名称',
  `dantai_name` varchar(210) DEFAULT NULL COMMENT '原文書政治団体名称',
  `daihyou_name` varchar(210) DEFAULT NULL COMMENT '原文書政治団体代表者名',
  `relation_kbn` int DEFAULT NULL COMMENT '関連者区分',
  `relation_person_id_delegate` bigint DEFAULT NULL COMMENT '代表者関連者Id',
  `relation_person_code_delegate` int DEFAULT NULL COMMENT '代表者関連者同一識別コード',
  `relation_person_name_delegate` varchar(210) DEFAULT NULL COMMENT '代表者関連者名称',
  `row_no` int DEFAULT NULL COMMENT '行番号',
  `item_name` varchar(210) DEFAULT NULL COMMENT '項目名称',
  `digest` varchar(210) DEFAULT NULL COMMENT '摘要',
  `amount` bigint DEFAULT NULL COMMENT '金額',
  `accrual_date` varchar(40) DEFAULT NULL COMMENT '発生日',
  `explain_text` varchar(210) DEFAULT NULL COMMENT '事情の説明',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`party_usage_0901_report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=500 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci