<template>
  <div class="session-list">
    <div class="header">
      <span>会话列表</span>
      <button class="new-btn" @click="$emit('newSession')">+ 新建</button>
    </div>
    <div class="sessions">
      <div
        v-for="s in sessions"
        :key="s.id"
        class="item"
        :class="{ active: s.id === activeId }"
        @click="$emit('select', s.id)"
      >
        <span class="title">{{ s.title || '新会话' }}</span>
        <span class="time">{{ s.updateTime || s.createTime || '' }}</span>
      </div>
      <div v-if="sessions.length === 0" class="empty">暂无会话</div>
    </div>
  </div>
</template>

<script setup>
defineProps({
  sessions: { type: Array, default: () => [] },
  activeId: { type: Number, default: null }
})
defineEmits(['newSession', 'select'])
</script>

<style scoped>
.session-list {
  width: 240px;
  background: #1a2332;
  border-right: 1px solid #334155;
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
}
.header {
  padding: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #334155;
  font-size: 14px;
  font-weight: 600;
  color: #f1f5f9;
}
.new-btn {
  background: #2563eb;
  border: none;
  border-radius: 6px;
  color: #fff;
  cursor: pointer;
  font-size: 12px;
  padding: 5px 10px;
}
.sessions {
  flex: 1;
  overflow-y: auto;
  padding: 8px;
}
.item {
  padding: 10px 12px;
  border-radius: 8px;
  cursor: pointer;
  margin-bottom: 4px;
  transition: background 0.15s;
}
.item:hover { background: #1e293b; }
.item.active { background: #1e3a5f; }
.title {
  display: block;
  font-size: 13px;
  color: #e2e8f0;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.time {
  display: block;
  font-size: 11px;
  color: #64748b;
  margin-top: 2px;
}
.empty {
  text-align: center;
  color: #64748b;
  font-size: 13px;
  padding: 24px 0;
}
</style>
