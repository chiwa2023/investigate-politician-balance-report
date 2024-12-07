package mitei.mitei.investigate.report.balance.politician.batch.poli_party.usgae.regist.wktbl; // NOPMD

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.common.publish.party.usage.report.dto.v5.AllShitoBook;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.PartyUsageDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDocumentEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliPartyUsageReportEntity;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0801And0807Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0802And0803Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0802Kbn02Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0804Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0805Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0806Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0901Logic;
import mitei.mitei.investigate.report.balance.politician.logic.party_usage.InsertPartyUsageShito0902Logic;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDocumentRepository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書を登録する
 */
@Component
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
public class AllSitoBookItemWriter extends JpaItemWriter<WkTblPoliPartyUsageReportEntity> {

    /** 様式8その1とその7 */
    @Autowired
    private InsertPartyUsageShito0801And0807Logic insertPartyUsageShito0801And0807Logic;

    /** 様式8その2とその3 */
    @Autowired
    private InsertPartyUsageShito0802And0803Logic insertPartyUsageShito0802And0803Logic;

    /** 様式8その2区分2 */
    @Autowired
    private InsertPartyUsageShito0802Kbn02Logic insertPartyUsageShito0802Kbn02Logic;

    /** 様式8その4 */
    @Autowired
    private InsertPartyUsageShito0804Logic insertPartyUsageShito0804Logic;

    /** 様式8その5 */
    @Autowired
    private InsertPartyUsageShito0805Logic insertPartyUsageShito0805Logic;

    /** 様式8その6 */
    @Autowired
    private InsertPartyUsageShito0806Logic insertPartyUsageShito0806Logic;

    /** 様式9 */
    @Autowired
    private InsertPartyUsageShito0901Logic insertPartyUsageShito0901Logic;

    /** 様式9その2 */
    @Autowired
    private InsertPartyUsageShito0902Logic insertPartyUsageShito0902Logic;

    /** 登録済Idと文書同一識別コード一時保存Repository */
    @Autowired
    private TaskPlanPartyUsageDocumentRepository taskPlanPartyUsageDocumentRepository;

    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(AllSitoBookItemWriter.class);

    /** 保存親フォルダ */
    private String storageFolder;

    /**
     * 最上位保存フォルダ絶対パスを取得する
     *
     * @return 最上位保存フォルダ絶対パス
     */
    public String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 最上位保存フォルダ絶対パスを設定する
     *
     * @param storageFolder 最上位保存フォルダ絶対パス
     */
    public void setStorageFolder(final String storageFolder) {
        this.storageFolder = storageFolder;
    }

    /**
     * コンストラクタ(EntityManagerFactoryをセットする必要がある)
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public AllSitoBookItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblPoliPartyUsageReportEntity> items) {

        PartyUsageDocumentPoliticalPropertyDto documentPropertyDto;
        CheckPrivilegeDto checkPrivilegeDto;
        AllShitoBook allShitoBook;
        for (WkTblPoliPartyUsageReportEntity entity : items.getItems()) {
            // 文書属性Dtoを復元
            documentPropertyDto = new PartyUsageDocumentPoliticalPropertyDto(); // NOPMD
            BeanUtils.copyProperties(entity, documentPropertyDto);

            // 権限確認Dtoを復元
            checkPrivilegeDto = new CheckPrivilegeDto(); // NOPMD
            checkPrivilegeDto.setLoginUserId(items.getItems().get(0).getInsertUserId());
            checkPrivilegeDto.setLoginUserCode(items.getItems().get(0).getInsertUserCode());
            checkPrivilegeDto.setLoginUserName(items.getItems().get(0).getInsertUserName());

            boolean isSearchRelation = true;
            // 正しく変換できた時だけ処理を行う
            // ワークテーブルデータさえ処理済にしなければ処理対象として残る(何回もtryして効率は悪いが…)
            // といっても計画予定の時にすでにファイル存在チェックは済んでいるのでシステム環境エラーしか想定していない
            allShitoBook = this.convertPractice(entity);
            if (!Objects.isNull(allShitoBook)) {
                /* ここから登録 */
                //log.info("---------"+entity.getTaskPlanPartyUsageDetailId() + "anlysis");

