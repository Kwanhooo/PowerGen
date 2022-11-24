package indi.kwanho.app;

import indi.kwanho.core.*;
import indi.kwanho.util.ResourceLoader;

public class GeneratorProxy implements ResourceLoader {
    Context context = Context.getContext();
    private final SnoGenerator snoGenerator = new SnoGenerator();
    private final SnameGenerator snameGenerator = new SnameGenerator();
    private final SexGenerator sexGenerator = new SexGenerator();
    private final TelGenerator telGenerator = new TelGenerator();
    //    private final EmailGenerator emailGenerator = new EmailGenerator();
    private final BirthdayGenerator birthdayGenerator = new BirthdayGenerator();
    private final NodeGenerator nodeGenerator = new NodeGenerator();

    public void generateData() {
        snoGenerator.generate();
        System.out.println("sno done");
        snameGenerator.generate();
        System.out.println("sname done");
        sexGenerator.generate();
        System.out.println("sex done");
        telGenerator.generate();
        System.out.println("tel done");
//        emailGenerator.generate();
//        System.out.println("email done");
        birthdayGenerator.generate();
        System.out.println("birthday done");
    }

    public void generateNodes() {
        nodeGenerator.generate();
    }


    public void flow() {
        generateData();
        System.out.println("data all done");
        generateNodes();
        System.out.println("node all done");
    }
}
