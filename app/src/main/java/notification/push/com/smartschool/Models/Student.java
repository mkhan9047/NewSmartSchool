package notification.push.com.smartschool.Models;

import java.io.Serializable;

/**
 * Created by Mujahid on 5/22/2018.
 */

public class Student implements Serializable {
    private String student_name;
    private String student_id;
    private String father_name;
    private String gender;
    private String present_address;
    private int bus_service;
    private String class_name;
    private String student_roll;
    private String student_photo;
    private String contact_number;
    private String section;


    public Student(String section, String student_roll, String contact_number,String student_name, String student_id, String father_name, String gender, String present_address, int bus_service, String class_name, String student_photo) {
        this.section = section;
        this.contact_number = contact_number;
        this.student_name = student_name;
        this.student_id = student_id;
        this.father_name = father_name;
        this.gender = gender;
        this.present_address = present_address;
        this.bus_service = bus_service;
        this.class_name = class_name;
        this.student_photo = student_photo;
        this.student_roll = student_roll;
    }

    public String getSection() {
        return section;
    }

    public String getStudent_roll() {
        return student_roll;
    }

    public String  getContact_number(){
        return  contact_number;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getGender() {
        return gender;
    }

    public String getPresent_address() {
        return present_address;
    }

    public int getBus_service() {
        return bus_service;
    }

    public String getClass_name() {
        return class_name;
    }

    public String getStudent_photo() {
        return student_photo;
    }
}
