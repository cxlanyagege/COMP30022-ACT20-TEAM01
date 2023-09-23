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
            console.log('Response:', response);
        if (response.status === 401) {
            alert('Incorrect password, or user doesn\'t exist');
            throw new Error('Unauthorized');
        } else {
            return response.json().then(data => {
                alert('Something went wrong：' + data.message);
                throw new Error(data.message || 'Network response was not ok');
            }).catch(() => {
                alert('There is an unknown issue causing login failed');
                throw new Error('Unknown error');
            });
        }
        }
        return response.json();
    })
    .then(data => {
        if (data.jwt) {
            localStorage.setItem("jwt", data.jwt);
            window.location.href = 'profile.html';  // Go to user profile page
        }
    })
    .catch((error) => {
        console.error('There was a problem with the fetch operation:', error);
    });
}
