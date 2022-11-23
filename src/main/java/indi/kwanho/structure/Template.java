package indi.kwanho.structure;

import indi.kwanho.config.TargetConfig;

public class Template {
    private final Node node;

    public Template(Node node) {
        this.node = node;
    }

    @Override
    public String toString() {
        // insert into C##U_504.T_STUD_09504 (SNO, SNAME, SEX, TEL, "E-MAIL", BIRTHDAY, MNO, "MajorNo", SUM_EVALUATION) values ();
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ")
                .append(TargetConfig.TABLE_NAME)
                .append(" ")
                .append("(SNO, SNAME, SEX, TEL, \"E-MAIL\", BIRTHDAY, MNO, \"MajorNo\", SUM_EVALUATION) ")
                .append("values (")
                .append(node)
                .append(");");
        return sql.toString();
    }
}
