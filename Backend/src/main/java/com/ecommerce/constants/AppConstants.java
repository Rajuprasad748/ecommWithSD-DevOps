package com.ecommerce.constants;

public class AppConstants   {

    private AppConstants() {
        // Prevent instantiation
    }

    // ==========================
    // Pagination
    // ==========================

    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;

    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    // ==========================
    // Messages
    // ==========================

    public static final String SUCCESS = "Success";
    public static final String FAILED = "Failed";

    public static final String USER_REGISTERED_SUCCESSFULLY =
            "User registered successfully";

    public static final String LOGIN_SUCCESSFUL =
            "Login successful";

    public static final String PRODUCT_CREATED_SUCCESSFULLY =
            "Product created successfully";

    public static final String PRODUCT_UPDATED_SUCCESSFULLY =
            "Product updated successfully";

    public static final String PRODUCT_DELETED_SUCCESSFULLY =
            "Product deleted successfully";

    public static final String ORDER_PLACED_SUCCESSFULLY =
            "Order placed successfully";

    // ==========================
    // API Paths
    // ==========================

    public static final String API_BASE = "/api";

    public static final String AUTH_API = "/api/auth";

    public static final String PRODUCT_API = "/api/products";

    public static final String ORDER_API = "/api/orders";

    // ==========================
    // Cache Names
    // ==========================

    public static final String PRODUCTS_CACHE = "products";

    public static final String CATEGORIES_CACHE = "categories";

}
