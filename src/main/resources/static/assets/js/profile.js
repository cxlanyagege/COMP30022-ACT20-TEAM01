window.onload = async function() {
    const response = await fetch('/api/getRequests', {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });
    const data = await response.json();

    // Get tbody from table
    const tableBody = document.getElementById("requestTableBody");

    // Traverse each requests
    for (const request of data) {
        
        // Create a new td in dashboard
        const newRow = document.createElement("tr");
        newRow.innerHTML = `
            <td>${request.id}</td>
            <td>${request.subjectCode}</td>
            <td>${request.taskName}</td>
            <td>${request.taskType}</td>
            <td>${request.status}</td>
            <td>${request.dueDate}</td>
        `;

        // Add new rows
        tableBody.appendChild(newRow);
    }
};
