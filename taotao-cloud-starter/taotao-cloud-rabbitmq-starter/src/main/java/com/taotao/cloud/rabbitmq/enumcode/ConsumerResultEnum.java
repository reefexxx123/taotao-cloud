package com.taotao.cloud.rabbitmq.enumcode;


/**
 * ConsumerResultEnum
 *
 * @author dengtao
 * @date 2020/5/28 17:29
 */
public enum ConsumerResultEnum {

    /**
     * 收到消息,未确认
     */
    SEND(0, "收到消息,未确认"),
    /**
     * 收到消息,确认消费成功
     */
    SUCCESS(1, "收到消息，确认消费成功"),
    /**
     * 收到消息,确认消费失败
     */
    FAIL(2, "收到消息，确认消费失败");

    private Integer code;
    private String desc;

    ConsumerResultEnum(Integer code, String desc) {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
