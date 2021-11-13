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
    
    /*
    @Column(updatable = true, unique = false, nullable = true)
    private List<Topic> children;
    */
}