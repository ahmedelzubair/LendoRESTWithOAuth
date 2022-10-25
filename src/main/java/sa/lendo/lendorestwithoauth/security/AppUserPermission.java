package sa.lendo.lendorestwithoauth.security;

public enum AppUserPermission {

    USERS_READ("users:read"),
    USERS_WRITE("users:write"),

    ADDRESS_READ("address:read"),
    ADDRESS_WRITE("address:write"),

    CONTINENT_READ("continent:read"),
    CONTINENT_WRITE("continent:write"),

    COUNTRY_READ("country:read"),
    COUNTRY_WRITE("country:write"),

    CITY_READ("city:read"),
    CITY_WRITE("city:write"),

    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),

    AD_READ("ad:read"),
    AD_WRITE("ad:write"),

    COMMENT_READ("comment:read"),
    COMMENT_WRITE("comment:write");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
