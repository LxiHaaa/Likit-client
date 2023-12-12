package com.likit.example.controller;

import com.likit.spring.api.LikitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LXY
 * @create: 2023-12-07 22:19
 * @Description:
 */
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private LikitService likitService;

    @GetMapping("/vote")
    public String Vote(){
        long count = likitService.vote("COMMENT_LIKE","MESSAGE","LXY");
        return "success:" + count;
    }

    @GetMapping("/unvote")
    public String UnVote(){
        long count = likitService.unvote("COMMENT_LIKE","MESSAGE","LXY");
        return "success:" + count;
    }

    @GetMapping("/count")
    public String count(){
        long count = likitService.getVoteCount("COMMENT_LIKE", "MESSAGE");
        return "success:" + count;
    }


}
