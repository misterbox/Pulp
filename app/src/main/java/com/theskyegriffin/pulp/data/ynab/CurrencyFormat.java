package com.theskyegriffin.pulp.data.ynab;

import com.google.gson.annotations.SerializedName;

public class CurrencyFormat {
    @SerializedName("iso_code")
    private String isoCode;
    @SerializedName("example_format")
    private String exampleFormat;
    @SerializedName("decimal_digits")
    private String decimalDigits;
    @SerializedName("decimal_separator")
    private String decimalSeparator;
    @SerializedName("symbol_first")
    private boolean symbolFirst;
    @SerializedName("group_separator")
    private String groupSeparator;
    @SerializedName("currency_symbol")
    private String currencySymbol;
    @SerializedName("display_symbol")
    private boolean displaySymbol;

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getExampleFormat() {
        return exampleFormat;
    }

    public void setExampleFormat(String exampleFormat) {
        this.exampleFormat = exampleFormat;
    }

    public String getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(String decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    public boolean isSymbolFirst() {
        return symbolFirst;
    }

    public void setSymbolFirst(boolean symbolFirst) {
        this.symbolFirst = symbolFirst;
    }

    public String getGroupSeparator() {
        return groupSeparator;
    }

    public void setGroupSeparator(String groupSeparator) {
        this.groupSeparator = groupSeparator;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public boolean isDisplaySymbol() {
        return displaySymbol;
    }

    public void setDisplaySymbol(boolean displaySymbol) {
        this.displaySymbol = displaySymbol;
    }
}
