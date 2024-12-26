package mitei.mitei.investigate.report.balance.politician.batch.financial_org_reflesh;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import mitei.mitei.investigate.report.balance.politician.dto.common_check.TemplateFrameworkResultDto;
import mitei.mitei.investigate.report.balance.politician.entity.ZenginOrgChangeBranchEntity;
import reactor.core.publisher.Mono;

@Component
public class TransferChangeCreateSiteTasklet implements Tasklet, StepExecutionListener {

    private WebClient webClient;

    /**
     * 実行メソッド
     */
    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {

        List<ZenginOrgChangeBranchEntity> listChange = new ArrayList<>();

        TemplateFrameworkResultDto resultDto =  WebClient.create().post() 
                .uri("http//localhost:8080")
                .body(Mono.just(listChange), new ParameterizedTypeReference<List<ZenginOrgChangeBranchEntity>>() {})
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TemplateFrameworkResultDto.class).block();
        
        // 処理終了
        return RepeatStatus.FINISHED;
    }
}
