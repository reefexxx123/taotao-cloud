package com.taotao.cloud.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.taotao.cloud.auth.exception.ValidateCodeException;
import com.taotao.cloud.auth.model.SecurityUser;
import com.taotao.cloud.auth.service.ISmsCodeService;
import com.taotao.cloud.auth.service.IUserDetailsService;
import com.taotao.cloud.common.constant.SecurityConstant;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.message.domain.SmsResponse;
import com.taotao.cloud.message.enums.SmsEnum;
import com.taotao.cloud.message.service.IAliyunSmsMessageService;
import com.taotao.cloud.redis.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 验证码
 *
 * @author dengtao
 * @date 2020/4/29 16:12
 */
@Slf4j
@Service
public class SmsCodeServiceImpl implements ISmsCodeService {

    @Autowired
    private RedisRepository redisRepository;
    @Resource
    private IUserDetailsService userService;
    @Autowired
    private IAliyunSmsMessageService aliyunSmsMessageService;

    /**
     * 保存用户验证码，和randomStr绑定
     *
     * @param mobile    客户端生成
     * @param imageCode 验证码信息
     * @return void
     * @author dengtao
     * @date 2020/4/29 16:17
     */
    @Override
    public void saveImageCode(String mobile, String imageCode) {
        redisRepository.setExpire(buildKey(mobile), imageCode, SecurityConstant.DEFAULT_IMAGE_EXPIRE);
    }

    /**
     * 发送验证码
     * <p>
     * 1. 先去redis 查询是否 60S内已经发送
     * 2. 未发送： 判断手机号是否存 ? false :产生4位数字  手机号-验证码
     * 3. 发往消息中心 -> 发送信息
     * 4. 保存redis
     *
     * @param mobile 手机号
     * @return true、false
     */
    @Override
    public Result<Boolean> sendSmsCode(String mobile) {
        Object tempCode = redisRepository.get(buildKey(mobile));
        if (tempCode != null) {
            log.error("用户:{}验证码未失效{}", mobile, tempCode);
            return Result.failed("验证码未失效，请失效后再次申请");
        }

        SecurityUser user = userService.loadUserByMobile(mobile);
        if (user == null) {
            log.error("根据用户手机号{}查询用户为空", mobile);
            return Result.failed("手机号不存在");
        }

        String[] phoneNumbers = {mobile};
        // 使用阿里短信发送
        SmsResponse smsResponse = aliyunSmsMessageService.sendSms(phoneNumbers, "惠游重庆", SmsEnum.LOGIN);
        if (ObjectUtil.isNull(smsResponse)) {
            return Result.failed("短信发送失败");
        }
        String smsCode = smsResponse.getSmsCode();
        log.info("短信发送请求消息中心 -> 手机号:{} -> 验证码：{}", mobile, smsCode);

        redisRepository.setExpire(buildKey(mobile), smsCode, SecurityConstant.DEFAULT_IMAGE_EXPIRE);
        return Result.succeed(true);
    }


    /**
     * 获取验证码
     *
     * @param mobile 前端唯一标识/手机号
     * @return java.lang.String
     * @author dengtao
     * @date 2020/4/29 16:14
     */
    @Override
    public String getCode(String mobile) {
        return (String) redisRepository.get(buildKey(mobile));
    }

    /**
     * 删除验证码
     *
     * @param mobile 前端唯一标识/手机号
     * @return void
     * @author dengtao
     * @date 2020/4/29 16:15
     */
    @Override
    public void remove(String mobile) {
        redisRepository.del(buildKey(mobile));
    }

    /**
     * 验证验证码
     *
     * @param request 请求数据
     * @return void
     * @author dengtao
     * @date 2020/4/29 16:15
     */
    @Override
    public void validate(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        if (StringUtils.isBlank(mobile)) {
            throw new ValidateCodeException("请在请求参数中携带mobile参数");
        }
        String code = this.getCode(mobile);
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request, "smsCode");
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("请填写验证码");
        }

        if (code == null) {
            throw new ValidateCodeException("验证码不存在或已过期");
        }

        if (!StringUtils.equals(code, codeInRequest.toLowerCase())) {
            throw new ValidateCodeException("验证码不正确");
        }

        this.remove(mobile);
    }

    private String buildKey(String mobile) {
        return SecurityConstant.DEFAULT_SMS_CODE_KEY + ":" + mobile;
    }
}
