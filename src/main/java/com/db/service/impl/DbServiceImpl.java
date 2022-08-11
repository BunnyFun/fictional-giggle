package com.db.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.workcenter.common.enums.DbEnum;
import cn.workcenter.common.enums.ResultCodeEnum;
import cn.workcenter.common.response.AjaxResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.db.dto.ExecuteQuerySqlDto;
import com.db.service.DbService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author fanbangnian
 * @since 2022/7/28 16:43
 */
@Service
public class DbServiceImpl implements DbService {

    // 1、申明数据库变量
    // mysql
    // 数据库地址
//    private static final String url = "jdbc:mysql://172.22.26.247:3307/smartpark";
//    // 数据库驱动
//    private static final String name = "com.mysql.cj.jdbc.Driver";
//    // 数据库登录用户名
//    private static final String username = "smartpark";
//    // 数据库登录密码
//    private static final String password = "sptest";

    // 连接对象
    private static Connection connection = null;
    // 事务对象
    private static PreparedStatement pst = null;
    // 定义sql语句只允许为select操作
    private static final Pattern PATTERN = Pattern.compile(
        "^(?i)(\\s*)(select)(\\s+)(((?!(insert|delete|update|drop)).)+)$");

    // 2、加载驱动

    /**
     * 获取连接
     */
    private static void getConnection(ExecuteQuerySqlDto sqlDto) throws Exception {
        try {
            // 获取驱动
            String driver = DbEnum.getEnumStr(sqlDto.getDbType());
            Class.forName(driver);// 加载驱动
            connection = DriverManager.getConnection(sqlDto.getUrl(), sqlDto.getUserName(),
                sqlDto.getPassword());
        } catch (Exception e) {
            throw e;
        }
    }

    // 3、执行sql语句

    /**
     * 执行sql查询
     */
    public AjaxResult executeQuerySql(ExecuteQuerySqlDto sqlDto) {
        AjaxResult ajaxResult = new AjaxResult<>();
        try {
            // 判断sql是否合规
            Matcher matcher = PATTERN.matcher(sqlDto.getSql());
            if (!matcher.matches()) {
                ajaxResult.setStatus(ResultCodeEnum.FAIL.getCode());
                ajaxResult.setMessage("sql语句不合规");
                return ajaxResult;
            }
            // 获取连接
            getConnection(sqlDto);
            pst = connection.prepareStatement(sqlDto.getSql());
            // 查询结果
            ResultSet result = pst.executeQuery();
            ResultSetMetaData rsmd = result.getMetaData();
            // 表格头
            JSONArray tableTitle = new JSONArray();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                // 表格头单元格
                JSONObject tableTitle_Th = new JSONObject();
                // 字段名
                tableTitle_Th.put("columnname", rsmd.getColumnName(i));
                // 表名
                tableTitle_Th.put("tablename", rsmd.getTableName(i));
                // JAVA_数据类型
                tableTitle_Th.put("columnclassname", rsmd.getColumnClassName(i));
                // DB_数据类型
                tableTitle_Th.put("columntypename",
                    rsmd.getColumnTypeName(i) + "(" + rsmd.getColumnDisplaySize(i) + ")");
                // 保存到数组
                tableTitle.add(tableTitle_Th);
            }
            // 所有查询数据
            JSONObject table = new JSONObject();
            // 表格内容
            JSONArray tableBody = new JSONArray();
            while (result.next()) {
                // 表内容单元格
                JSONArray tableRow = new JSONArray();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    // 数据类型
                    String classname = rsmd.getColumnClassName(i);
                    // 字段值
                    String rowStr = JSONObject.toJSONString(result.getObject(i));
//                    Object rowStr = result.getObject(i);
                    if (null == rowStr) {
                        rowStr = "";
                    }
                    rowStr = rowStr.replace("\"", "");
                    tableRow.add(rowStr);
                }
                tableBody.add(tableRow);
            }
            connection.close();// 关闭
            table.put("tableTitle", tableTitle);
            table.put("tableBody", tableBody);
            ajaxResult.setStatus(ResultCodeEnum.SUCCESS.getCode());
            ajaxResult.setMessage("查询成功");
            ajaxResult.setObject(table);
            return ajaxResult;
        } catch (Exception e) {
            ajaxResult.setStatus(ResultCodeEnum.FAIL.getCode());
            ajaxResult.setMessage(e.getMessage());
            return ajaxResult;
        }
    }

}
