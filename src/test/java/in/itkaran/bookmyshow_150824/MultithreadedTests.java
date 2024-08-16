package in.itkaran.bookmyshow_150824;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MultithreadedTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test1() {
        System.out.println("Test 1");
    }

}
