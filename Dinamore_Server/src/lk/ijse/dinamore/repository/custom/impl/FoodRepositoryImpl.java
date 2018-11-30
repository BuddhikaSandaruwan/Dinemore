package lk.ijse.dinamore.repository.custom.impl;

import lk.ijse.dinamore.dto.FoodDTO;
import lk.ijse.dinamore.repository.SuperRepositoryImpl;
import lk.ijse.dinamore.repository.custom.FoodRepository;

public class FoodRepositoryImpl extends SuperRepositoryImpl<FoodDTO,String> implements FoodRepository {

    public FoodRepositoryImpl() {
    }
}
