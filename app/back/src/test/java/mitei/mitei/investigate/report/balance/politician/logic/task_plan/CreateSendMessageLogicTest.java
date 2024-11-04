package mitei.mitei.investigate.report.balance.politician.logic.task_plan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import mitei.mitei.investigate.report.balance.politician.entity.TaskInfoEntity;

/**
 * CreateSendMessageLogic単体テスト
 */
class CreateSendMessageLogicTest {

    @Test
    void testPractice() {

        CreateSendMessageLogic createSendMessageLogic = new CreateSendMessageLogic();

        // Entityがnullの場合は処理中断(実装ミス)
        assertThrows(IllegalArgumentException.class, () -> createSendMessageLogic.practice("user", null));

        // 初期化を空文字にしているので基本的にはないが、万が一entity以外のすべての使用する要素が
        // nullでも空文字初期化を再度やり直して落ちない
        TaskInfoEntity entity01 = new TaskInfoEntity();
        entity01.setMessageTemplate(null);
        entity01.setTransferPass(null);
        entity01.setParamQuery(null);
        assertDoesNotThrow(() -> createSendMessageLogic.practice(null, entity01));

        TaskInfoEntity entity02 = new TaskInfoEntity();
        entity02.setMessageTemplate("【ユーザ名】さん　処理すべき新たなタスクが発生しました。【遷移先】にアクセスしてタスクの処理を行ってください");
        entity02.setTransferPass("http://localhost:8080/dummy-page");
        entity02.setParamQuery("?id=1&code=2");
        String userName = "作業員　実";

        assertThat(createSendMessageLogic.practice(userName, entity02)).isEqualTo(
                "作業員　実さん　処理すべき新たなタスクが発生しました。http://localhost:8080/dummy-page?id=1&code=2にアクセスしてタスクの処理を行ってください");
    }

}
