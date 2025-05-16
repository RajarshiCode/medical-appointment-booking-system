package com.cg.controller;

import java.util.Scanner;
import com.*;
import com.cg.dao.AppointmentSystem;
import com.cg.entity.Appointment;
import com.cg.entity.Doctor;
import com.cg.entity.InvalidAppointmentException;
import com.cg.entity.Patient;

public class Main {
    public static void main(String[] args) {
    	System.out.println("Welcome to Medical Appointment Booking System!");
        AppointmentSystem system = new AppointmentSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
//                System.out.println("\n1. Register Patient\n2. Register Doctor\n3. Book Appointment\n4. Show All Doctors\n5. Show All Appointments\n6. Complete Appointment\n7. Exit");
//                System.out.print("Enter choice: ");
            	System.out.println("\n ===== Medical Appointment System Menu =====");
	            System.out.println("||1.  Register Patient                      ||");
	            System.out.println("||2.  Register Doctor                       ||");
	            System.out.println("||3.  Book Appointment                      ||");
	            System.out.println("||4.  Show All Doctors                      ||");
	            System.out.println("||5.  Show All Appointments                 ||");
	            System.out.println("||6.  Complete Appointment                  ||");
	            System.out.println("||7.  Exit.                                 ||");
	            System.out.println("=============================================");
	            System.out.println("Enter your choice: ");
                int choice;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number from 1 to 7.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter Patient Name: ");
                        String pname = sc.nextLine();
                        system.registerPatient(pname);
                        break;

                    case 2:
                        System.out.print("Enter Doctor Name: ");
                        String dname = sc.nextLine();
                        system.registerDoctor(dname);
                        break;

                    case 3:
                        System.out.print("Enter Numeric Patient ID (digits only, without 'P'): ");
                        String bid = sc.nextLine();
                        if (!bid.matches("\\d+")) {
                            System.out.println("Invalid ID: Must contain only numbers.");
                            break;
                        }
                        Patient p = system.findPatientById("P" + bid);
                        if (p != null) {
                            system.bookAppointment(p);
                        } else {
                            System.out.println("Patient not found.");
                        }
                        break;

                    case 4:
                        system.showAllDoctors();
                        break;

                    case 5:
                        system.showAllAppointments();
                        break;

                    case 6:
                        system.showAllAppointments();
                        System.out.print("Enter appointment index to complete: ");
                        String indexInput = sc.nextLine();
                        try {
                            int idx = Integer.parseInt(indexInput);
                            system.completeAppointment(idx);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid index. Must be a number.");
                        }
                        break;

                    case 7:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 7.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Validation Error: " + e.getMessage());
            } catch (InvalidAppointmentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}