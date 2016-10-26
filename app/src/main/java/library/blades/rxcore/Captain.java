package library.blades.rxcore;

/**
 * ClassName: Captain<p>
 * Author: blades<p>
 * Des: Captain<p>
 * CreateTime: 2016/10/20 14:32<p>
 * UpdateTime: 2016/10/20 14:32<p>
 * GitHub: https://github.com/AlphaKnife
 */

public class Captain {

    /**
     * cap_id : 168105
     * sal_id : 637
     * cap_name : 测试
     * add_time : 2016-09-21 16:01:23
     * cap_tel : 13541253654
     * dial_num : 0
     * last_time : 0
     * is_cap : 1
     */

    public String cap_id;
    public String sal_id;
    public String cap_name;
    public String add_time;
    public String cap_tel;
    public String dial_num;
    public String last_time;
    public String is_cap;

    @Override
    public String toString() {
        return "Captain{" +
                "cap_id='" + cap_id + '\'' +
                ", sal_id='" + sal_id + '\'' +
                ", cap_name='" + cap_name + '\'' +
                ", add_time='" + add_time + '\'' +
                ", cap_tel='" + cap_tel + '\'' +
                ", dial_num='" + dial_num + '\'' +
                ", last_time='" + last_time + '\'' +
                ", is_cap='" + is_cap + '\'' +
                '}';
    }
}
