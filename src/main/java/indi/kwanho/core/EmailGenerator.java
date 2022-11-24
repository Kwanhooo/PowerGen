package indi.kwanho.core;

import indi.kwanho.app.Context;
import indi.kwanho.core.abs.Generator;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

@Deprecated
public class EmailGenerator implements Generator {
    Context context = Context.getContext();
    private final String SUFFIX = "@csu.edu.cn";
    private final HanyuPinyinOutputFormat format;

    public EmailGenerator() {
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    @Override
    public void generate() {
        context.getSnameSet().stream().toList().forEach(fullName -> {
            String pinyin;
            try {
                pinyin = PinyinHelper.toHanYuPinyinString(fullName, format, null, false);
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                pinyin = fullName;
            }
            String email = pinyin + SUFFIX;
//            context.getEmailList().add(email);
        });
    }
}
