import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentData {

    public static String[] Studentdata() {
        try {
            //Creating the File object
            File file = new File("C:\\Users\\Reema Alsahafy\\Desktop\\project data\\studentdata.txt");
            //Creating a Scanner object
            Scanner sc = null;

            sc = new Scanner(file);

            //StringBuffer to store the contents
            StringBuffer sb = new StringBuffer();
            //Appending each line to the buffer
            while (sc.hasNext()) {
                sb.append(" " + sc.nextLine());
            }
            // Replace the separator # with , Replace the separator $ with \n
           return sb.toString().replaceAll("#",",").replaceAll("\\$","\n").split("\n");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //add id - index
    public static String[] addID(String[]studList){
        for(int i=0;i<studList.length;i++){
            if (i==0){
                studList[i]="id,"+studList[i];
            }else {
                studList[i]=i+","+studList[i];

            }

            System.out.println(studList[i]);
        }
        return studList;
    }

}
