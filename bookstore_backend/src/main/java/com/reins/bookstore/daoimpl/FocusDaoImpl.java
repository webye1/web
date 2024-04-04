package com.reins.bookstore.daoimpl;
import com.reins.bookstore.dao.FocusDao;
import com.reins.bookstore.entity.Focus;

import com.reins.bookstore.repository.FocusRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FocusDaoImpl implements FocusDao {
    private final FocusRepository focusRepository;

    public FocusDaoImpl(FocusRepository focusRepository) {
        this.focusRepository = focusRepository;
    }
    public List<Focus> getAllFocus(){
        List<Focus> focus = focusRepository.getAllFocus();
        return focus;
    }

    @Override
    public Focus insertOneFocus(Focus focus) {
        System.out.println("focusDaoinsert");
        try {
            return focusRepository.save(focus);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Focus updateOneFocus(Focus focus) {
        System.out.println("focusDaoupdate");
        Focus focus1 = focusRepository.save(focus);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return focus1;
    }

    @Override
    public Focus getOneFocus(Integer id) {
        System.out.println("focusDaogetone");
        Focus focus1 = focusRepository.getOne(id);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return focus1;
    }

    @Override
    public void deleteOneFocus(Integer id) throws IllegalArgumentException {
        focusRepository.deleteById(id);
    }

    @Override
    public Focus getOneFocus(Integer userid, String trackingnum) {
        Focus focus1 = focusRepository.getOnefocus(userid,trackingnum);//insertOneFocus(focus.getUserid(),focus.getTrackingnum());
        return focus1;
    }

    @Override
    public List<Focus> getUnionFocus(Integer unionId) {
        List<Focus> focuslist = focusRepository.getUnionFocus( unionId);
        return focuslist;
    }


}
