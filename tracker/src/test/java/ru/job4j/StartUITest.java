package ru.job4j;

//import org.junit.Ignore;
//import org.junit.Test;
//import ru.job4j.ui.Input;
//import ru.job4j.ui.StartUI;
//import ru.job4j.ui.StubInput;
//import ru.job4j.dao.ram.Tracker;
//import ru.job4j.domain.Item;
//
//import static org.junit.Assert.assertThat;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.hamcrest.core.Is.is;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class StartUITest {
//    /**
//     * Test add.
//     */
//    @Test @Ignore
//    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
//        Tracker tracker = new Tracker();
//        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
//        Menu menuMock = mock(Menu.class);
//
//        new StartUI(input, tracker, menuMock).init();
//        String result = tracker.getAll()[0].getName();
//
//        assertThat(result, is("test name"));
//    }
//
//    /**
//     * Test add.
//     */
//    @Test @Ignore
//    public void whenUserChooseMenuShowAllItemsThenPrintsInConsoleAllItems() {
//        Tracker tracker = new Tracker();
//        Input input = new StubInput(new String[]{"1", "", "6"});
//        Menu menuMock = mock(Menu.class);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        Item itemA = new Item("5", "test1", "testDescription", 123L);
//        Item itemB = new Item("10", "test2", "testDescription_2", 123L);
//        tracker.add(itemA);
//        tracker.add(itemB);
//
//        new StartUI(input, tracker, menuMock).init();
//
//        String separator = System.getProperty("line.separator");
//        String expected = "id: 5, name: test1, description: "
//                + "testDescription, created: Thu Jan 01 06:00:00 ALMT 1970"
//                + separator + "id: 10, name: test2, description: testDescription_2, "
//                + "created: Thu Jan 01 06:00:00 ALMT 1970" + separator;
//        assertThat(out.toString(), is(expected));
//    }
//
//    /**
//     * Test add.
//     */
//    @Test @Ignore
//    public void whenUserDeleteItemByIdThenTrackerNotHasItemWithSameId() {
//        Tracker tracker = new Tracker();
//        Input input = new StubInput(new String[]{"3", "5", "6"});
//        Menu menuMock = mock(Menu.class);
//
//        Item itemA = new Item("5", "test1", "testDescription", 123L);
//        Item itemB = new Item("test2", "testDescription_2", 123L);
//        tracker.add(itemA);
//        tracker.add(itemB);
//
//        new StartUI(input, tracker, menuMock).init();
//        Item[] result = tracker.getAll();
//
//        Item[] expected = {itemB};
//        assertThat(result, is(expected));
//    }
//
//    /**
//     * Test add.
//     */
//    @Test @Ignore
//    public void whenUserFindItemByIdThenPrintsInConsoleSameItemById() {
//        Tracker tracker = new Tracker();
//        Input input = new StubInput(new String[]{"4", "10", "", "6"});
//        Menu menuMock = mock(Menu.class);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        Item itemA = new Item("10", "test1", "testDescription", 123L);
//        Item itemB = new Item("test2", "testDescription_2", 123L);
//        tracker.add(itemA);
//        tracker.add(itemB);
//
//        new StartUI(input, tracker, menuMock).init();
//
//        String separator = System.getProperty("line.separator");
//        String expected = "id: 10, name: test1, description: "
//                + "testDescription, created: Thu Jan 01 06:00:00 ALMT 1970"
//                + separator;
//        assertThat(out.toString(), is(expected));
//    }
//
//    /**
//     * Test add.
//     */
//    @Test @Ignore
//    public void whenUserFindItemByNameThenPrintsInConsoleSameItemsByName() {
//        Tracker tracker = new Tracker();
//        Input input = new StubInput(new String[]{"5", "test1", "", "6"});
//        Menu menuMock = mock(Menu.class);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(out));
//
//        Item itemA = new Item("5", "test1", "testDescription", 123L);
//        Item itemB = new Item("test2", "testDescription_2", 123L);
//        Item itemC = new Item("10", "test1", "testDescription_3", 123L);
//        tracker.add(itemA);
//        tracker.add(itemB);
//        tracker.add(itemC);
//
//        new StartUI(input, tracker, menuMock).init();
//
//        String separator = System.getProperty("line.separator");
//        String expected = "id: 5, name: test1, description: "
//                + "testDescription, created: Thu Jan 01 06:00:00 ALMT 1970"
//                + separator + "id: 10, name: test1, description: testDescription_3, "
//                + "created: Thu Jan 01 06:00:00 ALMT 1970" + separator;
//        assertThat(out.toString(), is(expected));
//    }
//
//    /**
//     * Test add.
//     */
//    @Test @Ignore
//    public void whenUserExitProgramThenShowMenuOnce() {
//        Tracker tracker = new Tracker();
//        Input input = new StubInput(new String[]{"6"});
//        Menu menuMock = mock(Menu.class);
//
//        new StartUI(input, tracker, menuMock).init();
//
//        verify(menuMock, times(1)).showMenu();
//    }
}