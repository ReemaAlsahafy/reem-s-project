import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static  Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException {



    showhome();


    }
    public static void showactions(JSONObject jsonObject,int StudentId,String[]courses){
        System.out.println("\n Please choose from the following:");
        System.out.println("a - Enroll in a course");
        System.out.println("d - Un-enroll from an existing course");
        System.out.println("r - Replacing an existing course");
        System.out.println("b - Back to the main page");
        System.out.println("Please select the required action:");
        String optionid=scanner.next();
        switch (optionid){
            case "a" :
                if (jsonObject!=null&&(!jsonObject.has(String.valueOf(StudentId))||(jsonObject.has(String.valueOf(StudentId))&&jsonObject.getJSONArray(String.valueOf(StudentId)).length()<6))) {
                    StudentVSCourse.getAvailableCourses(String.valueOf(StudentId), courses, jsonObject);
                    System.out.println("Please enter course id:");

                    int courseid = scanner.nextInt();
                    if (courseid < courses.length) {
                        jsonObject = StudentVSCourse.addcourse(String.valueOf(StudentId), courseid, jsonObject);
                        try {
                            FileWriter file = new FileWriter("C:\\Users\\Reema Alsahafy\\Desktop\\Java project\\Student course details.json");
                            file.write(jsonObject.toString());
                            file.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println("The input you have provided is invalid, please enter a valid input");
                    }
                   } else {
                        System.out.println("The student has a maximum of 6 courses to enroll in");

                    }

                showactions(jsonObject,StudentId,courses);
                break;

            case "d":
                if (jsonObject!=null&&jsonObject.getJSONArray(String.valueOf(StudentId)).length()>0) {
                    StudentVSCourse.getStudentCourses(String.valueOf(StudentId), courses, jsonObject);
                    System.out.println("Please enter course id:");

                    int courseid = scanner.nextInt();
                    jsonObject = StudentVSCourse.removecourse(String.valueOf(StudentId), courseid, jsonObject, courses);
                    try {
                        FileWriter file = new FileWriter("C:\\Users\\Reema Alsahafy\\Desktop\\Java project\\Student course details.json");
                        file.write(jsonObject.toString());
                        file.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else {
                    System.out.println("The student hasn't enrolled in any course yet.");
                }
                showactions(jsonObject,StudentId,courses);
                break;

            case "r" :
                System.out.println("Please enter the course id to be replaced:");

                int courseid=scanner.nextInt();
                if (jsonObject!=null&&jsonObject.getJSONArray(String.valueOf(StudentId)).length()>0) {
                    System.out.println(courses[0]);
                    System.out.println(courses[courseid]);
                    System.out.println("Please enter the required course id to replace:");
                    int rcourseid=Main.scanner.nextInt();
                    for (int i = 0; i<jsonObject.getJSONArray(String.valueOf(StudentId)).length(); i++){
                        if (jsonObject.getJSONArray(String.valueOf(StudentId)).getInt(i)==courseid){
                            jsonObject.getJSONArray(String.valueOf(StudentId)).put(i,rcourseid);
                            System.out.println("Courses replaced successfully from the"+ courses[courseid].split(",")[1].trim()+ "course to"+ courses[rcourseid].split(",")[1].trim()+ "course");
                            break;
                        }
                    }
                    try {
                        FileWriter file = new FileWriter("C:\\Users\\Reema Alsahafy\\Desktop\\Java project\\Student course details.json");
                        file.write(jsonObject.toString());
                        file.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                }else {
                    System.out.println("The student hasn't enrolled in any course yet.");
                }
                Main.drawline('=');
                System.out.println("\n Student Details page");
                Main.drawline('=');
                String[] data=   StudentData.Studentdata();
                String[] student=data[StudentId].split(",");
                System.out.println("\n Name:"+student[1]+"\t Grade:"+student[2]+" \t Email:"+student[3]);
                Main.drawline('-');


                System.out.println("\n Enrolled courses.");
                if (jsonObject!=null&&jsonObject.has(String.valueOf(StudentId))) {
                    System.out.println(courses[0]);
                    for (int j = 0; j < jsonObject.getJSONArray(String.valueOf(StudentId)).length(); j++) {
                        System.out.println(j + 1 + "- " + courses[jsonObject.getJSONArray(String.valueOf(StudentId)).getInt(j)]);
                    }
                }
                showactions(jsonObject,StudentId,courses);
                break;

            case "b":
                showhome();
                break;

            default: showactions(jsonObject,StudentId,courses);
            break;

        }

    }

    public static void showhome(){
        System.out.println("Welcome to LMS");
        System.out.println("Created by Reem Alsahafy _ 24 Jan 2023");
        drawline('*');
        System.out.println("\n Home page");
        drawline('*');
        System.out.println("\n Student list:");
        String[] data=   StudentData.Studentdata();
        data = StudentData.addID(data);

        drawline('-');
        System.out.println("\n Please select the required student:");

        int StudentId = scanner.nextInt();


        drawline('=');
        System.out.println("\n Student Details page");
        drawline('=');
        if (StudentId>=data.length){
            System.out.println("\n The input you have provided is invalid, please enter a valid input ");
            showhome();
        }else {
            String[] student = data[StudentId].split(",");
            System.out.println("\n Name:" + student[1] + "\t Grade:" + student[2] + " \t Email:" + student[3]);
            drawline('-');
            String[] courses = CoursesData.Coursedata();
            JSONObject jsonObject = StudentVSCourse.StudentVScourse();
            System.out.println("\n Enrolled courses.");
            if (jsonObject.has(String.valueOf(StudentId))) {
                System.out.println(courses[0]);
                for (int j = 0; j < jsonObject.getJSONArray(String.valueOf(StudentId)).length(); j++) {
                    System.out.println(j + 1 + "- " + courses[jsonObject.getJSONArray(String.valueOf(StudentId)).getInt(j)]);
                }
            } else {
                System.out.println("The student hasn't enrolled in any course yet.");

            }
            drawline('-');
            showactions(jsonObject, StudentId, courses);
        }
    }
    public static void drawline(char ch){
        for (int i=0; i<100;i++ ){
            System.out.print(ch);
        }
    }


    }






