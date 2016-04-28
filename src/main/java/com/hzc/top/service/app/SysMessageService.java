package com.hzc.top.service.app;

import com.hzc.framework.ssh.service.Transaction;
import com.hzc.top.model.SysMessage;
import com.hzc.top.util.factory.alias.D;
import com.hzc.top.vo.ResultVO;

/**
 * Created by LiuJY on 2015/4/6.
 */

@Transaction
public class SysMessageService {

    /**
     * 保存用户接受的推送消息
     * @param sysMessage
     * @return
     */
    public ResultVO saveSysMessage(SysMessage sysMessage) {
        D.sysMessageMapper().insertSelective(sysMessage);
        return new ResultVO(true,"保存成功");
    }
}
