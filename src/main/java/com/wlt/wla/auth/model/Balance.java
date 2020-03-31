package com.wlt.wla.auth.model;

import java.util.Date;

public class Balance {
    public float getBalanceChange() {
        return balanceChange;
    }

    public void setBalanceChange(float balanceChange) {
        this.balanceChange = balanceChange;
    }

    private float balanceChange;

    public String getIsUpdating() {
        return isUpdating;
    }

    public void setIsUpdating(String isUpdating) {
        this.isUpdating = isUpdating;
    }

    private String isUpdating;

    private String note;

    private String updateChoice;

    public String getUpdateChoice() {
        return updateChoice;
    }

    public void setUpdateChoice(String updateChoice) {
        this.updateChoice = updateChoice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    private String balanceChangeStr;

    public String getBalanceChangeStr() {
        return balanceChangeStr;
    }

    public void setBalanceChangeStr(String balanceChangeStr) {
        this.balanceChangeStr = balanceChangeStr;
    }
}
