package com.lai.web;

import com.lai.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by lailai on 2017/9/15.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    static Map<Integer,User> users= Collections.synchronizedMap(new HashMap<Integer, User>());//创建线程安全的Map

    @ApiOperation(value = "获取用户列表",notes = "")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<User> getUserList(){
        List<User> user=new ArrayList<>(users.values());
        return user;
    }

    @ApiOperation(value = "创建用户",notes = "根据User对象创建用户")
    @ApiImplicitParam(value = "用户详细实体user",name = "user",required = true,dataType = "User")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user){
        users.put(user.getId(),user);
        return "success";
    }

    @ApiOperation(value = "获取用户详细信息",notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Integer")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  User getUser(@PathVariable Integer id){
        return  users.get(id);
    }

    @ApiOperation(value = "更新用户的详细信息",notes = "根据url的id来指定更新对象，并根据传递的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "user",value="用户详细实体user",required = true,dataType = "User")
    })
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  String putUser(@PathVariable Integer id,@ModelAttribute User user){
        User u=users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id,u);
        return "success";
    }

    @ApiOperation(value = "删除用户",notes = "根据url的id指定删除的对象")
    @ApiImplicitParam(name = "id",value = "用户id",required =true,dataType = "Integer")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Integer id){
        users.remove(id);
        return "success";
    }
}
