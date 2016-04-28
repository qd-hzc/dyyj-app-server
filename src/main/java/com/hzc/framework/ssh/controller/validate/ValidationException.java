package com.hzc.framework.ssh.controller.validate;

import com.hzc.framework.ssh.controller.ActionContext;

/**
 * @author YinBin
 */
public class ValidationException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
        ActionContext.set("ValidationError",message);
    }

    public ValidationException(Throwable exception) {
        super(exception);
    }

    public ValidationException(String message, Throwable exception) {
        super(message, exception);
    }

}
