package mitei.mitei.investigate.report.balance.politician.service.zip_upload; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.constants.DocumentRecognizeKeyConstants;
import mitei.mitei.investigate.report.balance.politician.constants.GetCurrentResourcePath;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.poli_org.balancesheet.report.ReadXmlByFileCapsuleDto;
import mitei.mitei.investigate.report.balance.politician.dto.storage.SaveStorageResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDetailRepository;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.CreateTestPrivilegeDtoUtil;

/**
 * ZipUploadService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
class ZipUploadDocumentServiceTest { // NOPMD
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private ZipUploadDocumentService zipUploadDocumentService;

    /** propertiesからインジェクションされた最上位保存フォルダ絶対パス */
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

    /** 展開フォルダ */
    private String uncompress;

    /**
     * 展開フォルダを取得する
     *
     * @return 展開フォルダ
     */
    public String getUncompress() {
        return uncompress;
    }

    /**
     * 展開フォルダを設定する
     *
     * @param uncompress 展開フォルダ
     */
    public void setUncompress(final String uncompress) {
        this.uncompress = uncompress;
    }

    /** 政治資金収支報告書解析予定Repository */
    @Autowired
    private TaskPlanBalancesheetDetailRepository taskPlanBalancesheetDetailRepository;

    /** 政党交付金使途報告書解析予定Repository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    
    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("truncate_task_plan_balancesheet_detail.sql")
    void testPractcieBalancesheet() throws Exception {

        ReadXmlByFileCapsuleDto capsuleDto = new ReadXmlByFileCapsuleDto();
        String fileName = "all.zip";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/zip", fileName);

        String fileContent = Base64.getEncoder().encodeToString(Files.readAllBytes(path));

        capsuleDto.setFileName(fileName);
        capsuleDto.setFileContent(fileContent);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        capsuleDto.setCheckPrivilegeDto(privilegeDto);

        LocalDateTime datetimeShori = LocalDateTime.of(2024, 6, 13, 1, 2, 3);

        capsuleDto.setDocumentKey(DocumentRecognizeKeyConstants.SOFT_BALANCESHEET);
        SaveStorageResultDto saveStorageResultDto = zipUploadDocumentService.practcie(datetimeShori, capsuleDto);

        // 実ファイルリスト
        Path copyPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), uncompress);

        Predicate<? super Path> isRegularFile = p -> {
            return Files.isRegularFile(p);
        };
        List<Path> copiedList = new ArrayList<>();
        Files.walk(copyPath).filter(isRegularFile).forEach(p1 -> copiedList.add(p1));

        // 解凍前に想定した解凍後のファイルパス
        List<Path> compressList = new ArrayList<>();
        Path compressPath;
        try (ZipFile zipFile = new ZipFile(path.toFile(), StandardCharsets.UTF_8)) {
            Iterator<? extends ZipEntry> ite = zipFile.entries().asIterator();
            while (ite.hasNext()) {
                ZipEntry zipEntry = ite.next();
                if (!zipEntry.isDirectory()) {
                    compressPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), uncompress,
                            zipEntry.getName());
                    compressList.add(compressPath);
                }
            }
        }

        for (Path p : copiedList) {
            assertTrue(compressList.contains(p), "解凍前の予想ファイルと実際に存在するファイルパスが一致");
        }

        List<TaskPlanBalancesheetDetailEntity> list = taskPlanBalancesheetDetailRepository.findAll();
        // 対象ファイルの中に3件含まれているので予定登録も3件
        assertEquals(3,list.size(),"3件");

        Path databasePath;
        for (TaskPlanBalancesheetDetailEntity entity : list) {
            databasePath = Paths.get(storageFolder, entity.getFullPath());
            assertTrue(compressList.contains(databasePath), "解凍前の予想ファイルとデータベースに登録されたファイルパスが一致");
        }

    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("truncate_task_plan_party_usage_detail.sql")
    void testPractciePartyUsage() throws Exception {

        ReadXmlByFileCapsuleDto capsuleDto = new ReadXmlByFileCapsuleDto();
        String fileName = "all.zip";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/zip", fileName);

        String fileContent = Base64.getEncoder().encodeToString(Files.readAllBytes(path));

        capsuleDto.setFileName(fileName);
        capsuleDto.setFileContent(fileContent);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        capsuleDto.setCheckPrivilegeDto(privilegeDto);

        LocalDateTime datetimeShori = LocalDateTime.of(2024, 6, 13, 1, 2, 3);

        capsuleDto.setDocumentKey(DocumentRecognizeKeyConstants.SOFT_PARTY_USAGE);
        SaveStorageResultDto saveStorageResultDto = zipUploadDocumentService.practcie(datetimeShori, capsuleDto);

        // 実ファイルリスト
        Path copyPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), uncompress);

        Predicate<? super Path> isRegularFile = p -> {
            return Files.isRegularFile(p);
        };
        List<Path> copiedList = new ArrayList<>();
        Files.walk(copyPath).filter(isRegularFile).forEach(p1 -> copiedList.add(p1));

        // 解凍前に想定した解凍後のファイルパス
        List<Path> compressList = new ArrayList<>();
        Path compressPath;
        try (ZipFile zipFile = new ZipFile(path.toFile(), StandardCharsets.UTF_8)) {
            Iterator<? extends ZipEntry> ite = zipFile.entries().asIterator();
            while (ite.hasNext()) {
                ZipEntry zipEntry = ite.next();
                if (!zipEntry.isDirectory()) {
                    compressPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), uncompress,
                            zipEntry.getName());
                    compressList.add(compressPath);
                }
            }
        }

        for (Path p : copiedList) {
            assertTrue(compressList.contains(p), "解凍前の予想ファイルと実際に存在するファイルパスが一致");
        }

        List<TaskPlanPartyUsageDetailEntity> list = taskPlanPartyUsageDetailRepository.findAll();
        // 対象ファイルの中に3件含まれているので予定登録も3件
        assertEquals(3,list.size(),"予定登録も3件");

        Path databasePath;
        for (TaskPlanPartyUsageDetailEntity entity : list) {
            databasePath = Paths.get(storageFolder, entity.getFullPath());
            assertTrue(compressList.contains(databasePath), "解凍前の予想ファイルとデータベースに登録されたファイルパスが一致");
        }
    }
    
    @Test
    @Tag("LoadTest")
    @Transactional
    @Sql("truncate_task_plan_balancesheet_detail.sql")
    void testPractcieLoad() throws Exception {

        ReadXmlByFileCapsuleDto capsuleDto = new ReadXmlByFileCapsuleDto();
        String fileName = "2022_big_amount.zip";
        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "sample/zip", fileName);

        String fileContent = Base64.getEncoder().encodeToString(Files.readAllBytes(path));

        capsuleDto.setFileName(fileName);
        capsuleDto.setFileContent(fileContent);

        CheckPrivilegeDto privilegeDto = CreateTestPrivilegeDtoUtil.pracitce();
        capsuleDto.setCheckPrivilegeDto(privilegeDto);

        LocalDateTime datetimeShori = LocalDateTime.of(2024, 6, 13, 1, 2, 3);

        capsuleDto.setDocumentKey(DocumentRecognizeKeyConstants.SOFT_BALANCESHEET);
        SaveStorageResultDto saveStorageResultDto = zipUploadDocumentService.practcie(datetimeShori, capsuleDto);

        // 実ファイルリスト
        Path copyPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), uncompress);

        Predicate<? super Path> isRegularFile = p -> {
            return Files.isRegularFile(p);
        };
        List<Path> copiedList = new ArrayList<>();
        Files.walk(copyPath).filter(isRegularFile).forEach(p1 -> copiedList.add(p1));

        // 解凍前に想定した解凍後のファイルパス
        List<Path> compressList = new ArrayList<>();
        Path compressPath;
        try (ZipFile zipFile = new ZipFile(path.toFile(), StandardCharsets.UTF_8)) {
            Iterator<? extends ZipEntry> ite = zipFile.entries().asIterator();
            while (ite.hasNext()) {
                ZipEntry zipEntry = ite.next();
                if (!zipEntry.isDirectory()) {
                    compressPath = Paths.get(storageFolder, saveStorageResultDto.getChildDir(), uncompress,
                            zipEntry.getName());
                    compressList.add(compressPath);
                }
            }
        }

        for (Path p : copiedList) {
            assertTrue(compressList.contains(p), "解凍前の予想ファイルと実際に存在するファイルパスが一致");
        }

        List<TaskPlanBalancesheetDetailEntity> list = taskPlanBalancesheetDetailRepository.findAll();
        // 対象ファイルの中に3件含まれているので予定登録も202件
        assertEquals(202,list.size(),"予定登録も202件");

        Path databasePath;
        for (TaskPlanBalancesheetDetailEntity entity : list) {
            databasePath = Paths.get(storageFolder, entity.getFullPath());
            assertTrue(compressList.contains(databasePath), "解凍前の予想ファイルとデータベースに登録されたファイルパスが一致");
        }

    }

}
