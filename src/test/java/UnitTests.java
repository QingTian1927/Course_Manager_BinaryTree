import org.example.model.binaryTree.CommonTreeInterface;
import org.example.model.types.Course;
import org.example.model.types.CourseTree;
import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;;;

public final class UnitTests {
    private UnitTests() {}

    private static <T> void insertMany(CommonTreeInterface<T> tree, T[] values) {
        if (tree == null) {
            return;
        }
        for (T value : values) {
            tree.insert(value);
        }
    }

    public void testCommonTree() {
        CommonTreeInterface<Course> courseTree = new CourseTree();

    }

    public static Course[] getCourseData() {
        return new Course[] {
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

    @Test
    public void testLoadDataFromFileAndCountCourse(File file) {
        CourseTree manager = new CourseTree();
        manager.load(file);
        assertNotNull(manager.getRoot());
        assertEquals(13, manager.getCourseCount());
    }

    @Test
    public void testAddToTree() {
        CourseTree manager = new CourseTree();
        Course course = new Course("C015", "CSI101", "Introduction to Programming", "Fall", "2024", 30, 1, 1500);
        Course[] courseList = this.getCourseData();
        for (Course course2 : courseList) {
            manager.insert(course2);
        }
        assertEquals(13, manager.getCourseCount());
        manager.insert(course);
        assertEquals(14, manager.getCourseCount());
        manager.insert(course);
        assertEquals(14, manager.getCourseCount());
    }

    @Test
    public void testPreOrderTraversal() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        manager.preOrder(manager.getRoot());
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
    }

    @Test
    public void testPostOrder() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        manager.postOrder(manager.getRoot());
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
    }

    @Test
    public void testSearchByCcode() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        Course course = manager.searchByCode("C005").data;
        assertNotNull(course);
        assertEquals("C005", course.getCcode());
    }

    @Test
    public void testDeleteByCcodeCopyingWithLeaf() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals(0, manager.searchByCode("C011").data.getRegistered());
        manager.deleteByCopying(manager.searchByCode("C011").data);
        Course course = manager.searchByCode("C011").data;
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
        assertNull(course);
    }

    @Test
    public void testDeleteByCcodeCopyingWithNode1Child() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals(0, manager.searchByCode("C012").data.getRegistered());
        manager.deleteByCopying(manager.searchByCode("C012").data);
        Course course = manager.searchByCode("C012").data;
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
        assertNull(course);
    }

    @Test
    public void testDeleteByCcodeCopyingWithNode2Child() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals(0, manager.searchByCode("C003").data.getRegistered());
        manager.deleteByCopying(manager.searchByCode("C003").data);
        Course course = manager.searchByCode("C003").data;
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
        assertNull(course);
    }


    @Test
    public void testDeleteByCcodeMergingWithLeaf() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals(0, manager.searchByCode("C003").data.getRegistered());
        manager.deleteByMerging(manager.searchByCode("C003").data);
        Course course = manager.searchByCode("C003").data;
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
        assertNull(course);
    }

    @Test
    public void testDeleteByCcodeMergingWithNode1Child() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals(0, manager.searchByCode("C003").data.getRegistered());
        manager.deleteByMerging(manager.searchByCode("C003").data);
        Course course = manager.searchByCode("C003").data;
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
        assertNull(course);
    }

    @Test
    public void testDeleteByCcodeMergingWithNode2Child() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertEquals(0, manager.searchByCode("C003").data.getRegistered());
        manager.deleteByMerging(manager.searchByCode("C003").data);
        Course course = manager.searchByCode("C003").data;
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
        assertNull(course);
    }

    @Test
    public void testBalanceTree() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        manager.balance();
        assertTrue(manager.isBalanced());
    }

    @Test
    public void testBreadthFirstTraversal() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        manager.breadth();
        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
    }

    @Test
    public void testSearchByName() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        CourseTree courses = manager.searchByName("Artificial Intelligence");
        assertFalse(courses.isEmpty());
        assertEquals("Artificial Intelligence", courses.getRoot().data.getSname());
    }

    @Test
    public void testSearchByCcodeAndListStudents() {
        CourseTree manager = new CourseTree();
        Course[] courseList = this.getCourseData();
        for (Course course : courseList) {
            manager.insert(course);
        }
        Course course = manager.searchByCode("C001").data;
        assertNotNull(course);
        assertEquals("C001", course.getCcode());
    }

}
