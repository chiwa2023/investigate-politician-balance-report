package mitei.mitei.investigate.report.balance.politician.logic.party_usage.y2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.common.constants.party.usage.ConstantsKbn0804Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.RowShitoCoreDto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Sheet0804Dto;
import mitei.mitei.common.publish.party.usage.report.dto.v5.Shito0804Dto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.poli_party.usage.y2025.OfferingPartyUsage0804Report2025Entity;
import mitei.mitei.investigate.report.balance.politician.repository.poli_party.usage.y2025.OfferingPartyUsage0804Report2025Repository;
import mitei.mitei.investigate.report.balance.politician.util.DateConvertUtil;
import mitei.mitei.investigate.report.balance.politician.util.FormatNaturalSearchTextUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 使途報告書様式8その4を保存する
 */
@Component
public class InsertPartyUsageShito0804Y2025Logic {

    /** 様式8その4区分01 */
    private static final int KBN01 = ConstantsKbn0804Dto.KBN01;
    /** 様式8その4区分02 */
    private static final int KBN02 = ConstantsKbn0804Dto.KBN02;
    /** 様式8その4区分03 */
    private static final int KBN03 = ConstantsKbn0804Dto.KBN03;
    /** 様式8その4区分04 */
    private static final int KBN04 = ConstantsKbn0804Dto.KBN04;
    /** 様式8その4区分05 */
    private static final int KBN05 = ConstantsKbn0804Dto.KBN05;
    /** 様式8その4区分06 */
    private static final int KBN06 = ConstantsKbn0804Dto.KBN06;
    /** 様式8その4区分07 */
    private static final int KBN07 = ConstantsKbn0804Dto.KBN07;
    /** 様式8その4区分08 */
    private static final int KBN08 = ConstantsKbn0804Dto.KBN08;
    /** 様式8その4区分09 */
    private static final int KBN09 = ConstantsKbn0804Dto.KBN09;
    /** 様式8その4区分10 */
    private static final int KBN10 = ConstantsKbn0804Dto.KBN10;
    /** 様式8その4区分11 */
    private static final int KBN11 = ConstantsKbn0804Dto.KBN11;
    /** 様式8その4区分12 */
    private static final int KBN12 = ConstantsKbn0804Dto.KBN12;

    /** 様式8その4区分01名称 */
    private static final String KBN01_NAME = ConstantsKbn0804Dto.KBN01_TEXT;
    /** 様式8その4区分02名称 */
    private static final String KBN02_NAME = ConstantsKbn0804Dto.KBN02_TEXT;
    /** 様式8その4区分03名称 */
    private static final String KBN03_NAME = ConstantsKbn0804Dto.KBN03_TEXT;
    /** 様式8その4区分04名称 */
    private static final String KBN04_NAME = ConstantsKbn0804Dto.KBN04_TEXT;
    /** 様式8その4区分05名称 */
    private static final String KBN05_NAME = ConstantsKbn0804Dto.KBN05_TEXT;
    /** 様式8その4区分06名称 */
    private static final String KBN06_NAME = ConstantsKbn0804Dto.KBN06_TEXT;
    /** 様式8その4区分07名称 */
    private static final String KBN07_NAME = ConstantsKbn0804Dto.KBN07_TEXT;
    /** 様式8その4区分08名称 */
    private static final String KBN08_NAME = ConstantsKbn0804Dto.KBN08_TEXT;
    /** 様式8その4区分09名称 */
    private static final String KBN09_NAME = ConstantsKbn0804Dto.KBN09_TEXT;
    /** 様式8その4区分10名称 */
    private static final String KBN10_NAME = ConstantsKbn0804Dto.KBN10_TEXT;
    /** 様式8その4区分11名称 */
    private static final String KBN11_NAME = ConstantsKbn0804Dto.KBN11_TEXT;
    /** 様式8その4区分12名称 */
    private static final String KBN12_NAME = ConstantsKbn0804Dto.KBN12_TEXT;

    /** 様式8その4Repository */
    @Autowired
    private OfferingPartyUsage0804Report2025Repository offeringPartyUsage0804Report2025Repository;

    /** 日付変換Utility */
    @Autowired
    private DateConvertUtil dateConvertUtil;

    /** 自然検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** 初期値(不要になったら削除) */
    private static final long INIT_LONG = 0L;

    /** 初期値(不要になったら削除) */
    private static final int INIT_INT = 0;

    /** 初期値(不要になったら削除) */
    private static final String INIT_STRING = "";

    /**
     * 登録作業を行う
     *
     * @param documentCode                           文書同一識別コード
     * @param partyUsageDocumentPoliticalPropertyDto 使途報告書文書属性
     * @param shito0804Dto                           様式8その4
     * @param checkPrivilegeDto                      権限Dto
     * @return 登録行数
     */
    public int practice(final boolean isSearchRelation, final Long documentCode,
            final PartyUsageDocumentPoliticalPropertyDto partyUsageDocumentPoliticalPropertyDto,
            final Shito0804Dto shito0804Dto, final CheckPrivilegeDto checkPrivilegeDto) {

        // すべてにク共通のEntityを作成
        OfferingPartyUsage0804Report2025Entity entityBase = new OfferingPartyUsage0804Report2025Entity();
        SetTableDataHistoryUtil.practice(checkPrivilegeDto, entityBase, DataHistoryStatusConstants.INSERT);
        BeanUtils.copyProperties(partyUsageDocumentPoliticalPropertyDto, entityBase);
        entityBase.setDocumentCode(documentCode);

        /* 区分1から12を順に処理 */
        List<OfferingPartyUsage0804Report2025Entity> list = new ArrayList<>();

        list.addAll(this.createEntityInSheet(KBN01, KBN01_NAME, entityBase, shito0804Dto.getKbn080401Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN02, KBN02_NAME, entityBase, shito0804Dto.getKbn080402Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN03, KBN03_NAME, entityBase, shito0804Dto.getKbn080403Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN04, KBN04_NAME, entityBase, shito0804Dto.getKbn080404Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN05, KBN05_NAME, entityBase, shito0804Dto.getKbn080405Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN06, KBN06_NAME, entityBase, shito0804Dto.getKbn080406Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN07, KBN07_NAME, entityBase, shito0804Dto.getKbn080407Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN08, KBN08_NAME, entityBase, shito0804Dto.getKbn080408Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN09, KBN09_NAME, entityBase, shito0804Dto.getKbn080409Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN10, KBN10_NAME, entityBase, shito0804Dto.getKbn080410Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN11, KBN11_NAME, entityBase, shito0804Dto.getKbn080411Dto().getList(),
                isSearchRelation));
        list.addAll(this.createEntityInSheet(KBN12, KBN12_NAME, entityBase, shito0804Dto.getKbn080412Dto().getList(),
                isSearchRelation));

        // 行ごとに同一識別コードを振番する
        Long code = 0L;
        Optional<OfferingPartyUsage0804Report2025Entity> optional = offeringPartyUsage0804Report2025Repository
                .findFirstByOrderByPartyUsage0804ReportCodeDesc();
        if (!optional.isEmpty()) {
            code = optional.get().getPartyUsage0804ReportCode();
        }
        for (OfferingPartyUsage0804Report2025Entity entity : list) {
            code++;
            entity.setPartyUsage0804ReportCode(code);
        }

        // repositoryで保存
        return offeringPartyUsage0804Report2025Repository.saveAll(list).size();
    }

    private List<OfferingPartyUsage0804Report2025Entity> createEntityInSheet(final int kbn, final String kbnName,
            final OfferingPartyUsage0804Report2025Entity entityBase, final List<Sheet0804Dto> listSheet,
            final boolean isSearchRelation) {

        // 明細が0であっても必ずシートが1枚はは入っている
        OfferingPartyUsage0804Report2025Entity entityReport = new OfferingPartyUsage0804Report2025Entity();

        BeanUtils.copyProperties(entityBase, entityReport);
        entityReport.setShishutsuKbn(kbn);
        entityReport.setShishutsuKbnName(kbnName);

        List<OfferingPartyUsage0804Report2025Entity> list = new ArrayList<>();

        for (Sheet0804Dto sheet : listSheet) {

            // コピーutilが使えないので1件づつ対応(行とパラメータがかぶっている)
            // BeanUtils.copyProperties(sheet0804Dto, entityReport);
            // sheet0804Dto.getHimoku();sheet_himoku
            entityReport.setSheetHimoku(sheet.getHimoku());
            // sheet0804Dto.getAmountAll();sheet_amount_all
            entityReport.setSheetAmountAll(sheet.getAmountAll());
            // sheet0804Dto.getAmountAllKoufukin();sheet_amount_all_koufukin
            entityReport.setSheetAmountAllKoufukin(sheet.getAmountAllKoufukin());
            // sheet0804Dto.getAmountAllMyFunds();sheet_amount_all_my_funds
            entityReport.setSheetAmountAllMyFunds(sheet.getAmountAllMyFunds());
            // sheet0804Dto.getSonotaAmount();//sheet_amount_sonota
            entityReport.setSheetAmountSonota(sheet.getSonotaAmount());
            // sheet0804Dto.getSonotaKoufukin();//sheet_amount_sonota_koufukin
            entityReport.setSheetAmountSonotaKoufukin(sheet.getSonotaKoufukin());
            // sheet0804Dto.getSonotaMyFunds();//sheet_amount_sonota_my_funds
            entityReport.setSheetAmountSonotaMyFunds(sheet.getSonotaMyFunds());

            list.addAll(this.createEntityInRow(entityReport, sheet.getList(), isSearchRelation));
        }

        return list;
    }

