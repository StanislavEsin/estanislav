import ru.job4j.Calculate;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Stanislav (mailto:376825@mail.ru)
* @version $Id$
* @since 0.1
*/
public class CalculateTest {
	/**
	* Test add.
	*/
	@Test
	public void whenAddOneToOneThenTwo() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Calculate.main(null);
		
		assertThat(out.toString(),
			is(String.format("Hello world.%s", System.getProperty("line.separator"))));
	}
}
 