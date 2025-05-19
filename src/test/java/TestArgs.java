import dev.bagel.adopt.arg.SortParser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class TestArgs {
    private static final String ADDRESS = "http://localhost:3000/cats";
    @Test
    @Disabled
    public void testArgs() {
        String[] args = new String[]{ "-c", "gray", "-test", "-arg3", "12345" };
        SortParser parser = new SortParser(ADDRESS, args);

    }

    @Test
    public void testNoArg() {
       Executable invalid = () -> new SortParser(ADDRESS, new String[]{}).getParser();
       assertThrows(IllegalArgumentException.class, invalid);
    }

    @Test
    public void test() {
        String[] args = new String[]{};
        SortParser parser = new SortParser("http://localhost:3000/cats", args);

    }

    @Test
    public void aaaa() {
        String[] args = new String[]{ "-c", "gray", "--age:>=", "5", "-n:contains", "will"};
        SortParser parser = new SortParser("http://localhost:3000/cats", args);

    }
}
