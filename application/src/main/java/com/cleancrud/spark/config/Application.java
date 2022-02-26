package com.cleancrud.spark.config;

import com.cleancrud.spark.module.InjectorFactory;
import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import spark.servlet.SparkApplication;

public class Application implements SparkApplication {

  private final MainRouter mainRouter;
  private final Server server;

  public Application() {
    this.mainRouter = new MainRouter();
    this.server = new Server();
  }

  @Override
  public void init() {
    // Injectors
    InjectorFactory.addInjectors();

    // Server Config
    server.config();

    // Endpoints
    mainRouter.addRoutes();

    // Server Start
    server.start();
  }


  public static void main(String[] args) {
    new Application().init();
  }


  public static <T> T getInjectorInstance(Class<T> clazz) {
    try {
      Injector injector = InjectorFactory.getInjector();
      return injector != null ? injector.getInstance(clazz) : null;
    } catch (ConfigurationException ex) {
      return null;
    }
  }
}
