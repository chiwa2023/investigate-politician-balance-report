CREATE TABLE `political_organization` (
  `political_organization_id` bigint NOT NULL COMMENT '政治団体Id',
  `political_organization_code` bigint DEFAULT NULL COMMENT '政治団体同一識別コード',
  `political_organization_name` varchar(100) DEFAULT NULL COMMENT '政治団体名称',
  `latest_kbn` tinyint DEFAULT NULL COMMENT '最新区分',
  `submission_date` date DEFAULT NULL COMMENT '届出日',
  `political_organization_name_kana` varchar(150) DEFAULT NULL COMMENT '政治団体名称かな',
  `establishment_date` date DEFAULT NULL COMMENT '組織創立日',
  `dantai_kbn` tinyint DEFAULT NULL COMMENT '政治団体区分',
  `kokkaigiin_kbn` tinyint DEFAULT NULL COMMENT '国会議員関係団体区分',
  `postalcode1` varchar(5) DEFAULT NULL COMMENT '主たる事務所郵便番号(前)',
  `postalcode2` varchar(10) DEFAULT NULL COMMENT '主たる事務所郵便番号(後)',
  `jusho_all` varchar(600) DEFAULT NULL COMMENT '主たる事務所(全)',
  `tel1` varchar(10) DEFAULT NULL COMMENT '主たる事務所電話番号市外局番',
  `tel2` varchar(10) DEFAULT NULL COMMENT '主たる事務所電話番号局番',
  `tel3` varchar(10) DEFAULT NULL COMMENT '主たる事務所電話番号番号',
  `katsudou__kuiki_kbn` tinyint DEFAULT NULL COMMENT '団体活動区域区分',
  `daihyosha_first_name` varchar(50) DEFAULT NULL COMMENT '代表者の姓',
  `daihyosha_last_name` varchar(50) DEFAULT NULL COMMENT '代表者の名',
  `daihyosha_name_kana` varchar(150) DEFAULT NULL COMMENT '代表者姓名かな',
  `kaikei_sekininsha_first_name` varchar(50) DEFAULT NULL COMMENT '会計責任者の姓',
  `kaikei_sekininsha_last_name` varchar(50) DEFAULT NULL COMMENT '会計責任者の名',
  `kaikei_sekininsha_name_kana` varchar(150) DEFAULT NULL COMMENT '会計責任者の姓名かな',
  `kaikei_daiko_first_name` varchar(50) DEFAULT NULL COMMENT '会計責任者代行の姓',
  `kaikei_daiko_last_name` varchar(50) DEFAULT NULL COMMENT '会計責任者代行の名',
  `kaikei_daiko_name_kana` varchar(150) DEFAULT NULL COMMENT '会計責任者代行姓名かな',
  `is_subdivision` tinyint DEFAULT NULL COMMENT '支部の有無',
  `is_kazei_yuugu` tinyint DEFAULT NULL COMMENT '課税優遇措置の有無',
  `daihyosha_koushoku` varchar(45) DEFAULT NULL COMMENT '代表者の公職(候補も含む)',
  `shiensha_shimei` varchar(100) DEFAULT NULL COMMENT '支援したい政治家の姓名',
  `shiensha_koushoku` varchar(200) DEFAULT NULL COMMENT '支援する政治家の公職(候補も含む)',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_timestamp` timestamp NULL DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` timestamp NULL DEFAULT NULL COMMENT '更新タイムスタンプ',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  PRIMARY KEY (`political_organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
