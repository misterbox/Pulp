package com.theskyegriffin.pulp.ynab;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

public class Budget {
    private UUID id;
    private String name;
    @SerializedName("last_modified_on")
    private Date lastModified;
    @SerializedName("first_month")
    private Date firstMonth;
    @SerializedName("last_month")
    private Date lastMonth;
    @SerializedName("date_format")
    private DateFormat dateFormat;
    @SerializedName("currency_format")
    private CurrencyFormat currencyFormat;

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

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getFirstMonth() {
        return firstMonth;
    }

    public void setFirstMonth(Date firstMonth) {
        this.firstMonth = firstMonth;
    }

    public Date getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(Date lastMonth) {
        this.lastMonth = lastMonth;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public CurrencyFormat getCurrencyFormat() {
        return currencyFormat;
    }

    public void setCurrencyFormat(CurrencyFormat currencyFormat) {
        this.currencyFormat = currencyFormat;
    }
}
