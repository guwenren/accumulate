package com.guwr.accumulate.service.authority.facade;

import com.guwr.accumulate.facade.authority.entity.Role;
import com.guwr.accumulate.facade.authority.entity.User;
import com.guwr.accumulate.facade.authority.exception.AuthorityException;
import com.guwr.accumulate.facade.authority.facade.IRoleFacade;
import com.guwr.accumulate.facade.authority.facade.IUserFacade;
import com.guwr.accumulate.facade.authority.vo.UserVO;
import com.guwr.accumulate.service.authority.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by guwr
 * Project_name accumulate
 * Path com.guwr.accumulate.service.authority.facade.RoleFacade
 * Date 2017/4/5
 * Time 14:26
 * Description
 */
@Component
public class RoleFacade implements IRoleFacade {


    @Override
    public Set<String> findRoleByUid(int uid) {
        return null;
    }
}
