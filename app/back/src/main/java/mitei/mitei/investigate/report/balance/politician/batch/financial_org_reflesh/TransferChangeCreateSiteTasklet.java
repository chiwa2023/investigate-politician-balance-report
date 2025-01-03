package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplateFrameworkResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import reactor.core.publisher.Mono;

/**
 * 全銀変更内容送信Tasklet
 */
@Component
public class TransferChangeCreateSiteTasklet implements Tasklet, StepExecutionListener {

    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(TransferChangeCreateSiteTasklet.class);

    /** 全銀変更テーブル伝送データ取得Logic */
    @Autowired
    private TransferChageListGetLogic transferChageListGetLogic;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        TemplateFrameworkResultDto resultDto = WebClient.create().post()
                .uri("http://localhost:8080/zengin-change/recieve")
                .body(Mono.just(transferChageListGetLogic.practice()),
                        new ParameterizedTypeReference<List<ZenginOrgChangeBranchEntity>>() { // NOPMD
                        }) // NOPMD
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(TemplateFrameworkResultDto.class).block();

        // TODO 参照方法が決定次第修正する
        log.info("通信結果::" +resultDto.getIsOk());
        log.info("成功件数::" + resultDto.getSuccessCount());
        log.info("失敗件数::" + resultDto.getFailureCount());
        
        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
