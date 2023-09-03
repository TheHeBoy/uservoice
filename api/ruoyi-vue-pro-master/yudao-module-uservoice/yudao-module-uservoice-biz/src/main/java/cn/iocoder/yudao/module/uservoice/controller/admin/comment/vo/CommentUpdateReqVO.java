package cn.iocoder.yudao.module.uservoice.controller.admin.comment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 评论更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommentUpdateReqVO extends CommentBaseVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "22079")
    @NotNull(message = "主键不能为空")
    private Long id;

}
