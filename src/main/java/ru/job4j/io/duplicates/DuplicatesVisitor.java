package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Set<FileProperty> withoutDuplicates = new HashSet<>();
    private final List<FileProperty> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (!withoutDuplicates.add(fileProperty)) {
            duplicates.add(fileProperty);
        }
        return super.visitFile(file, attrs);
    }

    public void outPutDuplicates() {
        duplicates.forEach(duplicate -> System.out.printf("File %s is duplicated.%n", duplicate.getName()));
    }
}
