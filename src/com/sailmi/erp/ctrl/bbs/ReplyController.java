package com.sailmi.erp.ctrl.bbs;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.sailmi.erp.model.bbs.Reply;
import com.sailmi.validator.bbs.ReplyValidator;

/**
 * Created with IntelliJ IDEA.
 * Author: iver
 * Date: 13-4-2
 */
public class ReplyController extends Controller {
    public void index(){
        setAttr("replyPage", Reply.dao.getReplyPage(getParaToLong(0), getParaToInt(1, 1)));
        setAttr("postID", getParaToInt(0));
        render("/reply/_ajaxReply.html");
    }

    @Before(ReplyValidator.class)
    public void save(){
        Reply reply = getModel(Reply.class);
        int postID = reply.getInt("postID");
        reply.mySave(postID);
        forwardAction("/reply/" + postID);
    }

    /* ----------------------admin---------------------- */

    public void delete(){
        int id = getParaToInt(1);
        int postID = getParaToInt(0);
        Reply.dao.deleteByID(id);
        forwardAction("/reply/" + postID);
    }
}
