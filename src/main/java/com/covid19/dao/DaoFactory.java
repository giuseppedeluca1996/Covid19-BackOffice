package com.covid19.dao;

import com.covid19.model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public abstract class  DaoFactory {

    static {
        FileReader reader= null;
        try {
            ClassLoader classLoader= ClassLoader.getSystemClassLoader();
            reader = new FileReader(Objects.requireNonNull(classLoader.getResource("application.properties")).getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties p=new Properties();
        try {
            p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        daoCurrentType=p.getProperty("persistence");
    }

    private static String daoCurrentType;

    public abstract UserDao getUserDao();

    public abstract StructureDao getStructureDao();

    public abstract ReviewDao getReviewDao();

    public static String getDaoCurrentType() {
        return daoCurrentType;
    }

    public static void setDaoCurrentType(String daoCurrentType) {
        DaoFactory.daoCurrentType = daoCurrentType;
    }

    public static DaoFactory getDaoFactory(){

        switch (daoCurrentType) {
            case "Spring" -> {
                return new SpringDaoFactory();
            }
            default -> {
                return null;
            }
        }


    }



}
