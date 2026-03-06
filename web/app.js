let currentPage = 1;
const pageSize = 5;

document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const resultEl = document.getElementById('loginResult');

    try {
        const res = await fetch('/api/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });
        const data = await res.json();

        if (data.token) {
            resultEl.textContent = 'Token: ' + data.token;
            resultEl.className = 'result success';
        } else {
            resultEl.textContent = data.error || 'Login failed';
            resultEl.className = 'result error';
        }
    } catch (err) {
        resultEl.textContent = 'Request failed: ' + err.message;
        resultEl.className = 'result error';
    }
});

async function loadUsers(page, size) {
    const listEl = document.getElementById('userList');
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const pageInfo = document.getElementById('pageInfo');

    try {
        const res = await fetch(`/api/users?page=${page}&pageSize=${size}`);
        const users = await res.json();

        listEl.innerHTML = users.length === 0
            ? '<p class="user-card">No users on this page.</p>'
            : users.map(u => `<div class="user-card"><span class="id">#${u.id}</span> ${u.username}</div>`).join('');

        pageInfo.textContent = `Page ${page}`;
        prevBtn.disabled = page <= 1;
        nextBtn.disabled = users.length < pageSize;
        currentPage = page;
    } catch (err) {
        listEl.innerHTML = '<p class="result error">Failed to load users: ' + err.message + '</p>';
    }
}

document.getElementById('prevBtn').addEventListener('click', () => {
    if (currentPage > 1) loadUsers(currentPage - 1, pageSize);
});

document.getElementById('nextBtn').addEventListener('click', () => {
    loadUsers(currentPage + 1, pageSize);
});

loadUsers(1, pageSize);
