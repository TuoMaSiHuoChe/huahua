package com.huahua.user.service;

import com.huahua.user.dao.UserDao;
import com.huahua.user.pojo.User;
import huahua.until.IdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 服务层
 *
 * @author Administrator
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }


    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<User> findSearch(Map whereMap, int page, int size) {
        Specification<User> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return userDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<User> findSearch(Map whereMap) {
        Specification<User> specification = createSpecification(whereMap);
        return userDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public User findById(String id) {
        return userDao.findOneById(id);
    }

    /**
     * 增加
     *
     * @param user
     */
    public void add(User user) {
        user.setId(idWorker.nextId() + "");
        userDao.save(user);
    }

    /**
     * 修改
     *
     * @param user
     */
    public void update(User user) {
        userDao.save(user);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<User> createSpecification(final Map searchMap) {

        return new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 手机号码
                if (searchMap.get("mobile") != null && !"".equals(searchMap.get("mobile"))) {
                    predicateList.add(cb.like(root.get("mobile").as(String.class), "%" + (String) searchMap.get("mobile") + "%"));
                }
                // 密码
                if (searchMap.get("password") != null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(cb.like(root.get("password").as(String.class), "%" + (String) searchMap.get("password") + "%"));
                }
                // 昵称
                if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
                    predicateList.add(cb.like(root.get("nickname").as(String.class), "%" + (String) searchMap.get("nickname") + "%"));
                }
                // 性别
                if (searchMap.get("sex") != null && !"".equals(searchMap.get("sex"))) {
                    predicateList.add(cb.like(root.get("sex").as(String.class), "%" + (String) searchMap.get("sex") + "%"));
                }
                // 头像
                if (searchMap.get("avatar") != null && !"".equals(searchMap.get("avatar"))) {
                    predicateList.add(cb.like(root.get("avatar").as(String.class), "%" + (String) searchMap.get("avatar") + "%"));
                }
                // E-Mail
                if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
                    predicateList.add(cb.like(root.get("email").as(String.class), "%" + (String) searchMap.get("email") + "%"));
                }
                // 兴趣
                if (searchMap.get("interest") != null && !"".equals(searchMap.get("interest"))) {
                    predicateList.add(cb.like(root.get("interest").as(String.class), "%" + (String) searchMap.get("interest") + "%"));
                }
                // 个性
                if (searchMap.get("personality") != null && !"".equals(searchMap.get("personality"))) {
                    predicateList.add(cb.like(root.get("personality").as(String.class), "%" + (String) searchMap.get("personality") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }


    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送短信验证码
     *
     * @param mobile 手机号
     */
    public void sendSms(String mobile) {
        String code = (long) (Math.random() * 1000000) + "";
        System.out.println("验证码：" + code);
        //五分钟过期
        redisTemplate.opsForValue().set("smscode_" + mobile, code, 5, TimeUnit.MINUTES);
        Map<String, String> map = new HashMap();
        map.put("mobile", mobile);
        map.put("code", code + "");
        if (mobile.indexOf("@") >= 0 || mobile.indexOf("qq.com") >= 0) {
            rabbitTemplate.convertAndSend("email", map);
        } else {
            rabbitTemplate.convertAndSend("sms", map);
        }
    }

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * 增加
     *
     * @param user 用户
     * @param code 用户填写的验证码
     */
    public void add(User user, String code) {
        //判断验证码是否正确
        String syscode = (String) redisTemplate.opsForValue().get("smscode_" + user.getMobile());
        String email = (String) redisTemplate.opsForValue().get("smscode_" + user.getEmail());
        //提取系统正确的验证码
        if (syscode == null && email == null) {
            throw new RuntimeException("请点击获取验证码");
        }
        if (!code.equals(syscode) && !code.equals(email)) {
            throw new RuntimeException("验证码输入不正确");
        }
        if (null != user.getPassword()) {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        user.setId(idWorker.nextId() + "");
        //关注数
        user.setFollowcount(0);
        //粉丝数
        user.setFanscount(0);
        //在线时长
        user.setOnline(0L);
        //注册日期
        user.setRegdate(new Date());
        //更新日期
        user.setUpdatedate(new Date());
        //最后登陆日期
        user.setLastdate(new Date());
        userDao.save(user);
    }

    /**
     * 根据手机号和密码查询用户
     *
     * @param mobile
     * @param password
     * @return
     */
    public User findByMobileAndPassword(String mobile, String password) {
        User user = userDao.findByMobile(mobile);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }


    /**
     * 更新关注数
     * @param x
     */
    @Transactional(rollbackFor = Exception.class)
    public void incFollowcount(String userid,int x){
        userDao.incFollowcount(userid,x);
    }
    @Transactional(rollbackFor = Exception.class)
    public void incFanscount(String userid, int x) {
        userDao.incFanscount(userid,x);
    }
}
