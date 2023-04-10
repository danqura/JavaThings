package doubles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void checkOne(){
        assertEquals("[]", Main.properStackDeletion("aabb"));
    }
    @Test
    void checkTwo() {
        assertEquals("[a, b, f, b, a, f]", Main.properStackDeletion("abfbaf"));
    }
    @Test
    void checkFive(){
        assertEquals("[д, л, и, о, ш, е]", Main.properStackDeletion("длинношеее"));
    }

}