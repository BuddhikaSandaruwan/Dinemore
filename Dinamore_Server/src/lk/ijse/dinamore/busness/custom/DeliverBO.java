package lk.ijse.dinamore.busness.custom;

import lk.ijse.dinamore.busness.SuperBO;
import lk.ijse.dinamore.dto.DeliverDTO;

import java.util.List;

public interface DeliverBO extends SuperBO {
    public boolean addDeliver(DeliverDTO deliverDTO)throws Exception;

    public boolean updateDeliver(DeliverDTO deliverDTO)throws Exception;

    public boolean deleteDeliver(String id)throws Exception;

    public DeliverDTO searchDelivers(int id)throws Exception;

    public List<DeliverDTO> getAllDelivers()throws Exception;
}
