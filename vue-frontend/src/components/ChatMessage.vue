<template>
  <div class="message" :class="role">
    <div class="avatar">{{ role === 'user' ? 'U' : 'AI' }}</div>
    <div class="bubble" v-html="rendered"></div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { marked } from 'marked'

const props = defineProps({
  role: { type: String, required: true },
  content: { type: String, required: true }
})

const rendered = computed(() => marked.parse(props.content))
</script>

<style scoped>
.message {
  display: flex;
  gap: 12px;
  animation: fadeIn 0.3s ease;
  padding: 4px 0;
}
.message.user {
  flex-direction: row-reverse;
}
@keyframes fadeIn {
  from { opacity: 0; transform: translateY(8px); }
  to { opacity: 1; transform: translateY(0); }
}
.avatar {
  width: 36px;
  height: 36px;
  border-radius: 10px;
  background: #334155;
  color: #cbd5e1;
  font-size: 13px;
  font-weight: 700;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}
.message.user .avatar {
  background: #2563eb;
  color: #fff;
}
.bubble {
  background: #1e293b;
  border-radius: 12px;
  padding: 14px 18px;
  max-width: 72%;
  line-height: 1.7;
  font-size: 14px;
}
.message.user .bubble {
  background: #2563eb;
}
.bubble :deep(pre) {
  background: #0f172a;
  border-radius: 8px;
  padding: 12px;
  overflow-x: auto;
  margin: 8px 0;
  font-size: 13px;
}
.bubble :deep(code) {
  background: #0f172a;
  border-radius: 4px;
  padding: 2px 6px;
  font-size: 13px;
}
.bubble :deep(pre code) {
  background: none;
  padding: 0;
}
</style>
