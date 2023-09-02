function login() {
    const username = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('/api/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username: username, password: password }),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        if (data.message === "Login Successfully") {
            alert(data.message);
            localStorage.setItem("username", username);
            window.location.href = 'profile.html';  // Go to user profile page
        } else {
            alert(data.message);
        }
    })
    .catch((error) => {
        console.error('There was a problem with the fetch operation:', error);
    });
}
