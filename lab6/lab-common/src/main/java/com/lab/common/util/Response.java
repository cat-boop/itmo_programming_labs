package com.lab.common.util;

import com.lab.common.Data.Route;

import java.io.Serializable;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Response implements Serializable {
    private String serverMessage;
    private TreeSet<Route> collection;

    public Response(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public Response(TreeSet<Route> collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return (collection == null ? serverMessage : collection.stream().map(Route::toString).collect(Collectors.joining("\n")));
    }
}
