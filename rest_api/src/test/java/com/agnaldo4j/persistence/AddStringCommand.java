package com.agnaldo4j.persistence;

import java.util.Set;

public class AddStringCommand implements Command<Set<String>> {

    private String language;

    public AddStringCommand(String language) {
        this.language = language;
    }

    @Override
    public void execute(Set<String> state) {
        state.add(language);
    }
}
