package com.lai.domain.primary;

import com.lai.model.primary.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lailai on 2017/9/16.
 */
@Repository
public interface User2Repository extends JpaRepository<User,Long>{

}
