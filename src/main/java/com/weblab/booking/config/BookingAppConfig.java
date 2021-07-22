package com.weblab.booking.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({
        "com.weblab.booking.dao",
        "com.weblab.booking.service",
        "com.weblab.booking.controller"})
@PropertySource("classpath:db.properties")
public class BookingAppConfig {
}
