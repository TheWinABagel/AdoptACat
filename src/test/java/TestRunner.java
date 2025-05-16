import dev.bagel.adopt.util.ArgChecker;
import dev.bagel.adopt.util.ArgParser;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;
public class TestRunner {

    @Test
    public void testValidAge() {
        ArgParser parser = new ArgParser(new String[]{"-age", "15"});
        assertDoesNotThrow(() -> ArgChecker.checkAge(parser.get("age")));
    }

    @Test
    public void testNegativeAge() {
        ArgParser parser = new ArgParser(new String[]{"-age", "-37"});
        assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkAge(parser.get("age")));
    }

    @Test
    public void testInvalidAge() {
        ArgParser parser = new ArgParser(new String[]{"-age", "Older than time immemorial"});
        assertThrows(IllegalArgumentException.class, () -> ArgChecker.checkAge(parser.get("age")));
    }
}
