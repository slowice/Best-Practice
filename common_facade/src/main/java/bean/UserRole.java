package bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable {
    private String idUserRole;
    private String userId;
    private String roleName;
}

