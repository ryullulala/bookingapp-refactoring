package com.weblab.booking.controller;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class HandlerMappingIMPL implements HandlerMapping, ApplicationContextAware {
    private ApplicationContext container;

    @Override
    public Controller getController(String path) {
        Controller controller = null;

        try {
            controller = (Controller)container.getBean(path);
        }catch (BeansException ex) {
            ex.printStackTrace();
        }
        return controller;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.container = applicationContext;
    }
}
