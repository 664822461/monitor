package com.maigechuang.service.jkimpl;


import com.maigechuang.mapper.caiji.ProjectsSourceMapper;
import com.maigechuang.service.ProjectsSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectsSourceServiceImpl implements ProjectsSourceService{

    @Autowired
    ProjectsSourceMapper projectsSourceMapper;

    @Override
    public int findCountByName(String name, String startTime,String endTime) {

        return projectsSourceMapper.findCountByName(name, startTime,endTime);
    }

    @Override
    public int findyesData(String startTime,String endTime) {
        return projectsSourceMapper.findyesData(startTime,endTime);
    }

    @Override
    public int beforeyesData(String startTime,String endTime) {
        return projectsSourceMapper.beforeyesData(startTime,endTime);
    }

    @Override
    public int findeyesfvData(String startTime,String endTime) {
        return projectsSourceMapper.findeyesfvData(startTime,endTime);
    }

    @Override
    public int beforeyesefvData(String startTime,String endTime) {
        return projectsSourceMapper.beforeyesefvData(startTime,endTime);
    }
}
