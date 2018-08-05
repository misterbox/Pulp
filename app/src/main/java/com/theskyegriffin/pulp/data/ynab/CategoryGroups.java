package com.theskyegriffin.pulp.data.ynab;

import com.google.gson.annotations.SerializedName;

public class CategoryGroups {
    @SerializedName("category_groups")
    private CategoryGroup[] categoryGroups;

    public CategoryGroup[] getCategoryGroups() {
        return categoryGroups;
    }

    public void setCategoryGroups(CategoryGroup[] categoryGroups) {
        this.categoryGroups = categoryGroups;
    }
}
