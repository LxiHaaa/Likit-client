package com.likit.spring.api;

import com.google.protobuf.ProtocolStringList;
import com.likit.spring.api.dto.VoteDTO;

/**
 * @author: LXY
 * @create: 2023-12-07 20:13
 * @Description:
 */
public interface VoteUseCase {

    long vote(VoteDTO voteDTO);

    long unVote(VoteDTO voteDTO);

    long count(String businessId,String messageId);

    boolean isVote(VoteDTO voteDTO);

    ProtocolStringList VotedUsers(String businessId,String messageId);
}
