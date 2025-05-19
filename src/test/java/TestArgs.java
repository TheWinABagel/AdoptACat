import dev.bagel.adopt.arg.SortParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class TestArgs {
    private static final String ADDRESS = "http://localhost:3000/cats";
    @Test
    public void testArgs() {
        String[] args = new String[]{ "-c", "gray", "--age>=", "-n:contains=", "iam" };
        SortParser parser = new SortParser(ADDRESS, args);
        parser.getParser().saveToFile();
    }

    @Test
    public void testNoArg() {
       new SortParser(ADDRESS, new String[]{}).getParser();
       fail();
    }

    @Test
    public void testFail() {
        String[] args = new String[]{"--invalid", "not allowed"};
        SortParser parser = new SortParser(ADDRESS, args);
        assertThrows(IllegalArgumentException.class, () -> parser.getParser().saveToFile());
    }
}
