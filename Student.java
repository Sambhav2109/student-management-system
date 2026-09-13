public class Student {
    private final int rollNumber;
    private final String name;
    private final double javaMarks;
    private final double dsaMarks;
    private final double mathsMarks;

    public Student(int rollNumber, String name, double javaMarks, double dsaMarks, double mathsMarks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.javaMarks = javaMarks;
        this.dsaMarks = dsaMarks;
        this.mathsMarks = mathsMarks;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return (javaMarks + dsaMarks + mathsMarks) / 3.0;
    }

    public String getGrade() {
        double average = getAverage();
        if (average >= 90) return "A+";
        if (average >= 80) return "A";
        if (average >= 70) return "B";
        if (average >= 60) return "C";
        if (average >= 50) return "D";
        return "F";
    }

    public String toFileFormat() {
        return rollNumber + "," + name.replace(",", " ") + "," + javaMarks + "," + dsaMarks + "," + mathsMarks;
    }

    @Override
    public String toString() {
        return String.format("Roll No: %d | Name: %s | Java: %.1f | DSA: %.1f | Maths: %.1f | Average: %.2f | Grade: %s",
                rollNumber, name, javaMarks, dsaMarks, mathsMarks, getAverage(), getGrade());
    }
}
