CREATE TABLE `offering_balancesheet_income_2022` (
  `offering_balancesheet_income_id` bigint NOT NULL AUTO_INCREMENT COMMENT '収支報告書収入(その3から12)Id',
  `offering_balancesheet_income_code` bigint DEFAULT NULL COMMENT '収支報告書収入(その3から12)同一識別コード',
  `saishin_kbn` int DEFAULT NULL COMMENT '最新区分',
  `document_code` bigint DEFAULT NULL COMMENT '文書同一識別コード',
  `houkoku_nen` int DEFAULT NULL COMMENT '報告年',
  `offering_date` date DEFAULT NULL COMMENT '提出日',
  `political_organization_id` bigint DEFAULT NULL COMMENT '政治団体Id',
  `political_organization_code` int DEFAULT NULL COMMENT '政治団体同一識別コード',
  `political_organization_name` varchar(210) DEFAULT NULL COMMENT '政治団体名称',
  `daihyou_name` varchar(210) DEFAULT NULL COMMENT '原文書政治団体代表者名',
  `dantai_name` varchar(210) DEFAULT NULL COMMENT '原文書政治団体名称',
  `relation_kbn` int DEFAULT NULL COMMENT '関連者区分',
  `relation_person_id_delegate` bigint DEFAULT NULL COMMENT '代表者関連者Id',
  `relation_person_code_delegate` int DEFAULT NULL COMMENT '代表者関連者同一識別コード',
  `relation_person_name_delegate` varchar(210) DEFAULT NULL COMMENT '代表者関連者名称',
  `youshiki_kbn` int DEFAULT NULL COMMENT '様式区分',
  `youshiki_eda_kbn` int DEFAULT NULL COMMENT '様式枝区分項目',
  `page_total` bigint DEFAULT NULL COMMENT 'ページ計',
  `miman_total` varchar(20) DEFAULT NULL COMMENT '未満計',
  `sonota_total` varchar(20) DEFAULT NULL COMMENT 'その他合計',
  `party_name` varchar(210) DEFAULT NULL COMMENT 'パーティ名称',
  `sort_no` varchar(210) DEFAULT NULL COMMENT 'ソート番号',
  `ichiren_no` int DEFAULT NULL COMMENT '連番',
  `item_name` varchar(210) DEFAULT NULL COMMENT '項目名称',
  `kingaku` bigint DEFAULT NULL COMMENT '金額',
  `accrual_date` varchar(210) DEFAULT NULL COMMENT '発生日',
  `accrual_date_value` date DEFAULT NULL COMMENT '発生日実値',
  `bikou` varchar(210) DEFAULT NULL COMMENT '備考',
  `partner_name` varchar(210) DEFAULT NULL COMMENT '支出した相手先名',
  `partner_juusho` text COMMENT '支出した相手先住所',
  `shiharaisu` int DEFAULT NULL COMMENT 'パーティ支払人数',
  `kaisai_basho` text COMMENT 'パーティ開催場所',
  `flg_zeigaku_kohjo` smallint DEFAULT NULL COMMENT '税額控除フラグ',
  `tohshibangou` int DEFAULT NULL COMMENT '通し番号',
  `gyoukubun` smallint DEFAULT NULL COMMENT '行区分',
  `shokugyou` varchar(210) DEFAULT NULL COMMENT '職業',
  `period_mediate` varchar(210) DEFAULT NULL COMMENT 'あっせんの期間',
  `relation_person_id_income` bigint DEFAULT NULL COMMENT '支払者関連者Id(個人)',
  `relation_person_code_income` int DEFAULT NULL COMMENT '支払者関連者同一識別コード(個人)',
  `relation_person_name_income` varchar(210) DEFAULT NULL COMMENT '支払者関連者名称(個人)',
  `relation_corp_id_income` bigint DEFAULT NULL COMMENT '支払者関連者Id(法人)',
  `relation_corp_code_income` int DEFAULT NULL COMMENT '支払者関連者同一識別コード(法人)',
  `relation_corp_name_inccome` varchar(210) DEFAULT NULL COMMENT '支払者関連者Id(法人)',
  `relation_political_org_id_income` bigint DEFAULT NULL COMMENT '支払者関連者同一識別コード(政治団体)',
  `relation_political_org_code_income` int DEFAULT NULL COMMENT '支払者関連者名称(政治団体)',
  `relation_political_org_name_income` varchar(210) DEFAULT NULL COMMENT '支払者関連者名称(政治団体)',
  `search_words` text COMMENT '自由検索用カラム',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`offering_balancesheet_income_id`),
  FULLTEXT KEY `search_words` (`search_words`) /*!50100 WITH PARSER `ngram` */ 
) ENGINE=InnoDB AUTO_INCREMENT=13445 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci