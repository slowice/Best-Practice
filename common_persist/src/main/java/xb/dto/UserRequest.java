package xb.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private String id;

    private List<String> typeList;

    private String name;
}
