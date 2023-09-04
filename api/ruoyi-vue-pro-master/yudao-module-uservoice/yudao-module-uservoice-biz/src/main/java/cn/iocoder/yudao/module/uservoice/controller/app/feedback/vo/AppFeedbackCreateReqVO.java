package cn.iocoder.yudao.module.uservoice.controller.app.feedback.vo;

import cn.iocoder.yudao.module.uservoice.controller.admin.feedback.vo.FeedbackBaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "App - 用户反馈创建 Request VO")
@Data
@ToString(callSuper = true)
public class AppFeedbackCreateReqVO {

    @Schema(description = "内容")
    private String content;

    @Schema(description = "反馈类型", example = "1")
    private Integer feedbackType;
}
