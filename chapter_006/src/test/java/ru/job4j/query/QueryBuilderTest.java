package ru.job4j.query;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 25.11.2017
 */
public class QueryBuilderTest {
    /**
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getQueryTextForAllColumnsWithoutFilter() {
        String result = QueryBuilder.createQuery("user").toString();

        String expected = "SELECT * FROM public.user";

        assertThat(result, is(expected));
    }

    @Test
    public void getQueryTextForAllColumnsWithOneFilter() {
        String result = QueryBuilder.createQuery("user")
                .setCondition(ConditionalOperator.NOT_EQUAL, "id", "1").toString();

        String expected = "SELECT * FROM public.user WHERE public.user.id <> '1'";

        assertThat(result, is(expected));
    }

    @Test
    public void getQueryTextForSpecificColumnsWithoutFilter() {
        String result = QueryBuilder.createQuery("request", new String[]{"number", "create_date"}).toString();

        String expected = "SELECT public.request.number, public.request.create_date FROM public.request";

        assertThat(result, is(expected));
    }

    @Test
    public void getQueryTextForSpecificColumnsWithFilterEqualTo() {
        String result = QueryBuilder.createQuery("request", new String[]{"number", "create_date"})
                .setCondition(ConditionalOperator.EQUALLY, "number", "P000000002").toString();

        String expected = "SELECT public.request.number, public.request.create_date FROM public.request "
                + "WHERE public.request.number = 'P000000002'";

        assertThat(result, is(expected));
    }

    @Test
    public void getQueryTextForSpecificColumnsWithFilterNotEqualTo() {
        String result = QueryBuilder.createQuery("request", new String[]{"number", "create_date"})
                .setCondition(ConditionalOperator.NOT_EQUAL, "create_date", "2017-03-05 10:00:35").toString();

        String expected = "SELECT public.request.number, public.request.create_date FROM public.request "
                + "WHERE public.request.create_date <> '2017-03-05 10:00:35'";

        assertThat(result, is(expected));
    }

    @Test
    public void getQueryTextForSpecificColumnsWithFilterLike() {
        String result = QueryBuilder.createQuery("user", new String[]{"id", "name"})
                .setCondition(ConditionalOperator.LIKE, "name", "Вас").toString();

        String expected = "SELECT public.user.id, public.user.name FROM public.user "
                + "WHERE public.user.name LIKE '%Вас%'";

        assertThat(result, is(expected));
    }

    @Test
    public void getQueryTextForSpecificColumnsWithFilterMore() {
        String result = QueryBuilder.createQuery("user", new String[]{"id", "name"})
                .setCondition(ConditionalOperator.MORE, "id", "2").toString();

        String expected = "SELECT public.user.id, public.user.name FROM public.user "
                + "WHERE public.user.id > '2'";

        assertThat(result, is(expected));
    }

    @Test
    public void getQueryTextForSpecificColumnsWithFilterLess() {
        String result = QueryBuilder.createQuery("user", new String[]{"id", "name"})
                .setCondition(ConditionalOperator.LESS, "id", "2").toString();

        String expected = "SELECT public.user.id, public.user.name FROM public.user "
                + "WHERE public.user.id < '2'";

        assertThat(result, is(expected));
    }

    @Test
    public void getQueryTextForSpecificColumnsWithFilterBetween() {
        String result = QueryBuilder.createQuery("user", new String[]{"id", "name"})
                .setCondition(ConditionalOperator.BETWEEN, "id", "2", "3").toString();

        String expected = "SELECT public.user.id, public.user.name FROM public.user "
                + "WHERE public.user.id BETWEEN '2' and '3'";

        assertThat(result, is(expected));
    }

    @Test
    public void getQueryTextForSpecificColumnsWithFilterWithAnd() {
        String result = QueryBuilder.createQuery("user", new String[]{"id", "name"})
                .setCondition(ConditionalOperator.EQUALLY, "id", "1")
                .setAND().setCondition(ConditionalOperator.EQUALLY, "name", "Петя").toString();

        String expected = "SELECT public.user.id, public.user.name FROM public.user "
                + "WHERE public.user.id = '1' AND public.user.name = 'Петя'";

        assertThat(result, is(expected));
    }

    @Test
    public void getQueryTextForSpecificColumnsWithFilterWithOr() {
        String result = QueryBuilder.createQuery("user", new String[]{"id", "name"})
                .setCondition(ConditionalOperator.EQUALLY, "id", "1")
                .setOR().setCondition(ConditionalOperator.EQUALLY, "id", "3")
                .setOR().setCondition(ConditionalOperator.EQUALLY, "id", "2").toString();

        String expected = "SELECT public.user.id, public.user.name FROM public.user "
                + "WHERE public.user.id = '1' OR public.user.id = '3' OR public.user.id = '2'";

        assertThat(result, is(expected));
    }

    @Test
    public void whenAddAnIncorrectConditionalStatementWithOneValueThenConditionException()
            throws ConditionException {

        thrown.expect(ConditionException.class);

        QueryBuilder.createQuery("user", new String[]{"id", "name"})
                .setCondition(ConditionalOperator.BETWEEN, "id", "1");
    }

    @Test
    public void whenAddAnIncorrectConditionalStatementWithTwoValueThenConditionException()
            throws ConditionException {

        thrown.expect(ConditionException.class);

        QueryBuilder.createQuery("user", new String[]{"id", "name"})
                .setCondition(ConditionalOperator.AND, "id", "1", "2");
    }

    @Test
    public void whenAddConditionalOperatorANDButThereAreNoConditionsYetThenConditionException()
            throws ConditionException {

        thrown.expect(ConditionException.class);

        QueryBuilder.createQuery("user", new String[]{"id", "name"}).setAND();
    }

    @Test
    public void whenAddConditionalOperatorOrButThereAreNoConditionsYetThenConditionException()
            throws ConditionException {

        thrown.expect(ConditionException.class);

        QueryBuilder.createQuery("user", new String[]{"id", "name"}).setOR();
    }

    @Test
    public void whenQueryIsNotFullyConstructedThenConditionException()
            throws ConditionException {

        thrown.expect(ConditionException.class);

        QueryBuilder.createQuery("request", new String[]{"number", "create_date"})
                .setCondition(ConditionalOperator.EQUALLY, "number", "P000000002").setAND().toString();
    }
}