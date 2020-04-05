package com.demo.v.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CLIENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client implements Serializable {

    private static final long serialVersionUID = -1962988863889356095L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "inn", nullable = false)
    private long inn;

    @Column(name = "ogrn", nullable = false)
    private long ogrn;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_date", nullable = false)
    private Date updateDate;

}
