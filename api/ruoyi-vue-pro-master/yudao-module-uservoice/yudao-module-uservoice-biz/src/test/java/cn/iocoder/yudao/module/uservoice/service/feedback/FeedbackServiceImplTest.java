package cn.iocoder.yudao.module.uservoice.service.feedback;

import cn.iocoder.yudao.module.uservoice.service.appuser.AppUserService;
import cn.iocoder.yudao.module.uservoice.service.appuser.AppUserServiceImpl;
import cn.iocoder.yudao.module.uservoice.service.feedback.FeedbackServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import javax.annotation.Resource;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.uservoice.controller.admin.feedback.vo.*;
import cn.iocoder.yudao.module.uservoice.dal.dataobject.feedback.FeedbackDO;
import cn.iocoder.yudao.module.uservoice.dal.mysql.feedback.FeedbackMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.springframework.context.annotation.Import;
import java.util.*;
import static cn.iocoder.yudao.module.uservoice.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.LocalDateTimeUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link FeedbackServiceImpl} 的单元测试类
 *
 *  hehong
 */
@Import({FeedbackServiceImpl.class, AppUserServiceImpl.class})
public class FeedbackServiceImplTest extends BaseDbUnitTest {

    @Resource
    private FeedbackServiceImpl feedbackService;

    @Resource
    private FeedbackMapper feedbackMapper;

    @Test
    public void testCreateFeedback_success() {
        // 准备参数
        FeedbackCreateReqVO reqVO = randomPojo(FeedbackCreateReqVO.class);

        // 调用
        Long feedbackId = feedbackService.createFeedback(reqVO);
        // 断言
        assertNotNull(feedbackId);
        // 校验记录的属性是否正确
        FeedbackDO feedback = feedbackMapper.selectById(feedbackId);
        assertPojoEquals(reqVO, feedback);
    }

    @Test
    public void testUpdateFeedback_success() {
        // mock 数据
        FeedbackDO dbFeedback = randomPojo(FeedbackDO.class);
        feedbackMapper.insert(dbFeedback);// @Sql: 先插入出一条存在的数据
        // 准备参数
        FeedbackUpdateReqVO reqVO = randomPojo(FeedbackUpdateReqVO.class, o -> {
            o.setId(dbFeedback.getId()); // 设置更新的 ID
        });

        // 调用
        feedbackService.updateFeedback(reqVO);
        // 校验是否更新正确
        FeedbackDO feedback = feedbackMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, feedback);
    }

    @Test
    public void testUpdateFeedback_notExists() {
        // 准备参数
        FeedbackUpdateReqVO reqVO = randomPojo(FeedbackUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> feedbackService.updateFeedback(reqVO), FEEDBACK_NOT_EXISTS);
    }

    @Test
    public void testDeleteFeedback_success() {
        // mock 数据
        FeedbackDO dbFeedback = randomPojo(FeedbackDO.class);
        feedbackMapper.insert(dbFeedback);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbFeedback.getId();

        // 调用
        feedbackService.deleteFeedback(id);
       // 校验数据不存在了
       assertNull(feedbackMapper.selectById(id));
    }

    @Test
    public void testDeleteFeedback_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> feedbackService.deleteFeedback(id), FEEDBACK_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFeedbackPage() {
       // mock 数据
       FeedbackDO dbFeedback = randomPojo(FeedbackDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setUid(null);
           o.setContent(null);
           o.setLikes(null);
           o.setFeedbackType(null);
       });
       feedbackMapper.insert(dbFeedback);
       // 测试 createTime 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setCreateTime(null)));
       // 测试 uid 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setUid(null)));
       // 测试 content 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setContent(null)));
       // 测试 likes 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setLikes(null)));
       // 测试 feedbackType 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setFeedbackType(null)));
       // 准备参数
       FeedbackPageReqVO reqVO = new FeedbackPageReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setUid(null);
       reqVO.setContent(null);
       reqVO.setLikes(null);
       reqVO.setFeedbackType(null);

       // 调用
       PageResult<FeedbackDO> pageResult = feedbackService.getFeedbackPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbFeedback, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetFeedbackList() {
       // mock 数据
       FeedbackDO dbFeedback = randomPojo(FeedbackDO.class, o -> { // 等会查询到
           o.setCreateTime(null);
           o.setUid(null);
           o.setContent(null);
           o.setLikes(null);
           o.setFeedbackType(null);
       });
       feedbackMapper.insert(dbFeedback);
       // 测试 createTime 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setCreateTime(null)));
       // 测试 uid 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setUid(null)));
       // 测试 content 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setContent(null)));
       // 测试 likes 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setLikes(null)));
       // 测试 feedbackType 不匹配
       feedbackMapper.insert(cloneIgnoreId(dbFeedback, o -> o.setFeedbackType(null)));
       // 准备参数
       FeedbackExportReqVO reqVO = new FeedbackExportReqVO();
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));
       reqVO.setUid(null);
       reqVO.setContent(null);
       reqVO.setLikes(null);
       reqVO.setFeedbackType(null);

       // 调用
       List<FeedbackDO> list = feedbackService.getFeedbackList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbFeedback, list.get(0));
    }

}