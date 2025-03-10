CREATE TABLE `wk_tbl_poli_org_balancesheet_report` (
  `wk_tbl_poli_org_balancesheet_report_id` bigint NOT NULL AUTO_INCREMENT COMMENT '政治資金収支報告書登録準備ワークテーブルId',
  `wk_tbl_poli_org_balancesheet_report_code` int DEFAULT NULL COMMENT '政治資金収支報告書登録準備ワークテーブル同一識別コード',
  `saishin_kbn` int DEFAULT NULL COMMENT '最新区分',
  `task_plan_balancesheet_detail_id` bigint DEFAULT NULL,
  `task_plan_balancesheet_detail_code` bigint DEFAULT NULL,
  `shoshou_id` bigint DEFAULT NULL COMMENT '書証Id',
  `shoshou_code` bigint DEFAULT NULL COMMENT '書証同一識別コード',
  `shoshou_kbn` int DEFAULT NULL COMMENT '書証区分',
  `full_path` varchar(300) DEFAULT NULL COMMENT 'ファイルパス',
  `child_dir` varchar(300) DEFAULT NULL COMMENT '保存子フォルダ',
  `file_name` varchar(300) DEFAULT NULL COMMENT 'ファイル名',
  `regist_time_text` varchar(300) DEFAULT NULL COMMENT '登録時間',
  `charset` varchar(300) DEFAULT NULL COMMENT '読み取り文字コード',
  `political_organization_id` bigint DEFAULT NULL COMMENT '政治団体Id',
  `political_organization_code` int DEFAULT NULL COMMENT '政治団体同一識別コード',
  `political_organization_name` varchar(300) DEFAULT NULL COMMENT '政治団体名称',
  `dantai_name` varchar(300) DEFAULT NULL COMMENT '原文書団体名称',
  `daihyou_name` varchar(300) DEFAULT NULL COMMENT '原文書代表者名',
  `relation_kbn` int DEFAULT NULL COMMENT '関連者区分',
  `relation_person_id_delegate` bigint DEFAULT NULL COMMENT '代表者関連者個人Id',
  `relation_person_code_delegate` int DEFAULT NULL COMMENT '関連者同一識別コード',
  `relation_person_name_delegate` varchar(300) DEFAULT NULL COMMENT '関連者名称',
  `houkoku_nen` int DEFAULT NULL COMMENT '報告年度',
  `offering_date` date DEFAULT NULL COMMENT '提出日',
  `is_add_organization` tinyint DEFAULT NULL COMMENT '政治団体追加該否',
  `date_kaisai` varchar(300) DEFAULT NULL COMMENT '開催年月日 ',
  `dantai_name01` varchar(300) DEFAULT NULL COMMENT '政治団体名称',
  `dantai_name_kana` varchar(300) DEFAULT NULL COMMENT '政治団体名称かな',
  `jimusho_jusho` varchar(300) DEFAULT NULL COMMENT '事務所の住所',
  `jimusho_jusho_tatemono` varchar(300) DEFAULT NULL COMMENT '事務所の住所建物',
  `daihyousha_name_last` varchar(300) DEFAULT NULL COMMENT '代表者の姓',
  `daihyousha_name_first` varchar(300) DEFAULT NULL COMMENT '代表者の名',
  `kaikei_sekinnsha_name_last` varchar(300) DEFAULT NULL COMMENT '会計責任者の姓',
  `kaikei_sekinnsha_name_first` varchar(300) DEFAULT NULL COMMENT '会計責任者の名',
  `jimu_tantousha1_name_last` varchar(300) DEFAULT NULL COMMENT '事務担当者1の姓',
  `jimu_tantousha1_name_first` varchar(300) DEFAULT NULL COMMENT '事務担当者1の名',
  `jimu_tantousha1_tel` varchar(300) DEFAULT NULL COMMENT '事務担当者1の電話番号',
  `jimu_tantousha2_name_last` varchar(300) DEFAULT NULL COMMENT '事務担当者2の姓',
  `jimu_tantousha2_name_first` varchar(300) DEFAULT NULL COMMENT '事務担当者2の名',
  `jimu_tantousha2_tel` varchar(300) DEFAULT NULL COMMENT '事務担当者2の電話番号',
  `jimu_tantousha3_name_last` varchar(300) DEFAULT NULL COMMENT '事務担当者3の姓',
  `jimu_tantousha3_name_first` varchar(300) DEFAULT NULL COMMENT '事務担当者3の名',
  `jimu_tantousha3_tel` varchar(300) DEFAULT NULL COMMENT '事務担当者3の電話番号',
  `dantai_kbn` varchar(300) DEFAULT NULL COMMENT '団体区分 ',
  `katsudou_kuiki_kbn` int DEFAULT NULL COMMENT '活動区域区分',
  `umu_shikin_kanren_dantai` int DEFAULT NULL COMMENT '資金管理団体の有無',
  `koushoku_name` varchar(300) DEFAULT NULL COMMENT '公職の名称',
  `koushoku_gen_kouho` varchar(300) DEFAULT NULL COMMENT '現職候補者の別',
  `shikin_daihyou_name1` varchar(300) DEFAULT NULL COMMENT '資金管理団体の設立者の姓',
  `shikin_daihyou_name2` varchar(300) DEFAULT NULL COMMENT '資金管理団体の設立者の名',
  `kanri_dantai_period_start` varchar(300) DEFAULT NULL COMMENT '資金管理団体の指定期間(開始)',
  `kanri_dantai_period_end` varchar(300) DEFAULT NULL COMMENT '資金管理団体の指定期間(終了) ',
  `kanri_dantai_period_rest` varchar(300) DEFAULT NULL COMMENT '資金管理団体の複数指定期間',
  `kokkai_giin_dantai_kbn` int DEFAULT NULL COMMENT '国会議員関連団体区分',
  `kokkai_giin1_name_last` varchar(300) DEFAULT NULL COMMENT '国家議員1の姓',
  `kokkai_giin1_name_first` varchar(300) DEFAULT NULL COMMENT '国家議員1の名',
  `kokkai_giin1_shuu_san` varchar(300) DEFAULT NULL COMMENT '国家議員1の公職(衆参) ',
  `kokkai_giin1_gen_kouho` varchar(300) DEFAULT NULL COMMENT '国家議員1現職と候補者の別',
  `giin_dantantai_tokurei_period_start` varchar(300) DEFAULT NULL COMMENT '国会議員関係団体の特例適用期間(開始)',
  `giin_dantantai_tokurei_period_end` varchar(300) DEFAULT NULL COMMENT '国会議員関係団体の特例適用期間(終了)',
  `giin_dantantai_tokurei_period_rest` varchar(300) DEFAULT NULL COMMENT '国会議員関係団体の複数特例適用期間',
  `kokkai_giin2_name_last` varchar(300) DEFAULT NULL COMMENT '国家議員2の姓',
  `kokkai_giin2_name_first` varchar(300) DEFAULT NULL COMMENT '国家議員2の名',
  `kokkai_giin2_shuu_san` varchar(300) DEFAULT NULL COMMENT '国家議員2の公職(衆参)',
  `kokkai_giin2_gen_kouho` varchar(300) DEFAULT NULL COMMENT '国家議員2現職と候補者の別',
  `kokkai_giin3_name_last` varchar(300) DEFAULT NULL COMMENT '国家議員3の姓',
  `kokkai_giin3_name_first` varchar(300) DEFAULT NULL COMMENT '国家議員3の姓',
  `kokkai_giin3_shuu_san` varchar(300) DEFAULT NULL COMMENT '国家議員3の公職(衆参)',
  `kokkai_giin3_gen_kouho` varchar(300) DEFAULT NULL COMMENT '国家議員3現職と候補者の別',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` datetime DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`wk_tbl_poli_org_balancesheet_report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
