package com.covid19.dao;

import com.covid19.dao.impl.SpringReviewDao;
import com.covid19.dao.impl.SpringStructureDao;
import com.covid19.dao.impl.SpringUserDao;

public class SpringDaoFactory extends DaoFactory {

    @Override
    public UserDao getUserDao() {
        return SpringUserDao.getInstance();
    }

    @Override
    public StructureDao getStructureDao() {
        return SpringStructureDao.getInstance();
    }

    @Override
    public ReviewDao getReviewDao() {
        return SpringReviewDao.getInstance();
    }

}
