const tabLogin = document.querySelector('#tab-login');
const tabRegister = document.querySelector('#tab-login').nextElementSibling;
const form = document.querySelector('#form');
const judul = document.querySelector('#judul-form');

tabLogin.addEventListener('click', function () {
    form.innerHTML = `
        <input class="form-control shadow-warning mx-auto d-block" type="text" autocomplete="off" name="username" placeholder="Username" required><br>
        <input class="form-control shadow-warning mx-auto d-block" type="password" autocomplete="off" name="password" placeholder="Password" required><br>
        <button class="btn btn-warning" name="login">Login</button>
    `;
    judul.textContent = 'LOGIN';
    if (!tabLogin.classList.contains('btn-warning')) {
        tabLogin.classList.replace('btn-outline-warning', 'btn-warning');
        tabRegister.classList.replace('btn-warning', 'btn-outline-warning');
    }
});

tabRegister.addEventListener('click', function() {
    form.innerHTML = `
        <input class="form-control shadow-warning mx-auto d-block" type="text" autocomplete="off" name="username" placeholder="Username" required><br>
        <input class="form-control shadow-warning mx-auto d-block" type="password" autocomplete="off" name="password" placeholder="Password" required><br>
        <input class="form-control shadow-warning mx-auto d-block" type="password" autocomplete="off" name="konfirmasi-password" placeholder="Konfirmasi Password" required><br>
        <button class="btn btn-warning" name="register">Register</button>
    `;
    });