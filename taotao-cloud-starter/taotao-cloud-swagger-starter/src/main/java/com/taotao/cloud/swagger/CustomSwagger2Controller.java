/**
 * Project Name: my-projects
 * Package Name: com.taotao.cloud.swagger
 * Date: 2020/5/18 11:28
 * Author: dengtao
 */
package com.taotao.cloud.swagger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;
import springfox.documentation.swagger2.web.Swagger2Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <br>
 *
 * @author dengtao
 * @version v1.0.0
 * @create 2020/5/18 11:28
 */
@Controller
public class CustomSwagger2Controller implements InitializingBean {
    @Autowired
    private Environment environment;
    @Autowired
    private DocumentationCache documentationCache;
    @Autowired
    private ServiceModelToSwagger2Mapper mapper;
    @Autowired
    private JsonSerializer jsonSerializer;

    private Swagger2Controller swagger2Controller;

    @Override
    public void afterPropertiesSet() throws Exception {
        swagger2Controller = new Swagger2Controller(environment, documentationCache, mapper, jsonSerializer);
    }

    @RequestMapping(value = "/api-docs", method = RequestMethod.GET, produces = {"application/json", "application/hal+json"})
    @ResponseBody
    public ResponseEntity<Json> getDocumentation(
            @RequestParam(value = "group", required = false) String swaggerGroup,
            HttpServletRequest servletRequest) {
        return swagger2Controller.getDocumentation(swaggerGroup, servletRequest);
    }

}
