package com.taotao.cloud.file.biz.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.cloud.data.mapper.SuperMapper;
import com.taotao.cloud.file.api.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * @author dengtao
 * @date 2020/6/15 11:13
 */
@Mapper
public interface FileMapper extends SuperMapper<FileInfo> {
    List<FileInfo> findList(Page<FileInfo> page, @Param("f") Map<String, Object> params);
}