                // 8号様式表紙と宣誓書
                Long documentCode = insertPartyUsageShito0801And0807Logic.practice(documentPropertyDto, allShitoBook,
                        checkPrivilegeDto);

                // 8号様式その2と3:収支の総括表
                int sizeSummary = insertPartyUsageShito0802And0803Logic.practice(documentCode, documentPropertyDto,
                        allShitoBook.getShito0802Dto(), allShitoBook.getShito0803Dto(), checkPrivilegeDto);

                // 8号様式その2区分2
                int size2 = insertPartyUsageShito0802Kbn02Logic.practice(documentCode, documentPropertyDto,
                        allShitoBook.getShito0802Dto().getSheet0802Dto().getKbn080202Dto(), checkPrivilegeDto);

                // 8号様式その4
                int size4 = insertPartyUsageShito0804Logic.practice(isSearchRelation, documentCode, documentPropertyDto,
                        allShitoBook.getShito0804Dto(), checkPrivilegeDto);

                // 8号様式その5:支部政党交付金の内訳
                int size5 = insertPartyUsageShito0805Logic.practice(documentCode, documentPropertyDto,
                        allShitoBook.getShito0805Dto(), checkPrivilegeDto);

                // 8号様式その6:政党基金の内訳
                int size6 = insertPartyUsageShito0806Logic.practice(documentCode, documentPropertyDto,
                        allShitoBook.getShito0806Dto(), checkPrivilegeDto);

                // 9号様式:領収書を徴しがたかった明細書
                int size9 = insertPartyUsageShito0901Logic.practice(documentCode, documentPropertyDto,
                        allShitoBook.getShito0901Dto(), checkPrivilegeDto);

                // 9号様式その2:振込明細書にかかる支出目的書
                int size92 = insertPartyUsageShito0902Logic.practice(documentCode, documentPropertyDto,
                        allShitoBook.getShito0902Dto(), checkPrivilegeDto);

                // TODO 成功ログを記録する場合は修正する
                int all = 1 + sizeSummary + size2 + size4 + size5 + size6 + size9 + size92;
                log.info(all + "update row");

                // 一時テーブルに処理実行情報を退避
                taskPlanPartyUsageDocumentRepository.save(this.createDocumentEntity(checkPrivilegeDto,
                        entity.getWkTblPoliPartyUsageReportId(), documentCode));
            }
        }

    }

    private AllShitoBook convertPractice( // SUPPRESS CHECKSTYLE ReturnCheckCount
            final WkTblPoliPartyUsageReportEntity entity) {
        try {

            // 公式XML読み取り
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.setSerializationInclusion(Include.ALWAYS);
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            Path path = Paths.get(storageFolder, entity.getFullPath());
            return xmlMapper.readValue(Files.readString(path, Charset.forName(entity.getCharset())),
                    new TypeReference<>() {
                    });
        } catch (Exception exception) { // NOPMD
            // TODO Logの形式が決まり次第修正する
            log.error("XML読み取りで例外");
            for (StackTraceElement traceElement : exception.getStackTrace()) {
                log.error(traceElement.toString());
            }
            return null;
        }
    }

    /* タスク計画文書同一識別コード関連テーブルEntity生成 */
    private TaskPlanPartyUsageDocumentEntity createDocumentEntity(final CheckPrivilegeDto privilegeDto,
            final Long detaiId, final Long documentCode) {

        TaskPlanPartyUsageDocumentEntity documentEntity = new TaskPlanPartyUsageDocumentEntity();
        SetTableDataHistoryUtil.practice(privilegeDto, documentEntity, DataHistoryStatusConstants.INSERT);
        documentEntity.setTaskPlanPartyUsageDetailId(0L);
        documentEntity.setTaskPlanPartyUsageDetailId(detaiId);
        documentEntity.setDocumentCode(documentCode);

        return documentEntity;
    }

}
