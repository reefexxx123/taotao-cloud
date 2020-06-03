package com.taotao.cloud.data.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 父类，注意这个类不要让 mp 扫描
 *
 * @author dengtao
 * @date 2020/5/2 11:19
 */
public interface SuperMapper<T> extends BaseMapper<T> {
    // 这里可以放一些公共的方法
}
