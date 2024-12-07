CREATE TABLE `political_organization_access` (
  `political_organization_access_id` bigint NOT NULL COMMENT '政治団体連絡先Id',
  `spolitical_organization_access_code` int DEFAULT NULL COMMENT '政治団体連絡先同一識別コード',
  `saishin_kbn` smallint DEFAULT NULL COMMENT '最新区分',
  `political_organization_id` bigint DEFAULT NULL COMMENT '政治団体Id',
  `political_organization_code` bigint DEFAULT NULL COMMENT '政治団体同一識別コード',
  `political_organization_name` varchar(100) DEFAULT NULL COMMENT '政治団体名称',
  `postalcode1` varchar(10) DEFAULT NULL COMMENT '郵便番号1',
  `postalcode2` varchar(10) DEFAULT NULL COMMENT '郵便番号2',
  `address_all` text COMMENT '全住所',
  `address_postal` varchar(100) DEFAULT NULL COMMENT '住所(郵便番号呼び出し)',
  `address_block` varchar(100) DEFAULT NULL COMMENT '住所番地',
  `address_building` varchar(100) DEFAULT NULL COMMENT '住所建物',
  `phon_all` varchar(100) DEFAULT NULL COMMENT '電話番号全体',
  `phon_no1` varchar(10) DEFAULT NULL COMMENT '電話番号1',
  `phon_no2` varchar(10) DEFAULT NULL COMMENT '電話番号2',
  `phon_no3` varchar(10) DEFAULT NULL COMMENT '電話番号3',
  `mail_addless` varchar(10) DEFAULT NULL COMMENT 'メールアドレス',
  `owned_media_url` text COMMENT '自団体ホームページURL',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`political_organization_access_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
