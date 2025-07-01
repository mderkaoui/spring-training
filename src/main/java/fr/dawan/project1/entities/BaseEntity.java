package fr.dawan.project1.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@MappedSuperclass //ne crée pas de tables, elle sert à factoriser des propriétés pour les sous-classes
@NoArgsConstructor
public class BaseEntity {

    @Id //clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private long id;

    @Version
    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id == that.id && version == that.version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }
}
