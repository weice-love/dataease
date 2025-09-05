package io.dataease.sso.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.dataease.sso.user.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 生产数据统计Mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<Object> {

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    List<UserDO> selectUserByName(
            @Param("userName") String userName
    );

    UserDO selectUserById(Long userId);
}