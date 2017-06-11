package com.whichcontact.core.service;

public class ResponseDto {
    private int Status;
    private String message;


    /**
     * @return
     */
    public int getStatus() {
        return Status;
    }
    /**
     * @param status
     */
    public void setStatus(int status) {
        Status = status;
    }

    /**
     * @return
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return
     */
 
}