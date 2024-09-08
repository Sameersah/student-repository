package com.sameer.sjsu_student_information.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private Map<String, String> studentPhoneMap = new HashMap<>();

    public StudentService() {
        loadStudentData();
        
    }

    private void loadStudentData() {
        String line;
        String csvSplitBy = ",";
        int lineCount = 0;

        logger.info("Starting to load student data from CSV file");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("data.csv").getInputStream()))) {
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                lineCount++;
                String[] data = line.split(csvSplitBy);
                if (data.length >= 2) {
                    
                    String studentName = data[3].trim();
                    String studentPhone = data[4].trim();
                    studentPhoneMap.put(studentName, studentPhone);
                    logger.debug("Loaded student: {} with phone: {}", studentName, studentPhone);
                } else {
                    logger.warn("Invalid data format at line {}: {}", lineCount, line);
                }
            }
            logger.info("Finished loading student data. Total records loaded: {}", studentPhoneMap.size());
        } catch (IOException e) {
            logger.error("Error reading CSV file", e);
        }
    }

    public String getStudentPhoneNumber(String name) {
        logger.debug("Searching for student: {}", name);
        String lowercaseName = name.toLowerCase();
        Optional<Map.Entry<String, String>> matchingEntry = studentPhoneMap.entrySet().stream()
            .filter(entry -> entry.getKey().toLowerCase().contains(lowercaseName))
            .findFirst();

        if (matchingEntry.isPresent()) {
            String foundName = matchingEntry.get().getKey();
            String phoneNumber = matchingEntry.get().getValue();
            logger.info("Found matching student: {} with phone number: {}", foundName, phoneNumber);
            return phoneNumber;
        } else {
            logger.warn("No matching student found for: {}", name);
            return "Student not found";
        }
    }


}