package com.wlt.wla.auth.model;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
