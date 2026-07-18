package com.ecommerce.constants;

public class SecurityConstants {

    private SecurityConstants() {
        // Prevent instantiation
    }

    // =====================================
    // JWT
    // =====================================

    public static final String JWT_SECRET =
            "your-super-secret-key-change-in-production";

    public static final long JWT_EXPIRATION =
            86400000; // 24 hours

    // =====================================
    // Headers
    // =====================================

    public static final String AUTHORIZATION =
            "Authorization";

    public static final String BEARER =
            "Bearer ";

    // =====================================
    // Roles
    // =====================================

    public static final String ROLE_ADMIN =
            "ROLE_ADMIN";

    public static final String ROLE_CUSTOMER =
            "ROLE_CUSTOMER";

    // =====================================
    // Public Endpoints
    // =====================================

    public static final String AUTH_API =
            "/api/auth/**";

    // =====================================
    // Swagger Endpoints
    // =====================================

    public static final String SWAGGER_UI =
            "/swagger-ui/**";

    public static final String SWAGGER_UI_HTML =
            "/swagger-ui.html";

    public static final String API_DOCS =
            "/v3/api-docs/**";

    // =====================================
    // Claims
    // =====================================

    public static final String ROLE_CLAIM =
            "role";

    public static final String EMAIL_CLAIM =
            "email";

}
