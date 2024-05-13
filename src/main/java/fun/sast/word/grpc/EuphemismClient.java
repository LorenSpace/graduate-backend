package fun.sast.word.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Service
public class EuphemismClient {
    private ManagedChannel channel;
    private EuphemismServiceGrpc.EuphemismServiceBlockingStub blockingStub;
    private EuphemismServiceGrpc.EuphemismServiceStub asyncStub;

    @PostConstruct
    public void init() {
        String host = "localhost";
        int port = 50051;
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockingStub = EuphemismServiceGrpc.newBlockingStub(channel);
        asyncStub = EuphemismServiceGrpc.newStub(channel);
    }

    @PreDestroy
    public void close() {
        channel.shutdown();
    }

    public boolean isEuphemism(String text) {
        DetectEuphemismRequest request = DetectEuphemismRequest.newBuilder().setText(text).build();
        DetectEuphemismResponse response = blockingStub.detectEuphemism(request);
        boolean result = response.getResult();
        log.info("Received: {}", result);
        return result;
    }
}

