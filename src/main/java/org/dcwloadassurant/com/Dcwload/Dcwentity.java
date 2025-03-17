package org.dcwloadassurant.com.Dcwload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//id	company_id	group_id	variable_name	variable_value	variable_id

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_variables_groups_values")
public class Dcwentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "company_id")
    private Integer company_id;

    @Column(name = "group_id")
    private Integer group_id;

    @Column(name = "variable_name")
    private String variable_name;

    @Column(name = "variable_value")
    private String variable_value;

    @Column(name = "variable_id")
    private Integer variable_id;


}
