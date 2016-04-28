package com.hzc.top.ctrl.server;

import com.hzc.top.util.factory.alias.S;
import com.hzc.top.util.factory.alias.W;
import com.hzc.top.vo.QuestionVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by yinbin on 2015/5/8.
 */
public class LpExportExcelCtrl {

    public void exportForAllQuestion() throws IOException {
//        Integer currentNum, Integer pageSize)
        Integer currentNum = W.getInteger("currentNum");
        Integer pageSize = 500;
//        int start = currentNum * pageSize + 1;
        List<QuestionVO> list = S.lpQuestionService().getAllQuestionsForLimit(currentNum, pageSize);
        String templateFileName = "template.xlsx";
        String downloadFileName = new String(("总体库" +( currentNum + 1) + "至" + (currentNum + list.size()) + "题").getBytes("UTF-8"), "ISO8859_1");
        W.writeExcel(templateFileName, downloadFileName, list);
    }

}
