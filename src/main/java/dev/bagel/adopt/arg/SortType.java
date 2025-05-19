package dev.bagel.adopt.arg;

public enum SortType {
    EQUAL("=", "="),
    GREATER_THAN("_gt=", ">"),
    LESS_THAN("_lt=", "<"),
    GREATER_THAN_OR_EQUAL_TO("_gte=", ">="),
    LESS_THAN_OR_EQUAL_TO("_lte=", "<="),
    CONTAINS(null, "contains=");

    public final String getReqStr;
    public final String argStr;

    SortType(String getReqStr, String argStr) {
        this.getReqStr = getReqStr;
        this.argStr = argStr;
    }

    public boolean canUseForGetReq() {
        return getReqStr != null;
    }

    public static SortType find(String argStr) {
        for (SortType type : values()) {
            if (type == null) continue;
            if (argStr.equals(type.argStr)) return type;
        }
        return null;
    }
}
