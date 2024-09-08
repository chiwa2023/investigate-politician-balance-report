CREATE TABLE `offering_party_usage_0802_and_0803_report_2022` (
  `party_usage_0802_and_0803_report_id` bigint NOT NULL AUTO_INCREMENT COMMENT '使途報告書様式8その2と3Id',
  `party_usage_0802_and_0803_report_code` bigint DEFAULT NULL COMMENT '使途報告書様式8その2と3同一識別コード',
  `saishin_kbn` int DEFAULT NULL,
  `document_code` bigint DEFAULT NULL COMMENT '文書同一識別コード',
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
  `total_all_action_all` bigint DEFAULT NULL COMMENT '全政治活動全合計',
  `total_all_action_juto_koufukin` bigint DEFAULT NULL COMMENT '全政治活動交付金充当',
  `total_all_action_juto_my_funds` bigint DEFAULT NULL COMMENT '全政治活動自党基金充当',
  `total_all_action_bikou` varchar(240) DEFAULT NULL COMMENT '全政治活動備考',
  `total_all_amount_all` bigint DEFAULT NULL COMMENT '全総計全合計',
  `total_all_amount_juto_koufukin` bigint DEFAULT NULL COMMENT '全総計交付金充当',
  `total_all_amount_juto_my_funds` bigint DEFAULT NULL COMMENT '総計自党基金充当',
  `total_all_amount_bikou` varchar(240) DEFAULT NULL COMMENT '全総計備考',
  `total_all_jigyou_all` bigint DEFAULT NULL COMMENT '全事業総計全合計',
  `total_all_jigyou_juto_koufukin` bigint DEFAULT NULL COMMENT '全事業総計交付金充当',
  `total_all_jigyou_juto_my_funds` bigint DEFAULT NULL COMMENT '全事業自党基金充当',
  `total_all_jigyou_bikou` varchar(240) DEFAULT NULL COMMENT '全事業備考',
  `total_bihin_all` bigint DEFAULT NULL COMMENT '備品消耗品費全合計',
  `total_bihin_juto_koufukin` bigint DEFAULT NULL COMMENT '備品消耗品費交付金充当',
  `total_bihin_juto_my_funds` bigint DEFAULT NULL COMMENT '備品消耗品費自党基金充当',
  `total_bihin_bikou` varchar(240) DEFAULT NULL COMMENT '備品消耗品費備考',
  `total_chousa_all` bigint DEFAULT NULL COMMENT '調査研究費全合計',
  `total_chousa_juto_koufukin` bigint DEFAULT NULL COMMENT '調査研究費交付金充当',
  `total_chousa_juto_my_funds` bigint DEFAULT NULL COMMENT '調査研究費自党基金充当',
  `total_chousa_bikou` varchar(240) DEFAULT NULL COMMENT '調査研究費備考',
  `total_jimusho_all` bigint DEFAULT NULL COMMENT '事務所費全合計',
  `total_jimusho_juto_koufukin` bigint DEFAULT NULL COMMENT '事務所費交付金充当',
  `total_jimusho_juto_my_funds` bigint DEFAULT NULL COMMENT '事務所費自党基金充当',
  `total_jimusho_bikou` varchar(240) DEFAULT NULL COMMENT '事務所費備考',
  `total_jinkenhi_all` bigint DEFAULT NULL COMMENT '人件費全合計',
  `total_jinkenhi_juto_koufukin` bigint DEFAULT NULL COMMENT '人件費交付金充当',
  `total_jinkenhi_juto_my_funds` bigint DEFAULT NULL COMMENT '人件費自党基金充当',
  `total_jinkenhi_bikou` varchar(240) DEFAULT NULL COMMENT '人件費備考',
  `total_keihi_all` bigint DEFAULT NULL COMMENT '経費全合計',
  `total_keihi_juto_koufukin` bigint DEFAULT NULL COMMENT '経費交付金充当',
  `total_keihi_juto_my_funds` bigint DEFAULT NULL COMMENT '経費自党基金充当',
  `total_keihi_bikou` varchar(240) DEFAULT NULL COMMENT '経費備考',
  `total_kifu_all` bigint DEFAULT NULL COMMENT '寄附全合計',
  `total_kifu_juto_koufukin` bigint DEFAULT NULL COMMENT '寄附交付金充当',
  `total_kifu_juto_my_funds` bigint DEFAULT NULL COMMENT '寄附自党基金充当',
  `total_kifu_bikou` varchar(240) DEFAULT NULL COMMENT '寄附備考',
  `total_kikanshi_all` bigint DEFAULT NULL COMMENT '機関誌発行全合計',
  `total_kikanshi_juto_koufukin` bigint DEFAULT NULL COMMENT '機関誌発行交付金充当',
  `total_kikanshi_juto_my_funds` bigint DEFAULT NULL COMMENT '機関誌発行自党基金充当',
  `total_kikanshi_bikou` varchar(240) DEFAULT NULL COMMENT '機関誌発行備考',
  `total_kounetsuhi_all` bigint DEFAULT NULL COMMENT '光熱費全合計',
  `total_kounetsuhi_juto_koufukin` bigint DEFAULT NULL COMMENT '光熱費交付金充当',
  `total_kounetsuhi_juto_my_funds` bigint DEFAULT NULL COMMENT '光熱費自党基金充当',
  `total_kounetsuhi_bikou` varchar(240) DEFAULT NULL COMMENT '光熱費備考',
  `total_party_all` bigint DEFAULT NULL COMMENT 'パーティ費全合計',
  `total_party_juto_koufukin` bigint DEFAULT NULL COMMENT 'パーティ費交付金充当',
  `total_party_juto_my_funds` bigint DEFAULT NULL COMMENT 'パーティ費自党基金充当',
  `total_party_bikou` varchar(240) DEFAULT NULL COMMENT 'パーティ費備考',
  `total_senden_all` bigint DEFAULT NULL COMMENT '宣伝費全合計',
  `total_senden_juto_koufukin` bigint DEFAULT NULL COMMENT '宣伝費交付金充当',
  `total_senden_juto_my_funds` bigint DEFAULT NULL COMMENT '宣伝費自党基金充当',
  `total_senden_bikou` varchar(240) DEFAULT NULL COMMENT '宣伝費備考',
  `total_senkyo_all` bigint DEFAULT NULL COMMENT '選挙費全合計',
  `total_senkyo_juto_koufukin` bigint DEFAULT NULL COMMENT '選挙費交付金充当',
  `total_senkyo_juto_my_funds` bigint DEFAULT NULL COMMENT '選挙費自党基金充当',
  `total_senkyo_bikou` varchar(240) DEFAULT NULL COMMENT '選挙費備考',
  `total_shibu_koufu_all` bigint DEFAULT NULL COMMENT '支部交付金全合計',
  `total_shibu_koufu_juto_koufukin` bigint DEFAULT NULL COMMENT '支部交付金交付金充当',
  `total_shibu_koufu_juto_my_funds` bigint DEFAULT NULL COMMENT '支部交付金自党基金充当',
  `total_shibu_koufu_bikou` varchar(240) DEFAULT NULL COMMENT '支部交付金備考',
  `total_sonota_jigyou_all` bigint DEFAULT NULL COMMENT 'その他事業費全合計',
  `total_sonota_jigyou_juto_koufukin` bigint DEFAULT NULL COMMENT 'その他事業費交付金充当',
  `total_sonota_jigyou_juto_my_funds` bigint DEFAULT NULL COMMENT 'その他事業費自党基金充当',
  `total_sonota_jigyou_bikou` varchar(240) DEFAULT NULL COMMENT 'その他事業費備考',
  `total_sonota_keihi_all` bigint DEFAULT NULL COMMENT 'その他経費全合計',
  `total_sonota_keihi_juto_koufukin` bigint DEFAULT NULL COMMENT 'その他経費交付金充当',
  `total_sonota_keihi_juto_my_funds` bigint DEFAULT NULL COMMENT 'その他経費自党基金充当',
  `total_sonota_keihi_bikou` varchar(240) DEFAULT NULL COMMENT 'その他経費備考',
  `total_soshiki_all` bigint DEFAULT NULL COMMENT '組織費全合計',
  `total_soshiki_juto_koufukin` bigint DEFAULT NULL COMMENT '組織費交付金充当',
  `total_soshiki_juto_my_funds` bigint DEFAULT NULL COMMENT '組織費自党基金充当',
  `total_soshiki_bikou` varchar(240) DEFAULT NULL COMMENT '組織費備考',
  `party_all_koufukin` bigint DEFAULT NULL COMMENT '政党交付金（支部政党交付金）の総額',
  `last_year_rest` bigint DEFAULT NULL COMMENT '前年末政党基金（支部基金）残高',
  `outcome_koufukin_all` bigint DEFAULT NULL COMMENT '政党交付金（支部政党交付金）による支出総額',
  `outcome_koufukin` bigint DEFAULT NULL COMMENT '政党交付金（支部政党交付金）支出充当額総額',
  `outcome_shibu_kikin` bigint DEFAULT NULL COMMENT '政党基金（支部基金）支出充当額総額',
  `withdrawal_funds` bigint DEFAULT NULL COMMENT '基金取り崩し',
  `accumulate_funds` bigint DEFAULT NULL COMMENT '政党基金（支部基金）積立総額',
  `funds_sum_gain` bigint DEFAULT NULL COMMENT '政党基金（支部基金）の運用により収受した果実の総額',
  `this_year_rest` bigint DEFAULT NULL COMMENT '本年末等政党基金（支部基金）残高',
  `bikou_differ` bigint DEFAULT NULL COMMENT '備 考',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` timestamp NULL DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` timestamp NULL DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`party_usage_0802_and_0803_report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
