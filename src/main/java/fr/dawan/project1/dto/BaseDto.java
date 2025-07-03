package fr.dawan.project1.dto;



import java.io.Serializable;
import java.util.Objects;


public class BaseDto implements Serializable {
    private long id;

    private int version;



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseDto baseDto = (BaseDto) o;
        return id == baseDto.id && version == baseDto.version;
    }

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
    public int hashCode() {
        return Objects.hash(id, version);
    }
}
