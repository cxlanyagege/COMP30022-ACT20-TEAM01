window.onload = function() {
    const username = localStorage.getItem("username");
    if (username) {
        document.getElementById("usernameDisplay").innerText = username;
    } else {
        // Return to login page if there no user is login
        window.location.href = 'login.html';
    }

    document.addEventListener("DOMContentLoaded", function() {
        var userId = localStorage.getItem('userId');

        fetch('/api/getSubjectsForUser?userId=' + userId)
            .then(response => response.json())
            .then(data => {
                let subjectList = document.getElementById('subjectList');

                // Clear the list first
                subjectList.innerHTML = '';

                data.forEach(subject => {
                    let li = document.createElement('li');
                    li.textContent = subject.name;
                    subjectList.appendChild(li);
                });
            })
            .catch(error => console.error('There was an error fetching the subjects:', error));
    });

    document.getElementById("logoutButton").addEventListener("click", function() {
        logoutUser();
    });
}

function logoutUser() {
    // Clear user sessions
    localStorage.removeItem("username");
    // Return to login page
    window.location.href = 'login.html';
}
