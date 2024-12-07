package mitei.mitei.investigate.report.balance.politician.batch.poli_org.balancesheet.regist; // NOPMD

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
import mitei.mitei.common.publish.politician.balancesheet.report.dto.v5.AllBookDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.dto.political_organization.BalancesheetReportDocumentPoliticalPropertyDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDocumentEntity;
import mitei.mitei.investigate.report.balance.politician.entity.WkTblPoliOrgBalancesheetReportEntity;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganization08000Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganization0802Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganizationEstateAllLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganizationIncomeAllLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganizationOutcomeAllLogic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganizationSheet0701And0720Logic;
import mitei.mitei.investigate.report.balance.politician.logic.poli_org.balancesheet.InsertPoliticalOrganizationSummaryLogic;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDocumentRepository;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書を登録する
 */
@Component
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
public class AllBookItemWriter extends JpaItemWriter<WkTblPoliOrgBalancesheetReportEntity> {

    /** 政治資金収支報告書様式8領収書を徴しがたかったもの登録Logic */
    @Autowired
    private InsertPoliticalOrganization08000Logic insertPoliticalOrganization08000Logic;

    /** 政治資金収支報告書様式8その2支出項目別内訳登録Logic */
    @Autowired
    private InsertPoliticalOrganization0802Logic insertPoliticalOrganization0802Logic;

    /** 政治資金収支報告書資産登録Logic */
    @Autowired
    private InsertPoliticalOrganizationEstateAllLogic insertPoliticalOrganizationEstateAllLogic;

    /** 政治資金収支報告書収入登録Logic */
    @Autowired
    private InsertPoliticalOrganizationIncomeAllLogic insertPoliticalOrganizationIncomeAllLogic;

    /** 政治資金収支報告書支出登録Logic */
    @Autowired
    private InsertPoliticalOrganizationOutcomeAllLogic insertPoliticalOrganizationOutcomeAllLogic;

    /** 政治資金収支報告書表紙と誓約書登録Logic */
    @Autowired
    private InsertPoliticalOrganizationSheet0701And0720Logic insertPoliticalOrganizationSheet0701And0720Logic;

    /** 政治資金収支報告書表集計表登録Logic */
    @Autowired
    private InsertPoliticalOrganizationSummaryLogic insertPoliticalOrganizationSummaryLogic;

    /** 登録済Idと文書同一識別コード一時保存Repository */
    @Autowired
    private TaskPlanBalancesheetDocumentRepository taskPlanBalancesheetDocumentRepository;

    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(AllBookItemWriter.class);

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
    public AllBookItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblPoliOrgBalancesheetReportEntity> items) {

        BalancesheetReportDocumentPoliticalPropertyDto documentPropertyDto;
        CheckPrivilegeDto checkPrivilegeDto;
        AllBookDto allBookDto;
        for (WkTblPoliOrgBalancesheetReportEntity entity : items.getItems()) {
            // 文書属性Dtoを復元
            documentPropertyDto = new BalancesheetReportDocumentPoliticalPropertyDto(); // NOPMD
            BeanUtils.copyProperties(entity, documentPropertyDto);

            // 権限確認Dtoを復元
            checkPrivilegeDto = new CheckPrivilegeDto(); // NOPMD
            checkPrivilegeDto.setLoginUserId(items.getItems().get(0).getInsertUserId());
            checkPrivilegeDto.setLoginUserCode(items.getItems().get(0).getInsertUserCode());
            checkPrivilegeDto.setLoginUserName(items.getItems().get(0).getInsertUserName());

            // 正しく変換できた時だけ処理を行う
            // ワークテーブルデータさえ処理済にしなければ処理対象として残る(何回もtryして効率は悪いが…)
            // といっても計画予定の時にすでにファイル存在チェックは済んでいるのでシステム環境エラーしか想定していない
            allBookDto = this.convertPractice(entity);
            if (!Objects.isNull(allBookDto)) {
                /* ここから登録 */

                // 政治資金収支報告書表紙と宣誓書(その1と20)
                Long documentCode = insertPoliticalOrganizationSheet0701And0720Logic.practice(documentPropertyDto,
                        allBookDto, checkPrivilegeDto);

                // 政治資金収支報告集計表
                int sizeSummary = insertPoliticalOrganizationSummaryLogic.practice(documentCode, documentPropertyDto,
                        allBookDto.getAllSheet0702SummaryTableIncomeDto(),
                        allBookDto.getAllSheet0713ListOfExpenditureItemsDto(),
                        allBookDto.getAllSheet0717SummaryTableOfAssetsDto(), checkPrivilegeDto);

                // 政治資金収支報告収入
                int sizeIncome = insertPoliticalOrganizationIncomeAllLogic.practice(documentCode, documentPropertyDto,
                        allBookDto, checkPrivilegeDto);

                // 政治資金収支報告支出
                int sizeOutcome = insertPoliticalOrganizationOutcomeAllLogic.practice(documentCode, documentPropertyDto,
                        allBookDto, checkPrivilegeDto);

                // 政治資金収支報告資産
                int sizeEstate = insertPoliticalOrganizationEstateAllLogic.practice(documentCode, documentPropertyDto,
                        allBookDto.getAllSheet0718AssetsDto(), allBookDto.getAllSheet0719RealEstateDto(),
                        checkPrivilegeDto);

                // 政治資金収支報告
                int size0800 = insertPoliticalOrganization08000Logic.practice(documentCode, documentPropertyDto,
                        allBookDto.getAllSheet0800DifficultCollectReceiptDto(), checkPrivilegeDto);

                // 政治資金収支報告
                int size0802 = insertPoliticalOrganization0802Logic.practice(documentCode, documentPropertyDto,
                        allBookDto.getAllSheet0802WithdrawalItemsByTransferDto(), checkPrivilegeDto);

                // TODO 成功ログを記録する場合は修正する
                int all = 1 + sizeSummary + sizeIncome + sizeOutcome + sizeEstate + size0800 + size0802;
                log.info(all + "行更新しました");

                // 一時テーブルに処理実行情報を退避
                taskPlanBalancesheetDocumentRepository.save(this.createDocumentEntity(checkPrivilegeDto,
                        entity.getWkTblPoliOrgBalancesheetReportId(), documentCode));
            }
        }

    }

    private AllBookDto convertPractice( // SUPPRESS CHECKSTYLE ReturnCheckCount
            final WkTblPoliOrgBalancesheetReportEntity entity) {
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
    private TaskPlanBalancesheetDocumentEntity createDocumentEntity(final CheckPrivilegeDto privilegeDto,
            final Long detaiId, final Long documentCode) {

        TaskPlanBalancesheetDocumentEntity documentEntity = new TaskPlanBalancesheetDocumentEntity();
        SetTableDataHistoryUtil.practice(privilegeDto, documentEntity, DataHistoryStatusConstants.INSERT);
        documentEntity.setTaskPlanBalancesheetDocumentId(0L);
        documentEntity.setTaskPlanBalancesheetDetailId(detaiId);
        documentEntity.setDocumentCode(documentCode);

        return documentEntity;
    }

}
