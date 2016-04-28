package com.hzc.top.service.app;

import com.hzc.framework.ssh.service.Transaction;
import com.hzc.top.model.HsCategory;
import com.hzc.top.util.factory.alias.D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinbin on 2015/4/3.
 */
@Transaction
public class HsCategoryService {


    /**
     * 根据 父分类id查询旗下所有的数据
     *
     * @param parentId
     * @return
     */
    public List<HsCategory> listByPid(Integer parentId) {
        return D.hsCategoryMapper().selectByParentId(parentId);
    }

    /**
     * 根据id获取专业
     * @param id
     * @return
     */
    public HsCategory loadById(Integer id){
        return D.hsCategoryMapper().selectByPrimaryKey(id);
    }


 /*
    public HsCategory listAll(String phone) {
        HsCategory hsCategory = new HsCategory();
        List<HsCategory> hsCategories = D.hsCategoryMapper().selectAll(phone);
        Integer parentId = 0; //
        boolean isLeaf = digui(hsCategory, hsCategories, parentId);
        hsCategory.setLeaf(isLeaf);
        return hsCategory;
    }

    private boolean digui(HsCategory hsCategory, List<HsCategory> hsCategories, Integer parentId) {
        boolean isLeaf = true;
        for (HsCategory category : hsCategories) {
            if (parentId == category.getParentId()) {
                isLeaf = false;
                ArrayList<HsCategory> items = hsCategory.getItems();
                if (null == items) {
                    items = new ArrayList<HsCategory>();
                    hsCategory.setItems(items);
                }
                boolean isLeaf2 = digui(category, hsCategories, category.getId());
                category.setLeaf(isLeaf2);
                items.add(category);
            }
        }
        return isLeaf;
    }*/

}
