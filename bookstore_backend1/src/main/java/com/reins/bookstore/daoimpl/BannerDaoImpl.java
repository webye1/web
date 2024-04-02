package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.BannerDao;
import com.reins.bookstore.entity.Banner;
import com.reins.bookstore.repository.BannerRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BannerDaoImpl implements BannerDao {
    private final BannerRepository bannerRepository;

    public BannerDaoImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public Banner getOne(int count) {
        Banner banner = bannerRepository.getOnebanner(count);
        return banner;
    }

    @Override
    public int getAll() {
        int count = bannerRepository.getAll();
        return count;
    }

    @Override
    public Banner insertOne(Banner banner) {
        System.out.println("id:"+banner.getId()+" url:"+banner.getUrl());
        Integer i  = bannerRepository.insertOne(banner.getUrl());
        System.out.println(i);
        Banner banner1 = new Banner();
        banner1.setId(2);
        banner1.setUrl(banner.getUrl());
        return banner1;
    }

    @Override
    public Banner updateOne(Banner banner) {
        System.out.println("id:"+banner.getId()+" url:"+banner.getUrl());
        Banner banner1 = bannerRepository.save(banner);
        return banner1;
    }

    @Override
    public void deleteOne(int i) throws IllegalArgumentException{
        bannerRepository.deleteById(i);
    }
}
