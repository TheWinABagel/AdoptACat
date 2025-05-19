import dev.bagel.adopt.arg.SortParser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestArgs {

    @Test
    @Disabled
    public void testArgs() {
        String[] args = new String[]{ "-c", "gray", "-test", "-arg3", "12345" };
//        ArgumentParser parser = new ArgumentParser(args);

/*        assertTrue(parser.contains("c"));
        assertEquals("gray", parser.get("c"));
        assertEquals("gray", parser.getOrDefault("c", () -> "invalid"));

        assertTrue(parser.contains("test"));

        assertTrue(parser.contains("arg3"));
        assertEquals("12345", parser.get("arg3"));

        assertFalse(parser.contains("arg4"));
        assertEquals("15", parser.getOrDefault("arg4", () -> "15"));*/
    }

    @Test
    @Disabled
    public void testNoArg() {
/*        Executable invalid = () -> new ArgParser(new String[]{});
        assertThrows(IllegalArgumentException.class, invalid);*/
    }

    @Test
    public void test() {
        //todo "--age:>= 50"

    }

    @Test
    public void aaaa() {
        String[] args = new String[]{ "-c", "gray", "--age:>=", "5", "-n", "hi"};
        SortParser parser = new SortParser(args,);

    }
}
