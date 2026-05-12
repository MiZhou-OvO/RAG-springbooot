<template>
  <div class="chat-view">
    <SessionList
      :sessions="sessions"
      :activeId="activeSessionId"
      @newSession="handleNewSession"
      @select="handleSelectSession"
    />

    <div class="main-area">
      <div class="top-bar">
        <h1>AI Agent Chat</h1>
        <span class="session-info">会话 #{{ activeSessionId || '-' }}</span>
        <button class="kb-btn" @click="showKnowledge = !showKnowledge">知识库</button>
      </div>

      <div class="messages" ref="msgContainer">
        <div v-if="messages.length === 0" class="empty-hint">开始一个新对话吧</div>
        <template v-for="(msg, i) in messages" :key="i">
          <ChatMessage :role="msg.role" :content="msg.content" />
        </template>
        <div v-if="loading" class="typing">
          <div class="avatar">AI</div>
          <div class="bubble">
            <span class="dot"></span><span class="dot"></span><span class="dot"></span>
          </div>
        </div>
      </div>

      <ChatInput ref="inputRef" :disabled="loading" @send="handleSend" />
    </div>

    <KnowledgePanel
      :isOpen="showKnowledge"
      :documents="knowledgeDocs"
      @toggle="showKnowledge = false"
      @add="handleAddKnowledge"
      @delete="handleDeleteKnowledge"
    />
  </div>
</template>

<script setup>
import { ref, nextTick, onMounted } from 'vue'
import ChatMessage from '../components/ChatMessage.vue'
import ChatInput from '../components/ChatInput.vue'
import SessionList from '../components/SessionList.vue'
import KnowledgePanel from '../components/KnowledgePanel.vue'
import { createSession, sendMessage, getKnowledgeList, addKnowledge, deleteKnowledge } from '../api/index.js'

const sessions = ref([])
const activeSessionId = ref(null)
const messages = ref([])
const loading = ref(false)
const showKnowledge = ref(false)
const knowledgeDocs = ref([])
const inputRef = ref(null)
const msgContainer = ref(null)

function scrollBottom() {
  nextTick(() => {
    if (msgContainer.value) {
      msgContainer.value.scrollTop = msgContainer.value.scrollHeight
    }
  })
}

async function handleNewSession() {
  const res = await createSession()
  if (res.code === 1) {
    sessions.value.unshift({ id: res.data.sessionId, title: res.data.title })
    activeSessionId.value = res.data.sessionId
    messages.value = []
  }
}

function handleSelectSession(id) {
  activeSessionId.value = id
  messages.value = []
}

async function handleSend(text) {
  if (!activeSessionId.value) {
    await handleNewSession()
    if (!activeSessionId.value) return
  }
  messages.value.push({ role: 'user', content: text })
  scrollBottom()

  loading.value = true
  const response = await sendMessage(text, activeSessionId.value)
  messages.value.push({ role: 'assistant', content: response })
  loading.value = false
  scrollBottom()
  inputRef.value?.focus()
}

async function loadKnowledge() {
  const res = await getKnowledgeList()
  if (res.code === 1) {
    knowledgeDocs.value = res.data || []
  }
}

async function handleAddKnowledge(title, content) {
  await addKnowledge(title, content, 'general')
  await loadKnowledge()
}

async function handleDeleteKnowledge(id) {
  await deleteKnowledge(id)
  await loadKnowledge()
}

onMounted(() => {
  handleNewSession()
})
</script>

<style scoped>
.chat-view {
  display: flex;
  height: 100vh;
  overflow: hidden;
}
.main-area {
  flex: 1;
  display: flex;
  flex-direction: column;
  min-width: 0;
}
.top-bar {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 16px 20px;
  border-bottom: 1px solid #334155;
  flex-shrink: 0;
}
.top-bar h1 {
  font-size: 18px;
  color: #f1f5f9;
}
.session-info {
  font-size: 13px;
  color: #38bdf8;
  flex: 1;
}
.kb-btn {
  background: #334155;
  border: 1px solid #475569;
  border-radius: 8px;
  color: #cbd5e1;
  cursor: pointer;
  font-size: 13px;
  padding: 7px 14px;
  transition: background 0.2s;
}
.kb-btn:hover {
  background: #475569;
}
.messages {
  flex: 1;
  overflow-y: auto;
  padding: 20px 24px;
  display: flex;
  flex-direction: column;
  gap: 4px;
}
.empty-hint {
  text-align: center;
  color: #64748b;
  font-size: 14px;
  margin-top: 80px;
}
.typing {
  display: flex;
  gap: 12px;
  padding: 4px 0;
}
.typing .avatar {
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
.typing .bubble {
  background: #1e293b;
  border-radius: 12px;
  padding: 14px 18px;
  display: flex;
  align-items: center;
  gap: 4px;
}
.dot {
  width: 8px;
  height: 8px;
  background: #64748b;
  border-radius: 50%;
  animation: blink 1.4s infinite both;
}
.dot:nth-child(2) { animation-delay: 0.2s; }
.dot:nth-child(3) { animation-delay: 0.4s; }
@keyframes blink {
  0%, 80%, 100% { opacity: 0.2; }
  40% { opacity: 1; }
}
</style>
