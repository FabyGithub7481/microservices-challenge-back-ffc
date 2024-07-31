package com.client_person_service.util;

public class IdGenerator {
  private static Long currentId = 0L;

  public synchronized static Long generateNextId() {
    return ++currentId;
  }
}
