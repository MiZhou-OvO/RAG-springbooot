const BASE = '/api'

export async function createSession() {
  const res = await fetch(`${BASE}/ai/session`, { method: 'POST' })
  return res.json()
}

export async function sendMessage(message, sessionId) {
  const params = new URLSearchParams({ message })
  if (sessionId) params.set('sessionId', sessionId)
  const res = await fetch(`${BASE}/ai/generate?${params}`)
  return res.text()
}

export async function getKnowledgeList() {
  const res = await fetch(`${BASE}/knowledge/list`)
  return res.json()
}

export async function addKnowledge(title, content, category) {
  const res = await fetch(`${BASE}/knowledge`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title, content, category })
  })
  return res.json()
}

export async function deleteKnowledge(id) {
  const res = await fetch(`${BASE}/knowledge/${id}`, { method: 'DELETE' })
  return res.json()
}
