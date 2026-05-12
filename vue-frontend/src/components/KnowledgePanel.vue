<template>
  <div class="knowledge-panel" :class="{ open: isOpen }">
    <div class="header">
      <span>知识库</span>
      <button class="close-btn" @click="$emit('toggle')">&times;</button>
    </div>

    <div class="add-form">
      <input v-model="title" placeholder="文档标题" />
      <textarea v-model="content" placeholder="文档内容..." rows="4"></textarea>
      <button class="add-btn" @click="handleAdd" :disabled="!title || !content">添加到知识库</button>
    </div>

    <div class="docs">
      <div v-for="doc in documents" :key="doc.id" class="doc-item" @click="handleDelete(doc.id)">
        <h4>{{ doc.title }}</h4>
        <p>{{ doc.content?.substring(0, 100) }}...</p>
      </div>
      <div v-if="documents.length === 0" class="empty">暂无知识文档</div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({
  isOpen: Boolean,
  documents: { type: Array, default: () => [] }
})
const emit = defineEmits(['toggle', 'add', 'delete'])

const title = ref('')
const content = ref('')

function handleAdd() {
  emit('add', title.value.trim(), content.value.trim())
  title.value = ''
  content.value = ''
}

function handleDelete(id) {
  if (confirm('确定删除该文档？')) {
    emit('delete', id)
  }
}
</script>

<style scoped>
.knowledge-panel {
  width: 0;
  background: #1a2332;
  border-left: 1px solid #334155;
  overflow: hidden;
  transition: width 0.25s ease;
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
}
.knowledge-panel.open {
  width: 320px;
}
.header {
  padding: 14px 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #334155;
  font-size: 14px;
  font-weight: 600;
  color: #f1f5f9;
  white-space: nowrap;
}
.close-btn {
  background: none;
  border: none;
  color: #94a3b8;
  cursor: pointer;
  font-size: 20px;
  line-height: 1;
}
.add-form {
  padding: 12px 14px;
  display: flex;
  flex-direction: column;
  gap: 8px;
  border-bottom: 1px solid #334155;
}
.add-form input,
.add-form textarea {
  background: #0f172a;
  border: 1px solid #475569;
  border-radius: 8px;
  color: #e2e8f0;
  font-size: 13px;
  font-family: inherit;
  padding: 10px 12px;
  outline: none;
}
.add-form input:focus,
.add-form textarea:focus {
  border-color: #2563eb;
}
.add-form textarea {
  min-height: 80px;
  resize: vertical;
}
.add-btn {
  background: #2563eb;
  border: none;
  border-radius: 8px;
  color: #fff;
  cursor: pointer;
  font-size: 13px;
  padding: 8px;
  white-space: nowrap;
}
.add-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
.docs {
  flex: 1;
  overflow-y: auto;
  padding: 8px;
}
.doc-item {
  padding: 12px 14px;
  border-radius: 8px;
  cursor: pointer;
  margin-bottom: 4px;
  transition: background 0.15s;
  white-space: nowrap;
}
.doc-item:hover { background: #1e293b; }
.doc-item h4 {
  font-size: 13px;
  color: #e2e8f0;
  overflow: hidden;
  text-overflow: ellipsis;
}
.doc-item p {
  font-size: 12px;
  color: #64748b;
  margin-top: 4px;
  overflow: hidden;
  text-overflow: ellipsis;
}
.empty {
  text-align: center;
  color: #64748b;
  font-size: 13px;
  padding: 24px 0;
}
</style>
