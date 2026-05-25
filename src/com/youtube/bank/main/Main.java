package com.youtube.bank.main;

import com.youtube.bank.entity.User;
import com.youtube.bank.service.UserService;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    static Main main = new Main();
    static UserService userService = new UserService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("Enter your username");
            String username = scanner.next();

            System.out.println("Enter your password");
            String password = scanner.next();

            User user = userService.login(username, password);
            if(user != null && user.getRole().equals("admin")) {
                main.initAdmin();
            } else if(user != null && user.getRole().equals("user")){
                main.initCustomer(user);
            }else {
                System.out.println("Login failed! :(");
            }
        }
//        userService.printUsers();
//        System.out.println("Username: " + username + ", Password: " + password);
    }

    private void initAdmin(){

        boolean flag = true;

        while(flag){
            System.out.println("1. Exit/Logout");
            System.out.println("2. Create a customer account");

            int selectedOption = scanner.nextInt();

            switch(selectedOption){
                case 1:
                    flag = false;
                    System.out.println("You have successfully logged out...");
                    break;
                case 2:
                    main.addNewCustomer();
                break;
                default:
                    System.out.println("Wrong choice");

            }
        }
    }

    private void addNewCustomer(){
        System.out.println("Enter username");
        String username = scanner.next();

        System.out.println("Enter password");
        String password = scanner.next();

        System.out.println("Enter contact number");
        String contact = scanner.next();

        boolean result = userService.addNewCustomer(username, password, contact);

        if(result == true){
            System.out.println("Customer account is created...");
        } else {
            System.out.println("Customer account creation failed...");
        }

    }

    private void initCustomer(User user){
        boolean flag = true;

        while(flag) {
            System.out.println("1. Exit/Logout");
            System.out.println("2. Check bank balance");

            int selectedOption = scanner.nextInt();


            switch(selectedOption){
                case 1:
                    flag = false;
                    System.out.println("You have successfully logged out...");
                    break;
                case 2:
                    Double balance = main.checkBankBalance(user.getUsername());
                    if(balance != null){
                        System.out.println("Your bank balance is " + balance);
                    } else {
                        System.out.println("Check your username");
                    }
                    break;
                default:
                    System.out.println("Wrong choice");

            }
        }
    }

    private Double checkBankBalance(String userId){
        return userService.checkBankBalance(userId);
    }

}