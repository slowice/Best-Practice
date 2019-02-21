package xb.data_process_demo.bean;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ClassRoom {
    private String name;
    private List<Student> student;
}
