package com.jy.service.jc.questionfeedback;

import com.jy.entity.jc.analyst.Analyst;
import com.jy.entity.jc.questionfeedback.QuestionFeedback;
import com.jy.repository.jc.analyst.AnalystDao;
import com.jy.repository.jc.questionfeedback.QuestionFeedbackDao;
import com.jy.service.base.BaseServiceImp;
import com.jy.service.jc.analyst.AnalystService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by susen-pc on 2016/9/4.
 */
@Service("questionFeedbackService")
public class QuestionFeedbackServiceImp extends BaseServiceImp<QuestionFeedback> implements QuestionFeedbackService {

    @Autowired
    private QuestionFeedbackDao questionFeedbackDao;

}
