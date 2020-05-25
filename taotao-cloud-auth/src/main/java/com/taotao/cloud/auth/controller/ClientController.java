package com.taotao.cloud.auth.controller;

import com.google.common.collect.Maps;
import com.taotao.cloud.auth.dto.ClientDTO;
import com.taotao.cloud.auth.model.Client;
import com.taotao.cloud.auth.service.IClientService;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 应用管理API
 *
 * @author dengtao
 * @date 2020/4/29 15:10
*/
@RestController
@RequestMapping("/client")
@Api(value = "应用管理API", tags = { "应用管理API" })
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping("/list")
    @ApiOperation(value = "应用列表")
    public Result<PageResult<Client>> list(@RequestParam Map<String, Object> params) {
        return clientService.listClient(params, true);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取应用")
    public Result<Client> get(@PathVariable Long id) {
        Client client = clientService.getById(id);
        return Result.succeed(client);
    }

    @GetMapping("/all")
    @ApiOperation(value = "所有应用")
    public Result<List<Client>> allClient() {
        Result<PageResult<Client>> pageResultResult = clientService.listClient(Maps.newHashMap(), false);
        return Result.succeed(pageResultResult.getData().getData());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除应用")
    public Result<Boolean> delete(@PathVariable Long id) {
       return clientService.delClient(id);
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "保存或者修改应用")
    public Result<Boolean> saveOrUpdate(@RequestBody ClientDTO clientDto) {
        return clientService.saveClient(clientDto);
    }
}
