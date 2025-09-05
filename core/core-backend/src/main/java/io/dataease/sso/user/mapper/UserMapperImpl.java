package io.dataease.sso.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.dataease.sso.user.UserDO;
import io.dataease.utils.Md5Utils;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author qiuqiu
 * @date 2025/9/2
 */
@Service
@Primary
@ConditionalOnProperty(name = "data.mock.enabled", havingValue = "true")
public class UserMapperImpl implements UserMapper {

    private static final List<UserDO> USERS = new ArrayList<>();
    static {
        for (int i = 1; i <= 10; i++) {
            UserDO user = new UserDO();
            user.setUserId((long) i);
            user.setUsername("User" + i);
            user.setNickName("user" + i + "@example.com");
            user.setOId(i * 1L);
            user.setPwd(Md5Utils.md5(i + ""));
            USERS.add(user);
        }
    }
    @Override
    public List<UserDO> selectUserByName(String userName) {
        return selectList(userName);
    }

    @Override
    public UserDO selectUserById(Long userId) {
        return selectById(userId);
    }

    // 模拟生成用户列表数据，大概十个用户
    private List<UserDO> selectList(String userName) {
        return USERS.stream().filter(e -> Objects.equals(e.getUsername(), userName)).collect(Collectors.toList());
    }

    private UserDO selectById(Long userId) {
        return USERS.stream().filter(e -> Objects.equals(e.getUserId(), userId)).findAny().orElse(null);
    }

    @Override
    public int insert(Object entity) {
        return 0;
    }

    @Override
    public int deleteById(Serializable id) {
        return 0;
    }

    @Override
    public int deleteById(Object entity) {
        return 0;
    }

    @Override
    public int delete(Wrapper<Object> queryWrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<?> idList) {
        return 0;
    }

    @Override
    public int updateById(Object entity) {
        return 0;
    }

    @Override
    public int update(Object entity, Wrapper<Object> updateWrapper) {
        return 0;
    }

    @Override
    public Object selectById(Serializable id) {
        return null;
    }

    @Override
    public List<Object> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public void selectBatchIds(Collection<? extends Serializable> idList, ResultHandler<Object> resultHandler) {

    }

    @Override
    public Long selectCount(Wrapper<Object> queryWrapper) {
        return null;
    }

    @Override
    public List<Object> selectList(Wrapper<Object> queryWrapper) {
        return null;
    }

    @Override
    public void selectList(Wrapper<Object> queryWrapper, ResultHandler<Object> resultHandler) {

    }

    @Override
    public List<Object> selectList(IPage<Object> page, Wrapper<Object> queryWrapper) {
        return null;
    }

    @Override
    public void selectList(IPage<Object> page, Wrapper<Object> queryWrapper, ResultHandler<Object> resultHandler) {

    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<Object> queryWrapper) {
        return null;
    }

    @Override
    public void selectMaps(Wrapper<Object> queryWrapper, ResultHandler<Map<String, Object>> resultHandler) {

    }

    @Override
    public List<Map<String, Object>> selectMaps(IPage<? extends Map<String, Object>> page, Wrapper<Object> queryWrapper) {
        return null;
    }

    @Override
    public void selectMaps(IPage<? extends Map<String, Object>> page, Wrapper<Object> queryWrapper, ResultHandler<Map<String, Object>> resultHandler) {

    }

    @Override
    public <E> List<E> selectObjs(Wrapper<Object> queryWrapper) {
        return null;
    }

    @Override
    public <E> void selectObjs(Wrapper<Object> queryWrapper, ResultHandler<E> resultHandler) {

    }
}
