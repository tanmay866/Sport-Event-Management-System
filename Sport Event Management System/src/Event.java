// Event.java
public class Event {
    String eventName;             // Name of the event
    String genderConstraint;      // Gender constraint for the event ("Male", "Female", or "Both")
    Participant[] participants;   // Array of participants
    int participantCount;         // Current number of participants

    // Constructor to initialize an event
    public Event(String eventName, String genderConstraint) {
        this.eventName = eventName;
        this.genderConstraint = genderConstraint;
        this.participants = new Participant[100]; // Fixed size of 100 participants
        this.participantCount = 0; // Initialize participant count
    }

    // Method to add a participant to the event
    public void addParticipant(Participant participant) {
        // Check if the participant meets the gender constraint
        if (this.genderConstraint.equals("Both") || this.genderConstraint.equals(participant.gender)) {
            // Check if there is space to add the participant
            if (participantCount < participants.length) {
                participants[participantCount] = participant; // Add participant to the array
                participantCount++; // Increment the participant count
                System.out.println(participant.name + " added to " + eventName + ".");
            } else {
                // Inform if the event is full
                System.out.println("Event " + eventName + " is full. Participant not added.");
            }
        } else {
            // Inform if there's a gender constraint violation
            System.out.println("Gender constraint violation for " + eventName + ". Participant not added.");
        }
    }
    
    // Method to calculate average score of participants
    public double calculateAverageScore() {
        if (participantCount == 0) {
            return 0; // Avoid division by zero if no participants
        }
        int totalScore = 0; // Total score of all participants
        for (int i = 0; i < participantCount; i++) {
            totalScore += participants[i].score; // Accumulate total score
        }
        return (double) totalScore / participantCount; // Return average score
    }

    // Method to display event details
    public void displayEventDetails() {
        int maleCount = 0;          // Counter for male participants
        int femaleCount = 0;        // Counter for female participants
        int totalScore = 0;         // Total score of all participants

        // Iterate over participants to calculate statistics
        for (int i = 0; i < participantCount; i++) {
            Participant p = participants[i];
            if (p.gender.equals("Male")) {
                maleCount++;         // Increment male count
            } else if (p.gender.equals("Female")) {
                femaleCount++;       // Increment female count
            }
            totalScore += p.score;   // Accumulate total score
        }

        int totalParticipants = participantCount; // Total number of participants
        // Calculate average score, ensuring no division by zero
        double avgScore = totalParticipants == 0 ? 0 : (double) totalScore / totalParticipants;

        // Display event statistics
        System.out.println(eventName + " - Male: " + maleCount + ", Female: " + femaleCount);
        System.out.println("Average Score: " + avgScore);
    }
}
