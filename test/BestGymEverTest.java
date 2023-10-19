import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BestGymEverTest {
    BestGymEver b = new BestGymEver();
    Customer customer1 = new Customer("7703021234","Alhambra Aromes", LocalDate.parse("2023-07-01"));
    Customer customer3 = new Customer("8512021234","Chamade Coriola",LocalDate.parse("2018-03-12"));
    Customer customer5 = new Customer("7605021234","Elmer Ekorrsson",LocalDate.parse("2022-11-07"));
    String customersTestFilePath = "test/CustomerTestFile.txt";
    String trainingCustomersTestFilePath = "test/TrainingCustomersTest.txt";

    @Test
    void customerBasedOnSocialSecurityNumberFromFile() {
        String actualCustomerName1 = b.customerBasedOnSocialSecurityNumberFromFile(customersTestFilePath, "7703021234").getName();
        LocalDate actualCustomerPurchaseDateOfMembership1 = b.customerBasedOnSocialSecurityNumberFromFile(customersTestFilePath, "7703021234").getPurchaseDateOfMembership();
        assertEquals(customer1.getName(), actualCustomerName1);
        assertEquals(customer1.getPurchaseDateOfMembership(), actualCustomerPurchaseDateOfMembership1);

        String actualCustomerName3 = b.customerBasedOnSocialSecurityNumberFromFile(customersTestFilePath, "8512021234").getName();
        LocalDate actualCustomerPurchaseDateOfMembership3 = b.customerBasedOnSocialSecurityNumberFromFile(customersTestFilePath,"8512021234").getPurchaseDateOfMembership();
        assertEquals(customer3.getName(), actualCustomerName3);
        assertEquals(customer3.getPurchaseDateOfMembership(), actualCustomerPurchaseDateOfMembership3);

        String actualCustomerName5 = b.customerBasedOnSocialSecurityNumberFromFile(customersTestFilePath, "7605021234").getName();
        LocalDate actualCustomerPurchaseDateOfMembership5 = b.customerBasedOnSocialSecurityNumberFromFile(customersTestFilePath,"7605021234").getPurchaseDateOfMembership();
        assertEquals(customer5.getName(), actualCustomerName5);
        assertEquals(customer5.getPurchaseDateOfMembership(), actualCustomerPurchaseDateOfMembership5);

        assertNull(b.customerBasedOnSocialSecurityNumberFromFile(customersTestFilePath, "9999999999"));

    }

    @Test
    void isCustomerStillMember() {
        b.test = true;
        assertTrue(b.isCustomerStillMember(customer1));
        assertFalse(b.isCustomerStillMember(customer3));
        assertTrue(b.isCustomerStillMember(customer5));
    }

    @Test
    void isStringNumericTest(){
        String correctInput = "7605021234";
        String badInput1 = "asdfasdfas";
        String badInput2 = "asd123asd1";
        assertTrue(b.isStringNumeric(correctInput));
        assertFalse(b.isStringNumeric(badInput1));
        assertFalse(b.isStringNumeric(badInput2));
    }

     @Test
     void socialSecurityNumberCorrectInputTest(){
        String correctInput = "7605021234";
        String badInput1 = "asdfasdfas";
         String badInput2 = "12";
         String badInput3 = "123123123123";
         String badInput4 = "asd123asd1";
         assertEquals(correctInput, b.socialSecurityNumberCorrectInput(correctInput));
         assertNull(b.socialSecurityNumberCorrectInput(badInput1));
         assertNull(b.socialSecurityNumberCorrectInput(badInput2));
         assertNull(b.socialSecurityNumberCorrectInput(badInput3));
         assertNull(b.socialSecurityNumberCorrectInput(badInput4));
     }
    @Test
    void writeDataToFileTest(){
        b.test = true;
        b.writeCustomerToFile(customer1,trainingCustomersTestFilePath);
        int expectedLinesInFile = 2;
        int actualLinesInFile = countLinesInFile(trainingCustomersTestFilePath);
        assertEquals(expectedLinesInFile,actualLinesInFile);
    }
    public int countLinesInFile(String FilePath){
        int counter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(FilePath))){
            while (br.readLine() != null) {
            counter++;
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
        }
        return counter;
    }
}