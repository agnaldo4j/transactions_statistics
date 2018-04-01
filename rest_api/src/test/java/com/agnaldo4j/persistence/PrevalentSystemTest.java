package com.agnaldo4j.persistence;

import org.junit.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PrevalentSystemTest {

    private void initialState(PrevalentSystem<Set<String>> system) throws Exception {
        system.execute(new AddStringCommand("Scala"));
        system.execute(new AddStringCommand("Java"));
        system.execute(new AddStringCommand("Kotlin"));
    }

    @Test
    public void searchALanguageOnEmptyState() throws Exception {
        PrevalentSystem<Set<String>> system = new PrevalentSystem<>("teste1.dat");
        system.load(new HashSet<>());
        Optional<String> value = system.execute(new SelectLanguageCommand("Kotlin"));
        system.destroyState();
        assertEquals("", value.orElse(""));
    }

    @Test
    public void searchALanguageOnState() throws Exception {
        PrevalentSystem<Set<String>> system = new PrevalentSystem<>("teste2.dat");
        system.load(new HashSet<>());
        initialState(system);
        Optional<String> value = system.execute(new SelectLanguageCommand("Kotlin"));
        system.destroyState();
        assertEquals("Kotlin", value.orElse(""));
    }

    @Test
    public void searchALanguageOnReloadedState() throws Exception {
        PrevalentSystem<Set<String>> system = new PrevalentSystem<>("teste3.dat");
        system.load(new HashSet<>());
        initialState(system);

        system = new PrevalentSystem<>("teste3.dat");
        system.load(new HashSet<>());
        Optional<String> value = system.execute(new SelectLanguageCommand("Kotlin"));
        system.destroyState();
        assertEquals("Kotlin", value.orElse(""));
    }
}
