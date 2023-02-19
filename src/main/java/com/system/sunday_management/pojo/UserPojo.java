package com.system.sunday_management.pojo;

import com.system.sunday_management.model.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    private Integer id;
    @NotEmpty(message = "Full name can't be empty")
    private String fullName;
    @NotEmpty(message = "Email can't be empty")
    private String email;
    @NotEmpty(message = "Mobile Number can't be empty")
    private String mobileNumber;
    @NotEmpty(message = "Password can't be empty")
    private String password;

    private MultipartFile image;

    public UserPojo(User user){
        this.id= user.getId();
        this.email= user.getEmail();
        this.fullName= user.getFullName();
        this.mobileNumber= user.getMobileNo();
        this.password= user.getPassword();
    }
}
