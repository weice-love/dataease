package io.dataease.xpack;

import io.dataease.sso.user.UserDTO;
import io.dataease.sso.user.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qiuqiu
 * @date 2025/9/3
 */
@Service
public class ApisixCacheManage {

    @Autowired
    private UserService userService;

    public UserDTO userCacheBO(Long userId) {
        return userService.selectUserById(userId);
    }

}
