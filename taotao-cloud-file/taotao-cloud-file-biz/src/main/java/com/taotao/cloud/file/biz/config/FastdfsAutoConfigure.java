package com.taotao.cloud.file.biz.config;

import cn.hutool.core.util.StrUtil;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.taotao.cloud.file.api.entity.FileInfo;
import com.taotao.cloud.file.biz.properties.FileServerProperties;
import com.taotao.cloud.file.biz.service.impl.AbstractIFileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * FastDFS配置
 *
 * @author zlt
 */
@Configuration
@ConditionalOnProperty(name = "zlt.file-server.type", havingValue = "fastdfs")
public class FastdfsAutoConfigure {
    @Autowired
    private FileServerProperties fileProperties;

    @Service
    public class FastdfsServiceImpl extends AbstractIFileService {
        @Autowired
        private FastFileStorageClient storageClient;

        @Override
        protected String fileType() {
            return fileProperties.getType();
        }

        @Override
        protected void uploadFile(MultipartFile file, FileInfo fileInfo) throws Exception {
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            fileInfo.setUrl("http://" + fileProperties.getFdfs().getWebUrl() + "/" + storePath.getFullPath());
            fileInfo.setPath(storePath.getFullPath());
        }

        @Override
        protected boolean deleteFile(FileInfo fileInfo) {
            if (fileInfo != null && StrUtil.isNotEmpty(fileInfo.getPath())) {
                StorePath storePath = StorePath.parseFromUrl(fileInfo.getPath());
                storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            }
            return true;
        }
    }
}
