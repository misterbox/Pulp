package com.theskyegriffin.pulp.data.ynab;

import java.util.UUID;

public class CategoryGroup {
    private UUID id;
    private String name;
    private boolean hidden;
    private boolean deleted;
    private Category[] categories;

    public static String INTERNAL_MASTER_CATEGORY = "Internal Master Category";
    public static String CREDIT_CARD_PAYMENTS = "Credit Card Payments";
    public static String HIDDEN_CATEGORIES = "Hidden Categories";

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }
}
