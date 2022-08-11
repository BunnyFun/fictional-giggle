<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String basePath = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css"
      href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.css">

<style>
  #page-demo .form-group {
    margin-bottom: 15px;
  }

  #page-demo form label {
    min-width: 100px;
  }

  #page-demo form input, #page-demo form select {
    width: 200px;
  }

  th {
    background: #ccc;
  }
  .table-box {
    width: 100%;
  }
</style>

<div class="col-sm-12 col-md-12 main">
    <div id="page-demo" class="container">
        <form class="form-inline">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="databaseType">数据库类型：</label>
                        <!-- <input type="text" class="form-control" id="exampleInputName2" placeholder="请选择数据库类型"> -->
                        <select class="form-control" id="databaseType">
                            <option value="mysql">MySQL</option>
                            <option value="postgresql">PostgreSQL</option>
                            <option value="oracle">Oracle</option>
                            <option value="sqlserver">SQLServer</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="databaseURL">数据库URL：</label>
                        <input type="text" style="width: 300px;" class="form-control"
                               id="databaseURL" placeholder="请输入数据库URL">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="username">账号：</label>
                        <input type="text" class="form-control" id="username" placeholder="请输入账号">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="password">密码：</label>
                        <input type="password" class="form-control" id="password"
                               placeholder="请输入密码">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <label>SQL：</label>
                    <div class="form-group" style="width: 100%;">
                        <textarea id="sql" class="form-control" style="width: 100%;" rows="5"
                                  placeholder="请输入内容"></textarea>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <button id="execute" class="btn btn-info pull-right">执行</button>
                </div>
            </div>
        </form>
        <div>
            <label>执行结果：</label>
        </div>
        <div class="table-box">
            <table id="table1" class="display  cell-border" style="width: 100%;"></table>
        </div>

    </div>
</div>

<script src="<%=basePath%>/js/admin/redis/dataTypeLineHtml.js"></script>
<script type="text/javascript" charset="utf8"
        src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
<script>
    let table_ = null
  $(document).ready(function () {

    $("#username").val("");
    $("#password").val("");

    // 点击执行按钮
    $("#execute").on("click", function () {
      const url = "<%=basePath%>/db/manage/executeQuerySql";
      const dbType = $("#databaseType").val();
      const dbUrl = $("#databaseURL").val();
      const username = $("#username").val();
      const password = $("#password").val();
      const sql = $("#sql").val();
      $.ajax({
        type: "post",
        url: url,
        dataType: "json",
        beforeSend: function (xhr) {
          xhr.setRequestHeader('Content-Type', 'application/json');
        },
        data: JSON.stringify({
          dbType: dbType,
          url: dbUrl,
          userName: username,
          password: password,
          sql: sql
        }),
        success: function (data) {
          if (data.status == 0) {
            if (table_) {
              table_.destroy();
              table_.clear();
              table_ = null;
            }
            const tableBoxDom = document.querySelector('.table-box')
            const table = tableBoxDom.querySelector('table')

            table.remove()
            const newTable = document.createElement('table')
            const id = 't_'+new Date().getTime()
            newTable.setAttribute('id', id)
            newTable.setAttribute('class','display cell-border' )
            newTable.setAttribute('style','width: 100%;' )
            tableBoxDom.appendChild(newTable)
            const columns = data.object.tableTitle.map((item, index) => {
              return {
                title: item.columnname,
                width: '50px'
              }
            })
            table_ = $('#'+id).DataTable({
              data: data.object.tableBody,
              columns,
              paging: false,
              searching: false,
              ordering: false,
              info: false,
              scrollY: '500px',
              responsive: false,
              scrollX: true,
              scrollCollapse: true,
              destroy: true
            });

          } else {
            alert(data.message);
          }
        }
      })
      return false;
    })

  });
</script>
