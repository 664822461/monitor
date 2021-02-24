package com.maigechuang.service;

public interface ProjectsSourceService {

    int findCountByName(String name,String startTime,String endTime);

    int findyesData(String startTime,String endTime);
    int beforeyesData(String startTime,String endTime);

    int findeyesfvData(String startTime,String endTime);
    int beforeyesefvData(String startTime,String endTime);

}
