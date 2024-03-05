package main.java;
public class User {

    private String name, password;

    private int grade, exercisesCompleted;
    public User(String name, String password, int grade){
        this.name = name;
        this.password = password;
        this.grade = grade;
        exercisesCompleted = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getExercisesCompleted() {
        return exercisesCompleted;
    }

    public void setExercisesCompleted(int exercisesCompleted) {
        this.exercisesCompleted = exercisesCompleted;
    }
}
