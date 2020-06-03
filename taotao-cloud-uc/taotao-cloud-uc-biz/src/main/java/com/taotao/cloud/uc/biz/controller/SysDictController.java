package com.taotao.cloud.uc.biz.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.log.annotation.SysOperateLog;
import com.taotao.cloud.uc.api.dto.DictDTO;
import com.taotao.cloud.uc.api.entity.SysDict;
import com.taotao.cloud.uc.biz.service.ISysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 字典管理API
 *
 * @author dengtao
 * @date 2020/4/30 11:13
 */
@RestController
@RequestMapping("/dict")
@Api(value = "字典管理API", tags = { "字典管理API" })
public class SysDictController {

    @Autowired
    private ISysDictService dictService;

    @ApiOperation("添加字典信息")
    @SysOperateLog(description = "添加字典信息")
    @PreAuthorize("hasAuthority('sys:dict:add')")
    @PostMapping
    public Result<Boolean> add(@RequestBody SysDict sysDict) {
        return Result.succeed(dictService.save(sysDict));
    }

    @ApiOperation("查询字典集合")
    @SysOperateLog(description = "查询字典集合")
    @GetMapping
    @PreAuthorize("hasAuthority('sys:dipt:view')")
    public Result<PageResult<SysDict>> getList(Page page, SysDict sysDict) {
        Page pageResult = dictService.page(page, Wrappers.query(sysDict));
        PageResult<SysDict> result = PageResult.builder().currentPage(page.getCurrent()).total(pageResult.getTotal())
                .code(ResultEnum.SUCCESS.getCode()).pageSize(page.getSize()).data(pageResult.getRecords())
                .build();
        return Result.succeed(result);
    }

    @ApiOperation("更新字典")
    @SysOperateLog(description = "更新字典")
    @PreAuthorize("hasAuthority('sys:dict:edit')")
    @PutMapping
    public Result<Boolean> update(@RequestBody DictDTO dictDto) {
        return Result.succeed(dictService.updateDict(dictDto));
    }

    @ApiOperation("根据id删除字典")
    @SysOperateLog(description = "根据id删除字典")
    @PreAuthorize("hasAuthority('sys:dict:del')")
    @DeleteMapping("{id}")
    public Result<Boolean> delete(@PathVariable("id") int id) {
        return Result.succeed(dictService.removeById(id));
    }

}

