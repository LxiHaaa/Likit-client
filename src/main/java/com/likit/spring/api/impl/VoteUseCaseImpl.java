package com.likit.spring.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.likit.spring.api.VoteUseCase;
import com.likit.spring.api.dto.VoteDTO;
import com.likit.spring.config.LikitProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author: LXY
 * @create: 2023-12-07 19:51
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VoteUseCaseImpl implements VoteUseCase {

    private final RestTemplate restTemplate;

    private final LikitProperties likitProperties;

    @Override
    public long vote(VoteDTO voteDTO) {
        restTemplate.getForObject(getPath("/vote"), String.class);
        String body = JSONObject.toJSONString(voteDTO);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(getPath("/vote"), body, JSONObject.class);
        if(responseEntity.getStatusCodeValue() != 200){
            throw new RuntimeException("Likit Server return Error: " + responseEntity.getBody().get("Message"));
        }
        return Long.parseLong(Objects.requireNonNull(responseEntity.getBody()).getString("Count"));
    }

    @Override
    public long unvote(VoteDTO voteDTO) {
        restTemplate.getForObject(getPath("/vote"), String.class);
        String body = JSONObject.toJSONString(voteDTO);
        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(getPath("/vote"), body, JSONObject.class);
        if(responseEntity.getStatusCodeValue() != 200){
            throw new RuntimeException("Likit Server return Error: " + responseEntity.getBody().get("Message"));
        }
        return Long.parseLong(Objects.requireNonNull(responseEntity.getBody()).getString("Count"));
    }

    @Override
    public long count(String businessId,String messageId) {
        HashMap<String,Object> pathValueMap = new HashMap<>();
        pathValueMap.put("businessId",businessId);
        pathValueMap.put("messageId",messageId);
        ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(
                getPath("/count/{businessId}/{messageId}"),
                JSONObject.class,
                pathValueMap
        );
        return Long.parseLong(responseEntity.getBody().getString("count"));
    }

    public String getPath(String path) {
        return likitProperties.getHost() + path;
    }

}
