package com.sailmi.erp.ctrl.bbs;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.sailmi.erp.model.bbs.Post;
import com.sailmi.erp.model.bbs.Topic;
import com.sailmi.interceptor.bbs.LoginInterceptor;
import com.sailmi.validator.bbs.PostValidator;

/**
 * Created with IntelliJ IDEA.
 * Author: StevenChow
 * Date: 13-3-30
 */
public class PostController extends Controller {
    public void index(){
        Long topicID = getParaToLong(0);
        int pageNumber = getParaToInt(1, 1);
        Page<Post> postPage = Post.dao.getPostPage(topicID, pageNumber);
        setAttr("postPage", postPage);
        setAttr("topic", Topic.dao.getTopic(topicID));
        render("/post/post.html");
    }

    @Before({LoginInterceptor.class, PostValidator.class})
    public void save(){
        Post post = getModel(Post.class);
        post.mySave();
        redirect("/post/" + post.getInt("topicID"));
    }

    public void edit(){
        setAttr("post", Post.dao.getPost(getParaToLong(0)));
        render("/post/editPost.html");
    }

    @Before({ PostValidator.class})
    public void update(){
        Post post = getModel(Post.class);
        post.myUpdate();
        redirect("/post/" + post.getInt("topicID"));
    }
}
