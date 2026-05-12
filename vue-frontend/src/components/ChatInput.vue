<template>
  <div class="input-area">
    <textarea
      v-model="text"
      :disabled="disabled"
      placeholder="输入消息... (Enter 发送, Shift+Enter 换行)"
      rows="2"
      @keydown="handleKeydown"
      @input="autoResize"
      ref="textareaRef"
    ></textarea>
    <button :disabled="disabled || !text.trim()" @click="emitSend">发送</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({ disabled: Boolean })
const emit = defineEmits(['send'])

const text = ref('')
const textareaRef = ref(null)

function handleKeydown(e) {
  if (e.key === 'Enter' && !e.shiftKey) {
    e.preventDefault()
    emitSend()
  }
}

function autoResize() {
  const el = textareaRef.value
  if (el) {
    el.style.height = 'auto'
    el.style.height = Math.min(el.scrollHeight, 120) + 'px'
  }
}

function emitSend() {
  const val = text.value.trim()
  if (!val) return
  emit('send', val)
  text.value = ''
  if (textareaRef.value) {
    textareaRef.value.style.height = 'auto'
  }
}

function focus() {
  textareaRef.value?.focus()
}

defineExpose({ focus })
</script>

<style scoped>
.input-area {
  border-top: 1px solid #334155;
  padding: 16px 20px;
  display: flex;
  gap: 12px;
}
textarea {
  flex: 1;
  background: #0f172a;
  border: 1px solid #475569;
  border-radius: 12px;
  color: #e2e8f0;
  font-size: 14px;
  font-family: inherit;
  padding: 14px 16px;
  outline: none;
  resize: none;
  min-height: 56px;
  max-height: 120px;
  transition: border-color 0.2s;
}
textarea:focus {
  border-color: #2563eb;
}
button {
  background: #2563eb;
  border: none;
  border-radius: 12px;
  color: #fff;
  cursor: pointer;
  font-size: 14px;
  font-weight: 600;
  padding: 0 24px;
  white-space: nowrap;
  transition: opacity 0.2s;
}
button:hover:not(:disabled) {
  opacity: 0.9;
}
button:disabled {
  opacity: 0.45;
  cursor: not-allowed;
}
</style>
