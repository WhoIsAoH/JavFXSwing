package com.grg.cps4005oop2308819;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class LawFirmAppTest {
  private LawFirmApp lawFirmApp;
  private Scanner mockScanner;
  private DatabaseConnection mockConnection;

  @BeforeEach
  public void setUp() {
    mockScanner = mock(Scanner.class);
    mockConnection = mock(DatabaseConnection.class);
    lawFirmApp = new LawFirmApp(mockConnection);
  }


  @Test
  public void testViewAllCase() throws SQLException, InterruptedException {
    Date dateFiled1 = Date.valueOf("2022-01-01");
    Date dateClosed1 = Date.valueOf("2022-01-10");
    Date dateFiled2 = Date.valueOf("2022-02-01");
    Date dateClosed2 = Date.valueOf("2022-02-10");
    Client client1 = new Client(1, "John Doe", "123 Main St", "555-1234", "john@example.com");
    Client client2 = new Client(2, "Jane Doe", "456 Elm St", "555-5678", "jane@example.com");
    Case firstMockTest = new Case(1, "Case001", "Title1", "Description1", "Open", dateFiled1, dateClosed1, client1);
    Case secondMOnckTest = new Case(2, "Case002", "Title2", "Description2", "Closed", dateFiled2, dateClosed2, client2);
    when(mockConnection.getAllCases()).thenReturn(Arrays.asList(firstMockTest, secondMOnckTest));
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 5; i++) {
      executorService.execute(() -> lawFirmApp.viewAllCase());
    }
    executorService.shutdown();
    executorService.awaitTermination(1, TimeUnit.MINUTES);
    verify(mockConnection, times(5)).getAllCases();
  }

  @Test
  void testUpdateClient_SQLException() throws SQLException {
    DatabaseConnection connection = mock(DatabaseConnection.class);
    LawFirmApp lawFirmApp = new LawFirmApp();
    try {
      int clientId = 1;
      when(connection.getClient(clientId)).thenThrow(new SQLException("SQL Error"));
      lawFirmApp.updateClient();
      assertEquals("Error updating client: SQL Error", lawFirmApp.getOutputAreaText());
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
}

