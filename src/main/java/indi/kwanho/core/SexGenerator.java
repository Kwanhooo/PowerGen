package indi.kwanho.core;

import indi.kwanho.app.Context;
import indi.kwanho.config.TargetConfig;
import indi.kwanho.core.abs.Generator;

public class SexGenerator implements Generator {

    private final Context context = Context.getContext();

    @Override
    public void generate() {
        for (int i = 0; i < TargetConfig.TARGET_SIZE; i++) {
            context.getSexList().add(((int) (Math.random() * 2)) == 1 ? "男" : "女");
        }
    }
}
