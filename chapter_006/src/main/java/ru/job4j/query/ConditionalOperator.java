package ru.job4j.query;

/**
 * ConditionalOperator.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 25.11.2017
 */
public enum ConditionalOperator {
    EQUALLY,
    NOT_EQUAL,
    LIKE,
    MORE,
    LESS,
    BETWEEN,
    AND,
    OR;

    /**
     * getCriteria.
     * @param stringCriteria - строка условия.
     * @return ConditionalOperator - enum.
     */
    public static ConditionalOperator getConditionalOperator(String stringCriteria) {
       ConditionalOperator result = null;

       switch (stringCriteria.toLowerCase()) {
           case "==":
               result = EQUALLY;
               break;
           case "!=":
               result = NOT_EQUAL;
               break;
           case "like":
               result = LIKE;
               break;
           case ">":
               result = MORE;
               break;
           case "<":
               result = LESS;
               break;
           case "between":
               result = BETWEEN;
               break;
           case "and":
               result = AND;
               break;
           case "or":
               result = OR;
               break;
           default:
               break;
       }

       return result;
    }
}