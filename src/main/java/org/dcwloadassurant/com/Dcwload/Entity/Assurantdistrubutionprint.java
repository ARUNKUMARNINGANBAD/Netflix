package org.dcwloadassurant.com.Dcwload.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Groups_DistributedPrint")
public class Assurantdistrubutionprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "Group_id", nullable = false)
    private Integer groupid;

    @Column(name = "validFromDate")
    private String validFromDate;

    @Column(name = "ValidToDate")
    private String validToDate;

    @Column(name = "PrintFloor", nullable = false, length = 50)
    private String printFloor;

    @Column(name = "SLA", length = 50)
    private String sla;

    @Column(name = "isactive", nullable = false, length = 50)
    private Integer isactive;

    @Column(name = "Modified_By", length = 50)
    private String modified_By;

    @Column(name = "Modified_Datetime", length = 50)
    private String modified_Datetime;


}
