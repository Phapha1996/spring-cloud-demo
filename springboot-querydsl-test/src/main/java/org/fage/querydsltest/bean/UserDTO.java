package org.fage.querydsltest.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author Caizhf
 * @version 1.0
 * @date 2018/10/30 9:27
 * @description
 **/
@Data
@Builder
public class UserDTO {
    private String userId;
    private String username;
    private String nickname;
    private String birthday;
}
