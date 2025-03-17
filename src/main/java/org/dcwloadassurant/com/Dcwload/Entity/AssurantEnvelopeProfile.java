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
@Table(name = "Assurant_Envelope_Profile")
public class AssurantEnvelopeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Assurant_Envelope_Profile_Id", nullable = false)
    private Integer Assurant_Envelope_Profile_Id;

    @Column(name = "company_id", nullable = false)
    private Integer companyId;

    @Column(name = "correl", unique = true,nullable = false)
    private Integer correl;

    @Column(name = "location", nullable = false, length = 50)
    private String location;

    @Column(name = "EnvelopeStockNumber", nullable = false, length = 50)
    private String envelopeStockNumber;

    @Column(name = "OSE_Or_Remit", length = 50)
    private String oseOrRemit;

    @Column(name = "Size", nullable = false, length = 50)
    private String size;

    @Column(name = "Window", length = 50)
    private String window;

    @Column(name = "Color", length = 50)
    private String color;

    @Column(name = "Open_Window", length = 50)
    private String openWindow;

    @Column(name = "Special_Description", length = 200)
    private String specialDescription;

    @Column(name = "AnnualUssage",columnDefinition = "numeric(7, 0)")
    private BigDecimal annualUssage;

    @Column(name = "comments", length = 100)
    private String comments;

    @Column(name = "comments2", length = 100)
    private String comments2;

//    @Column(name = "\"REMIT Selective\"", length = 30)
//    private String remitSelective;

    @Column(name = "\"REMIT Selective\"", length = 30) // Updated here
    private String remitSelective;

    @Column(name = "NewEnvelopeStockNumber", length = 50)
    private String newEnvelopeStockNumber;
}
