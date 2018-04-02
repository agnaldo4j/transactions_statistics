package com.agnaldo4j.persistence;

import java.io.*;

public class PrevalentSystem<STATE> {

    private STATE state;
    private ObjectOutputStream journal;
    private File stateFile;

    public PrevalentSystem(String stateFile) {
        this(new File(stateFile));
    }

    public PrevalentSystem() {
        super();
    }

    public PrevalentSystem(File stateFile) {
        this.stateFile = stateFile;
    }

    public void destroyState() throws IOException {
        journal.flush();
        journal.close();
        this.stateFile.delete();
    }

    public synchronized void execute(Command<STATE> command) throws IOException {
        writeToJournal(command);
        command.execute(state);
    }

    public <RESULT> RESULT execute(Query<STATE, RESULT> query) {
        return query.execute(state);
    }

    public void load(STATE initialState) throws IOException, ClassNotFoundException {
        File tmpFile = new File(this.stateFile.getAbsolutePath() + ".tmp");
        state = restoreState(initialState, tmpFile);
        journal = new ObjectOutputStream(new FileOutputStream(tmpFile));
        writeToJournal(state);
        if (this.stateFile.exists()) this.stateFile.delete();
        if (!tmpFile.renameTo(this.stateFile))
            throw new IOException("Unable to rename " + tmpFile + " to " + this.stateFile);
    }

    private STATE restoreState(STATE initialState, File tempFile) throws IOException, ClassNotFoundException {
        if (this.stateFile.exists()) return restoreStateFrom(this.stateFile);
        else if (tempFile.exists()) return restoreStateFrom(tempFile);
        else return initialState;
    }

    private STATE restoreStateFrom(File storage) throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(storage));
        STATE restoredState = restoreImage(input);
        restoreCommands(restoredState, input);
        input.close();
        return restoredState;
    }

    private STATE restoreImage(ObjectInputStream input) throws IOException, ClassNotFoundException {
        return (STATE) input.readObject();
    }

    private void restoreCommands(STATE state, ObjectInputStream input) throws IOException, ClassNotFoundException {
        try {
            while (true) ((Command<STATE>) input.readObject()).execute(state);
        } catch (EOFException e) { }
    }

    private void writeToJournal(STATE state) throws IOException {
        journal.writeObject(state);
        journal.flush();
    }

    private void writeToJournal(Command<STATE> command) throws IOException {
        journal.writeObject(command);
        journal.flush();
    }
}
