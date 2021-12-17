<template>
  <div class="mqtt root-page-element">
    <h1>mqtt</h1>
    <div v-for="mqttTopic in mqttTopics.topics" :key = mqttTopic.id>
        <MqttTopicRowDisplay :mqttTopic = mqttTopic :marginLeft = 0></MqttTopicRowDisplay>
    </div>
  </div>
</template>

<script>
import MqttTopicRowDisplay from '../components/MqttTopicRowDisplay.vue'
    export default {
        components: { MqttTopicRowDisplay },
        name: 'MQTT',

        data: () => ({
            mqttTopics : [],
            apiIp: "http://localhost:8080",
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
