package com.agnaldo4j.persistence;

public interface Query<STATE, RESULT> {
    RESULT execute(STATE state);
}
