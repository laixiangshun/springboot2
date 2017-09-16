package com.lai.dao;

import com.lai.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by lailai on 2017/9/15.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User>{
    User findByName(String name);

    User findByNameAndAge(String name,Integer age);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
}
