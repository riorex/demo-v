package com.demo.v.repository;

import com.demo.v.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findByInn(long inn);
    Client findByOgrn(long ogrn);
    Client findByFullName(String fullName);
    List<Client> findByCreateDate(Date crDate);
}
