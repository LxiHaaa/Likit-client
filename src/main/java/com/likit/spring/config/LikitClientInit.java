package com.likit.spring.config;

import build.buf.gen.likit.api.v1.VoteServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author: LXY
 * @create: 2023-12-14 09:55
 * @Description:
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LikitClientInit {

    private final LikitProperties likitProperties;

    @Bean
    public VoteServiceGrpc.VoteServiceBlockingStub initLikitClient(){
        ManagedChannelBuilder<?> managedChannelBuilder = ManagedChannelBuilder
                .forAddress(likitProperties.getHost(), likitProperties.getPort())
                .usePlaintext();
        if(likitProperties.getTls()){
            managedChannelBuilder.useTransportSecurity();
        }
        return VoteServiceGrpc.newBlockingStub(managedChannelBuilder.build());
    }

}
