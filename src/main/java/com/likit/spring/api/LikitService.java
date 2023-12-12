package com.likit.spring.api;

/**
 * @author: LXY
 * @create: 2023-12-07 20:13
 * @Description:
 */
public interface LikitService {

    long vote(String businessId,String messageId,String userId);

    long unvote(String businessId, String messageId, String userId);

    long getVoteCount(String businessId, String messageId);

    boolean getIsVote(String businessId, String messageId, String userId);

    String[] getVotedUsers(String businessId, String messageId);
}
