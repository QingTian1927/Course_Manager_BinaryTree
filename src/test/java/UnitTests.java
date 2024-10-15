import org.example.model.binaryTree.CommonTreeInterface;
import org.example.model.types.Course;
import org.example.model.types.CourseTree;

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
}
