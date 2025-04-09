// Event data structure
const events = {
    Cricket: { 
        name: "Cricket",
        genderConstraint: "Male",
        participants: [],
    },
    Badminton: { 
        name: "Badminton",
        genderConstraint: "Female",
        participants: [],
    },
    Kabaddi: { 
        name: "Kabaddi",
        genderConstraint: "Both",
        participants: [],
    },
    Cycling: { 
        name: "Cycling",
        genderConstraint: "Both",
        participants: [],
    }
};

// DOM Elements
const participantForm = document.getElementById('participantForm');
const nameInput = document.getElementById('name');
const genderSelect = document.getElementById('gender');
const scoreInput = document.getElementById('score');
const eventSelect = document.getElementById('event');
const viewEventSelect = document.getElementById('viewEvent');
const viewEventBtn = document.getElementById('viewEventBtn');
const notification = document.getElementById('notification');

// Initialize event listeners
document.addEventListener('DOMContentLoaded', () => {
    participantForm.addEventListener('submit', handleFormSubmit);
    viewEventBtn.addEventListener('click', viewEventDetails);
});

// Handler for form submission
function handleFormSubmit(e) {
    e.preventDefault();
    
    // Get form values
    const name = nameInput.value.trim();
    const gender = genderSelect.value;
    const score = parseInt(scoreInput.value);
    const eventName = eventSelect.value;
    
    // Form validation
    if (!name) {
        showNotification('Please enter a name', true);
        return;
    }
    
    if (isNaN(score) || score < 0) {
        showNotification('Please enter a valid score', true);
        return;
    }
    
    // Create new participant
    const participant = {
        name,
        gender,
        score
    };
    
    // Check gender constraint
    const event = events[eventName];
    if (event.genderConstraint !== 'Both' && event.genderConstraint !== gender) {
        showNotification(`Gender constraint violation for ${eventName}. Participant not added.`, true);
        return;
    }
    
    // Add participant to event
    event.participants.push(participant);
    
    // Show success notification
    showNotification(`${name} added to ${eventName} successfully!`);
    
    // Reset form fields
    nameInput.value = '';
    scoreInput.value = '';
    nameInput.focus();
}

// View event details
function viewEventDetails() {
    const eventName = viewEventSelect.value;
    const event = events[eventName];
    
    // Update event title
    document.getElementById('eventTitle').textContent = `${eventName} Event Details`;
    
    // Update gender constraint
    document.getElementById('genderConstraint').textContent = event.genderConstraint;
    
    // Count participants
    const totalParticipants = event.participants.length;
    document.getElementById('totalParticipants').textContent = totalParticipants;
    
    // Count by gender
    const maleCount = event.participants.filter(p => p.gender === 'Male').length;
    const femaleCount = event.participants.filter(p => p.gender === 'Female').length;
    
    document.getElementById('maleCount').textContent = maleCount;
    document.getElementById('femaleCount').textContent = femaleCount;
    
    // Calculate average score
    let averageScore = 0;
    if (totalParticipants > 0) {
        const totalScore = event.participants.reduce((sum, p) => sum + p.score, 0);
        averageScore = (totalScore / totalParticipants).toFixed(2);
    }
    document.getElementById('averageScore').textContent = averageScore;
    
    // Display participants in table
    const participantsList = document.getElementById('participantsList');
    participantsList.innerHTML = '';
    
    if (totalParticipants === 0) {
        const row = document.createElement('tr');
        row.innerHTML = `<td colspan="4" style="text-align: center;">No participants yet</td>`;
        participantsList.appendChild(row);
    } else {
        event.participants.forEach((participant, index) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${index + 1}</td>
                <td>${participant.name}</td>
                <td>${participant.gender}</td>
                <td>${participant.score}</td>
            `;
            participantsList.appendChild(row);
        });
    }
}

// Show notification
function showNotification(message, isError = false) {
    notification.textContent = message;
    notification.className = isError ? 'notification error show' : 'notification show';
    
    // Hide after 3 seconds
    setTimeout(() => {
        notification.className = 'notification';
    }, 3000);
}

// Export to window for debugging
window.events = events; 