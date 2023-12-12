package com.likit.spring.api.impl;

import build.buf.gen.likit.api.v1.*;
import com.likit.spring.api.LikitService;
import com.likit.spring.config.LikitProperties;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author: LXY
 * @create: 2023-12-07 19:51
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LikitServiceImp implements LikitService {

    private final LikitProperties likitProperties;

    private VoteServiceGrpc.VoteServiceBlockingStub client = null;

    @PostConstruct
    public void init(){
        if (likitProperties.getTls()) {
            client = VoteServiceGrpc.newBlockingStub(
                    ManagedChannelBuilder
                            .forAddress(likitProperties.getHost(), likitProperties.getPort())
                            .useTransportSecurity()
                            .build());
        }else{
            client = VoteServiceGrpc.newBlockingStub(
                    ManagedChannelBuilder
                            .forAddress(likitProperties.getHost(), likitProperties.getPort())
                            .build());
        }
    }

    @Override
    public long vote(String businessId, String messageId, String userId) {
        VoteResponse response =  client.vote(VoteRequest
                .newBuilder()
                .setBusinessId(businessId)
                .setMessageId(messageId)
                .setUserId(userId)
                .build()
        );
        return response.getCount();
    }

    @Override
    public long unvote(String businessId, String messageId, String userId) {
        VoteResponse response = client.unVote(VoteRequest
                .newBuilder()
                .setBusinessId(businessId)
                .setMessageId(messageId)
                .setUserId(userId)
                .build()
        );
        return response.getCount();

    }

    @Override
    public long getVoteCount(String businessId, String messageId) {
        CountResponse response = client.count(CountRequest
                .newBuilder()
                .setBusinessId(businessId)
                .setMessageId(messageId)
                .build()
        );
        return response.getCount();
    }

    @Override
    public boolean getIsVote(String businessId, String messageId, String userId) {
        IsVotedResponse response = client.isVoted(IsVotedRequest
                .newBuilder()
                .setBusinessId(businessId)
                .setMessageId(messageId)
                .setUserId(userId)
                .build()
        );
        return response.getIsVoted();
    }

    @Override
    public String[] getVotedUsers(String businessId, String messageId) {
        VotedUsersResponse votedUsersResponse = client.votedUsers(VotedUsersRequest
                .newBuilder()
                .setBusinessId(businessId)
                .setMessageId(messageId)
                .build()
        );
        return votedUsersResponse.getUserIdsList().stream().map(String::valueOf).toArray(String[]::new);
    }
}
