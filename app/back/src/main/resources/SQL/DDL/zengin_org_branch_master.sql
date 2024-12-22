CREATE TABLE `zengin_org_branch_master` (
  `zengin_org_tempo_master_id` int NOT NULL AUTO_INCREMENT COMMENT '金融機関マスタId',
  `zengin_org_tempo_master_code` int DEFAULT NULL COMMENT '全銀協金融機関マスタ同一識別コード',
  `zengin_org_tempo_master_name` varchar(300) DEFAULT NULL COMMENT '全銀協金融機関名称',
  `saishin_kbn` tinyint DEFAULT NULL COMMENT '最新区分',
  `org_number` int DEFAULT NULL COMMENT '金融機関コード数字',
  `org_code` varchar(100) DEFAULT NULL COMMENT '全銀協金融機関コード',
  `branch_code` varchar(100) DEFAULT NULL COMMENT '全銀協金融機関支店コード',
  `org_name_kana` varchar(100) DEFAULT NULL COMMENT '金融機関かな',
  `org_name` varchar(100) DEFAULT NULL COMMENT '金融機関名称',
  `branch_name_kana` varchar(100) DEFAULT NULL COMMENT '支店名かな',
  `branch_name` varchar(100) DEFAULT NULL COMMENT '支店名',
  `postal_code` varchar(10) DEFAULT NULL COMMENT '支店郵便番号',
  `branch_address` varchar(300) DEFAULT NULL COMMENT '支店住所',
  `phon_number` varchar(45) DEFAULT NULL COMMENT '支店電話番号',
  `bill_exchange_number` varchar(45) DEFAULT NULL COMMENT '手形交換所番号',
  `order_code` varchar(45) DEFAULT NULL COMMENT '並び順',
  `flg_naikoku_kawase` varchar(10) DEFAULT NULL COMMENT '内国為替制度加盟該否',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`zengin_org_tempo_master_id`),
  FULLTEXT KEY `name` (`zengin_org_tempo_master_name`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
