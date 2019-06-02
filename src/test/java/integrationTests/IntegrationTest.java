package integrationTests;

import com.dpokidov.action.PrintAction;
import com.dpokidov.cmd.Main;
import com.dpokidov.loader.Loader;
import com.dpokidov.loader.LocalFileLoader;
import com.dpokidov.reducer.DuplicateReducer;
import org.junit.Test;

import java.nio.file.Paths;

public class IntegrationTest {
    @Test
    public void test() throws Exception {
        Main.main(new String[]{"./src/test/resources/integrationTests"});
    }
}
