package com.demo.v.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "DOCUMENT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Document {

    private static final long serialVersionUID = -2669676881426294849L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "document_id", nullable = false)
    private Long documentId;

    @Column(name ="client_id", nullable = false)
    private Long clientId;

    @ManyToOne
    @JoinTable(name = "client", joinColumns = @JoinColumn(name = "client_id"))
    private Client client;

    @Column(name ="file_id", nullable = false)
    private Long fileId;

    @OneToOne
    private File file;

    @Column
    private long docNum;

    @Column(name ="user_id", nullable = false)
    private Long userId;

    @ManyToOne
    private User user;


    @Temporal(TemporalType.DATE)
    @Column(name = "create_date", nullable = false)
    private Date createDate;


}
