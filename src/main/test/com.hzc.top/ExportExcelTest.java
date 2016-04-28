package com.hzc.top;
import com.hzc.framework.util.ExcelUtil;
import com.hzc.top.util.factory.alias.S;
import com.hzc.top.vo.QuestionVO;
import org.junit.Test;

import java.util.List;

/**
 * Created by yinbin on 2015/5/8.
 */
public class ExportExcelTest extends SupperJunit {

    @Test
    public void test1(){
        Integer userId = 1056;
        List<QuestionVO> list = S.lpQuestionService().getAllQuestionsForLimit(0, 20);
        String templateFileName = "template.xlsx";
        String resultFileName = "result.xlsx";
        new ExcelUtil().createExcel(templateFileName,list,resultFileName);
    }
}
