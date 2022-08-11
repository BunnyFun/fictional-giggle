package cn.workcenter.common.enums;

/**
 * @author fanbangnian
 * @since 2022/8/2 11:13
 */
public enum ResultCodeEnum {

    FAIL(-1,"失败"),
    SUCCESS(0,"成功");

    private int code;
    private String str;

    ResultCodeEnum(int code, String str) {
        this.code = code;
        this.str = str;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

}
