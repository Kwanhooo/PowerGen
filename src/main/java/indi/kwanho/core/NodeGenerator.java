package indi.kwanho.core;

import indi.kwanho.app.Context;
import indi.kwanho.config.TargetConfig;
import indi.kwanho.core.abs.Generator;
import indi.kwanho.structure.Node;
import indi.kwanho.structure.Template;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static indi.kwanho.config.PathConfig.RESUlT;
import static indi.kwanho.config.TargetConfig.PAGE_SIZE;

public class NodeGenerator implements Generator {
    private final Context context = Context.getContext();
    private final HanyuPinyinOutputFormat format;
    private final static String SUFFIX = "@csu.edu.cn";

    public NodeGenerator() {
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    @Override
    public void generate() {
        // convert to list
        List<String> snoList = context.getSnoSet().stream().toList();
        List<String> snameList = context.getSnameSet().stream().toList();
        List<String> sexList = context.getSexList();
        List<String> telList = context.getTelSet().stream().toList();
        List<String> birthdayList = context.getBirthdayList();

        int pagination = 0;
        File resultFile = null;
        for (int i = 0; i < TargetConfig.TARGET_SIZE; i++) {
            if (i % PAGE_SIZE == 0) {
                pagination++;
                resultFile = new File(RESUlT + "_" + pagination + ".sql");
                if (resultFile.exists())
                    resultFile.delete();
                System.out.println("Node => " + i);
            }
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
            Template template = new Template(node);
            String sql = template + "\n";
            try {
                FileUtils.write(new File(resultFile.toURI()), sql, StandardCharsets.UTF_8, true);
            } catch (IOException e) {
                System.out.println("写入结果失败");
                throw new RuntimeException(e);
            }
        }
    }
}
