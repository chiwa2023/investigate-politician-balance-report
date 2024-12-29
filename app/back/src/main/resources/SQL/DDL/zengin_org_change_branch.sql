CREATE TABLE `zengin_org_change_branch` (
  `zengin_org_change_branch_id` int NOT NULL AUTO_INCREMENT COMMENT '金融機関異動Id',
  `zengin_org_change_branch_code` int DEFAULT NULL COMMENT '金融機関異動同一識別コード',
  `saishin_kbn` tinyint DEFAULT NULL COMMENT '最新区分',
  `is_finished` tinyint DEFAULT NULL,
  `change_kbn` tinyint DEFAULT NULL COMMENT '異動区分',
  `change_kbn_text` varchar(300) DEFAULT NULL COMMENT '異動区分表示',
  `zengin_org_tempo_master_id` int NOT NULL COMMENT '編集金融機関マスタId',
  `zengin_org_tempo_master_code` int DEFAULT NULL COMMENT '編集全銀協金融機関マスタ同一識別コード',
  `zengin_org_tempo_master_name` varchar(300) DEFAULT NULL COMMENT '編集全銀協金融機関名称',
  `zengin_org_move_id` bigint DEFAULT NULL COMMENT '移転先金融機関id',
  `zengin_org_move_code` int DEFAULT NULL COMMENT '移転先金融機関同一識別コード',
  `zengin_org_move_name` varchar(300) DEFAULT NULL COMMENT '移転先金融機関名称',
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
  PRIMARY KEY (`zengin_org_change_branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci