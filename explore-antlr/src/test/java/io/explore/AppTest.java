package io.explore;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import io.antlr.hello.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
@Log4j2
public class AppTest {

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testApp()
    {
        log.info("Rigourous Test :-)");
        HelloBaseListener helloBaseListener = new HelloBaseListener();

        assertNotNull( helloBaseListener );
    }
}
