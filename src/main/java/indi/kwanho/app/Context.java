package indi.kwanho.app;

import indi.kwanho.structure.Node;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Context {
    private final Set<String> snoSet;
    private final Set<String> snameSet;
    private final List<String> sexList;
    private final Set<String> telSet;
    private final List<String> birthdayList;
    private final List<Node> nodeList;
    private static final Context context = new Context();

    private Context() {
        this.snoSet = new HashSet<>();
        this.snameSet = new HashSet<>();
        this.sexList = new ArrayList<>();
        this.telSet = new HashSet<>();
        this.birthdayList = new ArrayList<>();
        this.nodeList = new ArrayList<>();
    }

    public static Context getContext() {
        return context;
    }
}
