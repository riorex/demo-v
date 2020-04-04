package com.demo.v.service;

import com.demo.v.model.Client;
import com.demo.v.repository.ClientRepo;
import com.demo.v.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ClientService {

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    DocumentRepo documentRepo;

    public Client findByClientId(Long clientId) {
        if (null != clientId) {
            return clientRepo.findById(clientId).isPresent() ? clientRepo.findById(clientId).get() : null;
        }

        return null;
    }

    public Client findByInn(Long inn) {
        return clientRepo.findByInn(inn);
    }

    public Client findByOgrn(Long ogrn) {
        return clientRepo.findByOgrn(ogrn);
    }

    public Client findByFullName(String fullName) {
        return clientRepo.findByFullName(fullName);
    }

    public List<Client> findByCrDate(Date crDate) {
        return clientRepo.findByCreateDate(crDate);
    }


    public Client createOrUpdateClient(final Client client) {
        if (null == findByClientId(client.getClientId())) {
            clientRepo.save(client);
            return client;
        } else {
            Client updClient = findByClientId(client.getClientId());
            updClient.setFullName(client.getFullName());
            updClient.setInn(client.getInn());
            updClient.setOgrn(client.getOgrn());
            updClient.setCreateDate(client.getCreateDate());
            updClient.setUpdateDate(new Date());

            updClient = clientRepo.save(updClient);

            return updClient;
        }

    }

    public Long deleteClient(Long clientId) {
        if (null != findByClientId(clientId) && !isDocPresent(clientId)) {
            clientRepo.deleteById(clientId);
            return clientId;
        }
        return -1L;
    }


    private boolean isDocPresent(Long clientId) {
        return null != documentRepo.findByClientId(clientId);
    }
}
