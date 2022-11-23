package indi.kwanho.app;

import indi.kwanho.core.*;
import indi.kwanho.structure.Template;
import indi.kwanho.util.ResourceLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static indi.kwanho.config.PathConfig.RESUlT;

public class GeneratorProxy implements ResourceLoader {
    Context context = Context.getContext();
    private final SnoGenerator snoGenerator = new SnoGenerator();
    private final SnameGenerator snameGenerator = new SnameGenerator();
    private final SexGenerator sexGenerator = new SexGenerator();
    private final TelGenerator telGenerator = new TelGenerator();
    private final EmailGenerator emailGenerator = new EmailGenerator();
    private final BirthdayGenerator birthdayGenerator = new BirthdayGenerator();
    private final NodeGenerator nodeGenerator = new NodeGenerator();

    public void generate() {
        snoGenerator.generate();
        snameGenerator.generate();
        sexGenerator.generate();
        telGenerator.generate();
        emailGenerator.generate();
        birthdayGenerator.generate();
    }

    public void generateNodes() {
        nodeGenerator.generate();
    }

    public void build() {
        StringBuilder sql = new StringBuilder();
        context.getNodeList().forEach(node -> {
            Template template = new Template(node);
            sql.append(template).append("\n");
        });
        var resultFile = new File(RESUlT);
        if (resultFile.exists())
            resultFile.delete();
        try {
            FileUtils.write(new File(resultFile.toURI()), sql.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("写入结果失败");
            throw new RuntimeException(e);
        }
    }

    public void flow() {
        generate();
        generateNodes();
        build();
    }
}
