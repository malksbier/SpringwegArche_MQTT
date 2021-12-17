<template>
  <div class="mqtt-topic-row-display" style = style>
    <v-row>
  
      <p> {{mqttTopic.topicName}} </p>
      
      <div v-if="mqttTopic.children.length > 0" >
        <p> {{mqttTopic.topicName}} </p>
      </div>
      
    </v-row>
    
    <div v-for="mqttTop in mqttTopic.children" :key = mqttTop.id>
      <MqttTopicRowDisplays :mqttTopic = mqttTop :marginLeft = 20></MqttTopicRowDisplays>
    </div>
  </div>
</template>

<script>
import MqttTopicRowDisplays from '../components/MqttTopicRowDisplay.vue'
export default {
  name: "MqttTopicRowDisplays",
  components: { MqttTopicRowDisplays },
  computed: {
    style() {
      return {
          margin: this.marginLeft + "px 0px 0px 0px"
        };
    }
  },
  props: {
    mqttTopic: {
      required: true
    },
    marginLeft: {
      required: true
    }
  },
  mounted() {
    console.log("topic");
    console.log(JSON.stringify(this.mqttTopic.children))
  },
  data: function() {
  
  },
  methods: {
    
  }

  
};
</script>


<style scoped>
.mqtt-topic-row-display {
  margin-left: 5px;
}
</style>