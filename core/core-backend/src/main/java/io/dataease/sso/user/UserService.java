package io.dataease.sso.user;

/**
 * @author qiuqiu
 * @date 2025/9/4
 */
public interface UserService {
    UserDTO selectUserByName(String name);

    UserDTO selectUserById(Long userId);
}
