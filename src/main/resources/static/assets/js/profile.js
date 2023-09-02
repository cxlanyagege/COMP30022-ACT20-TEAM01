window.onload = async function() {
    const response = await fetch('/api/getRequests', {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
    });

    // Receive response
    const data = await response.json();
    console.log('Received data:', data);

    // Get tbody from table
    const tableBody = document.getElementById("requestTableBody");

    // Get the blank page image div
    const blankPageImageDiv = document.querySelector("div[style='display: grid; place-items: center; margin-top: 37px;']");

    // If no data, show the blank page image
    if (data.length === 0) {
        blankPageImageDiv.style.display = "grid";
    } 
    
    // If there is data, hide the image instead
    else {

        blankPageImageDiv.style.display = "none";

        // Initialize requestId counter
        let requestId = 1;

        // Traverse each requests
        for (const request of data) {
        
            // Create a new td in dashboard
            const newRow = document.createElement("tr");
            newRow.innerHTML = `
                <td>${requestId}</td>
                <td>${request.subjectCode || ''}</td>
                <td>${request.description || ''}</td>
                <td>${request.taskType || ''}</td>
                <td>${request.status || ''}</td>
                <td>${request.dueDate || ''}</td>
            `;
            requestId++;

            // Add new rows
            tableBody.appendChild(newRow);
        }
    }
};
