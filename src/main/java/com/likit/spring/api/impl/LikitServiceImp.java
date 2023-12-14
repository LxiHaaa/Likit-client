package com.likit.spring.api.impl;

import build.buf.gen.likit.api.v1.*;
import com.likit.spring.api.LikitService;
import com.likit.spring.exception.LikitException;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: LXY
 * @create: 2023-12-07 19:51
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LikitServiceImp implements LikitService {

    private final VoteServiceGrpc.VoteServiceBlockingStub client;

    @Override
    public long vote(String businessId, String messageId, String userId) {
        try{
            VoteResponse response =  client.vote(VoteRequest
                    .newBuilder()
                    .setBusinessId(businessId)
                    .setMessageId(messageId)
                    .setUserId(userId)
                    .build()
            );
            return response.getCount();
        }catch (StatusRuntimeException e){
            throw new LikitException(e.getMessage());
        }
    }

    @Override
    public long unvote(String businessId, String messageId, String userId) {
        try {
            VoteResponse response = client.unVote(VoteRequest
                    .newBuilder()
                    .setBusinessId(businessId)
                    .setMessageId(messageId)
                    .setUserId(userId)
                    .build()
            );
            return response.getCount();
        }catch (StatusRuntimeException e){
            throw new LikitException(e.getMessage());
        }
    }

    @Override
    public long getVoteCount(String businessId, String messageId) {
        try{
            CountResponse response = client.count(CountRequest
                    .newBuilder()
                    .setBusinessId(businessId)
                    .setMessageId(messageId)
                    .build()
            );
            return response.getCount();
        }catch (StatusRuntimeException e){
            throw new LikitException(e.getMessage());
        }
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
        try {
            VotedUsersResponse votedUsersResponse = client.votedUsers(VotedUsersRequest
                    .newBuilder()
                    .setBusinessId(businessId)
                    .setMessageId(messageId)
                    .build()
            );
            return votedUsersResponse.getUserIdsList().stream().map(String::valueOf).toArray(String[]::new);
        }catch (StatusRuntimeException e){
            throw new LikitException(e.getMessage());
        }
    }
}
