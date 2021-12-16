<template>
  <div class="mqtt root-page-element">
    <h1>mqtt</h1>
    <MqttTopicRowDisplay></MqttTopicRowDisplay>
  </div>
</template>

<script>
import MqttTopicRowDisplay from '../components/MqttTopicRowDisplay.vue'


  export default {
  components: { MqttTopicRowDisplay },
    name: 'MQTT',
  }
</script>
