package lk.ijse.dinamore.busness.custom;

import lk.ijse.dinamore.busness.SuperBO;
import lk.ijse.dinamore.dto.FoodDTO;

import java.util.List;

public interface FoodBO extends SuperBO {
    public boolean addFood(FoodDTO foodDTO)throws Exception;

    public boolean updateFood(FoodDTO foodDTO)throws Exception;

    public boolean deleteFood(String id)throws Exception;

    public FoodDTO searchFood(int id)throws Exception;

    public List<FoodDTO> getAllFoods()throws Exception;
}
