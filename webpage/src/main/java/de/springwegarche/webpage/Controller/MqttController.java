package de.springwegarche.webpage.Controller;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.springwegarche.webpage.Models.DAO.MqttDirectoryResponse;
import de.springwegarche.webpage.Util.WebResponses;
import de.springwegarche.webpage.Util.Mqtt.Models.DAO.InvertedTopic;
import de.springwegarche.webpage.Util.Mqtt.Models.DAO.NameSetByUserRequest;
import de.springwegarche.webpage.Util.Mqtt.Models.DAO.TopicStatusRequest;
import de.springwegarche.webpage.Util.Mqtt.Util.TopicWriter;
import de.springwegarche.webpage.Util.Mqtt.Util.Serives.TopicsService;

@RestController
public class MqttController {
    private final String TAG = "[AuthenicationController] ";
    private final String mainRoute = "/mqtt";

    @Autowired 
    TopicsService topicsService;

    @RequestMapping(value = mainRoute + "/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() throws Exception {

        ArrayList<InvertedTopic> topics = topicsService.getAllTopicsInverted();
        MqttDirectoryResponse result = new MqttDirectoryResponse(topics);

        ObjectMapper objectMapper = new ObjectMapper();
        String resultString = objectMapper.writeValueAsString(result);

        return WebResponses.okResponse(resultString);
    }
    @RequestMapping(value = mainRoute + "/postTopicStatus", method = RequestMethod.POST)
    public ResponseEntity<?> postTopicStatus(@RequestBody TopicStatusRequest request) throws Exception {
        String fullTopic = topicsService.getFullMqttTopicString(request.getId());
        System.out.println(TAG + "writng to Topic: " + fullTopic + ", status: " + request.getStatus());

        TopicWriter.writeToTopic(fullTopic, request.getStatus(), 1, false);

        return WebResponses.okResponse("good");
    }
    @RequestMapping(value = mainRoute + "/postNameSetByUser", method = RequestMethod.POST)
    public ResponseEntity<?> postNameSetByUser(@RequestBody NameSetByUserRequest request) throws Exception {

        topicsService.updateNameSetByUser(request.getId(),request.getNameSetByUser());

        return WebResponses.okResponse("good");
    }
    
    
    
}
