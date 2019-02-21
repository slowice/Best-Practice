package xb.data_process_demo.data_process;

import xb.data_process_demo.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class SteamHelper {
    private static List<Student> students = new ArrayList<>();

    {
        Student u1 = new Student();
        u1.setId("1");
        u1.setName("a");

        Student u2 = new Student();
        u1.setId("2");
        u1.setName("b");

        Student u3 = new Student();
        u1.setId("3");
        u1.setName("c");

        students.add(u1);
        students.add(u2);
        students.add(u3);
    }

}
