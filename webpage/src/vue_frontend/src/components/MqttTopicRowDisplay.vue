<template>
  <div class="mqtt-topic-row-display" style = style>
    <v-row>
      <p> {{mqttTopic.topicName}} </p>
      
      <div v-if="mqttTopic.children.length > 0" >
        <v-button icon  v-ripple="false" @click="toggleChildrenDisplay();" v-if="!displayChildren">
          <v-icon class="toggle-switch">fas fa-caret-up</v-icon>
        </v-button>

        <v-button icon v-ripple="false" @click="toggleChildrenDisplay();" v-if="displayChildren"> 
          <v-icon class="toggle-switch">fas fa-caret-down</v-icon> 
        </v-button>
        
      </div>
      
    </v-row>
    
    <div v-if="displayChildren">
      <div v-for="mqttTop in mqttTopic.children" :key = mqttTop.id>
        <MqttTopicRowDisplays :mqttTopic = mqttTop :marginLeft = 20></MqttTopicRowDisplays>
      </div>
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

  data() {
    return {
      displayChildren: true,
    }
  },
  mounted() {
    console.log("topic");
    console.log(JSON.stringify(this.mqttTopic.children))
  },
  methods: {
    toggleChildrenDisplay() {
      this.displayChildren = !this.displayChildren;
    }
  }

  
};
</script>


<style scoped>
.mqtt-topic-row-display {
  margin-left: 5px;
}
.toggle-switch {
  bottom: 2px;
  padding-left: 5px;
  color: rgba(0,0,0,.95);
}

</style>