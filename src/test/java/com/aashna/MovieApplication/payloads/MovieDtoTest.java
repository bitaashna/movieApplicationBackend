//package com.aashna.MovieApplication.payloads;
//
//import org.junit.jupiter.api.Test;
//
//import java.sql.Blob;
//import java.sql.SQLException;
//
//public class MovieDtoTest {
//    @Test
//    public void testBlobToBase64Conversion() throws SQLException {
//        // Create a mock Blob or fetch a real Blob from the database
//        Blob mockBlob = createMockBlob();
//
//        // Test the conversion logic
//        String posterBase64 = new MovieDto().convertBlobToBase64(mockBlob);
//        System.out.println("Base64 Image: " + posterBase64);
//    }
//
//    // Helper method to create a mock Blob for testing
//    private Blob createMockBlob() throws SQLException {
//        // Create a mock Blob using java.sql.Blob or any other testing library
//        // For example:
//        return new javax.sql.rowset.serial.SerialBlob(new byte[0]);
//    }
//}
