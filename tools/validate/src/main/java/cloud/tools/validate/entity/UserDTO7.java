package cloud.tools.validate.entity;

import cloud.tools.validate.annotation.FieldRely;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author 石佳
 * @date 2021/1/29 10:53
 */
@Data
@FieldRely(relyField = "faceValidation", currentField = "cardType", message = "人脸识别标志为Y时，必须输入证件类型", relyValues = {"Y"},
        relyType = FieldRely.RelyType.value2notnull, groups = {Second.class})
@FieldRely(relyField = "faceValidation", currentField = "cardNo", message = "人脸识别标志为Y时，必须输入证件号", relyValues = {"Y"},
        relyType = FieldRely.RelyType.value2notnull, groups = {Second.class})
public class UserDTO7 {
    private Integer userId;
    private String name;
    private Integer age;
    // 是否通过人脸识别
    @NotBlank(groups = First.class)
    @Pattern(regexp = "^Y|N$",groups = First.class)
    private String faceValidation;

    private String cardType;
    private String cardNo;

}
