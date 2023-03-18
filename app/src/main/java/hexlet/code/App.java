package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

class App {

    public static void main(String[] args) {
        System.out.println("hello world");
    }

}
