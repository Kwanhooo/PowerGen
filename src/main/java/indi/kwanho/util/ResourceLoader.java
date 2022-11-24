package indi.kwanho.util;

import java.io.File;
import java.util.Objects;

public interface ResourceLoader {
    default File load(String filename) {
        return new File(Objects.requireNonNull(getClass().getClassLoader().getResource(filename)).getPath());
    }
}
