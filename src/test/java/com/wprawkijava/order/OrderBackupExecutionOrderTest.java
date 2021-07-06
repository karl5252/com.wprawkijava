package com.wprawkijava.order;
import com.wprawkijava.order.Order;
import com.wprawkijava.order.OrderBackup;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionOrderTest {

    @Test
    void callingBackupWithoutCreatingAFileFirstShouldThrowException() throws IOException {
        OrderBackup orderBackup = new OrderBackup();

        assertThrows(IOException.class, ()->orderBackup.backupOrder(new Order()));
    }
}
