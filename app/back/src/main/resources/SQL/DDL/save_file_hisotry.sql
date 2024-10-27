CREATE TABLE `save_file_hisotry` (
  `save_file_hisotry_id` bigint NOT NULL COMMENT 'ファイル保存ストレージId',
  `save_file_hisotry_code` bigint DEFAULT NULL COMMENT 'ファイル保存ストレージ同一識別コード',
  `saishin_kbn` tinyint DEFAULT NULL COMMENT '最新区分',
  `henkou_riyu` text COMMENT '変更理由',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`save_file_hisotry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
