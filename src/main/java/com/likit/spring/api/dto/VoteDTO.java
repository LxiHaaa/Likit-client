package com.likit.spring.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: LXY
 * @create: 2023-12-07 19:48
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO implements Serializable {

    private String businessId;

    private String messageId;

    private String userId;

}
