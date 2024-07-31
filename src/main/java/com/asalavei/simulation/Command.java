package com.asalavei.simulation;

public enum Command {
    START_NEW_SIMULATION("s"),
    PAUSE_RESUME("p"),
    QUIT("q"),
    INVALID_COMMAND("");

    private final String key;

    Command(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static Command fromString(String input) {
        for (Command command : Command.values()) {
            if (command.getKey().equalsIgnoreCase(input)) {
                return command;
            }
        }

        return INVALID_COMMAND;
    }
}
