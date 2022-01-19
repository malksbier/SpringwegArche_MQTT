package de.springwegarche.webpage.Util.Mqtt.Models.Rules;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Main Class for Rules that controll Topics based on something (Time, other Topics)
 */
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id;

    private Dependency dependency;
    private Resolution resolution;

}
