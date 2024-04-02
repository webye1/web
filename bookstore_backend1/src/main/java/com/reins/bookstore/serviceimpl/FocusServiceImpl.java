package com.reins.bookstore.serviceimpl;


import com.reins.bookstore.dao.FocusDao;

import com.reins.bookstore.entity.Focus;


import com.reins.bookstore.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"lastResult"})
public class FocusServiceImpl implements FocusService {

    private final FocusDao focusDao;

    public FocusServiceImpl(FocusDao focusDao){
        this.focusDao = focusDao;
    }
    public List<Focus> getAllFocus(){
        List<Focus> focus = focusDao.getAllFocus();
        return focus;
    }

    @Override
    public Focus insertOneFocus(Focus focus) {
        System.out.println("focusserviceinsert");
        Focus focus1 = focusDao.insertOneFocus(focus);
        return focus1;
    }

    @Override
    public Focus updateOneFocus(Focus focus) {
        System.out.println("focusserviceupdate");
        Focus focus1 = focusDao.updateOneFocus(focus);
        return focus1;
    }

    @Override
    public Focus getOneFocus(Integer id) {
        System.out.println("focusservicegetone");
        Focus focus1 = focusDao.getOneFocus(id);
        return focus1;

    }

    @Override
    public boolean deleteOneFocus(Integer id) {
        System.out.println("focusservicedeleteone");
        try {
            focusDao.deleteOneFocus(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Focus getOneFocus(Integer userid, String trackingnum) {
        Focus focus1 = focusDao.getOneFocus(userid,trackingnum);
        return focus1;
    }


}
