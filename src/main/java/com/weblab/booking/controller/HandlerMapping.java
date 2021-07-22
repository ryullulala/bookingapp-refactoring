package com.weblab.booking.controller;

public interface HandlerMapping {
    Controller getController(String path);
}
