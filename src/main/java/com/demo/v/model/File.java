package com.demo.v.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "FILE")
public class File {

    private static final long serialVersionUID = -5967862371341680946L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "file_id", nullable = false)
    private Long fileId;

    @Column(name = "content", nullable = false)
    private String content;
}
