package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.WxuserDao;
import com.reins.bookstore.entity.Focus;
import com.reins.bookstore.entity.Wxuser;
import com.reins.bookstore.repository.FocusRepository;
import com.reins.bookstore.repository.WxuserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WxuserDaoImpl implements WxuserDao {
    private final WxuserRepository wxuserRepository;

    public WxuserDaoImpl(WxuserRepository wxuserRepository) {
        this.wxuserRepository = wxuserRepository;
    }

    @Override
    public List<Wxuser> getAllWxuser() {
        List<Wxuser> wxusers = wxuserRepository.getAllWxuser();
        return wxusers;
    }

    @Override
    public Wxuser getOneWxuser(String id) {
        System.out.println("WxuserDaogetone");
        Wxuser wxuser = wxuserRepository.getWXuser(id);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return wxuser;
    }

    @Override
    public Wxuser insertOneWxuser(Wxuser wxuser) {
        System.out.println("wxuserDaoinsert");
        try {
            System.out.println(wxuser.getOpenid());
            return wxuserRepository.save(wxuser);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Wxuser updateOneWxuser(Wxuser wxuser) {
        System.out.println("wxuserDaoupdate");
        Wxuser wxuser1 = wxuserRepository.save(wxuser);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return wxuser1;
    }

    @Override
    public void deleteOneWxuser(int id) throws IllegalArgumentException{
        wxuserRepository.deleteById(id);
    }
}
