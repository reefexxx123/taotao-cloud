package com.taotao.cloud.file.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.file.api.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 文件service
 *
 * @author 作者 owen E-mail: 624191343@qq.com
*/
public interface IFileService extends IService<FileInfo> {
	FileInfo upload(MultipartFile file ) throws Exception;
	
	PageResult<FileInfo> findList(Map<String, Object> params);

	void delete(String id);
}
