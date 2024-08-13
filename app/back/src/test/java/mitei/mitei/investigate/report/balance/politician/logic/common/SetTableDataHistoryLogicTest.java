package mitei.mitei.investigate.report.balance.politician.logic.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.CheckPrivilegeDto;
import mitei.mitei.investigate.report.balance.politician.dto.common_check.DataHistoryStatusConstants;
import mitei.mitei.investigate.report.balance.politician.util.AllTabeDataHistoryInterfaceForTestEntity;

/**
 * SetTableDataHistoryLogic単体テスト
 */
class SetTableDataHistoryLogicTest {
    // CHECKSTYLE:OFF

    /** Long初期値 */
    private static final Long INIT_LONG = 0L;

    /** Integer初期値 */
    private static final Integer INIT_INTEGER = 0;

    /** String初期値 */
    private static final String INIT_STRING = "";

    /** Timestamp初期値 */
    private final Timestamp INIT_TIMESTAMP = Timestamp.valueOf(LocalDateTime.of(1980, 1, 1, 0, 0, 0));

    @Test
    void testPracticeInsert() {

        AllTabeDataHistoryInterfaceForTestEntity dataEntity = new AllTabeDataHistoryInterfaceForTestEntity();

        CheckPrivilegeDto checkPrivilegeDto = new CheckPrivilegeDto();
        final long ID = 123321L; // NOPMD
        final int CODE = 987;
        final String NAME = "ユーザ";

        checkPrivilegeDto.setLoginUserId(ID);
        checkPrivilegeDto.setLoginUserCode(CODE);
        checkPrivilegeDto.setLoginUserName(NAME);

        // 新規挿入時
        SetTableDataHistoryLogic setTableDataHistoryLogic = new SetTableDataHistoryLogic();
        setTableDataHistoryLogic.practice(checkPrivilegeDto, dataEntity, DataHistoryStatusConstants.INSERT);
        
        // データ挿入ユーザId
        assertThat(dataEntity.getInsertUserId()).isEqualTo(checkPrivilegeDto.getLoginUserId());
        // データ挿入ユーザ同一識別コード
        assertThat(dataEntity.getInsertUserCode()).isEqualTo(checkPrivilegeDto.getLoginUserCode());
        // データ挿入ユーザ名称
        assertThat(dataEntity.getInsertUserName()).isEqualTo(checkPrivilegeDto.getLoginUserName());
        // データ挿入時間
        // 実行時には誤差1秒以内におさまっているでしょう…
        assertTrue(dataEntity.getInsertTimestamp().getTime() > new Date().getTime() - 1000); // NOPMD
        assertTrue(dataEntity.getInsertTimestamp().getTime() < new Date().getTime() + 1000); // NOPMD

        // データ挿入ユーザId
        assertThat(dataEntity.getUpdateUserId()).isEqualTo(INIT_LONG);
        // データ挿入ユーザ同一識別コード
        assertThat(dataEntity.getUpdateUserCode()).isEqualTo(INIT_INTEGER);
        // データ挿入ユーザ名称
        assertThat(dataEntity.getUpdateUserName()).isEqualTo(INIT_STRING);
        // データ挿入時間
        assertTrue(INIT_TIMESTAMP.compareTo(dataEntity.getUpdateTimestamp()) == 0); // NOPMD
    }

    @Test
    void testPracticeUpdate() {

        AllTabeDataHistoryInterfaceForTestEntity dataEntity = new AllTabeDataHistoryInterfaceForTestEntity();

        CheckPrivilegeDto checkPrivilegeDto = new CheckPrivilegeDto();
        final long ID = 123321L; // NOPMD
        final int CODE = 987;
        final String NAME = "ユーザ";

        checkPrivilegeDto.setLoginUserId(ID);
        checkPrivilegeDto.setLoginUserCode(CODE);
        checkPrivilegeDto.setLoginUserName(NAME);

        // 更新時
        SetTableDataHistoryLogic setTableDataHistoryLogic = new SetTableDataHistoryLogic();
        setTableDataHistoryLogic.practice(checkPrivilegeDto, dataEntity, DataHistoryStatusConstants.UPDATE);

        // データ挿入ユーザId
        assertThat(dataEntity.getUpdateUserId()).isEqualTo(checkPrivilegeDto.getLoginUserId());
        // データ挿入ユーザ同一識別コード
        assertThat(dataEntity.getUpdateUserCode()).isEqualTo(checkPrivilegeDto.getLoginUserCode());
        // データ挿入ユーザ名称
        assertThat(dataEntity.getUpdateUserName()).isEqualTo(checkPrivilegeDto.getLoginUserName());
        // データ挿入時間
        // 実行時には誤差1秒以内におさまっているでしょう…
        assertTrue(dataEntity.getUpdateTimestamp().getTime() > new Date().getTime() - 1000); // NOPMD
        assertTrue(dataEntity.getUpdateTimestamp().getTime() < new Date().getTime() + 1000); // NOPMD

        // データ挿入ユーザId
        assertThat(dataEntity.getInsertUserId()).isEqualTo(INIT_LONG);
        // データ挿入ユーザ同一識別コード
        assertThat(dataEntity.getInsertUserCode()).isEqualTo(INIT_INTEGER);
        // データ挿入ユーザ名称
        assertThat(dataEntity.getInsertUserName()).isEqualTo(INIT_STRING);
        // データ挿入時間
        assertTrue(INIT_TIMESTAMP.compareTo(dataEntity.getInsertTimestamp()) == 0); // NOPMD
    }

}
