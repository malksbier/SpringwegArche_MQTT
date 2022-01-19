package de.springwegarche.webpage.Util.Mqtt.Models.Rules;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import de.springwegarche.webpage.Util.Mqtt.Models.Rules.Enum.BoolOperatives;

@Entity
@Table(name = "dependency_bracket")
public class DependencyBracket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER) 
    private Dependency dependency;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(updatable = true, unique = false, nullable = true)
    private DependencyBracket dependencyBracketAfter;

    @Column(updatable = false, unique = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private BoolOperatives boolOperative;
}

