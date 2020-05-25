package com.taotao.cloud.uc.biz.controller;

import com.alibaba.fastjson.JSON;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.uc.biz.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主页模块API
 *
 * @author dengtao
 * @date 2020/4/30 11:02
 */
@RestController
@Api(value = "主页模块API", tags = { "主页模块API" })
public class IndexController {

    @Autowired
    private ISysUserService userService;

    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public Result<String> info() {
        String temp = "{\"id\":\"4291d7da9005377ec9aec4a71ea837f\",\"name\":\"天野远子\",\"username\":\"admin\",\"password\":\"\",\"avatar\":\"/avatar2.jpg\",\"status\":1,\"telephone\":\"\",\"lastLoginIp\":\"27.154.74.117\",\"lastLoginTime\":1534837621348,\"creatorId\":\"admin\",\"createTime\":1497160610259,\"merchantCode\":\"TLif2btpzg079h15bk\",\"deleted\":0,\"roleId\":\"admin\",\"role\":{\"id\":\"admin\",\"name\":\"管理员\",\"describe\":\"拥有所有权限\",\"status\":1,\"creatorId\":\"system\",\"createTime\":1497160610259,\"deleted\":0,\"permissions\":[{\"roleId\":\"admin\",\"permissionId\":\"dashboard\",\"permissionName\":\"仪表盘\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"query\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"查询\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"query\",\"describe\":\"查询\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"query\",\"get\",\"update\",\"delete\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"exception\",\"permissionName\":\"异常页面权限\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"query\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"查询\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"query\",\"describe\":\"查询\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"query\",\"get\",\"update\",\"delete\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"result\",\"permissionName\":\"结果权限\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"query\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"查询\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"query\",\"describe\":\"查询\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"query\",\"get\",\"update\",\"delete\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"profile\",\"permissionName\":\"详细页权限\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"query\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"查询\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"query\",\"describe\":\"查询\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"query\",\"get\",\"update\",\"delete\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"table\",\"permissionName\":\"表格权限\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"import\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"导入\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"import\",\"describe\":\"导入\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"import\",\"get\",\"update\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"form\",\"permissionName\":\"表单权限\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"query\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"查询\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"query\",\"describe\":\"查询\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"get\",\"query\",\"update\",\"delete\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"order\",\"permissionName\":\"订单管理\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"query\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"查询\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"query\",\"describe\":\"查询\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"query\",\"get\",\"update\",\"delete\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"permission\",\"permissionName\":\"权限管理\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"get\",\"update\",\"delete\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"role\",\"permissionName\":\"角色管理\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"get\",\"update\",\"delete\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"table\",\"permissionName\":\"桌子管理\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"query\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"查询\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"query\",\"describe\":\"查询\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"get\",\"query\",\"update\",\"delete\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"user\",\"permissionName\":\"用户管理\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"import\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"导入\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"},{\\\"action\\\":\\\"export\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"导出\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"import\",\"describe\":\"导入\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false},{\"action\":\"export\",\"describe\":\"导出\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"import\",\"get\",\"update\",\"delete\",\"export\"],\"dataAccess\":null},{\"roleId\":\"admin\",\"permissionId\":\"support\",\"permissionName\":\"超级模块\",\"actions\":\"[{\\\"action\\\":\\\"add\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"新增\\\"},{\\\"action\\\":\\\"import\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"导入\\\"},{\\\"action\\\":\\\"get\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"详情\\\"},{\\\"action\\\":\\\"update\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"修改\\\"},{\\\"action\\\":\\\"delete\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"删除\\\"},{\\\"action\\\":\\\"export\\\",\\\"defaultCheck\\\":false,\\\"describe\\\":\\\"导出\\\"}]\",\"actionEntitySet\":[{\"action\":\"add\",\"describe\":\"新增\",\"defaultCheck\":false},{\"action\":\"import\",\"describe\":\"导入\",\"defaultCheck\":false},{\"action\":\"get\",\"describe\":\"详情\",\"defaultCheck\":false},{\"action\":\"update\",\"describe\":\"修改\",\"defaultCheck\":false},{\"action\":\"delete\",\"describe\":\"删除\",\"defaultCheck\":false},{\"action\":\"export\",\"describe\":\"导出\",\"defaultCheck\":false}],\"actionList\":[\"add\",\"import\",\"get\",\"update\",\"delete\",\"export\"],\"dataAccess\":null}],\"permissionList\":[\"dashboard\",\"exception\",\"result\",\"profile\",\"table\",\"form\",\"order\",\"permission\",\"role\",\"table\",\"user\",\"support\"]}}";
        String s = JSON.toJSONString(temp);
        return Result.succeed(s);
    }

    @ApiOperation("用户退出")
    @GetMapping("/logout")
    public Result<String> logout() {
        return Result.succeed("success");
    }
}
