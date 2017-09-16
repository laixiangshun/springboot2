package com.lai.domain.secondary;

import com.lai.model.secondary.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lailai on 2017/9/16.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,Long>{

}
