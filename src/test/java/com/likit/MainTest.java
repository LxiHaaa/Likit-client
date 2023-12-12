package com.likit;

import com.likit.example.MainApplication;
import com.likit.spring.api.LikitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: LXY
 * @create: 2023-12-12 21:05
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class MainTest {

    @Autowired
    private LikitService likitService;

    private final String businessId = "COMMENT_LIKE";

    @Test
    public void testVoteCount(){
        String messageId = RandomStringGenerator.generateRandomString(10);
        String userId = RandomStringGenerator.generateRandomString(5);
        // assert count == 0;
        assertEquals(0, likitService.getVoteCount(businessId, messageId), "count should be equal to 0 before first vote");
        assertEquals(1, likitService.vote(businessId, messageId, userId), "count should be equal to 0 when votes");
        assertEquals(1, likitService.getVoteCount(businessId, messageId), "count should be equal to 0 after votes");
        assertEquals(0, likitService.unvote(businessId, messageId, userId), "count should be equal to 0 when unvotes");
        assertEquals(0, likitService.getVoteCount(businessId, messageId), "count should be equal to 0 after unvotes");
    }

    @Test
    public void testVoteUser(){
        String messageId = RandomStringGenerator.generateRandomString(10);

        String[] votedUsers = new String[5];
        for (int i = 0; i < 5; i++) {
            votedUsers[i] = RandomStringGenerator.generateRandomString(5);
        }

        String[] unvotedUsers = new String[5];
        for (int i = 0; i < 5; i++) {
            unvotedUsers[i] = RandomStringGenerator.generateRandomString(5);
        }

        for (String votedUser : votedUsers) {
            likitService.vote(businessId, messageId, votedUser);
        }

        assertEquals(5, likitService.getVoteCount(businessId,messageId), "count should be equal to 5 after 5 people count");

        for (String votedUser : votedUsers) {
            assertEquals(true, likitService.getIsVote(businessId, messageId, votedUser), "user should be voted");
        }

        for (String votedUser : unvotedUsers) {
            assertEquals(false, likitService.getIsVote(businessId, messageId, votedUser), "user should be not voted");
        }

        assertArrayEquals(votedUsers,likitService.getVotedUsers(businessId,messageId),"votedUsers should be equal");
    }
}
