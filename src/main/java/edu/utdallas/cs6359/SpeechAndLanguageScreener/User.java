package edu.utdallas.cs6359.SpeechAndLanguageScreener;

public class User {
    private String name;

    public enum Type {
        TESTER,
        TESTEE
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
