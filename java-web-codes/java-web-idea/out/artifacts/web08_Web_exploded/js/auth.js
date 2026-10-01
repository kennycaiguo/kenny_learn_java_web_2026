const AUTH_KEY = 'dept_admin_auth';
if (localStorage.getItem(AUTH_KEY) === 'true') {
    location.href = 'list.html';
}
document.getElementById('loginForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const u = document.getElementById('username').value.trim(), p = document.getElementById('password').value,
        remember = document.getElementById('remember').checked, error = document.getElementById('loginError');
    if (u === 'admin' && p === '123456') {
        if (remember) localStorage.setItem(AUTH_KEY, 'true'); else sessionStorage.setItem(AUTH_KEY, 'true');
        location.href = 'list.html';
    } else error.textContent = '用户名或密码错误，请使用：admin / 123456';
});
