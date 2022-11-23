package indi.kwanho.structure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Node {
    private String sno;
    private String sname;
    private String sex;
    private String tel;
    private String email;
    private String birthday;
    private String mno;
    private String majorNo;

    @Override
    public String toString() {
        StringBuilder nodeValues = new StringBuilder();
        nodeValues.append(sno).append(",");
        nodeValues.append(sname).append(",");
        nodeValues.append(sex).append(",");
        nodeValues.append(tel).append(",");
        nodeValues.append(email).append(",");
        nodeValues.append(birthday).append(",");
        nodeValues.append(mno).append(",");
        nodeValues.append(majorNo).append(",");
        nodeValues.append("100");
        return nodeValues.toString();
    }
}
