package indi.kwanho.core;

import indi.kwanho.app.Context;
import indi.kwanho.config.TargetConfig;
import indi.kwanho.core.abs.Generator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static indi.kwanho.util.NumberUtils.getRandomInRange;


public class BirthdayGenerator implements Generator {
    private Context context = Context.getContext();

    /**
     * 19940101 ~ 19990731
     */
    @Override
    @Test
    public void generate() {
        for (int i = 0; i < TargetConfig.TARGET_SIZE; i++) {
            String[] bigMonth = {"01", "03", "05", "07", "08", "10", "12"};
            StringBuilder dateBuilder = new StringBuilder();
            dateBuilder.append(getRandomInRange(1994, 1999));
            String month = getRandomInRange(1, 12);
            dateBuilder.append(month);
            if (Arrays.asList(bigMonth).contains(month)) {
                dateBuilder.append(getRandomInRange(1, 31));
            } else if (month.equals("02")) {
                dateBuilder.append(getRandomInRange(1, 28));
            } else {
                dateBuilder.append(getRandomInRange(1, 30));
            }
            context.getBirthdayList().add(dateBuilder.toString());
            dateBuilder.delete(0, dateBuilder.length());
        }
    }
}
