package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.FocusDao;
import com.reins.bookstore.dao.WxuserDao;
import com.reins.bookstore.entity.Focus;
import com.reins.bookstore.entity.Wxuser;
import com.reins.bookstore.service.WxuserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
@CacheConfig(cacheNames = {"lastResult"})
public class WxuserServiceImpl implements WxuserService {
    private final WxuserDao wxuserDao;


    public WxuserServiceImpl(WxuserDao wxuserDao) {
        this.wxuserDao = wxuserDao;
    }

    @Override
    public List<Wxuser> getAllWxuser() {
        List<Wxuser> wxusers = wxuserDao.getAllWxuser();
        return wxusers;
    }

    @Override
    public Wxuser getOneWxuser(String id) {
        System.out.println("Wxuserservicegetone");
        Wxuser wxuser = wxuserDao.getOneWxuser(id);
        return wxuser;
    }

    @Override
    public Wxuser insertOneWxuser(Wxuser wxuser) {
        System.out.println("wxuserserviceinsert");
        Wxuser wxuser1 = wxuserDao.insertOneWxuser(wxuser);
        return wxuser1;
    }

    @Override
    public Wxuser updateOneWxuser(Wxuser wxuser) {
        System.out.println("wxuserserviceupdate");
        Wxuser wxuser1 = wxuserDao.updateOneWxuser(wxuser);
        return wxuser1;
    }

    @Override
    public boolean deleteOneWxuser(int id) {
        System.out.println("wxuserservicedeleteone");
        try {
            wxuserDao.deleteOneWxuser(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
