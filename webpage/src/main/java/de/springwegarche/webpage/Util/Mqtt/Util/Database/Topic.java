package de.springwegarche.webpage.Util.Mqtt.Util.Database;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "topic")
public class Topic{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;
    @Column(updatable = false, unique = false, nullable = false)
    private String name;
    @Column(updatable = false, unique = false, nullable = true)
    private long parent_id;
    @Column(updatable = true, unique = false, nullable = false)
    private String info;


    public Topic(String name, String info) {
        this.name = name;
        this.info = info;
    }


    public Topic() {
    }

    public Topic(long id, String name, long parent_id, String info) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
        this.info = info;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public long getParent_id() {
        return this.parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public String getInfo() {
        return this.info;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Topic)) {
            return false;
        }
        Topic topic = (Topic) o;
        return id == topic.id && Objects.equals(name, topic.name) && parent_id == topic.parent_id && Objects.equals(info, topic.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parent_id, info);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", parent_id='" + getParent_id() + "'" +
            ", info='" + getInfo() + "'" +
            "}";
    }

    
    /*
    @Column(updatable = true, unique = false, nullable = true)
    private List<Topic> children;
    */
}