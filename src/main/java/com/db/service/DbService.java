package com.db.service;

import cn.workcenter.common.response.AjaxResult;
import com.db.dto.ExecuteQuerySqlDto;

/**
 * @author fanbangnian
 * @since 2022/7/28 16:43
 */
public interface DbService {

    AjaxResult executeQuerySql(ExecuteQuerySqlDto request);

}
