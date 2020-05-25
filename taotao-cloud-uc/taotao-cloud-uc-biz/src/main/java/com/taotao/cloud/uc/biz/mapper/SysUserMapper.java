package com.taotao.cloud.uc.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.cloud.data.datascope.DataScope;
import com.taotao.cloud.uc.api.dto.UserDTO;
import com.taotao.cloud.uc.api.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * 用户表 Mapper 接口
 *
 * @author dengtao
 * @date 2020/4/30 13:24
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Insert("insert into sys_user (username,password,dept_id,job_id,phone,email,avatar,lock_flag) values (#{username},#{password},#{deptId},#{jobId},#{phone},#{email},#{avatar},#{lockFlag})")
    @Options(useGeneratedKeys = true, keyProperty = "userId", keyColumn = "user_id")
    boolean insertUser(SysUser sysUser);

    IPage<SysUser> getUserVosPage(Page page, @Param("query") UserDTO userDTO, DataScope dataScope);

    @Results({
           @Result(id = true, column="user_id", property = "userId", jdbcType = JdbcType.INTEGER),
           @Result(column="dept_id", property = "deptId", jdbcType = JdbcType.VARCHAR),
           @Result(column="dept_name", property = "deptName", jdbcType = JdbcType.VARCHAR),
           @Result(column="job_id", property = "jobId", jdbcType = JdbcType.VARCHAR),
           @Result(column="job_name", property = "jobName", jdbcType = JdbcType.VARCHAR),
           @Result(column="create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
           @Result(column="update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
           @Result(column="lock_flag", property = "lockFlag", jdbcType = JdbcType.VARCHAR),
           @Result(column="del_flag", property = "delFlag", jdbcType = JdbcType.VARCHAR),
    })
    @Select("SELECT su.* FROM sys_user su LEFT JOIN sys_user_social sus ON su.user_id = sus.user_id WHERE sus.provider_id = #{providerId} AND sus.provider_user_id = #{providerUserId} and su.type = 1")
    SysUser getUserBySocial(@Param("providerId") String providerId, @Param("providerUserId") int providerUserId);

}
