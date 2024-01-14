package com.su0h.Chores.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "metadata")
public class Metadata {
    @Id
    private String key;
    private String value;

    public static enum Key {
        LAST_MODIFIED,
        LAST_UNSHIFTED
    }

    protected Metadata () { }

    public Metadata(Key key, String value) {
        this.key = key.name();
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
