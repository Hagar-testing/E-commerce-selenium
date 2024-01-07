package com.ecommerce.locators;

public class ProductsLocators {

    // XPath for the list of products
    public static final String PRODUCTS_LIST_XPATH = "//div[@class='card']//h5";

    // ID for the products count text element
    public static final String PRODUCTS_COUNT_TEXT_ELEMENT_ID = "res";

    // ID for the toast container
    public static final String TOAST_CONTAINER_ID = "toast-container";

    // XPath for the button containing 'Cart'
    public static final String ADD_TO_CART_BUTTON_XPATH = ".//following-sibling::button[contains(., 'Cart')]";
}
