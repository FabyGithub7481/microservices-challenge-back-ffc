package com.client_person_service.excepciones;

public class ClienteYaExisteException extends RuntimeException {
  public ClienteYaExisteException(String message) {
    super(message);
  }
}