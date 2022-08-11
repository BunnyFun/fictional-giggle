package cn.workcenter.common.enums;

/**
 * @author fanbangnian
 * @since 2022/7/29 14:09
 */
public enum DbEnum {

    MySQL("mysql","com.mysql.cj.jdbc.Driver"),
    PostgreSQL("postgresql","org.postgresql.Driver"),
    Oracle("oracle","oracle.jdbc.driver.OracleDriver"),
    SQLServer("sqlserver","com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    DB2("db2","com.ibm.db2.jcc.DB2Driver"),
    H2("h2","org.h2.Driver"),
    HSQL("hsql","org.hsqldb.jdbc.JDBCDriver"),
    SQLite("sqlite","org.sqlite.JDBC"),
    Firebird("firebird","org.firebirdsql.jdbc.FBDriver"),
    Ingres("ingres","com.ingres.jdbc.IngresDriver"),
    Sybase("sybase","com.sybase.jdbc3.jdbc.SybDriver"),
    Access("access","sun.jdbc.odbc.JdbcOdbcDriver");

    private String code;
    private String str;

    DbEnum(String code, String str) {
        this.code = code;
        this.str = str;
    }

    public static DbEnum getEnum(String code) {
        for (DbEnum dbEnum : DbEnum.values()) {
            if (code.equals(dbEnum.getCode())) {
                return dbEnum;
            }
        }
        return null;
    }

    public static String getEnumStr(String code){
        for (DbEnum dbEnum : DbEnum.values()) {
            if (code.equals(dbEnum.getCode())) {
                return dbEnum.getStr();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

}
