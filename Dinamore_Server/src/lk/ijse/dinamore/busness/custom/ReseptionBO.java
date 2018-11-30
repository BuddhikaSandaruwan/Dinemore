package lk.ijse.dinamore.busness.custom;

import lk.ijse.dinamore.busness.SuperBO;
import lk.ijse.dinamore.dto.ReseptionDTO;

import java.util.List;

public interface ReseptionBO extends SuperBO {
    public boolean addReseption(ReseptionDTO reseptionDTO)throws Exception;

    public boolean updateReseption(ReseptionDTO reseptionDTO)throws Exception;

    public boolean deleteReseption(String id)throws Exception;

    public ReseptionDTO searchReseption(int id)throws Exception;

    public List<ReseptionDTO> getAllReseptions()throws Exception;
}
