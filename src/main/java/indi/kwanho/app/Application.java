package indi.kwanho.app;

public class Application {
    private static final Context context = Context.getContext();
    private static final GeneratorProxy generatorProxy = new GeneratorProxy();

    public static void main(String[] args) {
        generatorProxy.flow();
    }
}