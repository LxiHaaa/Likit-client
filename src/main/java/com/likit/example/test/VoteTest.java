package com.likit.example.test;

import com.likit.example.MainApplication;
import com.likit.spring.api.LikitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = MainApplication.class)
public class VoteTest {
    private LikitService likitService;
    @Test
    public void vote(){
        long count = likitService.vote("COMMENT_LIKE","MESSAGE","LXY");
        System.out.println("success:" + count);
    }

}
