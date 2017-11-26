package ru.job4j.query;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * QueryBuilder - построитель запросов (не изменяемый).
 *
 * @author Stanislav (376825@mail.ru)
 * @since 25.11.2017
 */
public class QueryBuilder {
    /**
     * имя таблицы по которой делаем запрос.
     */
    private final String nameTableFrom;
    /**
     * список имен колонок, которые выводить.
     */
    private final LinkedList<String> viewColumns = new LinkedList<>();
    /**
     * список условий.
     */
    private final LinkedList<QueryBuilder.Condition> conditions = new LinkedList<>();

    /**
     * Constructor.
     */
    private QueryBuilder(String nameTableFrom) {
        this.nameTableFrom = nameTableFrom;
    }

    /**
     * Constructor.
     */
    private QueryBuilder(String nameTableFrom, String[] viewColumns) {
        this.nameTableFrom = nameTableFrom;
        this.viewColumns.addAll(Arrays.asList(viewColumns));
    }

    /**
     * createQuery.
     * @param nameTableFrom - имя таблицы по которой делаем запрос.
     * @return QueryBuilder.
     */
    public static QueryBuilder createQuery(String nameTableFrom) {
        return new QueryBuilder(nameTableFrom);
    }

    /**
     * createQuery.
     * @param nameTableFrom - имя таблицы по которой делаем запрос.
     * @param viewColumns - массив имен колонок, которые выводить.
     * @return QueryBuilder.
     */
    public static QueryBuilder createQuery(String nameTableFrom, String[] viewColumns) {
        return new QueryBuilder(nameTableFrom, viewColumns);
    }

    /**
     * createQuery.
     * @param conditionalOperator - условный оператор.
     * @param column - колонка по которой устанавливаем условие.
     * @param value - значение условия.
     * @exception ConditionException - неправильно формируем условие.
     * @return QueryBuilder.
     */
    public QueryBuilder setCondition(ConditionalOperator conditionalOperator, String column, String value) {
        if (conditionalOperator == ConditionalOperator.AND || conditionalOperator == ConditionalOperator.OR
                || conditionalOperator == ConditionalOperator.BETWEEN) {
            throw new ConditionException("wrong condition");
        }

        this.conditions.add(new Condition(conditionalOperator, column, new String[]{value}));
        return this;
    }

    /**
     * createQuery.
     * @param conditionalOperator - условный оператор.
     * @param column - колонка по которой устанавливаем условие.
     * @param value1 - значение условия 1.
     * @param value2 - значение условия 2.
     * @exception ConditionException - неправильно формируем условие.
     * @return QueryBuilder.
     */
    public QueryBuilder setCondition(ConditionalOperator conditionalOperator, String column, String value1, String value2) {
        if (conditionalOperator != ConditionalOperator.BETWEEN) {
            throw new ConditionException("wrong condition");
        }

        this.conditions.add(new Condition(conditionalOperator, column, new String[]{value1, value2}));
        return this;
    }

    /**
     * setAND.
     * @exception ConditionException - неправильно формируем условие.
     * @return QueryBuilder.
     */
    public QueryBuilder setAND() {
        if (this.conditions.size() == 0) {
            throw new ConditionException("wrong condition");
        }

        this.conditions.add(new Condition(ConditionalOperator.AND));
        return this;
    }

    /**
     * setOR.
     * @exception ConditionException - неправильно формируем условие.
     * @return QueryBuilder.
     */
    public QueryBuilder setOR() {
        if (this.conditions.size() == 0) {
            throw new ConditionException("wrong condition");
        }

        this.conditions.add(new Condition(ConditionalOperator.OR));
        return this;
    }

    /**
     * createBeginningQuery - создать начало запроса.
     */
    private void createBeginningQuery(StringBuilder stringBuilder) {
        String schema = "public.";

        stringBuilder.append("SELECT ");

        if (this.viewColumns.size() == 0) {
            stringBuilder.append("*");
        }

        for (int i = 0; i < this.viewColumns.size(); i++) {
            stringBuilder.append(schema)
                    .append(this.nameTableFrom)
                    .append(".")
                    .append(this.viewColumns.get(i).toLowerCase());

            if (i != this.viewColumns.size() - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append(" FROM ")
                .append(schema)
                .append(this.nameTableFrom);
    }

    /**
     * createBeginningQuery - создать условия выборки.
     */
    private void createConditionForQuery(StringBuilder stringBuilder) {
        if (this.conditions.size() != 0) {
            String schema = "public.";

            for (int i = 0; i < this.conditions.size(); i++) {
                Condition condition = this.conditions.get(i);

                if (this.conditions.size() == i + 1 && (condition.conditionalOperator == ConditionalOperator.OR
                        || condition.conditionalOperator == ConditionalOperator.AND)) {
                    throw new ConditionException("wrong condition");
                }

                if (condition.conditionalOperator != ConditionalOperator.OR
                        && condition.conditionalOperator != ConditionalOperator.AND) {

                    if (i == 0) {
                        stringBuilder.append(" WHERE ");
                    }

                    stringBuilder.append(schema)
                            .append(this.nameTableFrom)
                            .append(".")
                            .append(condition.column);

                    if (condition.conditionalOperator == ConditionalOperator.LIKE) {
                        stringBuilder.append(" LIKE '%").append(condition.values[0]).append("%'");
                    } else if (condition.conditionalOperator == ConditionalOperator.BETWEEN) {
                        stringBuilder.append(" BETWEEN '")
                                .append(condition.values[0])
                                .append("' and '")
                                .append(condition.values[1])
                                .append("'");
                    } else {
                        if (condition.conditionalOperator == ConditionalOperator.EQUALLY) {
                            stringBuilder.append(" = ");
                        } else if (condition.conditionalOperator == ConditionalOperator.NOT_EQUAL) {
                            stringBuilder.append(" <> ");
                        } else if (condition.conditionalOperator == ConditionalOperator.MORE) {
                            stringBuilder.append(" > ");
                        } else if (condition.conditionalOperator == ConditionalOperator.LESS) {
                            stringBuilder.append(" < ");
                        }

                        stringBuilder.append("'").append(condition.values[0]).append("'");
                    }
                } else {
                    stringBuilder.append(" ").append(condition.conditionalOperator).append(" ");
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        createBeginningQuery(result);
        createConditionForQuery(result);

        return result.toString();
    }

    /**
     * Condition.
     */
    private class Condition {
        /**
         * условный оператор.
         */
        ConditionalOperator conditionalOperator;
        /**
         * колонка по которой устанавливаем условие.
         */
        String column;
        /**
         * массив значений условий.
         */
        String[] values;

        /**
         * Constructor.
         */
        public Condition(ConditionalOperator conditionalOperator) {
            this.conditionalOperator = conditionalOperator;
        }

        /**
         * Constructor.
         */
        public Condition(ConditionalOperator conditionalOperator, String column, String[] values) {
            this.conditionalOperator = conditionalOperator;
            this.column = column;
            this.values = values;
        }
    }
}