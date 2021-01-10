package bean;

import lombok.Data;

import java.io.Serializable;

@Data
/**
 * 用户角色
 */
public class UserRole implements Serializable {
    private String idUserRole;
    private String userId;
    private String roleName;
}

