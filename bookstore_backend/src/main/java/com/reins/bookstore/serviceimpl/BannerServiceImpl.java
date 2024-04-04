package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.dao.BannerDao;
import com.reins.bookstore.dao.FocusDao;
import com.reins.bookstore.entity.Banner;
import com.reins.bookstore.service.BannerService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"lastResult"})
public class BannerServiceImpl  implements BannerService {
    private final BannerDao bannerDao;

    public BannerServiceImpl(BannerDao bannerDao){
        this.bannerDao = bannerDao;
    }

    @Override
    public Banner getOne(int count) {
        Banner banner = bannerDao.getOne(count);
        return banner;
    }

    @Override
    public int getAll() {
        int count = bannerDao.getAll();
        return count;
    }

    @Override
    public Banner insertOne(Banner banner) {
        Banner banner1 = bannerDao.insertOne(banner);
        return banner1;
    }

    @Override
    public Banner updateOne(Banner banner) {
        Banner banner1 = bannerDao.updateOne(banner);
        return banner1;
    }

    @Override
    public boolean deleteOne(int i) {
        try {
            bannerDao.deleteOne(i);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
