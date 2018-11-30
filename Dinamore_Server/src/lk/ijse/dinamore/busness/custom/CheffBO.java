package lk.ijse.dinamore.busness.custom;

import lk.ijse.dinamore.busness.SuperBO;
import lk.ijse.dinamore.dto.CheffDTO;

import java.util.List;

public interface CheffBO extends SuperBO {
    public boolean addCheff(CheffDTO cheffDTO)throws Exception;

    public boolean updateCheff(CheffDTO cheffDTO)throws Exception;

    public boolean deleteCheff(String id)throws Exception;

    public CheffDTO searchCheff(int id)throws Exception;

    public List<CheffDTO> getAllCheffs()throws Exception;
}
