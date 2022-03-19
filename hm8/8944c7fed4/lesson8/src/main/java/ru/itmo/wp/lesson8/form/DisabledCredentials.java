package ru.itmo.wp.lesson8.form;

import javax.validation.constraints.NotNull;

public class DisabledCredentials {
    @NotNull
    private long userId;

    @NotNull
    private boolean disabled;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
