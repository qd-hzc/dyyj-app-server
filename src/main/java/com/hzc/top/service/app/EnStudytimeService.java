package com.hzc.top.service.app;

import com.hzc.top.model.EnStudytime;
import com.hzc.top.util.factory.alias.D;
import com.hzc.framework.ssh.service.Transaction;

/**
 * Created by yinbin on 2015/3/30.
 */
@Transaction
public class EnStudytimeService {

    /**
     * 保存用户在某个节中停留的时间
     *
     * @param studytime
     * @return
     */
    public boolean save(EnStudytime studytime) {
        int i = D.enStudytimeMapper().insertSelective(studytime);
        return i == 1;
    }

}
