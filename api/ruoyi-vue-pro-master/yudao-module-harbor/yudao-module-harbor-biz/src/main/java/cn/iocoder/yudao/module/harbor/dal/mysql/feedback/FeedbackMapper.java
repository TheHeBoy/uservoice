package cn.iocoder.yudao.module.harbor.dal.mysql.feedback;

import java.util.*;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.harbor.controller.app.feedback.vo.AppFeedbackPageReqVO;
import cn.iocoder.yudao.module.harbor.dal.dataobject.feedback.FeedbackDO;
import cn.iocoder.yudao.module.harbor.enums.feedback.FeedbackOrderEnum;
import cn.iocoder.yudao.module.harbor.enums.feedback.FeedbackReplyStateEnum;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.harbor.controller.admin.feedback.vo.*;

/**
 * 用户反馈 Mapper
 */
@Mapper
public interface FeedbackMapper extends BaseMapperX<FeedbackDO> {

    default PageResult<FeedbackDO> selectPage(FeedbackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FeedbackDO>()
                .betweenIfPresent(FeedbackDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(FeedbackDO::getReplyState, reqVO.getReplyState())
                .eqIfPresent(FeedbackDO::getFeedbackTagId, reqVO.getFeedbackTagId())
                .orderByDesc(FeedbackDO::getId));
    }

    default PageResult<FeedbackDO> selectPage(AppFeedbackPageReqVO reqVO) {
        LambdaQueryWrapperX<FeedbackDO> wrapperX = new LambdaQueryWrapperX<FeedbackDO>()
                .eqIfPresent(FeedbackDO::getFeedbackTagId, reqVO.getFeedbackType());

        // 添加排序
        if (ObjectUtil.equal(reqVO.getOrder(), FeedbackOrderEnum.RECOMMEND.getCode())) {
            wrapperX.orderByDesc(FeedbackDO::getLikes);
        } else {
            wrapperX.orderByDesc(FeedbackDO::getId);
        }

        return selectPage(reqVO, wrapperX);
    }

    default int updateReplyState(Long id, FeedbackReplyStateEnum replyStateEnum) {
        return updateById(FeedbackDO.builder()
                .id(id)
                .replyState(replyStateEnum.getCode())
                .build());
    }

}
