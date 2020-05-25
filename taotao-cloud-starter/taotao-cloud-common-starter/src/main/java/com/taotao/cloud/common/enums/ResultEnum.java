package com.taotao.cloud.common.enums;

/**
 * 返回枚举
 *
 * @author dengtao
 * @date 2020/4/29 15:36
 */
public enum ResultEnum {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功", "操作成功"),

    /**
     * 操作失败
     */
    ERROR(500, "操作失败", "操作失败"),

    /**
     * 认证失败
     */
    UNAUTHORIZED(401, "认证失败", "认证失败"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(135400000, "参数错误", "请传入openid和wechat_type");

    /**
     * 返回码
     */
    private final int code;

    /**
     * 描述
     */
    private final String message;

    /**
     * 消息详情
     */
    private final String messageDetail;

    ResultEnum(int code, String message, String messageDetail) {
        this.code = code;
        this.message = message;
        this.messageDetail = messageDetail;
    }

    /**
     * 根据返回码得到描述信息
     *
     * @param code code
     * @return java.lang.String
     * @author dengtao
     * @date 2020/4/29 15:35
     */
    public static String getMessageByCode(int code) {
        for (ResultEnum result : ResultEnum.values()) {
            if (result.getCode() == code) {
                return result.getMessage();
            }
        }

        return null;
    }

    /**
     * 根据返回码得到解决方案信息
     *
     * @param code code
     * @return java.lang.String
     * @author dengtao
     * @date 2020/4/29 15:35
     */
    public static String getMessageDetailByCode(int code) {
        for (ResultEnum result : ResultEnum.values()) {
            if (result.getCode() == code) {
                return result.getMessageDetail();
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

}
