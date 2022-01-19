<template>
  <div class="mqtt root-page-element">
    


    <div class="container-fluid"> 
        <div class="row justify-content-start">
            <div class="col-xl-6">
                <h1 class="pb-4">{{ $t("mqtt_heading") }}</h1>

                <div class="main-content"  v-for="mqttTopic in mqttTopics.topics" :key = mqttTopic.id>
                    <MqttTopicRowDisplay :mqttTopic = mqttTopic :parentMqttDirectory = emptyMqttTopicString></MqttTopicRowDisplay>
                    
                </div>
            </div>
            <div class="col-xl-6">
                <h1 class="pb-4">Regeln</h1>

                <div class="main-content " v-for="mqttTopic in mqttTopics.topics" :key = mqttTopic.id>
                    <MqttRuleDisplay></MqttRuleDisplay>
                </div>
            </div>
        </div>
    </div>

    
  </div>
</template>

<script>
import MqttTopicRowDisplay from '../components/MqttTopicRowDisplay.vue'
import MqttRuleDisplay from '../components/MqttRuleDisplay.vue'
    export default {
        components: { MqttTopicRowDisplay, MqttRuleDisplay },
        name: 'MQTT',

        data: () => ({
            mqttTopics : [],
            apiIp: "http://localhost:8080",

            emptyMqttTopicString: "",
        }),

        created() {
            this.getMqttTopics();
        },
        methods: {
            getMqttTopics() {
                var _this = this;

                console.log("getting mqtt topics");
                this.$axios.get(_this.apiIp + '/mqtt/getAll', _this.userPasswordResetInit).then(function (response) {
                    if(response.status == 200) {
                        _this.mqttTopics = response.data;
                        //console.log(JSON.stringify(_this.mqttTopics));
                    }
                }).catch(function (error) {
                    console.log(error)
                });
            },
        }
    }
</script>
