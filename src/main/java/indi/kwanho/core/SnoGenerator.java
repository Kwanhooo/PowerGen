package indi.kwanho.core;

import indi.kwanho.app.Context;
import indi.kwanho.config.TargetConfig;
import indi.kwanho.core.abs.Generator;

import static indi.kwanho.util.NumberUtils.getRandomInRange;

public class SnoGenerator implements Generator {
    private final Context context = Context.getContext();

    /**
     * 格式 -> ABCD19EFGH
     * AB为从01到99
     * CD为从01到90
     * EF为01到50
     * GH为01到32
     */
    @Override
    public void generate() {
        StringBuilder snoBuilder = new StringBuilder();
        while (context.getSnoSet().size() < TargetConfig.TARGET_SIZE) {
            snoBuilder.append(getRandomInRange(1, 99));
            snoBuilder.append(getRandomInRange(1, 90));
            snoBuilder.append("19");
            snoBuilder.append(getRandomInRange(1, 50));
            snoBuilder.append(getRandomInRange(1, 32));
            context.getSnoSet().add(snoBuilder.toString());
            snoBuilder.delete(0, snoBuilder.length());
        }
    }
}
