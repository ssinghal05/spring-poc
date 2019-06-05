package io.pivotal.rest.springpoc.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@ApiModel("User information")
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should be minimum of 2 characters")
    @ApiModelProperty("User name")
    private String name;

    @Past
    @ApiModelProperty("User date of Birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

}
