package com.likit.spring.api.impl;

import build.buf.gen.likit.api.v1.*;
import com.google.protobuf.ProtocolStringList;
import com.likit.spring.api.dto.VoteDTO;
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
public class VoteUseCaseImpl implements com.likit.spring.api.VoteUseCase {

    private final LikitProperties likitProperties;

    private VoteServiceGrpc.VoteServiceBlockingStub client = null;

    @PostConstruct
    public void init(){
        client = VoteServiceGrpc.newBlockingStub(
                ManagedChannelBuilder
                        .forAddress(likitProperties.getHost(), likitProperties.getPort())
                        .usePlaintext()
                        .build());
    }

    /**
     * @param voteDTO A full object is require
     * @return count
     */
    @Override
    public long vote(VoteDTO voteDTO) {
        VoteResponse response =  client.vote(VoteRequest
                .newBuilder()
                .setBusinessId(voteDTO.getBusinessId())
                .setMessageId(voteDTO.getMessageId())
                .setUserId(voteDTO.getUserId())
                .build()
        );
        return response.getCount();
    }

    /**
     * @param voteDTO A full object is require
     * @return count
     */
    @Override
    public long unVote(VoteDTO voteDTO) {
        VoteResponse response = client.unVote(VoteRequest
                .newBuilder()
                .setBusinessId(voteDTO.getBusinessId())
                .setMessageId(voteDTO.getMessageId())
                .setUserId(voteDTO.getUserId())
                .build()
        );
        return response.getCount();
    }

    @Override
    public long count(String businessId,String messageId) {
        CountResponse response = client.count(CountRequest
                .newBuilder()
                .setBusinessId(businessId)
                .setMessageId(messageId)
                .build()
        );
        return response.getCount();
    }

    /**
     * @param voteDTO A full object is require
     * @return true or false
     */
    @Override
    public boolean isVote(VoteDTO voteDTO) {
        IsVotedResponse response = client.isVoted(IsVotedRequest
                .newBuilder()
                .setBusinessId(voteDTO.getBusinessId())
                .setMessageId(voteDTO.getMessageId())
                .setUserId(voteDTO.getUserId())
                .build()
        );
        return response.getIsVoted();
    }

    @Override
    public ProtocolStringList VotedUsers(String businessId,String messageId) {
        VotedUsersResponse votedUsersResponse = client.votedUsers(VotedUsersRequest
                .newBuilder()
                .setBusinessId(businessId)
                .setMessageId(messageId)
                .build()
        );
        return votedUsersResponse.getUserIdsList();
    }


}
