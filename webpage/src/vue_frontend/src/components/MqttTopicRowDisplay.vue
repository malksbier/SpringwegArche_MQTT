<template>
  <div class="mqtt-topic-row-display">
    <v-row>
      <!-- Front -->
      <v-icon style="height: 25px" class="black-icon" >fas fa-long-arrow-alt-right</v-icon>
      <p class="padding-in-row"> {{mqttTopic.topicName}} </p>
      <!-- Toggle Buttons -->
      <div v-if="mqttTopic.children.length > 0" >
        <v-button icon  v-ripple="false" @click="toggleChildrenDisplay();" v-if="!displayChildren">
          <v-icon class="toggle-switch black-icon padding-in-row">fas fa-caret-up</v-icon>
        </v-button>

        <v-button icon v-ripple="false" @click="toggleChildrenDisplay();" v-if="displayChildren"> 
          <v-icon class="toggle-switch black-icon padding-in-row">fas fa-caret-down</v-icon> 
        </v-button>
      </div>
      <!-- Functions -->
      <div v-if="mqttTopic.info != null" class="padding-in-row-big"> 
        <div v-if="mqttTopic.info == 'ON/OFF' "> 

            <v-btn @click="sendMqttTopicStatus('ON')" rounded class="function-button"> 
                <v-icon small style="color: rgb(0,255,0)" >fas fa-circle</v-icon>
                <span class="padding-in-row">{{ $t("on") }}</span>
            </v-btn>
            <v-btn @click="sendMqttTopicStatus('OFF')" rounded class="function-button padding-in-row"> 
                <v-icon small style="color: rgb(255,0,0)" >far fa-circle</v-icon>
                <span class="padding-in-row">{{ $t("off") }}</span>
            </v-btn>
        
          
          
        </div>
      </div>
      
    </v-row>
    
    <div v-if="displayChildren">
      <div v-for="mqttTop in mqttTopic.children" :key = mqttTop.id>
        <MqttTopicRowDisplays :mqttTopic = mqttTop></MqttTopicRowDisplays>
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
  },
  props: {
    mqttTopic: {
      required: true
    },
  },

  data() {
    return {
      displayChildren: true,
      apiIp: "http://localhost:8080",
      request: {
        id:0,
        status:0,
      },
    }
  },
  methods: {
    toggleChildrenDisplay() {
      this.displayChildren = !this.displayChildren;
    },
    sendMqttTopicStatus(status) {
      console.log("sending MQTT data: topic: " + this.mqttTopic.topicName + ", status: " + status);

      var _this = this;

      _this.request.id = _this.mqttTopic.id;
      _this.request.status = status;

      this.$axios.post(_this.apiIp + '/mqtt/postTopicStatus', _this.request).then(function (response) {
        if(response.status == 200) {
          console.log("send");
        }
        }).catch(function (error) {
          console.log(error.response.data);
        });
    }
  }

  
};
</script>


<style scoped>
.mqtt-topic-row-display {
  margin-left: 25px;
}
.toggle-switch {
  bottom: 2px;
}
.padding-in-row {
  margin-left: 5px;
}
.padding-in-row-big {
  margin-left: 15px;
}
.black-icon {
  color: rgba(0,0,0,.95);
}
.function-button {
  height: auto !important;
  padding: 0px !important;
}

</style>