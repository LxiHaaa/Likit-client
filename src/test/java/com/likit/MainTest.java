package com.likit;

import com.likit.example.MainApplication;
import com.likit.spring.api.LikitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void vote(){
        long count = likitService.vote("BTYPE", "MESSAGE", "LXY");
        System.out.println("success:" + count);
    }

}
