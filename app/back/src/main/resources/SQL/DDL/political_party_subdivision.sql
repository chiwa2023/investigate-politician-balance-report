CREATE TABLE `political_party_subdivision` (
  `political_party_subdivision_id` bigint NOT NULL COMMENT '政党支部Id',
  `political_party_subdivision_code` bigint DEFAULT NULL COMMENT '政党支部同一識別コード',
  `political_party_subdivision_name` varchar(200) DEFAULT NULL COMMENT '政党支部名称',
  `latest_kbn` tinyint DEFAULT NULL COMMENT '最新区分',
  `political_party_id` bigint DEFAULT NULL COMMENT '政党Id',
  `political_party_code` bigint DEFAULT NULL COMMENT '政党同一識別コード',
  `political_party_name` varchar(100) DEFAULT NULL COMMENT '政党名',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` timestamp NULL DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` timestamp NULL DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`political_party_subdivision_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
