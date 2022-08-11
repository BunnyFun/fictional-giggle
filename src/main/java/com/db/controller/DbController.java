package com.db.controller;

import cn.workcenter.common.response.AjaxResult;
import com.db.dto.ExecuteQuerySqlDto;
import com.db.service.DbService;
import com.mauersu.util.Constant;
import com.mauersu.util.RedisApplication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fanbangnian
 * @since 2022/7/28 16:21
 */
@Controller
@RequestMapping("/db/manage")
public class DbController implements Constant {

    @Autowired
    private DbService dbService;

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public Object index(HttpServletRequest request, HttpServletResponse response) {
        String defaultServerName = (String) (RedisApplication.redisServerCache.get(0) == null ? ""
            : RedisApplication.redisServerCache.get(0).get("name"));
        request.setAttribute("serverName", defaultServerName);
        request.setAttribute("dbIndex", DEFAULT_DBINDEX);
        request.setAttribute("viewPage", "db/databaseControl.jsp");
        return "admin/main";
    }

    @RequestMapping(value = "/executeQuerySql", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult executeQuerySql(@RequestBody @Validated ExecuteQuerySqlDto request) {
        return dbService.executeQuerySql(request);
    }


}
