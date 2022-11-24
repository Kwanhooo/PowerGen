package indi.kwanho.core;

import indi.kwanho.app.Context;
import indi.kwanho.config.TargetConfig;
import indi.kwanho.core.abs.Generator;

public class TelGenerator implements Generator {
    private final Context context = Context.getContext();

    @Override
    public void generate() {
        StringBuilder telBuilder = new StringBuilder("1");
        while (context.getTelSet().size() < TargetConfig.TARGET_SIZE) {
            for (int j = 0; j < 10; j++) {
                telBuilder.append((int) (Math.random() * 10));
            }
            context.getTelSet().add(telBuilder.toString());
            telBuilder.delete(1, telBuilder.length());
        }
    }
}
