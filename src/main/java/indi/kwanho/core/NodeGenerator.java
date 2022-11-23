package indi.kwanho.core;

import indi.kwanho.app.Context;
import indi.kwanho.config.TargetConfig;
import indi.kwanho.core.abs.Generator;
import indi.kwanho.structure.Node;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.List;

public class NodeGenerator implements Generator {
    private Context context = Context.getContext();
    private final HanyuPinyinOutputFormat format;
    private final String SUFFIX = "@csu.edu.cn";

    public NodeGenerator() {
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    @Override
    public void generate() {
        // 列表化
        List<String> snoList = context.getSnoSet().stream().toList();
        List<String> snameList = context.getSnameSet().stream().toList();
        List<String> sexList = context.getSexList();
        List<String> telList = context.getTelSet().stream().toList();
        // email
        List<String> birthdayList = context.getBirthdayList();
        // mno
        // majorNo

        for (int i = 0; i < TargetConfig.TARGET_SIZE; i++) {
            Node node = new Node();
            node.setSno(snoList.get(i));
            node.setSname(snameList.get(i));
            node.setSex(sexList.get(i));
            node.setTel(telList.get(i));
            String pinyin;
            try {
                pinyin = PinyinHelper.toHanYuPinyinString(snameList.get(i), format, null, false);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                pinyin = snameList.get(i);
            }
            String email = pinyin + SUFFIX;
            node.setEmail(email);
            node.setBirthday(birthdayList.get(i));
            node.setMno(snoList.get(i).substring(0, 8) + "01");
            node.setMajorNo(snoList.get(i).substring(0, 5));
            context.getNodeList().add(node);
        }
    }
}
