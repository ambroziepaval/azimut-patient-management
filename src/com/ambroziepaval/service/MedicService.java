package com.ambroziepaval.service;

import com.ambroziepaval.dao.MedicDao;
import com.ambroziepaval.model.Medic;

import java.util.List;

public class MedicService {

    private final MedicDao medicDao;

    public MedicService() {
        this.medicDao = new MedicDao();
    }

    public List<Medic> getAllMedics() {
        return medicDao.findAll();
    }
}
