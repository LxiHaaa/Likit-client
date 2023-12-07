package com.likit.spring.api;

import com.likit.spring.api.dto.VoteDTO;

/**
 * @author: LXY
 * @create: 2023-12-07 20:13
 * @Description:
 */
public interface VoteUseCase {

    public long vote(VoteDTO voteDTO);

    public long unvote(VoteDTO voteDTO);

    public long count(String businessId,String messageId);

}
