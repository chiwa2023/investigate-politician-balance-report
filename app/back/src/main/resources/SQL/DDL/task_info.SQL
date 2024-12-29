CREATE TABLE `task_info` (
  `task_info_id` bigint NOT NULL COMMENT 'タスク情報Id',
  `task_info_code` int DEFAULT NULL COMMENT 'タスク情報同一識別コード',
  `task_info_name` varchar(300) DEFAULT NULL COMMENT 'タスク情報名称',
  `saishin_kbn` tinyint DEFAULT NULL COMMENT '最新区分',
  `task_level_list` varchar(300) DEFAULT NULL COMMENT '権限区分',
  `message_template` varchar(300) DEFAULT NULL COMMENT 'メッセージテンプレート',
  `transfer_pass` text COMMENT '遷移パス(URL)',
  `param_query` text COMMENT 'SNS同一識別コード',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`task_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci