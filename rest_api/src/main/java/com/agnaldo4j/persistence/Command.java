package com.agnaldo4j.persistence;

import java.io.Serializable;

public interface Command<STATE> extends Serializable {
    public void execute(STATE state);
}
