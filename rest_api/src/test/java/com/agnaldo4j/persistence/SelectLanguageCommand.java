package com.agnaldo4j.persistence;

import java.util.Optional;
import java.util.Set;

public class SelectLanguageCommand implements Query<Set<String>, Optional<String>> {

    private String language;

    public SelectLanguageCommand(String language) {
        this.language = language;
    }

    @Override
    public Optional<String> execute(Set<String> state) {
        return state.stream().filter(s -> s.equalsIgnoreCase(language)).findFirst();
    }
}
