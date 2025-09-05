package io.dataease.sso.user;

import io.dataease.exception.DEException;
import io.dataease.sso.user.mapper.UserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author qiuqiu
 * @date 2025/9/4
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO selectUserByName(String name) {
        List<UserDO> userDOS = userMapper.selectUserByName(name);
        if (CollectionUtils.isEmpty(userDOS)) {
            DEException.throwException("用户不存在");
        }
        UserDO userDO = userDOS.get(0);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userDO.getUserId());
        userDTO.setUsername(userDO.getUsername());
        userDTO.setPwd(userDO.getPwd());
        userDTO.setOId(userDO.getOId());
        return userDTO;
    }

    @Override
    public UserDTO selectUserById(Long userId) {
        UserDO user = userMapper.selectUserById(userId);
        if (Objects.isNull( user)) {
            DEException.throwException("用户不存在");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPwd(user.getPwd());
        userDTO.setOId(user.getOId());
        userDTO.setNickName(user.getNickName());
        return userDTO;
    }
}
