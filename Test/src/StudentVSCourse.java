import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StudentVSCourse {
    public  static JSONObject StudentVScourse() {
        try {
            //Creating the File object
            File file = new File("C:\\Users\\Reema Alsahafy\\Desktop\\Java project\\Student course details.json");
            //Creating a Scanner object
            Scanner sc = null;

            sc = new Scanner(file);

            //StringBuffer to store the contents
            StringBuffer sb = new StringBuffer();
            //Appending each line to the buffer
            while (sc.hasNext()) {
                sb.append(" " + sc.nextLine());
            }
            //get data from file as strings and make it objects
            return new JSONObject(sb.toString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getAvailableCourses(String StudentX, String[]CoursesX,JSONObject JSONObjectX ){
        if (JSONObjectX!=null&&JSONObjectX.has(StudentX)){
            JSONArray jsonArray=JSONObjectX.getJSONArray(StudentX);
            for (int i=0;i<CoursesX.length;i++){

                boolean check=false;
                if (i>0) {
                    for (int c = 0; c < jsonArray.length(); c++) {
                        if (CoursesX[i].split(",")[0].contains(String.valueOf(jsonArray.getInt(c)))) {
                            check = true;
                            break;
                        }
                    }
                }
              if (!check){
                  System.out.println(CoursesX[i]);
              }

            }
        }else {
            for (int i=0;i<CoursesX.length;i++){
                System.out.println(CoursesX[i]);
            }
        }

    }
    public static void getStudentCourses(String StudentX, String[]CoursesX,JSONObject JSONObjectX ){
        if (JSONObjectX!=null&&JSONObjectX.has(StudentX)){
            JSONArray jsonArray=JSONObjectX.getJSONArray(StudentX);
            System.out.println(CoursesX[0]);

                    for (int c = 0; c < jsonArray.length(); c++) {
                          System.out.println(CoursesX[jsonArray.getInt(c)]);
                }



        }

    }
    //enroll from course
    public static JSONObject addcourse(String studentid,int courseid,JSONObject jsonObject){
        if (jsonObject.has(studentid)){


           boolean check=false;
           for (int i=0;i<jsonObject.getJSONArray(studentid).length();i++){
               if (jsonObject.getJSONArray(studentid).getInt(i)==courseid){
                   check=true;
               }
           }
           if (!check){
               jsonObject.getJSONArray(studentid).put(courseid);
               System.out.println("Student is Enrolled Successfully");
           }else {
               System.out.println("Students can’t enroll in the same course many times");
           }
        }else {

            JSONArray jsonArray=new JSONArray();
            jsonArray.put(courseid);
            jsonObject.put(studentid,jsonArray);
            System.out.println("Student is Enrolled Successfully");
        }


        return jsonObject;

    }
    //un-enroll from course
    public static JSONObject removecourse(String studentid,int courseid,JSONObject jsonObject,String[]course){
        if (jsonObject.has(studentid)){
            if (jsonObject.getJSONArray(studentid).length()<=1){
                System.out.println("\nFailed to un-enroll: The student has only one or no courses to un-enroll from");
            }else {
                for (int i = 0; i < jsonObject.getJSONArray(studentid).length(); i++) {
                    if (jsonObject.getJSONArray(studentid).getInt(i) == courseid) {
                        jsonObject.getJSONArray(studentid).remove(i);
                        System.out.println("Un-enrolled successfully from the "+course[courseid].split(",")[1]+"course");
                        break;
                    }
                }

            }
        }else {
            System.out.println("\nFaild to un-enroll: The student has only one or no courses to un-enroll from");

        }

        return jsonObject;

    }




}
