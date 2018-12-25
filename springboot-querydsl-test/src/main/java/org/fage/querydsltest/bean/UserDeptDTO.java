package org.fage.querydsltest.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author Caizhf
 * @version 1.0
 * @date 2018/11/2 11:23
 * @description
 **/
@Data
@Builder
public class UserDeptDTO {
    private String username;    //用户名
    private String nickname;    //昵称
    private String birthday;    //用户生日
    private String deptName;    //用户所属部门
    private String deptBirth;   //部门创建的时间
}
