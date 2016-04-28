package com.hzc.top.service.app;

import com.hzc.top.model.*;
import com.hzc.top.util.factory.alias.D;
import com.hzc.top.util.factory.alias.S;
import com.hzc.framework.ssh.service.Transaction;
import com.hzc.framework.util.DesUtil;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by LiuJY on 2015/3/26.
 */
@Transaction
public class EnSyncDataService {

    private static final Logger log = Logger.getLogger(EnSyncDataService.class);


    public EnCategory loadCategoryById(Integer id){
        return D.enCategoryMapper().selectByPrimaryKey(id);
    }

    /**
     * 查询所有category内容的信息
     * 参数：无
     * 返回List<EnCategory>
     *
     * @return
     */
    public List<EnCategory> getAllCategory() {
        return D.enCategoryMapper().selectAll();
    }

    /**
     * 根据code查询该code下一层的所有category
     * 参数：code
     * 返回List<EnCategory>
     *
     * @param code
     * @return
     */
    public List<EnCategory> getCategoryByCode(String code) {
        int length = code.length();
        int codeLength = 0;
        switch (length) {
            case 4:
                codeLength = 8;
                break;
            case 8:
                codeLength = 12;
                break;
        }
        return D.enCategoryMapper().selectByCode(code, codeLength);
    }

    /**
     * 根据节code获取节下的所有试题
     * 试题内容为加密内容
     * 参数categoryCode
     * 返回List<EnQuestion>
     *
     * @param sectionId
     * @param phone
     * @return
     */
    public List<EnQuestion> getQuestions(String sectionId, String phone) {
        List<EnQuestion> list = getEnQuestionsByCode(sectionId);
//        String desc = desc(list, phone);
        return list;
    }

    /**
     * 加密List,通过phone获取用户的加密字段
     * 加密的String[]keys值,长度为3
     * 返回String
     *
     * @param list
     * @return
     */
    public String desc(List list, String phone) {
        SysUser user = S.appUserService().getUserByPhone(phone);
        String[] keys = user.getKey().replaceAll("，", ",").split(",");
        JSONArray jsonArr = JSONArray.fromObject(list);
        String listString = jsonArr.toString();
        listString = new DesUtil().strEnc(listString, keys[0], keys[1], keys[2]);
        return listString;
    }

    /**
     * 根据节code获取节下的所有试题
     * 参数categoryCode(sectionId)
     * 返回List<EnQuestion>
     *
     * @param code
     * @return
     */
    public List<EnQuestion> getEnQuestionsByCode(String code) {
        return D.enQuestionMapper().selectByCode(code);
    }

    /**
     * 根据节id获取其下的所有试题的material
     * 参数id : sectionId（节id）
     *
     * @param id
     * @return
     */
    public List<EnMaterialWithBLOBs> getEnMaterialsBySectionId(String id, String phone) {
        List<EnMaterialWithBLOBs> list = D.enMaterialMapper().selectBySectionId(id);
//        String desc = desc(list, phone);
        return list;
    }

    /**
     * 根据节id获取其下的所有试题的option
     * 参数id：sectionId（节id）
     *
     * @param id
     * @param phone
     * @return
     */
    public List<EnOption> getEnOptionsBySectionId(String id, String phone) {
        return D.enOptionMapper().selectBySectionId(id);
    }

    /**
     * 根据节id获取其下的所有试题的resolution
     * 参数id：sectionId（节id）
     *
     * @param id
     * @param phone
     * @return
     */
    public List<EnResolution> getEnResolutionsBySectionId(String id, String phone) {
        return D.enResolutionMapper().selectBySectionId(id);
    }

}
