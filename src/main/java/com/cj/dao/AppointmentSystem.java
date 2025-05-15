package com.cj.dao;

import java.util.ArrayList;
import java.util.List;

import com.*;
import com.cj.entity.Appointment;
import com.cj.entity.Doctor;
import com.cj.entity.InvalidAppointmentException;
import com.cj.entity.Patient;

import java.util.*;

public class AppointmentSystem {
	private List<Patient> patients = new ArrayList<Patient>();
	private List<Doctor> doctors = new ArrayList<Doctor>();
	private List<Appointment> appointments = new ArrayList<Appointment>();
	private int patientCounter = 1000;
	private int doctorCounter = 1;

	public void registerPatient(String name) {
		String id = "P" + patientCounter++;
		patients.add(new Patient(id, name));
		System.out.println("Patient registered with ID: " + id);
	}

	public void registerDoctor(String name) {
		String id = "D" + doctorCounter++;
		doctors.add(new Doctor(id, name));
		System.out.println("Doctor registered with ID: " + id);
	}

	public Appointment bookAppointment(Patient patient) throws InvalidAppointmentException {
		for (Doctor doctor : doctors) {
			if (doctor.isAvailable()) {
				Appointment appointment = new Appointment(patient, doctor);
				appointments.add(appointment);
				System.out.println("Appointment booked.");
				return appointment;
			}
		}
		throw new InvalidAppointmentException("No available doctor found.");
	}

	public void completeAppointment(int index) throws InvalidAppointmentException {
		if (index < 0 || index >= appointments.size()) {
			throw new InvalidAppointmentException("Invalid appointment index.");
		}
		Appointment appointment = appointments.get(index);
		if ("Completed".equals(appointment.getStatus())) {
			throw new InvalidAppointmentException("Appointment already completed.");
		}
		appointment.completeAppointment();
		System.out.println("Appointment marked completed.");
	}

	public void showAllDoctors() {
		for (Doctor d : doctors) {
			d.showProfile();
		}
	}

	public void showAllAppointments() {
		for (int i = 0; i < appointments.size(); i++) {
			System.out.println(i + ": " + appointments.get(i).appointmentDetails());
		}
	}

	public Patient findPatientById(String id) {
		for (Patient p : patients) {
			if (p.getId().equals(id)) {
				return p;
			}
		}
		return null;
	}
}
