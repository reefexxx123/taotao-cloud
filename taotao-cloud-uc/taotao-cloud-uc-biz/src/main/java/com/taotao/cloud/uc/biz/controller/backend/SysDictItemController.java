package com.taotao.cloud.uc.biz.controller.backend;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.log.annotation.SysOperateLog;
import com.taotao.cloud.uc.api.entity.SysDictItem;
import com.taotao.cloud.uc.biz.service.ISysDictItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 字典详情管理API
 *
 * @author dengtao
 * @date 2020/4/30 11:21
 */
@RestController
@RequestMapping("/dict/item")
@Api(value = "字典详情管理API", tags = { "字典详情管理API" })
public class SysDictItemController {

    @Autowired
    private ISysDictItemService dictItemService;

    @ApiOperation("分页查询字典详情内容")
    @SysOperateLog(description = "分页查询字典详情内容")
    @GetMapping
    public Result<PageResult<SysDictItem>> getDictItemPage(Page page, SysDictItem sysDictItem) {
        Page pageResult = dictItemService.page(page, Wrappers.query(sysDictItem));
        PageResult<SysDictItem> result = PageResult.builder().currentPage(page.getCurrent()).total(pageResult.getTotal())
                .code(ResultEnum.SUCCESS.getCode()).pageSize(page.getSize()).data(pageResult.getRecords())
                .build();
        return Result.succeed(result);
    }

    @ApiOperation("添加字典详情")
    @SysOperateLog(description = "添加字典详情")
    @PreAuthorize("hasAuthority('sys:dictItem:add')")
    @PostMapping
    public Result<Boolean> add(@RequestBody SysDictItem sysDictItem) {
        return Result.succeed(dictItemService.save(sysDictItem));
    }

    @ApiOperation("更新字典详情")
    @SysOperateLog(description = "更新字典详情")
    @PreAuthorize("hasAuthority('sys:dictItem:edit')")
    @PutMapping
    public Result<Boolean> update(@RequestBody SysDictItem sysDictItem) {
        return Result.succeed(dictItemService.updateById(sysDictItem));
    }

    @ApiOperation("删除字典详情")
    @SysOperateLog(description = "删除字典详情")
    @PreAuthorize("hasAuthority('sys:dictItem:del')")
    @DeleteMapping("/{id}")
    public Result<Boolean> delete(@PathVariable("id") String id) {
        return Result.succeed(dictItemService.updateById(new SysDictItem().setId(id).setStatus(0)));
    }
}
