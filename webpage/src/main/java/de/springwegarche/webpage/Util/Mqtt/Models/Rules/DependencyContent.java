package de.springwegarche.webpage.Util.Mqtt.Models.Rules;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;

@Entity
@Table(name = "dependency_content")
public class DependencyContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(updatable = true, unique = false, nullable = false)
    private Topic topic;

    @Column(updatable = true, unique = false, nullable = false)
    private String logic;
}
