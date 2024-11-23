CREATE TABLE `send_alert_mail_2022` (
  `send_alert_mail_id` bigint NOT NULL AUTO_INCREMENT COMMENT '項目名称取得Id',
  `send_alert_mail_code` int DEFAULT NULL COMMENT '項目名称取得同一識別コード',
  `saishin_kbn` int DEFAULT NULL COMMENT '最新区分',
  `send_user_id` bigint DEFAULT NULL COMMENT '送信先ユーザId',
  `send_user_code` int DEFAULT NULL COMMENT '送信先ユーザ同一識別コード',
  `send_user_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '送信先ユーザ名称',
  `send_datetime` datetime DEFAULT NULL,
  `is_repeat` tinyint DEFAULT NULL,
  `from_mail` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '送信元メールアドレス',
  `to_mail` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '宛先メールアドレス',
  `cc_mail` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ccメールアドレス',
  `bcc_mail` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'bccメールアドレス',
  `reply__to_mail` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '返信先メールアドレス',
  `subject_mail` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'メールタイトル',
  `body_text_mail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT 'メール本文',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`send_alert_mail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
