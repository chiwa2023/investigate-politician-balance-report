CREATE TABLE `save_file_storage_2025` (
  `save_file_storage_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ファイル保存ストレージId',
  `save_file_storage_code` bigint DEFAULT NULL COMMENT 'ファイル保存ストレージ同一識別コード',
  `saishin_kbn` smallint DEFAULT NULL COMMENT '最新区分',
  `child_dir` varchar(100) DEFAULT NULL COMMENT '格納子ディレクトリ',
  `file_name` varchar(100) DEFAULT NULL COMMENT 'ファイル名',
  `regist_time_text` varchar(45) DEFAULT NULL COMMENT '登録Unix時間',
  `shosho_kbn` smallint DEFAULT NULL COMMENT '書証区分',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`save_file_storage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
