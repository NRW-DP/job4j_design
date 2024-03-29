package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.*;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(ArgsName arg) {
        Path dir = Paths.get(arg.get("d"));
        if (!Files.isDirectory(dir) || !Files.exists(dir)) {
            throw new IllegalArgumentException("There's no directory");
        }
        if (!arg.get("e").startsWith(".") || arg.get("e").length() == 1) {
            throw new IllegalArgumentException("Extension of excluding files is not correct");
        }
        if (!arg.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Extension of Zip file is not correct");
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Amount of arguments is not enough");
        }
        ArgsName arg = ArgsName.of(args);
        Zip zipFiles = new Zip();
        zipFiles.validate(arg);
        List<Path> paths = Search.search(Paths.get(arg.get("d")),
                p -> !p.toFile().getName().endsWith(arg.get("e")));
        zipFiles.packFiles(paths, new File(arg.get("o")));

        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
