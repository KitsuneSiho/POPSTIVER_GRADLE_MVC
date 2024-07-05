package kr.bit.function.admin.service;

import kr.bit.function.admin.dao.BusinessContentsDAO;
import kr.bit.function.admin.model.businessContents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessContentsService {

    private final BusinessContentsDAO businessContentsDAO;

    @Autowired
    public BusinessContentsService(BusinessContentsDAO businessContentsDAO) {
        this.businessContentsDAO = businessContentsDAO;
    }

    public List<businessContents> getAllBusinessContents() {
        return businessContentsDAO.getAllBusinessContents();
    }

    public List<businessContents> getBusinessContentsFromLastWeek() {
        return businessContentsDAO.getBusinessContentsFromLastWeek();
    }

    public int getTotalBusinessContents() {
        return businessContentsDAO.getTotalBusinessContents();
    }

    public List<businessContents> getBusinessContentsByPage(int page, int size) {
        int offset = (page - 1) * size;
        return businessContentsDAO.getBusinessContentsByPage(offset, size);
    }
}
