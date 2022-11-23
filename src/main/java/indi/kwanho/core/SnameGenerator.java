package indi.kwanho.core;

import indi.kwanho.app.Context;
import indi.kwanho.config.PathConfig;
import indi.kwanho.config.TargetConfig;
import indi.kwanho.core.abs.Generator;
import indi.kwanho.util.ResourceLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class SnameGenerator implements Generator, ResourceLoader {
    private final Context context = Context.getContext();
    private final Set<String> lastNames = new HashSet<>(TargetConfig.TARGET_SIZE);
    private final Set<String> firstNames = new HashSet<>(TargetConfig.TARGET_SIZE);
    private final Set<String> commonWords = new HashSet<>();

    {
        File lastNameFile = load(PathConfig.LAST_NAME);
        File commonNameFile = load(PathConfig.COMMON_NAME);
        File commonWordFile = load(PathConfig.COMMON_WORD);
        try {
            lastNames.addAll(FileUtils.readLines(lastNameFile, StandardCharsets.UTF_8));
            FileUtils.readLines(commonNameFile, StandardCharsets.UTF_8).forEach(line -> {
                String[] values = line.split(",");
                String fullName = values[1];
                String firstName = fullName.substring(1);
                firstNames.add(firstName);
            });
            commonWords.addAll(FileUtils.readLines(commonWordFile, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generate() {
        // 常用
        for (String lastName : lastNames) {
            for (String firstName : firstNames) {
                context.getSnameSet().add(lastName + firstName);
                if (context.getSnameSet().size() >= TargetConfig.TARGET_SIZE)
                    return;
            }
        }

        // 单字
        for (String lastName : lastNames) {
            for (String commonWord : commonWords) {
                context.getSnameSet().add(lastName + commonWord);
                if (context.getSnameSet().size() >= TargetConfig.TARGET_SIZE)
                    return;
            }
        }

        // 双字
        for (String lastName : lastNames) {
            for (String commonWord1 : commonWords) {
                for (String commonWord2 : commonWords) {
                    context.getSnameSet().add(lastName + commonWord1 + commonWord2);
                    if (context.getSnameSet().size() >= TargetConfig.TARGET_SIZE) {
                        return;
                    }
                }
            }
        }
        assert context.getSnameSet().size() >= TargetConfig.TARGET_SIZE;
    }
}
