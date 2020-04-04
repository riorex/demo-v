package com.demo.v.model;

import javax.persistence.*;

@Entity
@Table(name = "FILE")
public class File {

    private static final long serialVersionUID = -5967862371341680946L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "file_id", nullable = false)
    private Long fileId;

    private byte[] content;
}
