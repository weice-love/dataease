package io.dataease.sso.user;

import lombok.Data;

/**
 * @author qiuqiu
 * @date 2025/9/4
 */
@Data
public class UserDO {
    private Long userId;

    private Long oId;

    private String username;

    private String pwd;

    private String nickName;
}
