package com.system.sunday_management.pojo;
import com.system.sunday_management.model.Leave;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeavePojo {
    private Integer id;
    @NotEmpty(message = "from date can't be empty")
    private String fromDate;
    @NotEmpty(message = "to date can't be empty")
    private String toDate;
    @NotEmpty(message = "subject can't be empty")
    private String subject;
    @NotEmpty(message = "description can't be empty")
    private String description;



    public LeavePojo(Leave leave){
        this.id= leave.getId();
        this.fromDate= leave.getFromDate();
        this.toDate= leave.getToDate();
        this.subject= leave.getSubject();
        this.description= leave.getDescription();

    }

}
