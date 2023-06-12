package team.sdn.enums;

/**
 * SFlow使用的几种统计量
 *
 * @author Big-Bai
 **/
public enum SFlowStatistic {
    MAX("max"),
    MIN("min"),
    SUM("sum"),
    AVG("avg"),
    VAR("var"),
    SDEV("sdev"),
    MED("med"),
    Q1("q1"),
    Q2("q2"),
    Q3("q3"),
    IQR("iqr"),
    ANY("any");

    private String value;

    public String getValue() {
        return value;
    }

    SFlowStatistic(String value) {
        this.value = value;
    }
}
