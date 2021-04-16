package cloud.tools.excel.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author 石佳
 * @since 2020/08/06
 */
@Data
public class CustomerList {
    @Excel(name = "姓名", orderNum = "0")
    private String name;
    @Excel(name = "证件类型", replace = {"身份证_1"}, orderNum = "1")
    private String identifyType;
    @Excel(name = "证件号码", orderNum = "2")
    private String identifyNo;
    @Excel(name = "手机号1", orderNum = "3")
    private String phoneA;
    @Excel(name = "手机号2", orderNum = "4")
    private String phoneB;
    @Excel(name = "手机号3", orderNum = "5")
    private String phoneC;
    @Excel(name = "固定电话", orderNum = "6")
    private String telephone;
    @Excel(name = "电子邮箱", orderNum = "7")
    private String email;
    @Excel(name = "身份证地址", orderNum = "8")
    private String idcardAdress;
    @Excel(name = "户籍地址", orderNum = "9")
    private String householdAddress;
    @Excel(name = "居住地址", orderNum = "10")
    private String liveAddress;
    @Excel(name = "工作地址", orderNum = "11")
    private String workAddress;
}
