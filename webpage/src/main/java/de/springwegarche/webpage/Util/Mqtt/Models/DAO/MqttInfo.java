package de.springwegarche.webpage.Util.Mqtt.Models.DAO;

import java.util.List;

/*
Ist das Json was in dem /info drinsteht

*/
public class MqttInfo {
    // "Anton´s Zimmer/Terrarien/Allgemein"
    private String mainTopic;

    // ["/relais1", "/relais2"]
    private List<String> subTopics;

    // "on/off"
    // "value:temperature/humidity/length"
    // "value:percent"
    // "value:duration    in ms"
    private String description; 
}
