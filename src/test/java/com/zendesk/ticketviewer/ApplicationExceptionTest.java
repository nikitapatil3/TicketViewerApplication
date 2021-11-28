package com.zendesk.ticketviewer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ApplicationExceptionTest {

    @Test
    public void testEquals() {
        ApplicationException applicationException1 = new ApplicationException(200);
        ApplicationException applicationException2 = new ApplicationException(200);
        ApplicationException applicationException3 = new ApplicationException();
        applicationException3.setErrorCode(401);
        assertEquals(applicationException1, applicationException2);
        assertNotEquals(applicationException1, applicationException3);
    }

    @Test
    public void testHashCode() {
        ApplicationException applicationException1 = new ApplicationException(200);
        ApplicationException applicationException2 = new ApplicationException(200);
        ApplicationException applicationException3 = new ApplicationException(401);
        assertEquals(applicationException1.hashCode(), applicationException2.hashCode());
        assertNotEquals(applicationException1.hashCode(), applicationException3.hashCode());
    }
}
