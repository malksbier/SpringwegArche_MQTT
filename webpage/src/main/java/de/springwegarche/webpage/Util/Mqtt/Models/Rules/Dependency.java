package de.springwegarche.webpage.Util.Mqtt.Models.Rules;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class which knows when to apply a rule
 */
@Entity
@Table(name = "dependency")
public class Dependency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @OneToMany(mappedBy = "dependency", cascade = CascadeType.ALL)
    private Set<DependencyBracket> topicsToChange = new HashSet<DependencyBracket>();
}
