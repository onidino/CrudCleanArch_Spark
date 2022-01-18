package com.cleancrud.spark.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static spark.Spark.*;

public class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);
    private static final String SERVER_PROPERTIES = "server";
    private static final String SERVER_PORT = "server.port";
    private static final String SERVER_ADDRESS = "server.address";
    private static final String SERVER_BASE_THREADS = "server.base.threads";
    private static final String SERVER_MAX_MULTIPLIER = "server.max.multiplier";
    private static final String SERVER_MIN_MULTIPLIER = "server.min.multiplier";
    private static final String SERVER_REQUEST_TIMEOUT = "server.request.timeout";

    private int port;
    private String address;
    private int baseThreads;
    private int maxMultiplier;
    private int minMultiplier;
    private int timeout;

    public void config() {
        initWithDefaults();

        final int cores = Runtime.getRuntime().availableProcessors();
        final int maxThreads = cores * maxMultiplier + baseThreads;
        final int minThreads = cores * minMultiplier + baseThreads;

        port(port);
        ipAddress(address);
        threadPool(maxThreads, minThreads, timeout);

        LOGGER.info("Listening in port {} : {} - cores: {} - thread_pool [min: {} | max: {} | timeout : {}]",
                address, port, cores, minThreads, maxThreads, timeout);
    }

    public void start() {
        init();
        awaitInitialization();
    }

    private void initWithDefaults() {
        port = Integer.parseInt(getServerPropertyValue(SERVER_PORT, "0"));
        address = getServerPropertyValue(SERVER_ADDRESS, "0.0.0.0");
        baseThreads = Integer.parseInt(getServerPropertyValue(SERVER_BASE_THREADS, "3"));
        maxMultiplier = Integer.parseInt(getServerPropertyValue(SERVER_MAX_MULTIPLIER, "2"));
        minMultiplier = Integer.parseInt(getServerPropertyValue(SERVER_MIN_MULTIPLIER, "1"));
        timeout = Integer.parseInt(getServerPropertyValue(SERVER_REQUEST_TIMEOUT, "25000"));
    }

    private String getServerPropertyValue(String key, String defaultValue) {
        try {
            ResourceBundle serverProperties = ResourceBundle.getBundle(SERVER_PROPERTIES);
            return serverProperties.getString(key);
        } catch (MissingResourceException ex) {
            return defaultValue;
        }
    }
}
