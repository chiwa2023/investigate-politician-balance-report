CREATE TABLE `offering_balancesheet_0702_and_0713_and_0717_summary_2023` (
  `offering_balancesheet_0702_and_0713_and_0717_summary_id` bigint NOT NULL AUTO_INCREMENT COMMENT '収支報告書様式7その2と13と17Id',
  `offering_balancesheet_0702_and_0713_and_0717_summary_code` bigint DEFAULT NULL COMMENT '収支報告書様式7その2と13と17同一識別コード',
  `document_code` bigint DEFAULT NULL COMMENT '文書同一識別コード',
  `saishin_kbn` int DEFAULT NULL COMMENT '最新区分',
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
  `shunyu_gokei` bigint DEFAULT NULL COMMENT '収入総合計',
  `zennen_kurikoshi` bigint DEFAULT NULL COMMENT '前年繰り越し合計',
  `honnen_shunyu` bigint DEFAULT NULL COMMENT '本年収入合計',
  `shishutsu_goukei` bigint DEFAULT NULL COMMENT '支出総合計',
  `yokunen_kurikoshi` bigint DEFAULT NULL COMMENT '翌年繰り越し合計',
  `koji_futan_goukei` varchar(210) DEFAULT NULL COMMENT '個人の党費または会費を納入金額',
  `koji_futan_suu` varchar(210) DEFAULT NULL COMMENT '党費または会費を納入した員数',
  `kojin_kifu_goukei` bigint DEFAULT NULL COMMENT '個人寄付の合計',
  `kojin_kifu_bikou` varchar(210) DEFAULT NULL COMMENT '個人寄付備考',
  `tokutei_kifu_goukei` bigint DEFAULT NULL COMMENT '特定寄付合計',
  `tokutei_kifu_bikou` varchar(210) DEFAULT NULL COMMENT '特定寄付備考',
  `houjin_kifu_goukei` bigint DEFAULT NULL COMMENT '法人寄付合計',
  `houjin_kifu_biko` varchar(210) DEFAULT NULL COMMENT '法人寄付備考',
  `seiji_dantai_kifu_goukei` bigint DEFAULT NULL COMMENT '政治団体寄付合計',
  `seiji_dantai_kifu_bikou` varchar(210) DEFAULT NULL COMMENT '政治団体寄付備考',
  `kifu_shoukei_goukei` bigint DEFAULT NULL COMMENT '寄付小計合計',
  `kifu_shoukei_bikou` varchar(210) DEFAULT NULL COMMENT '寄付小計備考',
  `assen_goukei` bigint DEFAULT NULL COMMENT 'あっせん合計',
  `assen_bikou` varchar(210) DEFAULT NULL COMMENT 'あっせん備考',
  `tokumei_kifu_goukei` bigint DEFAULT NULL COMMENT '匿名寄附合計',
  `tokumei_kifu_bikou` varchar(210) DEFAULT NULL COMMENT '匿名寄付備考',
  `kifu_so_goukei` bigint DEFAULT NULL COMMENT '寄付総合計',
  `kifu_so_goukei_bikou` varchar(210) DEFAULT NULL COMMENT '寄付総合計備考',
  `goukei_jinkenhi` varchar(210) DEFAULT NULL COMMENT '人件費合計',
  `kohfu_jinkenhi` varchar(210) DEFAULT NULL COMMENT '人件費のうち交付金に係る支出',
  `bikou_jinkenhi` varchar(210) DEFAULT NULL COMMENT '人件費備考',
  `goukei_kohnetsuhi` varchar(210) DEFAULT NULL COMMENT '光熱費合計',
  `kohfu_kohnetsuhi` varchar(210) DEFAULT NULL COMMENT '光熱費のうち交付金に係る支出',
  `bikou_kohnetsuhi` varchar(210) DEFAULT NULL COMMENT '光熱費備考',
  `goukei_bihinhi` varchar(210) DEFAULT NULL COMMENT '備品合計',
  `kohfu_bihinhi` varchar(210) DEFAULT NULL COMMENT '備品のうち交付金に係る支出',
  `bikou_bihinhi` varchar(210) DEFAULT NULL COMMENT '備品備考',
  `goukei_jimushohi` varchar(210) DEFAULT NULL COMMENT '事務所費合計',
  `kohfu_jimushohi` varchar(210) DEFAULT NULL COMMENT '事務所費合計のうち交付金に係る支出',
  `bikou_jimushohi` varchar(210) DEFAULT NULL COMMENT '事務所費備考',
  `goukei_keihi_shoukei` bigint DEFAULT NULL COMMENT '経費項目の合計',
  `kohfu_keihi_shoukei` bigint DEFAULT NULL COMMENT '経費の供与した交付金に係る支出',
  `bikou_keihi_shoukei` varchar(210) DEFAULT NULL COMMENT '経費の備考',
  `goukei_sonota_keihi` bigint DEFAULT NULL COMMENT '経費項目の合計',
  `kohfu_sonota_keihi` varchar(20) DEFAULT NULL COMMENT '経費項目の合計のうち交付金に係る支出',
  `bikou_sonota_keihi` varchar(210) DEFAULT NULL COMMENT '経費項目の合計備考',
  `goukei_soshiki_katsudouhi` bigint DEFAULT NULL COMMENT '組織費合計',
  `kohfu_soshiki_katsudouhi` varchar(210) DEFAULT NULL COMMENT '組織費合計のうち交付金に係る支出',
  `bikou_soshiki_katsudouhi` varchar(210) DEFAULT NULL COMMENT '組織費合計備考',
  `goukei_senkyo_katsudou` bigint DEFAULT NULL COMMENT '選挙費合計',
  `kohfu_senkyo_katsudou` varchar(210) DEFAULT NULL COMMENT '選挙費合計のうち交付金に係る支出',
  `bikou_senkyo_katsudou` varchar(210) DEFAULT NULL COMMENT '選挙費合計備考',
  `goukei_sonota_jigyou` bigint DEFAULT NULL COMMENT 'その他事業費合計',
  `kohfu_sonota_jigyou` varchar(20) DEFAULT NULL COMMENT 'その他事業費合計のうち交付金に係る支出',
  `bikou_sonota_jigyou` varchar(210) DEFAULT NULL COMMENT 'その他事業費合計備考',
  `goukei_hakkou` bigint DEFAULT NULL COMMENT '機関誌発行合計',
  `kohfu_hakkou` varchar(210) DEFAULT NULL COMMENT '機関誌発行合計のうち交付金に係る支出',
  `bikou_hakkou` varchar(210) DEFAULT NULL COMMENT '機関誌発行合計備考',
  `goukei_senden` bigint DEFAULT NULL COMMENT '宣伝広告費合計',
  `kohfu_senden` varchar(210) DEFAULT NULL COMMENT '宣伝広告費合計のうち交付金に係る支出',
  `bikou_senden` varchar(210) DEFAULT NULL COMMENT '宣伝広告費合計備考',
  `goukei_kaisai_pty` bigint DEFAULT NULL COMMENT 'パーティ開催費合計',
  `kohfu_kaisai_pty` varchar(210) DEFAULT NULL COMMENT 'パーティ開催費合計のうち交付金に係る支出',
  `bikou_kaisai_pty` varchar(210) DEFAULT NULL COMMENT 'パーティ開催費合計備考',
  `goukei_sonota` bigint DEFAULT NULL COMMENT 'その他合計',
  `kohfu_sonota` varchar(210) DEFAULT NULL COMMENT 'その他合計のうち交付金に係る支出',
  `bikou_sonota` varchar(210) DEFAULT NULL COMMENT 'その他合計備考',
  `goukei_chousa_kenkyu` bigint DEFAULT NULL COMMENT '調査研究費合計',
  `kohfu_chousa_kenkyu` varchar(210) DEFAULT NULL COMMENT '調査研究費合計のうち交付金に係る支出',
  `bikou_chousa_kenkyu` varchar(210) DEFAULT NULL COMMENT '調査研究費合計備考',
  `goukei_kifukin` bigint DEFAULT NULL COMMENT '寄附合計',
  `kohfu_kifukin` varchar(210) DEFAULT NULL COMMENT '寄附合計のうち交付金に係る支出',
  `bikou_kifukin` varchar(210) DEFAULT NULL COMMENT '寄附合計備考',
  `goukei_katsudouhi` bigint DEFAULT NULL COMMENT '活動費小計',
  `kohfu_katsudouhi` bigint DEFAULT NULL COMMENT '活動費小計のうち交付金に係る支出',
  `bikou_katsudouhi` varchar(210) DEFAULT NULL COMMENT '活動費小計備考',
  `goukei_zen_gohkei` bigint DEFAULT NULL COMMENT '総合計',
  `flg_tochi` int DEFAULT NULL COMMENT '土地有無フラグ',
  `bikou_tochi` varchar(210) DEFAULT NULL COMMENT '土地備考',
  `flg_tatemono` int DEFAULT NULL COMMENT '建物有無フラグ',
  `bikou_tatemono` varchar(210) DEFAULT NULL COMMENT '建物備考',
  `flg_shakuchiken` int DEFAULT NULL COMMENT '借地権有無フラグ',
  `bikou_shakuchiken` varchar(210) DEFAULT NULL COMMENT '借地権備考',
  `flg_dohsan` int DEFAULT NULL COMMENT '動産有無フラグ',
  `bikou_dohsan` varchar(210) DEFAULT NULL COMMENT '動産備考',
  `flg_yokin` int DEFAULT NULL COMMENT '預金有無フラグ',
  `bikou_yokin` varchar(210) DEFAULT NULL COMMENT '預金備考',
  `flg_shintaku` int DEFAULT NULL COMMENT '信託有無フラグ',
  `bikou_shintaku` varchar(210) DEFAULT NULL COMMENT '信託備考',
  `flg_shouken` int DEFAULT NULL COMMENT '証券有無フラグ',
  `bikou_shouken` varchar(210) DEFAULT NULL COMMENT '証券備考',
  `flg_shusshi` int DEFAULT NULL COMMENT '出資有無フラグ',
  `bikou_shusshi` varchar(210) DEFAULT NULL COMMENT '出資備考',
  `flg_kashitsuke` int DEFAULT NULL COMMENT '貸付有無フラグ',
  `bikou_kashitsuke` varchar(210) DEFAULT NULL COMMENT '貸付備考',
  `flg_shikikin` int DEFAULT NULL COMMENT '敷金有無フラグ',
  `bikou_shikikin` varchar(210) DEFAULT NULL COMMENT '敷金備考',
  `flg_shisetsu_riyou` int DEFAULT NULL COMMENT '施設利用権有無フラグ',
  `bikou_shisetsu_riyou` varchar(210) DEFAULT NULL COMMENT '施設利用権備考',
  `flg_kairiire` int DEFAULT NULL COMMENT '借入有無フラグ',
  `bikou_kariire` varchar(210) DEFAULT NULL COMMENT '借入備考',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`offering_balancesheet_0702_and_0713_and_0717_summary_id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci