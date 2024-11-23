package mitei.mitei.investigate.report.balance.politician.service.offering.poli_org;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.investigate.report.balance.politician.constants.DocumentRecognizeKeyConstants;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanBalancesheetDetailEntity;
import mitei.mitei.investigate.report.balance.politician.entity.TaskPlanPartyUsageDetailEntity;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanBalancesheetDetailRepository;
import mitei.mitei.investigate.report.balance.politician.repository.TaskPlanPartyUsageDetailRepository;
import mitei.mitei.investigate.report.balance.politician.util.DecideXmlFileCharsetUtil;
import mitei.mitei.investigate.report.balance.politician.util.SetTableDataHistoryUtil;

/**
 * 政治資金収支報告書一括解析予定データを更新する
 */
@Service
@Transactional
@ConfigurationProperties(prefix = "mitei.mitei.investigate.report.balance.politician")
public class UpdateBalancesheetPlanService {

    /** 政治資金収支報告書一括処理タスク計画Repository */
    @Autowired
    private TaskPlanBalancesheetDetailRepository taskPlanBalancesheetDetailRepository;

    /** 政党交付金使途報告書一括処理タスク計画Repository */
    @Autowired
    private TaskPlanPartyUsageDetailRepository taskPlanPartyUsageDetailRepository;

    /** 文字コード決定Utility */
    @Autowired
    private DecideXmlFileCharsetUtil decideXmlFileCharsetUtil;

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

    /**
     * 更新処理を行う
     *
     * @param detailEntity タスク計画詳細Enetity
     * @param privilegeDto 権限確認Dto
     * @return 更新数(更新成功したら1)
     * @throws EmptyResultDataAccessException 元データを呼び出せない例外
     * @throws IOException                    ファイルが存在しないなどの例外
     */
    public long practice( // SUPPRESS CHECKSTYLE ReturnCountCheck
            final TaskPlanBalancesheetDetailEntity detailEntity, final CheckPrivilegeDto privilegeDto)
            throws EmptyResultDataAccessException, IOException { // NOPMD

        // 呼び出し元データを更新にして参照できないようにする
        Optional<TaskPlanBalancesheetDetailEntity> optopnal = taskPlanBalancesheetDetailRepository
                .findById(detailEntity.getTaskPlanBalancesheetDetailId());

        if (optopnal.isEmpty()) {
            throw new EmptyResultDataAccessException("編集しようとしたデータをIdで呼び出そうとしましたができません", 0);
        }

        String key;
        // 文書を政党交付金使途報告書に変更すると指定された場合は政党交付金、その他の場合は政治資金収支報告書
        if (DocumentRecognizeKeyConstants.SOFT_PARTY_USAGE.equals(detailEntity.getDocumentKey())) {
            key = DocumentRecognizeKeyConstants.SOFT_PARTY_USAGE;
        } else {
            key = DocumentRecognizeKeyConstants.SOFT_BALANCESHEET;
        }

        // 仮読み取りをしてみて読み取りできなかったら処理中断(ユーザさん指定ミスなのでシステム例外でない)
        Path path = Paths.get(storageFolder, detailEntity.getFullPath());

        Charset charset = decideXmlFileCharsetUtil.practice(path, key);
        String charsetName;
        if (Objects.isNull(charset)) {
            // TODO 指定が間違っているログを出す
            // TODO 指定された文字コードで読めるかチャレンジする?
            return 0L;
        } else {
            charsetName = charset.toString();
        }

        TaskPlanBalancesheetDetailEntity originalEntity = optopnal.get();
        SetTableDataHistoryUtil.practice(privilegeDto, originalEntity, DataHistoryStatusConstants.UPDATE);
        taskPlanBalancesheetDetailRepository.save(originalEntity);

        // 政治資金収支報告書ワークテーブルにデータは残さない
        if (DocumentRecognizeKeyConstants.SOFT_PARTY_USAGE.equals(key)) {

            TaskPlanPartyUsageDetailEntity partyUsageEntity = new TaskPlanPartyUsageDetailEntity();
            BeanUtils.copyProperties(detailEntity, partyUsageEntity);

            Long code = 1L;
            Optional<TaskPlanPartyUsageDetailEntity> optionalParty = taskPlanPartyUsageDetailRepository
                    .findFirstByOrderByTaskPlanPartyUsageDetailCodeDesc();
            if (!optionalParty.isEmpty()) {
                code = code + optionalParty.get().getTaskPlanPartyUsageDetailCode();
            }

            partyUsageEntity.setCharset(charsetName);
            partyUsageEntity.setTaskPlanPartyUsageDetailCode(code);

            return taskPlanPartyUsageDetailRepository.save(partyUsageEntity).getTaskPlanPartyUsageDetailId();
        }

        // 同一識別コードは同じで新規追加とする
        TaskPlanBalancesheetDetailEntity insertEntity = new TaskPlanBalancesheetDetailEntity();
        BeanUtils.copyProperties(detailEntity, insertEntity);
        insertEntity.setCharset(charsetName);
        SetTableDataHistoryUtil.practice(privilegeDto, insertEntity, DataHistoryStatusConstants.INSERT);
        insertEntity.setTaskPlanBalancesheetDetailId(0L);

        return taskPlanBalancesheetDetailRepository.save(insertEntity).getTaskPlanBalancesheetDetailId();
    }
}
