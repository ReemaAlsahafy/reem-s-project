import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CoursesData {
    public static String[] Coursedata() {
        try {
            //Creating the File object
            File file = new File("C:\\Users\\Reema Alsahafy\\Desktop\\project data\\coursedata.xml");
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
            return sb.toString().replaceAll("\\<\\?xml version=\"1.0\" encoding=\"UTF-8\"\\?\\> ","id, Course Name, Instructor, Course duration, Course time, Location \n")
                    .replaceAll("<root>","").replaceAll("</root>","")
                    .replaceAll("<row>","").replaceAll("</row>","\n")
                    .replaceAll("<id>","").replaceAll("</id>",",")
                    .replaceAll("<Course Name>","").replaceAll("</Course Name>",",")
                    .replaceAll("<Instructor>","").replaceAll("</Instructor>",",")
                    .replaceAll("<Course duration>","").replaceAll("</Course duration>",",")
                    .replaceAll("<Course time>","").replaceAll("</Course time>",",")
                    .replaceAll("<Location>","").replaceAll("</Location>",",").split("\n");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
