package com.taotao.cloud.auth.service;


import com.taotao.cloud.common.model.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * IValidateCodeService
 *
 * @author dengtao
 * @date 2020/4/29 16:27
 */
public interface ISmsCodeService {
    /**
     * 保存图形验证码
     *
     * @param deviceId  前端唯一标识
     * @param imageCode 验证码
     * @return void
     * @author dengtao
     * @date 2020/4/29 16:27
     */
    void saveImageCode(String deviceId, String imageCode);

    /**
     * 发送验证码
     *
     * @param mobile 前端唯一标识(手机号码
     * @return com.taotao.cloud.common.model.Result<java.lang.String>
     * @author dengtao
     * @date 2020/4/29 16:27
     */
    Result<Boolean> sendSmsCode(String mobile);

    /**
     * 获取验证码
     *
     * @param deviceId 前端唯一标识/手机号
     * @return java.lang.String
     * @author dengtao
     * @date 2020/4/29 16:28
     */
    String getCode(String deviceId);

    /**
     * 删除验证码
     *
     * @param deviceId 前端唯一标识/手机号
     * @return void
     * @author dengtao
     * @date 2020/4/29 16:28
     */
    void remove(String deviceId);

    /**
     * 验证验证码
     *
     * @param request 请求数据
     * @return void
     * @author dengtao
     * @date 2020/4/29 16:28
     */
    void validate(HttpServletRequest request);
}