    private List<OfferingPartyUsage0804Report2025Entity> createEntityInRow(
            final OfferingPartyUsage0804Report2025Entity entityBase, final List<RowShitoCoreDto> listRow,
            final boolean isSearchRelation) {

        List<OfferingPartyUsage0804Report2025Entity> list = new ArrayList<>();

        // シートがあるが明細がない場合、1件はシートがあることを示すために戻す
        if (listRow.isEmpty()) {
            list.add(entityBase);
            return list;
        }

        for (RowShitoCoreDto rowDto : listRow) {
            list.add(this.createEntity(entityBase, rowDto, isSearchRelation));
        }

        return list;
    }

    private OfferingPartyUsage0804Report2025Entity createEntity(final OfferingPartyUsage0804Report2025Entity entityBase,
            final RowShitoCoreDto rowDto, final boolean isSearchRelation) {

        OfferingPartyUsage0804Report2025Entity entity = new OfferingPartyUsage0804Report2025Entity();
        BeanUtils.copyProperties(entityBase, entity);

        // NOTE:あれ？この明細は集計値登録して無駄じゃね？と疑問に思われる方も多いと思います(ぶっちゃけいらないと思う)
        // 同じレベルで保存されているシート費目、各区分(名称・コード値)は調査実務上、なくてはならないため
        // (大量に検索出力されるのにいちいち検索された時点で不足情報を補充するなんてやってられない)、
        // そのうえで集計値6項目だけテーブルを分けるかどうか勘案したところこのようなテーブル構造になった

        BeanUtils.copyProperties(rowDto, entity);

        // 検索時日付情報で絞る可能性を考慮する
        entity.setAccrualDateValue(dateConvertUtil.practiceWarekiToLocalDate(entity.getAccrualDate()));
        // row.getAccrualDate();accrual_date_value

        // TODO 関連者紐づけ処理を行う(現段階では初期値を明示)

        // 将来必ず支払い関連者情報は必要となるのでコピーはしないがテーブルとして補っておく
        // `payee_relation_kbn` int DEFAULT NULL COMMENT '支払者関連者区分',
        entity.setRelationKbnPayee(INIT_INT);
        // `relation_person_id_payee` bigint DEFAULT NULL COMMENT '支払者関連者Id',
        entity.setRelationPersonIdPayee(INIT_LONG);
        // `relation_person_code_delegate` int DEFAULT NULL COMMENT '支払者関連者同一識別コード',
        entity.setRelationPersonCodePayee(INIT_INT);
        // `relation_person_name_delegate` varchar(210) DEFAULT NULL COMMENT '支払者関連者名称',
        entity.setRelationPersonNamePayee(INIT_STRING);
        // `relation_corp_id_delegate` bigint DEFAULT NULL COMMENT '支払者関連者(法人)Id',
        entity.setRelationCorpIdPayee(INIT_LONG);
        // `relation_corp_code_delegate` int DEFAULT NULL COMMENT '支払者関連者(法人)同一識別コード',
        entity.setRelationCorpCodePayee(INIT_INT);
        // `relation_corp_name_delegate` varchar(210) DEFAULT NULL COMMENT //
        // '支払者関連者(法人)名称',
        entity.setRelationCorpNamePayee(INIT_STRING);
        // `relation_political_org_id_delegate` bigint DEFAULT NULL COMMENT //
        // '支払者関連者(政治団体)Id',
        entity.setRelationPoliticalOrgIdPayee(INIT_LONG);
        // `relation_political_org_code_delegate` int DEFAULT NULL COMMENT //
        // '支払者関連者(政治団体)同一識別コード',
        entity.setRelationPoliticalOrgCodePayee(INIT_INT);
        // `relation_political_org_name_delegate` varchar(210) DEFAULT NULL COMMENT //
        // '支払者関連者(政治団体)名称',
        entity.setRelationPoliticalOrgNamePayee(INIT_STRING);

        // 自由検索の対象
        StringBuilder builder = new StringBuilder();
        builder.append(rowDto.getUsageItem()).append(rowDto.getPayeeName()).append(rowDto.getAddress());
        entity.setSearchWords(formatNaturalSearchTextUtil.practice(builder.toString()));

        return entity;
    }
}
