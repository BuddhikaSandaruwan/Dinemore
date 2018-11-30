package lk.ijse.dinamore.busness.custom.impl;

import lk.ijse.dinamore.busness.custom.ReseptionBO;
import lk.ijse.dinamore.dto.CheffDTO;
import lk.ijse.dinamore.dto.FoodDTO;
import lk.ijse.dinamore.dto.ReseptionDTO;
import lk.ijse.dinamore.password.PasswordUtil;
import lk.ijse.dinamore.repository.RepositoryFactory;
import lk.ijse.dinamore.repository.custom.CheffRepository;
import lk.ijse.dinamore.repository.custom.ReseptionRepository;
import lk.ijse.dinamore.util.HibanateUtil;
import org.hibernate.Session;

import java.util.List;

public class ReseptionBOImpl implements ReseptionBO {
    public ReseptionBOImpl() {
        this.reseptionRepository = (ReseptionRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.RESEPTION);
    }

    private ReseptionRepository reseptionRepository;
    @Override
    public boolean addReseption(ReseptionDTO reseptionDTO) throws Exception {
        System.out.println(reseptionDTO);
        Session session=HibanateUtil.getSessionFactory().openSession();

        reseptionRepository.setSession(session);

        session.beginTransaction();

        String userName=reseptionDTO.getUserName();
        String password=reseptionDTO.getPassword();
        String salt= PasswordUtil.getSalt(30);
        String secPw=PasswordUtil.generateSecurePassword(password, salt);

        ReseptionDTO reseptionDTO1=new ReseptionDTO(
                reseptionDTO.getReseptionNIC(),
                reseptionDTO.getName(),
                reseptionDTO.getAdress(),
                reseptionDTO.getTp(),
                userName,
                secPw,
                salt);
        boolean result=reseptionRepository.save(reseptionDTO1);

        session.getTransaction().commit();
        return result;
    }

    @Override
    public boolean updateReseption(ReseptionDTO reseptionDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteReseption(String id) throws Exception {
        return false;
    }

    @Override
    public ReseptionDTO searchReseption(int id) throws Exception {
        Session session=HibanateUtil.getSessionFactory().openSession();

        reseptionRepository.setSession(session);

        session.beginTransaction();



        ReseptionDTO result=reseptionRepository.findById(id);

        session.getTransaction().commit();
        return result;
    }

    @Override
    public List<ReseptionDTO> getAllReseptions() throws Exception {
        Session session = HibanateUtil.getSessionFactory().openSession();

        reseptionRepository.setSession(session);

        session.beginTransaction();

        List<ReseptionDTO> all = reseptionRepository.findAll();

        session.getTransaction().commit();

        if (all != null) {
            return all;

        } else {

            return null;
        }
    }
}
