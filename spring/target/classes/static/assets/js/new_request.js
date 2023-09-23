document.addEventListener('DOMContentLoaded', function() {

    const form = document.getElementById('request-form');

        form.addEventListener('submit', function(event) {

            event.preventDefault();

            const description = document.getElementById('description').value;

            const studentIdInput = document.getElementById("studentId");
            const studentId = parseInt(studentIdInput.value);

            const subjectId = document.getElementById("subjectId").value;

            fetch('/api/saveRequest', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('jwt')
                },
                body: JSON.stringify({ description: description, studentId: studentId, subjectId: subjectId})
            })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.message === 'Request saved successfully!') {
                    alert('Request saved successfully!');
                    window.location.href = 'profile.html';
                }
            })
            .catch((error) => {
                console.error('Error:', error);
            });
        });
    }
);