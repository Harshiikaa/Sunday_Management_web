package com.system.sunday_management.pojo;

import com.system.sunday_management.model.Notice;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class NoticePojo {
        private Integer id;
        @NotEmpty(message = "title can't be empty")
        private String title;
        @NotEmpty(message = "description can't be empty")
        private String description;

        public NoticePojo(Notice notice){
            this.id= notice.getId();
            this.title= notice.getTitle();
            this.description= notice.getDescription();

        }

}
