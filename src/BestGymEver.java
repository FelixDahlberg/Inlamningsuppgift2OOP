import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BestGymEver {
    private String customerFilePath = "src/customers.txt";
    private String trainingCustomerFilePath = "src/TrainingCustomers.txt";
    protected boolean test = false;

    public String socialSecurityNumberCorrectInput(String input) {
        if (!isStringNumeric(input)) {
            System.out.println("Får bara vara siffror");
            return null;
        } else if (input.length() == 12 && input.startsWith("19")) {
            input = input.substring(2, 12).strip();
            return input;
        } else if (input.length() != 10) {
            System.out.println("personnummer måste vara 10 eller 12 siffror långt");
            return null;
        }
        return input;
    }

    public Customer customerBasedOnSocialSecurityNumberFromFile(String FilePath, String socialSecurityNumber) {
        String firstLine;
        String tempLine;
        LocalDate localDate;
        String[] customerDataPartsFirstLine;
        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            while ((tempLine = br.readLine()) != null) {
                firstLine = tempLine;
                customerDataPartsFirstLine = firstLine.split(",");
                 if (customerDataPartsFirstLine[0].equalsIgnoreCase(socialSecurityNumber)) {
                    localDate = LocalDate.parse(br.readLine());

                    return new Customer(customerDataPartsFirstLine[0], customerDataPartsFirstLine[1].strip(), localDate);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("person finns inte");
        return null;
    }

    public boolean isCustomerStillMember(Customer customer) {
        LocalDate localDate = LocalDate.now();
        if (test) {
            localDate = LocalDate.parse("2023-10-19");
        }
        Period d = Period.between(customer.getPurchaseDateOfMembership(), localDate);
        if (d.getYears() == 0) {
            System.out.println(customer.getName() + " är kund, kortet går ut: " + (customer.getPurchaseDateOfMembership().plusYears(1)));
            return true;
        }
        System.out.println(customer.getName() + "är inte kund, kortet gick ut: " + (customer.getPurchaseDateOfMembership().plusYears(1)));
        return false;
    }

    public void writeCustomerToFile(Customer customer, String filePath) {
        boolean append = true;
        if (test) {
            append = false;
        }
        LocalDate localDate = LocalDate.now();
        String firstString = customer.getSocialSecurityNumber() + ", " + customer.getName();
        String secondString = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, append))) {
            bw.write(firstString + "\n" + secondString + "\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(customer.getName() + " träning har registrerats");
    }

    public void bestGymEverUserInterface() {
        Scanner sc = new Scanner(System.in);
        String input;
        Customer customer;
        boolean yesNo;
        System.out.println("Skriv exit för att sluta.");
        while (true) {
            System.out.println("Skriv ett personnummer:");
            input = String.valueOf(sc.nextLine());
            if (input.equalsIgnoreCase("exit")) {
                break;
            } else if ((input = socialSecurityNumberCorrectInput(input)) != null) {
                customer = customerBasedOnSocialSecurityNumberFromFile(customerFilePath, input);
                if (customer != null) {
                    yesNo = isCustomerStillMember(customer);
                    if (yesNo){
                        writeCustomerToFile(customer,trainingCustomerFilePath);
                    }
                }
            }
        }
    }

    public boolean isStringNumeric(String inputString) {
        try {
            Double.parseDouble(inputString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        BestGymEver b = new BestGymEver();

        b.bestGymEverUserInterface();
    }
}
