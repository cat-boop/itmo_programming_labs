package com.lab.common.util;

import java.io.Serializable;

public class Response implements Serializable {
    private String serverMessage;

    public Response(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public String getServerMessage() {
        return serverMessage;
    }
}
