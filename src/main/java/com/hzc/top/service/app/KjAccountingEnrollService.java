package com.hzc.top.service.app;

import com.hzc.top.model.BizAccountingEnroll;
import com.hzc.top.util.factory.alias.D;
import com.hzc.framework.ssh.repository.mybatis.DataTablePager;
import com.hzc.framework.ssh.service.Transaction;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by felix yin on 2015/3/19.
 */
@Transaction
public class KjAccountingEnrollService {

    @DataTablePager
    public List<BizAccountingEnroll> search(BizAccountingEnroll bae) {
        return D.bizAccountingEnrollMapper().selectPaperList(bae);
    }

    /**
     * 保存会计从业资格证考试的报名信息
     * @param bean
     * @return
     * @throws IOException
     */
    public boolean save(BizAccountingEnroll bean) throws IOException {
//        String photo = bean.getPhoto();
//        photo = photo.substring(22);
//        System.out.println(photo);
//        BASE64Decoder base64Decoder = new BASE64Decoder();
//        byte[] bytes = base64Decoder.decodeBuffer(photo);
//        String folder = PropertiesUtil.getProperties(Const.FILE_PATH_FOR_ACCOUNTING_APP);
//        File file = new File(folder + File.separator + bean.getCode()+ ".jpg");
//        FileUtils.touch(file);
//        System.out.println(file.getAbsolutePath());
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        FileUtils.writeByteArrayToFile(file, bytes);
//        bean.setPhoto(file.getAbsolutePath());
        bean.setCreateTime(new Date());
        bean.setPaymentOk(2);
        bean.setStatus(4);
        return D.bizAccountingEnrollMapper().insertSelective(bean) == 1;
    }

    public BizAccountingEnroll load(Integer pk) {
        return D.bizAccountingEnrollMapper().selectByPrimaryKey(pk);
    }

    public boolean remove(Integer pk) {
        return D.bizAccountingEnrollMapper().deleteByPrimaryKey(pk) == 1;
    }

    /**
     * 保存是否已经为学生代报名
     * 保存成功，返回true；否则直接抛出异常
     *
     * @param bean
     * @return
     */
    public Boolean enrollStatus(BizAccountingEnroll bean) {
        D.bizAccountingEnrollMapper().updateByPrimaryKeySelective(bean);
        return true;
    }
}
