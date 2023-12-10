package MidTerm18;

public class Main {
    public static void main(String[] args) {
        System.out.println("The CIS 1500 Class Exam");
        Exam exam = new Exam("Intro To Programming", "Fall 2023", "Professor Charnesky", 100);
        exam.setScore(90);
        System.out.println("Class: " + exam.getClassName());
        System.out.println("Semester: " + exam.getSemester());
        System.out.println("Professor: " + exam.getProfessor());
        System.out.println("Max Score: " + exam.getMaxScore());
        System.out.println("Score: " + exam.getScore());

        // Try to set an invalid score
        exam.setScore(110);
    }
}

class Exam {
    private String className;
    private String semester;
    private String professor;
    private int score;
    private int maxScore;

    public Exam(String className, String semester, String professor, int maxScore) {
        this.className = className;
        this.semester = semester;
        this.professor = professor;
        this.maxScore = maxScore;
        this.score = 0; // Initialize score to 0
    }

    // Getters for all attributes
    public String getClassName() {
        return className;
    }

    public String getSemester() {
        return semester;
    }

    public String getProfessor() {
        return professor;
    }

    public int getScore() {
        return score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    // Setter for the score, with bounds checking
    public void setScore(int score) {
        if (score >= 0 && score <= maxScore) {
            this.score = score;
        } else {
            System.out.println("Invalid score. Score must be between 0 and " + maxScore);
        }
    }
}