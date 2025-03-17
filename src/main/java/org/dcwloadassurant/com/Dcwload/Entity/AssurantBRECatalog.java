package org.dcwloadassurant.com.Dcwload.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Assurant_BRE_Catalog")
public class AssurantBRECatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "code", unique = true,nullable = false)
    private String code;

    @Column(name = "description", nullable = false, length = 50)
    private String description;

}
