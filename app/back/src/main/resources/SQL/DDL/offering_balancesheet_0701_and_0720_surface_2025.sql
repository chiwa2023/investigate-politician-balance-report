CREATE TABLE `offering_balancesheet_0701_and_0720_surface_2025` (
  `offering_balancesheet_0701_and_0720_surface_id` bigint NOT NULL AUTO_INCREMENT COMMENT '収支報告書様式7その1と20Id',
  `offering_balancesheet_0701_and_0720_surface_code` bigint DEFAULT NULL COMMENT '収支報告書様式7その1と20同一識別コード',
  `saishin_kbn` int DEFAULT NULL COMMENT '最新区分',
  `version` varchar(30) DEFAULT NULL COMMENT 'ヘッダのバージョン番号',
  `app_name` varchar(50) DEFAULT NULL COMMENT 'ヘッダのアプリ名称',
  `file_format_no` varchar(50) DEFAULT NULL COMMENT 'ファイルのフォーマット形式',
  `flg_kokuji` varchar(2) DEFAULT NULL COMMENT '告示フラグ',
  `choubo_app_ver` varchar(50) DEFAULT NULL COMMENT '帳簿アプリ番号',
  `input_bit_text` varchar(90) DEFAULT NULL COMMENT '情報入力有無テキスト',
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
  `date_kaisai` varchar(30) DEFAULT NULL COMMENT '開催年月日',
  `dantai_name_kana` varchar(210) DEFAULT NULL COMMENT '政治団体名称かな',
  `jimusho_jusho` text COMMENT '様式7の1主たる事務所の住所',
  `jimusho_jusho_tatemono` text COMMENT '様式7の1主たる事務所の住所建物',
  `daihyousha_name_last` varchar(210) DEFAULT NULL COMMENT '代表者の姓',
  `daihyousha_name_first` varchar(210) DEFAULT NULL COMMENT '代表者の名',
  `kaikei_sekinnsha_name_last` varchar(210) DEFAULT NULL COMMENT '会計責任者の姓',
  `kaikei_sekinnsha_name_first` varchar(210) DEFAULT NULL COMMENT '会計責任者の名',
  `jimu_tantousha1_name_last` varchar(210) DEFAULT NULL COMMENT '事務担当者1の姓',
  `jimu_tantousha1_name_first` varchar(210) DEFAULT NULL COMMENT '事務担当者1の名 ',
  `jimu_tantousha1_tel` varchar(210) DEFAULT NULL COMMENT '事務担当者1の電話番号',
  `jimu_tantousha2_name_last` varchar(210) DEFAULT NULL COMMENT '事務担当者2の姓',
  `jimu_tantousha2_name_first` varchar(210) DEFAULT NULL COMMENT '事務担当者2の名',
  `jimu_tantousha2_tel` varchar(210) DEFAULT NULL COMMENT '事務担当者2の電話番号',
  `jimu_tantousha3_name_last` varchar(210) DEFAULT NULL COMMENT '事務担当者3の姓',
  `jimu_tantousha3_name_first` varchar(210) DEFAULT NULL COMMENT '事務担当者3の名',
  `jimu_tantousha3_tel` varchar(210) DEFAULT NULL COMMENT '事務担当者3の電話番号',
  `dantai_kbn` varchar(210) DEFAULT NULL COMMENT '団体区分',
  `katsudou_kuiki_kbn` int DEFAULT NULL COMMENT '活動区域区分',
  `umu_shikin_kanren_dantai` int DEFAULT NULL COMMENT '資金管理団体の有無',
  `koushoku_name` varchar(210) DEFAULT NULL COMMENT '公職の名称',
  `koushoku_gen_kouho` varchar(210) DEFAULT NULL COMMENT '現職候補者の別',
  `shikin_daihyou_name1` varchar(210) DEFAULT NULL COMMENT '資金管理団体の設立者の姓',
  `shikin_daihyou_name2` varchar(210) DEFAULT NULL COMMENT '資金管理団体の設立者の名',
  `kanri_dantai_period_start` varchar(210) DEFAULT NULL COMMENT '資金管理団体の指定期間(開始)',
  `kanri_dantai_period_end` varchar(210) DEFAULT NULL COMMENT '資金管理団体の指定期間(終了)',
  `kanri_dantai_period_rest` varchar(210) DEFAULT NULL COMMENT '資金管理団体の複数指定期間',
  `kokkai_giin_dantai_kbn` int DEFAULT NULL COMMENT '国会議員関連団体区分',
  `kokkai_giin1_name_last` varchar(210) DEFAULT NULL COMMENT '国家議員1の姓',
  `kokkai_giin1_name_first` varchar(210) DEFAULT NULL COMMENT '国家議員1の名',
  `kokkai_giin1_shuu_san` varchar(210) DEFAULT NULL COMMENT '国家議員1の公職(衆参)',
  `kokkai_giin1_gen_kouho` varchar(210) DEFAULT NULL COMMENT '国家議員1現職と候補者の別',
  `giin_dantantai_tokurei_period_start` varchar(210) DEFAULT NULL COMMENT '国会議員関係団体の特例適用期間(開始)',
  `giin_dantantai_tokurei_period_end` varchar(210) DEFAULT NULL COMMENT '国会議員関係団体の特例適用期間(終了)',
  `giin_dantantai_tokurei_period_rest` varchar(210) DEFAULT NULL COMMENT '国会議員関係団体の複数特例適用期間',
  `kokkai_giin2_name_last` varchar(210) DEFAULT NULL COMMENT '国家議員2の姓',
  `kokkai_giin2_name_first` varchar(210) DEFAULT NULL COMMENT '国家議員2の名 ',
  `kokkai_giin2_shuu_san` varchar(210) DEFAULT NULL COMMENT '国家議員2の公職(衆参)',
  `kokkai_giin2_gen_kouho` varchar(210) DEFAULT NULL COMMENT '国家議員2現職と候補者の別',
  `kokkai_giin3_name_last` varchar(210) DEFAULT NULL COMMENT '国家議員3の姓',
  `kokkai_giin3_name_first` varchar(210) DEFAULT NULL COMMENT '国家議員3の名',
  `kokkai_giin3_shuu_san` varchar(210) DEFAULT NULL COMMENT '国家議員3の公職(衆参)',
  `kokkai_giin3_gen_kouho` varchar(210) DEFAULT NULL COMMENT '国家議員3現職と候補者の別',
  `flg_recipt_copy` int DEFAULT NULL COMMENT '領収書の写しの有無',
  `flg_kansa_ikensho` int DEFAULT NULL COMMENT '監査意見書の有無',
  `flg_seijishikin_hohkokusho` int DEFAULT NULL COMMENT '政治資金監査報告書',
  `date_oath` varchar(40) DEFAULT NULL COMMENT '宣誓日',
  `kaikei_sekininsha_name_last` varchar(210) DEFAULT NULL COMMENT '会計責任者姓名の姓',
  `kaikei_sekininsha_name_first` varchar(210) DEFAULT NULL COMMENT '会計責任者姓名の名',
  `dantai_name01` varchar(300) DEFAULT NULL COMMENT '団体名(その1実記)',
  `dantai_name20` varchar(300) DEFAULT NULL COMMENT '団体名(その20実記)',
  `daihyousha_kaisan_name_last` varchar(300) DEFAULT NULL COMMENT '解散時の代表者姓名の姓',
  `daihyousha_kaisan_name_first` varchar(300) DEFAULT NULL COMMENT '解散時の代表者姓名の名',
  `insert_user_id` bigint DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザ同一識別コード',
  `insert_user_name` varchar(300) DEFAULT NULL COMMENT '挿入ユーザ姓名',
  `insert_timestamp` DATETIME NULL DEFAULT NULL COMMENT '挿入タイムスタンプ',
  `update_user_id` bigint DEFAULT NULL COMMENT '更新ユーザId',
  `update_user_code` int DEFAULT NULL COMMENT '更新ユーザ同一識別コード',
  `update_user_name` varchar(300) DEFAULT NULL COMMENT '更新ユーザ姓名',
  `update_timestamp` DATETIME NULL DEFAULT NULL COMMENT '更新タイムスタンプ',
  PRIMARY KEY (`offering_balancesheet_0701_and_0720_surface_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
