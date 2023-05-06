package com.udacity.jdnd.course3.critter.shared;

import javassist.tools.web.BadHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UtilService{

    public <T> void validateIdsExist(List<Long> ids, List<T> results) {

        if(ids.size() == 0) {
            throw new BadRequestException();
        }

        if(results.size() != ids.size()) {
            throw new ResourceNotFoundException("one or more Ids are not valid");
        }
    }

}
