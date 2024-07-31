package com.client_person_service.excepciones;

public class ClienteNotFoundException extends RuntimeException {
  public ClienteNotFoundException(String message) {
    super(message);
  }
}

