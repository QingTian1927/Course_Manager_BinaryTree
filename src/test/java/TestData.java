import org.example.model.types.Course;

public final class TestData {
    private TestData() {
    }
    
    public static Course[] getCourseData() {
        return new Course[]{
                new Course("C001", "CSI101", "Introduction to Programming", "Fall", "2024", 30, 1, 1500),
                new Course("C002", "CSI102", "Data Structures", "Spring", "2024", 40, 1, 2000),
                new Course("C003", "CSCI103", "Database Management", "Summer", "2024", 30, 1, 1200),
                new Course("C004", "CSCI104", "Web Development", "Winter", "2024", 50, 1, 2500),
                new Course("C005", "CSCI105", "Software Engineering", "Fall", "2024", 30, 1, 1800),
                new Course("C006", "CSCI106", "Operating Systems", "Spring", "2024", 40, 1, 2200),
                new Course("C008", "CSCI108", "Artificial Intelligence", "Winter", "2024", 45, 1, 3000),
                new Course("C009", "CSCI109", "Machine Learning", "Fall", "2024", 30, 1, 2700),
                new Course("C010", "CSCI110", "Cyber Security", "Spring", "2024", 50, 1, 3200),
                new Course("C011", "CSCI111", "Mobile App Development", "Summer", "2024", 20, 1, 1400),
                new Course("C012", "CSCI112", "Cloud Computing", "Winter", "2024", 30, 0, 2500),
                new Course("C013", "DBI104", "Design Database", "Spring", "2024", 20, 0, 3500),
                new Course("C014", "JPD101", "Japanese for Beginner", "Fall", "2024", 30, 0, 2500),
        };
    }
}
