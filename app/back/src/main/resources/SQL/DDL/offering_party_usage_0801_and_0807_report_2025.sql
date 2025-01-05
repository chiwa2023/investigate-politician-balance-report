CREATE TABLE `offering_party_usage_0801_and_0807_report_2025` (
  `party_usage_0801_and_0807_report_id` bigint NOT NULL AUTO_INCREMENT COMMENT '使途報告書様式8その1と7Id',
  `party_usage_0801_and_0807_report_code` bigint DEFAULT NULL COMMENT '使途報告書様式8その1と7同一識別コード',
  `saishin_kbn` int DEFAULT NULL COMMENT '最新区分',
  `version` varchar(30) DEFAULT NULL COMMENT 'ヘッダのバージョン番号',
  `apli_name` varchar(50) DEFAULT NULL COMMENT 'ヘッダのアプリ名称',
  `flg_apli` varchar(2) DEFAULT NULL COMMENT 'ヘッダのアプリフラグ',
  `flg_honbu` varchar(2) DEFAULT NULL COMMENT 'ヘッダの本部支部フラグ',
  `joho_umu_text` varchar(30) DEFAULT NULL COMMENT '情報入力有無フラグテキスト',
  `kaikei_kijun_kingaku` bigint DEFAULT NULL COMMENT '会計支出(表示)基準',
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
  `party_name_kana` varchar(400) DEFAULT NULL COMMENT '様式8の1政党名称かな',
  `party_name` varchar(210) DEFAULT NULL COMMENT '様式8の1政党名称',
  `office_address` text COMMENT '様式8の1主たる事務所の住所',
  `delegate_name` varchar(210) DEFAULT NULL COMMENT '様式8の1代表者姓名',
  `account_manager_name` varchar(210) DEFAULT NULL COMMENT '様式8の1会計責任者姓名',
  `worker1_name` varchar(210) DEFAULT NULL COMMENT '様式8の1作業者1姓名',
  `worker1_tel` varchar(50) DEFAULT NULL COMMENT '様式8の1作業者1電話番号',
  `worker2_name` varchar(210) DEFAULT NULL COMMENT '様式8の1作業者2姓名',
  `worker2_tel` varchar(50) DEFAULT NULL COMMENT '様式8の1作業者2電話番号',
  `kaisan_kbn` smallint DEFAULT NULL COMMENT '様式8の1解散区分',
  `kaisan_date` varchar(32) DEFAULT NULL COMMENT '様式8の1解散日',
  `shibu_kbn` smallint DEFAULT NULL COMMENT '様式8の1支部区分',
  `uketsuke_no` varchar(50) DEFAULT NULL COMMENT '様式8の1受付番号',
  `seiri_no` varchar(50) DEFAULT NULL COMMENT '様式8の1整理番号',
  `copy_recipt` smallint DEFAULT NULL COMMENT '領収書の写し提出(フラグ)',
  `audit_option` smallint DEFAULT NULL COMMENT '監査意見書提出(フラグ)',
  `audit_report` smallint DEFAULT NULL COMMENT '監査報告書提出(フラグ)',
  `shibu_document` smallint DEFAULT NULL COMMENT '支部から受領下書類提出(フラグ)',
  `governing_document` smallint DEFAULT NULL COMMENT '統括文書提出(フラグ)',
  `flg_confirm` smallint DEFAULT NULL COMMENT '真実であることの宣誓フラグ',
  `accrual_date` varchar(45) DEFAULT NULL COMMENT '提出日和暦',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`party_usage_0801_and_0807_report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci