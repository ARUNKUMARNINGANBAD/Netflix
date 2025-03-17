package org.dcwloadassurant.com.Dcwload.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Assurant_ExceptionList")
public class ExceptionList_Unbundlejob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "flexjobname")
    private String flexjobname;

    @Column(name = "comments")
    private String comments;
}
