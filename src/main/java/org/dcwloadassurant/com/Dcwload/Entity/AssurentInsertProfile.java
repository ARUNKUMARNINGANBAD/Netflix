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
@Table(name = "Assurant_Insert_Profile")
public class AssurentInsertProfile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Assurant_Insert_Profile_Id", nullable = false)
    private Integer Assurant_Insert_Profile_Id;

    @Column(name = "company_id", nullable = false)
    private Integer companyId;

    @Column(name = "correl", unique = true,nullable = false)
    private Integer profilecorrel;

    @Column(name = "location", nullable = false, length = 50)
    private String location;

    @Column(name = "Type", nullable = false, length = 50)
    private String type;

    @Column(name = "insertStockNumber", length = 50)
    private String insertStockNumber;

    @Column(name = "Size", nullable = false, length = 50)
    private String size;

    @Column(name = "InsertType", length = 50)
    private String insertType;

    @Column(name = "AnnualUssage", length = 50)
    private String annualUssage;

    @Column(name = "comments", length = 50)
    private String comments;

    @Column(name = "comments2", length = 200)
    private String comments2;

    @Column(name = "NewinsertStockNumber", length = 200)
    private String newinsertStockNumber;

    @Column(name = "`INSERT Selective`", length = 50)
    private String insertSelective;
}
