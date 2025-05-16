import dev.bagel.adopt.util.ArgParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class TestArgs {

    @Test
    public void testArgs() {
        String[] args = new String[]{ "-c", "gray", "-test", "-arg3", "12345" };
        ArgParser parser = new ArgParser(args);

        assertTrue(parser.contains("c"));
        assertEquals("gray", parser.get("c"));
        assertEquals("gray", parser.getOrDefault("c", () -> "invalid"));

        assertTrue(parser.contains("test"));

        assertTrue(parser.contains("arg3"));
        assertEquals("12345", parser.get("arg3"));

        assertFalse(parser.contains("arg4"));
        assertEquals("15", parser.getOrDefault("arg4", () -> "15"));
    }

    @Test
    public void testNoArg() {
        Executable invalid = () -> new ArgParser(new String[]{});
        assertThrows(IllegalArgumentException.class, invalid);
    }
}
