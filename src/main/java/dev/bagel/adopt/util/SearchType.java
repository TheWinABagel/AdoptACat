package dev.bagel.adopt.util;

public enum SearchType {
    EQUAL("="),
    GREATER_THAN("gt="),
    LESS_THAN("lt="),
    GREATER_THAN_OR_EQUAL_TO("gte="),
    LESS_THAN_OR_EQUAL_TO("lte="),
    CLIENT_FILTER(null);

    final String getReqStr;

    SearchType(String getReqStr) {
        this.getReqStr = getReqStr;
    }
}
