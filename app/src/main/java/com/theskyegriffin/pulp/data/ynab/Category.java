package com.theskyegriffin.pulp.data.ynab;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

public class Category {
    private UUID id;
    @SerializedName("category_group_id")
    private UUID categoryGroupId;
    private String name;
    private boolean hidden;
    @SerializedName("original_category_group_id")
    private UUID originalCategoryGroupId;
    private String note;
    private long budgeted;
    private long activity;
    private long balance;
    @SerializedName("goal_type")
    private String goalType;
    @SerializedName("goal_creation_month")
    private Date goalCreationMonth;
    @SerializedName("goal_target")
    private long goalTarget;
    @SerializedName("goal_target_month")
    private Date goalTargetMonth;
    @SerializedName("goal_percentage_complete")
    private double goalPercentageComplete;
    private boolean deleted;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCategoryGroupId() {
        return categoryGroupId;
    }

    public void setCategoryGroupId(UUID categoryGroupId) {
        this.categoryGroupId = categoryGroupId;
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

    public UUID getOriginalCategoryGroupId() {
        return originalCategoryGroupId;
    }

    public void setOriginalCategoryGroupId(UUID originalCategoryGroupId) {
        this.originalCategoryGroupId = originalCategoryGroupId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getBudgeted() {
        return budgeted;
    }

    public void setBudgeted(long budgeted) {
        this.budgeted = budgeted;
    }

    public long getActivity() {
        return activity;
    }

    public void setActivity(long activity) {
        this.activity = activity;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public Date getGoalCreationMonth() {
        return goalCreationMonth;
    }

    public void setGoalCreationMonth(Date goalCreationMonth) {
        this.goalCreationMonth = goalCreationMonth;
    }

    public long getGoalTarget() {
        return goalTarget;
    }

    public void setGoalTarget(long goalTarget) {
        this.goalTarget = goalTarget;
    }

    public Date getGoalTargetMonth() {
        return goalTargetMonth;
    }

    public void setGoalTargetMonth(Date goalTargetMonth) {
        this.goalTargetMonth = goalTargetMonth;
    }

    public double getGoalPercentageComplete() {
        return goalPercentageComplete;
    }

    public void setGoalPercentageComplete(double goalPercentageComplete) {
        this.goalPercentageComplete = goalPercentageComplete;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
