package indi.kwanho.config;

public class TargetConfig {
    public static final int TARGET_SIZE = 10_000_000;
    public static final int SPLIT = 50;
    public static final int PAGE_SIZE = TARGET_SIZE / SPLIT;

    /**
     * About Database
     */
    public static final String TABLE_NAME = "T_ATTEND_09504";
}
