import dev.bagel.adopt.ArgParser;
import dev.bagel.adopt.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class TestArgs {

    @Test
    public void testArgs() {
        String[] args = new String[]{ "-ip", "localhost:3000", "-test", "-arg3", "12345" };
        ArgParser parser = new ArgParser(args);

        assertTrue(parser.has("ip"));
        assertEquals("localhost:3000", parser.get("ip"));
        assertEquals("localhost:3000", parser.getOrDefault("ip", () -> "invalid"));

        assertTrue(parser.has("test"));

        assertTrue(parser.has("arg3"));
        assertEquals("12345", parser.get("arg3"));

        assertFalse(parser.has("arg4"));
        assertEquals("blue", parser.getOrDefault("arg4", () -> "blue"));
    }

    @Test
    public void testNoArg() {
        Executable invalid = () -> new ArgParser(new String[]{});
        assertThrows(IllegalArgumentException.class, invalid);
    }
}
